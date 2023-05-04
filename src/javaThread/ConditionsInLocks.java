package javaThread;
/*
методы интерфейса Condition:

await: поток ожидает, пока не будет выполнено некоторое условие и пока другой поток не вызовет методы signal/signalAll.
Во многом аналогичен методу wait класса Object

signal: сигнализирует, что поток, у которого ранее был вызван метод await(), может продолжить работу.
 Применение аналогично использованию методу notify класса Object

signalAll: сигнализирует всем потокам, у которых ранее был вызван метод await(), что они могут продолжить работу.
Аналогичен методу notifyAll() класса Object
 */

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ConditionsInLocks {

    public static void main(String[] args) {

        Store2 store2 = new Store2();
        ProducerN producerN = new ProducerN(store2);
        ConsumerN consumerN = new ConsumerN(store2);
        new Thread(producerN).start();
        new Thread(consumerN).start();
    }
}

// Класс Магазин, хранящий произведенные товары
class Store2 {
    private int product = 0;
    ReentrantLock locker2;
    Condition condition;

    Store2() {
        locker2 = new ReentrantLock(); // создаем блокировку
        condition = locker2.newCondition(); // получаем условие, связанное с блокировкой
    }

    public void get() {

        locker2.lock();
        try {
            // пока нет доступных товаров на складе, ожидаем
            while (product < 1)
                condition.await();

            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker2.unlock();
        }
    }

    public void put() {

        locker2.lock();
        try {
            // пока на складе 3 товара, ждем освобождения места
            while (product >= 3)
                condition.await();

            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker2.unlock();
        }
    }
}

// класс Производитель
class ProducerN implements Runnable {

    Store2 store;

    ProducerN(Store2 store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }
}

// Класс Потребитель
class ConsumerN implements Runnable {

    Store2 store;

    ConsumerN(Store2 store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }
}