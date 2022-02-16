package optional;

import java.util.Optional;
import java.util.OptionalInt;

public class Main {

    public static void main(String[] args) {
        OptionalExample optionalExample = new OptionalExample();
        optionalExample.setMyClass(null); //NullPointerException
        Optional<MyClass> myClass = optionalExample.getMyClass();

    }

}
