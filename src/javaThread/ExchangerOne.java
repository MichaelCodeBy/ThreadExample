package javaThread;
//Класс Exchanger предназначен для обмена данными между потоками. Он является типизированным
// и типизируется типом данных, которыми потоки должны обмениваться.
// Обмен данными производится с помощью единственного метода этого класса exchange()


import java.util.concurrent.Exchanger;


public class ExchangerOne {

    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger<String>();
        new Thread(new PutThread(ex)).start();
        new Thread(new GetThread(ex)).start();
    }
}

class PutThread implements Runnable{

    Exchanger<String> exchanger;
    String message;

    PutThread(Exchanger<String> ex){

        this.exchanger=ex;
        message = "Hello Java!";
    }
    public void run(){

        try{
            message=exchanger.exchange(message);//В классе PutThread отправляет в буфер сообщение "Hello Java!":
            System.out.println("PutThread has received: " + message);
            //Причем в ответ метод exchange возвращает данные, которые отправил в буфер другой поток. То есть
            // происходит обмен данными. Хотя нам необязательно получать данные, мы можем просто их отправить:
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}
class GetThread implements Runnable{

    Exchanger<String> exchanger;
    String message;

    GetThread(Exchanger<String> ex){

        this.exchanger=ex;
        message = "Hello World!";
    }
    public void run(){

        try{
            message=exchanger.exchange(message);//Логика класса GetThread аналогична - также отправляется сообщение.
            System.out.println("GetThread has received: " + message);
        }
        catch(InterruptedException ex){
            System.out.println(ex.getMessage());
        }
    }
}