package tasks.interrupt.one;

public class TaskManipulator implements Runnable, CustomThreadManipulator{

    Thread thread;

    @Override
    public void run() {
        while (true){
            try {
                System.out.println(this.thread.getName());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
    }


    @Override
    public void start(String name) {
        this.thread = new Thread(this, name);
        thread.start();
    }

    @Override
    public void stop() {

        this.thread.interrupt();

    }


}
