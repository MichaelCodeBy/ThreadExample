package javaThread;


    class JThread extends Thread { //создаем наследника треда

        JThread(String name){
            super(name);
        }
//в JThread переопределяется метод run(), код которого собственно и будет представлять весь тот код, который выполняется в потоке.
        public void run(){

            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try{
                Thread.sleep(1500); //усыпляем его на полсекунды
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");//если случится чтото
            }
            System.out.printf("%s finished... \n", Thread.currentThread().getName());
        }
    }

    public class Program {

        public static void main(String[] args) {//запускаем поток мэйн

            System.out.println("Main thread started...");
            JThread t= new JThread("JThread ");//создаем обьект класса JThread
            t.start();// запускаем его
            try{
                t.join();//В этом случае поток мэйн будет ожидать завершения потока, для которого вызван метод join:
            }
            catch(InterruptedException e){

                System.out.printf("%s has been interrupted", t.getName());
            }
            System.out.println("Main thread finished...");
        }
    }

