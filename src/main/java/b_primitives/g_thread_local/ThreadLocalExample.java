package b_primitives.g_thread_local;

/**
 * ThreadLocal-переменная остается такой же вне зависимости от потока,
 * который обращается к ней, а вот количество потоков растет.
 *
 * ThreadLocal переменные отличаются от обычных тем, что у каждого потока свой собственный,
 * индивидуально инициализируемый экземпляр переменной, доступ к которой он получает через методы get() или set().
 *
 *
 *
 */

public class ThreadLocalExample {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();

        Thread t1 = new Thread(threadDemo);
        Thread t2 = new Thread(threadDemo);
        Thread t3 = new Thread(threadDemo);

        t1.start();
        t2.start();
        t3.start();

    }

}

class ThreadDemo implements Runnable {

    int counter;
    ThreadLocal<Integer> threadLocalCounter = ThreadLocal.withInitial(() -> 0);

    public void run() {
        counter++;
        threadLocalCounter.set(threadLocalCounter.get() + 1);

        printCounters();
    }

    public void printCounters(){
        System.out.println("Counter: " + counter);
        System.out.println("threadLocalCounter: " + threadLocalCounter.get());
    }
}
