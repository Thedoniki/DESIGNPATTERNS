package com.example.demo.decorator;

public abstract class ToppingsDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingsDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public abstract String getDecorations();

    public abstract Double getCost();
}
