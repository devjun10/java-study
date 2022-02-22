# 애너테이션이란?
- JDK 1.5이상부터 등장한 기능으로, 사전적 의미로는 **주석**을 의미한다. 자바에서는 단순히 주석이 아닌 클래스에 특수한 의미를 부여하거나 기능을 주입하기 위한 **메타데이터**라고 볼 수 있다.

# 필요한 개념 정리
- `메타데이터` : 데이터에 대한 데이터, 즉 다른 데이터를 설명해주는 데이터이다.
  (	ex. 카메라로 사진을 찍었을 때 사진에 대한 `촬영한 시간, 장소, 카메라 모델명, 플래쉬 사용 유무, 등등`이 메타데이터라고 할 수 있다. )

-	`애너테이션의 용도`
     - 컴파일러에 제공하는 정보 : 컴파일러는 애너테이션을 사용하여 에러를 체크하거나 에러메시지를 억제할 수 있다.
     (	ex. @Override, @SuppressWarnings )
     - 컴파일 시간 및 배포 시간 처리 : 소프트웨어 개발툴이 애너테이션 정보를 처리하여 코드, XML파일등을 생성할 수 있다.
     (	애너테이션을 사용한 곳에 특정 코드를 추가할 수 있다. ex. Lombok의 @Getter, @Setter	)
     - 런타임 처리 : 일부 애너테이션은 런타임에 특정 기능을 실행하도록 정보를 제공한다.
     (	자바 리플렉션 )

- `자바 리플렉션` : 구체적인 클래스 타입을 알지 못해도 그 클래스의 메소드, 타입, 변수들에 접근할 수 있도록 해주는 자바 API이다.
  런타임 시에 클래스 이름만 알고 있다면 클레스에 대한 정보를 가져오고 활용할 수 있게 해준다.

**클래스이름.class를 이용해서 클래스의 필드, 생성자, 메서드에 대한 정보를 얻을 수 있다.**

|메서드명|리턴타입|설명|
|:------|:---:|:------:|
|**getFields()**|Field[]|접근 제어자가 public인 필드들을 Field 배열로 반환. 부모 클래스의 필드들도 함께 반환한다.|
|**getConstructors()**|Constructor[]|	접근 제어자가 public인 생성자들을 Constructor 배열로 반환. 부모 클래스의 생성자들도 함께 반환한다.|
|**getMethods()**|Method[]|접근 제어자가 public인 메서드들을 Method 배열로 반환. 부모 클래스의 메서드들도 함께 반환한다.|
|**getDeclaredFields()**|Field[]|접근 제어자에 상관없이 모든 필드들을 Field배열로 반환. 부모 클래스의 필드들은 반환하지 않는다.|
|**getDeclaredConstructors()**|Constructor[]|접근 제어자에 상관없이 모든 생성자들을 Constructor배열로 반환. 부모 클래스의 생성자들은 반환하지 않는다.|
|**getDeclaredMethod()**|Method[]|접근 제어자에 상관없이 모든 메서드들을 Method배열로 반환. 부모 클래스의 메서드들은 반환하지 않는다.|

**애노테이션 정보를 얻기위한 메서드들.**

|리턴타입|메서드명|설명|
|:------|:---:|:------:|
|**boolean**|isAnnotationPresent(Class<? Extends Annotation> annotationClass)|지정한 애노테이션이 적용되었는지 여부를 확인. Class에서 호출했을 경우 상위 클래스에 적용된 경우에도 true를 리턴.|
|**Annotation**|getAnnotation(Class<T> annotationClass)|지정한 애노테이션이 적용되어 있으면 애노테이션을 반환하고 그렇지 않은 경우 null을 반환한다. Class에서 호출한 경우 상위 클래스에 적용된 애노테이션도 반환한다.|
|**Annotation[]**|getAnnotations()|적용된 모든 애노테이션을 반환한다. Class에 사용됐을 경우 상위 클래스에 적용된 애노테이션까지 전부 포함해서 반환한다. 애노테이션이 없을 경우 길이가 0인 배열을 반환한다.|
|**Annotation[]**|getDeclaredAnnotation()|직접 적용된 모든 애노테이션을 리턴한다. Class에서 호출했을 경우 상위 클래스에 적용된 애노테이션은 포함되지 않는다.(상위 클래스의 @Inherited가 붙은 애노테이션을 무시한다.)|


# 애너테이션이 나타난 배경

- 기존의 자바를 개발하던 사람들은 **웹 애플리케이션에서 구성과 설정값을 XML 설정 파일을 통해 명시하고 관리**하였다.
- 이는 변경될 수 있는 데이터들을 **코드가 아닌 외부 파일로 분리함으로써 컴파일 없이 변경사항을 적용하기 위함**이였다.
- 하지만 이런 방법은 **프로그램 작성때마다 많은 설정을 작성해야 하는 불편함**을 낳게 되었다.
- 이런 불편함을 해소하기 위해, **데이터에 대한 유효성 검사 조건을 보다 쉽게 파악할 수 있는 애너테이션을 사용**하게 된것이다.

# 애너테이션 정의하는 방법

~~~java

public @interface MyAnnotation{
}

~~~
	🔺애너테이션을 직접 정의하는 방법이다.
	@interface'는 애너테이션 타입(annotation type)을 선언하는 키워드다.
    애너테이션 타입 선언을 일반적인 인터페이스(interface) 선언과 구분하기 위해 interface 앞에 기호 @를 붙인다.

## 애너테이션 필드
- 애너테이션에 필드같은 요소들을 정의할 수 있다.(사실은 추상 메서드)
- 요소의 타입은 기본형, String, enum, 애노테이션, Class만 가질 수 있다.
- ()안에 매개변수를 선언할 수 없다.
- 예외를 선언할 수 없다.
- 요소를 타입 매개변수로 정의할 수 없다.
- 배열을 선언할 수 있다.  String[] arr();
- default값을 지정할 수 있다. int number() default 500;

~~~java

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyAnnotation{
	int number() default 500; // int 타입(기본형)
    String value(); 		// String
    String[] arr();			// 배열로 생성
    Operation opration();	// Enum 타입
    Class cc();				// Class 타입
    Target t();				// Target 애너테이션
}

~~~
	🔺애너테이션 필드종류는 위와 같이 선언이 가능하고, 각각 요소에 값을 줘서 아래와 같이 사용할 수 있다.

~~~java

public class MyClass{

	@MyAnnotation
    	value = "java study",
        arr = {"park", "kim", "lee"},
        operation = Operation.DIVIDE,
        cc = MyClass.class,
        t = @Target(ElementType.ANNOTATION_TYPE))
	public void method() {
    }
	
	public static void main(String[] args) {
    }
}

~~~

# 애너테이션 종류

- **Builit-in Annotation** : 자바에서 기본적으로 제공하는 애너테이션
- **Meta Annotation** : 커스텀 애너테이션을 만들수 있게 제공된 애너테이션
  (애너테이션을 정의하는데 사용하는 애너테이션의 애너테이션
- **Custom Annotation** : Meta Annotation을 통해 만든 애너테이션

## Meta Annotation

|애너테이션|설명|
|:------|:---|
|**@Target**|애너테이션이 적용가능한 대상을 지정하는데 사용한다.|
|**@Documented**|애너테이션 정보가 javadoc으로 작성된 문서에 포함되게 한다.|
|**@Inherited**|애너테이션이 자손 클래스에 상속되도록 한다.|
|**@Retention**|애너테이션이 유지되는 범위를 지정하는데 사용한다.|
|**@Repeatable**|애너테이션을 반복해서 사용할 수 있게 한다. (JDK 1.8)|


### @Target
- 애너테이션이 적용가능한 대상을 지정하는데 사용한다.
- 적용가능한 대상을 여러개 지정하고 싶다면 {} 배열을 사용하면 된다.

~~~java

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target{
	ElementType[] value();
}
~~~
	🔺@Target 애너테이션 실제 코드
    ElementType의 Enum 요소를 사용해서 애너테이션이 적용 가능한 대상을 결정한다.
    
    @Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
    요소타입이 ElementType[] 배열이기 때문에 적용가능한 대상을 여러 개의 값을 지정할 수 있다.

✅**@Target으로 지정할 수 있는 애너테이션 적용대상의 종류**

|ElementType|대상 타입|
|:------|:---|
|**ANNOTATION_TYPE**|애너테이션|
|**CONSTRUCTOR**|생성자|
|**FIELD**|필드(멤버변수, enum상수)|
|**LOCAL_VARIABLE**|지역변수|
|**METHOD**|메서드|
|**PACKAGE**|패키지|
|**PARAMETER**|매개변수|
|**TYPE**|타입(클래스, 인터페이스, enum)|
|**TYPE_PARAMETER**|타입 매개변수|
|**TYPE_USE**|타입이 사용되는 곳(타입의 변수를 선언할 때)|

~~~java

@ExAnnotation // ElementType.TYPE
public class ExClass {

	@ExAnnotation // ElementType.FIELD
    int num;
    
    @ExAnnotation // ElementType.TYPE_USE
    ExClass ec;
    
    @ExAnnotation // ElementType.METHOD
    public void method() {
    }
}
~~~

### @Inherited
- 애너테이션이 자손 클래스에 상속되도록 한다.
- @Inherited가 붙은 애너테이션을 조상클래스에 붙이면, 자손 클래스도 동일한 애너테이션이 붙은것과 같이 인식한다.

### @Retention
- 애너테이션의 유지시간(Retention)을 지정한다.
- 즉, 어느 시점까지 애너테이션의 메모리를 가져갈지 결정한다.

✅**애너테이션의 유지 정책(Retention policy)의 종류**

|유지정책|의미|
|:------|:---|
|**SOURCE**|소스 파일에만 존재. 클래스 파일에는 존재하지 않음|
|**CLASS**|클래스 파일에 존재. 실행시에 사용 불가. Default 값|
|**RUNTIME**|클래스 파일에 존재. 실행시에 사용가능|

~~~java

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention{
	RetentionPolicy value();
}
~~~
	🔺@Retention 애너테이션 실제 코드
    RetentionPolicy의 값을 넘겨주는 것으로 애너테이션의 유지 정책이 결정된다고 볼 수 있다.
    RetetionPolicy는 유지정책 3가지를 지니고 있는 Enum 형태를 갖고 있다.

#### SOURCE
- 컴파일러가 컴파일 할 때 해당 애너테이션의 메모리는 버린다.
- 즉, 사실상 주석처럼 사용하는것
- @Override나 @SuppressWarnings 처럼 컴파일러에 의해 사용되는 애너테이션은 유지정책이 `SOURCE`이다.
- 직접 컴파일러를 만드는것이 아니라면 주석처럼 사용된다.

#### CLASS(default값)
- 컴파일러가 컴파일 시에는 애너테이션의 메모리를 가져간다.
- 하지만 실질적으로 런타임시에는 사라지게 된다.
- 런타임시에 사라진다는 것은 리플렉션으로 선언된 애너테이션 데이터를 가져올 수 없게 되는것
- 즉, 런타임시에 이미 애너테이션에 대한 메모리가 없으므로 실행 타임에서 애너테이션에 대한 데이터를 활용할 수 없다는것을 뜻한다.
  (그래서 잘안쓰인다.)

#### RUNTIME
- 애너테이션을 런타임시까지 사용할 수 있다.
- JVM이 자바 바이트코드가 담긴 class 파일에서 런타임 환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있다.
- 실행 시, 리플렉션을 통해 클래스 파일에 저장된 애너테이션의 정보를 읽어서 처리할 수 있다.

### @Repeatable
- 보통은 하나의 대상에 한 종류의 애너테이션을 붙이지만 @Repeatable이 붙은 에너테이션은 여러번 붙일 수 있다.
- 적용가능한 대상을 여러개 지정하고 싶다면 {} 배열을 사용하면 된다.

~~~java

// @ToDo 애너테이션을 여러 번 반복해서 쓸 수 있게 한다.
@Repeatable(Todos.class)
@interface Todo {
	String value();
}
~~~
	🔺@ToDo라는 애너테이션이 위와 같이 정의되어 있을 때, 특정 클래스에 ToDo 애너테이션을 
    여러 번 붙이는 것이 가능하다.


~~~java

@ToDo("delete test codes.")
@ToDo("override inherited methods")
class MyClass{
	
}
~~~

	🔺일반적인 애너테이션과 달리, 같은 이름의 애너테이션이 여러개가 하나의 대상에 적용될 수 있기 때문에, 
    해당 애너테이션들을 하나로 묶어서 다룰 수 있는 애너테이션도 추가로 정의해야 한다. 
    위 예졔의 경우 Todos.class가 그 역할을 한다.

~~~java

@interface ToDos{   // 여러 개의 ToDo 애너테이션을 담을 컨테이너 애너테이션
	ToDo[] value();   // ToDo애너테이션 배열타입의 요소를 선언. "이름이 반드시 value"
}

@Repeatable(ToDos.class)
@interface ToDo{
	String value();
}
~~~

✅**@Target으로 지정할 수 있는 애너테이션 적용대상의 종류**

|ElementType|대상 타입|
|:------|:---|
|**ANNOTATION_TYPE**|애너테이션|
|**CONSTRUCTOR**|생성자|
|**FIELD**|필드(멤버변수, enum상수)|
|**LOCAL_VARIABLE**|지역변수|
|**METHOD**|메서드|
|**PACKAGE**|패키지|
|**PARAMETER**|매개변수|
|**TYPE**|타입(클래스, 인터페이스, enum)|
|**TYPE_PARAMETER**|타입 매개변수|
|**TYPE_USE**|타입이 사용되는 곳(타입의 변수를 선언할 때)|

~~~java

@ExAnnotation // ElementType.TYPE
public class ExClass {

	@ExAnnotation // ElementType.FIELD
    int num;
    
    @ExAnnotation // ElementType.TYPE_USE
    ExClass ec;
    
    @ExAnnotation // ElementType.METHOD
    public void method() {
    }
}
~~~


## Built-in Annotation

|애너테이션|설명|
|:------|:---|
|**@Override**|컴파일러에게 오바리이딩하는 메서드라는 것을 알린다.|
|**@Deprecated**|앞으로 사용하지 않을 것을 권장하는 대상에 붙인다.|
|**@FunctionalInterface**|함수형 인터페이스라는 것을 알린다. (JDK 1.8)|
|**@SuppressWarnings**|컴파일러의 특정 경고메시지가 나타나지 않게 해준다.|
|**@SafeVarargs**|제네릭스 타입의 가변인자에 사용한다. (JDK 1.7)|
|**@Native**|native 메서드에서 참조되는 상수 앞에 붙인다. (JDK 1.8)|

### @Override
- 메서드 앞에만 붙일 수 있는 애너테이션
- 조상의 메서드를 오버라이딩하는 것이라는걸 컴파일러에게 알려주는 역할
- 애너테이션이 없다면 아래와 같이 오버라이드 매서드명을 오타 내도 컴파일러는 알지 못한다.
- 위와 같은 이유로 필수는 아니지만 붙이는 것이 에러방지 측면에서 좋다.

~~~java

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override
~~~
	🔺@Override 애너테이션 실제 코드

~~~java

public class Annotation {

	// 에러없음, 단지 새로운 메서드라고 감지
    public String tString() {
        return "";
    }
    
	// 에러감지
	// java: method does not override or implement a method from a supertype
    @Override
    public String rString() {
        return "";
    }
}
~~~

### @Deprecated
- 개선된 버전의 기능이 새로 생겼으니 사용하지 말기를 권고하는 애노테이션
- 굳이 사용한다면 프로그램에 전혀 이상은 없다.
- 개선된 버전이 나왔다고 해도 이미 레거시에서 많이 구 버전을 사용하고 있을 것이므로 삭제가 불가능하여 해당 애노테이션을 사용한다.
- 아래는 Date 클래스에서 사용하는 예제이다.
~~~java

@Deprecated
public int getDate() {
    return normalize().getDayOfMonth();
}
~~~

### @FunctionalInterface
- 함수형 인터페이스를 선언할 때, 이 애노테이션을 붙이면 컴파일러가 함수형 인터페이스를 올바르게 선언 했는지 확인하고, 에러를 발생 시킨다.
  (ex. 메서드가 0개 혹은 2개 이상일때 에러를 발생시킨다.)
- 즉, 함수형 인터페이스를 선언할 때는 반드시 사용하도록 하자.
- 아래는 대표적인 함수형 인터페이스인 Runnable 인터페이스 이다.
~~~java

@FunctionalInterface
public interface Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see     java.lang.Thread#run()
     */
    public abstract void run();
}
~~~

### @SuppressWarnings
- 컴파일러가 보여주는 경고 메시지를 나타나지 않게 억제해준다.
- 일반적으로 경고가 발생하면 해결하고 다시 컴파일하여 에러 메시지를 나타나지 않게 하지만, **특수한 경우에 경고가 발생할 것을 알면서도 묵인해야 할때**가 있다.
- 이럴때 @SuppressWarnings 를 사용해서 특정 경고 메시지가 나오지 않게 할 수 있다.



✅**@SuppressWarnings의 토큰종류**
(**@SuppressWarnings("토큰")** 형태로 사용한다.)

|토큰|설명|
|:------|:---|
|**all**|모든 경고를 억제합니다.|
|**boxing**|오퍼레이션과 관련된 경고를 억제합니다.|
|**cast**|캐스트 오퍼레이션과 관련된 경고를 억제합니다.|
|**dep-ann**|권장되지 않는 애노테이션과 관련된 경고를 억제합니다.|
|**deprecation**|권장되지 않는 기능과 관련된 경고를 억제합니다. (@Deprecated)|
|**fallthrough**|switch문에서 누락된 break 문과 관련된 경고를 억제합니다.|
|**finally**|리턴되지 않는 마지막 블록과 관련된 경고를 억제합니다.|
|**hiding**|변수를 숨기는 로컬과 관련된 경고를 억제합니다.|
|**incomplete-switch**|switch 문에서 누락된 항목과 관련된 경고를 억제합니다.(enum case)|
|**javadoc**|javadoc 경고와 관련된 경고를 억제합니다.|
|**null**|널(null) 분석과 관련된 경고를 억제합니다.|
|**rawtypes**|원시 유형 사용법과 관련된 경고를 억제합니다.|
|**restriction**|올바르지 않거나 금지된 참조 사용법과 관련된 경고를 억제합니다.|
|**resource**|닫기 가능 유형의 자원 사용에 관련된 경고 억제합니다.|
|**serial**|직렬화 가능 클래스에 대한 누락된 serialVersionUID필드와 관련된 경고를 억제합니다.|
|**static-access**|잘못된 정적 액세스와 관련된 경고를 억제합니다.|
|**static-method**|static으로 선언될 수 있는 메서드와 관련된 경고를 억제합니다.|
|**super**|슈퍼 호출을 사용하지 않는 메서드 오버라이딩과 관련된 경고를 억제합니다.|
|**synthetic-access**|내부 클래스로부터의 최적화되지 않은 액세스와 관련된 경고를 억제합니다.|
|**sync-override**|동기화된 메서드를 오버라이드하는 경우 누락된 동기화로 인한 경고 억제합니다.|
|**unchecked**|미확인 오퍼레이션과 관련된 경고를 억제합니다.|
|**unqualified-field-access**|규정되지 않은 필드 액세스와 관련된 경고를 억제합니다.|
|**unused**|사용하지 않은 코드 및 불필요한 코드와 관련된 경고를 억제합니다.|

~~~java

class MyThread extends Thread {

	@SuppressWarnings("deprecation") // 에러 메시지 억제
    @Override
    public void run() {
    	stop();
    }

}

~~~
	🔺@SuppressWarnings 애너테이션 토큰사용 예시


### @SafeVarargs
- 메서드에 선언된 가변인자의 타입이 비 구체화 타입(non-reifiable type)일 경우,
  해당 메서드를 선언하는 부분과 호출하는 부분에서 `unchecked`경고가 발생한다.
- 해당 코드에 문제가 없다면 이 경고를 억제하기 위해 @SafeVarargs를 사용해야 한다.
- static 이나 final이 붙은 메서드와 생성자에만 붙일 수 있다.
  (즉, 오버라이드될 수 있는 메서드에는 사용이 불가하다.)

>
#### 비 구체화 타입(non-reifiable type)
- 컴파일 후에 제거되는 타입(런타임에 구체화되지 않는다.)
- 컴파일 후에 타입 정보가 유지되지 않는다는 의미이다.
#### 구체화 타입(reifiable type)
- 컴파일 후에도 제거되지 않는 타입(런타임에 구체화된다.)
- 컴파일 후에도 타입 정보가 유지된다는 의미이다.

~~~java

@SafeVarargs // unchecked 억제
@SuppressWarnings("varargs") // vargars 억제
public static <T> List<T> asList(T... var0) {
    return new Arrays.ArrayList(var0);
}

~~~

	🔺java.utill.Arrays의 asList()는 위처럼 정의되어 있으며, 이 메서드는 매개변수로 넘겨받은 값들로 배열을 
    만들어서 새로운 ArrayList 객체를 만들어서 반환하는데 이 과정에서 "unchecked"경고가 발생한다.


- 매개변수가 가변인자인 동시에 제네릭 타입이다.
- 메서드에 선언된 타입 T는 컴파일 과정에서 Object로 변경된다.
  (즉, Object[]가 되는 것이다.)
- 그러면, Object[]에는 모든 타입의 객체가 들어있을 수 있으므로, 이 배열로 ArrayList<T'>를 생성하는것이 위험하다고 경고하는 것이다.
- 하지만 사용하는 쪽에서 asList()가 호출되는 부분을 타입 T가 아니면 들어가지 못하게 하도록 만들어 놨다면, 전혀 문제가 없다.
- 전혀 문제가 없으므로, 경고를 없애기 위해 해당 에너테이션을 사용해야 한다.
- 메서드 선언 시 @SafeVarargs를 붙이면, 이 메서드를 호출하는 곳에서 발생하는 경고도 무시된다.
- @Suppresswarnings("unchecked")로 경고를 억제하려면, 메서드 선언뿐 아니라 메서드가 호출되는 곳에도 애너테이션을 붙여야 한다.
- @SafeVarargs로 'unchecked' 경고는 억제 가능하지만, 'varags'경고는 억제가 불가능 하므로 습관적으로 위처럼 @SafeVarargs와 @SuppressWarnings("varargs")를 같이 붙이는 것을 권장한다.
