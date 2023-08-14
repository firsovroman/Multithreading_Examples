package a_primitives.exceptionHandler;

/*
    В примере показано как у потока можно переопределить перехватчик исключений.

 */
public class Solution {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new Solution().new MyThread();
        myThread.start();
        Thread.sleep(3000);
    }

    public class MyThread extends Thread {

        public MyThread() {
            setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
            setDaemon(false);
        }

        @Override
        public void run() {
            throw new NullPointerException("Exception message");
        }

        private class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                String s = String.format("Exception with message %s, in  %s, was suppressed.", e.getMessage(), t.getName());

                System.out.println(s);
            }
        }

    }

}


