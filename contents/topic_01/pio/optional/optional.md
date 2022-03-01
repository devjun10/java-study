## Optional

```java
public class MyClass {
    private String value;

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
```

```java
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
```

### API

`of()`
* 데이터에 절대 null이 들어오지 않는 경우 사용한다.<br>
* `Optional.of(Null)` 을 하려고 하면 NPE가 발생한다.
```java
@Test
@DisplayName("Optional.of()에 Null을 입력할 시 NPE가 발생한다.")
void optional_of_test() {
    Assertions.assertThrows(NullPointerException.class, () -> {
        Optional<String> optional = Optional.of(null);
    });
}
```
<br>

`ofNullable()`
* 들어오는 값이 Null일 수도 있고 아닐 수도 있는 경우 사용한다.

```java
@Test
@DisplayName("ofNullable()에 들어가는 값은 Null일 수도 있고 아닐 수도 있다.")
void ofNullable_test() {
    Assertions.assertTrue(Optional.ofNullable(null).isEmpty());
    Assertions.assertFalse(Optional.ofNullable("not null").isEmpty());
    Assertions.assertTrue(Optional.ofNullable("not null").isPresent());
}
```

<br>

`get()`

값이 들어있을 수도 있고 없을 수도 있어서 체크를 해야 함. 값이 없는데 꺼내면 `NoSuchElementExcpetion` 발생.
때문에 가급적 get()을 사용하지 않고 다른 걸 사용하는 게 좋다.

<br>


`orElse()`

Optional에 값이 없을 시 Optional로 감싸고 있는 클래스의 인스턴스를 직접 반환해주는 메서드.<br>
하지만 값이 있을 때에도 orElse() 괄호 내의 코드를 실행시키기 때문에 불필요한 작업이 수행된다. 이를 개선한 게 orElseGet().

```java
@Test
@DisplayName("ofNullable()에 null이 입력될 시 orElse()로 직접 값을 만들어 낸다.")
void orElse_test() {
    MyClass myclass = Optional.ofNullable(null)
        .orElse(new MyClass("orElse_myClass"));

    Assertions.assertEquals("orElse_myClass", myclass.getValue());
}
```

<br>

`orElseGet()`

파라미터에 Supplier 함수형 인터페이스의 람다식을 넣을 수 있다. 
```java
@Test
@DisplayName("ofNullable()에 null이 입력될 시 orElseGet()으로 동적으로 값을 만들 수 있다.")
void orElseGet_test() {
    MyClass myClass = Optional.ofNullable(optionalExample.getMyClassOrNull())
        .orElseGet(() -> new MyClass("orElseGet_myClass"));

    Assertions.assertEquals("orElseGet_myClass", myClass.getValue());
}
```

<br>

`orElseThrow()`

값이 없을 때의 대안이 없는 경우 예외를 발생시키는 것도 방법이 될 수 있다.
```java
Optional<MyClass> myClass = optionalExample.getMyClass();
myClass.orElseThrow(IllegalAccessError::new);
```

<br><br>

<hr>

> Optional을 최대 1개의 원소를 가지고 있는 특별한 Stream이라고 생각하면 아래의 api들을 보다 잘 사용할 수 있다.

`filter()`

filter후 Optional타입을 리턴한다.
```java
optionalInstance.filter(() -> {...});
```

<br>

`map()`

```java
// optional의 map은 Optional이 감싸고 있는 단 하나의 인스턴스만 대상으로 변환 작업을 하는듯 듯.
optionalExample.setMyClass(new MyClass("ab"));
Optional<MyClass> myClass = optionalExample.getMyClass();

Optional<String> mapString = myClass.map(e -> e.getValue().repeat(2));
System.out.println(mapString.orElse("no value")); // "abab"
```

<br>

`flatMap()`
 
```java
public class MyClass {
    private String value;

    public MyClass(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //***
    public Optional<String> getOptionalValue() {
        return Optional.ofNullable(value);
    }
}
```

나중에..