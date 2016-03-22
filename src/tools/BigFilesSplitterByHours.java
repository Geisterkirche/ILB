package tools;

import tools.service.GlobalConfiguration;
import tools.service.SharedConstants;

import java.io.*;

/**
 * Created by Алекс on 22.03.2016.
 */
public class BigFilesSplitterByHours implements SharedConstants, GlobalConfiguration {
    public static void split(){
        if(WDS_SPLIT_BEFORE_PROCESSING) {
            splitterWDS();
            System.out.println("WDS spliting finished");
        }
        if(CCDM_SPLIT_BEFORE_PROCESSING) {
            splitterCCDM();
            System.out.println("CCDM spliting finished");
        }
        if(WCT_SPLIT_BEFORE_PROCESSING) {
            splitterWCT();
            System.out.println("WCT spliting finished");
        }
        if(INT4_SPLIT_BEFORE_PROCESSING) {
            splitterINT4();
            System.out.println("INT4 spliting finished");
        }
    }
    public static void splitterWDS(){
        try {
            for(int n=0;n<24;n++){
                for(int m=0;m<6;m++){
                    String fileLogName="WDS"+n+m+FILE_RESULT_FORMAT;
                    File file= new File(WDS_GENERATED_STUFF);
                    file.mkdirs();
                    file= new File(WDS_GENERATED_STUFF+fileLogName);
                    Writer outer = new FileWriter(file);
                    String[]fileName=WDS_SOURCE_FILES;
                    for(int h=0;h<fileName.length;h++){
                        File dataFile = new File(INPUT_FOLDER+fileName[h]);
                        FileReader in = new FileReader(dataFile);
                        char c;
                        StringBuffer ss = new StringBuffer();
                        long d=dataFile.length();
                        for(long i=0;i<d;i++){
                            c = (char) in.read();
                            ss.append(c);
                            if(c==10){
                                String s=ss.toString();
                                if(Integer.parseInt(""+s.charAt(0)+s.charAt(1)+s.charAt(2))==n*10+m){
                                    outer.write(s+'\n');
                                    outer.flush();
                                }
                                ss = new StringBuffer();
                            }
                            if(d-i<1){
                                String s=ss.toString();
                                if(Integer.parseInt(""+s.charAt(0)+s.charAt(1)+s.charAt(2))==n*10+m){
                                    outer.write(s+'\n');
                                    outer.flush();
                                }
                                ss = new StringBuffer();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void splitterCCDM(){
        try {
            for(int n=0;n<24;n++){for(int m=0;m<6;m++){
                String fileLogName="CCDM"+n+m+FILE_RESULT_FORMAT;
                File file= new File(CCDM_GENERATED_STUFF);
                file.mkdirs();
                file= new File(CCDM_GENERATED_STUFF+fileLogName);
                Writer outer = new FileWriter(file);
                String fileName=CCDM_SOURCE_FILE;
                File dataFile = new File(INPUT_FOLDER+fileName);
                FileReader in = new FileReader(dataFile);
                char c;
                long d = dataFile.length();
                StringBuffer ss = new StringBuffer();
                for(long i=0;i<d;i++){
                    c = (char) in.read();
                    ss.append(c);
                    if(c==10){
                        String s=ss.toString();
                        if(Integer.parseInt(""+s.charAt(1)+s.charAt(2)+s.charAt(3))==n*10+m){
                            outer.write(s+'\n');
                            outer.flush();
                        }
                        ss = new StringBuffer();
                    }
                }
            }}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void splitterWCT(){
        try {
            for(int n=0;n<24;n++){for(int m=0;m<6;m++){
                String fileLogName="WCT"+n+m+FILE_RESULT_FORMAT;
                File file= new File(WCT_GENERATED_STUFF);
                file.mkdirs();
                file= new File(WCT_GENERATED_STUFF+fileLogName);
                Writer outer = new FileWriter(file);
                String fileName=WCT_SOURCE_FILE;
                File dataFile = new File(INPUT_FOLDER+fileName);
                FileReader in = new FileReader(dataFile);
                char c;
                long d = dataFile.length();
                StringBuffer ss = new StringBuffer();
                for(long i=0;i<d;i++){
                    c = (char) in.read();
                    ss.append(c);
                    if(c==10){
                        String s=ss.toString();
                        try{
                            if((""+s.charAt(33)+s.charAt(34)).equals("\"\"") && Integer.parseInt(""+s.charAt(50)+s.charAt(51)+s.charAt(52))==n*10+m || Integer.parseInt(""+s.charAt(33)+s.charAt(34)+s.charAt(35))==n*10+m){
                                outer.write(s);
                                outer.flush();
                            }
                        }catch(Exception e){
                        }
                        ss = new StringBuffer();
                    }
                }
            }}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void splitterINT4(){
        try {
            for(int n=0;n<24;n++){for(int m=0;m<6;m++){
                String fileLogName="INT"+n+m+FILE_RESULT_FORMAT;
                File file= new File(INT4_GENERATED_STUFF);
                file.mkdir();
                file= new File(INT4_GENERATED_STUFF+fileLogName);
                Writer outer = new FileWriter(file);
                String fileName=INT4_SOURCE_FILE;
                File dataFile = new File(INPUT_FOLDER+fileName);
                FileReader in = new FileReader(dataFile);
                char c;
                long d = dataFile.length();
                boolean writeMode =false;
                StringBuffer ss = new StringBuffer();
                for(long i=0;i<d;i++){
                    c = (char) in.read();
                    ss.append(c);
                    if(c==10){
                        String s=ss.toString();
                        try{
                            if(s.charAt(0)=='0' || s.charAt(0)=='1' || s.charAt(0)=='2'){
                                if(Integer.parseInt(s.substring(0,3))==n*10+m){
                                    writeMode=true;
                                }else{
                                    writeMode=false;
                                }
                            }
                            if(writeMode){
                                outer.write(s);
                                outer.flush();
                            }
                        }catch(Exception e){
                        }
                        ss = new StringBuffer();
                    }
                }
            }}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void concatenator(){
        int componentCounter=0;
        int systemCounter=0;
        int pairCounter=0;
        try{
            String fileName2=ILB_RESULT_FILE;
            Writer outer2 = new FileWriter(new File(OUTPUT_FOLDER+fileName2));
            String fileName;
            for(int i=0;i<24;i++){
                for(int j=0;j<6;j++){
                    fileName=FILE_NAME_DEFAULT+i+j+FILE_RESULT_FORMAT;
                    File dataFile = new File(OUTPUT_FOLDER+fileName);
                    FileReader in = new FileReader(dataFile);
                    char c;
                    StringBuffer ss = new StringBuffer();
                    long d = dataFile.length();
                    for(long ii=0;ii<d;ii++){
                        c=(char)in.read();
                        ss.append(c);
                        if(c==10){
                            outer2.append(ss);
                            outer2.flush();
                            if(ss.toString().contains(":s")){
                                systemCounter++;
                            }else if(ss.toString().contains(":p")){
                                pairCounter++;
                            }else if(ss.toString().contains(":c")){
                                componentCounter++;
                            }else{
                                System.out.println(ss);
                            }
                            ss = new StringBuffer();
                        }
                    }
                    System.out.println("File"+fileName+"successful concatenated");
                    System.out.println("Systems :"+systemCounter);
                    System.out.println("Pairs :"+pairCounter);
                    System.out.println("Components :"+componentCounter);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
