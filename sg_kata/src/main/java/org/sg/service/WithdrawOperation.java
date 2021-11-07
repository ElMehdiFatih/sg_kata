package org.sg.service;

import org.sg.domaine.*;
import org.sg.exception.WithdrawAmountException;

public class WithdrawOperation extends Operation {
    
    private Tracker<Statement> tracker;
    
    @Override
    public Account execute(Account account, double withdrawAmount) {
        checkOperationAmount(withdrawAmount);
        checkAccountBalance(account, withdrawAmount);
        Balance accountBalance = account.getBalance();
        Balance updatedBalance = withdraw(accountBalance, withdrawAmount);
        account.updateBalance(updatedBalance);
        historizeOperation(account,withdrawAmount);
        
        return account;
    }
    
    private Balance withdraw(Balance accountBalance, double withdrawAmount) {
        double updatedBalanceValue = accountBalance.getValue() - withdrawAmount;
        
        return new Balance(updatedBalanceValue);
    }
    
    
    private void checkAccountBalance(Account account, double amount) {
        Balance accountBalance = account.getBalance();
        if (amount > accountBalance.getValue()) {
            throw new WithdrawAmountException("error : Operation Amount is higher than the account Balance");
        }
    }
    
    private Statement historizeOperation(Account account, double withdrawAmount) {
        Statement statement = new Statement(OperationType.WITHDRAW, withdrawAmount, account.getBalance());
        return tracker.historize(account.getAccountNumber(), statement);
    }
    
    public WithdrawOperation(Tracker<Statement> tracker) {
        this.tracker = tracker;
    }
}
