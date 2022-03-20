# 객체 지향 프로그래밍
- 실제 세계에 존재하는 모든것을 '객체'로 취급하여, 프로그래밍 하는 방법
- 실제 세계의 모습을 모델링 하기위해서 나온것이 바로 캡슐화, 정보은닉, 추상화, 상속성, 다형성 이다.

## 5가지 특징
- `캡슐화` : 속성(데이터)과 메서드(데이터를 처리하는 함수)를 하나로 합친것으로 캡슐화된 객체들은 재사용이 용이하다.
- `정보은닉` : 외부로부터 정보를 은닉하는 기능으로, 캡슐화를 통해 가능한 개념이다.
- `상속성` : 이미 정의된 부모 객체의 속성과 메소드를 다른, 자식 객체가 물려받아 쓸수 있는 기능으로 자식클래스가 부모클래스의 속성과 연산을 정의할 필요 없이 즉시 사용이 가능하다.
- `추상화` : 강아지를 Dog 클래스로 만드는것처럼 중요 특징들을 찾아내 간단하게 표현하는것을 뜻한다.
  (실제 사물을 클래스로 정의하는것)
- `다형성` : 정의된 하나의 클래스 혹은 메소드가 기존에 정의된 방식이 아닌, 다양한 방식으로 동작이 가능한것을 뜻한다.

# 클래스란
- 객체를 만들기 위해 필요한 틀(붕어빵 기계)
- 속성(상태)과 행위를 갖게되는데, 이를 변수, 메소드라 칭한다.
- 네이밍 컨벤션
    - 클래스의 이름은 명사이어야 한다.
      (ex) Place (o) , Change (x))
    - 각 단어의 첫글자는 대문자로한다.(파스칼 케이스)
      (ex) PlaceCalulator (o) , placeCalculator (x) , Placecalculator (x) , placecalculator(x))
    - 이니셜등 축약어는 잘 알려진 축약어가 아니라면 피한다.
      (ex) PlaceCalculator (o) , PlaceCCL (x))
~~~java
Class 클래스이름{
     //클래스 내용
}

~~~

~~~java
Class Test{
    //속성
    private int a;

    //메서드
    public int getA(){
        return a;
    }
}

~~~

## 클래스 구조
- `필드` : 해당 클래스 객체의 속성(상태)을 나타내며, 멤버 변수라고도 불린다.
  (여기서 초기화 하는것을 필드초기화 또는 명시적 초기화라고 한다.)
    - **인스턴스 변수** : 인서턴스가 생성될때 만들어지는 인스턴스가 갖는 변수다. 서로 독립적인 값을 가지므로 heap 영역에 할당되고 GC에 의해 관리된다.
    - **클래스 변수** : `static` 키워드가 **인스턴스 변수** 앞에 붙으면 클래스 변수이다. 해당 클래스에서 파생된 모든 인스턴스 변수는 이 변수를 공유하게 된다. 그러므로, heap영역이 아닌 static 영역에 할당된다.
- `메소드` : 객체의 행동을 나타내며, 보통 필드의 값을 조정하는데 쓰인다.
    - **인스턴스 메소드** : 인스턴스 변수와 연관된 행동을 하는 메소드. 인스턴스를 통해 호출할 수 있으므로 반드시 인스턴스 생성을 먼저 해야한다.
    - **클래스 메소드** : 정적 메소드로, 보통 인스턴스와 관계없는 메소드를 클래스 메소드로 정의한다.
- `생성자` : 객체가 생성된 직후, 클래스의 객체를 초기화하는데 사용되는 코드블록이다. 리턴타잆이 없고 클래스에는 최소 한개이상의 생성자가 존재한다.
- `초기화 블록` : 초기화 블록 내에서는 조건문, 반복문 등을 사용하여 명시적 초기화에서 불가능한 초기화가 가능하다.
    - **인스턴스 초기화 블록** : 인스턴스 변수 초기화에 쓰인다.
      (기본값 -> 명시적 초기화 -> 인스턴스 초기화 블록 -> 생성자)
    - **클래스 초기화 블록** : 클래스 변수 초기화에 쓰인다.
      (기본값 -> 명시적 초기화 -> 클래스 초기화 블록)

~~~java
class Class {               // 클래스
    String constructor;
    String instanceVar;     // 인스턴스 변수
    static String classVar; // 클래스 변수

    static {                // 클래스 초기화 블록
        classVar = "Class Variable";
    }

    {                       // 인스턴스 초기화 블록
        instanceVar = "Instance Variable";
    }

    Class() {                // 생성자
        constructor = "Constructor";
    }

    void instanceMethod() {       // 인스턴스 메서드
        System.out.println(instanceVar);
    }

    static void classMethod() {   // 클래스 메서드
        System.out.println(classVar);
    }
}
~~~

## 객체를 만드는 법(인스턴스화)
- 인스턴스화는 new 연산자를 통해 이루어지며, 이후 변수명을 통해 객체를 사용하게 된다.

> 클래스 --(인스턴스화)--> 객체

~~~java
클래스명 변수명 = new 클래스명();
~~~

### new 연산자
- new연산자는 클래스 type의 인스턴스를 생성해주는 역할을 하는 연산자이다.
- heap영역에 공간을 할당받고 그 공간의 주소(참조값)값을 객체에게 반환해주는 역할을 한다.

**# 객체 생성과정**
1. new연산자가 메모리 영역(heap)에 저장공간을 할당받는다.
2. 생성자가 객체를 초기화
3. new연산자가 새로 생긴 객체의 주소를 변수에 저장한다.
4. 변수를 통해 해당 객체에 접근한다.

### 메소드 정의하는 방법
~~~java
접근제어자 리턴타입 메소드명(파라미터){
   // 실행할코드
   return 리턴타입;
} 
~~~
- `접근제어자` : 해당 메소드에 접근할 수 있는 범위를 명시한다.
- `리턴타입` : 메소드가 모든 작업을 수행한 뒤 반환할 타입을 명시한다.
- `메소드명` : 메소드 이름이다. 컨벤션의 유의해야한다.
- `파라미터` : 메소드에서 사용할 매개변수들을 명시한다.
- `메소드 시그니처(메소드명+파라미터)` : 컴파일러가 오버로딩할때 구별하기 위한곳이다.
- 네이밍 컨벤션
    - 메소드의 이름은 동사를 포함해야한다.
      (ex) apple (x) , changeColor (o))
    - 각 단어의 첫글자는 소문자로, 나머지는 대문자로한다.(카멜 케이스)
      (ex) computeCoordinate (o), coordinatecalculate (x))
    - 자바 코딩 컨벤션은 회사마다 조금씩 다르게 사용하지만 대체적으로 거의 비슷하니 일반적으로 사용하는 컨벤션들은 외워두는것이 좋다.

### 접근제어자

`public`
- public으로 선언된 클래스 멤버 및 메소드는 프로그램 **어디에서나 접근이 가능하다.**
  ![](https://images.velog.io/images/dragon9265/post/378380f0-e7c9-4f3c-ae1a-61413f5a0c92/image.png)

`dafault`
- 접근제어자를 따로 명시하지 않을때, 자바에서 기본적으로 명시되는 접근제어자다. **같은 패키지만 접근이 가능하다.**
  ![](https://images.velog.io/images/dragon9265/post/483bbba9-1c4f-46bc-b8ed-d079a033ac9e/image.png)

`private`
- 정보은닉을 위한 접근제어자로 외부로부터 접근을 불가하게 해준다. **선언한 클래스 이외에는 접근이 불가하다. 접근하기위해서는 getter/setter 설정하여 사용한다.**
  ![](https://images.velog.io/images/dragon9265/post/06c8ac10-8994-4da9-9ebd-5ebf15c28c08/image.png)

`protected`
- dafault 접근제어자에서 권한이 하나 더 추가된 접근제어자다. **다른 패키지에서 해당 클래스를 상속받을 경우 사용권한이 생기게 된다.**
  ![](https://images.velog.io/images/dragon9265/post/6339ab9c-2fd8-4ae0-bce2-90c2798c3c8c/image.png)

### 생성자
**# 생성자의 특징**
1. 생성자의 메서드명은 반드시 클래스이름과 동일해야한다.
2. 생성자 앞에 붙는 접근제어자는 보통 public을 쓴다.
3. 생성자는 return 값을 가지지 않는다.
4. 외부에서는 객체를 생성할 수 없고, 내부에서만 객체를 생성할 수 있다는 특징을 이용하기 위해 private 생성자를 사용한다.

- 생성자는 미 선언시 기본생성자를 자동으로 생성해주지만, 만약 기본 생성자가 아닌 다른형태의 생성자만 명시해두었다면 기본 생성자는 컴파일시에 생성되지 않는다.

**# 기본생성자 + 생성자를 가지는 경우**
~~~java
public Class Test{
      int a;
      
      //기본생성자
      public void Test(){}

      //매개변수를 가진 생성자
      public void Test(int a){
            this.a = a;
      }
}

~~~

**# 기본생성자를 생성해주지 않는 경우**
~~~java
class Test1{} // 기본생성자

class Test2{
    int a;
    Test2(int a){
        this.a = a;
    }
}

class ConstructorTest{
    public static void main(String[] args]{
        Test t1 = new Test1();    //기본생성자를 만들어주므로 에러가 안남.
        Test t2 = new Test2();    //에러발생 (매개변수를 가진 생성자를 가지고 있기 때문)
    }
}

~~~

### this

- 객체 자기 자신을 나타내는 명령어다.
- 호출한 객체를 명확히 하기위해 쓰이며 3가지 형태로 쓰인다.

**# 클래스의 속성과 매개변수의 이름이 같은 경우 (this. 를 쓰는경우)**
~~~java
public Class Test{
       int a;
       
       public void Test(int a){
            this.a = a;
       }
}

~~~
- 클래스 속성인 a를 매개변수 a로 초기화 해주고 있다.
- this.a = a;의 문장은 `해당 인스턴스 필드값인 this.a를 외부의 매개변수로 받은 a로 초기화시킨다` 라는 의미다.

**# 객체 자신의 참조값을 전달하고 싶은 경우(this 를 쓰는경우)**
~~~java
public Class Test{
       int a;
       public Test(int a){
            this.a = a;
       }
        
       public Test getTest(){
            return this;
       }
}

~~~

**# 오버로딩된 다른 생성자를 호출하는 경우(this()를 쓰는경우)**
~~~java
public Class Test{
       int a;
       int b;

       public void Test(){ // 오버로딩1
           this(null,null);
       }

       public void Test(int a){ // 오버로딩2
           this(a, null);
       }

       public void Test(int b){ // 오버로딩3
           this(null, b);
       }

       public void Test(int a , int b){
            this.a = a;
            this.b = b;
       }
}

~~~
- this() 는 생성자를 부르기 위해 사용된다. 보통 위와같이 속성을 초기화하는 부분을 축약할때 사용되어 진다.
- 주의할점은 this()는 **반드시** 생성자의 첫번쨰 문장에 사용되어야 한다.


***

ref.
https://blog.naver.com/swoh1227/222174170682
https://jeeneee.dev/java-live-study/week5-class/
https://leemoono.tistory.com/17
