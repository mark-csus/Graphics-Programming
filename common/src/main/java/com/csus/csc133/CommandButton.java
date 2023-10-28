package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;

public class CommandButton extends Button {

    public CommandButton(String s) {
        this.setCommand(new ActionCommand(s));
        this.getStyle().setBgTransparency(255);
        this.getStyle().setFgColor(ColorUtil.WHITE);
        this.getStyle().setBgColor(ColorUtil.BLUE);
    }

    public CommandButton(String s, GameModel m) {
        this.setCommand(new ActionCommand(s, m));
        this.getStyle().setBgTransparency(255);
        this.getStyle().setFgColor(ColorUtil.WHITE);
        this.getStyle().setBgColor(ColorUtil.BLUE);
    }
}
