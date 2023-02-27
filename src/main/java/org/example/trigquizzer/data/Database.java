package org.example.trigquizzer.data;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.commons.io.IOUtils;
import org.example.trigquizzer.AppStart;

import java.io.*;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public enum Database {
    INSTANCE("data/app.db");
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
                String path = "./app.db";

                Optional<ButtonType> result = new Alert(
                        Alert.AlertType.INFORMATION,
                        "about to establish a database, please move your jar file to a new folder."
                ).showAndWait();

                if (result.get().equals(ButtonType.CANCEL)) {
                    return null;
                }



                try (FileOutputStream fileOut = new FileOutputStream(path);
                     InputStream sourceStream = AppStart.class.getResourceAsStream(source)) {
                    fileOut.write(IOUtils.toByteArray(sourceStream));

                }
                    connection = DriverManager.getConnection(
                            "jdbc:sqlite:" + path);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
