package optional;

import java.util.Optional;

public class MyClass {
    private String value;

    public MyClass() {}

    public MyClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Optional<String> getOptionalValue() {
        return Optional.ofNullable(value);
    }
}
