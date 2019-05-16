package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Employee.Employee;
import ru.ifmo.uml.domain.Equipment.Equipment;

import java.util.List;



public class AddEquipmentCommand extends Command {

    public AddEquipmentCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    public boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }

        cliApp.output.print("Equipment name: ");
        String eqName = cliApp.input.nextLine();
        cliApp.output.print("Equipment location: ");
        String eqLocation = cliApp.input.nextLine();

        List<Employee> freeEmp = cliApp.employeeManager.getFreeEngineer(cliApp.equipmentManager.getAll());

        if (freeEmp == null || freeEmp.size() == 0) {

            cliApp.output.print("Engineer name: ");
            String engName = cliApp.input.nextLine();
            cliApp.output.print("Engineer login: ");
            String engLogin = cliApp.input.nextLine();
            cliApp.output.print("Engineer pass: ");
            String engPass = cliApp.input.nextLine();
            String engType = "engineer";

            Employee engineer = cliApp.employeeManager.add(engName, engType);
            Equipment equipment = cliApp.equipmentManager.add(eqName, "warehouse", engineer);
            cliApp.authSystem.create(engLogin, engPass, engineer);
            cliApp.taskManager.add(equipment, "install", eqLocation);
        } else {
            for (Employee i : freeEmp)
                cliApp.output.println(i.toString());
            cliApp.output.print("Engineer id: ");
            long id = cliApp.input.nextLong();
            cliApp.input.nextLine();
            Employee engineer = cliApp.employeeManager.get(id);
            if (engineer == null) {
                cliApp.output.println("Not found");
                return true;
            }
            Equipment equipment = cliApp.equipmentManager.add(eqName, "warehouse", engineer);
            cliApp.taskManager.add(equipment, "install", eqLocation);

        }
        cliApp.output.println("Added");
        return true;
    }
}
