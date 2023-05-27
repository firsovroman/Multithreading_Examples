package c_patterns.deadlock;

public class Task27_task2706 {

    public void safeMethod(Object obj1, Object obj2) {
        Object objectMax =  obj1.hashCode() > obj2.hashCode();
        Object objectMin =  obj1.hashCode() > obj2.hashCode();

        synchronized (objectMax) {
            System.out.println(Thread.currentThread().getName() + " зашел в первый блок" + " занял мьютекс объекта - " + obj1.toString());
            longTimeMethod();
            System.out.println(Thread.currentThread().getName() + " ждет освобождение мьютекса" + obj2.toString());
            synchronized (objectMin) {
                System.out.println(Thread.currentThread().getName() + " зашел во второй блок" + " занял мьютекс объекта - " + obj2.toString());
                unsafeMethod(obj1, obj2);
            }
        }

    }


//    public void safeMethod(Object obj1, Object obj2) {

//        synchronized (obj1) {
//            System.out.println(Thread.currentThread().getName() + " зашел в первый блок" + " занял мьютекс объекта - " + obj1.toString());
//            longTimeMethod();
//            System.out.println(Thread.currentThread().getName() + " ждет освобождение мьютекса" + obj2.toString());
//            synchronized (obj2) {
//                System.out.println(Thread.currentThread().getName() + " зашел во второй блок" + " занял мьютекс объекта - " + obj2.toString());
//                unsafeMethod(obj1, obj2);
//            }
//        }
//    }


    public void longTimeMethod() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
    }

    protected void unsafeMethod(Object obj1, Object obj2) {
        System.out.println(obj1 + " " + obj2);
    }

    public static void main(String[] args) {
        final Object o1 = new Object();
        final Object o2 = new Object();
        final Task27_task2706 solution = new Task27_task2706();

        new Thread() {
            @Override
            public void run() {
                solution.safeMethod(o1, o2);
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                solution.safeMethod(o2, o1);
            }
        }.start();
    }
}
