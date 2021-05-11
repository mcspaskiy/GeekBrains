package com.mcspaskiy.core;

import com.mcspaskiy.utils.SimpleStack;
import com.mcspaskiy.utils.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ShuntingYardTransformer implements Transformer {
    private static final String OPERATORS = "+-*/";
    private static final String DELIMITERS = "( )" + OPERATORS;
    private Stack<String> stack;

    public ShuntingYardTransformer() {
        this.stack = new SimpleStack();
    }

    @Override
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
                stack.push(currentElement);
            } else if (isDelimiter(currentElement)) {
                if (currentElement.equals("(")) {
                    stack.push(currentElement);
                } else if (currentElement.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        resultExpression.add(stack.pop());
                        if (stack.isEmpty()) {
                            throw new RuntimeException("Incorrect expression");
                        }
                    }
                    stack.pop();
                    if (!stack.isEmpty() && isFunction(stack.peek())) {
                        resultExpression.add(stack.pop());
                    }
                } else {
                    if (currentElement.equals("-") && (prevElement.equals("") || (isDelimiter(prevElement) && !prevElement.equals(")")))) {
                        currentElement = "minus";
                    } else {
                        while (!stack.isEmpty() && (getPrecedence(currentElement) <= getPrecedence(stack.peek()))) {
                            resultExpression.add(stack.pop());
                        }
                    }
                    stack.push(currentElement);
                }
            } else {
                resultExpression.add(currentElement);
            }
            prevElement = currentElement;
        }

        while (!stack.isEmpty()) {
            if (isOperator(stack.peek())) {
                resultExpression.add(stack.pop());
            } else {
                throw new RuntimeException("Incorrect expression");
            }
        }
        return resultExpression;
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
}
