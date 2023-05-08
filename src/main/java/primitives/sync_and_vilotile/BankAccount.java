package primitives.sync_and_vilotile;

import java.math.BigDecimal;

public class BankAccount {
    private volatile BigDecimal balance;  // для наглядности можно убрать volatile
    private String owner;

    public BankAccount(String owner) { // конструктор 1
        this(BigDecimal.ZERO, owner);
    }

    public BankAccount(BigDecimal balance, String owner) { // конструктор 2
        this.balance = balance;
        this.owner = owner;
    }

    public void deposit(BigDecimal money) { // добавляем money к balance
        BigDecimal newBalance = balance.add(money); // создали переменную в неё положили balance + money
        System.out.println("Добавляем " + money + ", на счету " + newBalance);
        balance = newBalance; // перезадали значение переменной balance
    }

    // для наглядности можно убрать synchronized
    public synchronized void withdraw(BigDecimal money) throws NotEnoughMoneyException { // снять со счета
        BigDecimal newBalance = balance.subtract(money); // отнять

        if (newBalance.compareTo(BigDecimal.ZERO) < 0){ // {-1; 0 ; 1}
            throw new NotEnoughMoneyException();
        }
        // Если условие идентичности типов не будет выполняться, то мы получим (-1) и исключение NotEnoughMoneyException

        balance = newBalance; // присвоить balance новое значение
        System.out.println(Thread.currentThread().getName() + " Тратим " + money + ", на счету " + balance);
    }

    public void deposit(String money) { // принимает строку помещает в объект для сложения  (money)
        deposit(new BigDecimal(money));
    }

    public void withdraw(String money) throws NotEnoughMoneyException { // принимает строку помещает в объект для вычитания
        withdraw(new BigDecimal(money));
    }
}