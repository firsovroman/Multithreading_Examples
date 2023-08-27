package b_primitives.f_synchronizers.a_lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private static int count = 0;
    private static final int MAX_COUNT = 10;
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread producer = new Thread(() -> produce());
        Thread consumer = new Thread(() -> consume());

        producer.start();
        consumer.start();
    }

    public static void produce() {
        lock.lock();
        try {
            while (count < MAX_COUNT) {
                count++;
                System.out.println("Produced: " + count);
                condition.signal(); // Signal the consumer
                condition.await();  // Wait until consumer consumes
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void consume() {
        lock.lock();
        try {
            while (count < MAX_COUNT) {
                condition.await();  // Wait until producer produces
                System.out.println("Consumed: " + count);
                condition.signal(); // Signal the producer
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

