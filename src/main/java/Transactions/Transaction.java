package Transactions;

import Account.Account;
import Exceptions.InsufficientBalanceException;
import Exceptions.NegativeTransactionValueException;

import java.math.BigDecimal;

public abstract class Transaction {

    BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public abstract BigDecimal affectBalance(BigDecimal currentBalance);

    void validateTransactionValue(BigDecimal value) throws NegativeTransactionValueException {
        if (value.compareTo(BigDecimal.ZERO) == -1) {
            throw new NegativeTransactionValueException();
        }
    }

    public abstract void validateOn(Account anAccount) throws InsufficientBalanceException;
}
