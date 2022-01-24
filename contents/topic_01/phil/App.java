package section1;

import java.util.function.*;

public class App {

	public static void main(String[] args) {
		UnaryOperator<String> hi = Greeting::hi;
		System.out.println(hi.apply("Phil"));

		Greeting greeting = new Greeting();
		UnaryOperator<String> hello = greeting::hello;
		System.out.println(greeting.hello("Phil"));

		Supplier<Greeting> newGreeting = Greeting::new;
		newGreeting.get();

		Function<String, Greeting> philGreeting = Greeting::new;
		Greeting phil = philGreeting.apply("Phil");

		System.out.println(phil.getName());
		
	}
}
