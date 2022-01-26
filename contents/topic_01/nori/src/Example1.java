import java.util.ArrayList;
import java.util.List;

public class Example1 {

    public static void main(String[] args) {
        Apple apple1 = new Apple("red", 100);
        Apple apple2 = new Apple("green", 100);
        Apple apple3 = new Apple("red", 150);
        Apple apple4 = new Apple("green", 200);
        Apple apple5 = new Apple("red", 200);

        List<Apple> appleBox = List.of(apple1, apple2, apple3, apple4, apple5);

        // List<Apple> filteredBox = filterGreenApple(appleBox);
        List<Apple> filteredBox = filterApplesByColor(appleBox,"red");

        filteredBox.forEach(System.out::println);
    }

    private static List<Apple> filterApplesByColor(List<Apple> appleBox, String color) {
        ArrayList<Apple> box = new ArrayList<>();

        for (Apple apple : appleBox) {

            if(apple.getColor().equals(color)) {
                box.add(apple);
            }

        }

        return box;
    }


}
