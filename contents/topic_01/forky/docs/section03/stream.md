### Stream

- 데이터를 직접 저장하는 저장소는 아니며, 기존 데이터를 변경하지 않는다(Functional in Nature).
- 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
- 데이터의 개수가 무제한일 수도 있고, ShortCirciut 메서드를 통해 일정 개수만 골라올 수 있다.
- 병렬 처리가 가능하다. 정말 방대한 양의 데이터가 아닐경우, 대부분 스트림이 더 성능이 좋다.

### Stream Pipeline
- 스트림을 처리하는 오퍼레이션들의 모음(컨베이어 벨트에 데이터를 흘려보내는 예시를 생각해보자.)

#### 중간 연산(중개 오퍼레이션)
- lazy하다. 즉, 최종 연산이 실행되기 전까지는 실행하지 않는다.
- Stream을 리턴한다.
- Stateless/Stateful 오퍼레이션으로 구분할 수 있다.


#### 최종 연산(종료 오퍼레이션)
- Stream을 리턴하지 않는다.



Stream API

- Predicate.not : Predicate을 인자로 받아서 결과를 반전시키는 메서드. 메서드 참조를 사용할때 더 유용하다.
```java
    list.stream()
        .filter(Predicate.not(OnlineClass::isClosed);
```

- flatMap : 각각의 컬렉션의 요소들을 합쳐서 하나의 스트림으로 반환한다.
- limit(int size) : 가져올 요소의 최대 개수를 정한다.
- 