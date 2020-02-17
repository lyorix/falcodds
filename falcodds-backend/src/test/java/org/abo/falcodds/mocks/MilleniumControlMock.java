package org.abo.falcodds.mocks;

import io.quarkus.test.Mock;
import org.abo.falcodds.domain.control.MilleniumControl;

import javax.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class MilleniumControlMock extends MilleniumControl {

    @Override
    public String getArrival() {
        return "Endor";
    }

    @Override
    public String getDeparture() {
        return "Tatooine";
    }

    @Override
    public Integer getAutonomy() {
        return 6;
    }
}
