package javaThread;


    class JThread extends Thread {

        JThread(String name){
            super(name);
        }
//в JThread переопределяется метод run(), код которого собственно и будет представлять весь тот код, который выполняется в потоке.
        public void run(){

            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
            System.out.printf("%s finished... \n", Thread.currentThread().getName());
        }
    }

    public class Program {

        public static void main(String[] args) {

            System.out.println("Main thread started...");
            JThread t= new JThread("JThread ");
            t.start();
            try{
                t.join();//метод join(). В этом случае текущий поток будет ожидать завершения потока, для которого вызван метод join:
            }
            catch(InterruptedException e){

                System.out.printf("%s has been interrupted", t.getName());
            }
            System.out.println("Main thread finished...");
        }
    }

