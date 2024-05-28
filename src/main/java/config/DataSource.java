package config;

import util.ApplicationProperties;

import java.sql.*;

public class DataSource {
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(
                    ApplicationProperties.DB_URL,
                    ApplicationProperties.DB_USERNAME,
                    ApplicationProperties.DB_PASSWORD);

        } catch (Exception e) {
            System.out.println("Error In DataSource Attribute!");
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
