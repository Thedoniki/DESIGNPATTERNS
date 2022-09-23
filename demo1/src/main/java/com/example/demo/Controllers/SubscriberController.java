package com.example.demo.Controllers;

import com.example.demo.Models.Subscriber;
import com.example.demo.Repository.SubscriberRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class SubscriberController {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GetMapping(value = "/")
    public String showAllsubscribers(Model model) {
        List<Subscriber> Allsubscriber = namedParameterJdbcTemplate.query( "SELECT * FROM subscriber" , new SubscriberRowMapper());
        model.addAttribute("subscriber", Allsubscriber);
        return "subscriberview";
    }


    @PostMapping(value = "/addsubscriber")
    public String addsubscriber(@ModelAttribute Subscriber subscriber) {

        String insertSQLSubscriber = "INSERT INTO subscriber (id, name, email)VALUES(:id, :name, :email)";
        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", subscriber.getId());
        parameters.addValue("name", subscriber.getName());
        parameters.addValue("email", subscriber.getEmail());


        namedParameterJdbcTemplate.update(insertSQLSubscriber, parameters);
        return "redirect:/";

    }


    @GetMapping(value = "/deletesubscriber/{id}")
    public String deleteSubscriberById(@PathVariable String id) {
        //create a named parameter in the sql statement
        String deleteSQLSubscriber = "DELETE FROM subscriber WHERE id =:id";
        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        namedParameterJdbcTemplate.update(deleteSQLSubscriber, parameters);
        return "redirect:/";

    }


}
