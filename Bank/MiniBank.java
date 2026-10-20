// javac -d classFiles MiniBank.java model\*.java model\annotation\*.java util\*.java service\*.java
// java -cp classFiles clg.Bank.MiniBank

package clg.Bank;
import java.util.Scanner;

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

import static clg.Bank.util.Validator.isValidMobile;

record BankInfo(String name , String branch){}

class BankSession implements AutoCloseable
{
    @Override
    public void close()
    {
        System.out.println("Bank session closed.");
    }
}

public class MiniBank {

    enum MenuOption
    {
        OPEN_ACCOUNT ,
        DEPOSIT , 
        WITHDRAW ,
        TRANSFER ,
        EXIT
    }

    public static void main(String args[])
    {
        Scanner cin =  new Scanner(System.in);

        // P4: Check Validator

        System.out.println("\n-------- Checking Validators --------");

        System.out.println("Mobile 9988776655 : "
                + (Validator.isValidMobile("9988776655") ? "Valid" : "Invalid"));

        System.out.println("Mobile 998877665 : "
                + (isValidMobile("998877665") ? "Valid" : "Invalid"));

        System.out.println("Email abc@gmail.com : "
                + (Validator.isValidEmail("abc@gmail.com") ? "Valid" : "Invalid"));

        System.out.println("Email abc@gmail : "
                + (Validator.isValidEmail("abc@gmail") ? "Valid" : "Invalid"));

        System.out.println("PAN BCDPA5678K : "
                + (Validator.isValidPan("BCDPA5678K") ? "Valid" : "Invalid"));

        System.out.println("PAN BCD5678 : "
                + (Validator.isValidPan("BCD5678") ? "Valid" : "Invalid"));

        System.out.println("IFSC HDFC0005678 : "
                + (Validator.isValidIfsc("HDFC0005678") ? "Valid" : "Invalid"));

        System.out.println("IFSC HDFC567 : "
                + (Validator.isValidIfsc("HDFC567") ? "Valid" : "Invalid"));

        //p2
        // Account acc[] = new Account[2];

        // acc[0] = new Account("Zeel", 25000);
        // acc[1] = new Account("Diya");

        // System.out.println("Withdrawing 200 from acc[0] ...");
        // acc[0].withdraw(200);
        // System.out.println("Depositing 1000 into acc[1] ...");
        // acc[1].deposit(1000);

        // System.out.println("Checking...");
        // for(Account account : acc)
        // {
        //     //p2
        //     // System.out.println("---");
        //     // System.out.println("Account number : " + account.getAccountNumber());
        //     // System.out.println("Acount holder name : " + account.getOwnerName());
        //     // System.out.println("Account Balance : " + account.getBalance());

        //     //p3
        //     System.out.println(account);
        // }

        //p3
        // System.out.println("\nChecking if acc[0] and acc[1] is equal : " + acc[0].equals(acc[1]));

        // if(acc[0] instanceof Account)
        //     System.out.println("acc[0] is an Account");

        //p5
        Account[] accounts = {
            new SavingAccount("Zeel", 50000, 5000),
            new CurrentAccount("xyz", 20500, 5000),
            new FixedDepositAccount("abc", 30000)
        };


        System.out.println("----------------");
        for(Account acc : accounts)
        {
            System.out.println(acc);
            double rate = acc.interestRate();
            System.out.println("Interest rate: " + rate);


            if (acc instanceof FixedDepositAccount)
            {
                System.out.println("Note: This is a Fixed Deposit account.");
            }

            if(acc instanceof Premium)
            {
                System.out.println("This is a Premium Account.");
            }

            System.out.println("");
        }

        // P6: Withdraw Rule

        WithdrawRule rule = new WithdrawRule()
        {
            public boolean allow(Account account, long amount)
            {
                return account.getBalance() >= amount;
            }
        };

        System.out.println("-------- Withdraw Rules --------");

        System.out.println(
            "Anonymous Rule : " +
            rule.allow(accounts[0], 5000)
        );

        WithdrawRule lambdaRule =
            (account, amount) -> account.getBalance() >= amount;

        System.out.println(
            "Lambda Rule : " +
            lambdaRule.allow(accounts[1], 25000)
        );

        System.out.println("----------------");

        // P7: Annotation Validation
        System.out.println("-------- Annotation Validation --------");

        String[] errors = AnnotationValidator.validate(
            new SavingAccount("Test", -500, 100)
        );

        for(String error : errors)
        {
            System.out.println(error);
        }

        System.out.println("----------------");

        // P8: Exception Handling
        //transfer testing
        try
        {
            accounts[0].transfer(accounts[1], 5000);
            System.out.println("Transfer successful.");
        }
        catch(BankException e)
        {
            System.out.println("Transfer failed: " + e.getMessage());
        }

        //acc not found test
        try
        {
            accounts[0].transfer(null, 1000);
        }
        catch(AccountNotFoundException e)
        {
            System.out.println("Transfer failed: " + e.getMessage());
        }
        catch(BankException e)
        {
            System.out.println("Bank error: " + e.getMessage());
        }
        System.out.println("-------- Exception Handling --------");

        try(BankSession session = new BankSession())
        {
            try
            {
                accounts[0].withdraw(100000);
            }
            catch(InsufficientFundsException e)
            {
                System.out.println("Withdrawal failed: " + e.getMessage());
                System.out.println("Shortfall: " + e.getShortfall());
            }
            catch(InvalidAmountException e)
            {
                System.out.println("Invalid amount: " + e.getMessage());
            }
            finally
            {
                System.out.println("Withdrawal operation completed.");
            }

            try
            {
                accounts[0].deposit(-100);
            }
            catch(InvalidAmountException e)
            {
                System.out.println("Deposit failed: " + e.getMessage());
            }
        }
        catch(Exception e)
        {
            System.out.println("Bank session error: " + e.getMessage());
        }

        System.out.println("----------------");

        //p1
        int choice = 0;
        System.out.println(" ");
        System.out.println("-------- Virtual Mini Bank! --------");
        System.out.println(" ");
        
        while (choice != 5)
        {
            System.out.println("Menu : ");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.println("Enter your choice : ");
            choice = cin.nextInt();
            
            switch (choice) {
                case 1 -> System.out.println("Open Acocound in pending...");  
                case 2 -> System.out.println("Deposit in pending...");
                case 3 -> System.out.println("Withdraw in pending...");
                case 4 -> System.out.println("Transfer in pending...");
                case 5 ->{
                    System.out.println("Closing the bank...");
                }
                default -> System.out.println("Enter correct choice");
            }
            System.out.println("---------------");

        }
        cin.close();
    }
}
