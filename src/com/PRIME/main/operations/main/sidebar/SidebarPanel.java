package com.PRIME.main.operations.main.sidebar;

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.File;

public class SidebarPanel {
    public static SplitPane side(Stage stage){

        //Update the dir path want to show on side bar
        TreeItem<File> root = createNode.createNode(new File("C:\\"));
        TreeView<File> treeView = new TreeView<>(root);
        root.setExpanded(true);

        treeView.setCellFactory(treeeView -> new TextFieldTreeCellImpl(stage));
        HBox contentRight = new HBox();

        //Layout
        SplitPane layout = new SplitPane();
        layout.setDividerPositions(0.3f);
        contentRight.setStyle("-fx-background-color: #778899;");
        layout.setOrientation(Orientation.HORIZONTAL);
        layout.getItems().addAll(treeView, contentRight);

        return layout;
    }
}