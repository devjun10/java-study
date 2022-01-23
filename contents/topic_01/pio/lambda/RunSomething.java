package java8to11.lambda;

@FunctionalInterface
public interface RunSomething {

    void oneAbstractMethod(); //static, default메서드가 있어도, 추상메서드가 하나만 존재하면 함수형 인터페이스다.

    static void printAbc() {
        System.out.println("abc");
    }

    default void printAbcd() {
        System.out.println("abcd");
    }
}
