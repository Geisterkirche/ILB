package ILBprocessing;

import ILBprocessing.beans.*;
import lib.model.Pair;
import lib.model.StarSystem;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by Алекс on 21.03.2016.
 */
public class ParserFactory extends WDSparser{
    public static void parseFile(String xxx){
        try {
            String[] fileName={"WDS"+xxx+".txt"};//all zones;
            for(int h=0;h<fileName.length;h++){
                System.out.println("Stage:"+h);
                File dataFile = new File(OUTPUT_FOLDER+WDS_GENERATED_STUFF+"/"+fileName[h]);
                FileReader in = new FileReader(dataFile);
                char c;
                StringBuffer ss = new StringBuffer();
                long d=dataFile.length();
                for(long i=0;i<d;i++){
                    c = (char) in.read();
                    ss.append(c);
                    if(c==10){
                        String s=ss.toString();
                        if(s.length()>10){
                            s=s.substring(0, s.length()-2);
                            NodeWDS star = new NodeWDS(s);
                            listWDS.add(star);
                            ss = new StringBuffer();
                        }else{
                            ss = new StringBuffer();
                        }
                    }
                    if(d-i<1){
                        String s=ss.toString();
                        NodeWDS star = new NodeWDS(s);
                        listWDS.add(star);
                        ss = new StringBuffer();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseSCO(){
        try {
            File dataFile = new File(INPUT_FOLDER+SCO_SOURCE_FILE);
            FileReader in = new FileReader(dataFile);
            char c;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    s=s.substring(0, s.length()-2);
                    NodeORB6 star = new NodeORB6(s);
                    listSCO.add(star);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseCCDMcoords(){
        try {
            String fileName="CCDM_AK.txt";
            File dataFile = new File(INPUT_FOLDER+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    s=s.substring(0, s.length()-2);
                    NodeCCDMcoords star = new NodeCCDMcoords(s);
                    listCCDMcoords.add(star);
                    ss = new StringBuffer();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseCCDM(String xxx){
        try {
            String fileName="CCDM"+xxx+".txt";
            File dataFile = new File(CCDM_GENERATED_STUFF+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            long d = dataFile.length();
            StringBuffer ss = new StringBuffer();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    //s=s.substring(0, s.length()-2);
                    //System.doNotShowBcsResolved.println(s);
                    if(s.length()>1){
                        try {
                            NodeCCDM star = new NodeCCDM(s);
                            listCCDM.add(star);
                        }catch(Exception e){
                            e.printStackTrace();
                            System.err.println(s);
                        }
                    }
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseTDSC(){
        try {
            String fileName="dataTDSC.dat";
            File dataFile = new File(INPUT_FOLDER+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    s=s.substring(0, s.length()-2);
                    //System.doNotShowBcsResolved.println(s);
                    if(s.charAt(7)==' ' && s.charAt(6)!='A'){
                        NodeTDSC star = new NodeTDSC(s);
                        listTDSC.add(star);
                    }
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseGCVS(){
        try {
            String fileName="dataGCVS.dat";
            File dataFile = new File(INPUT_FOLDER+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    s=s.substring(0, s.length()-2);
                    //System.doNotShowBcsResolved.println(s);
                    NodeGCVS star = new NodeGCVS(s);
                    listGCVS.add(star);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseWCT(String xxx){//"log2.txt"
        try {
            String fileName2="logWCT.txt";
            Writer outer2 = new FileWriter(new File(OUTPUT_FOLDER+fileName2));
            String fileName="WCT"+xxx+FILE_RESULT_FORMAT;
            File dataFile = new File(WCT_GENERATED_STUFF+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            int xLog=0;
            long timePrev=System.nanoTime();
            StringBuffer sss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                sss.append(c);
                if(c==10){
                    String s=sss.toString();
                    s=s.substring(0, s.length()-2);
                    NodeWCT star = new NodeWCT(s);
                    int h= sysList.size();
                    boolean systemExistence=false;
                    for(int j=0;j<h;j++){
                        if(sysList.get(j).idWDS.length()>9 && (star.parameter[0]).equals(sysList.get(j).idWDS.substring(0, 10)) && star.parameter[0]!=""  ||
                                (star.parameter[2]).equals(sysList.get(j).CCDMid) && star.parameter[2]!="" ||
                                (star.parameter[4]).equals(sysList.get(j).TDSCid) && star.parameter[4]!=""){
                            boolean existence=false;
                            sysList.get(j).CCDMid=star.parameter[2];
                            sysList.get(j).TDSCid=star.parameter[4];
                            iter++;
                            Pair ee = new Pair();
                            ee.pairWDS=star.parameter[1];
                            ee.pairCCDM=star.parameter[3];
                            ee.pairTDSC=star.parameter[5];
                            ee.idCCDM=star.parameter[2];//
                            ee.idTDSC=star.parameter[4];//
                            ee.modifier[1]='v';
                            //System.doNotShowBcsResolved.println(star.parameter[0]+"_"+star.parameter[2]+"_"+star.parameter[4]);
                            //System.doNotShowBcsResolved.println(s);
                            if(ee.pairWDS=="" && star.parameter[0]!=""){
                                ee.pairWDS="AB";
                            }
                            if(ee.pairWDS!=""){
                                ee.pairIdILB =ee.pairWDS;
                            }else{
                                if(ee.pairCCDM!=""){
                                    ee.pairIdILB =ee.pairCCDM;
                                }else{
                                    if(ee.pairTDSC!=""){
                                        ee.pairIdILB =ee.pairTDSC;
                                    }else{
                                        ee.pairIdILB ="AB";
                                        if(ee.pairWDS!=""){
                                            ee.pairWDS="AB";
                                        }
                                        if(ee.pairTDSC!=""){
                                            ee.pairTDSC="AB";
                                        }
                                        if(ee.pairCCDM!=""){
                                            ee.pairCCDM="AB";
                                        }
                                        System.err.println("ALARM!");
                                    }
                                }
                            }
                            ee.pairIdILB=ee.pairIdILB.replaceAll(" ","");
                            int err=ee.dispWithoutRepeatCheckerInWriter(j);
                            if(err==1){
                                System.out.println("Error 1 in pairIdILB:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                            }else if(err==2){
                                System.out.println("Error 2 in pairIdILB:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                            }
                            for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                                //if(sysList.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!="" || sysList.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM) && ee.pairCCDM!="" || sysList.get(j).pairs.get(k).pairTDSC.equals(ee.pairTDSC) && ee.pairTDSC!=""){
                                if(sysList.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!=""){
                                    existence=true;
                                    sysList.get(j).idWDS=star.parameter[0];//��� ��������
                                    sysList.get(j).pairs.get(k).idCCDM=star.parameter[2];//
                                    sysList.get(j).pairs.get(k).idTDSC=star.parameter[4];//
                                    sysList.get(j).pairs.get(k).pairWDS=star.parameter[1];
                                    sysList.get(j).pairs.get(k).pairCCDM=star.parameter[3];
                                    sysList.get(j).pairs.get(k).pairTDSC=star.parameter[5];
                                    sysList.get(j).pairs.get(k).modifier[1]='v';
                                    break;
                                }
                            }
                            if(!existence){//�� �������
                                outer2.write("ExceptionPairExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                outer2.flush();
                                Pair e = new Pair();
                                e.pairIdILB =ee.pairIdILB +"!";
                                e.el1=ee.el1;
                                e.el2=ee.el2;
                                e.el1.nameInILB +="!";
                                e.el2.nameInILB +="!";
                                e.pairWDS=star.parameter[1];
                                e.pairCCDM=star.parameter[3];
                                e.pairTDSC=star.parameter[5];
                                e.idCCDM=star.parameter[2];
                                e.idTDSC=star.parameter[4];
                                e.modifier[1]='v';
                                e.pairIdILB=e.pairIdILB.replaceAll(" ","");
                                sysList.get(j).pairs.add(e);
                            }
                            systemExistence=true;
                            break;
                        }else{
                            try{
                                if((star.parameter[2]).equals(sysList.get(j).idWDS.substring(0, 10)) && star.parameter[2]!="" || (star.parameter[4]).equals(sysList.get(j).idWDS.substring(0, 10)) && star.parameter[4]!=""){
                                    outer2.write("WarningSystemWDSExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                    outer2.flush();
                                    boolean existence=false;
                                    iter++;
                                    Pair ee = new Pair();
                                    ee.idCCDM=star.parameter[2];
                                    ee.idTDSC=star.parameter[4];
                                    ee.pairWDS=star.parameter[1];
                                    ee.pairCCDM=star.parameter[3];
                                    ee.pairTDSC=star.parameter[5];
                                    ee.modifier[1]='v';
                                    if(ee.pairWDS!=""){
                                        ee.pairIdILB =ee.pairWDS;
                                    }else{
                                        if(ee.pairCCDM!=""){
                                            ee.pairIdILB =ee.pairCCDM;
                                        }else{
                                            if(ee.pairTDSC!=""){
                                                ee.pairIdILB =ee.pairTDSC;
                                            }else{
                                                ee.pairIdILB ="AB";
                                                ee.pairWDS="AB";
                                                ee.pairTDSC="AB";
                                                ee.pairCCDM="AB";
                                                star.parameter[1]="AB";
                                                outer2.write("ExceptionPairDoesNotExist: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                                outer2.flush();
                                            }
                                        }
                                    }
                                    ee.pairIdILB=ee.pairIdILB.replaceAll(" ","");
                                    int err=ee.dispWithoutRepeatCheckerInWriter(j);
                                    if(err==1){
                                        System.out.println("Error 11 in pairIdILB:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                                    }else if(err==2){
                                        System.out.println("Error 12 in pairIdILB:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                                    }
                                    for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                                        if(sysList.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!="" || sysList.get(j).pairs.get(k).pairWDS.equals(ee.pairCCDM) && ee.pairCCDM!="" || sysList.get(j).pairs.get(k).pairWDS.equals(ee.pairTDSC) && ee.pairTDSC!=""){
                                            existence=true;
                                            sysList.get(j).pairs.get(k).modifier[1]='v';
                                            sysList.get(j).pairs.get(k).pairCCDM=star.parameter[3];
                                            sysList.get(j).pairs.get(k).pairTDSC=star.parameter[5];
                                            break;
                                        }
                                    }
                                    if(!existence){//�� �������
                                        //System.doNotShowBcsResolved.println("Error:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                                        outer2.write("ExceptionPairExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                        outer2.flush();
                                        Pair e = new Pair();
                                        e.pairIdILB =ee.pairIdILB;
                                        e.el1=ee.el1;
                                        e.el2=ee.el2;
                                        e.pairWDS=star.parameter[1];
                                        e.pairCCDM=star.parameter[3];
                                        e.pairTDSC=star.parameter[5];
                                        ee.idCCDM=star.parameter[2];
                                        ee.idTDSC=star.parameter[4];
                                        e.idTDSC=star.parameter[4];
                                        e.idCCDM=star.parameter[2];
                                        e.modifier[1]='v';
                                        ee.modifier[1]='v';
                                        e.pairIdILB=e.pairIdILB.replaceAll(" ","");
                                        sysList.get(j).pairs.add(e);
                                    }
                                    systemExistence=true;
                                    break;
                                }
                            }catch(Exception e){
                            }
                        }
                    }
                    if(systemExistence==false){
                        outer2.write("ExceptionSystemExistence2: "+star.parameter[0]+""+(char)10);
                        outer2.flush();
                        StarSystem ss= new StarSystem();
                        sysList.add(ss);
                        ss.idWDS=star.parameter[0]+"           ";
                        ss.CCDMid=star.parameter[2];
                        ss.TDSCid=star.parameter[4];
                        ss.wdsSystemID =star.parameter[2];
                        ss.data="                  ";
                        ss.observ="";
                        ss.coordinatesNotFoundInWDS =true;
                        Pair ee = new Pair();
                        ee.pairIdILB =star.parameter[3];
                        ee.pairIdILB=ee.pairIdILB.replaceAll(" ","");
                        if(ee.pairIdILB.length()==1){
                            System.out.println(star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                        }
                        int err=ee.dispWithoutRepeatCheckerInWriter(-1);
                        Pair e = new Pair();
                        e.pairIdILB =ee.pairIdILB;
                        e.el1=ee.el1;
                        e.el2=ee.el2;
                        e.pairWDS=star.parameter[1];
                        e.pairCCDM=star.parameter[3];
                        e.pairTDSC=star.parameter[5];
                        e.idTDSC=star.parameter[4];
                        e.idCCDM=star.parameter[2];
                        e.modifier[1]='v';
                        ss.pairs.add(e);
                    }
                    sss = new StringBuffer();
                    if(xLog*1000000<i){
                        System.out.println(i+"/"+d+ "bytes readed in "+ (System.nanoTime()-timePrev)/1000000+"ms");
                        timePrev=System.nanoTime();
                        xLog=(int) (i/1000000+1);
                    }
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void parseCEV(){
        //parseGCVS();
        try {
            String fileName="dataCEV.txt";
            File dataFile = new File(INPUT_FOLDER+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    s=s.substring(0, s.length()-2);
                    //System.doNotShowBcsResolved.println(s);
                    NodeCEV star = new NodeCEV(s);
                    listCEV.add(star);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseINT4(String xxx) {
        try {
            String fileName = "INT"+xxx+FILE_RESULT_FORMAT;
            File dataFile = new File(INT4_GENERATED_STUFF+fileName);
            FileReader in = new FileReader(dataFile);
            char c=' ';
            char cPrev;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            ArrayList<String> s = new ArrayList<String>();
            for(long i=0;i<d;i++){
                cPrev=c;
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    if(c!=cPrev){
                        String sloc=ss.toString();
                        sloc=sloc.substring(0, sloc.length()-2);
                        s.add(sloc);
                    }else{
                        NodeINT4 star = new NodeINT4(s);
                        listINT4.add(star);
                        s = new ArrayList<String>();
                    }
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for(int i=0; i<listINT4.size();i++){
            if(listINT4.get(i).fakePair){
                listINT4.remove(i);
                i--;
            }
        }
    }
    public static void parseSB9() {
        try {
            String fileName="dataSB9.dta";
            File dataFile = new File(INPUT_FOLDER+fileName);
            FileReader in = new FileReader(dataFile);
            char c;
            String prevString="           ";
            int indexPrev = 0;
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    s=s.substring(0, s.length()-1);
                    int index=0;
                    while(s.charAt(index)!='|'){
                        index++;
                    }
                    if(!s.substring(0,index).equals(prevString.substring(0,indexPrev))){
                        NodeSB9 star = new NodeSB9(s, index);
                        listSB9.add(star);
                    }else{
                        listSB9.get(listSB9.size()-1).addEff(s, index);
                    }
                    prevString=s;
                    indexPrev=index;
                    //System.doNotShowBcsResolved.println(s);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String fileName="dataSB9main.txt";
            File dataFile = new File(INPUT_FOLDER+fileName);
            FileReader in = new FileReader(dataFile);
            StringBuffer ss = new StringBuffer();
            long d = dataFile.length();
            char c;
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    if(s.length()>10){
                        s=s.substring(0, s.length()-2);
                        NodeSB9main star = new NodeSB9main(s);
                        listSB9main.add(star);
                        ss = new StringBuffer();
                    }else{
                        ss = new StringBuffer();
                    }
                }
                if(d-i<1){
                    String s=ss.toString();
                    NodeSB9main star = new NodeSB9main(s);
                    listSB9main.add(star);
                    ss = new StringBuffer();
                }
            }

            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        int counter=0;
        for(int i=0;i<listSB9main.size();i++){
            for(int j=0;j<listSB9.size();j++){
                if(listSB9main.get(i).SB9.equals(listSB9.get(j).SB9)){
                    counter++;
                    listSB9.get(j).mainID=listSB9main.get(i).mainID;
                    listSB9.get(j).data=listSB9main.get(i).data;
                    //System.doNotShowBcsResolved.println("SB9 counter = "+ counter);
                }
            }
        }
    }
}
