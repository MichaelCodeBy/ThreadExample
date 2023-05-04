package javaThread;

public class ProgramSynchronized {


    public static void main(String[] args) {

        CommonResource commonResource = new CommonResource();
        for (int i = 1; i < 6; i++) {

            Thread t = new Thread(new CountThread(commonResource));
            t.setName("Thread " + i);
            t.start();
        }
    }
}

class CommonResource {

    int x = 0;
}

class CountThread implements Runnable {

    CommonResource res;

    CountThread(CommonResource res) {
        this.res = res;
    }

    public void run() {
        //Одним из способов синхронизации является использование ключевого слова synchronized. Этот оператор
        // предваряет блок кода или метод, который подлежит синхронизации.
        synchronized (res) {
            //При создании синхронизированного блока кода после оператора synchronized идет объект-заглушка:
            // synchronized(res). Причем в качестве объекта может использоваться только объект какого-нибудь класса,
            // но не примитивного типа.
            res.x = 1;
            for (int i = 1; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
//Каждый объект в Java имеет ассоциированный с ним монитор. Монитор представляет своего рода инструмент для
// управления доступа к объекту. Когда выполнение кода доходит до оператора synchronized, монитор объекта res
// блокируется, и на время его блокировки монопольный доступ к блоку кода имеет только один поток, который и
// произвел блокировку. После окончания работы блока кода, монитор объекта res освобождается и становится
// доступным для других потоков.
//
//После освобождения монитора его захватывает другой поток, а все остальные потоки продолжают ожидать его освобождения.