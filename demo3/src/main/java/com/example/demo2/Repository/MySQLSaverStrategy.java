package com.example.demo2.Repository;

import Interfaces.UserSaverStrategy;
import com.example.demo2.Models.User;
import org.apache.xmlbeans.impl.xb.xsdschema.All;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import javax.sql.DataSource;
import java.util.List;
import java.util.Observable;

@Repository
public class MySQLSaverStrategy implements UserSaverStrategy {


    DriverManagerDataSource dataSource = new DriverManagerDataSource("jdbc:mysql://localhost:3306/labb3", "root", "");

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<User> showusers() {
        List<User> showusers = namedParameterJdbcTemplate.query( "SELECT * FROM users" , new UserRowMapper());
        return showusers;
    }


    public void saveusers(User user) {

        String insertSQLUsers = "INSERT INTO users (name, email, password)VALUES(:name, :email, :password)";
        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("name", user.getName());
        parameters.addValue("email", user.getEmail());
        parameters.addValue("password", user.getPassword());


        namedParameterJdbcTemplate.update(insertSQLUsers, parameters);
    }

    public void updateusers(User user) {
        String updateSQLusers = "UPDATE users SET name=:name, email=:email, password=:password WHERE name=:name";

        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("name", user.getName());
        par.addValue("email", user.getEmail());
        par.addValue("password", user.getPassword());
        namedParameterJdbcTemplate.update(updateSQLusers, par);
    }





    @Override
    public void update(Observable o, Object arg) {
        User user = (User) arg;
        updateusers(user);

    }
}
