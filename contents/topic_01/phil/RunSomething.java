package section1;


/*
함수형 인터페이스는 추상메서드가 하나만 있는 인터페이스.
@FunctionalInterface 는 추상메서드가 하나만 있도록 강제하는 것.
 */
@FunctionalInterface
public interface RunSomething {
	// abstract 생략가능
	void doIt();

	// void doItAgain(); 추상메서드가 하나 초과하면 에러 발생

	// 추상메서드가 아닌 static 메서드 (앞에 public 생략 가능) 및 Default 메서드는 가능.
	static void printName() {
		System.out.println("Phil");
	}

	default void printAge() {
		System.out.println("Age");
	}
}
