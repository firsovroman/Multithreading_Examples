package b_primitives.b_executors;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*

1. В методе main создай очередь LinkedBlockingQueue<Runnable>.
2. В цикле добавь в очередь 10 задач Runnable.
3. У каждой задачи в методе run вызови метод doExpensiveOperation с порядковым номером задачи начиная с 1, см. пример вывода.
4. Создай объект ThreadPoolExecutor со следующими параметрами:
- основное количество трэдов (ядро) = 3,
- максимальное количество трэдов = 5,
- время удержания трэда живым после завершения работы = 1000,
- тайм-юнит - миллисекунды,
- созданная в п.1. очередь с задачами.
5. Запусти все трэды, которые входят в основное кол-во трэдов - ядро, используй метод prestartAllCoreThreads.
6. Запрети добавление новых задач на исполнение в пул (метод shutdown).
7. Дай объекту ThreadPoolExecutor 5 секунд на завершение всех тасок (метод awaitTermination и параметр TimeUnit.SECONDS).

*/

public class ExecutorService_Task28_task2807 {
    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<Runnable> linkedBlockingQueue = new LinkedBlockingQueue<>();

        for(int i = 1; i < 11; i++) { // В цикле отправь на исполнение в пул 10 задач Runnable.
            int finalI = i; // effectively final
            linkedBlockingQueue.add(() -> doExpensiveOperation(finalI)); // У каждой задачи в методе run вызови метод doExpensiveOperation с порядковым номером задачи начиная с 1, см. пример вывода.
        }


        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, linkedBlockingQueue);

        executorPool.prestartAllCoreThreads();

        executorPool.shutdown();

        executorPool.awaitTermination(5, TimeUnit.SECONDS);


        /* Example output
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}

