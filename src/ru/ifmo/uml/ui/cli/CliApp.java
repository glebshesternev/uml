package ru.ifmo.uml.ui.cli;



import ru.ifmo.uml.dao.sqlite.SQLiteDao;
import ru.ifmo.uml.domain.Accounts.Account;
import ru.ifmo.uml.domain.Accounts.AuthorizationSystem;
import ru.ifmo.uml.domain.Employee.EmployeeManager;
import ru.ifmo.uml.domain.Equipment.EquipmentManager;
import ru.ifmo.uml.domain.Task.TaskManager;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class CliApp {

    final private Map<String, Command> commands = new TreeMap<>();

    final private SQLiteDao sqLiteDao;

    final AuthorizationSystem authSystem;
    final EmployeeManager employeeManager;
    final EquipmentManager equipmentManager;
    final TaskManager taskManager;

    private Account account;

    public Scanner input;
    public PrintStream output;

    private boolean ready = true;


    public CliApp(String url, String login, String pass) throws SQLException {

        sqLiteDao = new SQLiteDao(url, login, pass);

        authSystem = new AuthorizationSystem(sqLiteDao.accountsDao);
        employeeManager = new EmployeeManager(sqLiteDao.employeeDao);
        equipmentManager = new EquipmentManager(sqLiteDao.equipmentDao);
        taskManager = new TaskManager(sqLiteDao.tasksDao);

        input = new Scanner(System.in);
        output = new PrintStream(System.out);

        putCommand(new SignInCommand("signin", this));
        putCommand(new SignOutCommand("signout", this));
        putCommand(new SignUpCommand("signup", this, "root"));
        putCommand(new TransferCommand("transfer", this, "manager"));
        putCommand(new AddEquipmentCommand("add", this, "manager"));
        putCommand(new EquipmentListCommand("eqlist", this, "manager"));
        putCommand(new EmployeeListCommand("emplist", this, "manager"));
        putCommand(new ActiveTasksCommand("curtasks", this, "manager", "engineer"));
        putCommand(new AllTasksCommand("alltasks", this, "manager"));
        putCommand(new UpdateTaskStatusCommand("upd", this, "engineer"));
        putCommand(new WriteOffCommand("writeoff", this, "manager"));


    }


    public void execute() {

        boolean result = true;

        do {
            String cmdStr;
            Scanner input = new Scanner(System.in);
            System.out.print(">");
            cmdStr = input.nextLine();
            ParsedCommand pc = new ParsedCommand(cmdStr);
            if (pc.command == null || "".equals(pc.command)) {
                continue;
            }
            Command command = commands.get(pc.command);
            if (command == null) {
                System.out.println("Command not found");
                continue;
            }
            ready = false;
            result = command.execute(pc.args);
            ready = true;
        } while (result);
    }


    private void putCommand(Command command) {

        commands.put(command.getName(), command);
    }


    public void setAccount(Account account) {

        this.account = account;
    }


    public Account getAccount() {

        return account;
    }


    public boolean isAuth() {

        return account != null;
    }


}
