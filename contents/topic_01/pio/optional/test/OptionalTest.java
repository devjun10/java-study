package optional.test;

import java.util.Optional;
import optional.MyClass;
import optional.OptionalExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OptionalTest {

    OptionalExample optionalExample;

    @BeforeEach
    void setUp() {
        optionalExample = new OptionalExample();
    }

    @Test
    @DisplayName("of()에 Null을 입력할 시 NPE가 발생한다.")
    void optional_of_test() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Optional<String> optional = Optional.of(null);
        });
    }

    @Test
    @DisplayName("ofNullable()에 들어가는 값은 Null일 수도 있고 아닐 수도 있다.")
    void ofNullable_test() {
        Assertions.assertTrue(Optional.ofNullable(null).isEmpty());
        Assertions.assertFalse(Optional.ofNullable("not null").isEmpty());
        Assertions.assertTrue(Optional.ofNullable("not null").isPresent());
    }

    @Test
    @DisplayName("ofNullable()에 null이 입력될 시 orElse()로 직접 값을 만들어 낸다.")
    void orElse_test() {
        MyClass myclass = Optional.ofNullable(optionalExample.getMyClassOrNull())
            .orElse(new MyClass("orElse_myClass"));
        Assertions.assertEquals("orElse_myClass", myclass.getValue());
    }

    @Test
    @DisplayName("ofNullable()에 null이 입력될 시 orElseGet()으로 동적으로 값을 만들 수 있다.")
    void orElseGet_test() {
        MyClass myClass = Optional.ofNullable(optionalExample.getMyClassOrNull())
            .orElseGet(() -> new MyClass("orElseGet_myClass"));

        Assertions.assertEquals("orElseGet_myClass", myClass.getValue());
    }

}
