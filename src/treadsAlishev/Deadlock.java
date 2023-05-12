package treadsAlishev;


public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        Runner2 runner2= new Runner2();

        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        runner2.firstThread();
                    }
                }
        );
        Thread thread2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        runner2.secondThread();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        runner2.finished();
    }
}

class Runner2 {

    public void firstThread() {

    }

    public void secondThread() {

    }

    public void finished() {
        //System.out.println(counter);
    }
}
class Account{
    private  int balance  =1000;
    public void deposit(int amount){
        balance+= amount;
    }

}