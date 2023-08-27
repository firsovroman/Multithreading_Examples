package b_primitives.e_synchronizers.a_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Runnable task = () -> {
            lock.lock(); // Acquire the lock
            try {
                System.out.println("Thread " + Thread.currentThread().getId() + " is in the critical section.");
                Thread.sleep(1000); // Simulating some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(); // Release the lock
                System.out.println("Thread " + Thread.currentThread().getId() + " released the lock.");
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

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
