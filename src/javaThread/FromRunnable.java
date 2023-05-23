package javaThread;
//Другой способ определения потока представляет реализация интерфейса Runnable. Этот интерфейс имеет один метод run
//public class FromRunnable

//class FromRunnable implements Runnable { //реализация интерфейса ранабл
//
//
//    public void run() {            // интерфейс имеет один метод run
//        //System.out.println("thread work");
//        System.out.printf("%s started... \n", Thread.currentThread().getName());
//
//        try {
//            Thread.sleep(4500);//в методе run определяется простейший код, который усыпляет поток на 500 миллисекунд.
//        } catch (InterruptedException e) {
//            System.out.println("Thread has been interrupted");
//        }
//        System.out.printf("%s finished... \n", Thread.currentThread().getName());
//    }
//}

     class ProgramOne {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Main thread started...");
        //Runnable фактически представляет функциональный интерфейс, который определяет один метод,
        // то объект этого интерфейса мы можем представить в виде лямбда-выражения:
        Runnable r = () -> {
            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try {
              System.out.println("**** thread work");
                Thread.sleep(1500);

                System.out.println(" --- Thread work ");
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
            System.out.printf("%s finished... \n", Thread.currentThread().getName());
        };
        Thread myThread = new Thread(r, "MyThread");
        myThread.start();
        myThread.join();// добавил ожидание завершения этого потока потоком мэйн
        System.out.println("Main thread finished...");
    }
}
