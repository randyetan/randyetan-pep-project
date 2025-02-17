package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAO();
    }

    public Account createAccount(String username, String password) {
        // The registration will be successful if and only if the username is not blank, 
        // the password is at least 4 characters long, and an Account with that username does not already exist. 
        // If all these conditions are met, the response body should contain a JSON of the Account, including its 
        // account_id. The response status should be 200 OK, which is the default. The new account should be 
        // persisted to the database.
        // If the registration is not successful, the response status should be 400. (Client error)


        if(username == null || username == "" || password == null || password.length() < 4) {
            return null;
        }

        if(accountDAO.getAccountByUsername(username) != null) {
            return null;
        }

        return accountDAO.createAccount(username, password);
    }

    public Account login(String username, String password) {
        // As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. 
        // The request body will contain a JSON representation of an Account, not containing an account_id. 
        // In the future, this action may generate a Session token to allow the user to securely use the site. 
        // We will not worry about this for now.
        // The login will be successful if and only if the username and password provided in the request body 
        // JSON match a real account existing on the database. If successful, the response body should contain 
        // a JSON of the account in the response body, including its account_id. The response status should be 
        // 200 OK, which is the default.
        // If the login is not successful, the response status should be 401. (Unauthorized)
        if(username == null || username == "" || password == null || password.length() < 4) {
            return null;
        }

        Account account = accountDAO.getAccountByUsername(username);
        if (account == null) {
            return null;
        }
        if(account.getPassword().equals(password)) {
            return account;
        }
        return null;
    }

    public Account getAccountById(int account_id) {
        return accountDAO.getAccountById(account_id);
    }

}
