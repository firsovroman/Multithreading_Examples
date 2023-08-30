# Multithreading Examples

Этот репозиторий содержит набор примеров, демонстрирующих использование концепций многопоточности в Java.

## Оглавление

### Fundamental
- Thread lifecycle (States)
- start(), run()   ( extends Thread & implements Runnable )
- Monitor  &  wait(), notify(), notifyAll()
- Thread.interrupt() и Thread.sleep()
- Thread.yield()
- Thread.join()
- synchronized
- volatile


### Primitives
* Thread Pooling
  - ThreadFactory
  - Executor and ExecutorService
  - Timer and ScheduledExecutorService
  - Future and CompletableFuture
* Collection
  - ArrayBlockingQueue
  - ConcurrentHashMap
* Synchronizers
  - Lock, Condition, and ReentrantLock
  - StampedLock and ReadWriteLock
  - Semaphore
  - CountDownLatch
  - CyclicBarrier
  - Phaser
  - Exchanger
  - StampedLock
  - LockSupport
* Others
  - ThreadLocal
  - Fork/Join and Fork/Join Framework
  - Akka Library's Actor

### Multithreading Design Patterns Examples
* Deadlock
* Double-Checked Locking
* Caching

### Akka Examples
- actor
- loadbalancing


