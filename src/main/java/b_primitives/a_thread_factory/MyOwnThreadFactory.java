package b_primitives.a_thread_factory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/*
Пишем свою ThreadFactory
    В классе Solution создай публичный статический класс AmigoThreadFactory, реализующий интерфейс ThreadFactory.
    1. Реализация интерфейсного метода - создай и верни трэд, который должен:
    1.1. не быть демоном,
    1.2. иметь нормальный приоритет,
    1.3. имя трэда должно иметь шаблон "GN-pool-A-thread-B",
    где GN - это имя группы,
    A - это номер фабрики инкрементируется в пределах класса начиная с 1, используй AtomicInteger,
    B - номер треда инкрементируется в пределах конкретной фабрики начиная с 1, используй AtomicInteger.
    2. Каждая фабрика должна иметь ту группу тредов (ThreadGroup), в которой она была создана.
    3. Методы main и emulateThreadFactory не участвуют в тестировании.
*/



/*
Ожидаемый вывод:
    secondGroup-pool-2-thread-1
    firstGroup-pool-1-thread-1
    firstGroup-pool-1-thread-3
    secondGroup-pool-2-thread-3
    firstGroup-pool-1-thread-2
    secondGroup-pool-2-thread-2
 */

public class MyOwnThreadFactory {

    public static void main(String[] args) {

        // создаем задачу выполнить метод emulateThreadFactory()
        class EmulatorThreadFactoryTask implements Runnable {
            @Override
            public void run() {
                emulateThreadFactory();
            }
        }

        // создаем группу и тред, треду присваиваем созданную группу и задачу
        ThreadGroup group = new ThreadGroup("firstGroup");
        Thread thread = new Thread(group, new EmulatorThreadFactoryTask());

        // создаем группу и тред, треду присваиваем созданную группу и задачу
        ThreadGroup group2 = new ThreadGroup("secondGroup");
        Thread thread2 = new Thread(group2, new EmulatorThreadFactoryTask());

        // запускаем оба треда
        thread.start();
        thread2.start();
    }

    // первая задача
    private static void emulateThreadFactory() {

        // создаем фабрику и тред с задачей выполнить sout
        CustomThreadFactory factory = new CustomThreadFactory();
        // вторая задача
        Runnable r = () -> System.out.println(Thread.currentThread().getName());

        // создаем новые треды фабрикой передаем туда вторую задачу вывести имя созданной нити и запускаем
        factory.newThread(r).start();
        factory.newThread(r).start();
        factory.newThread(r).start();
    }



    public static class CustomThreadFactory implements ThreadFactory {

        // счетчик для всех фабрик
        private static AtomicInteger shared = new AtomicInteger(0);

        // номер конкретной фабрики
        private volatile int poolNumber = 0;

        // счетчик для потоков
        private final AtomicInteger threadNumber = new AtomicInteger(1);


        public CustomThreadFactory() {
            this.poolNumber = shared.incrementAndGet();
        }

        @Override
        public Thread newThread(Runnable r) {

            // берем группу из текущего инициирующего треда
            ThreadGroup tg = Thread.currentThread().getThreadGroup();

            // создаем тред, передаем туда полученную группу, тип задачи из параметров, собираем имя
            Thread t = new Thread(tg, r,
                    tg.getName() + "-pool-" + poolNumber + "-thread-" + threadNumber.getAndIncrement(),
                    0);

            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);

            return t;
        }
    }

}
