package com.PRIME.main.operations.main.pdfreader;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParsePdfFiles {

    public static String folderpath = "\\src\\com\\PRIME\\main\\operations\\main\\pdfreader\\FolderPdf\\";

    public static void method() {


        VBox vb = new VBox(20);

        folderpath = System.getProperty("user.dir") + folderpath;
        // System.out.println(System.getProperty("user.dir"));
        PdfReader reader;
        File folder = new File(folderpath);
        File[] listOfFiles = folder.listFiles();
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

                                Button tx = new Button(location+"\n\n"+matched);
                                tx.setPrefWidth(500);
                                vb.getChildren().add(tx);

                                //matches.add(mat.start());
                            }
                            catch (StringIndexOutOfBoundsException exception)  {}
                        }
                    }
                    reader.close();
                }
            }
            ScrollPane pane = new ScrollPane(vb);
            Scene screen = new Scene(pane);
            Stage stage = new Stage();
            stage.show();
            stage.setScene(screen);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}