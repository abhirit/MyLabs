package com.example.aks.MyLabs;

/**
 * Created by AKS on 1/13/2018.
 */

public class User {
    private int id;
    private String name, mobileno;

    public User(int id, String name, String mobileno) {
        this.id = id;
        this.name=name;
        this.mobileno = mobileno;


    }

    public int getId() {
        return id;
    }

    public String getname() {
        return name;
    }

    public String getmobileno() {
        return mobileno;
    }


}
