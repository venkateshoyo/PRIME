package com.PRIME.main.operations.menubars.displayMenu.pdfreader;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Admin on 6/23/2017.
 */
public class ReadTable {
    public static List<int[]> rowList = new ArrayList<int[]>();
    public static int[][] run(String filename) throws IOException {
        // filename = "PDF.pdf"
        String folderpath = "\\src\\com\\PRIME\\main\\resources\\pdfs\\";
        folderpath = System.getProperty("user.dir") + folderpath;
        String filepath = folderpath + filename;
        PdfReader reader = new PdfReader(filepath);

        int pages = reader.getNumberOfPages();
        for (int i = 1;i<=pages;i++){
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, i);

            Pattern pat = Pattern.compile("(\\d{1,10})[ \n]+?(\\d{1,10})([ \n]+?(Sandstone|Shale|Claystone|Siltstone|Limestone)(.|\\n)+?(\\d{1,10}))" +
                    "([^\\d]+?(Sandstone|Shale|Claystone|Siltstone|Limestone)(.|\\n)+?(\\d{1,10})|[^\\d]*?)" +
                    "([^\\d]+?(Sandstone|Shale|Claystone|Siltstone|Limestone)(.|\\n)+?(\\d{1,10})|[^\\d]*?)" +
                    "([^\\d]+?(Sandstone|Shale|Claystone|Siltstone|Limestone)(.|\\n)+?(\\d{1,10})|[^\\d]*?)" +
                    "([^\\d]+?(Sandstone|Shale|Claystone|Siltstone|Limestone)(.|\\n)+?(\\d{1,10})|[^\\d]*?)");

            Matcher mat = pat.matcher(textFromPage);

            ArrayList<String> components = new ArrayList<>();
            components.add("Sandstone");components.add("Shale");components.add("Claystone");components.add("Siltstone");components.add("Limestone");

            while (mat.find()) {
                int [] temp = new int[5];
                try {
                    temp[components.indexOf(mat.group(4))] = Integer.parseInt(mat.group(6));
                    temp[components.indexOf(mat.group(8))] = Integer.parseInt(mat.group(10));
                    temp[components.indexOf(mat.group(12))] = Integer.parseInt(mat.group(14));
                    temp[components.indexOf(mat.group(16))] = Integer.parseInt(mat.group(18));
                    temp[components.indexOf(mat.group(20))] = Integer.parseInt(mat.group(22));
                }
                catch (ArrayIndexOutOfBoundsException|NumberFormatException e){}
                finally {rowList.add(new int[]{Integer.parseInt(mat.group(1)),Integer.parseInt(mat.group(2)),temp[0],temp[1],temp[2],temp[3],temp[4]});}
            }
        }
        int[][] matrix = new int[rowList.size()][7];
        for (int r=0;r<rowList.size();r++){
            matrix[r] = rowList.get(r);
        }
        return matrix;
    }
}