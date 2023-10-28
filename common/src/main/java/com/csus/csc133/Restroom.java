/*
Student will clear the water intake if they collide with the Restroom.
 */

package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public class Restroom extends Building {

    // Constructor
    Restroom() {
        super();

    }

    @Override
    void handleCollide(Student s) {
        // Clear water intake
        //setCooldown(100);
        s.useRestroom();
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) this.getX() + (int) this.getSize() + 400;
        int y = (int) this.getY() + (int) this.getSize() + 400;
        int s = (int) this.getSize();

        g.setColor(ColorUtil.rgb(0, 255, 0));
        g.fillRect(x, y, s, s);
    }

    public void draw(Graphics g, Point pCmpRelPrnt) {

        g.setColor(ColorUtil.rgb(0, 255, 0));
        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();

        g.fillRect(xLoc, yLoc, s, s);

        if (this.isSelected()) {
            drawBox(g, pCmpRelPrnt);
        }
    }


}
