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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.util.Callback;

public class setContent {

    public static TableView<Well> setContent(){

        TableView<Well> table = new TableView<>();

        // Editable
        table.setEditable(true);

        TableColumn<Well, String> well = new TableColumn<Well, String>("Well");
        TableColumn<Well, String> set = new TableColumn<Well, String>("Set");
        TableColumn<Well, String> reference = new TableColumn<Well, String>("Reference");
        TableColumn<Well, String> units = new TableColumn<Well, String>("Units");
        TableColumn<Well, Boolean> select = new TableColumn<Well, Boolean>("Select");

        well.setCellValueFactory( new PropertyValueFactory<Well, String>("well"));
        well.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        well.setMinWidth(100);
        well.setSortable(false);

        well.setCellFactory(new Callback<TableColumn<Well, String>, TableCell<Well, String>>() {

            @Override
            public TableCell<Well, String> call(TableColumn<Well, String> col) {
                final TableCell<Well, String> cell = new TableCell<>();
                final ContextMenu cellMenu = new ContextMenu();

                MenuItem editItem = new MenuItem("Well Option 1");
                MenuItem removeItem = new MenuItem("Well Option 2");
                MenuItem emailMenuItem = new MenuItem("Well Option 3");

                cellMenu.getItems().addAll(editItem,removeItem,emailMenuItem);

                cell.setContextMenu(cellMenu);
                cell.textProperty().bind(cell.itemProperty());
                
                return cell;
            }

        });

        set.setCellValueFactory( new PropertyValueFactory<Well, String>("set"));
        set.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        set.setMinWidth(100);
        set.setSortable(false);

        set.setCellFactory(new Callback<TableColumn<Well, String>, TableCell<Well, String>>() {

            @Override
            public TableCell<Well, String> call(TableColumn<Well, String> col) {
                final TableCell<Well, String> cell = new TableCell<>();
                final ContextMenu cellMenu = new ContextMenu();

                MenuItem editItem = new MenuItem("Set Option 1");
                MenuItem removeItem = new MenuItem("Set Option 2");
                MenuItem emailMenuItem = new MenuItem("Set Option 3");

                cellMenu.getItems().addAll(editItem,removeItem,emailMenuItem);

                cell.setContextMenu(cellMenu);
                cell.textProperty().bind(cell.itemProperty());

                return cell;
            }

        });

        reference.setCellValueFactory( new PropertyValueFactory<Well, String>("reference"));
        reference.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        reference.setMinWidth(100);
        reference.setSortable(false);

        units.setCellValueFactory( new PropertyValueFactory<Well, String>("units"));
        units.setCellFactory(TextFieldTableCell.<Well> forTableColumn());
        units.setMinWidth(100);
        units.setSortable(false);

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

        select.setCellFactory(new Callback<TableColumn<Well, Boolean>, //
                TableCell<Well, Boolean>>() {

            @Override
            public TableCell<Well, Boolean> call(TableColumn<Well, Boolean> p) {
                CheckBoxTableCell<Well, Boolean> cell = new CheckBoxTableCell<Well, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;
            }
        });

        select.setMinWidth(100);
        select.setSortable(false);

        table.getColumns().addAll(well,set,reference,units,select);

        well.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        set.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        reference.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
        units.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
        select.prefWidthProperty().bind(table.widthProperty().multiply(0.1));

        return table;
    }
}