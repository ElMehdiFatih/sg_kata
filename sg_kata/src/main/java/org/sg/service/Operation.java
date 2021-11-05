package org.sg.service;

import org.sg.domaine.Account;

public abstract class Operation {
    
    
    public abstract Account execute(Account account, double amount);
    
    protected void checkOperationAmount(double amount) {
        if (amount < 0) {
            throw new NumberFormatException("amount must be positive number");
        }
    }
}
