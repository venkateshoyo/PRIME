package com.PRIME.main.operations.sidebar;

import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

class TextFieldTreeCellImpl extends TreeCell<File> {

    private TextField textField;
    private ContextMenu addMenu = new ContextMenu();

    public TextFieldTreeCellImpl() {

        MenuItem addfile = new MenuItem("Add File");
        MenuItem addfolder = new MenuItem ("Add Folder");
        MenuItem rename = new MenuItem("Rename");

        String whiteText="-fx-text-fill: white";
        addfile.setStyle(whiteText);
        addfolder.setStyle(whiteText);
        rename.setStyle(whiteText);

        addMenu.getItems().addAll(addfile,addfolder,rename);

        rename.setOnAction(t -> start());

        addfolder.setOnAction(t -> {
            String name = newfolder.display();
            if(!name.isEmpty()) {
                Path des = getItem().toPath();
                new File(des + "\\" + name).mkdir();
                TreeItem<File> root = createNode.createNode(new File(des + "\\" + name));

                getTreeItem().getChildren().add(root);
            }
        });
        addfile.setOnAction(t -> {
            String name = newfile.display();

            if(!name.isEmpty())
            {
                Path des = getItem().toPath();
                File file = new File(des + "//" + name);

                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                TreeItem<File> newFile = new TreeItem<>(file);
                getTreeItem().getChildren().add(newFile);
            }
        });
    }

    public void start() {
        super.startEdit();
        if (textField== null) {
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
                if (!getTreeItem().isLeaf()&&getTreeItem().getParent()!= null){
                    setContextMenu(addMenu);
                }
            }
        }
    }

    private void createTextField() {
        textField = new TextField(getString());

        textField.setOnKeyReleased(t->  {
            if (t.getCode() == KeyCode.ENTER) {
                {
                    File oldName = new File(getItem().getPath());
                    File newName = new File(getItem().getParent()+"\\"+textField.getText());
                    oldName.renameTo(newName);
                    getTreeItem().setValue(newName);
                    commitEdit(getItem());

                }
            } else if (t.getCode() == KeyCode.ESCAPE) {
                cancelEdit();
            }
        });
    }

    private String getString() {
        String ret=getItem().getName();

        if(ret.length()==0)
            ret= getItem().getPath();

        return ret;
    }
}