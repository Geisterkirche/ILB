package tools;

import application.WDSparser;
import datasources.*;
import structureEntities.Pair;
import structureEntities.StarSystem;

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
        try {//String[]fileName={"data06.txt","data12.txt","data18.txt","data24.txt"};
            String[] fileName={"WDS"+xxx+".txt"};
            for(int h=0;h<fileName.length;h++){
                System.out.println("Stage:"+h);
                File dataFile = new File("C:/WDSparser/WDS/"+fileName[h]);
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
                            globalList.add(star);
                            ss = new StringBuffer();
                        }else{
                            ss = new StringBuffer();
                        }
                    }
                    if(d-i<1){
                        String s=ss.toString();
                        NodeWDS star = new NodeWDS(s);
                        globalList.add(star);
                        ss = new StringBuffer();
                    }
                }
            }
            System.out.println("Success. fileLength=");
        } catch (Exception e) {
            //System.out.println("����� ����!");
            //e.printStackTrace();
        }
		/*
		for(int i=0;i<globalList.size();i++){
			System.out.print(globalList.get(i).identifyer+"   ");
			System.out.print(globalList.get(i).number+"   ");
			System.out.print(globalList.get(i).pair+"   ");
			System.out.println(globalList.get(i).data+"   ");
		}
	*/
    }
    public static void parseSCO(){
        try {
            String fileName="dataSCO.txt";
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
                    NodeSCO star = new NodeSCO(s);
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
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
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
            File dataFile = new File("C:/WDSparser/CCDM/"+fileName);
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
                    //System.out.println(s);
                    if(s.length()>1){
                        NodeCCDM star = new NodeCCDM(s);
                        listCCDM.add(star);
                    }
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            System.out.print("$");
        }
    }
    public static void parseTDSC(){
        try {
            String fileName="dataTDSC.dat";
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
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
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
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
            String fileName2="log2.txt";
            Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName2));
            String fileName="WCT"+xxx+".txt";
            File dataFile = new File("C:/WDSparser/WCT/"+fileName);
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
                    int h=system.size();
                    boolean systemExistence=false;
                    for(int j=0;j<h;j++){
                        if(system.get(j).idWDS.length()>9 && (star.parameter[0]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[0]!=""  ||
                                (star.parameter[2]).equals(system.get(j).CCDMid) && star.parameter[2]!="" ||
                                (star.parameter[4]).equals(system.get(j).TDSCid) && star.parameter[4]!=""){
                            boolean existence=false;
                            system.get(j).CCDMid=star.parameter[2];
                            system.get(j).TDSCid=star.parameter[4];
                            iter++;
                            Pair ee = new Pair();
                            ee.pairWDS=star.parameter[1];
                            ee.pairCCDM=star.parameter[3];
                            ee.pairTDSC=star.parameter[5];
                            ee.idCCDM=star.parameter[2];//
                            ee.idTDSC=star.parameter[4];//
                            ee.modyfyer[1]='v';
                            //System.out.println(star.parameter[0]+"_"+star.parameter[2]+"_"+star.parameter[4]);
                            //System.out.println(s);
                            if(ee.pairWDS=="" && star.parameter[0]!=""){
                                ee.pairWDS="AB";
                            }
                            if(ee.pairWDS!=""){
                                ee.id=ee.pairWDS;
                            }else{
                                if(ee.pairCCDM!=""){
                                    ee.id=ee.pairCCDM;
                                }else{
                                    if(ee.pairTDSC!=""){
                                        ee.id=ee.pairTDSC;
                                    }else{
                                        ee.id="AB";
                                        if(ee.pairWDS!=""){
                                            ee.pairWDS="AB";
                                        }
                                        if(ee.pairTDSC!=""){
                                            ee.pairTDSC="AB";
                                        }
                                        if(ee.pairCCDM!=""){
                                            ee.pairCCDM="AB";
                                        }
                                        ee.fakeName=true;
                                        star.parameter[1]="AB";
                                        outer2.write("ExceptionPairNameDNE: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                        outer2.flush();
                                    }
                                }
                            }
                            ee.clean();
                            int err=ee.dispWithoutRepeatCheckerInWriter(j);
                            if(err==1){
                                System.out.println("Error 1 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                            }else if(err==2){
                                System.out.println("Error 2 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                            }
                            for(int k=0;k<system.get(j).pairs.size();k++){
                                //if(system.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!="" || system.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM) && ee.pairCCDM!="" || system.get(j).pairs.get(k).pairTDSC.equals(ee.pairTDSC) && ee.pairTDSC!=""){
                                if(system.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!=""){
                                    existence=true;
                                    system.get(j).idWDS=star.parameter[0];//��� ��������
                                    system.get(j).pairs.get(k).idCCDM=star.parameter[2];//
                                    system.get(j).pairs.get(k).idTDSC=star.parameter[4];//
                                    system.get(j).pairs.get(k).pairWDS=star.parameter[1];
                                    system.get(j).pairs.get(k).pairCCDM=star.parameter[3];
                                    system.get(j).pairs.get(k).pairTDSC=star.parameter[5];
                                    system.get(j).pairs.get(k).modyfyer[1]='v';
                                    break;
                                }
                            }
                            if(!existence){//�� �������
                                outer2.write("ExceptionPairExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                outer2.flush();
                                Pair e = new Pair();
                                e.id=ee.id+"!";
                                e.el1=ee.el1;
                                e.el2=ee.el2;
                                e.el1.name+="!";
                                e.el2.name+="!";
                                e.pairWDS=star.parameter[1];
                                e.pairCCDM=star.parameter[3];
                                e.pairTDSC=star.parameter[5];
                                e.idCCDM=star.parameter[2];
                                e.idTDSC=star.parameter[4];
                                e.modyfyer[1]='v';
                                e.clean();
                                system.get(j).pairs.add(e);
                            }
                            systemExistence=true;
                            break;
                        }else{
                            try{
                                if((star.parameter[2]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[2]!="" || (star.parameter[4]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[4]!=""){
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
                                    ee.modyfyer[1]='v';
                                    if(ee.pairWDS!=""){
                                        ee.id=ee.pairWDS;
                                    }else{
                                        if(ee.pairCCDM!=""){
                                            ee.id=ee.pairCCDM;
                                        }else{
                                            if(ee.pairTDSC!=""){
                                                ee.id=ee.pairTDSC;
                                            }else{
                                                ee.id="AB";
                                                ee.pairWDS="AB";
                                                ee.pairTDSC="AB";
                                                ee.pairCCDM="AB";
                                                star.parameter[1]="AB";
                                                outer2.write("ExceptionPairDoesNotExist: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                                outer2.flush();
                                            }
                                        }
                                    }
                                    ee.clean();
                                    int err=ee.dispWithoutRepeatCheckerInWriter(j);
                                    if(err==1){
                                        System.out.println("Error 11 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                                    }else if(err==2){
                                        System.out.println("Error 12 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                                    }
                                    for(int k=0;k<system.get(j).pairs.size();k++){
                                        if(system.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!="" || system.get(j).pairs.get(k).pairWDS.equals(ee.pairCCDM) && ee.pairCCDM!="" || system.get(j).pairs.get(k).pairWDS.equals(ee.pairTDSC) && ee.pairTDSC!=""){
                                            existence=true;
                                            system.get(j).pairs.get(k).modyfyer[1]='v';
                                            system.get(j).pairs.get(k).pairCCDM=star.parameter[3];
                                            system.get(j).pairs.get(k).pairTDSC=star.parameter[5];
                                            break;
                                        }
                                    }
                                    if(!existence){//�� �������
                                        //System.out.println("Error:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                                        outer2.write("ExceptionPairExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
                                        outer2.flush();
                                        Pair e = new Pair();
                                        e.id=ee.id;
                                        e.el1=ee.el1;
                                        e.el2=ee.el2;
                                        e.pairWDS=star.parameter[1];
                                        e.pairCCDM=star.parameter[3];
                                        e.pairTDSC=star.parameter[5];
                                        ee.idCCDM=star.parameter[2];
                                        ee.idTDSC=star.parameter[4];
                                        e.idTDSC=star.parameter[4];
                                        e.idCCDM=star.parameter[2];
                                        e.modyfyer[1]='v';
                                        ee.modyfyer[1]='v';
                                        e.clean();
                                        system.get(j).pairs.add(e);
                                    }
                                    systemExistence=true;
                                    break;
                                }
                            }catch(Exception e){
                            }
                        }
                    }
                    if(systemExistence==false){//�� �������
                        outer2.write("ExceptionSystemExistence2: "+star.parameter[0]+""+(char)10);
                        outer2.flush();
                        StarSystem ss= new StarSystem();
                        system.add(ss);
                        ss.idWDS=star.parameter[0]+"           ";
                        ss.CCDMid=star.parameter[2];
                        ss.TDSCid=star.parameter[4];
                        ss.identifyer=star.parameter[2];
                        ss.data="                  ";
                        ss.observ="";
                        ss.fake=true;
                        Pair ee = new Pair();
                        ee.id=star.parameter[3];
                        ee.clean();
                        if(ee.id.length()==1){
                            System.out.println(star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
                        }
                        int err=ee.dispWithoutRepeatCheckerInWriter(-1);
                        Pair e = new Pair();
                        e.id=ee.id;
                        e.el1=ee.el1;
                        e.el2=ee.el2;
                        e.pairWDS=star.parameter[1];
                        e.pairCCDM=star.parameter[3];
                        e.pairTDSC=star.parameter[5];
                        e.idTDSC=star.parameter[4];
                        e.idCCDM=star.parameter[2];
                        e.modyfyer[1]='v';
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
        parseGCVS();
        try {
            String fileName="dataCEV.txt";
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
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
            String fileName = "INT"+xxx+".txt";
            File dataFile = new File("C:/WDSparser/INT/"+fileName);
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
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String fileName="dataSB9main.txt";
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println("SB9 counter = "+ counter);
                }
            }
        }
    }
    public static void parseLMXB() {
        try {
            String fileName="dataLMXB.dat";
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
                    NodeLMXB star = new NodeLMXB(s);
                    listLMXB.add(star);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseHMXB() {
        try {
            String fileName="dataHMXB.dat";
            File dataFile = new File("C:/WDSparser/"+fileName);
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
                    //System.out.println(s);
                    NodeHMXB star = new NodeHMXB(s);
                    listHMXB.add(star);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
