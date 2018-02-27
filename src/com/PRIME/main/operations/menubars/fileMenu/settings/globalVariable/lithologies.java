package com.PRIME.main.operations.menubars.fileMenu.settings.globalVariable;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.util.Random;

public class lithologies {

    private static TableView<lithoInfo> table = new TableView<lithoInfo>();
    private static final ObservableList<lithoInfo> data =
            FXCollections.observableArrayList(
                    new lithoInfo("Sandstone", "16", "",""),
                    new lithoInfo("Clean Sandstone", "1", "",""),
                    new lithoInfo("Shaley Sandstone", "2", "",""),
                    new lithoInfo("Cemented Sandstone", "3", "",""),
                    new lithoInfo("Siltstone", "4", "",""),
                    new lithoInfo("Generic Shale", "6", "",""),
                    new lithoInfo("Hard Shale", "5", "",""),
                    new lithoInfo("Soft Shale", "7", "",""),
                    new lithoInfo("Limestone", "8", "",""),
                    new lithoInfo("Dolomite", "9", "",""),
                    new lithoInfo("Evaporite", "17", "",""),
                    new lithoInfo("Salt", "10", "",""),
                    new lithoInfo("Anyhdrite", "11", "",""),
                    new lithoInfo("Coal", "12", "",""),
                    new lithoInfo("Tuff", "13", "",""),
                    new lithoInfo("Igneous", "14", "",""),
                    new lithoInfo("Marl", "15", "",""),
                    new lithoInfo("Conglomerate", "18", "",""),
                    new lithoInfo("Chalk", "1000", "",""),
                    new lithoInfo("Mudstone", "1001", "",""),
                    new lithoInfo("New Lithology", "2000", "","")
            );

    public static BorderPane lithoTable(){

        final Label label = new Label("");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<lithoInfo, String> name = new TableColumn<>("Name");
        name.setMinWidth(150);
        name.setCellValueFactory( new PropertyValueFactory<>("name"));

        TableColumn<lithoInfo, String> value = new TableColumn<>("Value");
        value.setMinWidth(100);
        value.setCellValueFactory( new PropertyValueFactory<>("value"));

        TableColumn<lithoInfo, String> pattern = new TableColumn<>("Pattern");
        pattern.setMinWidth(200);
        pattern.setCellValueFactory( new PropertyValueFactory<>("pattern"));

        TableColumn colour  = new TableColumn("Colour");
        colour.setMinWidth(200);
        colour.setCellValueFactory( new PropertyValueFactory<lithoInfo, String>("colour"));

        char hexColor[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

        colour.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn param) {
                return new TableCell<lithoInfo, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-background-color: #"+
                                    hexColor[new Random().nextInt(15)]+
                                    hexColor[new Random().nextInt(15)]+
                                    hexColor[new Random().nextInt(15)]+
                                    hexColor[new Random().nextInt(15)]+
                                    hexColor[new Random().nextInt(15)]+
                                    hexColor[new Random().nextInt(15)]
                            );
                        }
                    }
                };
            }
        });

        table.setItems(data);
        table.getColumns().addAll(name, value, pattern, colour);
        table.setFixedCellSize(25);
        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        BorderPane info = new BorderPane(vbox);
        Platform.runLater(() -> table.refresh());
        return info;
    }

    public static class lithoInfo {
        final SimpleStringProperty name;
        final SimpleStringProperty value;
        final SimpleStringProperty pattern;
        final SimpleStringProperty colour;

        lithoInfo(String na, String va, String pa, String co) {
            this.name = new SimpleStringProperty(na);
            this.value = new SimpleStringProperty(va);
            this.pattern = new SimpleStringProperty(pa);
            this.colour = new SimpleStringProperty(co);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String na) {
            name.set(na);
        }

        public String getValue() {
            return value.get();
        }

        public void setValue(String va) {
            value.set(va);
        }

        public String getPattern() {
            return pattern.get();
        }

        public void setPattern(String pa) {
            pattern.set(pa);
        }

        public String getColour() {
            return colour.get();
        }

        public void setColour(String co) {
            colour.set(co);
        }
    }
}


