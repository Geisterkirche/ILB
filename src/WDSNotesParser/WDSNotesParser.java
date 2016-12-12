package WDSNotesParser;

import java.io.File;
import java.io.FileReader;
import java.util.*;

public class WDSNotesParser{
    public static String currentSystem="";
    public static String currentPair="";
    public static StringBuffer buffer = new StringBuffer();

    //public static void main(String[] args){
    //   String fileName = "D:/wdsnewnotes_main.txt";
    //   //Entry point for direct WDSnotes parsing. Should be commented if used as part of main ILBprocessing module
    //}

    public static void mainParserNotes(String fileName){
        try {
            File dataFile = new File(fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    try{
                        preProcessing(s.substring(0, s.length()-2));
                    }catch(StringIndexOutOfBoundsException e){
                        System.err.println("StringIndexOutOfBoundsException: seems like EOF");
                        e.printStackTrace();
                    }
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void preProcessing(String line){
        //System.doNotShowBcsResolved.println(line);
        if(line.substring(0,11).equals("           ")){
            if(line.substring(11,22).equals("           ")){
                continueParse(line);
            }else{
                newParse(line);
                currentPair=line.substring(11,22);
            }
        }else{
            newParse(line);
            currentSystem=line.substring(0,10);
            currentPair=line.substring(11,22);
        }
    }
    public static void continueParse(String line){
        try {
            buffer.append(" "+removeSpacesInString(line.substring(23, 94)));
        }catch(StringIndexOutOfBoundsException w){
            buffer.append(removeSpacesInString(line.substring(23)));
        }
    }
    public static void newParse(String line){
        if(buffer!=null){
            process(buffer.toString());
            //System.doNotShowBcsResolved.println(buffer);
        }
        buffer = new StringBuffer();
        continueParse(line);
    }
    public static String removeSpacesInString(String line){
        char[] ar = line.toCharArray();
        for(int i=ar.length-1;i>0;i--){
            if(ar[i]!=' '){
                return line.substring(0,i+1);
            }
        }
        return "";
    }
    public static void process(String text){
        String[] sentenses = text.split("[.]");
        for(int i=0;i<sentenses.length;i++){
            try {
                if (sentenses[i].length() > 2) processSentence(sentenses[i]);
            }catch (Exception e){
                if (sentenses[i].length() > 1) processSentence(sentenses[i].substring(0, sentenses[i].length() - 1));
                //e.printStackTrace();
                System.err.println(sentenses[i]);
                System.err.println("at text "+text);
            }
        }
    }
    public static void processSentence(String text){
        text=text.replaceAll("\\-"," ");
        text=text.replaceAll("  "," ");
        text= text.replaceAll("  "," ");
        text=text.replaceAll("  "," ");
        String[] words = text.split(" ");
        for(int i=0;i<words.length;i++){
            words[i]=Rule.toLowerCase(words[i]);
        }
        List<String> list= Arrays.asList(words);
        if(list.contains(Dictionary.IS)){
            Rule.ruleForIs(list);
        }

        if(list.contains(Dictionary.AKA)){
            Rule.ruleForAka(list);
        }
    }
}