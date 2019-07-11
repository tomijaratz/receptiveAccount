import Account.AccountManagerFacade;
import Controllers.AccountManagerController;
import org.apache.log4j.BasicConfigurator;


import static spark.Spark.get;
import static spark.Spark.put;
import static spark.Spark.post;

public class App {

    public static void main(String[] args) {


        AccountManagerController accountManagerController =
                new AccountManagerController(new AccountManagerFacade());

        BasicConfigurator.configure();

        post("/account", accountManagerController::createAccount);
        get("/account/:id/balance", accountManagerController::getAccountBalance);
        put("/account/:id/transactions/deposit/:value", accountManagerController::addDepositToAccount);
        put("/account/:id/transactions/withdraw/:value", accountManagerController::addWithdrawToAccount);

    }
}
