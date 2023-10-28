package com.csus.csc133;

public class StudentStrategy extends Student implements IStrategy {

    @Override
    public void apply() {
        super.setStrategy();
        //set head before move()
        System.out.println("Student strat");
    }

    @Override
    public void move() {
        apply();
        super.move();
    }

    @Override
    public String toString() {
        return(super.toString() + ", Strategy Name : ");
    }
}
