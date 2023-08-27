package b_primitives.f_lock_support;

import java.util.concurrent.locks.LockSupport;

/**
 * Реализация холостого цикла.
 * Создается поток, который пытается выполнить LockSupport.park(), что заблокирует его. Однако основной поток ждет 2 секунды,
 * прежде чем вызовет LockSupport.unpark(thread), который разблокирует заблокированный поток.
 *
 *
 * Thread.sleep() освобождает вычислительные мощности, но он негибкий и дорогой;
 * LockSupport.parkNanos() гибче и дешевле, но и он не бесплатен;
 * Thread.onSpinWait() подходит для коротких задержек (например, в примитивах многопоточности) и довольно жаден;
 */


public class LockSupportExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread is going to be blocked");
            LockSupport.park(); // Поток будет заблокирован здесь
            System.out.println("Thread is unblocked");
        });

        thread.start();

        try {
            Thread.sleep(2000); // Дадим потоку некоторое время на блокировку
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(thread); // Разблокируем поток
    }
}

