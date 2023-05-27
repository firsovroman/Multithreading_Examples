package a_primitives.intrinsic_locks_and_synchronization;

/*
 *  Сделайте так, чтобы оба метода могли выполняться одновременно двумя различными тредами.
    synchronized(this) для этого не подходит, используй другой объект для лока.
 */

/*
        Предположим, например, что класс LocksObjects два поля экземпляра c1и c2, которые никогда не используются вместе.
        Все обновления этих полей должны быть синхронизированы, но нет никаких причин препятствовать тому,
        чтобы обновление c1 чередовалось с обновлением c2 — и это уменьшает параллелизм, создавая ненужную блокировку.
        Вместо использования синхронизированных методов или иного использования блокировки, связанной с this,
        мы создаем два объекта исключительно для предоставления блокировок.
 */


public class LocksObjects {
    int var1;
    int var2;
    int var3;
    int var4;

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public LocksObjects(int var1, int var2, int var3, int var4) {
        this.var1 = var1;
        this.var2 = var2;
        this.var3 = var3;
        this.var4 = var4;
    }

    public int getSumOfVar1AndVar2() {
        synchronized (lock1){
            return var1 + var2;
        }
    }

    public int getSumOfVar3AndVar4() {
        synchronized (lock2){
            return var3 + var4;
        }
    }

    public static void main(String[] args) {

    }
}