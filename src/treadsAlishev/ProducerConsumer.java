package treadsAlishev;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//threadSafe
public class ProducerConsumer {

    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //статическая переменная для доступа к ней в статических методах

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer();
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

    private static void produce() throws InterruptedException {
        Random random = new Random();
        while (true) {//бесконечный цикл
            queue.put(random.nextInt(100)); //получаем случайное целое число элементы в кю
        }
    }
        private static void consumer() throws InterruptedException { //потребитель который берет элементы из кю
        Random random= new Random();
        while (true) {
                Thread.sleep(100);

                if (random.nextInt(10) == 5) // условие срабатывает при случайном числе 5
                System.out.println(queue.take()); //метод ожидает если в очереди нет элементов
                System.out.println("queue size is " + queue.size());
            }
        }
    }

