package com.PRIME.testingDirectory;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class testing extends Application {

    @Override
    public void start (Stage stage) {
        final SwingNode swingNode = new SwingNode();

        createSwingContent(swingNode);

        StackPane pane = new StackPane();
        pane.getChildren().add(swingNode);

        stage.setTitle("Swing in JavaFX");
        stage.setScene(new Scene(pane, 250, 150));
        stage.show();
    }

    private void createSwingContent(final SwingNode swingNode) {
        SwingUtilities.invokeLater(() -> swingNode.setContent(new JButton("Click me!")));
    }
}