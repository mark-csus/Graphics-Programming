/*
This student is happy. At some probability, this student may suddenly jump forward. To achieve
this, you can increase the speed in its move() with probability and set it back to normal after move().
 */

package com.csus.csc133;

import java.util.concurrent.ThreadLocalRandom;

public class StudentHappy extends Student {

    void move() {
        super.move();
        setSpeed(getSpeed() + ThreadLocalRandom.current().nextInt(0,  3)); // add a number 0-60 to speed
        setSpeed(DEFAULT_SPEED);
    }
}