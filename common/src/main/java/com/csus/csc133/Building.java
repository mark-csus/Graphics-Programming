/*
There are two children from GameObject. one is Building which is an abstract class. Its property is that it cannot move. In
other words, it do nothing when calling move() method.

There are three different concrete buildings: LectureHall, Restroom, and WaterDispenser.
 */
package com.csus.csc133;

abstract class Building extends GameObject {

    // Constructor
    Building() {
        super();
    }

    @Override
    public boolean collidesWith(ICollider otherObject) {
        return false;
    }

    @Override
    public void handleCollision(ICollider otherObject) {

    }

    @Override
    void move() {
        // do nothing (buildings can't move)
    }

    @Override
    public String toString() {
        return(super.toString());
    }
}
