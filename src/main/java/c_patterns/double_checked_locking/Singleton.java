package c_patterns.double_checked_locking;

public class Singleton {
    private volatile static Singleton instance;

    private Singleton() {
        // Приватный конструктор
    }

    public static Singleton getInstance() {
        if (instance == null) { // Первая проверка без блокировки
            synchronized (Singleton.class) {
                if (instance == null) { // Вторая проверка с блокировкой
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

