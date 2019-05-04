package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Task.Task;



public class ActiveTasksCommand extends Command {

    public ActiveTasksCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    boolean execute(String... args) {

        if (!checkPermission()) {
            cliApp.output.println("No permishion");
            return true;
        }
        cliApp.output.println(String.format("%5s %15s %15s %15s %15s", "id", "eqName", "engName","goal", "status"));
        for (Task i : cliApp.taskManager.get(cliApp.getAccount())) {
            cliApp.output.println(i.toString());
        }
        return true;
    }
}
