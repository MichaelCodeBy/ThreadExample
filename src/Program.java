class JThread extends Thread {

    JThread(String name){
        super(name);
    }
    public void run(){

        System.out.printf("%s started... \n", Thread.currentThread().getName());
        int counter=1; // счетчик циклов
        while(!isInterrupted()){
            // В классе, который унаследован от Thread, мы можем получить статус текущего потока
            // с помощью метода isInterrupted(). И пока этот метод возвращает false, мы можем выполнять цикл.
            // А после того, как будет вызван метод interrupt, isInterrupted() возвратит true,
            // и соответственно произойдет выход из цикла.

            System.out.println("Loop " + counter++);
        }
        System.out.printf("%s finished... \n", Thread.currentThread().getName());
    }
}
public class Program {

    public static void main(String[] args) {

        System.out.println("Main thread started...");
        JThread t = new JThread("JThread");
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