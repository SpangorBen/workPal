package main.java.com.workPal.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {

    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final String PROPERTIES_FILE = "database.properties";

    private final List<Connection> availableConnections;
    private final List<Connection> usedConnections;

    private ConnectionPool() {
        this.availableConnections = new ArrayList<>();
        this.usedConnections = new ArrayList<>();
        initializePool();
    }

    public static ConnectionPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public synchronized Connection getConnection() throws SQLException {
        if (availableConnections.isEmpty()) {
            if (usedConnections.size() < MAX_POOL_SIZE) {
                availableConnections.add(createConnection());
            } else {
                throw new SQLException("Connection pool exhausted. No available connections.");
            }
        }
        Connection connection = availableConnections.remove(0);
        usedConnections.add(connection);
        return connection;
    }

    public synchronized void releaseConnection(Connection connection) {
        if (usedConnections.remove(connection)) {
            availableConnections.add(connection);
        }
    }

    public synchronized void closeAllConnections() {
        for (Connection connection : availableConnections) {
            closeConnection(connection);
        }
        availableConnections.clear();
        for (Connection connection : usedConnections) {
            closeConnection(connection);
        }
        usedConnections.clear();
    }

    private void initializePool() {
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            availableConnections.add(createConnection());
        }
    }

    private Connection createConnection() {
        try (InputStream input = ConnectionPool.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Unable to find " + PROPERTIES_FILE + " file.");
            }
            Properties props = new Properties();
            props.load(input);

            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String username = props.getProperty("username");
            String password = props.getProperty("password");

            Class.forName(driver);
            return DriverManager.getConnection(url, username, password);

        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver not found: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating database connection: " + e.getMessage(), e);
        }
    }

    private void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    private static class SingletonHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}