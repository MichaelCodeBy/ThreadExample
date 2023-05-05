package treadsAlishev;

public class TreadClass {
    public static void main(String[] args) { //главный поток main
        MyThread myThread = new MyThread();// build new object
        myThread.start(); // метод start создает новый поток и выполняет то что написано в методе run
        System.out.println("Hello world");
    }
}
class MyThread extends Thread{
    public void run(){ //переопределили метод ран в этом классе
        for (int i=1; i<48; i++){
            System.out.println("Hello from my Thread");
        }
    }
}
