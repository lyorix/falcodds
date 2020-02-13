package org.abo.falcodds.control;

import org.abo.falcodds.helper.OddsHelper;
import org.abo.falcodds.helper.TravelHelper;
import org.abo.falcodds.model.EmpireConfiguration;
import org.abo.falcodds.model.Travel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class OddsControl {

    @Inject
    TravelControl travelControl;

    @Inject
    TravelHelper travelHelper;

    @Inject
    OddsHelper oddsHelper;

    public Long getOdds(EmpireConfiguration empirePlan) {
        List<Travel> travels = travelControl.getTravels(empirePlan);
        return travels.size() > 0 ? oddsHelper.computeOdds(travelHelper.getMinCaptureCounter(travels)) : 0;
    }
}
