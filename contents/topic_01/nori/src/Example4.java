import java.util.ArrayList;
import java.util.List;

public class Example4 {
    public static void main(String[] args) {
        Apple apple1 = new Apple("red", 100);
        Apple apple2 = new Apple("green", 100);
        Apple apple3 = new Apple("red", 150);
        Apple apple4 = new Apple("green", 200);
        Apple apple5 = new Apple("red", 200);

        Apple compare = new Apple("red", 100);

        List<Apple> appleBox = List.of(apple1, apple2, apple3, apple4, apple5);

        // 익명 클래스
        List<Apple> filteredBox = filterApples(appleBox, new ApplePredicate(){
            @Override
            public boolean test(Apple apple, Apple compare) {
                return apple.getWeight() > compare.getWeight();
            }
        }, compare);

        //결과 출력
        filteredBox.forEach(System.out::println);
    }

    public static List<Apple> filterApples(List<Apple> appleBox, ApplePredicate applePredicate, Apple compare) {
        List<Apple> box = new ArrayList<>();

        for (Apple apple : appleBox) {
            if(applePredicate.test(apple, compare)) {
                box.add(apple);
            }
        }

        return box;
    }
}
