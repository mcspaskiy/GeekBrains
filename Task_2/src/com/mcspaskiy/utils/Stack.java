package com.mcspaskiy.utils;

public interface Stack<T> {
    void push(T element);

    T peek();

    T pop();

    boolean isEmpty();
}
