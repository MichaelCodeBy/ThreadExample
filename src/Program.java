
//Класс Phaser позволяет синхронизировать потоки, представляющие отдельную фазу или стадию выполнения общего действия.
// Phaser определяет объект синхронизации, который ждет, пока не завершится определенная фаза.
// Затем Phaser переходит к следующей стадии или фазе и снова ожидает ее завершения.

//Основные методы класса Phaser:
//
//int register(): регистрирует участника, который выполняет фазы, и возвращает номер текущей фазы - обычно фаза 0
//
//int arrive(): сообщает, что участник завершил фазу, и возвращает номер текущей фазы
//
//int arriveAndAwaitAdvance(): аналогичен методу arrive, только при этом заставляет phaser ожидать
// завершения фазы всеми остальными участниками
//
//int arriveAndDeregister(): сообщает о завершении всех фаз участником и снимает его с регистрации.
// Возвращает номер текущей фазы или отрицательное число, если синхронизатор Phaser завершил свою работу
//
//int getPhase(): возвращает номер текущей фазы

import java.util.concurrent.Phaser;

public class Program {

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