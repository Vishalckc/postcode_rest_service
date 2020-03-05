package com.example.restservice.vo;

import com.example.restservice.model.Person;

public class ResponseVO {
    private String status;
    private int value;
    private Person person;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
