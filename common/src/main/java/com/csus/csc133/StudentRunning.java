/*
This student is running. Its sweatingRate is two times that of a regular student.
 */

package com.csus.csc133;

public class StudentRunning extends Student{
    //Constructor
    StudentRunning() {
        setSweatingRate(2 * getSweatingRate());
    }
}
