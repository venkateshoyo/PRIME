package com.PRIME.main.operations.mainMenus.displayMenu.pdfreader;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParsePdfFiles {

    public static int max=0;

    public static void method() {

        String folderpath = "\\src\\com\\PRIME\\main\\resources\\pdfs\\";
        VBox sp = new VBox(20);

        folderpath = System.getProperty("user.dir") + folderpath;
        // System.out.println(System.getProperty("user.dir"));
        PdfReader reader;
        File folder = new File(folderpath);
        File[] listOfFiles = folder.listFiles(new FilenameFilter(){public boolean accept(File folder,String filename){return filename.endsWith(".pdf");}});
        String but = "";
        int count =0;
        try {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    String filename = file.getName();
                    String filepath = folderpath + filename;

                    reader = new PdfReader(filepath);
                    int pages = reader.getNumberOfPages();

                    for (int i = 1;i<=pages;i++){
                        String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);
                        Pattern pat = Pattern.compile("([Pp]orosity|por |Por |PHI|phi)(.|\n){1,50}0\\.");
                        Matcher mat = pat.matcher(textFromPage);
                        //final List<Integer> matches = new ArrayList<>();

                        while (mat.find()) {
                            int strt = mat.start();
                            try {
                                System.out.println("File: " + filename+", On Page no: "+i);

                                System.out.println(textFromPage.substring(strt, strt + 300)+"\n\n");

                                String matched = textFromPage.substring(strt, strt + 300);
                                String location= "Found in "+filename+" at page no. "+i;
                                but = but+location+"\n\n"+matched+"\n\n\n\n\n";
                                if(matched.length()>max)
                                    max=matched.length();
                                if(location.length()>max)
                                    max = location.length();

                                count++;

                                //matches.add(mat.start());
                            }
                            catch (StringIndexOutOfBoundsException exception)  {}
                        }
                    }
                    reader.close();
                }
            }
             DoubleProperty fontSize = new SimpleDoubleProperty();
          IntegerProperty blues = new SimpleIntegerProperty();
            Label g= new Label(but);

            g.setWrapText(true);
            g.setPadding(new Insets(0,0,0,10));
            ScrollPane pane = new ScrollPane(new Group(g));
            Scene screen = new Scene(pane);
            g.setPrefWidth(pane.getWidth());
            screen.widthProperty().addListener(e->{pane.setPrefWidth(screen.getWidth());});
            screen.heightProperty().addListener(e->{pane.setPrefHeight(screen.getHeight());});
            screen.widthProperty().addListener(e->{g.setPrefWidth(screen.getWidth());});



            screen.widthProperty().addListener(e -> {
                        if(screen.widthProperty().get()<700) {
                            fontSize.bind(screen.widthProperty().subtract(screen.widthProperty()).add(12));
                        }
                        else
                        {
                            fontSize.bind((screen.widthProperty().add(500).divide(100)));
                        }
                g.styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"
                        , "-fx-base: rgb(100,100,", blues.asString(), ");"));
                });



            Stage stage = new Stage();
            stage.show();
            stage.setScene(screen);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}