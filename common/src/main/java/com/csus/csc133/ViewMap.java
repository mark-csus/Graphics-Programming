/*
Center region
- Empty container with a red solid line border
 */

package com.csus.csc133;

import java.util.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;


public class ViewMap extends Container implements Observer {

    private double height;
    private double width;

    private GameModel m;

    int startX, startY = 0;

    // A4 additions
    Transform worldToND, ndToDisplay, theVTM;
    private float winLeft, winBottom, winRight, winTop;
    private float winWidth, winHeight;


/*
    // Constructor
    public ViewMap() {
        //height = this.getHeight();
        //width = this.getWidth();

        // mouse selection
        //this.addPointerDraggedListener(this::actionPerformed);
        //this.addPointerReleasedListener(this);
        //this.addPointerPressedListener(this);


        this.getAllStyles().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255, 0, 0)));
        this.setLayout(new BorderLayout());
        this.getAllStyles().setBgColor(ColorUtil.WHITE);
        this.getAllStyles().setBgTransparency(255);

        //Display d = Display.getInstance();

    }



 */

    // Constructor w/ GameModel
    public ViewMap(GameModel m) {

        this.getAllStyles().setBorder(Border.createLineBorder(10, ColorUtil.rgb(255, 0, 0)));
        this.setLayout(new BorderLayout());

        winLeft = 0;
        winBottom = 0;
        winRight = getWidth()/2;
        winTop = getHeight()/2;
        winWidth = 1365;
        winHeight = 1353; //winTop - winBottom;
/*
        IIterator theElements = m.currentCollection.getIterator();
        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            obj.setMax(this.getWidth(), this.getHeight());
            obj.translate((winWidth/2), winHeight/2);
            obj.rotate(45);
            obj.scale(1, 2);
        }

 */
        m.printOutput();
    }

    // Getters & Setters
    double getMapWidth() {
        return this.width;

    }
    double getMapHeight() {
        return this.height;
    }

    // collision logic
    public void tick() {
        //for(GameObject object: object)
    }


    // size setters
    void setWidth(double w) {
        this.width = w;
    }
    void setHeight  (double h) {
        this.height = h;
    }

    public void pointerReleased(int x, int y) {
        //startX = x;
        //startY = y;

        //repaint();

    }
/*
    public void pointerDragged(int x, int y) {
        startX = x;
        startY = y;

        repaint();
    }
 */

    public void pointerPressed(int x, int y) {
        //System.out.println("pointerPressed in ViewMap called (from SacRun - actionPerformed)");

        x -= getParent().getAbsoluteX();
        y -= getParent().getAbsoluteY();

        Point pPtrRelPrnt = new Point( (int) x, (int) y);
        Point pCmpRelPrnt = new Point(getX(), getY());

        IIterator theElements = m.getCollection().getIterator();

        while(theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            ISelectable selected = (ISelectable) obj;

            if (selected.contains(pPtrRelPrnt, pCmpRelPrnt))
            {
                obj.setSelected(true);
                System.out.println("setSelected is TRUE");
            }
            else
            {
                obj.setSelected(false);
                System.out.println("setSelected is FALSE");
            }
            //repaint();
        }
        repaint();
    }

    public void paint(Graphics g, Point pCmpRelPrnt) {
        super.paint(g);
        IIterator theElements = m.getCollection().getIterator();

        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            obj.draw(g, pCmpRelPrnt);
        }
    }

    public void paint(Graphics g) {
/*
        super.paint(g);
        winWidth = winRight - winLeft;
        winHeight = winTop  - winBottom;
        //...[calculate winWidth and winHeight]
        // construct the Viewing Transformation Matrix
        worldToND = buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
        ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
        theVTM =  ndToDisplay.copy();
        theVTM.concatenate(worldToND); // worldToND will be applied first to points!
        // concatenate the VTM onto the gs current transformation (do not forget to apply local 		//origin transformation)
        Transform gXform = Transform.makeIdentity();
        g.getTransform(gXform);
        gXform.translate(getAbsoluteX(),getAbsoluteY()); //local origin xform (part 2)
        gXform.concatenate(theVTM); //VTM xform
        gXform.translate(-getAbsoluteX(),-getAbsoluteY()); //local origin xform (part 1)
        g.setTransform(gXform);

        Point pCmpRelPrnt = new Point(this.getX(), this.getY());
        Point pCmpRelScreen = new Point(getAbsoluteX(), getAbsoluteY()); // new for A4
        IIterator theElements = m.getCollection().getIterator();
        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            obj.draw(g, pCmpRelPrnt);
            //obj.draw(g, pCmpRelPrnt, pCmpRelScreen);
            //g.resetAffine();
        }
        g.resetAffine();

 */

        // calculate winWidth & winHeight

        winWidth = winRight - winLeft;
        winHeight = winTop  - winBottom;

        // construct VTM
        worldToND = buildWorldToNDXform(winWidth, winHeight, winLeft, winBottom);
        ndToDisplay = buildNDToDisplayXform(this.getWidth(), this.getHeight());
        theVTM = ndToDisplay.copy();
        theVTM.concatenate(worldToND);

        // apply

        Transform gXform = Transform.makeIdentity();
        g.getTransform(gXform);
        gXform.translate(getAbsoluteX(), getAbsoluteY()); // local origin
        gXform.concatenate(theVTM);
        gXform.translate(-getAbsoluteX(), -getAbsoluteY());
        g.setTransform(gXform);

        // tell each shape to draw itself using the g (which contains the VTM)
        Point pCmpRelPrnt = new Point(this.getX(), this.getY());
        Point pCmpRelScreen = new Point(getAbsoluteX(), getAbsoluteY()); // new for A4
        IIterator theElements = m.getCollection().getIterator();
        while (theElements.hasNext())
        {
            GameObject obj = (GameObject) theElements.getNext();
            //obj.draw(g, pCmpRelPrnt);
            obj.draw(g, pCmpRelPrnt, pCmpRelScreen);
            g.resetAffine();
        }
        //g.resetAffine();


    }

    private Transform buildWorldToNDXform(float winWidth, float winHeight, float winleft, float winBottom) {
        Transform  tmpXfrom = Transform.makeIdentity();
        tmpXfrom.scale( (1/winWidth) , (1/winHeight) );
        tmpXfrom.translate(-winLeft,-winBottom);
        return tmpXfrom;
    }

    private Transform buildNDToDisplayXform(float displayWidth, float displayHeight) {
        Transform tmpXfrom = Transform.makeIdentity();
        tmpXfrom.translate(0, displayHeight);
        tmpXfrom.scale(displayWidth, -displayHeight);
        return tmpXfrom;
    }
/*
    public void zoom(float factor) {
        //positive factor would zoom in (make the worldWin smaller), suggested value is 0.05f
        //negative factor would zoom out (make the worldWin larger), suggested value is -0.05f
        //...[calculate winWidth and winHeight]
        float newWinLeft = winLeft + winWidth*factor;
        float newWinRight = winRight - winWidth*factor;
        float newWinTop = winTop - winHeight*factor;
        float newWinBottom = winBottom + winHeight*factor;
        float newWinHeight = newWinTop - newWinBottom;
        float newWinWidth = newWinRight - newWinLeft;
        //in CN1 do not use world window dimensions greater loat newWinHeight = newWinTop - newWinBottom; than 1000!!!
        if (newWinWidth <= (float) 1000 && newWinHeight <= (float) 1000 && newWinWidth > (float) 0 && newWinHeight > (float) 0 ){
            winLeft = newWinLeft;
            winRight = newWinRight;
            winTop = newWinTop;
            winBottom = newWinBottom;
            winWidth = newWinWidth;
            winHeight = newWinHeight;
        }
        else
            System.out.println("Cannot zoom further!");
        this.repaint();

    }

 */
    public void panHorizontal(double delta) {
        //positive delta would pan right (image would shift left), suggested value is 5
        //negative delta would pan left (image would shift right), suggested value is 5
        winLeft += delta;
        winRight += delta;
        this.repaint();
    }
    public void panVertical(double delta) {
        //positive delta would pan up (image would shift down), suggested value is 5
        //negative delta would pan down (image would shift up), suggested value is 5
        winBottom += delta;
        winTop += delta;
        this.repaint();
    }
/*
    @Override
    public boolean pinch(float scale){
        if(scale < 1.0){
            //Zooming Out: two fingers come closer together (on actual device), right mouse
            //click + drag towards the top left corner of screen (on simulator)
            zoom(-0.05f);
        }else if(scale>1.0){
            //Zooming In: two fingers go away from each other (on actual device), right mouse
            //click + 	drag away from the top left corner of screen (on simulator)
            zoom(0.05f);
        }
        return true;
    }

 */

    private Point pPrevDragLoc = new Point(-1, -1);
    @Override
    public void pointerDragged(int x, int y)
    {
        if (pPrevDragLoc.getX() != -1)
        {
            System.out.println("x " + x + " pPrevDragLoc.getX() " + pPrevDragLoc.getX());
            if (pPrevDragLoc.getX() < x) {
                panHorizontal(5);
                System.out.println("Pan horizontal positive 5");
            }
            else if (pPrevDragLoc.getX() > x) {
                panHorizontal(-5);
                System.out.println("Pan horizontal negative 5");
            }
            if (pPrevDragLoc.getY() < y)
                panVertical(-5);
            else if (pPrevDragLoc.getY() > y)
                panVertical(5);
        }
        pPrevDragLoc.setX(x);
        pPrevDragLoc.setY(y);
    }

    @Override
    public void update(Observable o, Object arg) {
        m = (GameModel) o;
        m.printOutput();
        this.repaint();
    }


}
