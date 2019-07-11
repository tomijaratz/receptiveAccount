import Exceptions.NegativeTransactionValueException;
import Transactions.Withdraw;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class WithdrawTest {

    @Test(expected = NegativeTransactionValueException.class)
    public void canNotCreateWithdrawWithNegativeValue() throws NegativeTransactionValueException {
        Withdraw withdraw = new Withdraw(BigDecimal.valueOf(-1));
    }

    @Test
    public void withdrawKnowsTheTransactionValue() throws NegativeTransactionValueException {
        Withdraw withdraw = new Withdraw(BigDecimal.TEN);

        assertEquals(withdraw.getValue(), BigDecimal.TEN);
    }

    @Test
    public void withdrawAffectsBalanceCorrectly() throws NegativeTransactionValueException {
        Withdraw withdraw = new Withdraw(BigDecimal.TEN);

        assertEquals(withdraw.affectBalance(BigDecimal.valueOf(20)), BigDecimal.TEN);
    }
}
