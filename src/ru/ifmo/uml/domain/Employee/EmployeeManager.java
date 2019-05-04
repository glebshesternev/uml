package ru.ifmo.uml.domain.Employee;



import ru.ifmo.uml.dao.EmployeeDao;
import ru.ifmo.uml.domain.Equipment.Equipment;

import java.util.ArrayList;
import java.util.List;



public class EmployeeManager {

    private final EmployeeDao employeeDao;


    public EmployeeManager(EmployeeDao employeeDao) {

        this.employeeDao = employeeDao;
    }


    public Employee add(String name, String type) {

        Employee employee = new Employee(name, type);
        employeeDao.insert(employee);
        return employee;
    }


    public Employee get(long id) {

        return employeeDao.get(id);
    }


    public List<Employee> getFreeEngineer(List<Equipment> equipment) {

        List<Employee> alleng = employeeDao.get("engineer");
        List<Employee> bisieeng = new ArrayList<>();
        for (Equipment i : equipment)
            alleng.removeIf(n -> (n.getId() == i.getEngineer().getId()));
        return alleng;

    }


    public List<Employee> getAll() {

        return employeeDao.getAll();
    }


}

