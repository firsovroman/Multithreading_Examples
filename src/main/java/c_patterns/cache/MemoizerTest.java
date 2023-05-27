package c_patterns.cache;

import org.apache.commons.lang3.concurrent.Computable;

import java.util.concurrent.*;

public class MemoizerTest {

    private static class SlowComputable implements Computable<String, Integer> {
        @Override
        public Integer compute(String arg) throws InterruptedException {
            // Имитируем долгое вычисление
            Thread.sleep(1000);
            return arg.length();
        }
    }

    public void testCompute() throws InterruptedException, ExecutionException {
        Computable<String, Integer> slowComputable = new SlowComputable();
        Memoizer<String, Integer> memoizer = new Memoizer<>(slowComputable);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // Подготовка списка задач для вычисления
        Callable<Integer> task1 = () -> memoizer.compute("Hello");
        Callable<Integer> task2 = () -> memoizer.compute("Hello");
        Callable<Integer> task3 = () -> memoizer.compute("Hello");


        // Запуск задач в пуле потоков
        Future<Integer> future1 = executorService.submit(task1);
        Future<Integer> future2 = executorService.submit(task2);
        Future<Integer> future3 = executorService.submit(task3);

        // Ожидание завершения вычислений
        future1.get();
        future2.get();
        future3.get();

        // Остановка пула потоков
        executorService.shutdown();
    }

}
