package org.sg.service;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sg.domaine.Account;
import org.sg.domaine.Balance;
import org.sg.domaine.Statement;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DepositOperationTest {
    
    private final static double DEPOSIT_AMOUNT = 100;
    private final static double INVALID_DEPOSIT_AMOUNT = -1;
    
    private Operation depositOperation;
    @Mock
    private Tracker<Statement> tracker;
    
    @BeforeEach
    public void init() {
        depositOperation = new DepositOperation(tracker);
    }
    
    @Test
    public void should_deposit_money_in_account() {
        //GIVEN
        Account account = TestUtilities.createTestAccount();
        Balance accountBalance = account.getBalance();
        double expectedAccountBalance = DEPOSIT_AMOUNT + accountBalance.getValue();
        
        //WHEN
        Account actualAccount = depositOperation.execute(account, DEPOSIT_AMOUNT);
        //THEN
        Balance actualAccountBalance = actualAccount.getBalance();
        
        assertThat(actualAccountBalance.getValue()).isEqualTo(expectedAccountBalance);
    }
    
    @Test
    public void should_throw_exception_when_account_operation_amount_is_not_positive() {
        Account account = TestUtilities.createTestAccount();
        
        assertThatThrownBy(() -> depositOperation.execute(account, INVALID_DEPOSIT_AMOUNT))
                .isInstanceOf(NumberFormatException.class);
        
    }
    
}