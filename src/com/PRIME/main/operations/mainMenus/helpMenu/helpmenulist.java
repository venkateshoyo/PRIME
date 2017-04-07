package com.PRIME.main.operations.mainMenus.helpMenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class helpmenulist {

    public static Menu helpmenulist(){
        //Help Menu
        Menu helpMenu = new Menu("_Help");

        //Help Sub Menu starts------------------------------------------------------------------------------------------
        MenuItem findAction = new MenuItem("Fi_nd Action");
        findAction.setOnAction(e -> System.out.println(""));

        MenuItem help = new MenuItem("_Help");
        help.setOnAction(e -> System.out.println());

        MenuItem gettingstarted = new MenuItem("_Getting Started");
        gettingstarted.setOnAction(e -> System.out.println());

        MenuItem keymap = new MenuItem("_Keymap Reference");
        keymap.setOnAction(e -> System.out.println());

        SeparatorMenuItem helpseparater_1= new SeparatorMenuItem();

        MenuItem demos = new MenuItem("_Demos and Screencasts ");
        demos.setOnAction(e -> System.out.println());

        MenuItem tip = new MenuItem("_Tips of the Day");
        tip.setOnAction(e -> System.out.println());

        MenuItem whatsnew = new MenuItem("_Whats New in PNRPM");
        whatsnew.setOnAction(e -> System.out.println());

        SeparatorMenuItem helpseparater_2= new SeparatorMenuItem();

        MenuItem supportcenter = new MenuItem("_Support Center");
        supportcenter.setOnAction(e -> System.out.println());

        MenuItem feedback = new MenuItem("Submit _Feedback");
        feedback.setOnAction(e -> System.out.println());
        feedback.setDisable(true);

        MenuItem showlogs = new MenuItem("Show log in _Explorer");
        showlogs.setOnAction(e -> System.out.println());

        SeparatorMenuItem helpseparater_3= new SeparatorMenuItem();

        MenuItem updates = new MenuItem("Check for _Updates");
        updates.setOnAction(e -> System.out.println());

        MenuItem literature = new MenuItem("Related literatures");
        literature.setOnAction(e -> System.out.println());

        MenuItem about = new MenuItem("_About");
        about.setOnAction(e -> System.out.println());

        helpMenu.getItems().addAll(findAction,help,gettingstarted, keymap,helpseparater_1,demos,tip,whatsnew,helpseparater_2,supportcenter,feedback,showlogs,helpseparater_3,updates,literature, about);

        //Help Sub Menu ends--------------------------------------------------------------------------------------------

        return helpMenu;
    }
}