package com.PRIME.main.operations.main.sidebar;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class SidebarPanel {
    public static SplitPane side(){

        //Update the dir path want to show on side bar
        TreeItem<File> root = createNode.createNode(new File("C:\\"));
        TreeView<File> treeView = new TreeView<>(root);
        root.setExpanded(true);

        treeView.setCellFactory(e -> new TextFieldTreeCellImpl());
        StackPane leftPane = new StackPane(treeView);

        HBox contentRight = new HBox();
        StackPane rightPane = new StackPane(contentRight);

        //Layout
        SplitPane layout = new SplitPane();
        layout.setDividerPositions(0.25);
        layout.getItems().addAll(leftPane, rightPane);

        //Constrain size of left component:
        rightPane.minWidthProperty().bind(layout.widthProperty().multiply(0.7));

        return layout;
    }
}