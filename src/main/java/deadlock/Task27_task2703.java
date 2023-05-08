package deadlock;

/*
        DeadLock example
 */


public class Task27_task2703 {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        synchronized public void f1(Friend friend) {
            System.out.println(Thread.currentThread().getName() + " зашел в метод f1()" + " занял мьютекс объекта - " + this.name);
            System.out.format("Объект %s:  на объекте %s пытаемся вызвать метод f2() %n", this.name, friend.name);
            friend.f2();
        }

        synchronized public void f2() {
            System.out.println(Thread.currentThread().getName() + " зашел в метод f2()");
        }

    }


    public static void main(String[] args) {
        final Friend alphonse = new Friend("Alice");
        final Friend gaston = new Friend("Bob");

        new Thread(new Runnable() {
            public void run() {
                alphonse.f1(gaston);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                gaston.f1(alphonse);
            }
        }).start();

    }


}
