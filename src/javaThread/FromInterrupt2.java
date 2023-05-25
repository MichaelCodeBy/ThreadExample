package javaThread;

import java.sql.SQLOutput;

public class FromInterrupt2 extends Thread {

    FromInterrupt2(String name){
        super(name);
    }
    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!isInterrupted()){//метод isInterrupted пока возвращает false цикл работает

            System.out.println("Loop " + counter++);
            System.out.println(isInterrupted());
            try {
                Thread.sleep(150);//
            } catch (InterruptedException e) {
                System.out.println(getName()+" has been interrupted");// при обработке исключения в блоке кетч статус потока сбрасывается  и возвратит false
                System.out.println(isInterrupted());//false
                interrupt();

            }
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
class Program4 {

    public static void main(String[] args) {

        System.out.println("Main thread started...");
        FromInterrupt2 t = new FromInterrupt2("FromInterrupt2");
        t.start();
        try{
            Thread.sleep(150);

            t.interrupt(); // после вызова этого метода isInterrupt вернет true и поток выйдет из цикла
            System.out.println(t.isInterrupted());
            Thread.sleep(150);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}