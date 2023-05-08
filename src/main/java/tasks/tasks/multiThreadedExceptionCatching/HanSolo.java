package tasks.tasks.multiThreadedExceptionCatching;

/*
Все исключения, которые возникают в процессе работы нити Solution, должны быть обработаны одним из вариантов:
1. Если это Error, то вывести в консоль "Нельзя дальше работать".
2. Если это Exception, то вывести в консоль "Надо обработать".
3. Если это Throwable, то вывести в консоль "Поживем - увидим".
 */


public class HanSolo extends Thread {

    Thread thread;

    public HanSolo() {

        this.setUncaughtExceptionHandler((t, e) -> {
            if(e instanceof Error) {
                System.out.println("Нельзя дальше работать");
            } else if(e instanceof Exception) {
                System.out.println("Надо обработать");
            } else if(e instanceof Throwable) {
                System.out.println("Поживем - увидим");
            }
        });


    }

    public static void main(String[] args) {
    }
}

