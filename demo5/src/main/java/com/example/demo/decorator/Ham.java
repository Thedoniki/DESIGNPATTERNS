package com.example.demo.decorator;

public class Ham extends ToppingsDecorator{

    public Ham(Pizza pizza) {
        super(pizza);
    }
    @Override
    public String getDecorations(){
        return this.pizza.getDecorations() + "Ham,  ";
    }

    @Override
    public Double getCost() {
        return this.pizza.getCost() + 5.0;
    }
}
