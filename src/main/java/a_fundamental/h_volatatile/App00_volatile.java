package a_fundamental.h_volatatile;

public class App00_volatile {

    /**
     *  если не ставить volatile, то спецификацией не определено
     *  когда дочерний поток увидит изменения (5, 7, 10 минут или никогда)
     *  внесенные потоком main
     */
    static boolean run = true ;

    public static void main(String[] args) throws InterruptedException {

        Thread newThread = new Thread(() -> {
            while (run);
        });
        newThread.start();
        Thread.sleep(1);
        run = false;
    }

}
