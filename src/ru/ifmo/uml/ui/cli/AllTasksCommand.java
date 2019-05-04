package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Task.Task;



public class AllTasksCommand extends Command {

    public AllTasksCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }
        for (Task i : cliApp.taskManager.getAll()) {
            cliApp.output.println(i.toString());
        }
        return true;
    }
}

