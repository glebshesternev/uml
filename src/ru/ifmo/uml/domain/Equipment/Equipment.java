package ru.ifmo.uml.domain.Equipment;



import ru.ifmo.uml.domain.Employee.Employee;
import ru.ifmo.uml.domain.Entity;



public class Equipment extends Entity {

    private String name;
    private String location;
    private Employee engineer;


    public Equipment(String name, String location, Employee engineer) {

        this.name = name;
        this.location = location;
        this.engineer = engineer;
    }


    public String getName() {

        return name;
    }


    public String getLocation() {

        return location;
    }


    public void setLocation(String location) {

        this.location = location;
    }


    public Employee getEngineer() {

        return engineer;
    }


    @Override
    public String toString() {

        return String.format("%5d %15s %15s %5d %15s", getId(), getName(), getLocation(), getEngineer().getId(), getEngineer().getName());
    }

}
