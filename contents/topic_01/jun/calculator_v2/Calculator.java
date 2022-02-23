package calculator_v2;


import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Calculator {

    private static Map<String, BiFunction<Double, Double, Double>> operators = new HashMap<>();

    static {
        operators.put("+", (x, y) -> x + y);
        operators.put("-", (x, y) -> x - y);
        operators.put("*", (x, y) -> x * y);
        operators.put("/", (x, y) -> x / y);
    }

    public static double calculate(String operator, double x, double y) {
        return operators.get(operator).apply(x, y);
    }

}
