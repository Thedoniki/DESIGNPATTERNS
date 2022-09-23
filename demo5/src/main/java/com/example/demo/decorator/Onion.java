package com.example.demo.decorator;

public class Onion extends ToppingsDecorator{

    public Onion(Pizza pizza) {
        super(pizza);
    }
    @Override
    public String getDecorations(){
        return this.pizza.getDecorations() + "Onion,  ";
    }
    @Override
    public Double getCost() {
        return this.pizza.getCost() + 5.0;
    }
}
