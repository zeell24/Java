package clg.Bank.model;

public class SavingAccount extends Account
{
    private long minBalance;

    public SavingAccount(String ownerName , long balance , long minBalance)
    {
        super(ownerName, balance);
        this.minBalance = minBalance;
    }

    @Override 
    public double interestRate()
    {
        return 4.0;
    }

    @Override 
    public boolean canwithdraw(long amount)
    {
        return getBalance()-amount >= minBalance;
    }
}
