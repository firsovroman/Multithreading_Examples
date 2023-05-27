package c_patterns.cache;

import org.apache.commons.lang3.concurrent.Computable;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer<A, V> implements Computable<A, V> {

    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();

    private final Computable<A, V> computable;

    public Memoizer(Computable<A, V> computable) {
        this.computable = computable;
    }

    public Map<A, Future<V>> getCache() {
        return Map.copyOf(cache);
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " зашел в метод compute()");
        while (true) {
            Future<V> f = cache.get(arg);
            System.out.println(Thread.currentThread().getName() + " - First moment f = " + f);
            if (f == null) {

                Callable<V> eval = () -> computable.compute(arg); // определяем таску
                FutureTask<V> futureTask = new FutureTask<>(eval);

                f = cache.putIfAbsent(arg, futureTask);
                System.out.println(Thread.currentThread().getName() + " - Second moment f = " + f);

                if (f == null) {
                    System.out.println(Thread.currentThread().getName() + " *** Exclusive moment ***");
                    f = futureTask;
                    futureTask.run();
                }
            }

            try {
                System.out.println(Thread.currentThread().getName() + " - Four moment f = " + f);
                V result = f.get();
                return result;
            } catch (CancellationException e) {
                cache.remove(arg, f); // удаляем Future из кеша если вычисление было отменено
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
