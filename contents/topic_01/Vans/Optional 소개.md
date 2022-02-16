자바 기초 스터디_22.02.06(토)
***
# Optional
- 오직 값 1개가 들어있을 수도 / 없을 수도 있는 컨테이너
- 리턴값으로만 쓰기를 권장한다.(매개변수 타입, 맵의 키 타입, 인스턴스 필드 타입 X)
  (호출하는 쪽에서 파라미터에 null을 줄수도 있기 때문에 Optional을 쓰는 의미가 사라진다.)
- 프리미티브 타입용 Optional을 따로 있으니 Optional.of(10) 처럼 쓰지 말것(박싱, 언박싱이 이루어져 성능이 저하됨)		   
  (OptionalInt, OptionalLong...)
- Collection, Map, Stream Array, Optional들은 자체적으로 값이 비어있는지 확인이 가능하므로
  Opiontal로 감싸지 말 것.(껍데기를 2번 감싸는 꼴)

~~~java

public class Progress {

	private Duration studyDuration;
    
    private boolean finished;
    
    public Duration getStudyDuration() {
    	return studyDuration;
    }
    
    public void setStudyDuration(Duration studyDuration) {
    	this.studyDuration = stduyDuration;
    }

}

~~~
~~~java

public class OnlineClass {

	public Progress progress;
	
    public Progress getProgress() {
    	return progress; // Null
    }

}

~~~

~~~java

public class App{
	
    public static void main(String[] args){
    	List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(...));
        
        OnlineClass spring_boot = new OnlineClass(...));
        Duration studyDuration = spring_boot.getProgress().getStudyDuration();
        System.out.println(studyDuration);
        
        //NullPointerException 에러 발생
    }

}

~~~

> Optional이 자바 8에서 등장하기 전에는 위와 같은 에러 상황을 해결하기 위해 🔻`if문`을 활용했었다.

~~~java

public class App{
	
    public static void main(String[] args){
    	List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(...));
        
        OnlineClass spring_boot = new OnlineClass(...));
        Progress progress = spring_boot.getProgress();
        if (progress != null) {
        	System.out.println(progress.getStudyDuration());
        }
            
        
    }

}

~~~

> 이렇게 코딩할 경우, null을 체크할 경우를 잊을 수 있어서 좋지 못한 코드다.
이외에도 근본적으로 return progress에서 null을 리턴하는것 자체가 문제다.
이런 경우, 🔻에러를 던지는 방법도 있다.

~~~java

public class OnlineClass {

	public Progress progress;
	
    public Progress getProgress() {
    	if (this.progress == null){
        	throw new IllegalStateException(); // 에러 던지기!
        }
        return progress;
    }

}

~~~

> 에러 던지는것은 클라이언트 쪽 코드에서 불편함이 덜하지만, 에러 처리를 강제하기 때문에
자바가 스택트레이스를 찍어 리소스 비용이 비싸진다.
이렇게 비어있는 값이 전달될수도 있는 경우에는,
🔻Optional로 감싸서 리턴하여 해당 경우에 대한 처리를 강제해준다.
(클라이언트의 코드에게 명시적으로 빈값일 수도 있단걸 알려줄 수 있다.)
(그러므로, Optional을 리턴하는 메소드에서 null을 리턴하지 말자. 클라이언트를 속이게 된다.
null을 리턴할 수 밖에 없다면 Optional.empty()을 써주자.)

~~~java

public class OnlineClass {

	public Progress progress;
	
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress); // ofNullable 이 아닌 of를 하게되면 에러가 발생할수도 있다.
    }

}

~~~