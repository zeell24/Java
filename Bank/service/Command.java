package clg.Bank.service;

import clg.Bank.model.TransactionType;

public record Command(TransactionType type, String accountNumber, long amount) {}
