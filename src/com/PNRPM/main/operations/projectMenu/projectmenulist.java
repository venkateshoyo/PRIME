package com.PNRPM.main.operations.projectMenu;

import javafx.scene.control.*;

public class projectmenulist {

    public static Menu projectmenulist(){
        //Project Menu
        Menu projectMenu = new Menu("_Project");

        //Project Sub Menu starts---------------------------------------------------------------------------------------
        MenuItem openproject = new MenuItem( "_Open Project...");
        openproject.setOnAction(e -> System.out.println(""));

        MenuItem newproject = new MenuItem( "_New Project");
        newproject.setOnAction(e -> {
            newProject.newProject();
        });

        Menu recentproject = recentProject.recentProject();

        MenuItem lastsaveproject = new MenuItem( "_Last Saved");
        lastsaveproject.setOnAction(e -> System.out.println(""));

        Menu autosave = new Menu("Auto Save Duration");

        RadioMenuItem none = new RadioMenuItem("None");
        none.setOnAction(e -> System.out.println(""));

        RadioMenuItem halfhourautosave = new RadioMenuItem("30 minutes");
        halfhourautosave.setOnAction(e -> System.out.println(""));

        RadioMenuItem onehourautosave = new RadioMenuItem("1 hour");
        onehourautosave.setOnAction(e -> System.out.println(""));

        ToggleGroup toggle = new ToggleGroup();

        none.setToggleGroup(toggle);
        halfhourautosave.setToggleGroup(toggle);
        onehourautosave.setToggleGroup(toggle);
        none.setSelected(true);

        TextField manual = new TextField();
        manual.setPromptText("Duration in minutes");
        CustomMenuItem custom = new CustomMenuItem(manual);
        custom.setHideOnClick(false);

        autosave.getItems().addAll(none,onehourautosave,halfhourautosave,custom);

        projectMenu.getItems().addAll(openproject,newproject,recentproject,lastsaveproject,autosave);

        //Project Sub Menu ends-----------------------------------------------------------------------------------------

        return projectMenu;
    }
}