/*
This is the student that you controlled. You need to provide methods to start moving (i.e., set the
speed to DEFAULT_SPEED), to stop moving (i.e., set the speed to zero), turn left (i.e., add -5 to the head) and
turn right (i.e., add 5 to the head).
 */

package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class StudentPlayer extends Student {

    private static StudentPlayer p; // track existence of player

    // Singleton
    public static Student getStudent() {
        if (p == null)
        {
            p = new StudentPlayer();
        }
        return p;
    }
    void startMoving() { // not used thus far?
        setSpeed(DEFAULT_SPEED);
        move();
    }

    void stopMoving() {
        setSpeed(0);
    }

    void turnLeft() {
        setHead( getHead() - 20);
    }

    void turnRight() {
        setHead( getHead() + 20);
    }

    @Override
    public void handleCollision(ICollider otherObject) {
        System.out.println("handleCollision in StudentPlayer called");
        //setCooldown(10);
        //otherObject.handleCollision(this);

        /*
        if(otherObject instanceof Student)
        {
            this.setSpeed(0); // player stops to talk
            this.setTimeRemain(10);
        }

        if(otherObject instanceof WaterDispenser)
        {
            this.setHydration(DEFAULT_HYDRATION);
        }

         */
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(ColorUtil.rgb(255, 0, 0));
        int x = (int) (this.getX() +  super.getX() + this.getSize());
        int y = (int) (this.getY() + super.getY() + this.getSize());
        int s = (int) this.getSize();

        int[] xPoints = {x, x + s/2, x + s};
        int[] yPoints = {y, y + s/2, y};

        g.fillPolygon(xPoints, yPoints, 3);
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(ColorUtil.rgb(255, 0, 0));

        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();

        int[] xPoints = {xLoc, xLoc + s/2, xLoc+s};
        int[] yPoints = {yLoc, yLoc + s, yLoc};

        g.fillPolygon(xPoints, yPoints, 3);

        if (this.isSelected()) {
            drawBox(g, pCmpRelPrnt);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Absence: " + this.getAbsenceTime() + ", WaterIntake: " + this.getWaterIntake();
    }
}