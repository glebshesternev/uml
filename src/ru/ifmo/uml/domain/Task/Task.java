package ru.ifmo.uml.domain.Task;



import ru.ifmo.uml.domain.Entity;
import ru.ifmo.uml.domain.Equipment.Equipment;



public class Task extends Entity {

    private Equipment equipment;
    private String goal;
    private String status;


    public Task(Equipment equipment, String goal) {

        this.equipment = equipment;
        this.goal = goal;
        this.status = "planning";
    }


    public Task(Equipment equipment, String goal, String status) {

        this.equipment = equipment;
        this.goal = goal;
        if (status.equals("inProgress") || status.equals("done")) {
            this.status = status;
        } else {
            this.status = "planning";
        }
    }

    public void update() {
        if (status.equals("planning"))
            status = "inProgress";
        else if (status.equals("inProgress"))
            status = "done";

    }

    public Equipment getEquipment() {

        return equipment;
    }


    public String getGoal() {

        return goal;
    }


    public String getStatus() {

        return status;
    }

    @Override
    public String toString() {
        return String.format("%5d %15s %15s %15s %15s", getId(), equipment.getName(), equipment.getEngineer().getName(), getGoal(), getStatus());
    }
}
