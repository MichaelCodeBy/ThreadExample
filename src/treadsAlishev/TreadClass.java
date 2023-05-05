package treadsAlishev;

public class TreadClass {
    public static void main(String[] args) throws InterruptedException { //главный поток main
        Thread.sleep(300);
       /* MyThread myThread = new MyThread();// build new object
        myThread.start(); // метод start создает новый поток и выполняет то что написано в методе run

        MyThread myThread2 = new MyThread();// build second object
        myThread2.start();// start second tread
        System.out.println("Hello world");
   */
        Thread thread = new Thread(new Runner());
        thread.start();
        System.out.println("Hello from main thread");
    }
}
class Runner implements Runnable {//реализуем интерфейс раннабл
    @Override
    public void run(){
        for (int i=1; i<10; i++){
            try {
                Thread.sleep(100); // sleep
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from my Thread"+i);
        }
    }
}
class MyThread extends Thread{
    public void run(){ //переопределили метод ран в этом классе
        for (int i=1; i<100; i++){
            try {
                Thread.sleep(100); // sleep
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello from my Thread"+i);
        }
    }
}
