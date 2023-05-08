package tasks.concurrent_collections.concurrent_hashmap;

import java.util.concurrent.ConcurrentHashMap;

public class Producer1 implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Producer1(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {

        try {
            int i = 1;
            while (!Thread.currentThread().isInterrupted()){
                map.put(String.valueOf(i),"Some text for "+ i);
                i++;
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" thread was terminated");
        }

    }
}
