package b_java_util_concurrent.f_synchronizers.f_phaser;

import java.util.concurrent.Phaser;

public class PhaserExample {
    public static void main(String[] args) {
        final int numberOfThreads = 3;
        Phaser phaser = new Phaser(numberOfThreads);

        Runnable worker = () -> {
            System.out.println("Thread " + Thread.currentThread().getId() + " is working on phase " + phaser.getPhase());
            phaser.arriveAndAwaitAdvance(); // Wait for all threads to reach this point
            System.out.println("Thread " + Thread.currentThread().getId() + " continues after phase " + phaser.getPhase());
        };

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(worker);
            thread.start();
        }

        phaser.awaitAdvance(phaser.getPhase()); // Wait for all threads to complete
        System.out.println("All threads have completed the work.");
    }
}

