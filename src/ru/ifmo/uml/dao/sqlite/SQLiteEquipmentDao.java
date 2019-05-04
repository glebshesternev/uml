package ru.ifmo.uml.dao.sqlite;



import ru.ifmo.uml.dao.Dao;
import ru.ifmo.uml.domain.Equipment.Equipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class SQLiteEquipmentDao implements Dao<Equipment> {

    private final static String SELECT_BY_ID = "SELECT * FROM Equipment WHERE id=?";
    private final static String SELECT_ALL = "SELECT * FROM Equipment";
    private final static String REMOVE_BY_ID = "DELETE FROM Equipment WHERE id=?";
    private final static String INSERT = "INSERT INTO Equipment VALUES (?, ?, ?)";
    private final static String UPDATE = "UPDATE Equipment SET name=? WHERE id=?";

    private final SQLiteDao sqLiteDao;
    private final Connection connection;


    public SQLiteEquipmentDao(SQLiteDao sqLiteDao) {

        this.sqLiteDao = sqLiteDao;
        this.connection = sqLiteDao.getConnection();
    }


    @Override
    public void insert(Equipment o) {

        try {

            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setNull(1, 0);
            ps.setString(2, o.getName());
            ps.setInt(3, (int) o.getEngineer().getId());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            o.setId(rs.getInt(1));
        } catch (SQLException e) {
        }
    }


    @Override
    public Equipment get(long id) {

        try {

            PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID);
            ps.setLong(1, id);
            ResultSet result = ps.executeQuery();
            assert result != null;
            Equipment equipment = new Equipment(result.getString(2), sqLiteDao.employeeDao.get(result.getInt(3)));
            equipment.setId(id);
            return equipment;
        } catch (SQLException e) {
        }
        return null;
    }


    @Override
    public List<Equipment> getAll() {

        List<Equipment> equipments = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL);
            ResultSet result = ps.executeQuery();
            assert result != null;
            while (result.next()) {
                Equipment equipment = new Equipment(result.getString(2), sqLiteDao.employeeDao.get(result.getInt(3)));
                equipment.setId(result.getInt(1));
                equipments.add(equipment);
            }
        } catch (SQLException e) {
        }
        return equipments;
    }


    @Override
    public void update(Equipment o) {

        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, o.getName());
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



