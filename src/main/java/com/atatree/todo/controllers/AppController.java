package com.atatree.todo.controllers;

import com.atatree.todo.App;
import com.atatree.todo.event.ButtonClickEvent;
import com.atatree.todo.event.ButtonClickListener;
import com.atatree.todo.model.TodoElement;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AppController implements Initializable {

    @FXML
    public Button newTask_button;

    @FXML
    public BorderPane root;

    @FXML
    public VBox todoView_vbox;
    @FXML
    public HBox categoryHBox;

    private double x,y = 0;

    private final Set<ToggleButton> toggleButtons = new HashSet<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
        categoryInit();
        ButtonClickListener.addListener(e -> {

            if (e instanceof HBox) {
                todoView_vbox.getChildren().add(((Node) e));
            }

            if (!(e instanceof Label)) return;

            first:
            for (Node child : todoView_vbox.getChildren()) {

                if (!(child instanceof HBox)) continue;

                for (Node node : ((HBox) child).getChildren()) {
                    if (!(node instanceof VBox)) continue;

                    for (Node vbox : ((VBox) node).getChildren()) {

                        if (((Label) vbox).textProperty().get().equals(((Label) e).textProperty().get())) {

                            todoView_vbox.getChildren().remove(child);
                            break first;
                        }
                    }

                }
            }
        });
    }

    private void init() {

        root.setOnMousePressed(e -> {
            x = e.getSceneX();
            y = e.getSceneY();
        });

        root.setOnMouseDragged(e -> {
            App.stage.setY(e.getScreenY() - y);
            App.stage.setX(e.getScreenX() - x);
        });

        newTask_button.setOnAction(e -> {
            if (TodoTaskPopupController.isActive) return;
            TodoTaskPopupController.isActive = true;
            loadTaskPopup();

        });

    }

    private void categoryInit() {
         categoryHBox.getChildren().forEach(e -> toggleButtons.add((ToggleButton) e));
         toggleButtons.forEach(e -> {
             e.setOnAction(action -> {
                 for (ToggleButton toggleButton : toggleButtons) {
                     if (!toggleButton.textProperty().getValue().equals(e.textProperty().getValue())) {
                         toggleButton.setSelected(false);
                     }
                 }
             });
         });
    }

    private void loadTaskPopup() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TodoCreatePopup.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            Stage stage = new Stage(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.getCause();
        }
    }


}
