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
import org.sg.exception.WithdrawAmountException;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DepositOperationTest {

    private final static double DEPOSIT_AMOUNT =100;
    private final static double INVALID_DEPOSIT_AMOUNT =-1;
    private final static long ACCOUNT_NUMBER = 000012356;
    private static Balance ACCOUNT_INIT_BALANCE ;
    private Operation depositOperation;
    @Mock
    private Tracker tracker;
    
    @BeforeEach
    public  void init() throws Exception {
        depositOperation = new DepositOperation(tracker);
        ACCOUNT_INIT_BALANCE = new Balance(1000);
    }
    
    @Test
    public void should_deposit_money_in_account() {
        //GIVEN
        Account account = new Account(ACCOUNT_NUMBER ,ACCOUNT_INIT_BALANCE);
        Balance accountBalance = account.getBalance();
        double expectedAccountBalance = DEPOSIT_AMOUNT + accountBalance.getValue();
    
        //WHEN
        Account actualAccount = depositOperation.execute(account, DEPOSIT_AMOUNT);
        //THEN
        Balance actualAccountBalance = actualAccount.getBalance();
    
        assertThat(expectedAccountBalance).isEqualTo(actualAccountBalance.getValue());
    }
    
    @Test
    public void should_throw_exception_when_account_operation_amount_is_not_positive() {
        Account account = new Account(ACCOUNT_NUMBER ,ACCOUNT_INIT_BALANCE);
        
        assertThatThrownBy( () -> depositOperation.execute(account, INVALID_DEPOSIT_AMOUNT)).isInstanceOf(NumberFormatException.class);
    
    }

}