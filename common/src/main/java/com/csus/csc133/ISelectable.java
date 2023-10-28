package com.csus.csc133;


import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

public interface ISelectable {

    // mark an object as selected or not
    public void setSelected(boolean b);

    // test whether an object is selected
    public boolean isSelected();

    // determine if a pointer is "in" an object
    // pPtrRelPrnt is pointer position relative to the parent origin
    // pCmpRelPrnt is component position relative to the parent origin
    public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);

    // way to draw the object that knows about drawing
    // different ways depending on "isSelected"
    public void draw(Graphics g, Point pCmpRelPrnt);
}
