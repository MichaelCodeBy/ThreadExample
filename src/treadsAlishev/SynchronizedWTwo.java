package treadsAlishev;

public class SynchronizedWTwo {

    private int counter;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedW synchronizedW= new SynchronizedW();
        synchronizedW.doWork();

    }
    public void increment(){
        synchronized (this) {
        counter++;
        }
    }
    public void doWork() throws InterruptedException {
        Thread thread0 =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10000; i++)
                    increment();
            }
        });
        Thread thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10000; i++)
                    increment();
            }
        });

        thread0.start();
        thread2.start();

        thread0.join();
        thread2.join();

        System.out.println(counter);
    }
}
