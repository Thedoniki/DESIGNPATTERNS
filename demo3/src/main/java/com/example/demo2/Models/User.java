package com.example.demo2.Models;

import java.util.Observable;

public class User extends Observable {


    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void update(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        setChanged();
        notifyObservers(this);
    }

}

