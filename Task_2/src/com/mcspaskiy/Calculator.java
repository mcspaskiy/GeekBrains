package com.mcspaskiy;

import java.util.List;

public class Calculator {

    public Double calculateValue(List<String> resultExpression) {
        Stack<Double> localStack = new HandMadeStack();
        for (String element : resultExpression) {
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
