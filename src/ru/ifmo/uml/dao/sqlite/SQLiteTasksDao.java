package ru.ifmo.uml.dao.sqlite;



import ru.ifmo.uml.dao.Dao;
import ru.ifmo.uml.domain.Equipment.Equipment;
import ru.ifmo.uml.domain.Task.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class SQLiteTasksDao implements Dao<Task> {

    private final static String SELECT_BY_ID = "SELECT * FROM Tasks WHERE id=?";
    private final static String SELECT_ALL = "SELECT * FROM Tasks";
    private final static String REMOVE_BY_ID = "DELETE FROM Tasks WHERE id=?";
    private final static String INSERT = "INSERT INTO Tasks VALUES (?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE Tasks SET status=? WHERE id=?";

    private final SQLiteDao sqLiteDao;
    private final Connection connection;


    public SQLiteTasksDao(SQLiteDao sqLiteDao) {

        this.sqLiteDao = sqLiteDao;
        this.connection = sqLiteDao.getConnection();
    }


    @Override
    public void insert(Task o) {

        try {

            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setNull(1, 0);
            ps.setInt(2, (int) o.getEquipment().getId());
            ps.setString(3, o.getGoal());
            ps.setString(4, o.getStatus());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
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
            Equipment equipment = sqLiteDao.equipmentDao.get(result.getInt(2));
            Task task = new Task(equipment, result.getString(3), result.getString(4));
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
                Equipment equipment = sqLiteDao.equipmentDao.get(result.getInt(2));
                Task task = new Task(equipment, result.getString(3), result.getString(4));
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

