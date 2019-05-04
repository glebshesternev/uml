package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.domain.Accounts.Account;



public class SignInCommand extends Command {

    public SignInCommand(String name, CliApp cliApp, String... permishions) {

        super(name, cliApp, permishions);
    }


    @Override
    public boolean execute(String... args) {

        if (cliApp.isAuth()) {
            cliApp.output.println("Please logout");
            return true;
        }

        cliApp.output.print("login: ");
        String login = cliApp.input.nextLine();
        cliApp.output.print("pass: ");
        String pass = cliApp.input.nextLine();
        Account account = cliApp.authSystem.auth(login,pass);
        cliApp.setAccount(account);

        if (cliApp.isAuth()) {
            cliApp.output.println("Done");
            return true;
        } else {
            cliApp.output.println("Incorrect login/pass");
            return true;
        }
    }

}
