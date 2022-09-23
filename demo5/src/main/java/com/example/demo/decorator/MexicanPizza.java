package com.example.demo.decorator;

public class MexicanPizza implements Pizza {

    @Override
    public String getDecorations() {
        return "";
    }

    @Override
    public Double getCost() {
        return 70.0;
    }
}
