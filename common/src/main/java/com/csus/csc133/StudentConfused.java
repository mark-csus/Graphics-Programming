/*
Because of some reason, this student is confused. They are moving on the campus randomly.
To achieve this, you can add a random value to its head at the beginning of its move().
 */

package com.csus.csc133;

public class StudentConfused extends Student {

    @Override
    void move () {
        super.move();
        this.setHead( this.getHead() + 2);
    }
}
