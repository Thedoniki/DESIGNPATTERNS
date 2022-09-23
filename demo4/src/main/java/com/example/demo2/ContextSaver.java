package com.example.demo2;

import Interfaces.UserSaverStrategy;
import com.example.demo2.Models.User;

public class ContextSaver {
    private UserSaverStrategy saverStrategy;

    public ContextSaver(UserSaverStrategy saverStrategy) {
        this.saverStrategy = saverStrategy;
    }

    public UserSaverStrategy getSaverStrategy() {
        return saverStrategy;
    }

    public void setSaverStrategy(UserSaverStrategy saverStrategy) {
        this.saverStrategy = saverStrategy;
    }


    public double save(User user) {
        return this.saverStrategy.save(user);
    }
}
