package com.atatree.todo.event;

import java.util.ArrayList;
import java.util.List;

public class ButtonClickListener {

    private static final List<ButtonClickEvent> listenerList = new ArrayList<>();

    public static void addListener(ButtonClickEvent event) {
        listenerList.add(event);
    }

    public static <T> void trigger(T object) {
        for (ButtonClickEvent event : listenerList) {
            event.task(object);
        }
    }
}
