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

public class logID {

    private static TableView<logInfo> table = new TableView<>();
    private static final ObservableList<logInfo> data =
            FXCollections.observableArrayList(new logInfo("Vp", "", "Vp VpL DT DTP","","2000","6000","",""),
                    new logInfo("Vs", "", "Vs","","500","2500","",""),
                    new logInfo("GR", "", "GR Gamma CGR SGR GR1 GR2 GR3 GRGC GRI SGRC","","0","150","",""),
                    new logInfo("Rho", "", "Rho RhoB Drho DENS RHOZ DENF DENN DEN RHOMAA RHOBHC-CPX RHOH_CPX","","1.45","2.95","",""),
                    new logInfo("Por", "", "Por Phi Phie Phit DPOR DPHI CORP SPHI PHIDLS PHIN PHIS PHICP DPRL DPRS DPRD","","0","0.4","",""),
                    new logInfo("Volume", "", "Vol VQtz VClay VQuartz SST LST DOLL VSh VShale VCL VLST VSST COAL VLS","","0","1","",""),
                    new logInfo("Sat", "", "Sat Sw So Sg sxo SWE SWT SWE-CPX SWT-CPX SXO-CPX SWEU-CPX SXOU-CPX","","0","1","",""),
                    new logInfo("Cal", "", "Cal Cali Caliper BS RUGOSIT BIT DI DD CLDC CSGS CDGW HD1_PPC1 HD2_PPC1","","5.906","19.685","",""),
                    new logInfo("p_Sonic", "", "Sonic DT DTL SON DTC DT4P DTCO DT35 DTMA DTCRC-CPX STCFNL-CPX","","152.4","50.801","",""),
                    new logInfo("s_Sonic", "", "DTSM DTSML DTS DTSRC-CPX DTSFNL-CPX","","609.6","121.","",""),
                    new logInfo("Depth", "", "Depth Dept","","2000","6000","",""),
                    new logInfo("Time", "", "Time twt TWT owt TR21 TR11 TR22 TR12","","2000","6000","",""),
                    new logInfo("Poisson_Ratio", "", "Poisson Poissons Poisson's Pois sigma ratio PR PR-CPX","","2000","6000","",""),
                    new logInfo("Trace", "", "TRACE","","2000","6000","",""),
                    new logInfo("AI", "", "AI AI AI-CPX","","2000","6000","",""),
                    new logInfo("SI", "", "SI","","2000","6000","",""),
                    new logInfo("Mu", "", "MU UMOD-CPX","","2000","6000","","")
            );

    public static BorderPane logIDTable(){

        final Label label = new Label("");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<logInfo, String> logType = new TableColumn<>("Log Type");
        logType.setMinWidth(100);
        logType.setCellValueFactory( new PropertyValueFactory<>("logType"));

        TableColumn<logInfo, String> displayUnits = new TableColumn<>("Display Units");
        displayUnits.setMinWidth(50);
        displayUnits.setCellValueFactory( new PropertyValueFactory<>("displayUnits"));

        TableColumn<logInfo, String> logStrings = new TableColumn<>("Log Identification Strings");
        logStrings.setMinWidth(250);
        logStrings.setCellValueFactory( new PropertyValueFactory<>("logStrings"));

        TableColumn<logInfo, String> common = new TableColumn<>("Common");
        common.setMinWidth(50);
        common.setCellValueFactory( new PropertyValueFactory<>("common"));

        TableColumn<logInfo, String> plotMin = new TableColumn<>("Plot Min");
        plotMin.setMinWidth(50);
        plotMin.setCellValueFactory( new PropertyValueFactory<>("plotMin"));

        TableColumn<logInfo, String> plotMax = new TableColumn<>("Plot Max");
        plotMax.setMinWidth(50);
        plotMax.setCellValueFactory( new PropertyValueFactory<>("plotMax"));

        TableColumn defaultColour  = new TableColumn("Default Colour");
        defaultColour.setMinWidth(100);
        defaultColour.setCellValueFactory( new PropertyValueFactory<logInfo, String>("defaultColour"));

        defaultColour.setCellFactory(new Callback<TableColumn, TableCell>() {
            public TableCell call(TableColumn param) {
                return new TableCell<logInfo, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-background-color: #000");
                        }
                    }
                };
            }
        });

        TableColumn colourScale = new TableColumn("Colour Scale");
        colourScale.setMinWidth(100);
        colourScale.setCellValueFactory( new PropertyValueFactory<logInfo, String>("colourScale"));

        table.setItems(data);
        table.getColumns().addAll(logType, displayUnits, logStrings, common, plotMin, plotMax, defaultColour, colourScale);
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
    public static class logInfo {
        final SimpleStringProperty logType;
        final SimpleStringProperty displayUnits;
        final SimpleStringProperty logStrings;
        final SimpleStringProperty common;
        final SimpleStringProperty plotMin;
        final SimpleStringProperty plotMax;
        final SimpleStringProperty defaultColour;
        final SimpleStringProperty colourScale;

        logInfo(String lT, String dU, String lS, String c, String pMn, String pMx, String dC, String cS) {
            this.logType = new SimpleStringProperty(lT);
            this.displayUnits = new SimpleStringProperty(dU);
            this.logStrings = new SimpleStringProperty(lS);
            this.common = new SimpleStringProperty(c);
            this.plotMin = new SimpleStringProperty(pMn);
            this.plotMax = new SimpleStringProperty(pMx);
            this.defaultColour = new SimpleStringProperty(dC);
            this.colourScale = new SimpleStringProperty(cS);
        }

        public String getLogType() {
            return logType.get();
        }

        public void setLogType(String lT) {
            logType.set(lT);
        }

        public String getDisplayUnits() {
            return displayUnits.get();
        }

        public void setDisplayUnits(String dU) {
            displayUnits.set(dU);
        }

        public String getLogStrings() {
            return logStrings.get();
        }

        public void setLogStrings(String pa) {
            logStrings.set(pa);
        }

        public String getCommon() {
            return common.get();
        }

        public void setColour(String c) {
            common.set(c);
        }

        public String getPlotMin() {
            return plotMin.get();
        }

        public void setPlotMin(String pMn) {
            plotMin.set(pMn);
        }

        public String getPlotMax() {
            return plotMax.get();
        }

        public void setPlotMax(String pMx) {
            plotMax.set(pMx);
        }

        public String getDefaultColour() {
            return defaultColour.get();
        }

        public void setDefaultColour(String dC) {
            defaultColour.set(dC);
        }

        public String getColourScale() {
            return colourScale.get();
        }

        public void setColourScale(String cS) {
            colourScale.set(cS);
        }
    }
}
