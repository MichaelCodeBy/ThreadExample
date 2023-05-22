package javaThread;

public class ClassThread {
    public static void main(String[] args) {

        Thread t = Thread.currentThread(); // получаем главный поток

        System.out.println(t.getName()); // name of thread

        t.setPriority(6);// set priority)
        System.out.println( t.isAlive());//возвращает true, если поток активен
        System.out.println( t.isInterrupted());//возвращает true, если поток был прерван


        System.out.println(t); // main
    }


}
  /*    getName(): возвращает имя потока

        setName(String name): устанавливает имя потока

        getPriority(): возвращает приоритет потока

        setPriority(int priority): устанавливает приоритет потока. Приоритет является одним из ключевых факторов для выбора системой потока из кучи потоков для выполнения. В этот метод в качестве параметра передается числовое значение приоритета - от 1 до 10. По умолчанию главному потоку выставляется средний приоритет - 5.

        isAlive(): возвращает true, если поток активен

        isInterrupted(): возвращает true, если поток был прерван

        join(): ожидает завершение потока

        run(): определяет точку входа в поток

        sleep(): приостанавливает поток на заданное количество миллисекунд

        start(): запускает поток, вызывая его метод run()

*/