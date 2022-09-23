package com.example.demo.decorator;

public class Schrimps extends ToppingsDecorator{
    public Schrimps(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDecorations(){
        return this.pizza.getDecorations() + "Schrimps,  ";
    }
    @Override
    public Double getCost() {
        return this.pizza.getCost() + 5.0;
    }
}


