# Optional Class

## ****NPE(NullPointerException)****

개발을 할 때 가장 많이 발생하는 예외 중 하나가 바로 NPE(NullPointerException)이다. NPE를 피하기 위해서는 null을 검사하는 로직을 추가해야 하는데, null 검사를 해야하는 변수가 많은 경우 코드가 복잡해지고 로직이 상당히 번거롭다. 

```java
List<String> names = getNames(); 
names.sort(); // names가 null이라면 NPE가 발생함

List<String> names = getNames(); 

// NPE를 방지하기 위해 null 검사를 해야함 
if(names != null){
	names.sort(); 
}
```

## Optional

Java8에서는 Optional<T> 클래스를 사용해 NPE를 방지할 수 있도록 도와준다. 

Optional<T>는 null이 올 수 있는 값을 감싸는 Wrapper 클래스로, 참조하더라도 NPE가 발생하지 않도록 도와준다. 

## Optional 객체 생성

Optional은 Wrapper 클래스이기 때문에 빈 값이 올수도 있는데, 빈 객체는 아래와 같이 생성할 수 있다.

```java
Optional<String> optional = Optional.empty();
// String temp = "";

System.out.println(optional); // Optional.empty
System.out.println(optional.isPresent()); // false
```

만약 어떤 데이터가 null이 올 수 있는 경우에는 해당 값을 Optional로 감싸서 생성할 수 있다. 

그리고 orElse 또는 orElseGet 메소드를 이용해서 값이 없는 경우라도 안전하게 값을 가져올 수 있다.

```java
// Optional의 value는 값이 있을 수도 있고 null 일 수도 있다.
Optional<String> optional = Optional.ofNullable(getName());
String name = optional.orElse("anonymous"); // 값이 없다면 "anonymous" 를 리턴
```

## ****Optional 사용하기****

기존에는 아래와 같이 null 검사를 한 후에 null일 경우에는 새로운 객체를 생성해주어야 했다. 이러한 과정을 코드로 나타내면 다소 번잡해보이는데, Optional<T>와 Lambda를 이용하면 해당 과정을 보다 간단하게 표현할 수 있다.

```java
// Java8 이전
List<String> names = getNames();
List<String> tempNames = list != null ? list : new ArrayList<>();

List<String> nameList = Optional.ofNullable(getList())
														.orElseGet(() -> new ArrayList<>());
```

### 우편번호 예제

Optional 사용 전

```java
public String findPostCode() { 
	UserDTO userDTO = getUser();
	if (userDTO != null) {
		Address address = user.getAddress();
		if (address != null) { 
			String postCode = address.getPostCode();
			if (postCode != null) {
				return postCode; 
			}
		} 
	} 
	return "우편번호 없음";
}
```

Optional 사용 후

```java
public String findPostCode() {

	// 위의 코드를 Optional로 펼쳐놓으면 아래와 같다.
	Optional<UserDTO> userDTO = Optional.ofNullable(getUser());
	Optional<Address> address = userDTO.map(UserDTO::getAddress); 
	Optional<String> postCode = address.map(Address::getPostCode);
	String result = postCode.orElse("우편번호 없음");
	
	// 그리고 위의 코드를 다음과 같이 축약해서 쓸 수 있다.
	String result = user.map(UserDTO::getAddress)
											 .map(Address::getPostCode) 
										 	 .orElse("우편번호 없음"); 
}
```

### 예외처리 예제

Optional 사용 전

```java
String name = getName();
String result = "";

try {
	result = name.toUpperCase(); 
}  catch (NullPointerException e) { 
	throw new CustomUpperCaseException(); 
}
```

Optional 사용 후

```java
Optional<String> nameOpt = Optional.ofNullable(getName());
String result = nameOpt.orElseThrow(CustomUpperCaseExcpetion::new) 
											.toUpperCase();
```

## Optional 정리

- Optional은 null 또는 실제 값을 value로 갖는 wrapper로 감싸서 NPE(NullPointerException)로부터 자유로워지기 위해 나온 Wrapper 클래스이다.
- Optional을 반환하는 메소드는 절대 null을 갖는 value를 반환해서는 안된다.
    - Optional은 값을 Wrapping하고 다시 풀고, null일 경우에는 대체하는 함수를 호출하는 성능이 저하될 수 있다.

<aside>
💡 메소드의 반환 값이 절대 null이 아니라면 Optional을 사용하지 않는 것이 성능저하가 적다. 즉, Optional은 메소드의 결과가 null이 될 수 있으며, 클라이언트가 이 상황을 처리해야 할 때 사용하는 것이 좋다.

</aside>