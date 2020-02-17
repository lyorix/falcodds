package org.abo.falcodds.business.control;

import org.abo.falcodds.business.helper.TravelHelper;
import org.abo.falcodds.business.model.EmpireConfiguration;
import org.abo.falcodds.business.model.Travel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class OddsControl {

    @Inject
    TravelControl travelControl;

    @Inject
    TravelHelper travelHelper;

    public Long getOdds(EmpireConfiguration empirePlan) {
        List<Travel> travels = travelControl.getTravels(empirePlan);
        return travels.size() > 0 ? computeOdds(travelHelper.getMinCaptureCounter(travels)) : 0;
    }

    Long computeOdds(Integer captureCounter) {
        Double probability = captureCounter == 0 ? 0D : 1.0 / 10;
        for (int i = 1; i < captureCounter; i++) {
            probability = probability + Math.pow(9, i) / Math.pow(10, i + 1);
        }
        return 100 - Math.round(probability * 100);
    }
}
