package clg.Bank;

import clg.Bank.model.SavingAccount;
import clg.Bank.thread.TransactionProcessor;

public class MiniBankPoolTest
{
    public static void main(String[] args) throws InterruptedException
    {
        SavingAccount account = new SavingAccount("Zeel", 10000, 1000);

        TransactionProcessor processor = new TransactionProcessor();

        System.out.println("Initial balance: " + account.getBalance());

        for(int i = 1; i <= 10; i++)
        {
            processor.submit(new AccountWorker(account, 1, 500));
        }

        processor.stop();

        System.out.println("Final balance: " + account.getBalance());
    }
}
