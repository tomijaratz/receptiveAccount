package Exceptions;

import static Utils.Constants.ACCOUNT_NOT_EXISTENT_MESSAGE;

public class NotExistentAccountException extends Exception {

    public NotExistentAccountException() {
        super(ACCOUNT_NOT_EXISTENT_MESSAGE);
    }
}
