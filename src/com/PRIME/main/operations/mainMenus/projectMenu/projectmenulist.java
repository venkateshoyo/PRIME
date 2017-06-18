package com.PRIME.main.operations.mainMenus.projectMenu;

import com.PRIME.testingDirectory.test;
import javafx.scene.control.*;

public class projectmenulist {

    public static Menu projectmenulist(){
        //Project Menu list
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

        //_______________________________________________________________________________

        Menu autosave = new Menu("Auto Save Duration");

        RadioMenuItem none = new RadioMenuItem("None");
        none.setOnAction(e -> System.out.println(""));

        RadioMenuItem halfhourautosave = new RadioMenuItem("30 minutes");
        halfhourautosave.setOnAction(e -> System.out.println(""));

        RadioMenuItem onehourautosave = new RadioMenuItem("1 hour");
        onehourautosave.setOnAction(e -> System.out.println(""));

        MenuItem projectViewer = new MenuItem( "Project Viewer");
        projectViewer.setOnAction(e -> {
            test Test = new test();
            try {
                Test.project();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });

        ToggleGroup toggle = new ToggleGroup();

        none.setToggleGroup(toggle);
        halfhourautosave.setToggleGroup(toggle);
        onehourautosave.setToggleGroup(toggle);
        none.setSelected(true);

        TextField manual = new TextField();
        manual.setPromptText("Duration in minutes");
        CustomMenuItem custom = new CustomMenuItem(manual);
        custom.setHideOnClick(false);

        //________________________________________________________________________________

        autosave.getItems().addAll(none,onehourautosave,halfhourautosave,custom);

        //Project Sub Menu ends-----------------------------------------------------------------------------------------

        projectMenu.getItems().addAll(openproject,newproject,recentproject,lastsaveproject,autosave,projectViewer);

        return projectMenu;
    }
}