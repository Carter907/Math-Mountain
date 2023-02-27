package org.example.trigquizzer.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Database {
    INSTANCE("./Data/app.db");
    private volatile String source;

    private volatile Connection connection;

    Database(String source) {
        this.source = source;
    }
    synchronized String getSource() {
        return source;
    }
    public synchronized Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:"+source);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
