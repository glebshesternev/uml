package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Equipment.Equipment;
import ru.ifmo.uml.domain.Task.Task;



public class UpdateTaskStatusCommand extends Command {

    public UpdateTaskStatusCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }

        cliApp.output.print("Task id: ");
        long id = cliApp.input.nextLong();
        Task task = cliApp.taskManager.get(id);
        if (task == null) {
            cliApp.output.println("Not found");
            return true;
        }
        task.update();
        cliApp.taskManager.update(task);

        cliApp.output.println("Updated: ");
        cliApp.output.println(task.toString());

        if (task.getGoal().equals("dismantling") && task.getStatus().equals("done")) {
            long eqId = task.getEquipment().getId();
            cliApp.taskManager.remove(task.getId());
            cliApp.equipmentManager.remove(eqId);
        } else if (task.getStatus().equals("done")) {
            Equipment eq = task.getEquipment();
            eq.setLocation(task.getLocation());
            cliApp.equipmentManager.update(eq);
            cliApp.taskManager.remove(task.getId());

        }

        return true;
    }
}
