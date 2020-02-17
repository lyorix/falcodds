package org.abo.falcodds.domain.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;
import org.abo.falcodds.domain.model.MilleniumConfiguration;
import org.abo.falcodds.domain.utils.SQLiteConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class MilleniumControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(MilleniumControl.class);

    MilleniumConfiguration milleniumConfiguration;

    @Inject
    SQLiteConnector sqliteConnector;

    void onStart(@Observes StartupEvent ev) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            milleniumConfiguration = mapper.readValue(
                    getClass().getClassLoader().getResource("millenium-falcon.json"),
                    MilleniumConfiguration.class
            );
            sqliteConnector.openConnection(milleniumConfiguration.getRoutesDb());
            LOGGER.debug(milleniumConfiguration.toString());
        } catch (IOException e) {
            LOGGER.error("error while reading millenium configuration", e);
        }
    }

    public String getArrival() {
        return milleniumConfiguration.getArrival();
    }

    public String getDeparture() {
        return milleniumConfiguration.getDeparture();
    }

    public Integer getAutonomy() {
        return milleniumConfiguration.getAutonomy();
    }
}
