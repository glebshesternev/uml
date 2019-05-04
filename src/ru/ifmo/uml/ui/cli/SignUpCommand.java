package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Employee.Employee;



public class SignUpCommand extends Command {

    public SignUpCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    public boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permission");
            return true;
        }

        cliApp.output.print("login: ");
        String login = cliApp.input.nextLine();
        cliApp.output.print("pass: ");
        String pass = cliApp.input.nextLine();
        cliApp.output.print("type: ");
        String type = cliApp.input.nextLine();
        cliApp.output.print("name: ");
        String name = cliApp.input.nextLine();

        Employee employee = cliApp.employeeManager.add(name, type);

        cliApp.authSystem.create(login, pass, employee);

        cliApp.output.println("Created");

        cliApp.authSystem.getLogns();

        return true;
    }

}
