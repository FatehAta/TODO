package com.atatree.todo.controllers.component;

import com.atatree.todo.event.ButtonClickListener;
import com.atatree.todo.model.TodoElement;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TodoCardController implements Initializable {

    @FXML
    public CheckBox complete_checkBox;

    @FXML
    public Label dateTime_label;

    @FXML
    public Button delete_button;

    @FXML
    public Circle priority_shape;

    @FXML
    public HBox root;

    @FXML
    public Label task_label;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TodoElement element = TodoElement.todoList.getLast();
        task_label.setText(element.getName());
        dateTime_label.setText(element.getDateTime().toString());
        switch (element.getPriority()) {
            case LOW -> priority_shape.setFill(Color.GREEN);
            case MEDIUM -> priority_shape.setFill(Color.YELLOW);
            case HIGH -> priority_shape.setFill(Color.RED);
            default -> priority_shape.setFill(Color.BLACK);
        }

        delete_button.setOnAction(e -> {
            ButtonClickListener.trigger(task_label);
        });

    }


    public static Node load() {
        Node node;
        try {
            node = FXMLLoader.load(TodoCardController.class.getResource("/fxml/component/TodoCard.fxml"));
            return node;
        } catch (IOException e) {
            e.getCause();
        }
        return null;
    }
}
