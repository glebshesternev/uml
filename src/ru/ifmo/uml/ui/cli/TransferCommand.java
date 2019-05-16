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
        cliApp.input.nextLine();
        cliApp.output.print("New location: ");
        String location = cliApp.input.nextLine();
        Equipment equipment = cliApp.equipmentManager.get(id);
        cliApp.output.print("You chose: ");
        cliApp.output.println(equipment.toString());
        cliApp.taskManager.add(equipment, "move", location);
        cliApp.output.println("task created.");
        return true;
    }


}
