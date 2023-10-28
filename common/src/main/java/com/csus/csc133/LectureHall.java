/*
LectureHall has a String field name, and a Lecture instance. In its handleCollide()method, if the Student is
the StudentPlayer, the current Lecture in this LectureHall end immediately.
 */

package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class LectureHall extends Building {
    private String hallName;
    private Lecture currentLecture = new Lecture(); // Lecture instance

    // Constructor
    LectureHall() {
        super();
        hallName = "Riverside Hall";
    }

    // Getters
    String getHallName() {
        return hallName;
    }
    Lecture getLecture() {
        return currentLecture;
    }

    // Setters
    void setLecture(Lecture l) {
        currentLecture = l;
    }

    // End lecture
    void endLecture() {
       currentLecture.setTime(0);
    }

    @Override
    public boolean collidesWith(ICollider otherObject) {
        return false;
    }

    @Override
    public void handleCollision(ICollider otherObject) {

    }

    @Override
    void handleCollide(Student s) {
        if(s instanceof StudentPlayer)
        {
            this.endLecture();
        }
    }


    @Override
    public void draw(Graphics g) {
        int x = (int) this.getX() + (int) this.getSize();
        int y = (int) this.getY() + (int) this.getSize();
        int s = (int) this.getSize();

        g.setColor(ColorUtil.rgb(0, 0, 255));
        g.fillRect(x, y, s, s);
        g.drawString(this.hallName, x, y+80); // label with the lecture hall name
    }

    public void draw(Graphics g, Point pCmpRelPrnt) {

        g.setColor(ColorUtil.rgb(0, 0, 255));
        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();

        g.fillRect(xLoc, yLoc, s, s);
        if (this.isSelected()) {
            //drawHighlighted(g, pCmpRelPrnt);
            drawBox(g, pCmpRelPrnt);
        }

    }

    @Override
    public String toString() {
        String s;
        if (currentLecture.getTime() == 0)
        {
            s = super.toString() + ", Current Lecture time remaining: null";
        }
        else
        {
            s = super.toString() + ", Current Lecture time remaining: " +  currentLecture.getTime();
        }
        return s;
    }

}
