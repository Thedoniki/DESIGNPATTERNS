package com.example.demo.Controllers;

import com.example.demo.Models.Newsletters;
import com.example.demo.Models.Subscriber;
import com.example.demo.Repository.NewsRowMapper;
import com.example.demo.Repository.SubscriberRowMapper;
import com.example.demo.Service.SendEmailService;
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
public class NewslettersController {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @GetMapping(value = "/allnews")
    public String showAllNewsletters(Model model) {
        List<Newsletters> AllNewsletters = namedParameterJdbcTemplate.query("SELECT * FROM newsletter", new NewsRowMapper());
        model.addAttribute("newsletters", AllNewsletters);
        return "newslettersview";
    }

    @Autowired
    private SendEmailService mailservice;
    @PostMapping(value = "/addnewsletter")
    public String addnewsletter(@ModelAttribute Newsletters newsletter) {

        String insertSQLNewsLetter = "INSERT INTO newsletter (id, topic, author, text)VALUES(:id, :topic, :author, :text )";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", newsletter.getId());
        param.addValue("topic", newsletter.getTopic());
        param.addValue("author", newsletter.getAuthor());
        param.addValue("text", newsletter.getText());

        namedParameterJdbcTemplate.update(insertSQLNewsLetter, param);
        List<Subscriber> Allsubscriber = namedParameterJdbcTemplate.query( "SELECT * FROM subscriber" , new SubscriberRowMapper());
        for(Subscriber s : Allsubscriber){
            mailservice.sendEmail(s.getEmail(), newsletter.getTopic(),newsletter.getText());
        }
        return "redirect:/allnews";


    }


    @GetMapping(value = "/deletenewsletter/{id}")
    public String deleteNewsletter(@PathVariable String id) {
        //create a named parameter in the sql statement
        String deleteSQLNewsletter = "DELETE FROM newsletter WHERE id =:id";
        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);
        namedParameterJdbcTemplate.update(deleteSQLNewsletter, parameters);
        return "redirect:/allnews";

    }
    @GetMapping(value = "/editnewsletter/{id}")
    public String showEditFormByNewsLetterById(@PathVariable String id, Model model) {
        //create a named parameter in the sql statement
        String selectSQLNewsLettersById = "SELECT * FROM newsletter WHERE id =:id";
        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("id", id);

        Newsletters newsletters = namedParameterJdbcTemplate.queryForObject(selectSQLNewsLettersById, parameters, new NewsRowMapper());

        model.addAttribute("newsletters", newsletters);

        return "editview";

    }//end showEditFormByShoeById

    @PostMapping(value = "/updatenewsletter")
    public String updateNewsLetters(@ModelAttribute Newsletters newsletters) {
        String updateSQLNewsletters = "UPDATE newsletter SET topic=:topic, author=:author, text=:text WHERE id=:id";

        //create named parameters for the named parameter in the sql statement above using the MapSqlParameterSource
        MapSqlParameterSource par = new MapSqlParameterSource();
        par.addValue("id", newsletters.getId());
        par.addValue("topic", newsletters.getTopic());
        par.addValue("author", newsletters.getAuthor());
        par.addValue("text", newsletters.getText());
        namedParameterJdbcTemplate.update(updateSQLNewsletters, par);
        return "redirect:/allnews";
    }

    @GetMapping(value = "/addform")
    public String showAddForm() {
        return "redirect:/allnews";
    }

    }







