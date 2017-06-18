package com.PRIME.testingDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import javafx.scene.layout.VBox;

public class test extends Application {


    public static void main(String[] args) {
        Application.launch(args);
    }


    public TreeItem<File> createNode( File f) {
        return new TreeItem<File>(f) {
            public boolean isLeaf;
            public boolean isFirstTimeChildren = true;
            public boolean isFirstTimeLeaf = true;

            @Override
            public ObservableList<TreeItem<File>> getChildren() {
                if (isFirstTimeChildren) {
                    isFirstTimeChildren = false;
                    super.getChildren().setAll(buildChildren(this));
                }
                return super.getChildren();
            }

            @Override
            public boolean isLeaf() {
                if (isFirstTimeLeaf) {
                    isFirstTimeLeaf = false;
                    File f = (File) getValue();
                    isLeaf = f.isFile();
                }
                return isLeaf;
            }

            public ObservableList<TreeItem<File>> buildChildren(
                    TreeItem<File> TreeItem) {
                File f = TreeItem.getValue();

                if (f == null) {
                    return FXCollections.emptyObservableList();
                }
                if (f.isFile()) {
                    return FXCollections.emptyObservableList();
                }
                File[] files = f.listFiles();
                if (files != null) {
                    ObservableList<TreeItem<File>> children = FXCollections
                            .observableArrayList();
                    for (File childFile : files) {
                        children.add(createNode(childFile));
                    }
                    return children;
                }
                return FXCollections.emptyObservableList();
            }
        };
    }





    @Override
    public void start(Stage stage) {

        stage.setTitle("Tree View Sample");

//     Update the dir path want to show on side bar
        TreeItem<File> root = createNode(new File("C://"));
        TreeView<File> treeeView = new TreeView<File>(root);
        root.setExpanded(true);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        treeeView.setEditable(true);
        treeeView.setCellFactory(new Callback<TreeView<File>,TreeCell<File>>(){
            @Override
            public TreeCell<File> call(TreeView<File> treeeView) {
                return new TextFieldTreeCellImpl(stage,fileChooser);
            }
        });
        HBox contentRight = new HBox();
        //Layout
        SplitPane layout = new SplitPane();
        layout.setDividerPositions(0.3f);
        contentRight.setStyle("-fx-background-color: #778899;");
        layout.setOrientation(Orientation.HORIZONTAL);
        layout.getItems().addAll(treeeView, contentRight);
        VBox box = new VBox();


        final Scene scene = new Scene(layout, 1000, 1000);
        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);
        stage.show();

    }

    private final class TextFieldTreeCellImpl extends TreeCell<File> {

        private TextField textField;
        private ContextMenu addMenu = new ContextMenu();


        public TextFieldTreeCellImpl(Stage stage,FileChooser fileChooser ) {



            setOnDragDropped(new EventHandler <DragEvent>() {
                @Override
                public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                    System.out.println("dropped");
                    Dragboard db = event.getDragboard();
                    boolean success = false;
                    if (db.hasFiles()) {
                        String filePath = null;
                        Path des = getItem().toPath();
                        for (File file:db.getFiles()) {
                            filePath = file.getAbsolutePath();
                            File nfile = new File(des+"//"+file.getName());

                            try {
                                Files.copy(file.toPath(),nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            TreeItem newFile =
                                    new TreeItem<File>(file);
                            getTreeItem().getChildren().add(newFile);
                            success = true;
                        }
                    }
                /* let the source know whether the string was successfully
                 * transferred and used */
                    event.setDropCompleted(success);

                    event.consume();
                }
            });

            MenuItem addMenuItem = new MenuItem("Add File");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(new EventHandler() {

                public void handle(Event t) {
                    File file = fileChooser.showOpenDialog(stage);
                    Path des = getItem().toPath();
                    File nfile = new File(des+"//"+file.getName());

                    try {
                        Files.copy(file.toPath(),nfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    TreeItem newFile =
                            new TreeItem<File>(file);
                    getTreeItem().getChildren().add(newFile);
                }
            });
        }


        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText( getItem().toString());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(File item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    if (
                            !getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
                            ){
                        setContextMenu(addMenu);
                    }
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(getItem());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().getName();

        }
    }

}