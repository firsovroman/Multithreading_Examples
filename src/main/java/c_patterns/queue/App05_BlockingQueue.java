package c_patterns.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App05_BlockingQueue {


    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(16);


        // Producer
        new Thread(() -> {
            int counter = 0;
            while (true) {

                try {
                    Thread.sleep(300);
                    queue.put(++counter);
                    System.out.println("put: " + counter);
                } catch (InterruptedException e) { /*NOP*/ }
            }
        }).start();


        // Consumer
        new Thread(() -> {
            while (true) {
                try {
                    System.out.println("...wait for take");
                    int data = queue.take(); // блокируется поток пока в очереди не появятся данные

//                    Integer data = queue.poll(1, TimeUnit.SECONDS); // если на момент запроса (окончание таймаута) нет данных вернет null
//                    if (data == null) {
//                        System.out.println("NO data");
//                    }

                    // int data = queue.remove(); // если на момент запроса нет данных бросите исключение

                    System.out.println("take: " + data);
                } catch (InterruptedException e) { /*NOP*/ }

            }
        }).start();


    }


}
