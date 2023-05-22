package treadsAlishev;


import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        Runner2 runner2 = new Runner2();

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
    private Account2 account21 = new Account2();
    private Account2 account2 = new Account2();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            lock1.lock();
            lock2.lock();
            Account2.transfer(account21, account2, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        }
    }


    public void secondThread() {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {

            lock1.lock();
            lock2.lock();
            Account2.transfer(account2, account21, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        }
    }


    public void finished() {
        System.out.println(account21.getBalance());
        System.out.println(account2.getBalance());
        System.out.println("total balance " + (account21.getBalance() + account2.getBalance()));
    }
}

class Account {
    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account2 acc1, Account2 acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}