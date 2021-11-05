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
    private final static double HIGH_WITHDRAW_AMOUNT =100000;
    private final static long ACCOUNT_NUMBER = 000012356;
    private static Balance ACCOUNT_INIT_BALANCE ;
    private Operation depositOperation;
    @Mock
    private OperationTracker tracker;
    
    @BeforeEach
    public  void init() throws Exception {
        depositOperation = new WithdrawOperation(tracker);
        ACCOUNT_INIT_BALANCE = new Balance(1000);
    }
    
    @Test
    public void should_withdraw_money_from_account() {
        //GIVEN
        Balance accountBalance = new Balance(1000);
        Account account = new Account(ACCOUNT_NUMBER ,accountBalance);
        double expectedAccountBalance = accountBalance.getValue() - WITHDRAW_AMOUNT;
        
        //WHEN
        Account actualAccount = depositOperation.execute(account, WITHDRAW_AMOUNT);
        //THEN
        Balance actualAccountBalance = actualAccount.getBalance();
        
        assertThat(expectedAccountBalance).isEqualTo(actualAccountBalance.getValue());
    }
    
    @Test
    public void should_throw_exception_when_account_balance_is_low() {
        //GIVEN
        Account account = new Account(ACCOUNT_NUMBER ,ACCOUNT_INIT_BALANCE);
        Balance accountBalance = account.getBalance();
        //WHEN
        assertThatThrownBy( () -> depositOperation.execute(account, HIGH_WITHDRAW_AMOUNT)).isInstanceOf(WithdrawAmountException.class);
        
    }
}