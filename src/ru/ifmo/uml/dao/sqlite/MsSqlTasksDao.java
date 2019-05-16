package ru.ifmo.uml.dao.sqlite;



import ru.ifmo.uml.dao.Dao;
import ru.ifmo.uml.domain.Equipment.Equipment;
import ru.ifmo.uml.domain.Task.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class MsSqlTasksDao implements Dao<Task> {

    private final static String SELECT_BY_ID = "SELECT * FROM Tasks WHERE id=?";
    private final static String SELECT_ALL = "SELECT * FROM Tasks";
    private final static String REMOVE_BY_ID = "DELETE FROM Tasks WHERE id=?";
    private final static String INSERT = "INSERT INTO Tasks VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE Tasks SET status=? WHERE id=?";

    private final MsSqlDao msSqlDao;
    private final Connection connection;


    public MsSqlTasksDao(MsSqlDao msSqlDao) {

        this.msSqlDao = msSqlDao;
        this.connection = msSqlDao.getConnection();
    }


    @Override
    public void insert(Task o) {

        try {

            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, (int) o.getEquipment().getId());
            ps.setString(2, o.getGoal());
            ps.setString(3, o.getLocation());
            ps.setString(4, o.getStatus());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            o.setId(rs.getInt(1));
        } catch (SQLException e) {
        }
    }


    @Override
    public Task get(long id) {

        try {

            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            assert result != null;
            result.next();
            Equipment equipment = msSqlDao.equipmentDao.get(result.getInt(2));
            Task task = new Task(equipment, result.getString(3), result.getString(4), result.getString(5));
            task.setId(id);
            return task;
        } catch (SQLException e) {
        }
        return null;
    }


    @Override
    public List<Task> getAll() {

        List<Task> tasks = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet result = ps.executeQuery();
            assert result != null;
            while (result.next()) {
                Equipment equipment = msSqlDao.equipmentDao.get(result.getInt(2));
                Task task = new Task(equipment, result.getString(3), result.getString(4), result.getString(5));
                task.setId(result.getInt(1));
                tasks.add(task);
            }
        } catch (SQLException e) {
        }
        return tasks;
    }


    @Override
    public void update(Task o) {

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, o.getStatus());
            ps.setLong(2, o.getId());
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


