package org.sg.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sg.domaine.Account;
import org.sg.domaine.Balance;
import org.sg.domaine.OperationType;
import org.sg.domaine.Statement;

import static org.assertj.core.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class OperationTrackerTest {
    
    private static final long ACCOUNT_NUMBER = 10000123;
    @InjectMocks
    private OperationTracker operationTracker;
    
    @BeforeEach
    public  void init() throws Exception {
    }
    
    @Test
    public void should_historize_operation() {
        Balance accountInitBalance = new Balance(1000);
        Account account = new Account(ACCOUNT_NUMBER ,accountInitBalance);
        Statement statement = new Statement(OperationType.DEPOSIT, accountInitBalance.getValue(), account.getBalance());
    
        operationTracker.historize(account, statement);
        
        assertThat(account.getStatements()).contains(statement);
    }
}