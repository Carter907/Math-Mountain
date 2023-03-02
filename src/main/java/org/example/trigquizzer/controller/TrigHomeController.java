package org.example.trigquizzer.controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.example.trigquizzer.AppStart;
import org.example.trigquizzer.model.Category;
import org.example.trigquizzer.repo.JdbcRepository;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrigHomeController implements Initializable {
    @FXML
    public ListView<Category> trigCategories;
    @FXML
    public Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        new JdbcRepository<>(Category.class).insert(new Category(1, "Trigonometric Proofs"));
        trigCategories.getSelectionModel().selectedItemProperty().addListener(this::onTrigCategorySelected);




    }
    public void onTrigCategorySelected(ObservableValue<? extends Category> observableValue, Category oldValue, Category newValue) {

//        switch (newValue.name()) {
//            case "Trigonometric Proofs" -> {
//                System.out.println("proofs");
//
//            }
//
//        }

    }

    @FXML
    public void onPressedBack(ActionEvent event) throws IOException {
        Scene scene = trigCategories.getScene();

        scene.setRoot(new FXMLLoader().load(AppStart.class.getResourceAsStream("home-view.fxml")));
    }
}
