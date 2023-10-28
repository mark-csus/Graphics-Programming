package com.csus.csc133;

import java.util.concurrent.ThreadLocalRandom;

public class RandomStrategy extends StudentStrategy {
    @Override
    public void apply() {
        this.setHead( this.getHead() + ThreadLocalRandom.current().nextInt(0, 360 + 1));
    }
}
