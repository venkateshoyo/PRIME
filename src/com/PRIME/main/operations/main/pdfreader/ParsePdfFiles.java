package com.PRIME.main.operations.main.pdfreader;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ParsePdfFiles {

    public static String folderpath = "\\src\\com\\PRIME\\main\\operations\\main\\pdfreader\\FolderPdf\\";

    public static void method() {
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
                                //matches.add(mat.start());
                            }
                            catch (StringIndexOutOfBoundsException exception)  {}
                        }
                    }
                    reader.close();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}