package com.PRIME.main.functions.newW.wellWindow.contents.set;

import com.PRIME.main.functions.newW.wellWindow.contents.set.Well;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class setContent {

    public static TableView<Well> setContent(){

        TableView<Well> table = new TableView<>();
        TableColumn<Well, Void> well = new TableColumn<>("Well");
        TableColumn<Well, Void> set = new TableColumn<>("Set");
        TableColumn<Well, Void> reference = new TableColumn<>("Reference");
        TableColumn<Well, Void> units = new TableColumn<>("Units");
        TableColumn<Well, Void> select = new TableColumn<>("Select");

        table.getColumns().addAll(well,set,reference,units,select);

        well.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        set.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        reference.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        units.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        select.prefWidthProperty().bind(table.widthProperty().multiply(0.1));

        well.setResizable(false);
        set.setResizable(false);
        reference.setResizable(false);
        units.setResizable(false);
        select.setResizable(false);

        return table;
    }
}