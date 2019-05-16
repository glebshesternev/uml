package ru.ifmo.uml;



import ru.ifmo.uml.ui.cli.CliApp;

import java.sql.*;



public class Main {

    public static void main(String[] args) {

        String connectionUrl = "jdbc:sqlserver://192.168.43.204:1433;databaseName=UMLproj";
        String login = "123";
        String pass = "1234";
        try {
            //MsSqlAccountsDao sqLiteAccountsDao = new MsSqlAccountsDao("jdbc:sqlite:db");
            //System.out.println(sqLiteAccountsDao.get("root").getLogin());
            CliApp cliApp = new CliApp(connectionUrl, login, pass);
            cliApp.execute();
        } catch (
                SQLException ignore) {
        }
    }
}