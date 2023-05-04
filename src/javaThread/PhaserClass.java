package javaThread;
/*
Класс Phaser позволяет синхронизировать потоки, представляющие отдельную фазу или
стадию выполнения общего действия. Phaser определяет объект синхронизации, который
ждет, пока не завершится определенная фаза. Затем Phaser переходит к следующей стадии
 или фазе и снова ожидает ее завершения.
 Для создания объекта Phaser используется один из конструкторов:

1 Phaser()
2 Phaser(int parties)
3 Phaser(Phaser parent)
4 Phaser(Phaser parent, int parties)

Параметр parties указывает на количество участников (грубо говоря, потоков), которые должны выполнять
все фазы действия. Первый конструктор создает объект Phaser без каких-либо участников.
Второй конструктор регистрирует передаваемое в конструктор количество участников.
Третий и четвертый конструкторы также устанавливают родительский объект Phaser.

 Основные методы класса Phaser:

int register(): регистрирует участника, который выполняет фазы, и возвращает номер текущей фазы - обычно фаза 0

int arrive(): сообщает, что участник завершил фазу, и возвращает номер текущей фазы

int arriveAndAwaitAdvance(): аналогичен методу arrive, только при этом заставляет phaser ожидать завершения фазы
всеми остальными участниками

int arriveAndDeregister(): сообщает о завершении всех фаз участником и снимает его с регистрации.
Возвращает номер текущей фазы или отрицательное число, если синхронизатор Phaser завершил свою работу

int getPhase(): возвращает номер текущей фазы

 При работае с классом Phaser обычно сначала создается его объект. Далее нам надо зарегистрировать
 всех участников. Для регистрации для каждоого участника вызывается метод register(), либо можно
 обойтись и без этого метода, передав нужное количество участников в конструктор Phaser.

Затем каждый участник выполняет некоторый набор действий, составляющих фазу. А синхронизатор Phaser ждет,
 пока все участники не завершат выполнение фазы. Чтобы сообщить синхронизатору, что фаза завершена,
  участник должен вызвать метод arrive() или arriveAndAwaitAdvance(). После этого синхронизатор переходит
   к следующей фазе.
 */


import java.util.concurrent.Phaser;
public class PhaserClass {

    public static void main(String[] args) {

        Phaser phaser = new Phaser(1);
        new Thread(new PhaseThread(phaser, "PhaseThread 1")).start();
        new Thread(new PhaseThread(phaser, "PhaseThread 2")).start();

        // ждем завершения фазы 0
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");
        // ждем завершения фазы 1
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        // ждем завершения фазы 2
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("Фаза " + phase + " завершена");

        phaser.arriveAndDeregister();
    }
}

class PhaseThread implements Runnable{

    Phaser phaser;
    String name;

    PhaseThread(Phaser p, String n){

        this.phaser=p;
        this.name=n;
        phaser.register();
    }
    public void run(){

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что первая фаза достигнута
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndAwaitAdvance(); // сообщаем, что вторая фаза достигнута
        try{
            Thread.sleep(200);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println(name + " выполняет фазу " + phaser.getPhase());
        phaser.arriveAndDeregister(); // сообщаем о завершении фаз и удаляем с регистрации объекты
    }
}