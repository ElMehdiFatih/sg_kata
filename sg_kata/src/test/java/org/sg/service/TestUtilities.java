package org.sg.service;

import org.sg.domaine.Account;
import org.sg.domaine.Balance;
import org.sg.domaine.OperationType;
import org.sg.domaine.Statement;

public class TestUtilities {
    
    private static final long ACCOUNT_NUMBER = 1000012356;
    private static final double BALANCE_AMOUNT = 1000;
    private final static double OPERATION_AMOUNT = 100;
    private static Balance accountInitBalance;
    
    public static Account createTestAccount() {
        accountInitBalance = new Balance(BALANCE_AMOUNT);
        
        return Account.createAccount(ACCOUNT_NUMBER, accountInitBalance);
    }
    
    public static Statement createStatement() {
        return new Statement(OperationType.DEPOSIT, OPERATION_AMOUNT, new Balance(BALANCE_AMOUNT));
    }
}
