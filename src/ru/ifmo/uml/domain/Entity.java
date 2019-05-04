package ru.ifmo.uml.domain;



public abstract class Entity {

    protected long id;


    public final long getId() {

        return id;
    }


    public void setId(long id) {

        this.id = id;
    }
}
