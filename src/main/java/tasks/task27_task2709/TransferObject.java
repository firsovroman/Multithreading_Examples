package tasks.task27_task2709;

public class TransferObject {
    private int value;
    protected volatile boolean isValuePresent = false; //use this variable

    synchronized public int get() {

        while(!isValuePresent) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Got: " + value);
        isValuePresent = false;

        notifyAll();
        return value;
    }

    synchronized public void put(int value) {

        while(isValuePresent) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        isValuePresent = true;

        this.value = value;
        System.out.println("Put: " + value);

        notifyAll();
    }
}
