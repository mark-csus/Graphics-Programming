package com.csus.csc133;

public interface ICollider {
    // Detection algorithm
    public boolean collidesWith(ICollider otherObject);

    // Response algorithm
    public void handleCollision(ICollider otherObject);



    boolean collidesWith(int x, int y);

    // More interactivity (optional?); maybe for use with boundaries?
    //public void collidesWith(int x, int y);
    //public void handleCollision();

    // Possible to handle pointer selection/collision in ViewMap using these?
}
