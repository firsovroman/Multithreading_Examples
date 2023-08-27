package b_primitives.f_synchronizers.d_coun_dow_latch;

import java.util.concurrent.CountDownLatch;

/**
 *  Каждый поток выполняет свою "работу" и сигнализирует о завершении вызовом countDown().
 *
 * Затем главный поток вызывает метод await(), который блокирует его до тех пор, пока значение счетчика не станет равным нулю.
 * Это означает, что все потоки завершили свою работу и сигнализировали об этом.
 *
 */

public class CountDownLatchExample {
    public static void main(String[] args) {
        final int numberOfThreads = 5;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);

        Runnable worker = () -> {
            System.out.println("Thread " + Thread.currentThread().getId() + " is working.");
            latch.countDown(); // Signal that this thread's work is done
        };

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(worker);
            thread.start();
        }

        try {
            latch.await(); // Wait until all threads finish their work
            System.out.println("All threads have completed their work.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

