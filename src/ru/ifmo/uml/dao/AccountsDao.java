package ru.ifmo.uml.dao;



import ru.ifmo.uml.domain.Accounts.Account;

import java.util.List;



public interface AccountsDao extends Dao<Account> {

    Account get(String login);

    void remove(String login);

}
