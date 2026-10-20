package clg.Bank;
import clg.Bank.model.Account;
import clg.Bank.model.SavingAccount;

public class MiniBankThreadTest {
    public static void main(String[] args) throws InterruptedException {
        Account account = new SavingAccount("Zeel", 50000, 5000);

        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            AccountWorker worker = new AccountWorker(account, 10000, 1);
            threads[i] = new Thread(worker, "Worker-" + (i + 1));
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Final balance: " + account.getBalance());
        System.out.println("Expected balance: 150000");
    }
}
