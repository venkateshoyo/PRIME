package com.PNRPM.main.operations.DataMenu;


import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class loadlasfile {

    public void loadlas(File selectedlas)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedlas));
        try {
            bufferedReader = new BufferedReader(new FileReader(selectedlas));
            String text;
            Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
            double datas=0.0;
            boolean Isdata=false,Isparameter=false;
            String parameter[]=new String[30];
            double values[][]= new double[4][4];
            int noOfParameter=0;
            int index=0,parameterindex=0;
            while ((text = bufferedReader.readLine()) != null && text.length()>0) {
                if(Isdata){
                    text=(text.replaceAll("[ ]+", " ")).substring(1);
                    text+=" ";
                    int textindex = 0;
                    while(text.indexOf(" ",textindex)>0){
                        values[index][parameterindex++]=Double.parseDouble(text.substring(textindex,text.indexOf(" ",textindex)));
                        textindex=text.indexOf(" ",textindex)+1;
                    }
                    ++index;
                    parameterindex=0;
                }
                else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STRT"))){
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas-=Double.parseDouble(matcher.group(1));
                    }
                }
                else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STOP"))){
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas+=Double.parseDouble(matcher.group(1));
                    }
                }
                else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("STEP"))){
                    Matcher matcher = regex.matcher(text);
                    while (matcher.find()) {
                        datas/=Double.parseDouble(matcher.group(1));
                    }
                }
                else if (text.length() > 5 && (text.substring(1, 6).equalsIgnoreCase("depth"))){
                    Isparameter=true;
                    parameter[++noOfParameter]=text.substring(0, text.indexOf(" "));
                }
                else if (Isparameter && !(text.substring(0, 4).equalsIgnoreCase("~Asc"))){
                    parameter[++noOfParameter]=text.substring(0, text.indexOf(" "));
                }
                else if (text.length() > 4 && (text.substring(0, 4).equalsIgnoreCase("~Asc"))){
                    Isdata=true;
                    values= new double[(int)datas+1][noOfParameter];
                }
            }
            System.out.println(values[(int)datas][noOfParameter-1]);
        } catch (FileNotFoundException ex) {
//            Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
//            Logger.getLogger(JavaFX_OpenFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

}
