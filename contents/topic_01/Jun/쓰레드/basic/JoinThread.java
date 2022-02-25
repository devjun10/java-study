package 쓰레드.basic;

public class JoinThread extends Thread {

    public void run() {
        for (int index = 0; index < 5; index++) {
            System.out.println("Thread= " + index);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
