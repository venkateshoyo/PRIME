package com.PRIME.main.functions.newW.wellWindow;

import com.PRIME.database.mainMenus.projectMenu.recentprojects;
import com.PRIME.database.utils.DBUtils;
import com.PRIME.main.functions.newW.wellWindow.contents.commentsContent;
import com.PRIME.main.functions.newW.wellWindow.contents.contentsContent;
import com.PRIME.main.functions.newW.wellWindow.contents.logsContent;
import com.PRIME.main.functions.newW.wellWindow.contents.set.Well;
import com.PRIME.main.functions.newW.wellWindow.contents.set.setContent;
import com.PRIME.database.mainMenus.dataMenu.wellload.fetchWellDir;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;


public class newWellwindow{

    public static BorderPane wellview;
    public static DirectoryChooser directoryChooser = new DirectoryChooser();
    public static File projectDirectory ;
    public static Stage wellload ;
    public static ObservableList<Well> data = FXCollections.observableArrayList();
    public static TableView<Well> table;

    public void newWellwindow(){
        wellload = new Stage();
        wellload.setTitle(fetchlatestproject());

        wellview = new BorderPane();

        fetchHeader();

        fetchMain();

        Scene scene = new Scene(wellview,800,300);

        // bind to take available space
        wellview.prefHeightProperty().bind(scene.heightProperty());
        wellview.prefWidthProperty().bind(scene.widthProperty());

        wellload.setScene(scene);
//        scene.getStylesheets().add(newWellwindow.class.getResource("../../../resources/css/themes/maintheme1.css").toExternalForm());
        wellload.getIcons().add(new Image(getClass().getResourceAsStream("../../../resources/images/main_favicon.png")));
        wellload.show();
    }

    public static String fetchlatestproject(){
        String project="";

        ResultSet rs = null;
        try{
            rs = recentprojects.recentprojects();
            rs.last();
            project = rs.getString("Project Name");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DBUtils.closeResultSet(rs);
        }

        return project;
    }

    public static void fetchHeader(){
        VBox vbHeader = new VBox(10);

        BorderPane grid = new BorderPane();
        grid.setPadding(new Insets(15));

        Label file = new Label("File");
        file.setPadding(new Insets(5,25,0,0));
        grid.setLeft(file);

        final ComboBox<String> combo = fetchWellDir.fetchWellDir();

        combo.prefWidthProperty().bind(wellview.widthProperty());
        combo.setEditable(true);
        grid.setCenter(combo);

        HBox hbBrowse = new HBox(25);
        Label space = new Label("");
        Button Browse = new Button("Browse");
        Browse.setOnAction(e -> {
            directoryChooser.setInitialDirectory(new File("C:\\Program Files\\Common Files"));
            projectDirectory = directoryChooser.showDialog(wellload);
            if (projectDirectory!=null) {
                combo.setValue(projectDirectory.getAbsolutePath());
            }
        });


        hbBrowse.getChildren().addAll(space,Browse);
        grid.setRight(hbBrowse);

        HBox hbWellBy = new HBox(30);
        hbWellBy.setPadding(new Insets(0,15,0,0));

        Label wellBy = new Label("Import Well By: ");

        HBox radioButton = new HBox(20);
        radioButton.setPadding(new Insets(0));
        final ToggleGroup group = new ToggleGroup();
        RadioButton rb1 = new RadioButton("Well Name");
        rb1.setToggleGroup(group);
        rb1.setSelected(true);
        RadioButton rb2 = new RadioButton("API ID");
        rb2.setToggleGroup(group);
        RadioButton rb3 = new RadioButton("Well ID");
        rb3.setToggleGroup(group);
        radioButton.getChildren().addAll(rb1,rb2,rb3);

        Button wellImport = new Button("Import");
        wellImport.setOnAction(e->{
            data.add(new Well("A","B","C","D","E"));
            table.setItems(data);
        });

        hbWellBy.getChildren().addAll(wellBy,radioButton,wellImport);
        hbWellBy.setAlignment(Pos.BASELINE_RIGHT);

        final Separator separator = new Separator();
        vbHeader.getChildren().addAll(grid,hbWellBy,separator);

        wellview.setTop(vbHeader);
    }

    public static void fetchMain(){
        TabPane tabPane = new TabPane();

        Tab set = new Tab("Set");
        set.setClosable(false);
        table =setContent.setContent();
        set.setContent(table);

        Tab contents= new Tab("Contents");
        contents.setClosable(false);
        contents.setContent(contentsContent.contentsContent());

        Tab comments = new Tab("Comments");
        comments.setClosable(false);
        comments.setContent(commentsContent.commentsContent());

        Tab logs = new Tab("Logs");
        logs.setClosable(false);
        logs.setContent(logsContent.logsContent());

        tabPane.getTabs().addAll(set,contents,comments,logs);

        wellview.setCenter(tabPane);
    }
}