package 쓰레드.basic;


public class Main {
    public static void main(String[] args) throws Exception {
        JoinThread thread = new JoinThread();
        ThreadA threadA = new ThreadA();
        threadA.start();
        System.out.println("Start");
        thread.join();
        System.out.println("End");
    }
}
