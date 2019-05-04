package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Equipment.Equipment;



public class TransferCommand extends Command {

    public TransferCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    public boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }

        cliApp.output.print("Equipment id: ");
        long id = cliApp.input.nextLong();
        Equipment equipment = cliApp.equipmentManager.get(id);
        cliApp.output.print("You chose: ");
        cliApp.output.println(equipment.toString());
        cliApp.taskManager.add(equipment, "dismantling");
        cliApp.taskManager.add(equipment, "move");
        cliApp.taskManager.add(equipment, "install");
        cliApp.output.println("3 tasks created.");
        return true;
    }


}
