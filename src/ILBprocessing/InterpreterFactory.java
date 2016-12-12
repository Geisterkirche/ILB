package ILBprocessing;

import ILBprocessing.datasources.ORB6DS;
import lib.model.Component;
import lib.model.Pair;
import lib.model.StarSystem;
import lib.tools.ConverterFINALIZED;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by Алекс on 22.03.2016.
 */
public class InterpreterFactory extends WDSparser {
    public static void interpr(){
        int xLog=0;
        long timePrev=System.nanoTime();
        int d= listWDS.size();
        for(int i=0;i<d;i++){
            firstClassificationOfpair(i);
            if(xLog*500<=i){
                System.out.println("............snap:"+i+"/"+d+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                timePrev=System.nanoTime();
                xLog=(int) (i/500+1);
            }
        }
    }
    public static void interprSCO(){
        try{
            int f=listSCO.size();
            for(int i=0;i<f;i++){
                level1: for(int j = 0; j< sysList.size(); j++){
                    if((listSCO.get(i).idWDS).equals(sysList.get(j).idWDS)){
                        for(int k=0;k<sysList.get(j).pairs.size();k++){
                            if(listSCO.get(i).pairWDS!="" && sysList.get(j).pairs.get(k).pairWDS.equals(listSCO.get(i).pairWDS)){
                                ORB6DS.propagate(sysList.get(j).pairs.get(k),listSCO.get(i));
                                listSCO.remove(i);
                                i--;
                                f--;
                                break level1;
                            }else if(sysList.get(j).pairs.get(k).observer.equals(listSCO.get(i).nameOfObserver)){
                                ORB6DS.propagate(sysList.get(j).pairs.get(k),listSCO.get(i));
                                listSCO.remove(i);
                                i--;
                                f--;
                                break level1;
                            }else if(sysList.get(j).pairs.get(k).idDM.equals(listSCO.get(i).idDM)){
                                ORB6DS.propagate(sysList.get(j).pairs.get(k),listSCO.get(i));
                                listSCO.remove(i);
                                i--;
                                f--;
                                break level1;
                            }else if(sysList.get(j).pairs.get(k).modifier[0]=='o'){
                                ORB6DS.propagate(sysList.get(j).pairs.get(k),listSCO.get(i));
                                listSCO.remove(i);
                                i--;
                                f--;
                                break level1;
                            }
                        }
                    }
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }
    public static void interprTDSC(){//"log4.txt"
        try{
            //String fileName="logTDSC.txt";
            //Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
            int f= sysList.size();
            int h=listTDSC.size();
            for(int j=0;j<f;j++){
                int zzz= sysList.get(j).pairs.size();
                for(int k=0;k<zzz;k++){
                    if(sysList.get(j).pairs.get(k).idTDSC==null || sysList.get(j).pairs.get(k).idTDSC==""){

                    }else{
                        boolean systemExistence=false;
                        layer_1: for(int i=0;i<h;i++){
						/*System.doNotShowBcsResolved.println("a"+sysList.get(j).mainID);
						System.doNotShowBcsResolved.println("b"+sysList.get(j).wdsSystemID);
						System.doNotShowBcsResolved.println("c"+sysList.get(j).observ);
						System.doNotShowBcsResolved.println("d"+sysList.get(j).mainID.substring(0, 10));-*/
                            try{
                                int u=Integer.parseInt(listTDSC.get(i).TDSCid);
                                int gfg=0;
                                if(sysList.get(j).pairs.get(k).idTDSC.charAt(0)!='"'){
                                    gfg=Integer.parseInt(sysList.get(j).pairs.get(k).idTDSC);
                                }
                                if(gfg==0){
                                    break;
                                }
                                if(u==gfg){
                                    iter++;
                                    sysList.get(j).pairs.get(k).modifier[3]='v';
                                    if(sysList.get(j).pairs.get(k).idHD==""){
                                        sysList.get(j).pairs.get(k).idHD=listTDSC.get(i).HD;
                                    }else{
                                        //if(!(sysList.get(j).pairs.get(k).idHD.contains(listTDSC.get(i).HD) || listTDSC.get(i).HD.contains(sysList.get(j).pairs.get(k).idHD))){
                                        //outer2.write("CompareError HD:"+sysList.get(j).pairs.get(k).idHD+"_"+listTDSC.get(i).HD+(char)10);
                                        //}
                                    }
                                    if(sysList.get(j).pairs.get(k).idHIP==""){
                                        sysList.get(j).pairs.get(k).idHIP=listTDSC.get(i).HIPid;
                                    }else{
                                        //if(!(sysList.get(j).pairs.get(k).idHIP.contains(listTDSC.get(i).HIPid) || listTDSC.get(i).HIPid.contains(sysList.get(j).pairs.get(k).idHIP))){
                                        //outer2.write("CompareError HIP:"+sysList.get(j).pairs.get(k).idHIP+"_"+listTDSC.get(i).HIPid+(char)10);
                                        //}
                                    }
                                    //outer2.write("Existence: HD "+listTDSC.get(i).HD+" CCDMidPAIR "+listTDSC.get(i).CCDMidPAIR+" TDSCid "+listTDSC.get(i).TDSCid+" "+(char)10);
                                    //outer2.flush();
                                    break layer_1;
                                }
                            }catch(Exception rf){
                                rf.printStackTrace();
                            }
                        }
                        if(systemExistence==false){
                            //outer2.write("ExceptionSysExistence: HD "+listTDSC.get(i).pairTDSC+"  "+listTDSC.get(i).TDSCid+" "+(char)10);
                            //outer2.flush();
                        }
                    }
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }
    public static void interprCCDM(){//"log3.txt"
        int xLog=0;
        int matches=0;
        long timePrev=System.nanoTime();
        try{
            int f=listCCDM.size();
            System.out.println("listCCDM.size()= "+listCCDM.size());
            int h;
            for(int i=0;i<f;i++){
                h= sysList.size();
                upper:{ for(int j=0;j<h;j++){
                    if(sysList.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(sysList.get(j).CCDMid)){
                        for(int dd = 0; dd< sysList.get(j).pairs.size(); dd++){
                            //������� �������.
                            if(sysList.get(j).pairs.get(dd).pairCCDM.equals(listCCDM.get(i).pairCCDM)){
                                //iter++;
                                int k=dd;
                                if(sysList.get(j).pairs.get(k).idHD==""){
                                    if(listCCDM.get(i).HD!=""){
                                        sysList.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
                                        if((""+listCCDM.get(i).componentInfo)== sysList.get(j).pairs.get(k).el1.nameInILB){
                                            sysList.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
                                        }else{
                                            sysList.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
                                            sysList.get(j).pairs.get(k).el2.idHD=listCCDM.get(i).HD;
                                        }
                                    }
                                }
                                if(sysList.get(j).pairs.get(k).idHIP=="" ){
                                    if(listCCDM.get(i).HIP!=""){
                                        sysList.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
                                        if((""+listCCDM.get(i).componentInfo)== sysList.get(j).pairs.get(k).el1.nameInILB){
                                            sysList.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
                                        }else{
                                            sysList.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).HIP;
                                            sysList.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
                                        }
                                    }
                                }
                                if(sysList.get(j).pairs.get(k).idADS==""){
                                    sysList.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
                                }
                                if(sysList.get(j).pairs.get(k).idDM=="" ){
                                    if(listCCDM.get(i).DM!=""){
                                        sysList.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
                                        if((""+listCCDM.get(i).componentInfo)== sysList.get(j).pairs.get(k).el1.nameInILB){
                                            sysList.get(j).pairs.get(k).el1.idDM=listCCDM.get(i).DM;
                                        }else{
                                            sysList.get(j).pairs.get(k).el2.idDM=listCCDM.get(i).DM;
                                            sysList.get(j).pairs.get(k).el1.idDM=listCCDM.get(i).DM;
                                        }
                                    }
                                }
                                if(listCCDM.get(i).astrometric){
                                    sysList.get(j).pairs.get(k).modifier[4]='a';
                                }else{
                                    sysList.get(j).pairs.get(k).modifier[4]='v';
                                }
                                matches+=1;
                                break upper;
                            }
                        }
                    }
                }}
                if(xLog*1000<=i){
                    System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                    timePrev=System.nanoTime();
                    xLog=(int) (i/1000+1);
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
        System.out.println("f= "+listCCDM.size());
        System.out.println("matches= "+matches);
    }
    public static void interprINT4() {//"logINT4.txt"
        int xLog=0;
        long timePrev=System.nanoTime();
        try{
            String fileName="logINT4.txt";
            Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
            int f=listINT4.size();
            int h;
            boolean exists = false;
            for(int i=0;i<f;i++){
                h= sysList.size();
                exists = false;
                layer1:{ for(int j=0;j<h;j++){
                    try{
                        if(listINT4.get(i).idWDS!= null && listINT4.get(i).idWDS!="" && !(sysList.get(j).idWDS.substring(0, 2).equals("  ")) && (listINT4.get(i).idWDS).equals(sysList.get(j).idWDS.substring(0, 10))){
                            exists = true;
                            for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                                if(sysList.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd) || sysList.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd1)){
                                    if(sysList.get(j).pairs.get(k).idADS!=null && listINT4.get(i).name1.contains(sysList.get(j).pairs.get(k).idADS) && sysList.get(j).pairs.get(k).idADS!="" || sysList.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name1.contains(sysList.get(j).pairs.get(k).idDM) && sysList.get(j).pairs.get(k).idDM!=""){
                                        sysList.get(j).pairs.get(k).modifier[6]='i';
                                        break layer1;
                                    }else if(sysList.get(j).pairs.get(k).idBayer!=null && listINT4.get(i).name2.contains(sysList.get(j).pairs.get(k).idBayer) && sysList.get(j).pairs.get(k).idBayer!="" || sysList.get(j).pairs.get(k).idFlamsteed !=null && listINT4.get(i).name2.contains(sysList.get(j).pairs.get(k).idFlamsteed) && sysList.get(j).pairs.get(k).idFlamsteed !=""){
                                        sysList.get(j).pairs.get(k).modifier[6]='i';
                                        if(sysList.get(j).pairs.get(k).idHD==""){
                                            sysList.get(j).pairs.get(k).idHD=listINT4.get(i).idHD;
                                        }
                                        if(sysList.get(j).pairs.get(k).idHIP==""){
                                            sysList.get(j).pairs.get(k).idHIP=listINT4.get(i).idHIP;
                                        }
                                        if(sysList.get(j).pairs.get(k).idFlamsteed ==""){
                                            sysList.get(j).pairs.get(k).idFlamsteed =listINT4.get(i).idFlamsteed;
                                        }
                                        if(sysList.get(j).pairs.get(k).idBayer==""){
                                            sysList.get(j).pairs.get(k).idFlamsteed =listINT4.get(i).idBayer;
                                        }
                                        if(sysList.get(j).pairs.get(k).idDM==""){
                                            sysList.get(j).pairs.get(k).idDM=listINT4.get(i).idDM;
                                        }
                                        if(sysList.get(j).pairs.get(k).el1.coord1I==0 && sysList.get(j).pairs.get(k).el1.coord2I==0 && listINT4.get(i).rho!=-1){
                                            try{
                                                sysList.get(j).pairs.get(k).el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
                                                sysList.get(j).pairs.get(k).el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
                                                sysList.get(j).pairs.get(k).el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
                                                sysList.get(j).pairs.get(k).el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
                                                sysList.get(j).pairs.get(k).el1.coord_flag=31;
                                                if(sysList.get(j).pairs.get(k).el1.coord2I<0){
                                                    sysList.get(j).pairs.get(k).el1.coord2F*=-1;
                                                }
                                                if(sysList.get(j).pairs.get(k).el1.coord1I!=0 || sysList.get(j).pairs.get(k).el1.coord2I!=0){
                                                    sysList.get(j).pairs.get(k).el2.coord1I=(int)(long) ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(sysList.get(j).pairs.get(k).el1.coord1I, sysList.get(j).pairs.get(k).el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(sysList.get(j).pairs.get(k).el1.coord2I, sysList.get(j).pairs.get(k).el1.coord2F)));
                                                    sysList.get(j).pairs.get(k).el2.coord1F=(int)(long)(100*(ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(sysList.get(j).pairs.get(k).el1.coord1I, sysList.get(j).pairs.get(k).el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(sysList.get(j).pairs.get(k).el1.coord2I, sysList.get(j).pairs.get(k).el1.coord2F)))- sysList.get(j).pairs.get(k).el2.coord1I));
                                                    sysList.get(j).pairs.get(k).el2.coord2I=(int)(long) ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(sysList.get(j).pairs.get(k).el1.coord2I, sysList.get(j).pairs.get(k).el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                                    sysList.get(j).pairs.get(k).el2.coord2F=(int)(long)(10*(ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(sysList.get(j).pairs.get(k).el1.coord2I, sysList.get(j).pairs.get(k).el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))- sysList.get(j).pairs.get(k).el2.coord2I));
                                                    sysList.get(j).pairs.get(k).el2.coord_flag=326666;
                                                }
                                            }catch(Exception td){
                                                td.printStackTrace();
                                            }
                                        }
                                        break layer1;
                                    }else if(sysList.get(j).pairs.get(k).idHD!=null && listINT4.get(i).name3.contains(sysList.get(j).pairs.get(k).idHD) && sysList.get(j).pairs.get(k).idHD!="" || sysList.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name3.contains(sysList.get(j).pairs.get(k).idDM) && sysList.get(j).pairs.get(k).idDM!=""){
                                        sysList.get(j).pairs.get(k).modifier[6]='i';
                                        break layer1;
                                    }else if(sysList.get(j).pairs.get(k).idHIP!=null && sysList.get(j).pairs.get(k).idHIP!="" && listINT4.get(i).name4.contains(sysList.get(j).pairs.get(k).idHIP) || sysList.get(j).pairs.get(k).observer!=null && sysList.get(j).pairs.get(k).observer!="" && listINT4.get(i).name2.contains(sysList.get(j).pairs.get(k).observer)){//discoverer 2!!!
                                        sysList.get(j).pairs.get(k).modifier[6]='i';
                                        break layer1;
                                    }else if(listINT4.get(i).rho!=0 && sysList.get(j).pairs.get(k).rho!=0 && listINT4.get(i).rho/ sysList.get(j).pairs.get(k).rho>1/3 && listINT4.get(i).rho/ sysList.get(j).pairs.get(k).rho<3){
                                        sysList.get(j).pairs.get(k).modifier[6]='i';
                                        break layer1;
                                    }
                                    System.out.println(listINT4.get(i).rho+" "+ sysList.get(j).pairs.get(k).rho);
                                }
                            }
                            //���� ������, � ���� �� ���������, ������ ��� ���� ���� ���������
                            if(listINT4.get(i).dd.contains("Ab comp")){
                                Pair e = new Pair();
                                e.pairIdILB ="Aba-Abb";
                                e.el1.nameInILB ="Aba";
                                e.el2.nameInILB ="Abb";
//								e.el1.coord1I=Integer.parseInt(listINT4.get(i).coordinatesFromWDSasString.substring(0,6));
//								e.el1.coord1F=Integer.parseInt(listINT4.get(i).coordinatesFromWDSasString.substring(7,9));
//								e.el1.coord2I=Integer.parseInt(listINT4.get(i).coordinatesFromWDSasString.substring(9,16));
//								e.el1.coord2F=Integer.parseInt(listINT4.get(i).coordinatesFromWDSasString.substring(17,18));
                                e.pairWDS="";
                                e.observer=listINT4.get(i).dd1;
                                e.idHD=listINT4.get(i).idHD;
                                e.idHIP=listINT4.get(i).idHIP;
                                e.idADS=listINT4.get(i).idADS;
                                e.idFlamsteed =listINT4.get(i).idFlamsteed;
                                e.idDM=listINT4.get(i).idDM;
                                e.idBayer=listINT4.get(i).idBayer;
                                e.modifier[6]='i';
                                if(listINT4.get(i).rho!=-1){
                                    try{
                                        e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
                                        e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
                                        e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
                                        e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
                                        System.out.println("XXX"+listINT4.get(i).data);
                                        System.out.println(e.el1.coord2I);
                                        e.el1.coord_flag=31;
                                        if(e.el1.coord2I<0){
                                            e.el1.coord2F*=-1;
                                        }
                                        if(e.el1.coord1I!=0 || e.el1.coord2I!=0){
                                            e.el2.coord1I=(int)(long) ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)));
                                            e.el2.coord1F=(int)(long)(100*(ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                                            e.el2.coord2I=(int)(long) ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                            e.el2.coord2F=(int)(long)(10*(ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
                                            e.el2.coord_flag=327666;
                                        }
                                        System.out.println(e.el1.coord2I);
                                    }catch(Exception td){

                                    }
                                }
                                sysList.get(j).checkAndMarkComponentAsResolved("Ab");
                                sysList.get(j).pairs.add(e);
                                outer2.write("CP: WDSid:"+ sysList.get(j).wdsSystemID +"    INT4 DD:"+listINT4.get(i).dd+" "+(char)10);
                                outer2.flush();
                                break layer1;
                            }
                            Pair e = new Pair();
                            e.pairIdILB ="Aa-Ab";
                            e.el1.nameInILB ="Aa";
                            e.el2.nameInILB ="Ab";
                            if(listINT4.get(i).rho!=-1){
                                try{
                                    e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
                                    e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
                                    e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
                                    e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
                                    e.el1.coord_flag=31;
                                    if(e.el1.coord2I<0){
                                        e.el1.coord2F*=-1;
                                    }
                                    if(e.el1.coord1I!=0 || e.el1.coord2I!=0){
                                        e.el2.coord1I=(int)(long) ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)));
                                        e.el2.coord1F=(int)(long)(100*(ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                                        e.el2.coord2I=(int)(long) ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                        e.el2.coord2F=(int)(long)(10*(ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
                                        e.el2.coord_flag=328666;
                                    }
                                }catch(Exception td){
                                    td.printStackTrace();
                                }
                            }
                            e.pairWDS="";
                            e.observer=listINT4.get(i).dd;
                            e.idHD=listINT4.get(i).idHD;
                            e.idHIP=listINT4.get(i).idHIP;
                            e.idADS=listINT4.get(i).idADS;
                            e.idFlamsteed =listINT4.get(i).idFlamsteed;
                            e.idDM=listINT4.get(i).idDM;
                            e.idBayer=listINT4.get(i).idBayer;
                            e.modifier[6]='i';
                            sysList.get(j).checkAndMarkComponentAsResolved("A");
                            sysList.get(j).pairs.add(e);
                            outer2.write("CP: WDSid:"+ sysList.get(j).wdsSystemID +"    INT4 DD:"+listINT4.get(i).dd+" "+(char)10);
                            outer2.flush();
                            break layer1;
                        }
                    }catch(Exception e){
                    }
                }
                    if(exists == false){
                        StarSystem s= new StarSystem();
                        //System.doNotShowBcsResolved.println(listCCDM.get(i).ccdmID+"_");
                        //s.coordinatesFromWDSasString=listCCDM.get(i).coordinatesFromWDSasString;
                        Pair e = new Pair();
                        e.pairIdILB ="AB";
                        e.el1.nameInILB ="A";
                        e.el2.nameInILB ="B";
                        if(listINT4.get(i).rho!=-1){
                            try{
                                e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
                                e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
                                e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
                                e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
                                e.el1.coord_flag=31;
                                if(e.el1.coord2I<0){
                                    e.el1.coord2F*=-1;
                                }
                                if(e.el1.coord1I!=0 || e.el1.coord2I!=0){
                                    e.el2.coord1I=(int)(long) ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)));
                                    e.el2.coord1F=(int)(long)(100*(ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                                    e.el2.coord2I=(int)(long) ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                    e.el2.coord2F=(int)(long)(10*(ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
                                    e.el2.coord_flag=329666;
                                }
                            }catch(Exception td){
                                td.printStackTrace();
                            }
                        }
                        e.pairWDS="";
                        e.observer=listINT4.get(i).dd;
                        e.idHD=listINT4.get(i).idHD;
                        e.idHIP=listINT4.get(i).idHIP;
                        e.idADS=listINT4.get(i).idADS;
                        e.idFlamsteed =listINT4.get(i).idFlamsteed;
                        e.idDM=listINT4.get(i).idDM;
                        e.idBayer=listINT4.get(i).idBayer;
                        e.modifier[6]='i';
                        s.pairs.add(e);
                        s.idWDS=listINT4.get(i).idWDS;
                        s.data=listINT4.get(i).data;
                        s.observ=s.pairs.get(0).observer;
                        sysList.add(s);
                    }
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }
    public static void interprCEV() {//"logCEV.txt"+
        int xLog=0;
        long timePrev=System.nanoTime();
        try{
            String fileName="logCEV.txt";
            Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
            int f=listCEV.size();
            int h;
            for(int i=0;i<f;i++){
                h= sysList.size();
                layer1: for(int j=0;j<h;j++){
                    try{
                        if(sysList.get(j).data!=null && sysList.get(j).data!="" && (listCEV.get(i).WDScoord.substring(0,7)).equals(sysList.get(j).data.substring(0, 7)) && (listCEV.get(i).WDScoord.substring(8,14)).equals(sysList.get(j).data.substring(9, 15))){
                            boolean content=false;
                            for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                                if(listCEV.get(i).idFlamsteed.equals(sysList.get(j).pairs.get(k).idFlamsteed) ||
                                        listCEV.get(i).idHIP.equals(sysList.get(j).pairs.get(k).idHIP) ||
                                        listCEV.get(i).idFlamsteed.equals(sysList.get(j).pairs.get(k).idBayer)){//����� ������ ���� ������� ��� ����;
                                    content=true;
                                    sysList.get(j).pairs.get(k).modifier[7]='e';
                                    if(sysList.get(j).pairs.get(k).idHIP=="") {
                                        sysList.get(j).pairs.get(k).idHIP = listCEV.get(i).idHIP;
                                    }
                                    sysList.get(j).pairs.get(k).idCEV=listCEV.get(i).idCEV;
                                    System.out.println("CEV added:"+listCEV.get(i).idCEV);
                                    break;
                                }
                            }
                            if(!content){
                                Pair e = new Pair();
                                e.pairIdILB ="Aa-Ab";
                                e.el1.nameInILB ="Aa";
                                e.el2.nameInILB ="Ab";
                                e.idCEV=listCEV.get(i).idCEV;
                                e.idFlamsteed =listCEV.get(i).idFlamsteed;
                                e.idHIP=listCEV.get(i).idHIP;
                                int jj=0;
                                int kk=0;
                                for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                                    if(sysList.get(j).pairs.get(k).el1.nameInILB =="A"){
                                        jj=j;
                                        kk=k;
                                    }
                                }
                                e.el1.coord1I= sysList.get(jj).pairs.get(kk).el1.coord1I;
                                e.el1.coord1F= sysList.get(jj).pairs.get(kk).el1.coord1F;
                                e.el1.coord2I= sysList.get(jj).pairs.get(kk).el1.coord2I;
                                e.el1.coord2F= sysList.get(jj).pairs.get(kk).el1.coord2F;
                                e.el2.coord1I= sysList.get(jj).pairs.get(kk).el2.coord1I;
                                e.el2.coord1F= sysList.get(jj).pairs.get(kk).el2.coord1F;
                                e.el2.coord2I= sysList.get(jj).pairs.get(kk).el2.coord2I;
                                e.el2.coord2F= sysList.get(jj).pairs.get(kk).el2.coord2F;
                                e.el1.coord_flag=41;
                                sysList.get(j).checkAndMarkComponentAsResolved("A");
                                sysList.get(j).pairs.add(e);
                                outer2.write("CP?: "+listCEV.get(i).WDScoord+"  " +listCEV.get(i).idFlamsteed +(char)10);
                                outer2.flush();
                                e.modifier[7]='e';
                                outer2.append("$!");
                                outer2.flush();
                                break layer1;
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                //outer2.append(listCEV.get(i).WDScoord+(char)10);
                //outer2.flush();
                if(xLog*1000<=i){
                    //System.doNotShowBcsResolved.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                    timePrev=System.nanoTime();
                    xLog=(int) (i/1000+1);
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }
    public static void interprSB9(String key1) {//"log8.txt"
        System.out.println("interpr SB9");
        try{
            String fileName="log8.txt";
            int f=listSB9.size();
            int h;
            for(int i=0;i<f;i++){
                boolean exists=false;
                h= sysList.size();
                for(int j=0;j<h;j++){
                    for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                        try{
                            if(listSB9.get(i).ADS!="" && sysList.get(j).pairs.get(k).idADS.equals(listSB9.get(i).ADS) ||
                                    listSB9.get(i).idFlamsteed!="" && sysList.get(j).pairs.get(k).idBayer.equals(listSB9.get(i).idFlamsteed) ||
                                    listSB9.get(i).HD!="" && sysList.get(j).pairs.get(k).idHD.equals(listSB9.get(i).HD)  ||
                                    listSB9.get(i).HIP!="" && sysList.get(j).pairs.get(k).idHIP.equals(listSB9.get(i).HIP) ||
                                    listSB9.get(i).DM!="" && sysList.get(j).pairs.get(k).idDM.equals(listSB9.get(i).DM)){
                                exists=true;
                                sysList.get(j).pairs.get(k).modifier[8]='s';
                                //System.doNotShowBcsResolved.println(sysList.get(j).pairs.get(k).idSB9);
                                if(sysList.get(j).pairs.get(k).idADS==""){
                                    sysList.get(j).pairs.get(k).idADS=listSB9.get(i).ADS;
                                }
                                if(sysList.get(j).pairs.get(k).idHIP==""){
                                    sysList.get(j).pairs.get(k).idHIP=listSB9.get(i).HIP;
                                }
                                if(sysList.get(j).pairs.get(k).idHD==""){
                                    sysList.get(j).pairs.get(k).idHD=listSB9.get(i).HD;
                                }
                                if(sysList.get(j).pairs.get(k).idDM==""){
                                    sysList.get(j).pairs.get(k).idDM=listSB9.get(i).DM;
                                }
                                if(sysList.get(j).pairs.get(k).idBayer==""){
                                    sysList.get(j).pairs.get(k).idBayer=listSB9.get(i).idFlamsteed;
                                }
                                sysList.get(j).pairs.get(k).idSB9=listSB9.get(i).SB9;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                apart: if(exists==false){
                    String avalValue=listSB9.get(i).data.substring(0,3);
                    if(avalValue.charAt(0)=='0'){
                        avalValue=""+avalValue.charAt(1)+avalValue.charAt(2);
                    }
                    if(!key1.equals(avalValue)){
                        break apart;
                    }else{
                        //System.doNotShowBcsResolved.println("added SB9 sysList "+avalValue);
                    }
                    StarSystem s= new StarSystem();
                    s.wdsSystemID =listSB9.get(i).generateIdentifyer();
                    s.data=listSB9.get(i).data;
                    s.idWDS=listSB9.get(i).mainID;
                    boolean uniqueSystem=true;
					/*for(int jjj=0;jjj<hhh;jjj++){
						if(s.wdsSystemID.substring(0,10).equals(sysList.get(jjj).wdsSystemID.substring(0,10))){
							uniqueSystem=false;
							sysNumber=j;
							break;
						}
					}*/
                    if(uniqueSystem || !uniqueSystem){
                        sysList.add(s);
                        Pair e = new Pair();
                        e.pairIdILB ="AB";
                        s.pairs.add(e);
                        //
                        e.modifier[8]='s';
                        e.el1.nameInILB ="A";
                        e.el2.nameInILB ="B";
                        try {
                            e.el1.coord1I = Integer.parseInt(listSB9.get(i).data.substring(0, 6));
                            e.el1.coord1F = Integer.parseInt(listSB9.get(i).data.substring(7, 9));
                            e.el1.coord2I = Integer.parseInt(listSB9.get(i).data.substring(9, 16));
                            e.el1.coord2F = Integer.parseInt(listSB9.get(i).data.substring(17, 18));
                            e.el2.coord1I = Integer.parseInt(listSB9.get(i).data.substring(0, 6));
                            e.el2.coord1F = Integer.parseInt(listSB9.get(i).data.substring(7, 9));
                            e.el2.coord2I = Integer.parseInt(listSB9.get(i).data.substring(9, 16));
                            e.el2.coord2F = Integer.parseInt(listSB9.get(i).data.substring(17, 18));
                        }catch(Exception l){
                            l.printStackTrace();
                            System.out.println("for " + listSB9.get(i).data);
                        }
                        e.el1.coord_flag = 61;
                        e.el2.coord_flag = 61;
                        e.idSB9=listSB9.get(i).SB9;
                        e.idADS=listSB9.get(i).ADS;
                        e.idHIP=listSB9.get(i).HIP;
                        e.idHD=listSB9.get(i).HD;
                        e.idDM=listSB9.get(i).DM;
                        e.idBayer=listSB9.get(i).idFlamsteed;
                    }
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }
    public static void predInterprCCDM(){
        int f=listCCDM.size();
        int h;
        for(int i=0;i<f;i++){
            if(listCCDM.get(i).pairCCDM!="ZZ"){
                try{
                    if(listCCDM.get(i).pairCCDM.charAt(0)=='A'){
                        int j=i-1;
                        while(j>0){
                            if(listCCDM.get(j).pairCCDM=="ZZ"){
                                //listCCDM.get(i).coord_I1_1=listCCDM.get(j).coord_I2_1;
                                //listCCDM.get(i).coord_I1_2=listCCDM.get(j).coord_I2_2;
                                //listCCDM.get(i).coord_F1_1=listCCDM.get(j).coord_F2_1;
                                //listCCDM.get(i).coord_F1_2=listCCDM.get(j).coord_F2_2;
                                break;
                            }
                            j--;
                        }
                    }else{
                        int j=i-1;
                        while(j>0){
                            if(listCCDM.get(j).pairCCDM.charAt(1)==listCCDM.get(i).pairCCDM.charAt(0)){
                                //listCCDM.get(i).coord_I1_1=listCCDM.get(j).coord_I2_1;
                                //listCCDM.get(i).coord_I1_2=listCCDM.get(j).coord_I2_2;
                                //listCCDM.get(i).coord_F1_1=listCCDM.get(j).coord_F2_1;
                                //listCCDM.get(i).coord_F1_2=listCCDM.get(j).coord_F2_2;
                                break;
                            }
                            j--;
                        }
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void interprCCDMcoords(){
        int f= sysList.size();
        int h=listCCDMcoords.size();
        for(int i=0;i<f;i++){
            for(int j=0;j<h;j++){
                for(int k = 0; k< sysList.get(i).pairs.size(); k++){
                    if(sysList.get(i).pairs.get(k).idCCDM.equals(listCCDMcoords.get(j).ccdmID)){
                        if(sysList.get(i).pairs.get(k).pairCCDM.equals(listCCDMcoords.get(j).pairCCDM)){
                            if(sysList.get(i).pairs.get(k).el1.coord1I==0 && sysList.get(i).pairs.get(k).el1.coord2I==0){
                                try{
                                    //System.doNotShowBcsResolved.println("CCDMcoords concatenated");
                                    sysList.get(i).pairs.get(k).el1.coord1I=listCCDMcoords.get(j).el1_1I;
                                    sysList.get(i).pairs.get(k).el1.coord1F=listCCDMcoords.get(j).el1_1F;
                                    sysList.get(i).pairs.get(k).el1.coord2I=listCCDMcoords.get(j).el1_2I;
                                    sysList.get(i).pairs.get(k).el1.coord2F=listCCDMcoords.get(j).el1_2F;
                                    sysList.get(i).pairs.get(k).el2.coord1I=listCCDMcoords.get(j).el2_1I;
                                    sysList.get(i).pairs.get(k).el2.coord1F=listCCDMcoords.get(j).el2_1F;
                                    sysList.get(i).pairs.get(k).el2.coord2I=listCCDMcoords.get(j).el2_2I;
                                    sysList.get(i).pairs.get(k).el2.coord2F=listCCDMcoords.get(j).el2_2F;
                                    sysList.get(i).pairs.get(k).el1.coord_flag=304;
                                    sysList.get(i).pairs.get(k).el2.coord_flag=304;
                                }catch(Exception td){
                                    td.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void ccdmFillZZ(Component el1, int i){
        try{
            while(i>=0){
                if(listCCDM.get(i).pairCCDM=="ZZ"){
                    el1.idDM=listCCDM.get(i).DM;
                    el1.idHIP=listCCDM.get(i).HIP;
                    el1.idHD=listCCDM.get(i).HD;
                    return;
                }
                i--;
            }
            System.out.println("Wrong borders! ERR104");
        }catch(Exception e){
            System.out.println("Wrong borders! ERR103");
        }
    }
    public static void interprCCDMr(){//"log3.txt"
        int xLog=0;
        long timePrev=System.nanoTime();
        try{
            String fileName="log3.txt";
            Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
//		System.doNotShowBcsResolved.println(listSCO.size());
//		System.doNotShowBcsResolved.println(sysList.size());
            int f=listCCDM.size();
            int h;
            for(int i=0;i<f;i++){
                h= sysList.size();
                boolean systemExistence=false;
                upper: for(int j=0;j<h;j++){
                    for(int dd = 0; dd< sysList.get(j).pairs.size(); dd++){
                        boolean zzFixed=false;
                        if(listCCDM.get(i).pairCCDM!="ZZ" && listCCDM.get(i).wdsID!=null && sysList.get(j).idWDS!=null && sysList.get(j).idWDS.length()>10 && (listCCDM.get(i).wdsID).equals(sysList.get(j).idWDS.substring(0, 10)) ||
                                sysList.get(j).pairs.get(dd).idCCDM!=null && listCCDM.get(i).ccdmID.equals(sysList.get(j).pairs.get(dd).idCCDM) ||
                                sysList.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(sysList.get(j).CCDMid)){
                            systemExistence=true;
                            boolean existence=false;
                            iter++;
                            int number = 0;
                            Pair ee = new Pair();
                            ee.pairIdILB =listCCDM.get(i).pairCCDM;
                            ee.pairCCDM=listCCDM.get(i).pairCCDM;
                            ee.idCCDM=listCCDM.get(i).ccdmID;
                            ee.observer=listCCDM.get(i).nameOfObserver;
                            ee.pairIdILB=ee.pairIdILB.replaceAll(" ","");
                            ee.dispWithoutRepeatCheckerInWriter(j);
                            for(int k = 0; k< sysList.get(j).pairs.size(); k++){
                                //	System.doNotShowBcsResolved.println("case"+j+ee.pairCCDM+"_"+sysList.get(j).pairs.get(k).pairCCDM);//general output
                                if(sysList.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM)){
                                    existence=true;
                                    number=i;
                                    if(sysList.get(j).pairs.get(k).idHD==""){
                                        if(listCCDM.get(i).HD!=""){
                                            sysList.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
                                            if((""+listCCDM.get(i).componentInfo)== sysList.get(j).pairs.get(k).el1.nameInILB){
                                                sysList.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
                                            }else{
                                                sysList.get(j).pairs.get(k).el2.idHD=listCCDM.get(i).HD;
                                            }
                                        }
                                    }
                                    if(sysList.get(j).pairs.get(k).idHIP=="" ){
                                        if(listCCDM.get(i).HIP!=""){
                                            sysList.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
                                            if((""+listCCDM.get(i).componentInfo)== sysList.get(j).pairs.get(k).el1.nameInILB){
                                                sysList.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
                                            }else{
                                                sysList.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).HIP;
                                            }
                                        }
                                    }
                                    if(sysList.get(j).pairs.get(k).idADS==""){
                                        sysList.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
                                    }
                                    if(sysList.get(j).pairs.get(k).idDM=="" ){
                                        if(listCCDM.get(i).DM!=""){
                                            sysList.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
                                            if((""+listCCDM.get(i).componentInfo)== sysList.get(j).pairs.get(k).el1.nameInILB){
                                                sysList.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).DM;
                                            }else{
                                                sysList.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).DM;
                                            }
                                        }
                                    }
                                    sysList.get(j).pairs.get(k).idCCDM=listCCDM.get(i).ccdmID;
                                    if(listCCDM.get(i).astrometric){
                                        sysList.get(j).pairs.get(k).modifier[4]='a';
                                        //System.doNotShowBcsResolved.println("ASTROMETRIC!");
                                    }else{
                                        sysList.get(j).pairs.get(k).modifier[4]='v';
                                    }
                                    break upper;
                                }
                            }
                            if(!existence && listCCDM.get(i).pairCCDM!="ZZ"){
                                outer2.write("err: ccdmID:"+listCCDM.get(i).ccdmID+" pairNameXXXXXfromWDS:"+listCCDM.get(i).pairCCDM+" "+(char)10);
                                outer2.flush();
                                Pair eea = new Pair();
                                eea.pairIdILB =listCCDM.get(i).pairCCDM;
                                eea.idCCDM=listCCDM.get(i).ccdmID;
                                eea.pairCCDM=listCCDM.get(i).pairCCDM;
                                eea.observer=listCCDM.get(i).nameOfObserver;
                                eea.el1=ee.el1;
                                eea.el2=ee.el2;
                                eea.idHD=listCCDM.get(i).HD;
                                eea.idHIP=listCCDM.get(i).HIP;
                                eea.idADS=listCCDM.get(i).ADS;
                                eea.idDM=listCCDM.get(i).DM;
                                eea.el2.idDM=listCCDM.get(i).DM;
                                eea.el2.idHIP=listCCDM.get(i).HIP;
                                eea.el2.idHD=listCCDM.get(i).HD;
                                if(eea.el1.nameInILB.equals("A")){
                                    if(zzFixed==false){
                                        ccdmFillZZ(eea.el1, i);
                                        zzFixed=true;
                                    }
                                }
                                if(listCCDM.get(i).astrometric){
                                    outer2.write("Close pairNameXXXXXfromWDS: ccdmID:"+listCCDM.get(i).ccdmID+" pairNameXXXXXfromWDS:"+listCCDM.get(i).pairCCDM+" "+(char)10);
                                    outer2.flush();
                                    boolean closeExists=false;
                                    for(int ij = 0; ij< sysList.get(j).pairs.size(); ij++){
                                        if(sysList.get(j).pairs.get(ij).el1.nameInILB.equals("Aa")){
                                            closeExists=true;
                                            break;
                                        }
                                    }
                                    if(closeExists){
                                        eea.pairIdILB ="Aaa-Aab";
                                        eea.el1.nameInILB ="Aaa";
                                        eea.el2.nameInILB ="Aab";
                                    }else{
                                        eea.pairIdILB ="Aa-Ab";
                                        eea.el1.nameInILB ="Aa";
                                        eea.el2.nameInILB ="Ab";
                                    }
                                    eea.modifier[4]='a';
                                    //System.doNotShowBcsResolved.println("astrometric!");
                                }else{
                                    eea.modifier[4]='v';
                                }
                                eea.pairIdILB=eea.pairIdILB.replaceAll(" ","");
                                eea.dispWithoutRepeatCheckerInWriter(-1);
                                sysList.get(j).pairs.add(eea);
                            }
                            break upper;
                        }
                    }
                }
                if(systemExistence==false){
                    StarSystem s= new StarSystem();
                    sysList.add(s);
                    s.wdsSystemID =listCCDM.get(i).ccdmID+listCCDM.get(i).nameOfObserver;
                    //System.doNotShowBcsResolved.println(listCCDM.get(i).ccdmID+"_");
                    //s.coordinatesFromWDSasString=listCCDM.get(i).coordinatesFromWDSasString;
                    s.observ=listCCDM.get(i).nameOfObserver;
                    s.coordinatesNotFoundInWDS =true;
                    s.CCDMid=listCCDM.get(i).ccdmID;
                    s.data=s.CCDMid.substring(0,5)+"0.00"+s.CCDMid.substring(5,10)+"00.0";
                    Pair ee = new Pair();
                    ee.pairIdILB =listCCDM.get(i).pairCCDM;
                    //ee.el1.coord1I=listCCDM.get(i).coord_I1_1;
                    //ee.el1.coord1F=listCCDM.get(i).coord_F1_1;
                    //ee.el1.coord2I=listCCDM.get(i).coord_I1_2;
                    //ee.el1.coord2F=listCCDM.get(i).coord_F1_2;
//				ee.el2.coord1I=listCCDM.get(i).coord_I2_1;
//				ee.el2.coord1F=listCCDM.get(i).coord_F2_1;
//				ee.el2.coord2I=listCCDM.get(i).coord_I2_2;
//				ee.el2.coord2F=listCCDM.get(i).coord_F2_2;
                    ee.pairIdILB=ee.pairIdILB.replaceAll(" ","");
                    ee.dispWithoutRepeatCheckerInWriter(-1);
                    Pair e = new Pair();
                    e.pairIdILB =ee.pairIdILB;
                    e.el1=ee.el1;
                    e.el2=ee.el2;
                    e.pairCCDM=listCCDM.get(i).pairCCDM;
                    e.idCCDM=listCCDM.get(i).ccdmID;
                    e.idHD=listCCDM.get(i).HD;
                    e.idHIP=listCCDM.get(i).HIP;
                    e.idADS=listCCDM.get(i).ADS;
                    e.idDM=listCCDM.get(i).DM;
                    e.el2.idDM=listCCDM.get(i).DM;
                    e.el2.idHIP=listCCDM.get(i).HIP;
                    e.el2.idHD=listCCDM.get(i).HD;
                    if(e.el1.nameInILB.equals("A")){
                        ccdmFillZZ(e.el1, i);
                    }
                    if(listCCDM.get(i).astrometric){
                        e.modifier[4]='a';
                        e.pairIdILB ="AB";
                        e.el1.nameInILB ="A";
                        e.el2.nameInILB ="B";
                        outer2.write("ErrorSysEx1Astrmtr: "+s.wdsSystemID +""+(char)10);
                    }else{
                        //e.modifier="v";
                        outer2.write("ErrorSysEx3: "+s.wdsSystemID +""+(char)10);
                    }
                    s.pairs.add(e);
                    outer2.flush();
                }
                if(xLog*1000<=i){
                    //System.doNotShowBcsResolved.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                    timePrev=System.nanoTime();
                    xLog=(int) (i/1000+1);
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }

    //WDS helpers
    private static void firstClassificationOfpair(int i) {
        //create coordinatesNotFoundInWDS sysList
        StarSystem s= new StarSystem();
        s.wdsSystemID = listWDS.get(i).wdsSystemID;
        s.data= listWDS.get(i).coordinatesFromWDSasString;
        s.observ= listWDS.get(i).nameOfObserver;
        s.idWDS= listWDS.get(i).wdsSystemID;
        s.coordinatesNotFoundInWDS = listWDS.get(i).coordinatesNotFoundInWDS;

        //check if such sysList not exists
        boolean uniqueSystem=true;
        int sysNumber=0;
        int h= sysList.size();
        for(int j=0;j<h;j++){
            if(s.wdsSystemID.substring(0,10).equals(sysList.get(j).wdsSystemID.substring(0,10))){
                uniqueSystem=false;
                sysNumber=j;
                break;
            }
        }
        if(uniqueSystem){
            sysList.add(s);
            s.pairs.add(assign(i));
        }else{
            assign(i);
            sysList.get(sysNumber).pairs.add(assign(i));
        }
    }
    private static Pair assign(int i) {
        Pair e = new Pair();
        e.observer= listWDS.get(i).nameOfObserver;
        e.idDM= listWDS.get(i).idDM;
        e.rho= listWDS.get(i).rho;
        e.theta= listWDS.get(i).theta;
        e.modifier[0]= listWDS.get(i).modifier2[0];
        if(listWDS.get(i).modifier2[1]!=0){
            e.modifier[0]= listWDS.get(i).modifier2[1];
        }
        e.pairWDS= listWDS.get(i).pairNameXXXXXfromWDS.replaceAll(" ","");
        e.nameComponentsByPairNameForWDS();
        parseCoordinatesDirectlyFromWDS(e,i);
        return e;
    }
    public static void parseCoordinatesDirectlyFromWDS(Pair e, int i){
        //global error tracker. Should be never throwed;
        try{
            //
            //

            //here can't be exception XXXXXX.YY-YYYYYY.Y
            e.el1.coord1I=Integer.parseInt(listWDS.get(i).coordinatesFromWDSasString.substring(0,6));
            e.el1.coord_flag=11;

            // YYYYYY.XX-YYYYYY.Y sometimes missing
            try{
                e.el1.coord1F=Integer.parseInt(listWDS.get(i).coordinatesFromWDSasString.substring(7,9));
            }catch(NumberFormatException exc1){
                System.err.println("ERR01 caught while parsing "+ listWDS.get(i).coordinatesFromWDSasString);
                try {
                    e.el1.coord1F=Integer.parseInt(listWDS.get(i).coordinatesFromWDSasString.substring(7,8));
                    e.el1.coord_flag=-11;
                }catch(NumberFormatException exc2){
                    System.err.println("ERR01b caught while parsing "+ listWDS.get(i).coordinatesFromWDSasString);
                    e.el1.coord1F = 0;
                    e.el1.coord_flag=-11;
                }
            }

            // YYYYYY.YY-XXXXXX.Y should be always clear
            try{
                e.el1.coord2I=Integer.parseInt(listWDS.get(i).coordinatesFromWDSasString.substring(9,16));
            }catch(NumberFormatException exc) {
                    System.err.println("ERR02 caught while parsing "+ listWDS.get(i).coordinatesFromWDSasString);
                    e.el1.coord2I = 0;
                    e.el1.coord_flag = -11;
            }

            // YYYYYY.YY-YYYYYY.X sometimes missing
            try{
                e.el1.coord2F=Integer.parseInt(listWDS.get(i).coordinatesFromWDSasString.substring(17,18));
            }catch(Exception exc){
                System.err.println("ERR03 caught while parsing "+ listWDS.get(i).coordinatesFromWDSasString);
                e.el1.coord2F=0;
                e.el1.coord_flag=-11;
            }

            if(listWDS.get(i).rho!=0){
                e.el2.coord_flag=12;
                try{
                    e.el2.coord1I=(int)(long) ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+ listWDS.get(i).rho*Math.sin(listWDS.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)));
                }catch(NumberFormatException exc){
                    System.err.println("ERR04 caught while processing with e.el2.coord1I "+ listWDS.get(i).coordinatesFromWDSasString);
                    e.el2.coord1I=0;
                    e.el2.coord_flag=-12;
                }
                try{
                    e.el2.coord1F=(int)(long)(100*(ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+ listWDS.get(i).rho*Math.sin(listWDS.get(i).theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                }catch(NumberFormatException exc){
                    System.err.println("ERR05 caught while processing with e.el2.coord1F "+ listWDS.get(i).coordinatesFromWDSasString);
                    e.el2.coord1F=0;
                    e.el2.coord_flag=-12;
                }
                try{
                    e.el2.coord2I=(int)(long) ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+ listWDS.get(i).rho*Math.cos(listWDS.get(i).theta/180*Math.PI));
                }catch(NumberFormatException exc){
                    System.err.println("ERR06 caught while processing with e.el2.coord2I "+ listWDS.get(i).coordinatesFromWDSasString);
                    e.el2.coord2I=0;
                    e.el2.coord_flag=-12;
                }
                try{
                    e.el2.coord2F=(int)(long)(10*(ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+ listWDS.get(i).rho*Math.cos(listWDS.get(i).theta/180*Math.PI))-e.el2.coord2I));
                }catch(NumberFormatException exc){
                    System.err.println("ERR07 caught while processing with e.el2.coord2F "+ listWDS.get(i).coordinatesFromWDSasString);
                    e.el2.coord2F=0;
                    e.el2.coord_flag=-12;
                }
                if(e.el2.coord2I<0){
                    e.el2.coord2F*=-1;
                }
            }

            //
            //Case for fatal error catched. Transaction should be rolled back(?) (should we implement ORM?)
            //
        }catch(Exception td){
            System.err.println("ERR08 FATAL error caught"+ listWDS.get(i).coordinatesFromWDSasString);
            e.el1.coord_flag=-1;
            td.printStackTrace();
        }
    }
}
