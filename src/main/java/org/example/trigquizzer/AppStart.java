package org.example.trigquizzer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AppStart extends Application {

    final String APP_NAME = "Math Mountain";
    final int WIDTH = 600, HEIGHT = 400;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(AppStart.class.getResource("home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        scene.getStylesheets().add(AppStart.class.getResource("css/Application.css").toExternalForm());
        stage.getIcons().add(new Image(AppStart.class.getResource("imgs/Math-Mountain.png").toExternalForm()));

        stage.setTitle(APP_NAME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}