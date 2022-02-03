package model;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadB extends Thread {

    // 해당 메서드 안에서 모니터링 락을 얻는다.
    public static void main(String[] args) throws Exception {
        ThreadA threadA = new ThreadA();
        threadA.start();

        synchronized (threadA) {
            try {
                System.out.println("b가 완료될 때까지 기다린다.");
                threadA.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Total= " + threadA.getTotal());
        }
    }
}
