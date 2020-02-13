package org.abo.falcodds.control;

import org.abo.falcodds.helper.RouteHelper;
import org.abo.falcodds.model.Route;
import org.abo.falcodds.utils.SQLiteConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class RouteControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteControl.class);

    @Inject
    SQLiteConnector sqliteConnector;

    @Inject
    RouteHelper routeHelper;

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
            routes.addAll(routeHelper.createLandingRoutes(routes));
            return routes;
        } catch (SQLException e) {
            LOGGER.error("error while retrieving routes", e);
            return null;
        }
    }
}
