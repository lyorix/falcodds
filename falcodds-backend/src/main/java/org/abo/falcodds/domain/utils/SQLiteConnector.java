package org.abo.falcodds.domain.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@ApplicationScoped
public class SQLiteConnector {

    private static final Logger LOGGER = LoggerFactory.getLogger(SQLiteConnector.class);

    private Connection connection;

    public void openConnection(String dbPath) {
        try {
            String url = new StringBuilder("jdbc:sqlite:").append(dbPath).toString();
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            LOGGER.error("error while opening database connection", e);
        }
    }

    @PreDestroy
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            LOGGER.error("error while closing database connection", e);
        }
    }

    public Statement createStatement() throws SQLException {
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);
        return statement;
    }
}
