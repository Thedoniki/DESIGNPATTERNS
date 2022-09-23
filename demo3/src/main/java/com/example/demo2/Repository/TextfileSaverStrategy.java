package com.example.demo2.Repository;


import Interfaces.UserSaverStrategy;
import com.example.demo2.Models.User;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


public class TextfileSaverStrategy implements UserSaverStrategy {


    public List<User> showusers() {
        List<User> usersList = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("textdoc.txt"), StandardCharsets.UTF_8);
            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] rowUser = line.split(";");
                User user = new User(rowUser[0], rowUser[1], rowUser[2]);
                usersList.add(user);
            }
            reader.close();
        } catch (IOException ex) {

        }
        return usersList;

    }



    public void saveusers (User user) {

        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(Paths.get("textdoc.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            writer.write(user.getName() + ";" + user.getEmail() + ";" + user.getPassword());
            writer.newLine();
            writer.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void updateusers(User user) {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("textdoc.txt"), StandardCharsets.UTF_8);
            String line = null;
            List<String> newFile = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String row = line;
                String[] rowUser = line.split(";");
                if(rowUser[0].equals(user.getName())){
                    row = user.getName() + ";" + user.getEmail() + ";" +user.getPassword();
                }
                newFile.add(row);
            }
            reader.close();
            FileWriter fileWriter = new FileWriter("textdoc.txt");
            for (String s : newFile){
                fileWriter.write(s + "\n");
            }
            fileWriter.close();

        } catch (IOException ex) {

        }
    }



    @Override
    public void update(Observable o, Object arg) {
        User user = (User) arg;
        updateusers(user);
    }
}