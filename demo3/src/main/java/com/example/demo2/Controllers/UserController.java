package com.example.demo2.Controllers;


import Interfaces.UserSaverStrategy;
import com.example.demo2.ContextSaver;
import com.example.demo2.Models.User;
import com.example.demo2.Repository.ExcelSaverStrategy;
import com.example.demo2.Repository.MySQLSaverStrategy;
import com.example.demo2.Repository.TextfileSaverStrategy;
import com.example.demo2.Repository.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Observable;

@Controller
public class UserController {
    ContextSaver contextSaver = new ContextSaver(new MySQLSaverStrategy());

    @GetMapping("usercontroller/users")
    public String getUsers(Model model) {
        ExcelSaverStrategy ess = new ExcelSaverStrategy();
        MySQLSaverStrategy mss = new MySQLSaverStrategy();
        TextfileSaverStrategy tfss = new TextfileSaverStrategy();



        model.addAttribute("users", contextSaver.showusers());


        return "allusersview";
    }

    @PostMapping("/updateusers")
    public String updateusers(@ModelAttribute("user") User user) {


        user.addObserver(contextSaver.getSaverStrategy());
        user.update(user.getName(), user.getEmail(), user.getPassword());
        return "redirect:/usercontroller/users";
    }
}
