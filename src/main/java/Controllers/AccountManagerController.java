package Controllers;

import Account.AccountManagerFacade;
import Exceptions.InvalidTransactionException;
import Exceptions.NotExistentAccountException;
import spark.Request;
import spark.Response;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

import static Utils.Constants.*;

public class AccountManagerController {

    private Gson gson = new Gson();

    private AccountManagerFacade accountManagerFacade;

    public AccountManagerController(AccountManagerFacade accountManagerFacade) {
        this.accountManagerFacade = accountManagerFacade;
    }

    public String getAccountBalance(Request request, Response response) {
        HashMap<String, Object> result = new HashMap<>();
        String id = request.params("id");

        Integer accountId;

        try {
            accountId = Integer.valueOf(id);
        } catch (Exception e) {
            response.status(400);
            result.put("Error", BAD_REQUEST);
            return gson.toJson(result);
        }

        try {
            BigDecimal balance = accountManagerFacade.getBalanceFor(accountId);
            ArrayList<String> transactions = accountManagerFacade.getTransactionsFor(accountId);

            result.put("accountId", accountId);
            result.put("balance", balance);
            result.put("transactions", transactions);

        } catch (NotExistentAccountException e) {
            result.put("Error", ACCOUNT_NOT_EXISTENT_MESSAGE);
        }

        return gson.toJson(result);
    }

    public String createAccount(Request request, Response response) {
        HashMap<String, Integer> result = new HashMap<>();
        Integer accountId = accountManagerFacade.createAccount();

        result.put("Account created with id", accountId);
        return gson.toJson(result);
    }

    public String addDepositToAccount(Request req, Response response) {
        HashMap<String, Object> result = new HashMap<>();

        if (isBadFormatted(req)) {
            response.status(400);
            result.put("Error", BAD_REQUEST);
            return gson.toJson(result);
        }
        Integer accountId = Integer.valueOf(req.params("id"));
        BigDecimal value = new BigDecimal(req.params("value"));

        try {
            accountManagerFacade.addDepositToAccount(accountId, value);
            result.put("Transaction status", "successful");
        } catch (InvalidTransactionException e) {
            result.put("Transaction status", "error");
            result.put("Error message", e.getMessage());
        } catch (NotExistentAccountException e) {
            result.put("Transaction status", "error");
            result.put("Error message", ACCOUNT_NOT_EXISTENT_MESSAGE);
        }

        return gson.toJson(result);

    }

    public String addWithdrawToAccount(Request req, Response response) {

        //this repeated code should be extracted to another method
        HashMap<String, Object> result = new HashMap<>();

        if (isBadFormatted(req)) {
            response.status(400);
            result.put("Error", BAD_REQUEST);
            return gson.toJson(result);
        }
        Integer accountId = Integer.valueOf(req.params("id"));
        BigDecimal value = new BigDecimal(req.params("value"));

        try {
            accountManagerFacade.addWithdrawToAccount(accountId, value);
            result.put("Transaction status", "successful");
        } catch (InvalidTransactionException e) {
            result.put("Transaction status", "error");
            result.put("Error message", e.getMessage());
        } catch (NotExistentAccountException e) {
            result.put("Transaction status", "error");
            result.put("Error message", ACCOUNT_NOT_EXISTENT_MESSAGE);
        }

        return gson.toJson(result);
    }

    Boolean isBadFormatted(Request request) {
        //there should be a way to do this with spark
        //also this check is slow, we could use "isInteger" and "isBigDecimal"
        //or something like that
        try {
            Integer.valueOf(request.params("id"));
            new BigDecimal(request.params("value"));
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
