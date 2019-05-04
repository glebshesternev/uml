package ru.ifmo.uml.domain.Task;



import ru.ifmo.uml.dao.Dao;
import ru.ifmo.uml.domain.Accounts.Account;
import ru.ifmo.uml.domain.Equipment.Equipment;

import java.util.ArrayList;
import java.util.List;



public class TaskManager {

    private final Dao<Task> tasksDao;


    public TaskManager(Dao<Task> taskDao) {

        this.tasksDao = taskDao;
    }


    public Task add(Equipment equipment, String goal) {

        Task task = new Task(equipment, goal);
        tasksDao.insert(task);
        return task;
    }


    public void update(Task task) {

        tasksDao.update(task);
    }

    public void remove(long id) {
        tasksDao.remove(id);
    }


    public Task get(long id) {

        return tasksDao.get(id);
    }


    public List<Task> get(Account account) {

        List<Task> tasks = new ArrayList();
        if (account.getEmployee().getType().equals("root") || account.getEmployee().getType().equals("manager")) {
            for (Task i : tasksDao.getAll()) {
                if (!i.getStatus().equals("done")) {
                    tasks.add(i);
                }
            }
        } else if (account.getEmployee().getType().equals("engineer")) {
            for (Task i : tasksDao.getAll()) {
                if (i.getEquipment().getEngineer().getId() == account.getEmployee().getId() && !i.getStatus().equals("done")) {
                    tasks.add(i);
                }
            }
        }
        return tasks;
    }


    public List<Task> getAll() {

        return tasksDao.getAll();
    }
}
