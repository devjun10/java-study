## 함수형 인터페이스와 람다 표현식 소개

###1급 객체 (First-class citizen)의 세가지 조건
- 변수나 데이터에 할당 할 수 있어야 한다.
- 객체의 인자로 넘길 수 있어야 한다. 
- 객체의 리턴값으로 리턴 할 수 있다.

### 함수형 인터페이스 
- 람다 함수의 객체 타입을 만들기 위한 인터페이스
- 추상 메서드가 하나만 있어야 함(추상 메서드가 아닌 메서드는 ok)

### 함수형 프로그래밍
- 자바에서 함수란 특수한 형태의 object 
- 순수 함수: 입력 받은 값이 동일한 경우 결과도 같아야한다  
아래의 예시는 외부값인 baseNumber에 의해 결과가 영향을 받으므로 순수함수(X)
```java
public class Foo {
	public static void main(String[] args) {
		RunSomething runSomething = new RunSomething() {
			int baseNumber = 10;

			@Override
			public void doIt() {
				baseNumber++;
				return number + baseNumber;
			}
		};
	}
}
```
- 고차 함수: 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수 있다(1급객체 조건 2, 3)

### 변수 캡쳐
- 람다 함수의 파라미터로 넘겨진 변수가 아니라 바디 외부에 있는 변수를 참조하는 것
- 제약조건: 지역변수는 `final`로 선언 또는 값의 재할당이 일어나지 않아 `사실상` `final` 과 같을것(`effective final`) 
- 쉐도잉: 같은 이름의 여러 로컬 변수가 존재 할 때 가장 안쪽의 로컬 변수가 참조되는 것(데이터 덮어쓰기). 익명 클래스와 로컬 클래스는 내부에 새로운 Scope을 만들지만, 람다함수는 새로운 Scope을 만들지 않고 기존 변수와 동일한 Scope을 공유하기 때문에 쉐도잉이 일어나지 않음. 
    