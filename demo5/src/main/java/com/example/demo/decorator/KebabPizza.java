package com.example.demo.decorator;

public class KebabPizza implements Pizza {

    @Override
    public String getDecorations() {
        return "Kebab Pizza";
    }

    @Override
    public Double getCost() {
        return 50.0;
    }
}
