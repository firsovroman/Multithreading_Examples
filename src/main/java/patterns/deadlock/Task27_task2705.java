package patterns.deadlock;

/*
Второй вариант patterns.deadlock
*/

public class Task27_task2705 extends Thread {

    private final Object lock = new Object();

    public synchronized void firstMethod() {
        System.out.println(Thread.currentThread().getName() + " зашел в метод firstMethod() занял мьютекс для объекта - "
                + this.getName() +" ждет пока освободится мьютекс для - " + lock);
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " зашел в метод secondMethod()" + " занял мьютекс объекта - " + lock +
                    " ждет пока освободится мьютекс для - " + this.getName());

//            Thread.sleep(1);   // Усыпляем поток на 1 мс
            synchronized (this) {
                doSomething();
            }
        }
    }

    private void doSomething() {
    }

    public static void main(String[] args) {
        Task27_task2705 solution = new Task27_task2705();

        Thread t1 = new Thread(solution::secondMethod);

        Thread t2 = new Thread(solution::secondMethod);
        t1.start();
        t2.start();
    }


}
