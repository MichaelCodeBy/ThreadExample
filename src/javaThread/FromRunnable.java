package javaThread;
//Другой способ определения потока представляет реализация интерфейса Runnable. Этот интерфейс имеет один метод run
//public class FromRunnable

class FromRunnable implements Runnable {


    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        try{
            Thread.sleep(500);//в методе run определяется простейший код, который усыпляет поток на 500 миллисекунд.
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}

class ProgramOne {

    public static void main(String[] args) {

            System.out.println("Main thread started...");
            //Runnable фактически представляет функциональный интерфейс, который определяет один метод,
        // то объект этого интерфейса мы можем представить в виде лямбда-выражения:
            Runnable r = ()->{
                System.out.printf("%s started... \n", Thread.currentThread().getName());
                try{
                    Thread.sleep(500);
                }
                catch(InterruptedException e){
                    System.out.println("Thread has been interrupted");
                }
                System.out.printf("%s finished... \n", Thread.currentThread().getName());
            };
            Thread myThread = new Thread(r,"MyThread");
            myThread.start();
            System.out.println("Main thread finished...");
        }
    }
