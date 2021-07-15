import java.util.*

fun main() {
    val input: String = "3 + 4"
}


val delimeter = { token: String, delimiters: String ->
    {
        if (token.isNotEmpty()) {
            false
        }
        //delimiters.contains(token[0])
        for (delimiter in delimiters) {
            if (delimiter == token[0]) {
                true
            }
        }
    }
}

val operator = { token: String, operators: String ->
    {
        if (token.equals("minus")) {
            true
        }
        for (operator in operators) {
            if (operator == token[0]) {
                true
            }
        }
    }
}

val function = { token: String -> false }

val predicate = { token: String ->
    {
        when (token) {
            "(" -> 1
            "+", "-" -> 2
            "*", "/" -> 3
        }
        throw RuntimeException("Incorrect token")
    }
}

val expression = {inputExpression : String ->
    {
        val resultExpression = arrayListOf<String>()
        var prevElement = ""
        var currentElement = "";
    }
}




public List<String> parseExpression(String inputExpression) {
    //List<String> resultExpression = new ArrayList<>();
    StringTokenizer tokenizer = new StringTokenizer(inputExpression, DELIMITERS, true);
    //String prevElement = "";
  //  String currentElement;
    while (tokenizer.hasMoreTokens()) {
        currentElement = tokenizer.nextToken();
        if (!tokenizer.hasMoreTokens() && isOperator(currentElement)) {
            throw new RuntimeException ("Incorrect expression");
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
                        throw new RuntimeException ("Incorrect expression");
                    }
                }
                stack.pop();
                if (!stack.isEmpty() && isFunction(stack.peek())) {
                    resultExpression.add(stack.pop());
                }
            } else {
                if (currentElement.equals("-") && (prevElement.equals("") || (isDelimiter(prevElement) && !prevElement.equals(
                        ")"
                    )))
                ) {
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
            throw new RuntimeException ("Incorrect expression");
        }
    }
    return resultExpression;
}

fun parseExpression(inputExpression: String?): List<String>? {
    val resultExpression: MutableList<String> = java.util.ArrayList()
    val tokenizer =
        StringTokenizer(inputExpression, com.mcspaskiy.core.ShuntingYardTransformer.DELIMITERS, true)
    var prevElement = ""
    var currentElement: String
    while (tokenizer.hasMoreTokens()) {
        currentElement = tokenizer.nextToken()
        if (!tokenizer.hasMoreTokens() && isOperator(currentElement)) {
            throw RuntimeException("Incorrect expression")
        }
        if (currentElement == " ") {
            continue
        }
        if (isFunction(currentElement)) {
            stack.push(currentElement)
        } else if (isDelimiter(currentElement)) {
            if (currentElement == "(") {
                stack.push(currentElement)
            } else if (currentElement == ")") {
                while (!stack.peek().equals("(")) {
                    resultExpression.add(stack.pop())
                    if (stack.isEmpty()) {
                        throw RuntimeException("Incorrect expression")
                    }
                }
                stack.pop()
                if (!stack.isEmpty() && isFunction(stack.peek())) {
                    resultExpression.add(stack.pop())
                }
            } else {
                if (currentElement == "-" && (prevElement == "" || isDelimiter(prevElement) && prevElement != ")")) {
                    currentElement = "minus"
                } else {
                    while (!stack.isEmpty() && getPrecedence(currentElement) <= getPrecedence(stack.peek())) {
                        resultExpression.add(stack.pop())
                    }
                }
                stack.push(currentElement)
            }
        } else {
            resultExpression.add(currentElement)
        }
        prevElement = currentElement
    }
    while (!stack.isEmpty()) {
        if (isOperator(stack.peek())) {
            resultExpression.add(stack.pop())
        } else {
            throw RuntimeException("Incorrect expression")
        }
    }
    return resultExpression
}