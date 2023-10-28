/*
In the skeleton, we provide a primary class SacRun, which extends from the CN1 build-in Form class. The Game constructor
instantiates a GameModel and then starts the game by calling A1() method, which can accept commands from the player
and invokes appropriate methods in GameModel to manipulate the game data in the GameModel. To get started, a text
field is provided in the skeleton code and you can modify the handleInput(char key) to process the command
input.
 */

package com.csus.csc133;

import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.UITimer;


public class SacRun extends Form implements Runnable, ActionListener{

     private GameModel gm;

     UITimer timer; //Timer
     Label label;
     int t = 0;

     ViewMap map;

    public SacRun(){
        gm = new GameModel();
        map = new ViewMap(gm);
        //map.b

        // timer
        label = new Label("0");
        add(label);
        show();
        timer = new UITimer(this);
        timer.schedule(20, true, this);
        map.repaint();
        this.show();
        A2();

        gm.init();
    }

    // New GUI for A2
    private void A2() {

        this.setLayout(new BorderLayout());
        map.repaint();

        ViewStatus status = new ViewStatus();
        ViewMessage message = new ViewMessage();
        //map = new ViewMap(gm);
        ViewCommands cmds = new ViewCommands(gm);
        ViewMenu menu = new ViewMenu();

        gm.addObserver(map);
        gm.addObserver(status);
        gm.addObserver(message);
        gm.addObserver(cmds);
        gm.addObserver(menu);

        this.add(BorderLayout.CENTER, map);
        this.add(BorderLayout.EAST, status);
        this.add(BorderLayout.SOUTH, message);
        this.add(BorderLayout.WEST, cmds);

        //toolbar (hamburger menu)
        Toolbar.setGlobalToolbar(true);

        Toolbar myToolbar = new Toolbar();
        setToolbar(myToolbar);
        //Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
        CommandButton changeStrategies = new CommandButton("Change Strategies", gm);
        CommandButton about = new CommandButton("About", gm);
        CommandButton exit = new CommandButton("Exit", gm);

        myToolbar.addComponentToLeftSideMenu(changeStrategies);
        myToolbar.addComponentToLeftSideMenu(about);
        myToolbar.addComponentToLeftSideMenu(exit);

        // top right title bar buttons
        CommandButton lectureHallButton = new CommandButton("Lecture Hall", gm);
        myToolbar.addCommandToRightBar(lectureHallButton.getCommand());

        CommandButton aboutButton = new CommandButton("About", gm);
        myToolbar.addCommandToRightBar(aboutButton.getCommand());

        // map setup
        timer = new UITimer(this);
        timer.schedule(100, true, this);

        gm.init();
        this.show();

        // set GameModel dimensions to reflect the ViewMap size
        gm.setWidth(map.getWidth());
        gm.setHeight(map.getHeight());

        System.out.println("MAP=====Width: " + map.getWidth() + " Height: " + map.getHeight());
        System.out.println("GM=====Width: " + gm.getWidth() + " Height: " + gm.getHeight());
        Display d = Display.getInstance();
        System.out.println(d.getCurrent());

    }

    @Override
    public void run() {
        label.setText(Integer.toString(t++) + " sec "); // timer
        gm.nextFrame();

        // A4 additions
        //map.    ().updateLTs();
        map.repaint();
    }

    private void handleInput(char key) {
        //TODO: add code to handle different key command
        switch (key)
        {
            case 'w':
                // ask StudentPlayer to start moving
                gm.movePlayer();
                break;
            case 's':
                // ask StudentPlayer to stop moving
                gm.stopPlayer();
                break;
            case 'a':
                // StudentPlayer turns left
                gm.goLeft();
                break;
            case 'd':
                // StudentPlayer turns right
                gm.goRight();
                break;
            case '1':
                // collision between StudentPlayer and LectureHall (don't need to check if it actually happens)
                gm.hallCollision();
                break;
            case '2':
                // ollision between StudentPlayer and Restroom
                gm.restroomCollision();
                break;
            case '3':
                // collision between StudentPlayer and WaterDispenser
                gm.waterCollision();
                break;
            case '4':
                // randomly pick a non-player Student instance in the GameModel and collide with StudentPlayer
                gm.studentCollision();
                break;
            case 'f':
                gm.nextFrame();
                break;
            case 'm':
                // output the game info to the console (see instructions for more detail)
                gm.printOutput();
                break;
            default:
                gm.invalidInput();
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println(evt.getEventType());
        System.out.println("actionPerformed in SacRun has been called");
        map.pointerPressed(getX(), getY());
    }

    // handle WASD controls
    @Override
    public void keyPressed(int keyCode) {
        switch (keyCode)
        {
            case 'w':
                gm.movePlayer();
                break;
            case 'a':
                gm.goLeft();
                break;
            case 's':
                gm.stopPlayer();
                break;
            case 'd':
                gm.goRight();
                break;
            default:
                gm.invalidInput();
                break;
        }
    }


}
