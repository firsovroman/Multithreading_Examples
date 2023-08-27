package b_primitives.f_synchronizers.f_phaser;

import java.util.concurrent.Phaser;

/**
 * аждый поток выполняет некоторую работу на фазе и затем вызывает метод arriveAndAwaitAdvance(),
 * который блокирует потоки до тех пор, пока все остальные потоки не достигнут этой точки.
 *
 * После завершения этой фазы главный поток вызывает метод awaitAdvance(),
 * чтобы дождаться, пока все потоки закончат работу. Этот метод блокирует главный поток до тех пор,
 * пока все потоки не достигнут фазы, на которой он вызывается.
 *
 */

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

