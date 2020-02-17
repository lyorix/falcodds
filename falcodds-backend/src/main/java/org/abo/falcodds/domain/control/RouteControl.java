package org.abo.falcodds.domain.control;

import org.abo.falcodds.domain.model.Route;
import org.abo.falcodds.domain.utils.SQLiteConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class RouteControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteControl.class);

    @Inject
    SQLiteConnector sqliteConnector;

    public List<Route> getAllRoutes() {
        try {
            ResultSet rs = sqliteConnector.createStatement().executeQuery("SELECT * from ROUTES");
            List<Route> routes = new ArrayList<>();
            while (rs.next()) {
                routes.add(new Route(
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getInt("travel_time")
                ));
            }
            routes.addAll(createLandingRoutes(routes));
            return routes;
        } catch (SQLException e) {
            LOGGER.error("error while retrieving routes", e);
            return null;
        }
    }

    List<Route> createLandingRoutes(List<Route> routes) {
        List<String> planets = routes.stream().map(r -> r.getOrigin()).collect(Collectors.toList());
        planets.addAll(routes.stream().map(r -> r.getDestination()).collect(Collectors.toList()));
        return planets.stream().distinct().map(planet -> new Route(planet, planet, 1)).collect(Collectors.toList());
    }
}
