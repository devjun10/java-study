package section04;

import section03.OnlineClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {
        List<section03.OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new section03.OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(c -> c.getTitle().startsWith("spring"))
                .findFirst();

        OnlineClass onlineClass = optional.orElse(getNewClass());
        System.out.println(onlineClass.getTitle());

        OnlineClass optional2 = optional.orElseGet(App::getNewClass);
        System.out.println(optional2.getTitle());

    }

    private static OnlineClass getNewClass() {
        System.out.println("함수 실행");
        return new OnlineClass(10, "new", false);
    }
}
