package b_java_util_concurrent.f_synchronizers.e_cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    public static void main(String[] args) {

        final int numberOfThreads = 3;

        CyclicBarrier barrier = new CyclicBarrier(numberOfThreads, () -> {
            System.out.println("All threads have reached the barrier. Continuing...");
        });

        Runnable worker = () -> {
            System.out.println("Thread " + Thread.currentThread().getId() + " is working.");
            try {
                barrier.await(); // Wait for all threads to reach the barrier
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getId() + " continues after the barrier.");
        };

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(worker);
            thread.start();
        }
    }
}

