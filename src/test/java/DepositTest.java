import Exceptions.NegativeTransactionValueException;
import Transactions.Deposit;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DepositTest {

    @Test(expected = NegativeTransactionValueException.class)
    public void canNotCreateDepositWithNegativeValue() throws NegativeTransactionValueException {
        Deposit deposit = new Deposit(BigDecimal.valueOf(-1));
    }

    @Test
    public void depositKnowsTheTransactionValue() throws NegativeTransactionValueException {
        Deposit deposit = new Deposit(BigDecimal.TEN);

        assertEquals(deposit.getValue(), BigDecimal.TEN);
    }

    @Test
    public void depositAffectsBalanceCorrectly() throws NegativeTransactionValueException {
        Deposit deposit = new Deposit(BigDecimal.TEN);

        assertEquals(deposit.affectBalance(BigDecimal.valueOf(20)), BigDecimal.valueOf(30));
    }
}
