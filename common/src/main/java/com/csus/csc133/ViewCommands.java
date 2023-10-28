package com.csus.csc133;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayoutStyle;


public class ViewCommands extends Container implements Observer {

    // Constructor
    public ViewCommands() {
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Label header = new Label("Commands: " );
        add(header);
        CommandButton moveButton = new CommandButton("Move");
        add(moveButton);
        CommandButton stopButton = new CommandButton("Stop");
        add(stopButton);
        CommandButton turnLeftButton = new CommandButton("Turn Left");
        add(turnLeftButton);
        CommandButton turnRightButton = new CommandButton("Turn Right");
        add(turnRightButton);
        CommandButton changeStrategiesButton = new CommandButton("Change Strategies");
        add(changeStrategiesButton);
        CommandButton lectureHallButton = new CommandButton("Lecture Hall");
        add(lectureHallButton);
        CommandButton restroomButton = new CommandButton("Restroom");
        add(restroomButton);
        CommandButton waterDispenserButton = new CommandButton("Water Dispenser");
        add(waterDispenserButton);
        CommandButton studentButton = new CommandButton("Student");
        add(studentButton);
        CommandButton nextFrameButton = new CommandButton("Next Frame");
        add(nextFrameButton);
    }

    // Constructor w/ GameModel
    public ViewCommands(GameModel m) {

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        CommandButton moveButton = new CommandButton("Move", m);
        add(moveButton);
        CommandButton stopButton = new CommandButton("Stop", m);
        add(stopButton);
        CommandButton turnLeftButton = new CommandButton("Turn Left", m);
        add(turnLeftButton);
        CommandButton turnRightButton = new CommandButton("Turn Right", m);
        add(turnRightButton);
        CommandButton changeStrategiesButton = new CommandButton("Change Strategies", m);
        add(changeStrategiesButton);
        CommandButton lectureHallButton = new CommandButton("Lecture Hall", m);
        add(lectureHallButton);
        CommandButton restroomButton = new CommandButton("Restroom", m);
        add(restroomButton);
        CommandButton waterDispenserButton = new CommandButton("Water Dispenser", m);
        add(waterDispenserButton);
        CommandButton studentButton = new CommandButton("Student", m);
        add(studentButton);
        CommandButton nextFrameButton = new CommandButton("Next Frame", m);
        add(nextFrameButton);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

}
