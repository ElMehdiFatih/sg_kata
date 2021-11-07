package org.sg.service;

import org.sg.domaine.*;

public class DepositOperation extends Operation {
    
    private Tracker<Statement> tracker;
    
    public Account execute(Account account, double depositAmount) {
        checkOperationAmount(depositAmount);
        Balance accountBalance = account.getBalance();
        Balance newAccountBalance = deposit(accountBalance, depositAmount);
        account.updateBalance(newAccountBalance);
        historizeOperation(account, depositAmount);
        
        return account;
    }
    
    private Balance deposit(Balance accountBalance, double depositAmount) {
        double updatedBalanceValue = accountBalance.getValue() + depositAmount;
        
        return new Balance(updatedBalanceValue);
    }
    
    private Statement historizeOperation(Account account, double depositAmount) {
        Statement statement = new Statement(OperationType.DEPOSIT, depositAmount, account.getBalance());
        return tracker.historize(account.getAccountNumber(), statement);
    }
    
    public DepositOperation(Tracker<Statement> tracker) {
        this.tracker = tracker;
    }
}
