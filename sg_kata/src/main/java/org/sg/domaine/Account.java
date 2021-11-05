package org.sg.domaine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private long accountNumber;
    private Balance balance;
    private List<Statement> statements;
    
    public Account(long accountNumber, Balance balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        statements = new ArrayList<>();
    }
    
    public Balance getBalance() {
        return balance;
    }
    
    public void updateBalance(Balance updatedBalance) {
        this.balance = updatedBalance;
    }
    
    public List<Statement> getStatements() {
        return Collections.unmodifiableList(statements);
    }
    
    public void addStatement(Statement statement) {
        statements.add(statement);
    }
}
