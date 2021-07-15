package com.mcspaskiy;


import com.mcspaskiy.utils.Converter;
import com.mcspaskiy.utils.DataReader;
import com.mcspaskiy.utils.Evaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Launcher {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        String inputExpression = calculator.readExpression(consoleReadFunction);
        List<String> resultExpression = calculator.convert(convertFunction);
        Double resultValue = calculator.calculate(calcFunction, s);
    }

    public void show(List<String> values) {
        values.forEach((x) -> System.out.println(x + " "));
    }

    public void show(Object value) {
        System.out.println();
        System.out.println(value);
    }

    /**
     * Description of data-read function
     */
    static DataReader consoleReadFunction = () -> {
        System.out.println("Input data:");
        String inputValue = new Scanner(System.in).nextLine();
        if ("exit".equalsIgnoreCase(inputValue)) {
            System.exit(0);
        }
        return inputValue;
    };

    static Evaluator calcFunction = (DataReader readFunction) -> {
        List<String> elements = convert(readFunction);
        Stack<Double> stack = new Stack<>();
        for (String element : elements) {
            switch (element) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    Double value1 = stack.pop();
                    Double value2 = stack.pop();
                    stack.push(value2 - value1);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    value1 = stack.pop();
                    value2 = stack.pop();
                    stack.push(value2 / value1);
                    break;
                case "minus":
                    stack.push(-stack.pop());
                    break;
                default:
                    stack.push(Double.valueOf(element));
            }
        }
        Double pop = stack.pop();
        System.out.println(pop);
        return pop;
    };

    //private static final String OPERATORS = "+-*/";
    //private static final String DELIMITERS = "( )" + OPERATORS;
    //private Stack<String> stack;

    

    static Converter convertFunction = () -> {
        List<String> resultExpression = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(readFunction.read(), DELIMITERS, true);
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
        System.out.println(resultExpression);
        return resultExpression;
    };

    static Function<String, Boolean> delimiterFunction = (token) -> {
        //String OPERATORS = "+-*/";
        String DELIMITERS = "( )+-*/";

        if (token.length() != 1) {
            return false;
        }
        for (int i = 0; i < DELIMITERS.length(); i++) {
            if (token.charAt(0) == DELIMITERS.charAt(i)) {
                return true;
            }
        }
        return false;
    };

    static Function<String, Boolean> operatorFunction = (token) -> {
        String OPERATORS = "+-*/";
        if (token.equals("minus")) {
            return true;
        }
        for (int i = 0; i < OPERATORS.length(); i++) {
            if (token.charAt(0) == OPERATORS.charAt(i)) {
                return true;
            }
        }
        return false;
    };

    static Function<String, Integer> precedenceFunction = (token) -> {
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
    };
}
