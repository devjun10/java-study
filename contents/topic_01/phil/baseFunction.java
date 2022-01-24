package section1;

import java.util.function.*;

public class baseFunction {
	public static void main(String[] args) {
		Function<Integer, Integer> plus10 = num -> num + 10;
		System.out.println(plus10.apply(3));
		Function<Integer, Integer> multiply2 = num -> num*2;
		System.out.println(multiply2.apply(1));

		System.out.println(plus10.compose(multiply2).apply(3));
		//A.compose(B) B함수 먼저 계산하고 그 값으로 A함수 계산
		// 3*2 + 10 = 16

		System.out.println(plus10.andThen(multiply2).apply(2));
		// A.andThen(B) A 함수 먼저 계산하고 그 값으로 B함수 계산
		// (2+10) * 2 = 24


		BiFunction<Integer, Integer, Integer> multiply = (numA, numB) -> numA*numB;
		System.out.println(multiply.apply(10, 20));

		Consumer<Integer> printT = number -> System.out.println(number);
		printT.accept(10);

		Supplier<Integer> get10 = () -> 10;
		System.out.println(get10.get());

		Predicate<String> startsWithP = s -> s.startsWith("P");
		System.out.println(startsWithP.test("Phil"));

		UnaryOperator<Integer> multiply5 = num -> num * 5;
		System.out.println(multiply5.apply(4));

		BinaryOperator<String> concatenate = (strA, strB) -> strA+strB;
		System.out.println(concatenate.apply("code", "squad"));

		BiConsumer<Character, Integer> charPlusInt = (Character alpha, Integer num) -> System.out.println((char)(alpha + num));
		charPlusInt.accept('A', 1);
	}
}
