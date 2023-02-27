package org.example.trigquizzer.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import org.example.trigquizzer.model.AnswerParser;
import org.example.trigquizzer.repo.JdbcLinkRepository;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;


public class HomeController implements Initializable {
    @FXML
    public Button resources;
    @FXML
    public VBox layout;
    @FXML
    public ListView categories;
    private JdbcLinkRepository repo;
    private AnswerParser parser;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        parser = new AnswerParser();

        repo = new JdbcLinkRepository();

        categories.getItems().addAll(Arrays.asList(
                "Trigonometry",
                "Algebra 2",
                "Calculus",
                "Linear Algebra",
                "Geometry"
        ));
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