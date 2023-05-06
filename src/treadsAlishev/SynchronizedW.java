package treadsAlishev;

public class SynchronizedW {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedW synchronizedW= new SynchronizedW();// this have a monitor lock
        //каждому обьекту сознанному в жаве присваивается определенная сущность и эта сущность в один момент времени
        // может быть только у одного потока. Только один поток может завладеть монитором одного обьекта.
        // Монитор используется для того чтобы дать понять жаве что в данный момент этот поток взаимодействует с обьектом
        synchronizedW.doWork();

    }
    public synchronized void increment(){// был создан метод и посредством ключевого слова synchronized потоки были
        // синхронизированы. Только один поток выполняет тело этого метода, другие ждут.
        counter++;
    }
    public void doWork() throws InterruptedException {
        Thread thread1 =new Thread(new Runnable() {
            @Override
            public void run() {
               for (int i=0; i<10000; i++)
                   increment();//counter++

            }
        });
        Thread thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<10000; i++)
                    increment();

            }
        });

        thread1.start();//поток мэйн запускает потоки 1 и 2
        thread2.start();

        // join тормозит поток main пока завершится запущенный поток
        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}
