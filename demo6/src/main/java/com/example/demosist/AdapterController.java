package com.example.demosist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdapterController {
    
    @RequestMapping("/adapter")
    public String adapter(Model model){

         User user = new User();
         user.setName("Yazan");
         user.setEmail("yazan@du.se");
         user.setPassword("Yaz123");

         IUserSaver iUserSaver = new AdapterArrayListSaver(new MySqlSaver());
         iUserSaver.userSaver(user);


        return "adapter";
    }
}
