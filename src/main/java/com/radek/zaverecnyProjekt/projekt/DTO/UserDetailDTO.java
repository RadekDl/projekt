package com.radek.zaverecnyProjekt.projekt.DTO;

import java.util.UUID;

public class UserDetailDTO {
    private int id;
    private String name;
    private String surname;
    private String personID;
    private UUID Uuid;

    public UserDetailDTO(int id, String name, String surname, String personID, UUID uuid) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personID = personID;
        Uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UUID getUuid() {
        return Uuid;
    }

    public void setUuid(UUID uuid) {
        Uuid = uuid;
    }
}
