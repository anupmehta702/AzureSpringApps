package com.example.studentservice;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String name;
    private String address;
    private String cls;

    public Student(){
        //framework required
    }

    public Student(String name, String address, String cls) {
        super();
        this.name = name;
        this.address = address;
        this.cls = cls;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCls() {
        return cls;
    }

    public int getId() {
        return Id;
    }
}
