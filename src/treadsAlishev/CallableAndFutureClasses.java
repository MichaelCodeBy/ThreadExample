package treadsAlishev;

import java.util.Random;
import java.util.concurrent.*;

// для возврата значений из потоков и выбрасывания исключений из потоков
public class CallableAndFutureClasses {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        System.out.println("Starting");

        Future<Integer> future = executorService.submit(() -> {

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished");
            Random random = new Random();
            int randomValue = random.nextInt();
            if (randomValue < 5)
                throw new Exception("Something bad happened");
            return random.nextInt(10);

        });

        executorService.shutdown();
        try {
            int result = future.get(); //get ждет окончания выполнения потока
            System.out.println(result);

        } catch (InterruptedException e) {
            e.printStackTrace();

        } catch (ExecutionException e) {
            Throwable ex = e.getCause();
            System.out.println(ex.getMessage());
        }
    }
}