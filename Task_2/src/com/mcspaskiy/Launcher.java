package com.mcspaskiy;

import java.util.List;

/**
 * Shunting-yard algorithm
 */
public class Launcher {
    public static void main(String[] args) {
        /**
         * Read initial data
         */
        DataRetriever dataRetriever = new DataRetriever();
        String inputExpression = dataRetriever.readData();

        /**
         * Convert to Reverse Polish Notation
         */
        Transformer transformer = new ShuntingYardTransformer();
        List<String> resultData = transformer.parseExpression(inputExpression);

        /**
         * Calculate final value
         */
        Calculator calculator = new Calculator();
        Double resultValue = calculator.calculateValue(resultData);

        /**
         * Show results
         */
        DataViewer dataViewer = new DataViewer();
        dataViewer.show(resultData);
        dataViewer.show(resultValue);
    }
}
