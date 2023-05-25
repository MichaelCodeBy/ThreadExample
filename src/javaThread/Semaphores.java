package javaThread;

import java.util.concurrent.Semaphore;

public class Semaphores {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(1); // семафор с 1 разрешением
        CommonResource1 res = new CommonResource1();
        new Thread(new CountThread1(res, sem, "CountThread 1")).start();
        new Thread(new CountThread1(res, sem, "CountThread 2")).start();
        new Thread(new CountThread1(res, sem, "CountThread 3")).start();//запускаются потоки и ждут разрешения
    }
}

/*
здесь есть общий ресурс CommonResource с полем x, которое изменяется каждым потоком.
 Потоки представлены классом CountThread1, который получает семафор и выполняет некоторые
 действия над ресурсом. В основном классе программы эти потоки запускаются
 */
class CommonResource1{

    int x=0;
}

class CountThread1 implements Runnable{

    CommonResource1 res;
    Semaphore sem;
    String name;
    CountThread1(CommonResource1 res, Semaphore sem, String name){
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
        sem.release();//После окончания работы с ресурсом полученное ранее разрешение надо освободить с помощью метода release()
    }
}