package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Employee.Employee;



public class EmployeeListCommand extends Command {

    public EmployeeListCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }
        cliApp.output.println(String.format("%5s %15s %15s", "id", "name", "type"));
        for (Employee i : cliApp.employeeManager.getAll()) {
            cliApp.output.println(i.toString());
        }
        return true;
    }
}
