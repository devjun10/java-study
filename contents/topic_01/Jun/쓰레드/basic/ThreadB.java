package 쓰레드.basic;

public class ThreadB extends Thread {

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
