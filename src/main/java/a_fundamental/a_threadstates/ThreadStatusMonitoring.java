package a_fundamental.a_threadstates;

/*
 Вывести в консоль 3 состояния (State) переданной в конструктор нити.
*/

public class ThreadStatusMonitoring {
    public static void main(String[] args) throws InterruptedException {
        Thread target = new Thread();
        LoggingStateThread loggingStateThread = new LoggingStateThread(target);

        loggingStateThread.start(); //NEW
        Thread.sleep(100);
        target.start();  //RUNNABLE
        Thread.sleep(100);
        //TERMINATED
    }
}

class LoggingStateThread extends Thread {

    Thread thread;

    public LoggingStateThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        State state = thread.getState();
        System.out.println(state);
        while (true) {
            if(state != thread.getState()){
                state = thread.getState();
                System.out.println(state);
            }
            if(state == State.TERMINATED){
                return;
            }
        }
    }

}
