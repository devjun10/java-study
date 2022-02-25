자바 8 이전에는 null을 처리하는 방법은 2가지 밖에 없었다.

#### 1. 예외를 던지는 방법
- 예외는 진짜 예외적인 상황에서만 사용하는게 좋다. 
- 확인된 예외를 던지면 반드시 처릭를 해줘야한다, 
- 이 방법을 사용할 경우 Stack Trace를 찍기 때문에 비용이 비싸다.
    > Stack Trace : 에러가 발생하기 전까지 어떤 콜스택을 거쳐서 에러가 발생했는지에 대한 정보. 

#### 2. null을 리턴하는 방법.
- 사용자가 알아서 null에 대한 처리를 해야한다.

자바 8부터는 Optional을 리턴하는 방식을 사용할 수 있게 되었다.


### Optional
- 원소를 최대 1개 가질 수 있는 **불변** 컬렉션.
- Optional 타입을 반환하는 경우 : **결과값이 null일수도 있으며, 클라이언트가 특별하게 처리해야 하는 경우**
  - 성능에 민감할 경우 그냥 null을 반환하거나 예외를 던지는게 나을 수 있다.

#### 주의사항
1. **리턴값으로만 사용할 것**. 
  - 파라미터로 사용하는 경우 의도적으로 null을 넘겨주게 되면 Optional을 쓰는 의미가 없어짐.
  - Map의 키타입으로 사용하게 되면 Map의 "키는 null이 아니다" 라는 조건을 깨트리는 행위

2. Optional을 리턴하는 메서드에서 null을 리턴하지 말자.
    - Primitive 타입용 Optional이 존재한다.
      - 일반 Optional에 Primitive 타입을 넣으면 내부에서 박싱, 언박싱 (Wrapper클래스로 포장하는 행위)이 발생한다.
    
3. Collection, Map, Stream Array, Optional은 Optional로 감싸지 말것.
  - 이 클래스들은 그 자체적으로 내부가 비어있을 수 있다는 것을 나타내는 컨테이너 클래스들이기 때문에 한번 더 감싸는 꼴이 된다.

### Optional Api
- isPresent로 확인해서 처리를 하기보다는 orElse, orElseGet, orElseThrow 같은 메서드로 처리할것. -> 더 짧고 명확하다.
- 스트림에서는 `Stream<Optional<T>>` 형태로 받아서 값이 있는것들만 뽑아서 처리하는 식으로도 많이 사용한다.
   ```java
        s.stream()
          .filter(Optional::isPresent)
          .map(Optional::get);
   ```
- 자바 9 부터는 Optional을 Stream으로 변환해주는 `stream()` 메서드가 추가됨.
  - Optional에 값이 있으면 해당 값을 원소로 담은 스트림으로, 없으면 빈 스트림으로 반환.

- `flatmap()` : Optional 내부에 Optional이 들어있는 경우 내부에서 한번 더 껍질을 까준다.
  

#### orElse vs orElseGet
- orElse의 경우 매개변수로 넘어온 값이 함수 호출이라면 optional에 값이 존재하든 말든 실행을 한다.
- orElseGet의 경우 null일 경우에만 매개변수로 넘어온 Supplier를 실행한다.