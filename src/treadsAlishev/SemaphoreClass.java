package treadsAlishev;

import java.util.concurrent.Semaphore;

public class SemaphoreClass {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(3);//сколько потоков одновременно могут работать с ресурсом
        try {
            semaphore.acquire();
            semaphore.acquire();
            semaphore.acquire();
            System.out.println("all permits are acquired");// все разрешения использованы
            semaphore.acquire();//тут поток маин остановится
            System.out.println("cant reach here.....");// это сообщение никогда не выведется на экран
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        semaphore.release();
        System.out.println(semaphore.availablePermits());
    }
}
