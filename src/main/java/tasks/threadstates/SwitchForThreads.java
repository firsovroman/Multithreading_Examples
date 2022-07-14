package tasks.threadstates;

public class SwitchForThreads {

    public static void processThreads(Thread... threads) {

        for(Thread thread : threads){

            switch(thread.getState()){  // "age" вот по значению этой переменной мы будем сравнивать с условиями
                case NEW :
                    thread.start();
                    break;
                case TIMED_WAITING :
                case WAITING :
                case BLOCKED:
                    thread.interrupt();
                    break;
                case RUNNABLE :
                    thread.isInterrupted();
                    break;
                case TERMINATED :
                    System.out.println(thread.getPriority());
                    break;
                default:

            }
        }
    }

    public static void main(String[] args) {
    }

}
