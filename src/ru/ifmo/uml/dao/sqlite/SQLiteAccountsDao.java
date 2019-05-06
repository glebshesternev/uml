package ru.ifmo.uml.dao.sqlite;



import ru.ifmo.uml.dao.AccountsDao;
import ru.ifmo.uml.domain.Accounts.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class SQLiteAccountsDao implements AccountsDao {

    private final static String SELECT_BY_ID = "SELECT * FROM Accounts WHERE id=?";
    private final static String SELECT_BY_LOGIN = "SELECT * FROM Accounts WHERE login=?";
    private final static String SELECT_ALL = "SELECT * FROM Accounts";
    private final static String REMOVE_BY_ID = "DELETE FROM Accounts WHERE id=?";
    private final static String REMOVE_BY_LOGIN = "DELETE FROM Accounts WHERE login=?";
    private final static String INSERT = "INSERT INTO Accounts VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE Accounts SET pass=? WHERE login=?";

    private final SQLiteDao sqLiteDao;
    private final Connection connection;

    public SQLiteAccountsDao(SQLiteDao sqLiteDao) {
        this.sqLiteDao = sqLiteDao;
        this.connection = sqLiteDao.getConnection();
    }

    @Override
    public Account get(String login) {

        try {

            PreparedStatement ps = sqLiteDao.getConnection().prepareStatement(SELECT_BY_LOGIN);
            ps.setString(1, login);
            ResultSet result = ps.executeQuery();
            assert result != null;
            result.next();
            return new Account(
                    result.getString(2),
                    result.getString(3),
                    sqLiteDao.employeeDao.get(result.getInt(4)));
        } catch (SQLException e) {
        }
        return null;
    }


    @Override
    public void remove(String login) {

        try {

            PreparedStatement ps = connection.prepareStatement(REMOVE_BY_LOGIN);
            ps.setString(1, login);
            ResultSet result = ps.executeQuery();
        } catch (SQLException e) {
        }
    }


    @Override
    public void insert(Account o) {

        try {

            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getLogin());
            ps.setString(2, o.getPass());
            long id = o.getEmployee().getId();
            ps.setLong(3, id);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            o.setId(rs.getInt(1));
        } catch (SQLException e) {
        }
    }


    @Override
    public Account get(long id) {

        try {

            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            assert result != null;
            result.next();
            Account account = new Account(result.getString(2), result.getString(3), sqLiteDao.employeeDao.get(result.getInt(4)));
            account.setId(id);
            return account;
        } catch (SQLException e) {
        }
        return null;
    }


    @Override
    public List<Account> getAll() {

        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet result = ps.executeQuery();
            assert result != null;
            while (result.next()) {
                Account account = new Account(result.getString(2), result.getString(3), sqLiteDao.employeeDao.get(result.getInt(4)));
                account.setId(result.getInt(1));
                accounts.add(account);
            }
        } catch (SQLException e) {
        }
        return accounts;
    }


    @Override
    public void update(Account o) {

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, o.getPass());
            ps.setString(2, o.getLogin());
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }


    @Override
    public void remove(long id) {

        try {

            PreparedStatement ps = connection.prepareStatement(REMOVE_BY_ID);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
        }
    }
}
