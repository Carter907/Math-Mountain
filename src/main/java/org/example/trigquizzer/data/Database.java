package org.example.trigquizzer.data;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.apache.commons.io.IOUtils;
import org.example.trigquizzer.AppStart;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public enum Database {
    INSTANCE("data/app.db");
    private volatile String source;
    public static final String PATH_TO_PROGRAM_FILES;

    static {
        String pathToJar = System.getProperty("user.dir");
        PATH_TO_PROGRAM_FILES = pathToJar.substring(0,
                pathToJar.indexOf("\\", pathToJar.indexOf("Users") + "Users".length() + 1)) + "\\MathMountain\\";
    }

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

                Files.createDirectories(Path.of(PATH_TO_PROGRAM_FILES + source.substring(0, source.indexOf("app.db")-1)));

                try (FileOutputStream fileOut = new FileOutputStream(PATH_TO_PROGRAM_FILES+source);
                     InputStream sourceStream = AppStart.class.getResourceAsStream(source)) {

                    fileOut.write(IOUtils.toByteArray(sourceStream));

                }
                    connection = DriverManager.getConnection(
                            "jdbc:sqlite:" + PATH_TO_PROGRAM_FILES + source);

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
