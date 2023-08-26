package b_java_util_concurrent.f_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static int sharedResource = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> incrementSharedResource());
        Thread thread2 = new Thread(() -> incrementSharedResource());

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final shared resource value: " + sharedResource);
    }

    /**
     * С помошью замка разграничиваем для потоков, доступ к переменной.
     */
    public static void incrementSharedResource() {
        lock.lock();
        try {
            for (int i = 0; i < 100000; i++) {
                sharedResource++;
            }
        } finally {
            lock.unlock();
        }
    }
}

