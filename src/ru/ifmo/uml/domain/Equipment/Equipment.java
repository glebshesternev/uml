package ru.ifmo.uml.domain.Equipment;



import ru.ifmo.uml.domain.Employee.Employee;
import ru.ifmo.uml.domain.Entity;



public class Equipment extends Entity {

    private String name;
    private Employee engineer;

    public Equipment(String name, Employee engineer) {
        this.name = name;
        this.engineer = engineer;
    }


    public String getName() {

        return name;
    }


    public Employee getEngineer() {

        return engineer;
    }

    @Override
    public String toString() {

        return String.format("%5d %15s %5d %15s", getId(), getName(), getEngineer().getId(), getEngineer().getName());
    }

}
