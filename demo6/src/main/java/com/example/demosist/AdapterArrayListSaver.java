package com.example.demosist;

public class AdapterArrayListSaver implements IUserSaver {
    private MySqlSaver arrSaver;

    public AdapterArrayListSaver(MySqlSaver arrSaver){
        this.arrSaver=arrSaver;
    }


    @Override
    public void userSaver(User user) {

    }
}
