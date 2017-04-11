package com.PRIME.main.functions.newW.wellWindow.contents.set;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class setContent {

    public static TableView<Well> setContent(){

        TableView<Well> table = new TableView<>();
        // Editable
        table.setEditable(true);

        TableColumn<Well, String> well = new TableColumn("Well");
        TableColumn<Well, String> set = new TableColumn("Set");
        TableColumn<Well, String> reference = new TableColumn("Reference");
        TableColumn<Well, String> units = new TableColumn("Units");
        TableColumn<Well, Boolean> select = new TableColumn("Select");

        well.setCellValueFactory( new PropertyValueFactory<Well, String>("well"));
        well.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        well.setMinWidth(100);

        set.setCellValueFactory( new PropertyValueFactory<Well, String>("set"));
        set.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        set.setMinWidth(100);

        reference.setCellValueFactory( new PropertyValueFactory<Well, String>("reference"));
        reference.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        reference.setMinWidth(100);

        units.setCellValueFactory( new PropertyValueFactory<Well, String>("units"));
        units.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        units.setMinWidth(100);

        select.setCellValueFactory( new PropertyValueFactory<Well, Boolean>("select"));
        select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Well, Boolean>, ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Well, Boolean> param) {
                Well wel = param.getValue();

                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(wel.isSelect());

                booleanProp.addListener(new ChangeListener<Boolean>() {

                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        wel.setSelect(newValue);
                    }
                });
                return booleanProp;
            }
        });
        select.setMinWidth(100);

        select.setCellFactory(new Callback<TableColumn<Well, Boolean>, //
                TableCell<Well, Boolean>>() {
            @Override
            public TableCell<Well, Boolean> call(TableColumn<Well, Boolean> p) {
                CheckBoxTableCell<Well, Boolean> cell = new CheckBoxTableCell<Well, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        table.getColumns().addAll(well,set,reference,units,select);

        well.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        set.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        reference.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        units.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        select.prefWidthProperty().bind(table.widthProperty().multiply(0.1));

        return table;
    }
}