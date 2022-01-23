package java8to11.lambda;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;

public class Boo {

    private int globalNumber = 10;

    private int number;

    public Boo(int number) {
        this.number = number;
    }

    private Predicate<Integer> predicateField = (i) -> {
        number++;
        return i == number;
    };

    public static void main(String[] args) {
        Boo boo = new Boo(5);
        boo.run();

    }

    private void run() {
        int baseNumber = 10;
        globalNumber++;

        class LocalClass {
            private int baseNumber = 11;
            void printBaseNumber() {
                System.out.println(globalNumber);
                System.out.println(baseNumber);
            }
        }

        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 12;
                System.out.println(baseNumber);
            }
        };

        IntConsumer pritnInt = (i) -> {
            System.out.println(baseNumber + i);
        };

        pritnInt.accept(10);
    }

    private Predicate<Integer> returnPredicate = (i) -> {
        number++;
        return i == number;
    };

}
