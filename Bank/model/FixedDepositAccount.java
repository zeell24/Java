package clg.Bank.model;

public class FixedDepositAccount extends Account implements Premium
{
    public FixedDepositAccount(String ownerName , long balance)
    {
        super(ownerName, balance);
    }

    @Override 
    public double interestRate()
    {
        return 7.0;
    }

    @Override 
    public boolean canwithdraw(long amount)
    {
        return false;
    }     
}
