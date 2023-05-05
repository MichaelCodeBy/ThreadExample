package treadsAlishev;
// обозначенная ключевым словом volatile не кешируется (сохраняется) в память ядра а всякий раз читается из общей памяти
import java.util.Scanner;

public class VolatileW {
    public static void main(String[] args) {

        MyThread1 myThread = new MyThread1();
        myThread.start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread.shutdown();


    }
}

class MyThread1 extends Thread {
    private volatile boolean running = true;// volatile переменная может быть изменена, не кешировать
//volatile убирает когерентность кешей
    public void run() {
        while (running) {
            System.out.println("Hello!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutdown() {
        this.running = false;
    }
}