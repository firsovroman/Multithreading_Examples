package b_java_util_concurrent.collections.concurrent_hashmap;

import java.util.concurrent.ConcurrentHashMap;

public class Consumer1 implements Runnable {
    private ConcurrentHashMap<String, String> map;

    public Consumer1(ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    public void run() {
        Thread currentThread = Thread.currentThread();
        while (!currentThread.isInterrupted()) {
            if (!map.isEmpty()) {
                for (String key : map.keySet()) {
                    System.out.println(map.remove(key));
                }
            }
        }
    }
}