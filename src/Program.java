
//Условие блокировки представлет собой объект интерфейса Condition из пакета java.util.concurrent.locks

//await: поток ожидает, пока не будет выполнено некоторое условие и пока другой поток не вызовет
// методы signal/signalAll. Во многом аналогичен методу wait класса Object
//
//signal: сигнализирует, что поток, у которого ранее был вызван метод await(), может продолжить работу.
// Применение аналогично использованию методу notify класса Object
//
//signalAll: сигнализирует всем потокам, у которых ранее был вызван метод await(), что они могут
// продолжить работу. Аналогичен методу notifyAll() класса Object

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Program {

    public static void main(String[] args) {

        Store store=new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
// Класс Магазин, хранящий произведенные товары
class Store{
    private int product=0;
    ReentrantLock locker;
    Condition condition;

    Store(){
        locker = new ReentrantLock(); // создаем блокировку
        condition = locker.newCondition(); // получаем условие, связанное с блокировкой
    }

    public void get() {

        locker.lock();
        try{
            // пока нет доступных товаров на складе, ожидаем
            while (product<1)
                condition.await();

            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            // сигнализируем
            condition.signalAll();//Важно в конце вызвать метод signal/signalAll, чтобы избежать возможности взаимоблокировки потоков.
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();
        }
    }
    public void put() {

        locker.lock();
        try{
            // пока на складе 3 товара, ждем освобождения места
            while (product>=3)
                condition.await();

            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            // сигнализируем
            condition.signalAll();//Важно в конце вызвать метод signal/signalAll, чтобы избежать возможности взаимоблокировки потоков.
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();
        }
    }
}
// класс Производитель
class Producer implements Runnable{

    Store store;
    Producer(Store store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}
// Класс Потребитель
class Consumer implements Runnable{

    Store store;
    Consumer(Store store){
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}