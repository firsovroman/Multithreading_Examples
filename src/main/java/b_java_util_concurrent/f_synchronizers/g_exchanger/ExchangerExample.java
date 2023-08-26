package b_java_util_concurrent.f_synchronizers.g_exchanger;

import java.util.concurrent.Exchanger;

/**
 * Каждый поток отправляет строку методом exchange(),
 * а затем ожидает получение строки от другого потока.
 * Когда оба потока завершают обмен, они могут продолжить выполнение.
 *
 */

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        Runnable task1 = () -> {
            try {
                String data = "Hello from Thread 1";
                System.out.println("Thread 1 is sending: " + data);
                String receivedData = exchanger.exchange(data);
                System.out.println("Thread 1 received: " + receivedData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable task2 = () -> {
            try {
                String data = "Hello from Thread 2";
                System.out.println("Thread 2 is sending: " + data);
                String receivedData = exchanger.exchange(data);
                System.out.println("Thread 2 received: " + receivedData);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

