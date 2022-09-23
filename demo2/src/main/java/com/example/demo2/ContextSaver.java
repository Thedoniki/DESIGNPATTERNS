package com.example.demo2;

import com.example.demo2.Models.User;

public class ContextSaver {
    private SaverStrategy saverStrategy;

    public ContextSaver(SaverStrategy saverStrategy){
        this.saverStrategy=saverStrategy;
    }

    public SaverStrategy getSaverStrategy() {
        return saverStrategy;
    }

    public void setSaverStrategy(SaverStrategy saverStrategy) {
        this.saverStrategy = saverStrategy;
    }


    public double save(User user){
        return this.saverStrategy.save(user);
    }
}
