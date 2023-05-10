package treadsAlishev;


import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerTwo {


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumer2 pc = new ProducerConsumer2();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}

class ProducerConsumer2 {

    private Queue<Integer> queue = new LinkedList<>();
    private final int LIMIT = 10;//limits of elements in queue
    private Object lock = new Object();// object of synchronization

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (queue.size() == LIMIT) {
                    lock.wait();
                }

                queue.offer(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (queue.size() == 0) {
                    lock.wait();
                }
                int value = queue.poll();
                System.out.println(value);
                System.out.println("queue size is "+ queue.size());
                lock.notify();//insert last in line
            }
            Thread.sleep(1000);
        }
    }
}
