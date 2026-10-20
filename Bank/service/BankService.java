package clg.Bank.service;

import clg.Bank.model.Account;
import clg.Bank.model.SavingAccount;
import clg.Bank.repository.Repository;
import clg.Bank.exception.BankException;
import clg.Bank.exception.AccountNotFoundException;
import clg.Bank.exception.InvalidAmountException;
import clg.Bank.util.Validator;

public class BankService
{
    private Repository<Account> repository;

    public BankService(Repository<Account> repository)
    {
        this.repository = repository;
    }

    public Account openAccount(String name, long initialBalance, long minBalance)
    {
        if(name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if(initialBalance < 0 || minBalance < 0)
        {
            throw new IllegalArgumentException("Balances cannot be negative.");
        }

        Account account = new SavingAccount(name, initialBalance, minBalance);
        repository.save(account);
        return account;
    }

    public Account findAccount(String accountNumber)
    {
        Account account = repository.findById(accountNumber);

        if(account == null)
        {
            throw new IllegalArgumentException("Account not found: " + accountNumber);
        }

        return account;
    }

    public void deposit(String accountNumber, long amount)
        throws BankException
    {
        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, long amount)
        throws BankException
    {
        Account account = findAccount(accountNumber);
        account.withdraw(amount);
    }

    public void transfer(String fromId, String toId, long amount)
        throws BankException
    {
        Account from = findAccount(fromId);
        Account to = findAccount(toId);
        from.transfer(to, amount);
    }

    public String statement(String accountNumber)
    {
        return findAccount(accountNumber).toString();
    }

    public java.util.ArrayList<Account> listAccounts()
    {
        return repository.findAll();
    }
}
