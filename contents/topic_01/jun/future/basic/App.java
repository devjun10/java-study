package future.basic;

public class App {
    public static void main(String[] args) throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("Main: "+Thread.currentThread().getName());
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
