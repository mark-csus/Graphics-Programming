package com.csus.csc133;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;



public class ActionCommand extends Command {



    private GameModel model;

    public ActionCommand(String command) {
        super(command);


    }

    public ActionCommand(String command, GameModel m){
        super(command);
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {


        switch(getCommandName())
        {
            case "Move":
                model.movePlayer();
                //System.out.println("moving...");
                break;
            case "Stop":
                model.stopPlayer();
                //System.out.println("stopping...");
                break;
            case "Turn Left":
                //System.out.println("turning left...");
                model.goLeft();
                break;
            case "Turn Right":
                //System.out.println("turning right...");
                model.goRight();
                break;
            case "Change Strategies":
                model.player.setStrategy();
                //System.out.println("changing strategies...");
                break;
            case "Lecture Hall":
                model.hallCollision();
                //System.out.println("lecture hall collision...");
                break;
            case "Restroom":
                model.restroomCollision();
                //System.out.println("restroom collision...");
                break;
            case "Water Dispenser":
                model.waterCollision();
                //System.out.println("water dispenser collision...");
                break;
            case "Student":
                model.studentCollision();
                //System.out.println("student collision...");
                break;
            case "Next Frame":
                model.nextFrame();
                //System.out.println("next frame...");
                break;
            case "Exit":
                System.out.println("Do you want to exit?");
                // pop out a dialogue box to confirm exit
                Boolean bOk = Dialog.show("Confirm quit?",
                        "Are you sure you want to quit?", "Yes", "No");
                if (bOk)
                    Display.getInstance().exitApplication();
                break;
            case "About":
                //model.printOutput(); // same as "m" command in A1
                System.out.println("A2, Mark Burgasser, Summer 2023"); // change to dialog box
                Dialog.show("About", "A2, Mark Burgasser, Summer 2023", "Confirm", "");
                break;
            default:
                System.out.println("unexpected input....");
                break;

        }
    }
}
