/*
GameObject class is an abstract class. It contains x and y variables to define the object position in the world. It also provide
two abstract methods: move() and handleCollide(Student s), to represent any GameObject can move and
interact with Student class after collision. You do not need to detect collision in any handleCollide() method
 */

package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

import java.util.Random;

abstract class GameObject implements ICollider, ISelectable {

    private double x, y; // position of object in the world
    private double size;
    public Random rand = new Random();

    private boolean isSelected;
    //private boolean isColliding;

    private double cooldown;
    private double maxX;
    private double maxY;

    // New A4
    int base, height;
    Point top, bLeft, bRight;
    int myColor;
    Transform myRotation, myTranslation, myScale;

    // Constructor
    GameObject() {
        // Give it a random location within the boundaries
        x = (Math.round((1000.0 * rand.nextDouble() * 10.0)) / 10.0);
        y = (Math.round((1000.0 * rand.nextDouble() * 10.0)) / 10.0);
        size = 80;

        // A4 translation / triangle handling
        height = 40;
        base = 40;
        top = new Point(0, height/2);
        bLeft = new Point(-base/2, -height/2);
        bRight = new Point(base/2, -height/2);

        myColor = ColorUtil.rgb(255, 0, 0);
        myRotation = Transform.makeIdentity();
        myTranslation = Transform.makeIdentity();
        myScale = Transform.makeIdentity();
    }

    // Getters
    double getX () {
        //return this.x;
        return myTranslation.getTranslateX();
    }
    double getY () {
        //return this.y;
        return myTranslation.getTranslateY();
    }
    double getSize() {
        return this.size;
    }
    double getMaxX() {
        return this.maxX;
    }
    double getMaxY() {
        return this.maxY;
    }
    double getCooldown()
    {
        return this.cooldown;
    }


    // Setters
    void setX ( double a){
        this.x = a;
    }
    void setY ( double b){
        this.y = b;
    }
    void setSize(double s) {
        this.size = s;
    }
    void setMax(double a, double b) {
        this.maxX = a;
        this.maxY = b;
    }
    void setCooldown(double c) {
        this.cooldown = c;
    }


    public String toString () {
        return(getClass().getSimpleName() + ", pos (" +  this.getX() + "," + this.getY() + ")");
    }

    boolean inBounds() {
        if ((this.getX() >= this.getMaxX() - this.getSize()) || (this.getY() >= this.getMaxY() - this.getSize()) || (this.getX() <= 0) || (this.getY() <= 0))
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    public boolean collidesWith(int x, int y) {
        return false;
    }

    // Collision detection; return true when there is a collision & false when there is not
    public boolean collidesWith(ICollider otherObject) {
        double dx = ((GameObject)otherObject).getX() - this.getX();
        double dy = ((GameObject)otherObject).getY() - this.getY();

        if (dx * dx + dy * dy < 50)
            return true;
        return false;
    }

    @Override
    public void setSelected(boolean b) {
        isSelected = b;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
        int px = pPtrRelPrnt.getX();
        int py = pPtrRelPrnt.getY();
        int xLoc = pCmpRelPrnt.getX() + (int) this.x;
        int yLoc = pCmpRelPrnt.getY() + (int) this.y;
        if ( (px >= xLoc) && (px <= xLoc + this.size) && (py >= yLoc) && (py <= yLoc + this.size))
        {
            return true;
        }
        else {
            return false;
        }

    }

    public void drawBox(Graphics g, Point pCmpRelPrnt){
        g.setColor(ColorUtil.rgb(255, 0, 0));
        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();

        g.drawRect(xLoc, yLoc, s, s);
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {

        g.setColor(ColorUtil.rgb(0, 0, 255));
        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();


        //if (this.isSelected())
        //{
        //    //g.fillRect(xLoc, yLoc, s, s);
        //    drawBox();
        //}

    }

    public void draw(Graphics g) {
        int x = (int) (this.getX() + this.getX() + this.getSize());
        int y = (int) (this.getY() + this.getY() + this.getSize());
        int s = (int) this.getSize();
    }

    abstract public void handleCollision(ICollider otherObject);
    // Response to a collision (what the Object does assuming a collision is detected)

    abstract void move (); //
    abstract void handleCollide (Student s); // takes Student s as parameter, does not need to detect collision

    public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
        System.out.println("draw(g, p, p) in GameObject was called");

        g.setColor(myColor);

        Transform gXform = Transform.makeIdentity();
        g.getTransform(gXform);
        Transform gOrigXform = gXform.copy();
        gXform.translate(pCmpRelScrn.getX(),pCmpRelScrn.getY());
        gXform.translate(myTranslation.getTranslateX(), myTranslation.getTranslateY());
        gXform.concatenate(myRotation);
        gXform.scale(myScale.getScaleX(), myScale.getScaleY());
        gXform.translate(-pCmpRelScrn.getX(),-pCmpRelScrn.getY());
        g.setTransform(gXform);
        //draw the lines as before

        g.drawLine (pCmpRelPrnt.getX()+ top.getX(), pCmpRelPrnt.getY()+top.getY(),pCmpRelPrnt.getX()+bLeft.getX(),	pCmpRelPrnt.getY()+bLeft.getY());
        g.drawLine (pCmpRelPrnt.getX()+bLeft.getX(), pCmpRelPrnt.getY()+bLeft.getY(), pCmpRelPrnt.getX()+bRight.getX(), pCmpRelPrnt.getY()+bRight.getY());
        g.drawLine (pCmpRelPrnt.getX()+bRight.getX(),pCmpRelPrnt.getY()+bRight.getY(),pCmpRelPrnt.getX()+top.getX(), pCmpRelPrnt.getY()+top.getY());
        g.setTransform(gOrigXform);

    }

    // Rotate, translate, and scale methods
    public void rotate(float degrees) {
        myRotation.rotate((float) Math.toRadians(degrees), 0, 0);
    }
    public void translate(float tx, float ty) {
        myTranslation.translate(tx, ty);
    }
    public void scale(float sx, float sy) {
        myScale.scale(sx, sy);
    }
    public void resetTransform() {
        myRotation.setIdentity();
        myTranslation.setIdentity();
        myScale.setIdentity();
    }



}



