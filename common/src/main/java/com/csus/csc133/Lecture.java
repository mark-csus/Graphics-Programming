/*
Contains lecture information: the end time and which LectureHall it is held in
 */
package com.csus.csc133;

public class Lecture {

    // indicate when the Lecture will end
    double time;

    // Constructor
    Lecture() {
        time = 10;
    }

    // Setters
    void setTime(double t) {
        time = t;
    }

    // Getters
    double getTime() {
        return this.time;
    }


}
