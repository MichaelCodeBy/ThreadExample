package treadsAlishev;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockWithLock {

    public static void main(String[] args) throws InterruptedException {
        Runner3 runner3 = new Runner3();

        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        runner3.firstThread();
                    }
                }
        );
        Thread thread2 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        runner3.secondThread();
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        runner3.finished();
    }
}

class Runner3 {
    private Account2 account21 = new Account2();
    private Account2 account22 = new Account2();
    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    private void takeLocks(Lock lock1, Lock lock2) throws InterruptedException {
        boolean firstLockTaken = false;
        boolean secondLockTaken = false;
        while (true) {
            try {
                firstLockTaken = lock1.tryLock();
                secondLockTaken = lock2.tryLock();
            } finally {
                if (firstLockTaken && secondLockTaken) {
                    return;
                }
                if (firstLockTaken) {
                    lock1.unlock();
                }
                if (secondLockTaken) {
                    lock2.unlock();
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void firstThread() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            try {
                takeLocks(lock1, lock2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Account2.transfer(account21, account22, random.nextInt(100));
            lock1.unlock();
            lock2.unlock();
        }
    }


    public void secondThread() {
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            try {
                takeLocks(lock2, lock1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Account2.transfer(account22, account21, random.nextInt(100));
            lock2.unlock();
            lock1.unlock();
        }
    }


    public void finished() {
        System.out.println(account21.getBalance());
        System.out.println(account22.getBalance());
        System.out.println("total balance " + (account21.getBalance() + account22.getBalance()));
    }
}

class Account2 {
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
