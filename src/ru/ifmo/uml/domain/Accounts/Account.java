package ru.ifmo.uml.domain.Accounts;



import ru.ifmo.uml.domain.Employee.Employee;
import ru.ifmo.uml.domain.Entity;



public class Account extends Entity {

    private final String login;
    private final String pass;
    private final Employee employee;


    public Account(String login, String pass, Employee employee) {

        this.login = login;
        this.pass = pass;
        this.employee=employee;

    }


    public String getLogin() {

        return login;
    }


    public String getPass() {

        return pass;
    }


    public Employee getEmployee() {

        return employee;
    }


    public Account auth(String pass) {

        if (this.pass.equals(pass))
            return this;
        return null;
    }
}