import java.util.concurrent.Semaphore;
//Семафоры представляют еще одно средство синхронизации для доступа к ресурсу
public class Program {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1); // 1 разрешение
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();
    }
}
class CommonResource{

    int x=0;
}

class CountThread implements Runnable{

    CommonResource res;
    Semaphore sem;
    String name;
    CountThread(CommonResource res, Semaphore sem, String name){
        this.res=res;
        this.sem=sem;
        this.name=name;
    }

    public void run(){

        try{
            System.out.println(name + " ожидает разрешение");
            sem.acquire();//Для получения разрешения у семафора надо вызвать метод acquire()
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.println(this.name + ": " + res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){System.out.println(e.getMessage());}
        System.out.println(name + " освобождает разрешение");
        sem.release();//полученное ранее разрешение надо освободить с помощью метода release()
    }
}
//Итак, здесь есть общий ресурс CommonResource с полем x, которое изменяется каждым потоком.
// Потоки представлены классом CountThread, который получает семафор и выполняет некоторые действия над ресурсом.
// В основном классе программы эти потоки запускаются.