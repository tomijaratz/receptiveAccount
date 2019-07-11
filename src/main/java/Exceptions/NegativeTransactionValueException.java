package Exceptions;

import static Utils.Constants.NEGATIVE_VALUE_MESSAGE;

public class NegativeTransactionValueException extends InvalidTransactionException {

    public NegativeTransactionValueException() {
        super(NEGATIVE_VALUE_MESSAGE);
    }
}
