package ru.ifmo.uml.domain.Employee;



import ru.ifmo.uml.domain.Entity;



public class Employee extends Entity {

    private String name;
    private String type;


    public Employee(String name, String type) {

        this.name = name;
        this.type = type;
    }


    public String getName() {

        return name;
    }


    public String getType() {

        return type;
    }


    @Override
    public String toString() {

        return String.format("%5d %15s %15s", getId(), getName(), getType());
    }

}
