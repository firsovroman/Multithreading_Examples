package a_fundamental.e_yield;


/*
(c yield могут вывести текст в такой последовательности)
Thread-0
Thread-1
Thread-1
Thread-0

(без yield потоки по очереди выведут текст)
Thread-0
Thread-0
Thread-1
Thread-1

Использование yield() редко необходимо , и, следовательно, его следует избегать
 */

public class ThreadYieldExample {
    public static void main(String[] args) {

        Runnable r = () -> {
            int counter = 0;
            while (counter < 2) {
                System.out.println(Thread.currentThread().getName());
                counter++;
                Thread.yield();
            }
        };

        new Thread(r).start();
        new Thread(r).start();
    }
}
