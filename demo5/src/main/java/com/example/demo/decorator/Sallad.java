package com.example.demo.decorator;

public class Sallad extends ToppingsDecorator{

    public Sallad(Pizza pizza) {
        super(pizza);
    }
    @Override
    public String getDecorations(){
        return this.pizza.getDecorations() + "Sallad,  ";
    }
    @Override
    public Double getCost() {
        return this.pizza.getCost() + 5.0;
    }
}


