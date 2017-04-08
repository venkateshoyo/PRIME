package com.PRIME.main.functions.newW.wellWindow.contents;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class setContent {

    public static BorderPane setContent(){
        BorderPane setcontent = new BorderPane();

        TableView<Void> table = new TableView<>();
        TableColumn<Void, Void> well = new TableColumn<>("Well");
        TableColumn<Void, Void> set = new TableColumn<>("Set");
        TableColumn<Void, Void> reference = new TableColumn<>("Reference");
        TableColumn<Void, Void> units = new TableColumn<>("Units");
        TableColumn<Void, Void> select = new TableColumn<>("Select");

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

        setcontent.setCenter(table);
        return setcontent;
    }
}