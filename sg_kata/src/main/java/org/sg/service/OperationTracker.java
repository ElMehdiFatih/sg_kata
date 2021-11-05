package org.sg.service;

import org.sg.domaine.Account;
import org.sg.domaine.Statement;

public class OperationTracker implements Tracker{
    @Override
    public void historize(Account account, Statement statement) {
        account.addStatement(statement);
    }
}
