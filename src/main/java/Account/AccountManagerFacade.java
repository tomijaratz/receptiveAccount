package Account;

import Exceptions.InvalidTransactionException;
import Exceptions.NotExistentAccountException;
import Transactions.Deposit;
import Transactions.Transaction;
import Transactions.Withdraw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class AccountManagerFacade {

    private Integer lastAccountId;

    private HashMap<Integer, Account> accounts;

    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    public AccountManagerFacade() {
        accounts = new HashMap<Integer, Account>();
        lastAccountId = 0;

        createAccount();
        createAccount();
    }

    public Integer createAccount() {
        lastAccountId++;
        Integer accountId = lastAccountId;

        accounts.put(accountId, new Account());

        return accountId;
    }

    public BigDecimal getBalanceFor(Integer accountId) throws NotExistentAccountException {
        if (accounts.keySet().contains(accountId)) {
            return accounts.get(accountId).balance();
        } else {
            throw new NotExistentAccountException();
        }
    }

    public ArrayList<String> getTransactionsFor(Integer accountId) throws NotExistentAccountException {
        if (accounts.keySet().contains(accountId)) {
            return accounts.get(accountId).getMovements();
        } else {
            throw new NotExistentAccountException();
        }
    }

    private void addTransactionToAccount(Integer accountId, Transaction transaction) throws InvalidTransactionException, NotExistentAccountException {
        if (!accounts.containsKey(accountId)) {
            throw new NotExistentAccountException();
        }
        accounts.get(accountId).register(transaction);
    }

    public void addDepositToAccount(Integer accountId, BigDecimal amount) throws InvalidTransactionException, NotExistentAccountException {

        Deposit deposit = new Deposit(amount);
        addTransactionToAccount(accountId, deposit);
    }

    public void addWithdrawToAccount(Integer accountId, BigDecimal amount) throws InvalidTransactionException, NotExistentAccountException {

        Withdraw withdraw = new Withdraw(amount);
        addTransactionToAccount(accountId, withdraw);

    }
}
