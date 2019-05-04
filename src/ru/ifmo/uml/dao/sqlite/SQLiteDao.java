package ru.ifmo.uml.dao.sqlite;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class SQLiteDao {

  private final Connection connection;

  public final SQLiteAccountsDao accountsDao;
  public final SQLiteEmployeeDao employeeDao;
  public final SQLiteEquipmentDao equipmentDao;
  public final SQLiteTasksDao tasksDao;

  public SQLiteDao(String url, String login, String pass) throws SQLException {

      connection = DriverManager.getConnection(url, login, pass);
      accountsDao = new SQLiteAccountsDao(this);
      employeeDao = new SQLiteEmployeeDao(this);
      equipmentDao = new SQLiteEquipmentDao(this);
      tasksDao = new SQLiteTasksDao(this);
  }


    public Connection getConnection() {

        return connection;
    }
}
