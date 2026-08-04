package clg.Bank;

public class Account {
    
    private final String accountNumber;
    private String ownerName;
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

    public void Deposit(int amount)
    {
        if(amount > 0)
            balance += amount;
    }
    
    public boolean Withdraw(long amount)
    {
        if(amount <= balance && amount > 0)
        {
            balance -= amount;
            return true;
        }
        return false;
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

        return this.accountNumber == a.accountNumber &&
                this.ownerName == a.ownerName &&
                this.balance == a.balance;
    }

    @Override
    public int hashCode()
    {
        return accountNumber.hashCode();
    }
}
