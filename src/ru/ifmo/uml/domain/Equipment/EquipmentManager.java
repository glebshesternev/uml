package ru.ifmo.uml.domain.Equipment;



import ru.ifmo.uml.dao.Dao;
import ru.ifmo.uml.domain.Employee.Employee;

import java.util.List;



public class EquipmentManager {

    private final Dao<Equipment> equipmentDao;


    public EquipmentManager(Dao<Equipment> equipmentDao) {

        this.equipmentDao = equipmentDao;
    }


    public Equipment add(String name, Employee engineer) {

        if (!engineer.getType().equals("engineer")) {
            return null;
        }
        Equipment equipment = new Equipment(name, engineer);
        equipmentDao.insert(equipment);
        return equipment;
    }


    public Equipment get(long id) {

        return equipmentDao.get(id);
    }


    public void remove(long id) {

        equipmentDao.remove(id);
    }


    public List<Equipment> getAll() {

        return equipmentDao.getAll();
    }

}
