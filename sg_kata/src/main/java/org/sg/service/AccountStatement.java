package org.sg.service;

import org.sg.domaine.Account;
import org.sg.domaine.Statement;
import org.sg.persistance.StatementPersistence;

import java.util.List;

public class AccountStatement {
    
    private StatementPersistence statementPersistence ;
    
    public AccountStatement(StatementPersistence statementPersistence) {
        this.statementPersistence = statementPersistence;
    }
    
    public List<Statement> getAccountStatements(Account account) {
        return statementPersistence.getStatements(account.getAccountNumber());
    }
    
    public Statement saveStatement(long accountNumber, Statement statement) {
        return statementPersistence.saveStatement(accountNumber, statement);
    }
}
