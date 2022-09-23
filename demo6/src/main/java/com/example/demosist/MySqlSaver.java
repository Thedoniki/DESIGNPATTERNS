package com.example.demosist;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySqlSaver{

    public void add(User user) {

        try {
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/labb2", "root","");
            String sqlSelectAllEntries = "INSERT INTO users (name, email, password) values(?,?,?)";
            PreparedStatement statement = con.prepareStatement(sqlSelectAllEntries);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setString(3,user.getPassword());
            statement.executeUpdate();
            statement.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}