/*
East (Right) region
Contains CN1 built-in Label to display the game info, including:
- right hand side (East)
- Name of the lecture hall that held the lecture
- Remaining time of the lecture
- Current game time
- Number of absences for the player
- The player Hydration and water intake
- The remaining time that the player can't move
 */

package com.csus.csc133;

import java.util.*;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

public class ViewStatus extends Container implements Observer {

    private GameModel g;
    private Student s;

    private Label lectureHallNameHeader;
    private Label lectureHallName;
    private Label lectureTimeRemainingHeader;
    private Label lectureTimeRemaining;

    private Label currentGameTimeHeader;
    private Label currentGameTime;

    private Label playerAbsencesHeader;
    private Label playerAbsences;

    private Label playerHydrationHeader;
    private Label playerHydration;

    private Label playerWaterIntakeHeader;
    private Label playerWaterIntake;

    private Label timeUntilMoveHeader;
    private Label timeUntilMove;


    // Constructor
    public ViewStatus() {
        this.g = g;
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // setup info
        this.setLectureInfo();
        this.setGameInfo();
        this.setPlayerInfo();
    }

    public void setLectureInfo() {

        // lecture name info
        lectureHallNameHeader = new Label("Lecture Hall Name: ");
        lectureHallName = new Label("NA");
        add(lectureHallNameHeader);
        add(lectureHallName);

        // lecture time info
        lectureTimeRemainingHeader = new Label("Lecture Time Remaining: ");
        lectureTimeRemaining = new Label("0");
        add(lectureTimeRemainingHeader);
        add(lectureTimeRemaining);
    }

    public void setGameInfo() {
        currentGameTimeHeader = new Label("Game Time: ");
        currentGameTime = new Label("0");
        add(currentGameTimeHeader);
        add(currentGameTime);
    }

    public void setPlayerInfo() {

        // player absence info
        playerAbsencesHeader = new Label("Absences: ");
        playerAbsences = new Label("0");
        add(playerAbsencesHeader);
        add(playerAbsences);

        // player hydration info
        playerHydrationHeader = new Label("Hydration: ");
        playerHydration = new Label ("100");
        add(playerHydrationHeader);
        add(playerHydration);

        // player water intake info
        playerWaterIntakeHeader = new Label("Water Intake: ");
        playerWaterIntake = new Label("0");
        add(playerWaterIntakeHeader);
        add(playerWaterIntake);

        //player hold
        timeUntilMoveHeader = new Label("Hold: ");
        timeUntilMove = new Label("0");
        add(timeUntilMoveHeader);
        add(timeUntilMove);

    }

    @Override
    public void update(Observable o, Object obj) {
        g = (GameModel) o;

        this.lectureHallName.setText(g.returnHallName());
        this.lectureTimeRemaining.setText(" " + g.hall.getLecture().getTime());
        this.currentGameTime.setText(" " + g.returnGameTime());
        this.playerAbsences.setText(" " + g.player.getAbsenceTime());
        this.playerHydration.setText(" " + g.player.getHydration());
        this.playerWaterIntake.setText(" " + g.player.getWaterIntake());
        this.timeUntilMove.setText(" " + g.player.getTimeRemain());
    }
}
