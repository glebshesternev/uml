package ru.ifmo.uml.ui.cli;



public class SignOutCommand extends Command {

    public SignOutCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }

    @Override
    public boolean execute(String... args) {

        if (!cliApp.isAuth()) {
            cliApp.output.println("You are not sign in");
            return true;
        }
        cliApp.setAccount(null);
        cliApp.output.println("Done");
        return true;
    }
}
