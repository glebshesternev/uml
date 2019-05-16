package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Equipment.Equipment;



public class WriteOffCommand extends Command {

    public WriteOffCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }
        cliApp.output.print("Equipment id: ");
        long id = cliApp.input.nextLong();
        cliApp.input.nextLine();
        Equipment equipment = cliApp.equipmentManager.get(id);
        if (equipment == null) {
            cliApp.output.println("Not found");
            return true;
        }
        cliApp.output.print("You chose: ");
        cliApp.output.println(equipment.toString());
        cliApp.taskManager.add(equipment, "dismantling", "warehouse");
        return true;
    }
}

