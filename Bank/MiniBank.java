// javac -d classFiles MiniBank.java AccountStore.java model/*.java model/annotation/*.java util/*.java service/*.java exception/*.java thread/*.java persistence/*.java repository/*.java
// java -cp classFiles clg.Bank.MiniBank
package clg.Bank;

import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;

import clg.Bank.model.Account;
import clg.Bank.model.CurrentAccount;
import clg.Bank.model.FixedDepositAccount;
import clg.Bank.model.Premium;
import clg.Bank.model.SavingAccount;
import clg.Bank.model.WithdrawRule;
import clg.Bank.util.Validator;
import clg.Bank.util.AnnotationValidator;
import clg.Bank.exception.BankException;
import clg.Bank.exception.InsufficientFundsException;
import clg.Bank.exception.AccountNotFoundException;
import clg.Bank.exception.InvalidAmountException;
import clg.Bank.persistence.StatePersister;
import clg.Bank.persistence.TransactionLog;
import clg.Bank.persistence.ReportGenerator;
import clg.Bank.thread.TransactionProcessor;
import clg.Bank.repository.Repository;
import clg.Bank.service.BankService;

record BankInfo(String name, String branch)
{
}

class BankSession implements AutoCloseable
{
    @Override
    public void close()
    {
        System.out.println("Bank session closed.");
    }
}

public class MiniBank
{
    private static Account findAccount(AccountStore store, String id)
    {
        Account account = store.get(id);

        if(account == null)
        {
            throw new IllegalArgumentException("Account not found: " + id);
        }

        return account;
    }

    public static void main(String[] args)
    {
        Scanner cin = new Scanner(System.in);
        Path stateFile = Paths.get("accounts.dat");
        Path logsFolder = Paths.get("logs");
        Path reportFile = Paths.get("report.txt");
        Path transactionFile = logsFolder.resolve("transactions.txt");

        try
        {
            Files.createDirectories(logsFolder);
        }
        catch(IOException e)
        {
            System.out.println("Could not create logs folder: " + e.getMessage());
        }

        System.out.println("-------- Checking Validators --------");
        System.out.println("Mobile 9988776655 : " +
                (Validator.isValidMobile("9988776655") ? "Valid" : "Invalid"));
        System.out.println("Email abc@gmail.com : " +
                (Validator.isValidEmail("abc@gmail.com") ? "Valid" : "Invalid"));
        System.out.println("PAN BCDPA5678K : " +
                (Validator.isValidPan("BCDPA5678K") ? "Valid" : "Invalid"));
        System.out.println("IFSC HDFC0005678 : " +
                (Validator.isValidIfsc("HDFC0005678") ? "Valid" : "Invalid"));

        // Load saved accounts if a saved file exists.
        Account[] loadedAccounts = new Account[0];

        if(Files.exists(stateFile))
        {
            loadedAccounts = StatePersister.load(stateFile);
        }

        AccountStore store = new AccountStore();
        TransactionProcessor processor = new TransactionProcessor();
        Repository<Account> repository = new Repository<>();
        BankService bankService = new BankService(repository);

        if(loadedAccounts.length > 0)
        {
            for(Account account : loadedAccounts)
            {
                store.add(account);
                processor.addAccount(account);
                repository.save(account);
            }

            System.out.println("Loaded " + loadedAccounts.length + " saved account(s).");
        }
        else
        {
            Account[] initialAccounts = {
                new SavingAccount("Zeel", 50000, 5000),
                new CurrentAccount("xyz", 20500, 5000),
                new FixedDepositAccount("abc", 30000)
            };

            for(Account account : initialAccounts)
            {
                store.add(account);
                processor.addAccount(account);
                repository.save(account);
            }

            store.addTransaction("Initial accounts added");
        }

        System.out.println("-------- Accounts Sorted by Balance --------");
        for(Account account : store.sortedByBalance())
        {
            System.out.println(account);
        }

        System.out.println("Shared accounts: " + processor.getAccounts().size());

        System.out.println("-------- Account Information --------");
        for(Account account : store.getAll())
        {
            System.out.println(account);
            System.out.println("Interest rate: " + account.interestRate());

            if(account instanceof FixedDepositAccount)
            {
                System.out.println("Note: This is a Fixed Deposit account.");
            }

            if(account instanceof Premium)
            {
                System.out.println("This is a Premium Account.");
            }

            System.out.println();
        }

        WithdrawRule rule = (account, amount) -> account.getBalance() >= amount;
        System.out.println("-------- Withdraw Rules --------");
        Account firstAccount = store.getAll().isEmpty() ? null : store.getAll().get(0);

        if(firstAccount != null)
        {
            System.out.println("Lambda Rule: " + rule.allow(firstAccount, 5000));
        }

        System.out.println("-------- Annotation Validation --------");
        for(String error : AnnotationValidator.validate(
                new SavingAccount("Test", -500, 100)))
        {
            System.out.println(error);
        }

        int choice = 0;

        System.out.println("\n-------- Virtual Mini Bank! --------");

        while(choice != 7)
        {
            System.out.println("\nMenu:");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. List Accounts");
            System.out.println("6. Generate Report");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try
            {
                choice = Integer.parseInt(cin.nextLine().trim());

                switch(choice)
                {
                    case 1 ->
                    {
                        System.out.print("Owner name: ");
                        String name = cin.nextLine().trim();

                        System.out.print("Initial balance: ");
                        long balance = Long.parseLong(cin.nextLine().trim());

                        System.out.print("Minimum balance: ");
                        long minimum = Long.parseLong(cin.nextLine().trim());

                        Account account = bankService.openAccount(name, balance, minimum);
                        store.add(account);
                        processor.addAccount(account);
                        store.addTransaction("OPEN " + account.getAccountNumber());

                        System.out.println("Account opened: " + account);
                    }

                    case 2 ->
                    {
                        System.out.print("Account number: ");
                        String id = cin.nextLine().trim();

                        System.out.print("Deposit amount: ");
                        long amount = Long.parseLong(cin.nextLine().trim());

                        Account account = findAccount(store, id);
                        bankService.deposit(id, amount);

                        store.addTransaction("DEPOSIT " + id + " " + amount);
                        TransactionLog.append(transactionFile,
                                "DEPOSIT " + id + " " + amount);

                        System.out.println("Deposit successful: " + account);
                    }

                    case 3 ->
                    {
                        System.out.print("Account number: ");
                        String id = cin.nextLine().trim();

                        System.out.print("Withdrawal amount: ");
                        long amount = Long.parseLong(cin.nextLine().trim());

                        Account account = findAccount(store, id);
                        bankService.withdraw(id, amount);

                        store.addTransaction("WITHDRAW " + id + " " + amount);
                        TransactionLog.append(transactionFile,
                                "WITHDRAW " + id + " " + amount);

                        System.out.println("Withdrawal successful: " + account);
                    }

                    case 4 ->
                    {
                        System.out.print("From account number: ");
                        String fromId = cin.nextLine().trim();

                        System.out.print("To account number: ");
                        String toId = cin.nextLine().trim();

                        System.out.print("Transfer amount: ");
                        long amount = Long.parseLong(cin.nextLine().trim());

                        findAccount(store, fromId);
                        findAccount(store, toId);

                        bankService.transfer(fromId, toId, amount);

                        store.addTransaction(
                                "TRANSFER " + fromId + " " + toId + " " + amount);
                        TransactionLog.append(transactionFile,
                                "WITHDRAW " + fromId + " " + amount);
                        TransactionLog.append(transactionFile,
                                "DEPOSIT " + toId + " " + amount);

                        System.out.println("Transfer successful.");
                    }

                    case 5 ->
                    {
                        System.out.println("-------- All Accounts --------");
                        for(Account account : store.getAll())
                        {
                            System.out.println(account);
                        }
                    }

                    case 6 ->
                    {
                        ReportGenerator.generate(logsFolder, reportFile);

                        if(Files.exists(reportFile))
                        {
                            for(String line : Files.readAllLines(reportFile))
                            {
                                System.out.println(line);
                            }
                        }
                    }

                    case 7 -> System.out.println("Closing the bank...");

                    default -> System.out.println("Enter a choice from 1 to 7.");
                }
            }
            catch(NumberFormatException e)
            {
                System.out.println("Please enter a valid number.");
            }
            catch(InvalidAmountException e)
            {
                System.out.println("Invalid amount: " + e.getMessage());
            }
            catch(InsufficientFundsException e)
            {
                System.out.println("Withdrawal failed: " + e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());
            }
            catch(BankException e)
            {
                System.out.println("Bank operation failed: " + e.getMessage());
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
            catch(IOException e)
            {
                System.out.println("File operation failed: " + e.getMessage());
            }

            System.out.println("---------------");
        }

        StatePersister.save(store.getAll().toArray(new Account[0]), stateFile);

        System.out.println("-------- Saved Accounts --------");
        for(Account account : store.getAll())
        {
            System.out.println(account);
        }

        ReportGenerator.generate(logsFolder, reportFile);

        System.out.println("-------- End-of-Day Report --------");
        try
        {
            for(String line : Files.readAllLines(reportFile))
            {
                System.out.println(line);
            }
        }
        catch(IOException e)
        {
            System.out.println("Could not read report: " + e.getMessage());
        }

        try
        {
            processor.stop();
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
            System.out.println("Processor shutdown interrupted.");
        }

        cin.close();
    }
}