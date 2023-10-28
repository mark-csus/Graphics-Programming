package com.csus.csc133;

import java.util.concurrent.ThreadLocalRandom;

public class VerticalStrategy extends StudentStrategy{

     @Override public void apply() {
         int rand = ThreadLocalRandom.current().nextInt(0, 1 + 1);
         if (rand == 0)
             this.setHead(0);
         else if (rand == 1)
             this.setHead(180);
        }


}
