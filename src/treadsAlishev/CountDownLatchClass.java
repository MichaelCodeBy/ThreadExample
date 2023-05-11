package treadsAlishev;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchClass {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(3);//количество итераций чесрез какое защелка откроется

        ExecutorService executorService= Executors.newFixedThreadPool(3);
        for (int i=0; i<3; i++)
            executorService.submit(new Processor(countDownLatch));


    }
}
class Processor implements  Runnable{
    private  CountDownLatch countDownLatch;

    public Processor (CountDownLatch countDownLatch){
        this.countDownLatch=countDownLatch;
    }
    @Override
    public void run(){
        try {
            Thread.sleep(3000);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    countDownLatch.countDown();//декрементирует переменную на 1
    }

}