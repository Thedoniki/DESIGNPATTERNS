package com.example.demo.controllers;

import com.example.demo.decorator.*;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DecoratorController {
    @GetMapping("/pizzadecorator")
    public String pizzadecorator(Model model) {

        Pizza MexicanPizza = new Ham(new Onion(new Sallad(new Schrimps(new MexicanPizza()))));
       /* Pizza KebabPizza = new Ham(new Onion(new Sallad(new MexicanPizza())));

        model.addAttribute("pizza", KebabPizza.getDecorations());
        model.addAttribute("cost", KebabPizza.getCost());*/
        model.addAttribute("pizza", MexicanPizza.getDecorations());
        model.addAttribute("cost", MexicanPizza.getCost());

        return "pizzadecoratorview";


    }
}
