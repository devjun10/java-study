package optional;

import java.util.Optional;
import java.util.OptionalInt;

public class OptionalExample {

    private MyClass myClass;

    public void setMyClass(MyClass myClass) {
        this.myClass = myClass;
    }

    public Optional<MyClass> getMyClass() {
        return Optional.ofNullable(myClass);
    }

    public Optional<Optional<MyClass>> getOptionalMyClass() {
        return Optional.ofNullable(Optional.ofNullable(myClass));
    }

    public MyClass getMyClassOrNull() {
        return null;
    }

}
