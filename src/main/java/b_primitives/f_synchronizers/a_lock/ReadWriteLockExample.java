package b_primitives.f_synchronizers.a_lock;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/*
Организация многопоточного чтения/записи любого общего ресурса без блокировки при чтении.

1) ReentrantReadWriteLock - это усовершенствованная версия ReentrantLock, которая дает более высокую производительность при множественных операциях чтения и записи из разных потоков.
2) поток чтения, пытающийся захватить readLock, смотрит, не занят ли writeLock. если не занят, то тогда (именно в имплементации ReentrantReadLock) он смотрит, не стоит ли в очереди ожидания поток записи:
- если нет, то захватывает readLock, независимо от того, захвачен ли этот readLock кем-то еще;
- если поток записи уже ждет окончания чтения, то наш поток чтения становится в хвост очереди (несмотря на то, что идет операция чтения одним или несколькими потоками).
3) поток записи сможет захватить writeLock только в том случае, когда оба лока readLock и writeLock не заняты. в ином случае он попадает в очередь ожидания.

итого: чтение может осуществляться несколькими потоками одновременно, запись - только одним (при обязательном отсутствии читающих).
readLock может быть захвачен несколькими потоками, writeLock - только одним.


P.S. Для корректной работы Map в многопоточной среде, желательно пользоваться стандартной реализацией,
например ConcurrentHashMap из пакета java.util.concurrent. Но иногда требуется работа с другим типом мапы,
например LinkedHashMap и тогда предложенное решение будет как никогда кстати.
 */

public class ReadWriteLockExample<K, V> {
    private final Map<K, V> map;
    private final java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock();

    public ReadWriteLockExample(Map<K, V> map) {
        this.map = map;
    }

    public V put(K key, V value) {
        lock.writeLock().lock();
        try {
            return map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }

}