package clg.Bank.thread;

import clg.Bank.model.Account;
import java.util.concurrent.*;
import java.util.ArrayList;

public class TransactionProcessor
{
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Account> accounts =
        new ConcurrentHashMap<>();

    public void addAccount(Account account)
    {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber)
    {
        return accounts.get(accountNumber);
    }

    public ConcurrentHashMap<String, Account> getAccounts()
    {
        return accounts;
    }

    public void submit(Runnable task)
    {
        executor.execute(task);
    }

    public void stop() throws InterruptedException
    {
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
