package section1;

public class Foo {
	public static void main(String[] args) {

		RunSomething anonymousFunction = new RunSomething() {
			@Override
			public void doIt() {
				System.out.println("Hello");
			}
		};
		// 기존에 사용하던 위의 익명함수와 아래 람다 함수는 동일함.
		RunSomething lambdaFunction = () -> System.out.println("Hello");
		anonymousFunction.doIt();
		lambdaFunction.doIt();
	}
}
