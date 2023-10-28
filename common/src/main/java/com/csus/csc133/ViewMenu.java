/*
Menu with: ChangeStrategies, About, Exit
 */

package com.csus.csc133;

import java.util.*;
import com.codename1.io.Log;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;

import static com.codename1.ui.FontImage.MATERIAL_WARNING;


public class ViewMenu extends Container implements Observer {

    // Constructor
    public ViewMenu() {

        this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        //Toolbar.setGlobalToolbar(true);
        //Form hi = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));

        //Style s = UIManager.getInstalledLookAndFeels().getComponentStyle("titlecommand");
        // FontImage icon = FontImage.createMaterial(MATERIAL_WARNING, s);

        //Label header = new Label("Hamburger menu area ");
        //add(header);

        //SideMenuBar gameMenu = new SideMenuBar();
        //setToolbar(gameMenu);
        //add(gameMenu);

        //Command changeStrategies = new Command("Change Strategies");

        //hi.getToolbar().addCommandToSideMenu(changeStrategies);

        //hi.getToolbar().addCommandToLeftBar(changeStrategies);
        //hi.getToolbar().addCommandToSideMenu(changeStrategies);
        //hi.show();

        //gameMenu.addCommand(changeStrategies);

        //setToolbar(gameMenu);
        //setToolbar(gameMenu);
        //addComm
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
