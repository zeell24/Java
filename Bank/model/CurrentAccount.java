package clg.Bank.model;

public class CurrentAccount extends Account
{
    private long overdraftLimit;

    public CurrentAccount(String ownerName , long balance , long overdraftLimit)
    {
        super(ownerName, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override 
    public double interestRate()
    {
        return 0.0;
    }

    @Override 
    public boolean canwithdraw(long amount)
    {
        return getBalance()-amount >= overdraftLimit;
    }    
}
