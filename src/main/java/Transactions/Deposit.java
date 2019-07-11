package Transactions;

import Account.Account;
import Exceptions.NegativeTransactionValueException;

import java.math.BigDecimal;

public class Deposit extends Transaction {

    public Deposit(BigDecimal value) throws NegativeTransactionValueException {
        validateTransactionValue(value);
        this.value = value;
    }

    public BigDecimal affectBalance(BigDecimal currentBalance) {
        return currentBalance.add(value);
    }

    public void validateOn(Account account) {
        //a deposit is always accepted
    }

    @Override
    public String toString() {
        return "Deposit for " + value.toString();
    }
}
