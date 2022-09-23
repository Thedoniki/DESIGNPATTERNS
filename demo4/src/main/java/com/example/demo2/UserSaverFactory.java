package com.example.demo2;

import Interfaces.UserSaverStrategy;
import com.example.demo2.Controllers.ExcelSaverStrategy;
import com.example.demo2.Controllers.MySQLSaverStrategy;
import com.example.demo2.Controllers.TextfileSaverStrategy;

public class UserSaverFactory {


    public static UserSaverStrategy getUserSaverStrategy(UserSaverEnum UserSavers) {
        UserSaverStrategy userSaverStrategy = null;
        switch (UserSavers) {
            case EXCEL:
                userSaverStrategy = new ExcelSaverStrategy();
                break;
            case TEXTFILE:
                userSaverStrategy = new TextfileSaverStrategy();
                break;
            case MYSQL:
                userSaverStrategy = new MySQLSaverStrategy();
                break;
            default:
                break;
        }
        return userSaverStrategy;
    }


}
