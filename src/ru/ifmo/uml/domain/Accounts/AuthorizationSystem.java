package ru.ifmo.uml.domain.Accounts;



import ru.ifmo.uml.dao.AccountsDao;
import ru.ifmo.uml.domain.Employee.Employee;

import java.util.List;



public class AuthorizationSystem {

    private final AccountsDao accounts;


    public AuthorizationSystem(AccountsDao accounts) {

        this.accounts = accounts;
    }


    public Account create(String login, String pass, Employee employee) {

        Account account = new Account(login,pass,employee);
        accounts.insert(account);
        return account;
    }


    public Account auth(String login, String pass) {

        Account account = accounts.get(login);

        if (account == null)
            return null;
        return account.auth(pass);
    }


    public void getLogns() {

        List<Account> accs = accounts.getAll();
        for (Account i : accs) {
            System.out.println(i.getLogin());
        }
    }
}
