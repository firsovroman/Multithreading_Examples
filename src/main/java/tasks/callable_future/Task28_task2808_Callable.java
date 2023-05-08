package tasks.callable_future;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*
Осваиваем Callable

Реализуй логику метода getTask, который должен возвращать объект Callable.
Объект Callable должен вычислять сумму всех чисел от 1 до переданного числа i включая его, и возвращать его в виде строки.
*/

public class Task28_task2808_Callable {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<Future<String>> futures = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1000; i <= 1010; i++) {
            futures.add(executor.submit(getTask(i)));
        }

        futures.add(executor.submit(getTask(10000000)));

        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        executor.shutdown(); //  отправленные задачи выполняются, но новые задачи не принимаются
        executor.awaitTermination(10, TimeUnit.SECONDS);  // даем 10 сек на зваершение выполняющихся задач

/* output
500500
501501
502503
503506
504510
505515
506521
507528
508536
509545
510555
50000005000000
*/
    }

    public static Callable<String> getTask(final int i) {
        return () -> BigInteger.valueOf((long)((i*1.0*(i+1.0))/2.0)).toString();
    }
}
