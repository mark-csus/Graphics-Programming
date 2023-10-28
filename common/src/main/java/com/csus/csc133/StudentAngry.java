/*
Its talkingTime is two times of a regular student as they are angry.
 */
package com.csus.csc133;

public class StudentAngry extends Student {

    //Constructor
    StudentAngry() {
        super();
        this.setTalkingTime(2 * this.getTalkingTime());

    }
}
