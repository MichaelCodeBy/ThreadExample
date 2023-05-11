package treadsAlishev;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchClass {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(3);//количество итераций через какое защелка откроется

        ExecutorService executorService = Executors.newFixedThreadPool(3);//создаем три потока при помощи тредпулл
        for (int i = 0; i < 3; i++)
            executorService.submit(new Processor(countDownLatch)); //передаем потокам задания- латч в качестве аргумента в конструктор

        executorService.shutdown();// обязательный метод для прекращения новых заданий

        countDownLatch.await();//мэйн ожидает открытия защелки
        System.out.println("Latch has been opened, main thread is proceeding");
    }
}

class Processor implements Runnable {
    private CountDownLatch countDownLatch;

    public Processor(CountDownLatch countDownLatch) {//передаем латч в конструктор
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        countDownLatch.countDown();//декрементирует переменную на 1
    }

}