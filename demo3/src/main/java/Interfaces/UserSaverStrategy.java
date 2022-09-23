package Interfaces;


import com.example.demo2.Models.User;

import java.util.List;
import java.util.Observer;


public interface UserSaverStrategy extends Observer {
   List<User> showusers();
   void saveusers(User user);
   void updateusers(User user);

}
