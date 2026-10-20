package clg.Bank.model;
import clg.Bank.model.annotation.Id;
import clg.Bank.model.annotation.Positive;
import clg.Bank.exception.InsufficientFundsException;
import clg.Bank.exception.InvalidAmountException;
import clg.Bank.exception.BankException;
import clg.Bank.exception.AccountNotFoundException;

public abstract class Account implements Transactable, InterestBearing
{
    @Id
    private final String accountNumber;

    private String ownerName;
    
    @Positive 
    private long balance;
    
    private boolean active;

    private static int counter = 0;
    

    Account(String ownerName , long balance)
    {
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;

        this.accountNumber = generateAccNum();
    }

    Account(String ownerName)
    {
        this(ownerName, 0);
    }

    private static String generateAccNum()
    {
        counter++;
        String accNo = String.format("AC%04d" , counter);
        return accNo;
    }

    @Override 
    public void deposit(long amount) throws InvalidAmountException
    {
        if(amount <= 0)
        {
            throw new InvalidAmountException("Deposit amount must be positive.");
        }

        balance += amount;
    }
    
    public abstract boolean canwithdraw(long amount);

    public abstract double interestRate();

    @Override
    public boolean withdraw(long amount)
        throws InsufficientFundsException, InvalidAmountException
    {
        if(amount <= 0)
        {
            throw new InvalidAmountException("Withdrawal amount must be positive.");
        }

        if(!canwithdraw(amount))
        {
            long shortfall = amount - balance;
            throw new InsufficientFundsException("Insufficient funds.", shortfall);
        }

        balance -= amount;
        return true;
    }

    public void transfer(Account to, long amount) throws BankException
    {
        try
        {
            if(to == null)
            {
                throw new AccountNotFoundException("Target account not found.");
            }

            this.withdraw(amount);
            try
            {
                to.deposit(amount);
            }
            catch(BankException e)
            {
                this.deposit(amount);
                throw e;
            }
        }
        catch(BankException e)
        {
            throw e;
        }
        finally
        {
            System.out.println("Transfer attempt completed.");
        }
    }

    public String getAccountNumber() 
    { 
        return accountNumber;
    } 
    
    public String getOwnerName()
    {
        return ownerName;
    }
    
    public long getBalance()
    {
        return balance;
    } 
    
    public boolean isActive() 
    { 
        return active; 
    }

    @Override
    public String toString()
    {
        return accountNumber + " | " + ownerName + " | " + balance ;
    }

    @Override
    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;

        if(obj == null || !(obj instanceof Account)) return false;

        Account a = (Account) obj;

        return this.accountNumber.equals(a.accountNumber) &&
                this.ownerName.equals(a.ownerName) &&
                this.balance == a.balance;
    }

    @Override
    public int hashCode()
    {
        return accountNumber.hashCode();
    }
}
