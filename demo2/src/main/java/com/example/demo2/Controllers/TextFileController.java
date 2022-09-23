package com.example.demo2.Controllers;


import com.example.demo2.SaverStrategy;
import com.example.demo2.Models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TextFileController implements SaverStrategy {

    @GetMapping("/textfile/users")
    public String showUseres(Model model){
        List<User> usersList= new ArrayList<>();
        try {
            BufferedReader reader=Files.newBufferedReader(Paths.get("textdoc.txt"), StandardCharsets.UTF_8);
            String line=null;
            while ((line = reader.readLine())!=null){
                String[] rowUser=line.split(";");
                User user=new User(rowUser[0], rowUser[1], rowUser[2]);
                usersList.add(user);
            }
            reader.close();
        } catch (IOException ex){

        }
        model.addAttribute("user", usersList);
        return "userstextfileview";
    }


        @PostMapping("/textfile/saveuser")
        public String saveuser(@ModelAttribute("user") User user){

        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(Paths.get("textdoc.txt"), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            writer.write(user.getName()+ ";" + user.getEmail()+ ";" + user.getPassword());
            writer.newLine();
            writer.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return "redirect:/textfile/users";
    }

    @Override
    public double save(User user) {
        return 0;
    }
}
