package com.atatree.todo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoElement {

    public static List<TodoElement> todoList = new ArrayList<>();

    public enum Priority {
        HIGH,
        MEDIUM,
        LOW
    }

    private String name;
    private LocalDateTime dateTime;
    private Priority priority;

    public TodoElement(String name, LocalDateTime dateTime, Priority priority) {
        this.name = name;
        this.dateTime = dateTime;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "TodoElement{" +
                "name='" + name + '\'' +
                ", dateTime=" + dateTime +
                ", priority=" + priority +
                '}';
    }
}
