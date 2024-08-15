package com.atatree.todo.event;

@FunctionalInterface
public interface ButtonClickEvent<T> {

     void task(T object);
}
