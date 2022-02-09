package study1;

import java.util.ArrayList;
import java.util.List;

public class Example {

    public static void main(String[] args) {
        // 5개의 사과를 만듭니다. 사과는 색상과 무게를 가지고 있습니다.
        Apple apple1 = new Apple("red", 100);
        Apple apple2 = new Apple("green", 100);
        Apple apple3 = new Apple("red", 150);
        Apple apple4 = new Apple("green", 200);
        Apple apple5 = new Apple("red", 200);

        // 사과박스에 생성한 5개의 사과를 넣어 줍니다.
        List<Apple> appleBox = List.of(apple1, apple2, apple3, apple4, apple5);

        // 초록색인 사과만 거르는 메서드
        List<Apple> filteredBox = filterGreenApple(appleBox);

        //출력
        filteredBox.forEach(System.out::println);
    }

    // 사과박스를 받아서 초록색인 사과만 반환 합니다.
    private static List<Apple> filterGreenApple(List<Apple> appleBox) {
        ArrayList<Apple> box = new ArrayList<>();

        for (Apple apple : appleBox) {
            if("green".equals(apple.getColor())) {
                box.add(apple);
            }

        }

        return box;
    }


}
