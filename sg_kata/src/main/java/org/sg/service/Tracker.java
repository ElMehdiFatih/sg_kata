package org.sg.service;

import org.sg.domaine.Account;
import org.sg.domaine.OperationType;
import org.sg.domaine.Statement;


public interface Tracker {
     void historize(Account account, Statement statement);
}
