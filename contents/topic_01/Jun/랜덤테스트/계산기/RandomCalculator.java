package 랜덤테스트.계산기;

import java.util.Random;

public class RandomCalculator {

    private final Random random;
    private final Calculator calculator;

    public RandomCalculator(Random random, Calculator calculator) {
        this.random = random;
        this.calculator = calculator;
    }

    public int plusFromTo(int start, int end) {
        return this.calculator.plus(start, end) + random.nextInt();
    }
}
