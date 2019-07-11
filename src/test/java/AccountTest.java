import Account.Account;
import Exceptions.InsufficientBalanceException;
import Exceptions.InvalidTransactionException;
import Transactions.Deposit;
import Transactions.Withdraw;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void AccountHaveZeroAsBalanceWhenCreated() {
        Account account = new Account();

        assertEquals(account.balance(), BigDecimal.ZERO);

    }

    @Test
    public void DepositIncreasesBalanceOnTransactionValue() throws InvalidTransactionException {
        Account account = new Account();
        Deposit deposit = new Deposit(BigDecimal.TEN);

        account.register(deposit);

        assertEquals(account.balance(), BigDecimal.TEN);

    }

    @Test
    public void WithdrawDecreasesBalanceOnTransactionValue() throws InvalidTransactionException {
        Account account = new Account();

        Deposit deposit = new Deposit(BigDecimal.TEN);
        Withdraw withdraw = new Withdraw(BigDecimal.TEN);

        account.register(deposit);
        account.register(withdraw);

        assertEquals(account.balance(), BigDecimal.ZERO);

    }

    @Test(expected = InsufficientBalanceException.class)
    public void CanNotRegisterWithdrawWhenBalanceIsInsufficient() throws InvalidTransactionException {
        Account account = new Account();

        Deposit deposit = new Deposit(BigDecimal.ONE);
        Withdraw withdraw = new Withdraw(BigDecimal.TEN);

        account.register(deposit);
        account.register(withdraw);

    }

    @Test()
    public void AccountKnowsRegisteredTransactions() throws InvalidTransactionException {
        Account account = new Account();

        Deposit deposit = new Deposit(BigDecimal.ONE);

        account.register(deposit);
        assertEquals(account.hasRegistered(deposit), true);

    }

    @Test()
    public void AccountShowRegisteredTransactionsCorrectly() throws InvalidTransactionException {
        Account account = new Account();

        Deposit deposit = new Deposit(BigDecimal.TEN);
        Withdraw withdraw = new Withdraw(BigDecimal.ONE);

        account.register(deposit);
        account.register(withdraw);

        ArrayList<String> expectedMovements = new ArrayList<>();
        expectedMovements.add("Deposit for 10");
        expectedMovements.add("Withdraw for 1");

        assertEquals(account.getMovements(), expectedMovements);

    }

}
