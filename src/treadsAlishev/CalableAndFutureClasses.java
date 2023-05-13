package treadsAlishev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// для возврата значений из потоков и выбрасывания исключений из потоков
public class CalableAndFutureClasses {
    private static int result;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable(){
            @Override
            public void run() {

                System.out.println("Starting");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Finished");
                result=5;
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.DAYS);
        System.out.println(result);
    }
    public static int calculate(){

        return 0;
    }
}
