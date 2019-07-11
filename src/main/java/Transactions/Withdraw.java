package Transactions;

import Account.Account;
import Exceptions.InsufficientBalanceException;
import Exceptions.NegativeTransactionValueException;

import java.math.BigDecimal;

public class Withdraw extends Transaction {

    public Withdraw(BigDecimal value) throws NegativeTransactionValueException {
        validateTransactionValue(value);
        this.value = value;
    }

    public BigDecimal affectBalance(BigDecimal currentBalance) {
        return currentBalance.subtract(value);
    }

    @Override
    public void validateOn(Account account) throws InsufficientBalanceException {
        BigDecimal balance = account.balance();
        if (balance.compareTo(this.value) == -1) {
            throw new InsufficientBalanceException();
        }
    }

    @Override
    public String toString() {
        return "Withdraw for " + value.toString();
    }

}
