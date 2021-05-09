package com.mcspaskiy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Shunting-yard algorithm
 */
public class Calculator {
    private static final String OPERATORS = "+-*/";
    private static final String DELIMITERS = "( )" + OPERATORS;
    private List<String> stack;

    public Calculator() {
        this.stack = new ArrayList<>();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String inputExpression = calculator.readInputData();
        List<String> resultExpression = calculator.parseExpression(inputExpression);
        Double resultValue = calculator.calculateValue(resultExpression);
        for (String x : resultExpression) {
            System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(resultValue);
    }

    public List<String> parseExpression(String inputExpression) {
        List<String> resultExpression = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(inputExpression, DELIMITERS, true);
        String prevElement = "";
        String currentElement;
        while (tokenizer.hasMoreTokens()) {
            currentElement = tokenizer.nextToken();
            if (!tokenizer.hasMoreTokens() && isOperator(currentElement)) {
                throw new RuntimeException("Incorrect expression");
            }
            if (currentElement.equals(" ")) {
                continue;
            }
            if (isFunction(currentElement)) {
                pushToStack(currentElement);
            } else if (isDelimiter(currentElement)) {
                if (currentElement.equals("(")) {
                    pushToStack(currentElement);
                } else if (currentElement.equals(")")) {
                    while (!peekFromStack().equals("(")) {
                        resultExpression.add(popFromStack());
                        if (stack.isEmpty()) {
                            throw new RuntimeException("Incorrect expression");
                        }
                    }
                    popFromStack();
                    if (!stack.isEmpty() && isFunction(peekFromStack())) {
                        resultExpression.add(popFromStack());
                    }
                } else {
                    if (currentElement.equals("-") && (prevElement.equals("") || (isDelimiter(prevElement) && !prevElement.equals(")")))) {
                        currentElement = "minus";
                    } else {
                        while (!stack.isEmpty() && (getPrecedence(currentElement) <= getPrecedence(peekFromStack()))) {
                            resultExpression.add(popFromStack());
                        }
                    }
                    pushToStack(currentElement);
                }
            } else {
                resultExpression.add(currentElement);
            }
            prevElement = currentElement;
        }

        while (!stack.isEmpty()) {
            if (isOperator(peekFromStack())) {
                resultExpression.add(popFromStack());
            } else {
                throw new RuntimeException("Incorrect expression");
            }
        }
        return resultExpression;
    }

    private void pushToStack(String element) {
        stack.add(element);
    }

    private String peekFromStack() {
        return stack.get(stack.size() - 1);
    }

    private String popFromStack() {
        int lastIndex = stack.size() - 1;
        String topElement = stack.get(lastIndex);
        stack.remove(lastIndex);
        return topElement;
    }

    private Double calculateValue(List<String> resultExpression) {
        Deque<Double> localStack = new ArrayDeque<>();
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

    private boolean isDelimiter(String token) {
        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < DELIMITERS.length(); i++) {
            if (token.charAt(0) == DELIMITERS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOperator(String token) {
        if (token.equals("minus")) {
            return true;
        }
        for (int i = 0; i < OPERATORS.length(); i++) {
            if (token.charAt(0) == OPERATORS.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFunction(String token) {
        //Mock method
        return false;
    }

    private int getPrecedence(String token) {
        switch (token) {
            case "(":
                return 1;
            case "+":
            case "-":
                return 2;
            case "*":
            case "/":
                return 3;
        }
        throw new RuntimeException("Incorrect token");
    }

    private String readInputData() {
        Scanner input = new Scanner(System.in);
        System.out.print("Input data:");
        String inputValue = input.nextLine();
        input.close();
        return inputValue;
        //Mock value: return 29/ (3 + 2) * 51 - 5 + 2 * 3 - 4 * (3 - 1)
    }
}
