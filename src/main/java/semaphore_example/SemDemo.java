package semaphore_example;

import java.util.concurrent.Semaphore;

public class SemDemo {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);

        Thread myThread = new Thread(new IncThread(sem, "A"));
        Thread myThread2 = new Thread(new DecThread(sem, "B"));


        myThread.start();
        myThread2.start();
    }

}
