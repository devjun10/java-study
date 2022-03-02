package optional;

import java.util.Optional;
import java.util.OptionalInt;

public class Main {

    public static void main(String[] args) {
        OptionalExample optionalExample = new OptionalExample();
//        optionalExample.setMyClass(null); //NullPointerException
//        optionalExample.setMyClass(new MyClass("ab"));
        optionalExample.setMyClass(new MyClass());
        Optional<MyClass> myClass = optionalExample.getMyClass();
//        System.out.println(myClass.orElse(createMyClass("create")).getValue());
//        myClass.orElseThrow(IllegalAccessError::new);
        Optional<String> mapString = myClass.map(e -> e.getValue()); // optional의 map은 Optional이 감싸고 있는 단 하나의 인스턴스로만 작업을 할 수 있는 듯?
        System.out.println(mapString.orElse("no value"));

//        Optional<String> flatMapString = myClass.flatMap(e -> e.getOptionalValue());
//        System.out.println(flatMapString.orElse("no value"));
    }


    public static MyClass createMyClass(String value) {
        return new MyClass(value);
    }
}
