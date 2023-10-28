/*
Student will drink water if they collide with the WaterDispenser. And clear the water intake if they collide with
the Restroom.

Student class is another GameObject children. It can move in each frame of the game. When a Student collides with other
Student, they cannot move for certain seconds (for talking). A Student contains the following fields:
 */

package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.geom.Point;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

abstract class Student extends GameObject {

    static final double DEFAULT_SPEED = 1;
    static final double DEFAULT_TALKINGTIME = 1;
    static final double DEFAULT_HYDRATION = 100;
    static final double DEFAULT_SWEATINGRATE = 1;

    private double head;// represents facing direction in degrees
    private double speed;
    private double talkingTime;
    private double timeRemain; // start at talkingTime and need to update every 1s to be 1s less
    private double Hydration; // how much water is left in their body
    private double waterIntake; // how much water the Student drank
    private double sweatingRate; // how much water is lost per second
    private double absenceTime; // how many times the student is absent in the lecture
    double elapsedTime = 20;
    double conversion = elapsedTime/1000;

    private IStrategy currentStrategy;
    private IStrategy aStrategy;

    Random rand = new Random();

    // A4 additions
    int base, height;
    Point top, bLeft, bRight;
    int myColor;
    Transform myRotation, myTranslation, myScale;

    // Constructor
     Student() {
        // Set initial values
        head = ThreadLocalRandom.current().nextInt(0, 359 + 1); // represents facing direction in degrees
        speed = DEFAULT_SPEED; // represents the moving speed
        talkingTime = DEFAULT_TALKINGTIME; // represents how many seconds they talk when colliding with others
        timeRemain = 0; // start at talkingTime and need to update every 1s to be 1s less
        Hydration = DEFAULT_HYDRATION; // how much water is left in their body
        waterIntake = 0; // how much water the Student drank
        sweatingRate = DEFAULT_SWEATINGRATE; // how much water is lost per second
        absenceTime = 0; // how many times the student is absent in the lecture

         // Triangle handling
         height = (int) this.getSize();
         base = (int) this.getSize();
         top = new Point(0, height/2);
         bLeft = new Point(-base/2, -height/2);
         bRight = new Point(base/2, -height/2);

         myColor = ColorUtil.rgb(255, 0, 0);
         myRotation = Transform.makeIdentity();
         myTranslation = Transform.makeIdentity();
         myScale = Transform.makeIdentity();

    }


    // Getters
    double getSpeed() {
        return speed;
    }
    double getHead() {
        return head;
    }
    double getTimeRemain() {
        return timeRemain;
    }
    double getWaterIntake(){
        return waterIntake;
    }
    double getHydration() {
        return Hydration;
    }
    double getAbsenceTime(){
        return absenceTime;
    }
    double getSweatingRate(){
        return sweatingRate;
    }
    double getTalkingTime(){
        return talkingTime;
    }

    // Setters
    void setSpeed(double s) {
        speed = s;
    }
    void setHead(double h) {
        head = h;
    }
    void setTimeRemain(double t) {
        timeRemain = t;
    }
    void setWaterIntake (double f) {
        waterIntake = f;
    }
    void setHydration(double h) {
        Hydration = h;
    }
    void setAbsenceTime(double a){
        absenceTime = a;
    }
    void setSweatingRate(double r)
    {
        sweatingRate = r;
    }
    void setTalkingTime(double t){
        talkingTime = t;
    }


    void drinkWater() {
        //this.waterIntake = this.waterIntake + (DEFAULT_HYDRATION - this.Hydration); // increase by the value change in Hydration
        waterIntake = waterIntake + 5;
        this.Hydration = DEFAULT_HYDRATION; // Hydration goes back to default value
    }

    void useRestroom() {
        waterIntake = 0;
    }


    @Override
    public void handleCollision(ICollider otherObject) {
        //((GameObject) otherObject).handleCollide(this);
        System.out.println("handleCollision in Student called");
        //setCooldown(10);
        if(otherObject instanceof Student)
        {
            this.setSpeed(0); // player stops to talk
            this.setTimeRemain(10);
        }
    }

    @Override
    void handleCollide(Student s) {
        s.setSpeed(0); // player stops to talk
        s.setTimeRemain(10);
    }

    public void setStrategy() {
         int r = rand.nextInt(3);

         switch (r)
         {
             case 0: //random
                 aStrategy = new RandomStrategy();
                 this.currentStrategy = aStrategy;
                 System.out.println("Strategy: Random");
                 break;
             case 1: // horizontal
                 aStrategy = new HorizontalStrategy();
                 this.currentStrategy = aStrategy;
                 System.out.println("Strategy: Horizontal");
                 break;
             case 2: // vertical
                 aStrategy = new VerticalStrategy();
                 this.currentStrategy = aStrategy;
                 System.out.println("Strategy: Vertical");
                 break;
             default:
                 System.out.println("Unexpected input");
                 break;
         }
    }


    void move() {
        if(this.timeRemain > 0) // If student is talking
        {
            this.timeRemain = this.timeRemain - 1;
            this.setCooldown(this.getCooldown() - 1);
        }
        else // Student is not talking
        {
            if (!inBounds())
            {
                this.setHead(this.getHead() + 180);
            }
            double xVal = (Math.round(this.getX() + Math.cos(Math.toRadians(90 - this.head)) * this.speed));
            double yVal = Math.round(this.getY() + Math.sin(Math.toRadians(90 - this.head)) * this.speed);

            this.setX(xVal);
            this.setY(yVal);

            this.Hydration = this.Hydration - this.sweatingRate;
        }
    }

    // Rotate, translate, and scale methods
    // Defining in GameObject class instead
    /*
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

     */

    // New draw method for A4
    public void draw(Graphics g, Point pCmpRelPrnt, Point pCmpRelScrn) {
        System.out.println("draw(g, p, p) in Student was called");
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

        /*
         g.drawLine(pCmpRelPrnt.getX() + top.getX(), pCmpRelPrnt.getY() + top.getY(), pCmpRelPrnt.getX() + bLeft.getX(), pCmpRelPrnt.getY() + bLeft.getY());

         int[] xPoints = {pCmpRelPrnt.getX() + top.getX(), pCmpRelPrnt.getX() + bLeft.getX(), pCmpRelPrnt.getX() + bRight.getX()};
         int[] yPoints = {pCmpRelPrnt.getX() + top.getY(), pCmpRelPrnt.getX() + bLeft.getY(), pCmpRelPrnt.getX() + bRight.getY()};
         g.drawPolygon(xPoints, yPoints, 3);

         */



        //int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        //int yLoc = pCmpRelPrnt.getY() + (int) this.getY();

        int xLoc = pCmpRelPrnt.getX() + bLeft.getX();
        int yLoc = pCmpRelPrnt.getY() + bLeft.getY();
        int s = (int) getSize();

        int[] xPoints = {xLoc, xLoc + s/2, xLoc+s};
        int[] yPoints = {yLoc, yLoc + s, yLoc};

        g.drawPolygon(xPoints, yPoints, 3);

        if (this.isSelected()) {
            drawBox(g, pCmpRelPrnt);
        }


    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        g.setColor(ColorUtil.rgb(255, 0, 0));

        int xLoc = pCmpRelPrnt.getX() + (int) this.getX();
        int yLoc = pCmpRelPrnt.getY() + (int) this.getY();
        int s = (int) getSize();

        int[] xPoints = {xLoc, xLoc + s/2, xLoc+s};
        int[] yPoints = {yLoc, yLoc + s, yLoc};

        g.drawPolygon(xPoints, yPoints, 3);

        if (this.isSelected()) {
            drawBox(g, pCmpRelPrnt);
        }
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(ColorUtil.rgb(255, 0, 0));
        int x = (int) (this.getX() +  super.getX() + this.getSize());
        int y = (int) (this.getY() + super.getY() + this.getSize());
        int s = (int) this.getSize();

        int[] xPoints = {x, x + s/2, x + s};
        int[] yPoints = {y, y + s/2, y};

        g.drawPolygon(xPoints, yPoints, 3);
    }



    @Override
    public String toString() {
        return(super.toString() + ", head: " + this.getHead()
                + ", speed: " + this.getSpeed() + ", hydration: " + this.getHydration()
                + ", timeRemain: " + this.getTimeRemain() );
    }
}
