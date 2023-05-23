package javaThread;

class StopThread implements Runnable {

    private boolean isActive;

    void disable(){
        isActive=false;
    }

    StopThread(){
        isActive = true;//переменная указывает на активность потока,метод disable может сбросить состояние переменной
    }

    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(isActive){ //Переменная isActive указывает на активность потока.
            System.out.println("Loop " + counter++);
            try{
                Thread.sleep(300);
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
        new Thread(myThread,"MyThread").start();//запуск потока , в теле которого цикл со сном 0.3 секунды

        try{
            Thread.sleep(1500);//через полторы секунды этот поток закроется методом ниже

            myThread.disable();//С помощью метода disable() сбросываем состояние этой переменной.

            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}
