package 계산기_V1;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {


    @Test
    @DisplayName("")
    void m() throws Exception {
        Calculator calculator = new Calculator();

        int result = calculator.add(3, 5);
        int expected = 8;

        Assertions.assertEquals(expected, result);;
    }


    @Test
    @DisplayName("")
    void subtract() throws Exception {
        Calculator calculator = new Calculator();

        int result = calculator.subtract(5, 3);
        int expected = 2;

        Assertions.assertEquals(expected, result);;
    }

}