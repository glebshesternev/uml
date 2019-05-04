package ru.ifmo.uml.ui.cli;



public abstract class Command {

    protected final String name;
    protected final CliApp cliApp;
    protected final String[] permishions;


    public Command(String name, CliApp cliApp, String... permishions) {

        this.name = name;
        this.cliApp = cliApp;
        this.permishions = permishions;
    }


    abstract boolean execute(String... args);


    public String getName() {

        return name;
    }


    public boolean checkPermission() {

        if (cliApp.getAccount() == null)
            return false;
        if (cliApp.getAccount().getEmployee().getType().equals("root"))
            return true;
        if (permishions.length == 0)
            return false;
        for (String i : permishions)
            if (cliApp.getAccount().getEmployee().getType().equals(i))
                return true;
        return false;

    }
}
