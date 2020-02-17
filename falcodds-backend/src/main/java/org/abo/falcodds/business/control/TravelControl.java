package org.abo.falcodds.business.control;

import org.abo.falcodds.business.helper.BountyHuntersHelper;
import org.abo.falcodds.business.helper.RouteHelper;
import org.abo.falcodds.business.helper.TravelHelper;
import org.abo.falcodds.business.model.EmpireConfiguration;
import org.abo.falcodds.business.model.Travel;
import org.abo.falcodds.business.model.TravelStep;
import org.abo.falcodds.domain.control.MilleniumControl;
import org.abo.falcodds.domain.control.RouteControl;
import org.abo.falcodds.domain.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class TravelControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TravelControl.class);

    @Inject
    RouteControl routeControl;

    @Inject
    RouteHelper routeHelper;

    @Inject
    MilleniumControl milleniumControl;

    @Inject
    BountyHuntersHelper bountyHuntersHelper;

    @Inject
    TravelHelper travelHelper;

    public List<Travel> getTravels(EmpireConfiguration empirePlan) {
        List<Route> allRoutes = routeControl.getAllRoutes();
        LOGGER.debug("get travels for routes: " + allRoutes);

        List<Travel> travelList = Arrays.asList(createInitialTravel());
        while (travelList.size() > 0 && travelList.stream().anyMatch(travel -> !travelHelper.hasReached(travel, milleniumControl.getArrival()))) {
            travelList = createInnerTravels(travelList, allRoutes, empirePlan);
        }

        LOGGER.debug("possible travels found: " + travelList);
        return travelList;
    }

    private Travel createInitialTravel() {
        return new Travel(Arrays.asList(new TravelStep(milleniumControl.getDeparture(), 0)), milleniumControl.getAutonomy(), 0);
    }

    private List<Travel> createInnerTravels(List<Travel> travels, List<Route> allRoutes, EmpireConfiguration empirePlan) {
        List<Travel> innerTravels = new ArrayList<>();
        for (Travel travel : travels) {
            if (!travelHelper.hasReached(travel, milleniumControl.getArrival())) {
                List<TravelStep> innerSteps = getInnerSteps(allRoutes, travelHelper.getLastStep(travel).get().getPlanet(), empirePlan.getCountdown(), travel.getRemainingAutonomy(), travelHelper.getTotalTravelTime(travel));
                innerTravels.addAll(
                        innerSteps.stream().map(step -> {
                            List<TravelStep> steps = new ArrayList<>(travel.getSteps());
                            steps.add(step);
                            return new Travel(steps,
                                    travelHelper.getLastStep(travel).get().getPlanet().equals(step.getPlanet()) ? milleniumControl.getAutonomy() : travel.getRemainingAutonomy() - step.getTravelTime(),
                                    bountyHuntersHelper.hasBountyHunters(empirePlan, step.getPlanet(), travelHelper.getTotalTravelTime(travel) + step.getTravelTime()) ? travel.getCaptureCounter() + 1 : travel.getCaptureCounter()
                            );
                        }).collect(Collectors.toList())
                );
            } else {
                innerTravels.add(travel);
            }
        }
        return innerTravels;
    }

    private List<TravelStep> getInnerSteps(List<Route> allRoutes, String planet, Integer countdown, Integer remainingAutonomy, Integer traveledTime) {
        return allRoutes.stream().filter(route -> isInnerRoute(route, planet, countdown, remainingAutonomy, traveledTime))
                .map(route -> route.getDestination().equals(planet) ? new TravelStep(route.getOrigin(), route.getTravelTime()) : new TravelStep(route.getDestination(), route.getTravelTime()))
                .collect(Collectors.toList());
    }

    private boolean isInnerRoute(Route route, String planet, Integer countdown, Integer remainingAutonomy, Integer traveledTime) {
        return (routeHelper.isLandingRoute(route) || remainingAutonomy - route.getTravelTime() >= 0) &&
                routeHelper.isInnerRoute(route, planet) &&
                traveledTime + route.getTravelTime() <= countdown;
    }
}
