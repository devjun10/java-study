## 예외 / 에러의 종류
1. **Error**
시스템 레벨에서 발생 하는 오류. 프로그램은 비정상 종료된다.
ex: OutOfMemoryError, StackOverflowError

2. **Checked Exception**
컴파일 단계에서 걸러지는 예외 (인텔리제이 빨간줄). 처리하지 않으면 컴파일 자체가 되지 않아 프로그램을 실행할 수 없다. 

3. **Unchecked Exception(Runtime Exception)**
프로그램 실행 시점에 발생하게 되는 예외
![](https://images.velog.io/images/gruzzimo/post/5a65bf05-105b-43e4-83a9-29512d2d6566/image.png)

출처 : [madplay.github.io](https://www.google.com/url?sa=i&url=https%3A%2F%2Fmadplay.github.io%2Fpost%2Fjava-checked-unchecked-exceptions&psig=AOvVaw0E0LiEbHPnGuRa3M-XbqPp&ust=1646303512318000&source=images&cd=vfe&ved=0CAwQjhxqFwoTCLDo7dScp_YCFQAAAAAdAAAAABAD})

---
## try/catch

```java
try {
    // 예외가 발생할 가능성이 있는 코드
    int a = 5/0;
    String st = bufferedReader.readLine();
    int b = Integer.parseInt(st);

} catch (Exception1 e) {
    // Exception1이 발생했을 때, 이를 처리하기 위한 코드
} catch (Exception2 e) {
    // Exception2가 발생했을 때, 이를 처리하기 위한 코드
}
// 이후 실행 로직
```
* 이렇게 연속으로 catch문 사용시 Exception1이 Exception2의 상위 타입 에러라면, 컴파일러 에러가 발생한다. 서로 다른 예외에 대해 다른 처리를 해줄 경우 사용할 수 있다.

```java
try {
    int a = 5 / 0;
} catch (Exception1 | Exception2 e) {
    System.out.println(e.getMessage());
}
```
* 이렇게 Exception1 또는 Exception2 형태로도 쓸 수 있다. 여기서도 두 예외가 서로 상속 관계라면 컴파일 에러가 발생한다. 서로 다른 예외에 대해 같은 처리를 해줄 때 사용할 수 있다.


![](https://images.velog.io/images/gruzzimo/post/74f9bf9c-dc2e-4d3c-82b0-32b568b96d8e/image.png)

* 특정 메서드가 어떤 예외를 발생시킬 수 있는지는 Cmd+우클릭으로 API 문서에 들어가면 쉽게 찾아볼 수 있다.

## throws Exception
```java
public void run(){
	getRemainder(5);
}
    
public int getRemainder(int input){
	return input % 0;
}
```
* 0으로 나누었기 때문에 ArithmeticException 이 발생하여 프로그램은 멈추게 된다.

```java
public void run(){
	getRemainder(5);
}
    
public int getRemainder(int input) throws ArithmeticException{
	return input % 0;
}
```
* throws 를 사용하면 ArithmeticException가 발생했을 때 이 에러를 run() 메서드로 던져버린다.
* run()에서는 에러를 잡을 try/catch 문이 없기 때문에 여전히 프로그램은 멈춘다.

```java
public void run(){
	try{
		getRemainder(5);
    } catch(ArithmeticException e){
    // 로직~~
    }
    // 이후 로직도 실행
}
    
public int getRemainder(int input) throws ArithmeticException{
	return input % 0;
}
```
* 던진 ArithmeticException 을 run에서 try/catch로 잡아서 원하는 처리를 해줄 수 있다.
* 프로그램은 종료되지 않고 getRemainder() 이후의 로직도 무사히 수행할 수 있다.

---

## throw new ~

```java
public void run(){
	try{
		getRemainder(5);
    } catch(IllegalArgumentException e){
    System.out.println(e.getMessage);
    }
    // 이후 로직도 실행
}
    
public int getRemainder(int input){
	try{
	return input % 0;
    } catch(ArithmeticException e){
    throw new IllegalArgumentException("0으로 나눌 수 없습니다.")
    }
}
```

* throw new 를 이용해 원하는 예외와 메세지를 던질 수 있다.


```java

public int getRemainder(int input){
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
try {
    int num = input % Integer.parseInt(br.readLine());
} catch (IOException e) {  			// br.close() 된 상태일 경우

    System.out.println("누가 버퍼드리더를 닫았는가?");
    
} catch (ArithmeticException e) { 	// 분모가 0인 경우

    throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
    
} catch(NumberFormatException e){ 	// 입력값이 숫자가 아닌 경우

    return getRemainder(input);
}

}
```
1. 콘솔 출력만 하고 종료된다.
2. IllegalArgumentException 를 던진다
3. 재귀를 돌려 입력을 다시 받는다. 


## Test에 적용

```java
    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"a", "ab"})
    @DisplayName("빈문자 또는 문자가 입력되면 \"숫자를 입력해주세요.\"라는 메세지를 담은 LottoIllegalInputException을 던진다.")
    
    void validateInteger_InvalidString(String input) {
        assertThatThrownBy(() -> inputValidator.validateInteger(input))
            .isInstanceOf(LottoIllegalInputException.class)
            .hasMessage("숫자를 입력해주세요.");
    }
```

---

## 커스텀 예외

```java
public class LottoIllegalInputException extends RuntimeException{

    public LottoIllegalInputException(String message) {
        super(message);
    }
```
* 상속을 받고 `Ctrl+o` 를 이용해 원하는 생성자를 가져오면 된다. 
![](https://images.velog.io/images/gruzzimo/post/3457815a-2db9-418f-8a01-db7973441f86/image.png)
* 사용 이유 : IAE 같은 범용적인 예외보다 구체적은 예외를 사용하면 
예외 모니터링 시에 원하는 예외만 짚어내어 모니터링 할 수 있다.
![](https://images.velog.io/images/gruzzimo/post/bdcd01f5-1797-440e-a39a-29fd4b4caa1e/image.png)

