package javaThread;

class StopThread implements Runnable {

    private boolean isActive;

    void disable(){
        isActive=false;
    }

    StopThread(){
        isActive = true;
    }

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(isActive){ //Переменная isActive указывает на активность потока.
            System.out.println("Loop " + counter++);
            try{
                Thread.sleep(400);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
    public static void main(String[] args) {

        System.out.println("Main thread started...");
        StopThread myThread = new StopThread();
        new Thread(myThread,"MyThread").start();

        try{
            Thread.sleep(1100);

            myThread.disable();//С помощью метода disable() можем сбросить состояние этой переменной.

            Thread.sleep(1000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}
