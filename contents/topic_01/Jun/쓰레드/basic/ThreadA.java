package 쓰레드.basic;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadA extends Thread {
    private int total;

    public void run() {
        for (int index = 0; index < 5; index++) {
            System.out.println(index + "를 더합니다.");
            plus();
        }
        notify();
        System.out.println(getTotal());
    }

    private void plus() {
        this.total = new AtomicInteger(total).incrementAndGet();
    }

    public int getTotal() {
        return total;
    }
}
