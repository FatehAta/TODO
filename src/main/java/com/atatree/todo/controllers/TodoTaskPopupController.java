package com.atatree.todo.controllers;

import com.atatree.todo.controllers.component.TodoCardController;
import com.atatree.todo.event.ButtonClickListener;
import com.atatree.todo.model.TodoElement;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class TodoTaskPopupController implements Initializable {

    @FXML
    public TextField todoTextField;

    @FXML
    public Button back_button;

    @FXML
    public ChoiceBox<String> priority_choiceBox;

    @FXML
    public AnchorPane root;

    @FXML
    public Button todo_Button;

    public static boolean isActive = false;

    private double x, y = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    private void init() {
        todo_Button.setOnAction(e -> {
            if (todoTextField.textProperty().get().isEmpty()) return;
            ((Stage) back_button.getScene().getWindow()).close();
            isActive = false;
            TodoElement.todoList.add(new TodoElement(todoTextField.textProperty().get(), LocalDateTime.now().withNano(0), TodoElement.Priority.valueOf(priority_choiceBox.getValue())));
            ButtonClickListener.trigger(TodoCardController.load());
        });

        back_button.setOnAction(e -> {
            ((Stage) back_button.getScene().getWindow()).close();
            isActive = false;
        });

        priority_choiceBox.getItems().addAll("HIGH", "MEDIUM", "LOW");
        priority_choiceBox.setValue("MEDIUM");

        root.setOnMousePressed(e -> {
            y = e.getSceneY();
            x = e.getSceneX();
        });

        root.setOnMouseDragged(e -> {
            root.getScene().getWindow().setY(e.getScreenY() - y);
            root.getScene().getWindow().setX(e.getScreenX() - x);
        });

    }
}
