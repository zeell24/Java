package clg.Bank.model;

import clg.Bank.exception.InvalidAmountException;
import clg.Bank.exception.InsufficientFundsException;

public interface Transactable
{
    void deposit(long amount) throws InvalidAmountException;

    boolean withdraw(long amount)
        throws InsufficientFundsException, InvalidAmountException;
}