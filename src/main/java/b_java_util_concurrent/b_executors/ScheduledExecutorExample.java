package b_java_util_concurrent.b_executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {
    public static void main(String[] args) {
        // Создаем ScheduledExecutorService с одним потоком
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Запускаем задачу с задержкой 3 секунды
        Runnable task = () -> System.out.println("Задача выполнена!");
        scheduler.schedule(task, 3, TimeUnit.SECONDS);

        // Завершаем планировщик после выполнения задачи
        scheduler.shutdown();
    }
}
