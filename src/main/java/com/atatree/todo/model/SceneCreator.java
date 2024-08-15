package com.atatree.todo.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SceneCreator extends Stage {

    private boolean isResizeable;
    private boolean isTransparent;
    private boolean isDraggable;
    private double x,y = 0;
    private Stage stage;

    public SceneCreator(boolean setResizeable, boolean setTransparent, boolean setDraggable) {
        this.isResizeable = setResizeable;
        this.isTransparent = setTransparent;
        this.isDraggable = setDraggable;
        loadScene();
    }

    private void stageSetting() {
        if (isTransparent) initStyle(StageStyle.TRANSPARENT);
        setResizable(isResizeable);
        if (isDraggable) {
            getScene().setOnMousePressed(e -> {
                y = e.getSceneY();
                x = e.getSceneX();
            });
            getScene().setOnMouseDragged(e -> {
                this.setY(e.getScreenY() - y);
                this.setX(e.getScreenX() - x);
            });
        }
    }

    private void loadScene() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/TodoCreatePopup.fxml"));
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            setScene(scene);
            stageSetting();
            show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean istResizeable() {
        return isResizeable;
    }

    public void setResizeable(boolean resizeable) {
        this.isResizeable = resizeable;
    }

    public boolean isTransparent() {
        return isTransparent;
    }

    public void setTransparent(boolean transparent) {
        this.isTransparent = transparent;
    }

    public boolean isDraggable() {
        return isDraggable;
    }

    public void setDraggable(boolean draggable) {
        this.isDraggable = draggable;
    }
}
