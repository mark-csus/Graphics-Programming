/*
Student will drink water if they collide with the WaterDispenser
 */

package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class WaterDispenser extends Building {

    // Constructor
    WaterDispenser() {
        super();
    }

    @Override
    public boolean collidesWith(ICollider otherObject) {
        return false; // Placeholder
    }


    @Override
    void handleCollide(Student s) {
        // Have student drink water
        System.out.println("Student drank water.");
        s.drinkWater();
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) this.getX();
        int y = (int) this.getY() ;
        int s = (int) this.getSize();

        g.setColor(ColorUtil.rgb(0, 0, 255));
        g.fillArc(x, y, s, s, 0, 360);
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(ColorUtil.rgb(255, 0, 0));

        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();

        g.setColor(ColorUtil.rgb(0, 0, 255));
        g.fillArc(xLoc, yLoc, s, s, 0, 360);

        if (this.isSelected()) {
            drawBox(g, pCmpRelPrnt);
        }
    }
}
