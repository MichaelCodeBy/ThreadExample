package javaThread;
//Если основная функциональность заключена в классе, который реализует интерфейс Runnable,
// то там можно проверять статус потока с помощью метода Thread.currentThread().isInterrupted():

import static java.lang.Character.getName;

class StopThread2 implements Runnable {

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        try{
            while(!isInterrupted()){
                System.out.println("Loop " + counter++);
                Thread.sleep(100);
            }
        }
        catch(InterruptedException e){
            System.out.println(getName() + " has been interrupted");
        }

        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }

    private boolean getName() {
        return false;
    }

    private void interrupt() {
    }

    private boolean isInterrupted() {
        return false;
    }
}
 class Program3 {

    public static void main(String[] args) {

        System.out.println("Main thread started...");
        StopThread2 stopThread = new StopThread2();
        Thread t = new Thread(stopThread,"StopThread2");
        t.start();
        try{
            Thread.sleep(150);
            t.interrupt();

            Thread.sleep(150);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}