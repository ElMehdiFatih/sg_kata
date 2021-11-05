package org.sg.domaine;

import org.sg.service.Operation;

import java.time.LocalDateTime;

public class Statement {
    //id statement required for real program
    private OperationType operation;
    private LocalDateTime date;
    private double amount;
    private Balance balance;
    
    public Statement(OperationType operation, double amount, Balance balance) {
        
        this.operation = operation;
        this.date = LocalDateTime.now();
        this.amount = amount;
        this.balance = balance;
    }
    
    @Override
    public String toString() {
        return "Statement{" +
                "operation=" + operation +
                ", date=" + date +
                ", amount=" + amount +
                ", Account balance=" + balance.getValue() +
                '}';
    }
}
