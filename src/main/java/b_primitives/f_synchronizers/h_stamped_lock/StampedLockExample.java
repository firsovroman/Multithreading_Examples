package b_primitives.f_synchronizers.h_stamped_lock;

import java.util.concurrent.locks.StampedLock;

/**
 *  Сначала выполняется запись, затем оптимистическое чтение (с проверкой наличия изменений) и, наконец, чтение данных.
 *  Оптимистическое чтение (Optimistic Read): Позволяет быстро попробовать прочитать данные без блокировки.
 *  Если данные не изменились с момента начала оптимистического чтения, операция считается успешной
 *
 *  Может быть полезным в ситуациях, где требуется высокая производительность для операций.
 */

public class StampedLockExample {
    public static void main(String[] args) {
        StampedLock lock = new StampedLock();
        double x = 0.0, y = 0.0;

        // Операция записи (монопольная блокировка)
        long writeStamp = lock.writeLock();
        try {
            x += 1.0;
            y += 2.0;
        } finally {
            lock.unlockWrite(writeStamp);
        }

        // Оптимистичное чтение
        long optimisticStamp = lock.tryOptimisticRead();
        double currentX = x;
        double currentY = y;
        if (!lock.validate(optimisticStamp)) {
            optimisticStamp = lock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                lock.unlockRead(optimisticStamp);
            }
        }

        // Операция чтения
        long readStamp = lock.readLock();
        try {
            System.out.println("Current coordinates: (" + currentX + ", " + currentY + ")");
        } finally {
            lock.unlockRead(readStamp);
        }
    }
}

