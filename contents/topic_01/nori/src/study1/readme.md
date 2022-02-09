# 람다식과 함수형 인터페이스

##  람다식이란?

- 람다식은 반환값으로 함수형 인터페이스를 반환하고 있다.
- Stream 연산들은 매개변수로 함수형 인터페이스(Functional Interface)를 받도록 되어있다.
- 그렇기 때문에 우리는 Stream API를 정확히 이해하기 위해 람다식과 함수형 인터페이스에 대해 알고 있어야 한다.


### [ 람다식이란? ]

- 함수를 하나의 식(expression)으로 표현한 것
- 함수를 람다식으로 표현하면 메소드의 이름이 필요 없기 때문에, 람다식은 익명 함수(Anonymous Function)의 한 종류라고 볼 수 있다.


## 왜 람다를 쓰게 되었나, 얼마나 편해진건가?

###  과거의 자바 

### 과일 재고 프로그램 예제



### 과거 전략패턴을 이용한 방법

특정 메소드 코드를 캡슐화하여 런타임에 원하는 전략을 선택하도록 하는 것을 전략패턴이라고 합니다.

```java
import study1.Apple;

public interface ApplePredicate {
    boolean test(Apple apple, Apple compare);
}

public class study1.AppleColorPredicate implements study1.ApplePredicate {
    @Override
    public boolean test(Apple apple, Apple compare) {
        return apple.getColor().equals(compare.getColor());
    }
}

public class study1.AppleWeightPredicate implements study1.ApplePredicate {
    @Override
    public boolean test(Apple apple, Apple compare) {
        return apple.getWeight() > compare.getWeight(); // compare에 150g이 포함
    }
}
```

- 사용자가 원하는 기준에 따라 study1.ApplePredicate 객체를 넘겨 필터 조건에 사용할 수 있다.
- 조건이 추가되어도 study1.ApplePredicate 구현체를 생성해서 필터 조건을 만들어 넘겨주면 된다..

- 하지만 매번 조건이 추가될때마다 일반 클래스를 계속해서 구현해야만 한다.
- 사실은 단순히 if 조건문만 있으면 되는데 너무나 많은 불필요한 코드가 필요하다.

<br>

### 과거 익명클래스를 이용한 방법

- 일반클래스를 생성하는것보다는 코드가 줄었지만, 그래도 아직 불필요한 코드가 많다.
- 필요한 코드는 사실상 apple.getWeight() > compare.getWeight() 뿐이다.
