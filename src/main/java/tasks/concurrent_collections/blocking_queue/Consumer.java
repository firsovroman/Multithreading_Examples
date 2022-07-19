package tasks.concurrent_collections.blocking_queue;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {


        try {

            while(true) {
                System.out.println(queue.take());
            }

        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }


    }
}