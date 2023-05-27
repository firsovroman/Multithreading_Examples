package c_patterns.deadlock;

/*

    DeadLock example 4

 */


public class Task27_task2704 {

    private final String field;

    public Task27_task2704(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }

    synchronized public void sout(Task27_task2704 solution) {
        System.out.format("111:  %s: %s %n", this.field, solution.getField());
        solution.sout2(this);
    }

    synchronized public void sout2(Task27_task2704 solution) {
        System.out.format("222:  %s: %s %n", this.field, solution.getField());
        solution.sout(this);
    }

    public static void main(String[] args) {
        final Task27_task2704 solution = new Task27_task2704("first");
        final Task27_task2704 solution2 = new Task27_task2704("second");
        new Thread(new Runnable() {
            public void run() {
                solution.sout(solution2);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                solution2.sout(solution);
            }
        }).start();

    }

}
