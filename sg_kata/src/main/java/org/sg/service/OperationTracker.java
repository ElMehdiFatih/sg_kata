package org.sg.service;

import org.sg.domaine.Account;
import org.sg.domaine.Statement;

public class OperationTracker implements Tracker<Statement> {
    
    private AccountStatement accountStatement;
    
    public OperationTracker(AccountStatement accountStatement) {
        this.accountStatement = accountStatement;
    }
    
    @Override
    public Statement historize(long accountNumber, Statement statement) {
        return accountStatement.saveStatement(accountNumber, statement);
    }
}
