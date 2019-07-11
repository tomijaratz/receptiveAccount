package Exceptions;


import static Utils.Constants.INSUFFICIENT_BALANCE_MESSAGE;

public class InsufficientBalanceException extends InvalidTransactionException {

    public InsufficientBalanceException() {
        super(INSUFFICIENT_BALANCE_MESSAGE);
    }
}
