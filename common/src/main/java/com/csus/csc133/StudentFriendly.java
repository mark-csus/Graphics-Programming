/*
Its talkingTime is half of a regular student as they are friendly.
 */

package com.csus.csc133;

public class StudentFriendly extends Student {

    // Constructor
    StudentFriendly() {
        //super();
        setTalkingTime( getTalkingTime() / 2);
    }
}
