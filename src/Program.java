//Распространенный способ завершения потока представляет опрос логической переменной. И если она равна,
// например, false, то поток завершает бесконечный цикл и заканчивает свое выполнение.

class MyThread implements Runnable {

    private boolean isActive;//Переменная isActive указывает на активность потока.
    // С помощью метода disable() мы можем сбросить состояние этой переменной.

    void disable() {
        isActive = false;
    }

    MyThread() {
        isActive = true;
    }

    public void run() {

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter = 1; // счетчик циклов
        while (isActive) {
            System.out.println("Loop " + counter++);
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        System.out.println("Main thread started...");
        MyThread myThread = new MyThread();
        new Thread(myThread, "MyThread").start();

        try {
            Thread.sleep(1100);

            myThread.disable();

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted");
        }
        System.out.println("Main thread finished...");
    }
}