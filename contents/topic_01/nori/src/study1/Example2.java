package study1;

import java.util.ArrayList;
import java.util.List;

public class Example2 {

    public static void main(String[] args) {
        Apple apple1 = new Apple("red", 100);
        Apple apple2 = new Apple("green", 100);
        Apple apple3 = new Apple("red", 150);
        Apple apple4 = new Apple("green", 200);
        Apple apple5 = new Apple("red", 200);

        List<Apple> appleBox = List.of(apple1, apple2, apple3, apple4, apple5);

        // List<study1.Apple> filteredBox = filterApplesByColor(appleBox,"red");
        List<Apple> filteredBox = filterApplesByColorAndWeight(appleBox, "red", 200);

        filteredBox.forEach(System.out::println);

    }

    public static List<Apple> filterApplesByColorAndWeight(List<Apple> appleBox, String color, int weight) {
        List<Apple> box = new ArrayList<>();

        for(Apple apple : appleBox) {
            // 결국 이 조건절 안의 내용들을 파라미터로 받아서 처리할 수 있다면 이 같은 변경 사항에 모두 유연하게 대처할 수 있습니다.
            // 원하는 것은 동적으로 boolean을 반환하는 함수입니다.
            if(apple.getColor().equals(color) && apple.getWeight() >= weight) {
                box.add(apple);
            }
        }

        return box;
    }

}
