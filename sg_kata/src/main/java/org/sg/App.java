package org.sg;

import org.sg.domaine.Account;
import org.sg.domaine.Balance;
import org.sg.service.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //ioc framework (spring) can be more flexible for instantiation
        Tracker tracker = new OperationTracker();
        Operation depositOperation = new DepositOperation(tracker);
        Operation withdrawOperation = new WithdrawOperation(tracker);
    
        Account account = new Account(123000, new Balance(1000));
        //deposit 100 euros
        depositOperation.execute(account, 100);
        //deposit 500 euros
        withdrawOperation.execute(account, 500);
        
        account.getStatements().forEach(System.out::println);
    }
}
