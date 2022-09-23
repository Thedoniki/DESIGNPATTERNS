package com.example.demo.Repository;

import com.example.demo.Models.Newsletters;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsRowMapper implements RowMapper<Newsletters> {

    @Override
    public Newsletters mapRow(ResultSet resultSet, int i) throws SQLException {
        Newsletters newsletter = new Newsletters();
        newsletter.setId(resultSet.getInt("id"));
        newsletter.setTopic(resultSet.getString("topic"));
        newsletter.setAuthor(resultSet.getString("author"));
        newsletter.setText(resultSet.getString("text"));
        return newsletter;
    }}
