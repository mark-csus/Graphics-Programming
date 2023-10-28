/*
This student does not care about other students. They do not stop even if they collide with
others. Their timeRemain cannot be changed
 */
package com.csus.csc133;

public class StudentNonstop extends Student{

    // Constructor
    StudentNonstop() {
        super();
        final double timeRemain = 0;
    }

    @Override
    void handleCollide(Student s) {
        // do nothing (do not stop when there is a collission)
    }
}
