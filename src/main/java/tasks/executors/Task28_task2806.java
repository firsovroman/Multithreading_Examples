package tasks.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*

1. В методе main создай фиксированный пул из 5 трэдов используя класс Executors.
2. В цикле отправь на исполнение в пул 10 задач Runnable.
3. У каждой задачи в методе run вызови метод doExpensiveOperation с порядковым номером задачи начиная с 1, см. пример вывода.
4. Запрети добавление новых задач на исполнение в пул (метод shutdown).
5. Дай пулу 5 секунд на завершение всех задач (метод awaitTermination и параметр TimeUnit.SECONDS).

*/

public class Task28_task2806 {
    public static void main(String[] args) throws InterruptedException {


        ExecutorService service = Executors.newFixedThreadPool(5); // В методе main создай фиксированный пул из 5 трэдов используя класс Executors.

        for(int i = 1; i < 11; i++) { // В цикле отправь на исполнение в пул 10 задач Runnable.
            int finalI = i; // effectively final
            service.submit(() -> doExpensiveOperation(finalI)); // У каждой задачи в методе run вызови метод doExpensiveOperation с порядковым номером задачи начиная с 1, см. пример вывода.
        }

        service.shutdown(); // Запрети добавление новых задач на исполнение в пул (метод shutdown).
        service.awaitTermination(5, TimeUnit.SECONDS); // Дай пулу 5 секунд на завершение всех задач (метод awaitTermination и параметр TimeUnit.SECONDS).

        /* output example
pool-1-thread-2, localId=2
pool-1-thread-1, localId=1
pool-1-thread-3, localId=3
pool-1-thread-1, localId=7
pool-1-thread-1, localId=9
pool-1-thread-4, localId=4
pool-1-thread-5, localId=5
pool-1-thread-2, localId=6
pool-1-thread-1, localId=10
pool-1-thread-3, localId=8
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}