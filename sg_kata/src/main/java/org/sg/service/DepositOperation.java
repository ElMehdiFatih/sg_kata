package org.sg.service;

import org.sg.domaine.*;

public class DepositOperation extends Operation {
    
    private Tracker tracker;
    
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
    
    private void historizeOperation(Account account, double depositAmount) {
        Statement statement = new Statement(OperationType.DEPOSIT, depositAmount, account.getBalance());
        tracker.historize(account, statement);
    }
    
    public DepositOperation(Tracker tracker) {
        this.tracker = tracker;
    }
}
