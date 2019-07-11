package Account;

import Exceptions.InvalidTransactionException;
import Transactions.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Account {

    private ArrayList<Transaction> transactions;

    public Account() {
        transactions = new ArrayList<Transaction>();
    }

    public BigDecimal balance() {
        BigDecimal balance = new BigDecimal(0);

        for (Transaction transaction : transactions) {
            balance = transaction.affectBalance(balance);
        }

        return balance;
    }

    public void register(Transaction aTransaction) throws InvalidTransactionException {
        //Validating the transaction on this account, we use Double Dispatch for cleaner code
        aTransaction.validateOn(this);

        transactions.add(aTransaction);
    }

    public Boolean hasRegistered(Transaction aTransaction) {
        return transactions.contains(aTransaction);
    }

    public ArrayList<String> getMovements() {
        ArrayList<String> movements = new ArrayList<>();

        for (Transaction transaction : transactions) {
            movements.add(transaction.toString());
        }
        return movements;
    }
}
