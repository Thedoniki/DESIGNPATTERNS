package com.example.demo2;

import Interfaces.UserSaverStrategy;
import com.example.demo2.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

public class ContextSaver {
    private UserSaverStrategy saverStrategy;

    public ContextSaver(UserSaverStrategy saverStrategy){
        this.saverStrategy=saverStrategy;
    }

    public UserSaverStrategy getSaverStrategy() {
        return saverStrategy;
    }

    public void setSaverStrategy(UserSaverStrategy saverStrategy) {
        this.saverStrategy = saverStrategy;
    }

    public void save(User user){
        this.saverStrategy.saveusers(user);
    }
    public List<User> showusers(){
       return this.saverStrategy.showusers();
    }
}
