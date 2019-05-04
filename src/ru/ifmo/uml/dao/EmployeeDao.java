package ru.ifmo.uml.dao;



import ru.ifmo.uml.domain.Employee.Employee;

import java.util.List;



public interface EmployeeDao extends Dao<Employee> {

    List<Employee> get(String type);

}
