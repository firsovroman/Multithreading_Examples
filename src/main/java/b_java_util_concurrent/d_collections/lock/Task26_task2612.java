package b_java_util_concurrent.d_collections.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Task26_task2612 {

    private Lock lock = new ReentrantLock();

    public void someMethod() {
        //пытаемся взять лок
        if (lock.tryLock()) {
            try {
                actionIfLockIsFree();
            } finally {
                lock.unlock();
            }
        } else {
            actionIfLockIsBusy();
        }

    }

    public void actionIfLockIsFree(){
    }

    public void actionIfLockIsBusy() {
    }

}
