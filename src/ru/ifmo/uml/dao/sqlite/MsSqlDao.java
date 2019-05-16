package ru.ifmo.uml.dao.sqlite;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MsSqlDao {

  private final Connection connection;

  public final MsSqlAccountsDao accountsDao;
  public final MsSqlEmployeeDao employeeDao;
  public final MsSqlEquipmentDao equipmentDao;
  public final MsSqlTasksDao tasksDao;

  public MsSqlDao(String url, String login, String pass) throws SQLException {

      connection = DriverManager.getConnection(url, login, pass);
      accountsDao = new MsSqlAccountsDao(this);
      employeeDao = new MsSqlEmployeeDao(this);
      equipmentDao = new MsSqlEquipmentDao(this);
      tasksDao = new MsSqlTasksDao(this);
  }


    public Connection getConnection() {

        return connection;
    }
}
