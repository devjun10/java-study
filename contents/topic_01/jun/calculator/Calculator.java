package calculator;

import java.util.function.BiFunction;

public class Calculator {

    private BiFunction<Integer, Integer, Integer> operator;

    public int add(int x, int y) {
        operator = Integer::sum;
        return operator.apply(x, y);
    }

    public int subtract(int x, int y) {
        operator = (a, b) -> (a - b);
        return operator.apply(x, y);
    }


}
