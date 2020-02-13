package org.abo.falcodds.control;

import org.abo.falcodds.helper.BountyHuntersHelper;
import org.abo.falcodds.helper.RouteHelper;
import org.abo.falcodds.helper.TravelHelper;
import org.abo.falcodds.model.EmpireConfiguration;
import org.abo.falcodds.model.Route;
import org.abo.falcodds.model.Travel;
import org.abo.falcodds.utils.MilleniumHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
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
    MilleniumHandler milleniumHandler;

    @Inject
    BountyHuntersHelper bountyHuntersHelper;

    @Inject
    TravelHelper travelHelper;

    public List<Travel> getTravels(EmpireConfiguration empirePlan) {
        List<Route> allRoutes = routeControl.getAllRoutes();
        LOGGER.debug("get travelList for routes: " + allRoutes);

        List<Travel> travelList = createInitialTravels(allRoutes, empirePlan);
        while (travelList.size() > 0 && travelList.stream().anyMatch(travel -> !travelHelper.hasReached(travel, milleniumHandler.getArrival()))) {
            travelList = createInnerTravels(travelList, allRoutes, empirePlan);
        }

        LOGGER.debug("possible travelList found: " + travelList);
        return travelList;
    }

    private List<Travel> createInitialTravels(List<Route> routeList, EmpireConfiguration empirePlan) {
        List<Route> firstRoutes = routeHelper.createInnerRoutes(routeList, milleniumHandler.getDeparture());
        return firstRoutes.stream().filter(route -> isRouteValid(empirePlan, route, milleniumHandler.getAutonomy(), 0))
                .map(route -> createTravel(empirePlan, null, route))
                .collect(Collectors.toList());
    }

    private List<Travel> createInnerTravels(List<Travel> travels, List<Route> routeList, EmpireConfiguration empirePlan) {
        List<Travel> innerTravels = new ArrayList<>();
        for (Travel travel : travels) {
            if (!travelHelper.hasReached(travel, milleniumHandler.getArrival())) {
                List<Route> innerRoutes = routeHelper.createInnerRoutes(routeList, travelHelper.getLastRoute(travel).get().getDestination());
                innerTravels.addAll(
                        innerRoutes.stream().filter(route -> isRouteValid(empirePlan, route, travel.getRemainingAutonomy(), travelHelper.getTotalTravelTime(travel)))
                                .map(route -> createTravel(empirePlan, travel, route))
                                .collect(Collectors.toList())
                );
            } else {
                innerTravels.add(travel);
            }
        }
        return innerTravels;
    }

    private boolean isRouteValid(EmpireConfiguration empirePlan, Route route, Integer remainingAutonomy, Integer traveledTime) {
        return (routeHelper.isLandingRoute(route)
                || remainingAutonomy - route.getTravelTime() >= 0)
                && traveledTime + route.getTravelTime() <= empirePlan.getCountdown();
    }

    private Travel createTravel(EmpireConfiguration empirePlan, Travel existingTravel, Route route) {
        Travel travel = new Travel();
        if (existingTravel != null) {
            travel.setRoutes(new ArrayList<>(existingTravel.getRoutes()));
            travel.setRemainingAutonomy(existingTravel.getRemainingAutonomy());
            travel.setCaptureCounter(existingTravel.getCaptureCounter());
        } else {
            travel.setRemainingAutonomy(milleniumHandler.getAutonomy());
        }
        travel.addRoute(route);
        travel.setRemainingAutonomy(routeHelper.isLandingRoute(route) ? milleniumHandler.getAutonomy() : travel.getRemainingAutonomy() - route.getTravelTime());
        travel.setCaptureCounter(bountyHuntersHelper.hasBountyHunters(empirePlan, route.getDestination(), travelHelper.getTotalTravelTime(travel)) ? travel.getCaptureCounter() + 1 : travel.getCaptureCounter());
        return travel;
    }
}
