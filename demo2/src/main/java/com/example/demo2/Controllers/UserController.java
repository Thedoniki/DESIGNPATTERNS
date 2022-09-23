package com.example.demo2.Controllers;

import com.example.demo2.SaverStrategy;
import com.example.demo2.Models.User;
import com.example.demo2.Repository.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
@Controller
public class UserController implements SaverStrategy {



        private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

        @Autowired
        public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        }

        @GetMapping(value = "/users")
        public String showAllUsers(Model model) {
            List<User> AllUsers = namedParameterJdbcTemplate.query( "SELECT * FROM users" , new UserRowMapper());
            model.addAttribute("user", AllUsers);
            return "usersmysqlview";
        }

    @PostMapping(value = "/adduser")
    public String adduser(@ModelAttribute User user) {

        String insertSQLUsers = "INSERT INTO users (name, email, password)VALUES(:name, :email, :password)";
        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", user.getName());
        parameters.addValue("email", user.getEmail());
        parameters.addValue("password", user.getPassword());


        namedParameterJdbcTemplate.update(insertSQLUsers, parameters);
        return "redirect:/users";

    }



    @Override
    public double save(User user) {
        return 0;
    }
}
