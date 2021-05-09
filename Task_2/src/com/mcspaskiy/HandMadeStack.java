package com.mcspaskiy;

import java.util.ArrayList;
import java.util.List;

public class HandMadeStack<T> implements Stack<T> {
    private final List<T> stack;

    public HandMadeStack() {
        this.stack = new ArrayList<>();
    }

    @Override
    public void push(T element) {
        stack.add(element);
    }

    @Override
    public T peek() {
        return stack.get(stack.size() - 1);
    }

    public T pop() {
        int lastIndex = stack.size() - 1;
        T topElement = stack.get(lastIndex);
        stack.remove(lastIndex);
        return topElement;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
