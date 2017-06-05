package com.PRIME.main.operations.toolbars.fileMenu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.lang.reflect.Field;


public class file {

    private void createToolbar() {
        //    ToolBar toolBar = new ToolBar();
//
//    Button openFileBtn = new Button();
//    Button printBtn = new Button();
//    Button snapshotBtn = new Button();
//
//        openFileBtn.setGraphic(new ImageView("http://icons.iconarchive.com/icons/capital18/ethereal-2/128/Toolbar-Browser-Search-icon.png"));
//        printBtn.setGraphic(new ImageView("http://icons.iconarchive.com/icons/capital18/ethereal-2/128/Toolbar-Browser-Search-icon.png"));
//        snapshotBtn.setGraphic(new ImageView("http://icons.iconarchive.com/icons/capital18/ethereal-2/128/Toolbar-Browser-Search-icon.png"));
//
//        toolBar.getItems().addAll(openFileBtn, printBtn, snapshotBtn);
        ToolBar toolbar = new ToolBar();
        Button button = new Button("Move the slider around to freeze me.");
        button.setOnAction(e-> {
                    System.out.println("I am still active - I just don't look the way.");
                }
        );
        Tooltip tp = new Tooltip("My tooltip is still fine!");
        button.setTooltip(tp);
        toolbar.getItems().add(button);
    }
}