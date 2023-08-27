package b_primitives.c_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureExample {
    public static void main(String[] args) {
        // Создаем CompletableFuture, представляющий асинхронное выполнение операции
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // Имитация длительной операции
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        });

        // Прикрепляем обработчик, который будет выполнен по завершению операции
        CompletableFuture<String> processedFuture = future.thenApply(result -> {
            return result + " World";
        });

        try {
            // Ожидаем завершения операции и получаем результат
            String finalResult = processedFuture.get();
            System.out.println(finalResult); // Вывод: Hello World
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

