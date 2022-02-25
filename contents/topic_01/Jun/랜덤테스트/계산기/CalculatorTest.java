package 랜덤테스트.계산기;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

class CalculatorTest {

    @Test
    @DisplayName("고정숫자를 넣으면 일정한 값이 나오는 테스트")
    void 계산기_테스트() {
        final int fixedValue = 5;
        RandomCalculator randomCalculator = new RandomCalculator(new Random() {
            @Override
            public int nextInt() {
                return fixedValue;
            }
        }, new Calculator());

        int expected = 20;
        int result = randomCalculator.plusFromTo(1, 5);

        Assertions.assertEquals(expected, result);
    }
}