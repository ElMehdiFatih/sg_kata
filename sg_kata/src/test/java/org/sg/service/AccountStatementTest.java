package org.sg.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sg.domaine.Account;
import org.sg.domaine.Statement;
import org.sg.persistance.StatementPersistence;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AccountStatementTest {
   
    @Mock
    private StatementPersistence statementPersistence;
    
    @InjectMocks
    private AccountStatement accountStatement;
    
    
    @Test
    void should_get_all_account_statements() {
        //GIVEN
        Account account = TestUtilities.createTestAccount();
        Statement statement = TestUtilities.createStatement();
        
        Mockito.when(statementPersistence.getStatements(account.getAccountNumber()))
                .thenReturn(Collections.singletonList(statement));
        //WHEN
        List<Statement> actualStatements = accountStatement.getAccountStatements(account);
        //THEN
        assertThat(actualStatements).contains(statement);
    }
    
    @Test
    void should_save_account_statement() {
        //GIVEN
        Account account = TestUtilities.createTestAccount();
        Statement statement = TestUtilities.createStatement();
        
        Mockito.when(statementPersistence.saveStatement(account.getAccountNumber(), statement))
                .thenReturn(statement);
        //WHEN
        Statement actualStatements = accountStatement.saveStatement(account.getAccountNumber(), statement);
        //THEN
        assertThat(actualStatements).isEqualTo(statement);
    }
    
    
}
