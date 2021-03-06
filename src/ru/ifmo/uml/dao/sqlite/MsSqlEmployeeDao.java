package ru.ifmo.uml.dao.sqlite;



import ru.ifmo.uml.dao.EmployeeDao;
import ru.ifmo.uml.domain.Employee.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MsSqlEmployeeDao implements EmployeeDao {

    private final static String SELECT_BY_ID = "SELECT * FROM Employee WHERE id=?";
    private final static String SELECT_BY_TYPE = "SELECT * FROM Employee WHERE type=?";
    private final static String SELECT_ALL = "SELECT * FROM Employee";
    private final static String REMOVE_BY_ID = "DELETE FROM Employee WHERE id=?";
    private final static String INSERT = "INSERT INTO Employee VALUES (?, ?)";
    private final static String UPDATE = "UPDATE Employee SET name=?, type=? WHERE id=?";

    private final MsSqlDao msSqlDao;
    private final Connection connection;


    public MsSqlEmployeeDao(MsSqlDao msSqlDao) {

        this.msSqlDao = msSqlDao;
        this.connection = msSqlDao.getConnection();
    }


    @Override
    public void insert(Employee o) {

        try {
            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, o.getName());
            ps.setString(2, o.getType());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            o.setId(rs.getInt(1));
        } catch (SQLException e) {
        }
    }


    @Override
    public Employee get(long id) {

        try {

            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            assert result != null;
            result.next();
            Employee employee = new Employee(result.getString(2), result.getString(3));
            employee.setId(id);
            return employee;
        } catch (SQLException e) {
        }
        return null;
    }


    @Override
    public List<Employee> getAll() {

        List<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet result = ps.executeQuery();
            assert result != null;
            while (result.next()) {
                Employee employee = new Employee(result.getString(2), result.getString(3));
                employee.setId(result.getInt(1));
                employees.add(employee);
            }
        } catch (SQLException e) {
        }
        return employees;
    }


    @Override
    public void update(Employee o) {

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, o.getName());
            ps.setString(2, o.getType());
            ps.setLong(3, o.getId());
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


    @Override
    public List<Employee> get(String type) {

        List<Employee> employees = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_BY_TYPE);
            ps.setString(1, type);
            ResultSet result = ps.executeQuery();
            assert result != null;
            while (result.next()) {
                Employee employee = new Employee(result.getString(2), result.getString(3));
                employee.setId(result.getInt(1));
                employees.add(employee);
            }
        } catch (SQLException e) {
        }
        return employees;
    }

}