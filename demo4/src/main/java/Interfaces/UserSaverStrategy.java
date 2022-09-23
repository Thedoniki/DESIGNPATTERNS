package Interfaces;


import com.example.demo2.Controllers.ExcelSaverStrategy;
import com.example.demo2.Controllers.MySQLSaverStrategy;
import com.example.demo2.Controllers.TextfileSaverStrategy;
import com.example.demo2.Models.User;


public interface UserSaverStrategy {

    public double save(User user);

}
