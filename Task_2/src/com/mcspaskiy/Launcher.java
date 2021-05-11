package com.mcspaskiy;

import com.mcspaskiy.core.CalculatorDijkstra;
import com.mcspaskiy.core.ShuntingYardTransformer;
import com.mcspaskiy.core.Transformer;
import com.mcspaskiy.io.DataRetriever;
import com.mcspaskiy.io.DataViewer;

import java.util.List;

/**
 * Shunting-yard algorithm
 */
public class Launcher {
    public static void main(String[] args) {
        while (true) {
            /**
             * Read initial data
             */
            DataRetriever dataRetriever = new DataRetriever();
            String inputExpression = dataRetriever.readData();
            if ("exit".equalsIgnoreCase(inputExpression)) {
                return;
            }

            /**
             * Convert to Reverse Polish Notation
             */
            Transformer transformer = new ShuntingYardTransformer();
            List<String> resultData;
            try {
                resultData = transformer.parseExpression(inputExpression);
            } catch (Exception e) {
                System.out.println("Incorrect Expression");
                continue;
            }

            /**
             * Calculate final value
             */
            CalculatorDijkstra calculator = new CalculatorDijkstra();
            Double resultValue;
            try {
                resultValue = calculator.calculateValue(resultData);
            } catch (Exception e) {
                System.out.println("Incorrect Expression");
                continue;
            }


            /**
             * Show results
             */
            DataViewer dataViewer = new DataViewer();
            dataViewer.show(resultData);
            dataViewer.show(resultValue);
        }
    }
}
