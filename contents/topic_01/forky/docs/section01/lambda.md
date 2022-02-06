## 함수형 인터페이스
- 추상 메서드가 하나만 존재하는 인터페이스
- Java8부터는 **static 메서드**, **default 메서드**를 가질 수 있게 되었다.
- `@FunctionalInterface` 어노테이션을 붙여서 사용하면 코드 규칙을 위배하면 에러를 발생시켜준다.


### 람다식
- 기존에는 익명 객체를 사용해서 구현을 했다면, Java8부터는 함수형 인터페이스를 구현할 때 람다식으로 대체할 수 있게 되었다.
- 컴파일러가 제네릭 타입을 보고 추론해주기 때문에 매개변수의 타입을 생략할 수 있다.
- 람다식에서 참조하게 되는 로컬 변수는 **final** 특성을 갖게 된다.

#### 장점
- 코드의 길이가 짧아지고, 가독성이 좋아진다.
- 메서드 매개변수, 리턴타입, 변수로 만들어서 사용할 수 있다.(고차함수의 성질)

<br>

## 자바에서 제공하는 함수형 인터페이스

| 함수명 | 매개변수 | 리턴값 | 용도 |
|---|---|---|---|
| Function | T | R | 하나의 매개변수를 받아서 하나의 리턴값을 반환 |
| BiFunction | T,U | R | 두개의 매개변수를 받아서 하나의 리턴값을 반환 |
| Consumer | T | x | 하나의 매개변수를 받고 리턴하지 않음 |
| Supplier | x | T | 매개변수를 받지 않고 리턴만 함 |
| Predicate | T | boolean | 한개의 매개변수를 받아서 참,거짓을 반환 |
| UnaryOperator | T | T | Function 인터페이스의 특수한 형태. 매개변수가 한개이며, 매개변수와 리턴값의 타입이 같을때 사용. |
| BinaryOperator | T,T | T | BiFunction의 특수한 형태. 두개의 매개변수와 리턴타입이 모두 같을 경우 사용. |

<br>

## 변수 캡처
- java 8 이전에는 로컬변수, 매개변수를 사용하려면 반드시 final 키워드를 작성해야 했다.
- 이제는 코드 내에서 값의 변경 지점이 없으면 사실상 final이라고 (**effective final이라고도 한다**) 간주하고 생략이 가능하다.

익명 클래스, 로컬 클래스와 람다의 차이는 **쉐도잉**을 하냐, 안하냐의 차이다.
- 람다는 쉐도잉을 하지 않는다. 즉, 람다의 스코프는 람다식이 선언되어 있는 메서드와 동일한 스코프를 갖는다.

> 쉐도잉이란?  
> 각 클래스가 자신의 스코프(범위)를 갖게 되어서 클래스의 외부와 내부에 동일한 이름의 변수가 있을경우, 더 가까운 스코프에 있는 변수가 상위 스코프의 변수를 가리는 것.

<br>

## 메서드 레퍼런스
- 람다식을 사용하는데, 기존에 존재하는 메서드 혹은 생성자를 호출하도록 작성하는 경우 사용할 수 있다.

```java
Greeting greeting = new Greeting();

//static 메서드 참조
UnaryOperator<String> hi = Greeting::hi;

//특정 인스턴스 메서드 참조
UnaryOperator<String> hello = greeting::hello;

//임의의 인스턴스 메서드 참조
String[] names = {"포키1","포키2","포키3"};
Arrays.sort(names, String::compareToIgnoreCase);

//생성자 메서드 참조
Supplier<Greeting> greetingSupplier = Greeting::new;
Function<String, Greeting> anotherGreetingSupplier = Greeting::new;


```
