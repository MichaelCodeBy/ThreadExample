package javaThread;
/*
Классы блокировок реализуют интерфейс Lock, который определяет следующие методы:

void lock(): ожидает, пока не будет получена блокировка

void lockInterruptibly() throws InterruptedException: ожидает, пока не будет получена блокировка,
если поток не прерван


boolean tryLock(): пытается получить блокировку, если блокировка получена, то возвращает true.
        Если блокировка не получена, то возвращает false. В отличие от метода lock() не ожидает получения блокировки, если она недоступна

void unlock(): снимает блокировку

Condition newCondition(): возвращает объект Condition, который связан с текущей блокировкой

Организация блокировки в общем случае довольно проста: для получения блокировки вызывается метод lock(),
 а после окончания работы с общими ресурсами вызывается метод unlock(), который снимает блокировку.

Объект Condition позволяет управлять блокировкой.

для работы с блокировками используется класс ReentrantLock из пакета java.util.concurrent.locks.
Данный класс реализует интерфейс Lock.
 */

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantlockBlok {


    public static void main(String[] args) {

        CommonResource3 commonResource3= new CommonResource3();
        ReentrantLock locker = new ReentrantLock(); // создаем заглушку
        for (int i = 1; i < 6; i++){

            Thread t = new Thread(new CountThread3(commonResource3, locker));
            t.setName("Thread "+ i);
            t.start();
        }
    }
}

class CommonResource3{

    int x=0;
}

class CountThread3 implements Runnable{

    CommonResource3 res;
    ReentrantLock locker;
    CountThread3(CommonResource3 res, ReentrantLock lock){
        this.res=res;
        locker = lock;
    }
    public void run(){

        locker.lock(); // устанавливаем блокировку
        try{
            res.x=1;
            for (int i = 1; i < 5; i++){
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock(); // снимаем блокировку
        }
    }
}
/*
В итоге мы получим вывод, аналогичный тому, который был в случае с оператором synchronized:

 */