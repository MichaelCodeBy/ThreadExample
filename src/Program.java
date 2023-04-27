

    class JThread extends Thread {

        JThread(String name){
            super(name);
        }

        public void run(){

            System.out.printf("%s started... \n", Thread.currentThread().getName());
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.out.println("Thread has been interrupted");
            }
            System.out.printf("%s fiished... \n", Thread.currentThread().getName());
        }
    }

    public class Program {

        public static void main(String[] args) {

            System.out.println("Main thread started...");
            JThread t= new JThread("JThread ");
            t.start();
            try{
                t.join();//Метод join() заставляет вызвавший поток (в данном случае Main thread)
                // ожидать завершения вызываемого потока, для которого и применяется метод join (в данном случае JThread).
            }
            catch(InterruptedException e){

                System.out.printf("%s has been interrupted", t.getName());
            }
            System.out.println("Main thread finished...");
        }
    }
