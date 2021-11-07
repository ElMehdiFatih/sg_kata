package org.sg.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sg.domaine.Account;
import org.sg.domaine.Balance;
import org.sg.domaine.Statement;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class OperationTrackerTest {
    
    @InjectMocks
    private OperationTracker operationTracker;
    @Mock
    private AccountStatement accountStatement;
    
    @BeforeEach
    public  void init() {
    }
    
    @Test
    public void should_historize_operation() {
        Account account = TestUtilities.createTestAccount();
        Statement statement = TestUtilities.createStatement();
        when(accountStatement.saveStatement(account.getAccountNumber(), statement))
                .thenReturn(statement);
        
        Statement actualStatement = operationTracker.historize(account.getAccountNumber(), statement);
        
        assertThat(actualStatement).isEqualTo(statement);
    }
}