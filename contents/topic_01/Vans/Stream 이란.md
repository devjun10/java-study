자바 기초 스터디_22.02.06(토)
***
# Stream
- 어떠한 연속된 데이터를 처리하는 오퍼레이션들의 모음
- 스트림으로 처리하는 데이터는 오직 한번만 처리한다.
  (컨베이어 벨트 위(Stream)에서 데이터 소스가 슥 한번 지나가면서 처리되는것)
- 실시간으로 계속해서 Stream으로 들어오는 데이터를 Stream으로 무제한으로 받아 처리할수도 있다.
  (이럴경우 Short Circuit 메소드로 제한할 수 있다.)
- Stream 자체는 데이터가 아니고 데이터 원본을 변경하지 않는다.
  (컬렉션은 데이터를 가지고 있는것, Stream은 데이터를 소스로 쓰는것)
- Stream이 제공하는 Api는 크게 중개 오퍼레이터, 터미널(종료) 오퍼레이터로 구분할 수 있다.
- 손쉽게 병렬 처리 할 수 있다.


~~~java

public class App {
	
    public static void main(String[] args){
    	List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println);
        /*
         * a
         * b
         * c
         * d
         */
    
    }

}

~~~

> toUpperCase된 새로운 Stream을 만든것이지 데이터 원본을 변경한것이 아니다. 원본을 출력해보면 그대로 소문자로 출력됨.


# 중개 오퍼레이션
- Stream을 리턴한다. <-> Stream을 리턴하지 않는다.(종료 오퍼레이션)
- 중개 오퍼레이션은 근본적으로 Lazy하다.



~~~java

public class App {
	
    public static void main(String[] args){
    	List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        
        names.stream().map((s) -> {
        	System.out.println(s);
            return s.toUpperCase();
        });
        
    
    }

}

~~~

> map()은 중개형 오퍼레이션중 하나인데, 위 같이 중개형 오퍼레이션만 구현할 경우 {함수}는 실행되지 않는다.(무의미)
Stream 파이프라인을 정의한것일 뿐, 터미널(종료) 오퍼레이션이 최소 1개 이상 와야지 실행할 수 있다.(Lazy하다의 의미중 하나)
🔻종료형 오퍼레이션을 추가할 경우

~~~java

public class App {
	
    public static void main(String[] args){
    	List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        
        List<String> collect = names.stream().map((s) -> {
        	System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        // 종료형 오퍼레이션중 하나인 collect를 써서 List를 받음

		collect.forEach(System.out::println);
        // 실제 collect에 대문자로 바뀐것을 출력하기 위함
        
        /*
         * A
         * B
         * C
         * D
         */
    
    }

}

~~~

# 병렬처리

~~~java

public class App {
	
    public static void main(String[] args){
    	List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        
        for (String names : names){
        	if(names.startsWith("a")){
            	System.out.println(names.toUpperCase());
            }
        }
        // 일반적인 Loop 형태
    
    }

}

~~~

> 위와 같이 for-Loop 형태로 순회하게되면 병렬적으로 처리하기가 어렵다.
🔻 Stream의 경우 parallerlStream()을 써서 병렬적으로 처리할 수 있다.

~~~java

public class App {
	
    public static void main(String[] args){
    	List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");
        
        List<String> collect = names.parallelstream().map(String::toUpperCase)
        	.collect(Collectors.toList());
        collect.forEach(System.out::println);
        
		/*
         * A
         * B
         * C
         * D
         */
    
    }

}

~~~

> 위의 경우 ForkJoinFool을 써서 병렬적으로 데이터를 처리하게 만든다.
stream()을 쓰게되면 단일 스레드를 쓰게된다.
❗️하지만 무조건 이방법이 빠른건아니다. 데이터가 방대하거나 시간복잡도가 높은 연산작업이 필요할때 유리할 수 있다.