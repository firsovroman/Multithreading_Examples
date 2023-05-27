package b_java_util_concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class DecThread implements Runnable{

    String name;
    Semaphore sem;

    public DecThread(Semaphore sem, String a) {
        this.sem = sem;
        this.name = a;
    }

    @Override
    public void run() {
        System.out.println("Запуск потока: "+ name);

        try {
            System.out.println("Поток " + name + " ожидает разрешения");
            sem.acquire();  // можно закомментировать строку, чтобы посмотреть разницу
            System.out.println("Поток " + name + " получает разрешение");

            for(int  i =0; i<5; i++) {
                Shared.count--;
                System.out.println(name + ": " + Shared.count);

                Thread.sleep(10);
            }

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            System.out.println("Поток " + name + " освобождает разрешение");
            sem.release(); // можно закомментировать строку, чтобы посмотреть разницу
        }
    }
}
