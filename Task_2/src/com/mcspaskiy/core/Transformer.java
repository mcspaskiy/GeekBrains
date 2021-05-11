package com.mcspaskiy.core;

import java.util.List;

public interface Transformer {
    List<String> parseExpression(String inputExpression);
}
