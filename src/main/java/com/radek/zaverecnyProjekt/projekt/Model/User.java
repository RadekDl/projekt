package com.radek.zaverecnyProjekt.projekt.Model;

import java.util.UUID;


public class User {
    private int ID;
    private String name;
    private String surname;
    private String personID;
    private UUID Uuid;

    public User(int ID, String name, String surname, String personID, UUID Uuid) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.personID = personID;
        this.Uuid = Uuid;
    }
    public User(){
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public UUID getUuid() {
        return Uuid;
    }

    public void setUuid(UUID uuid) {
        Uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }


}
