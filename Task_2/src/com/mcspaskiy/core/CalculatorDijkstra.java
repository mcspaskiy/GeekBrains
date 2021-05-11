package com.mcspaskiy.core;

import com.mcspaskiy.utils.SimpleStack;
import com.mcspaskiy.utils.Stack;

import java.util.List;

public class CalculatorDijkstra implements Calculator {

    public Double calculateValue(List<String> elements) {
        Stack<Double> localStack = new SimpleStack();
        for (String element : elements) {
            switch (element) {
                case "+":
                    localStack.push(localStack.pop() + localStack.pop());
                    break;
                case "-":
                    Double value1 = localStack.pop();
                    Double value2 = localStack.pop();
                    localStack.push(value2 - value1);
                    break;
                case "*":
                    localStack.push(localStack.pop() * localStack.pop());
                    break;
                case "/":
                    value1 = localStack.pop();
                    value2 = localStack.pop();
                    localStack.push(value2 / value1);
                    break;
                case "minus":
                    localStack.push(-localStack.pop());
                    break;
                default:
                    localStack.push(Double.valueOf(element));
            }
        }
        return localStack.pop();
    }
}
