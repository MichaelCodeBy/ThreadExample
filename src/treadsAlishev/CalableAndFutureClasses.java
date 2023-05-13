package treadsAlishev;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// для возврата значений из потоков и выбрасывания исключений из потоков
public class CalableAndFutureClasses {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(1);
        executorService.submit(()->{
            System.out.println("Starting");
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("Finished");
        });
        executorService.shutdown();
    }
    public static int calculate(){

        return 0;
    }
}
