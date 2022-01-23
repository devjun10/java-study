package java8to11.method_reference;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;

public class App {

    public static void main(String[] args) {
        Supplier<Greeting> createGreeting = Greeting::new;
        Greeting greeting = createGreeting.get();
        System.out.println("greeting = " + greeting);

        Function<String, Greeting> createGreeting2 = Greeting::new;
        Greeting greeting2 = createGreeting2.apply("jaehong");
        System.out.println(greeting2.getName());


        String[] alphabets = {"def", "ghi", "abc"};
        Arrays.sort(alphabets, String::compareToIgnoreCase); //String 클래스에 있는 static메서드인 게 아니고
        //임의의 객체의 인스턴스 메서드를 참조한 것이다. 위 코드는 아래와 동일하다.
        Arrays.sort(alphabets, (o, o2) -> o.compareToIgnoreCase(o2));


        for (String a : alphabets) {
            System.out.println(a);
        }
        System.out.println("alphabets = " + alphabets); //abc def ghi
    }

}
