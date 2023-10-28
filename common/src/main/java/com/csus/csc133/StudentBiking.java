/*
This student is riding a bike. Its speed is three times that of a regular student, and its
sweatingRate is two times that of a regular student
 */
package com.csus.csc133;

public class StudentBiking extends Student {
    // Constructor
    StudentBiking() {
        super();
        this.setSpeed(3 * this.getSpeed());
        this.setSweatingRate(2 * this.getSweatingRate());

    }

}
