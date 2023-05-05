package treadsAlishev;

public class SynchronizedW {
    private int counter1;

    public static void main(String[] args) {
        SynchronizedW synchronizedW= new SynchronizedW();
        synchronizedW.doWork();

    }
    public void doWork(){
        Thread thread1 =new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i=0; i<10000; i++)
                   counter1++;

            }
        });
        Thread thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10000; i++)
                    counter1++;

            }
        });

        thread1.start();
        System.out.println(counter1);
        thread2.start();

        System.out.println(counter1);
    }
}
