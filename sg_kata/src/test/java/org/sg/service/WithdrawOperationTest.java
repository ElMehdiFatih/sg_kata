package org.sg.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sg.domaine.Account;
import org.sg.domaine.Balance;
import org.sg.domaine.Statement;
import org.sg.exception.WithdrawAmountException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class WithdrawOperationTest {
    
    private final static double WITHDRAW_AMOUNT =100;
    private final static double HIGH_WITHDRAW_AMOUNT =1000000;
    
    private Operation depositOperation;
    @Mock
    private OperationTracker tracker;
    
    @BeforeEach
    public  void init() {
        depositOperation = new WithdrawOperation(tracker);
    }
    
    @Test
    public void should_withdraw_money_from_account() {
        //GIVEN
        Account account = TestUtilities.createTestAccount();
        Balance accountBalance = account.getBalance();
        double expectedAccountBalance = accountBalance.getValue() - WITHDRAW_AMOUNT;
        
        //WHEN
        Account actualAccount = depositOperation.execute(account, WITHDRAW_AMOUNT);
        //THEN
        Balance actualAccountBalance = actualAccount.getBalance();
        
        assertThat(actualAccountBalance.getValue()).isEqualTo(expectedAccountBalance);
    }
    
    @Test
    public void should_throw_exception_when_account_balance_is_low() {
        //GIVEN
        Account account =  TestUtilities.createTestAccount();
        //WHEN
        assertThatThrownBy( () -> depositOperation.execute(account, HIGH_WITHDRAW_AMOUNT)).isInstanceOf(WithdrawAmountException.class);
        
    }
}