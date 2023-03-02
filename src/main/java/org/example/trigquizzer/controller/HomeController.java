package org.example.trigquizzer.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PageOrientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.example.trigquizzer.AppStart;
import org.example.trigquizzer.model.AnswerParser;
import org.example.trigquizzer.model.MathType;
import org.example.trigquizzer.repo.JdbcLinkRepository;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;

import static org.example.trigquizzer.model.MathType.*;


public class HomeController implements Initializable {
    @FXML
    public Button resources;
    @FXML
    public VBox layout;
    @FXML
    public ListView<MathType> categories;
    @FXML
    public Label categoryListTitle;
    private JdbcLinkRepository repo;
    private AnswerParser parser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        parser = new AnswerParser();

        repo = new JdbcLinkRepository();

        categories.getItems().addAll(Arrays.asList(
                ALGEBRA_2,
                GEOMETRY,
                CALCULUS,
                LINEAR_ALGEBRA,
                TRIGONOMETRY
        ));
        categories.getSelectionModel().selectedItemProperty().addListener(this::categoriesChanged);
    }

    public void categoriesChanged(
            ObservableValue<? extends MathType> observableValue,
            MathType oldVal, MathType newValue) {

        switch (newValue) {
            case TRIGONOMETRY -> {
                try {
                    FXMLLoader loader = new FXMLLoader();
                    Scene scene = categoryListTitle.getScene();
                    scene.setRoot(loader.load(AppStart.class.getResourceAsStream("trig-home-view.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    public void onResourcesPressed(ActionEvent event) {
        ContextMenu menu = new ContextMenu();
        try {
            menu.getItems().addAll(repo.findAll()
                    .stream()
                    .map(e -> {

                        Hyperlink link = new Hyperlink(e.getUrl());
                        link.setOnAction(ev -> {
                            try {
                                Desktop.getDesktop().browse(new URI(e.getUrl()));

                            } catch (URISyntaxException | IOException ex) {
                                ex.printStackTrace();
                            }
                        });
                        return new CustomMenuItem(link);
                    }).toList());
        } catch (NullPointerException | NoSuchElementException e) {
            return;
        }
        menu.show(((Node) event.getSource()).getScene().getWindow());


    }
}