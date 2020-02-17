package org.abo.falcodds.mocks;

import io.quarkus.test.Mock;
import org.abo.falcodds.domain.utils.SQLiteConnector;

import javax.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class SQLiteConnectorMock extends SQLiteConnector {

    @Override
    public void openConnection(String dbPath) {
    }

    @Override
    public void closeConnection() {
    }
}
