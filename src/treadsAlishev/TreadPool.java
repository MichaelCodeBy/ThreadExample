package treadsAlishev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TreadPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3); //пулл потоков
        for (int i = 0; i < 7; i++)
            executorService.submit(new Work(i)); //передали 5 заданий

            executorService.shutdown();
            System.out.println("All tasks submitted");

            executorService.awaitTermination(1, TimeUnit.DAYS);


    }
}

class Work implements Runnable {
    private int id;

    public Work(int id) {

        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work" + id + " was completed");
    }
}