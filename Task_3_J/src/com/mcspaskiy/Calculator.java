package com.mcspaskiy;

import com.mcspaskiy.utils.Converter;
import com.mcspaskiy.utils.DataReader;
import com.mcspaskiy.utils.Evaluator;

import java.util.List;

public class Calculator {

    public String readExpression(DataReader consoleReadFunction) {
        return consoleReadFunction.read();
    }

    public List<String> convert(Converter convertFunction) {
        return convertFunction.convert();
    }

    public Double calculate(Evaluator calcFunction) {
        return calcFunction.process();
    }
}
