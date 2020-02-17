package org.abo.falcodds.mocks;

import io.quarkus.test.Mock;
import org.abo.falcodds.domain.control.RouteControl;
import org.abo.falcodds.domain.model.Route;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.List;

@Mock
@ApplicationScoped
public class RouteControlMock extends RouteControl {

    @Override
    public List<Route> getAllRoutes() {
        return Arrays.asList(
                new Route("Tatooine", "Dagobah", 6),
                new Route("Dagobah", "Endor", 4),
                new Route("Dagobah", "Hoth", 1),
                new Route("Hoth", "Endor", 1),
                new Route("Tatooine", "Hoth", 6),
                new Route("Tatooine", "Tatooine", 1),
                new Route("Dagobah", "Dagobah", 1),
                new Route("Hoth", "Hoth", 1),
                new Route("Endor", "Endor", 1)
        );
    }
}
