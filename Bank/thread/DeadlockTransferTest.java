package clg.Bank.thread;

import clg.Bank.model.SavingAccount;
import clg.Bank.model.Account;

public class DeadlockTransferTest
{
    static void transfer(Account a, Account b, long amount)
    {
        Account first = a.getAccountNumber().compareTo(b.getAccountNumber()) < 0 ? a : b;
        Account second = first == a ? b : a;

        synchronized(first)
        {
            synchronized(second)
            {
                try
                {
                    a.withdraw(amount);
                    b.deposit(amount);
                    System.out.println("Transfer completed: "
                            + a.getAccountNumber() + " to "
                            + b.getAccountNumber());
                }
                catch(Exception e)
                {
                    System.out.println("Transfer failed: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException
    {
        SavingAccount a = new SavingAccount("Alice", 10000, 1000);
        SavingAccount b = new SavingAccount("Bob", 10000, 1000);

        Thread t1 = new Thread(() -> transfer(a, b, 500));
        Thread t2 = new Thread(() -> transfer(b, a, 500));

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Account A: " + a.getBalance());
        System.out.println("Account B: " + b.getBalance());
    }
}