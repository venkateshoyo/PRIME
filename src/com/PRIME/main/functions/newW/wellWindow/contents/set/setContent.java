package com.PRIME.main.functions.newW.wellWindow.contents.set;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class setContent {

    public static TableView<Well> setContent(){

        TableView<Well> table = new TableView<>();
        TableColumn well = new TableColumn("Well");
        TableColumn set = new TableColumn("Set");
        TableColumn reference = new TableColumn("Reference");
        TableColumn units = new TableColumn("Units");
        TableColumn select = new TableColumn("Select");

        well.setCellValueFactory( new PropertyValueFactory<Well, String>("well"));
        set.setCellValueFactory( new PropertyValueFactory<Well, String>("set"));
        reference.setCellValueFactory( new PropertyValueFactory<Well, String>("reference"));
        units.setCellValueFactory( new PropertyValueFactory<Well, String>("units"));
        select.setCellValueFactory( new PropertyValueFactory<Well, String>("select"));

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