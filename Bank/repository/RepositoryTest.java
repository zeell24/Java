package clg.Bank.repository;

import clg.Bank.model.Account;
import clg.Bank.model.SavingAccount;

public class RepositoryTest
{
    public static void main(String[] args)
    {
        Repository<Account> repo = new Repository<>();

        Account a1 = new SavingAccount("Zeel", 45000L, 5000L);
        Account a2 = new SavingAccount("Ami", 30000L, 3000L);
        Account a3 = new SavingAccount("Riya", 20000L, 2000L);

        repo.saveAll(a1, a2, a3);

        System.out.println("-------- Find By ID --------");
        System.out.println(repo.findById(a1.getAccountNumber()));

        System.out.println("-------- All Accounts --------");
        for(Account a : repo.findAll())
        {
            System.out.println(a);
        }

        System.out.println("Deleted: " + repo.delete(a2.getAccountNumber()));
        System.out.println("Remaining accounts: " + repo.findAll().size());
    }
}
