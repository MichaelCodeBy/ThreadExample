package javaThread;



public class ProgramSynchronized2 {


    public static void main(String[] args) {

        CommonResource2 commonResource2 = new CommonResource2();
        for (int i = 1; i < 6; i++) {

            Thread t = new Thread(new CountThread2(commonResource2));
            t.setName("Thread " + i);
            t.start();
        }
    }
}

class CommonResource2 {

    int x;
    synchronized void increment(){
        x=1;
        for (int i = 1; i < 5; i++){
            System.out.printf("%s %d \n", Thread.currentThread().getName(), x);
            x++;
            try{
                Thread.sleep(100);
            }
            catch(InterruptedException e){}
        }
    }
}

class CountThread2 implements Runnable {

    CommonResource2 res;

    CountThread2(CommonResource2 res) {
        this.res = res;
    }

    public void run() {
     res.increment();

                }
            }
//Результат работы в данном случае будет аналогичен примеру с блоком synchronized.
// Здесь опять в дело вступает монитор объекта CommonResource - общего объекта для всех потоков.
// Поэтому синхронизированным объявляется не метод run() в классе CountThread,
// а метод increment класса CommonResource. Когда первый поток начинает выполнение метода increment,
// он захватывает монитор объекта CommonResource.
// А все потоки также продолжают ожидать его освобождения.