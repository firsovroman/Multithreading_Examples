package b_primitives.b_executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorPeriodicTaskExample {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Запускаем задачу, которая будет выполняться каждые 2 секунды
        Runnable periodicTask = () -> System.out.println("Периодическая задача выполняется!");
        scheduler.scheduleAtFixedRate(periodicTask, 0, 2, TimeUnit.SECONDS);

        // Даем задаче выполняться в течение некоторого времени
        try {
            Thread.sleep(10000); // Позволяем задаче выполняться в течение 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Завершаем планировщик после выполнения задач
        scheduler.shutdown();
    }
}
