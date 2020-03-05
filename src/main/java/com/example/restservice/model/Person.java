package com.example.restservice.model;

import javax.persistence.*;
import java.util.List;


/*@Table(name="person")*/
public class Person {
    private int id;
    private String name;
    private List<Address> address;

    public Person() {
        super();
    }

    public Person(String name, int id, List<Address> address) {
        this.name = name;
        this.id = id;
        this.address = address;
    }

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")*/
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

  /*  @Column(name = "address")
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Address.class, cascade = CascadeType.ALL, mappedBy = "person")
    @Fetch(FetchMode.JOIN)*/

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

}
