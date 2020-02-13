package org.abo.falcodds.helper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OddsHelper {

    public Long computeOdds(Integer captureCounter) {
        Double probability = captureCounter == 0 ? 0D : 1.0 / 10;
        for (int i = 1; i < captureCounter; i++) {
            probability = probability + Math.pow(9, i) / Math.pow(10, i + 1);
        }
        return 100 - Math.round(probability * 100);
    }
}
