package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Equipment.Equipment;



public class EquipmentListCommand extends Command {

    public EquipmentListCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }
        cliApp.output.println(String.format("%5s %15s %15s %5s %15s", "id", "name", "location", "engId", "engName"));
        for (Equipment i : cliApp.equipmentManager.getAll()) {
            cliApp.output.println(i.toString());
        }
        return true;
    }
}
