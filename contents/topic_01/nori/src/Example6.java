import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Example6 {
    public static void main(String[] args) {
        Apple apple1 = new Apple("red", 100);
        Apple apple2 = new Apple("green", 100);
        Apple apple3 = new Apple("red", 150);
        Apple apple4 = new Apple("green", 200);
        Apple apple5 = new Apple("red", 200);

        List<Apple> appleBox = List.of(apple1, apple2, apple3, apple4, apple5);

        List<Apple> filteredBox = filterApples(appleBox, new Apple("red", 100));

        filteredBox.forEach(System.out::println);
    }

    public static List<Apple> filterApples(List<Apple> appleBox, Apple compare) {

        return appleBox.stream()
                .filter(apple -> apple.getWeight() > compare.getWeight())
                .collect(Collectors.toList());
    }
}





