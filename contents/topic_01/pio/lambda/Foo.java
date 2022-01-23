package java8to11.lambda;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Foo {

    public static void main(String[] args) {
        //실질적으로는 특수한 형태의 오브젝트. 함수를 선언한 것이 아니다.
        RunSomething runSomething = new RunSomething() {
            @Override
            public void oneAbstractMethod() {

            }
        };

        RunSomething runSomething1 = () -> System.out.println("hello world");

        Function<Integer, Integer> plus10 = (i) -> i + 10;
        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        Function<Integer, Integer> plus10AndMultiply2 = multiply2.compose(plus10);
        Function<Integer, Integer> mixedCrazy = multiply2AndPlus10.compose(plus10);
        System.out.println(multiply2AndPlus10.apply(10)); // (10 x 2) + 10 == 30
        System.out.println(plus10AndMultiply2.apply(10)); // (10 + 10) x 2 == 40
        System.out.println(mixedCrazy.apply(10)); // ((10 + i) x 2) + (i + 10) -> 50
        System.out.println(mixedCrazy.apply(5)); // ((10 + i) x 2) + (i + 10) -> 40

        System.out.println(plus10.andThen(multiply2).apply(10)); //앞에 꺼 먼저 계산하고, 결과값이 뒤에 인풋으로 들어감.

        UnaryOperator<Integer> unaryOperator = (i) -> i + 10;
        System.out.println(unaryOperator.apply(10)); //20 -> Function 함수형 인터페이스의 특수 케이스. 인풋, 아웃풋의 자료형이 같은 경우


    }

}
