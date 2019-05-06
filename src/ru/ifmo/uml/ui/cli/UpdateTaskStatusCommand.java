package ru.ifmo.uml.ui.cli;



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
        task.update();
        cliApp.taskManager.update(task);

        cliApp.output.println("Updated: ");
        cliApp.output.println(task.toString());

        if (task.getGoal().equals("dismantling") && task.getStatus().equals("done")) {
            long eqId = task.getEquipment().getId();
            cliApp.taskManager.remove(task.getId());
            cliApp.equipmentManager.remove(eqId);
        }

        if (task.getStatus().equals("done")) {
            cliApp.taskManager.remove(task.getId());
        }

        return true;
    }
}
