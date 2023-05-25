package javaThread;

public class WaitAndNotify {

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
    public synchronized void get() {//Оба метода Store - put и get являются синхронизированными.
        while (product<1) {//Для отслеживания наличия товаров в классе Store проверяем значение переменной product
            try {
                wait();//Если товар отсутсвует, вызывается метод wait(). Этот метод освобождает монитор объекта Store
                // и блокирует выполнение метода get, пока для этого же монитора не будет вызван метод notify().
            }
            catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("Покупатель купил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
    }
    //Когда в методе put() добавляется товар и вызывается notify(), то метод get() получает монитор
    // и выходит из конструкции while (product<1), так как товар добавлен. Затем имитируется получение
    // покупателем товара. Для этого выводится сообщение, и уменьшается значение product: product--.
    // И в конце вызов метода notify() дает сигнал методу put() продолжить работу.
    public synchronized void put() {
        //В методе put() работает похожая логика, только теперь метод put() должен срабатывать,
        // если в магазине не более трех товаров. Поэтому в цикле проверяется наличие товара,
        // и если товар уже есть, то освобождаем монитор с помощью wait()
        // и ждем вызова notify() в методе get().
        while (product>=5) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("Производитель добавил 1 товар");
        System.out.println("Товаров на складе: " + product);
        notify();
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
            //Производитель в методе run() добавляет в объект Store с помощью его метода put() 6 товаров.
        }
    }
}
// Класс Потребитель
class Consumer implements Runnable{

    Store store;
    Consumer(Store store)
    {
        this.store=store;
    }
    public void run(){
        for (int i = 1; i < 6; i++) {
            store.get();

            //Потребитель в методе run() в цикле обращается к методу get объекта Store для получения этих товаров.
        }
    }
}

/*
Таким образом, с помощью wait() в методе get() мы ожидаем, когда производитель добавит новый продукт.
 А после добавления вызываем notify(), как бы говоря, что на складе освободилось одно место,
  и можно еще добавлять.
 А в методе put() с помощью wait() мы ожидаем освобождения места на складе. После того, как место
 освободится, добавляем товар и через notify() уведомляем покупателя о том, что он может забирать товар.
 */