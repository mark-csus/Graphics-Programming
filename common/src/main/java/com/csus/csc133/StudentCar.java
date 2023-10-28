/*
This student is riding a car. Its speed is five times that of a regular student, and its sweatingRate
is zero. It can only move horizontally. In other words, its head always rounds to either 90 or 270.
 */

package com.csus.csc133;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class StudentCar extends Student {

    // Constructor
    StudentCar() {
        this.setSpeed(3 * this.getSpeed() );
        this.setSweatingRate(0);

        // let head equal either 90 to 270 (only moves horizontally)
        int rand = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if ( rand == 0) {
            this.setHead(90);
        }
        else if (rand == 1) {
            this.setHead(270);
        }
    }

}
