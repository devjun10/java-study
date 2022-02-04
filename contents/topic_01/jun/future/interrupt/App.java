package future.interrupt;

public class App {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("Exit");
                    return;
                }
            }
        });
        thread.start();
        System.out.println("Hello: " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        thread.interrupt();
    }
}
