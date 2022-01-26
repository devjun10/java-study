

### 함수형 인터페이스
* 추상 메소드가 하나만 있어야 한다
<span style = "fontSize:80%">(인터페이스에서는 원래 추상 메소드의 abstract 키워드를 생략한다)</span>

* 자바8부터는 인터페이스에 기본적으로 static 또는 default 메소드도 선언이 가능하다. 함수형 인터페이스도 마찬가지다. 
( 다른 구현된 메소드들이 더 있더라도 추상 메소드가 하나만 있는게 중요하다)
```java
@FunctionalInterface
interface Calc {
    int myFunction(int x, int y);
    
    default int reset(int x){
        return 0;
    }
    static void printAll(){
        System.out.println("ALL");
    }

}

public class Practice {
    public static void main(String[] args) {

        Calc calc = (x, y) -> x * y * 100;  // 추상 메소드 구현
        System.out.println(calc.myFunction(6, 3));
        }
// 출력
1800
```


### 함수형 프로그래밍
#### 함수를 1급 객체로 사용할 수 있다.
* 1급 객체((First Class Object)
1. 변수로 사용될 수 있다.
2. 반환값으로 사용될 수 있다.
3. 인자로 사용될 수 있다

-> 고차 함수 : 함수를 인자로 받거나, 함수를 반환하는 등 함수를 다루는 함수 (ex: map(String::toUpperCase))

#### 순수 함수
외부의 값에 의존하지 않고 오로지 함수에 입력된 인자에만 의존한다.
외부의 값을 변경하지 않는다.
\#참조투명성 \#불변성 

### 자바에서 제공하는 함수형 인터페이스
> 각 인터페이스마다 사용 가능한 메소드가 다르다.

`Consumer<T>` : T 타입 인자를 받기만 하고 리턴값은 없다.
* accept() :  인자를 입력할 때 사용한다.
* andThen() :  두개 이상의 Consumer를 연속으로 실행한다.

```java
Consumer<String> printString = text -> System.out.println(" I'm " + text );
Consumer<String> printString2 = text -> System.out.println(text + " " + text + " " + text);
printString.accept("BC");
System.out.println("------------")
printString.andThen(printString2).accept("BC")

// 출력
I'm BC
------------
I'm BC
BC BC BC
```

`Supplier<R>` : 인자 없이 R 타입을 리턴한다. 
* get() : 리턴값을 받기 위해 사용한다
```java
Supplier<String> getString = () -> "Happy new year!";
String str = getString.get();
System.out.println(str);
```

`Predicate<T>` : T 타입 인자를 받아서 Boolean을 리턴한다.
* and()
* or()
* negate()

```java
Predicate<Integer> isEvenNumber = i -> i % 2 == 0 ;
Predicate<Integer> isBiggerThanTen = i -> i > 10 ;
System.out.println(isEvenNumber.test(77));
System.out.println(isEvenNumber.negate().test(77)); 
System.out.println(isEvenNumber.and(isBiggerThanTen).test(77)); 
System.out.println(isEvenNumber.or(isBiggerThanTen).test(77)); 

//출력
false
true
false
true
```

`Function<T,R>` : T 타입 인자를 받아서 R 타입을 리턴한다.
서로 다른 타입의 입력 인자 2개를 받을 때는 BiFunction<T, U, R>
입력과 리턴 타입이 같다면 UnaryOperator<T>(인자 1개), BinaryOperator<T>(인자2개) 등을 사용할 수 있다.
* apply() : 인자를 입력할 때 사용한다
* andThen() : Function 들을 조합하여 순차적으로 실행
* compose() : andThen과 같으나 실행 순서가 반대

```java
Function<Integer, Integer> multiply = (value) -> value * 2;
Function<Integer, Integer> add      = (value) -> value + 3;

multiply.apply(3);	// 6
multiply.andThen(add).apply(3); // 9
multiply.compose(add).apply(3); // 12
multiply.andThen(add).andThen(multiply).apply(3) // 18
```
---
  
### 람다 표현식
(인자) -> {본문}
  
람다의 함수 본문에서 로컬 변수를 참조하기 위해서는
해당 변수가 final 이거나 effectively final 이여야 한다.
* effectively final
final로 지정하진 않았지만, 생성 후 값이 재할당(변동) 되지 않는 변수
  
```java
  public class Test {
    int number2 = 90;

    public static void main(String[] args) {

        Test test = new Test();
        test.run();
    }

    private void run() {
        int number1 = 10;
        IntUnaryOperator printInt = i -> i + number1;
        
        System.out.println(printInt.applyAsInt(5));
	// number1 ++;  
  	// number1 값을 변경할 경우 effectively final이 아니게 되어 에러 발생
  
        //------------------------------------------------

        IntUnaryOperator printInt2 = i -> i + number2;
        System.out.println(printInt2.applyAsInt(5));
        this.number2 += 5; 
  	// number2 값을 변경하였음에도 에러가 나지 않는다.
  	// 하지만 이 경우에는 순수 함수가 아닌것?!
        System.out.println(printInt2.applyAsInt(5));

    }
}
  
//출력
15
95
100
```

#### 메소드 레퍼런스
  
  ```java
interface Executable {
        void doSomething(String text);
    }

    public class Practice2 {
        String name;
  
        public Practice2() {
        }
  
        public Practice2(String name) {
            this.name = name;
        }
  
        public static void printSomething(String text) {
            System.out.println(text);
        }
  
        public void printOther(String otherText){
            System.out.println(otherText+ "-----");
        }

        public static void main(String args[]) {
            Practice2 practice2 = new Practice2();
            Executable exe = Practice2::printSomething; // 스태틱 메소드 참조

            Executable exe2 = practice2::printOther; // 특정 객체의 인스턴스 메소드 참조

            exe.doSomething("do something");
            exe2.doSomething("do something else");

            List<String> list = List.of("lesson1","lesson2");
            list.stream()
                .map(Practice2::new) // 생성자 참조 (name -> new Practice(name))
                .forEach(i -> System.out.println(i.name));

            }
        }
  
//출력  
do something
do something else-----
lesson1
lesson2
  ```
 
---
  