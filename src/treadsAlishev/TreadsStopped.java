package treadsAlishev;

import java.util.Random;

// прерывание потока из другого потока
public class TreadsStopped {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                for (int i = 0; i < 1_000_000_000; i++) {
                   if( Thread.currentThread().isInterrupted()){
                       System.out.println("Thread was interrupted");
                       break;
                   }
                    Math.sin(random.nextDouble());
                }
            }
        });
        System.out.println("Starting thread");

        thread.start();//запуск потока

        Thread.sleep(1000);
        thread.interrupt();//завершение потока

        thread.join();//ожидание завершения потока в методе мэйн
        // thread.stop();
        System.out.println("Thread has finished");
    }
}
