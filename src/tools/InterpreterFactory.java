package tools;

import application.WDSparser;
import structureEntities.Component;
import structureEntities.Pair;
import structureEntities.StarSystem;

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
        int d=globalList.size();
        for(int i=0;i<d;i++){
            StarSystem s= new StarSystem();
            s.identifyer=globalList.get(i).identifyer;
            s.data=globalList.get(i).data;
            s.observ=globalList.get(i).nameOfObserver;
            s.idWDS=globalList.get(i).mainID;
            s.fake=globalList.get(i).fake;
            boolean uniqueSystem=true;
            int sysNumber=0;
            int h=system.size();
            for(int j=0;j<h;j++){
                if(s.identifyer.substring(0,10).equals(system.get(j).identifyer.substring(0,10))){
                    uniqueSystem=false;
                    sysNumber=j;
                    break;
                }
            }
            if(uniqueSystem){
                //System.out.println(system.size());
                system.add(s);
                Pair e = new Pair();
                e.id=globalList.get(i).pair;
                e.observer=globalList.get(i).nameOfObserver;
                e.pairWDS=globalList.get(i).pair;
                e.idDM=globalList.get(i).idDM;
                e.modyfyer[0]=globalList.get(i).modyfyer2[0];
                e.rho=globalList.get(i).rho;
                e.theta=globalList.get(i).theta;
                if(globalList.get(i).modyfyer2[1]!=0){
                    e.modyfyer[0]=globalList.get(i).modyfyer2[1];
                }
                e.clean();
                e.disp();
                try{
                    e.el1.coord_flag=11;
                    e.el1.coord1I=Integer.parseInt(globalList.get(i).data.substring(0,6));
                    try{
                        e.el1.coord1F=Integer.parseInt(globalList.get(i).data.substring(7,9));
                    }catch(NumberFormatException exc){
                        e.el1.coord1F=0;
                        e.el1.coord_flag=0;
                    }
                    try{
                        e.el1.coord2I=Integer.parseInt(globalList.get(i).data.substring(9,16));
                    }catch(NumberFormatException exc){
                        e.el1.coord2I=0;
                        e.el1.coord_flag=0;
                    }
                    try{
                        e.el1.coord2F=Integer.parseInt(globalList.get(i).data.substring(17,18));
                    }catch(NumberFormatException exc){
                        e.el1.coord2F=0;
                        e.el1.coord_flag=0;
                    }
                    if(e.el1.coord2I<0){
                        e.el1.coord2F*=-1;
                    }
                    if(globalList.get(i).rho!=0){
                        e.el2.coord_flag=12;
                        try{
                            e.el2.coord1I=(int)(long) Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
                        }catch(NumberFormatException exc){
                            e.el2.coord1I=0;
                            e.el2.coord_flag=0;
                        }
                        try{
                            e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                        }catch(NumberFormatException exc){
                            e.el2.coord1F=0;
                            e.el2.coord_flag=0;
                        }
                        try{
                            e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI));
                        }catch(NumberFormatException exc){
                            e.el2.coord2I=0;
                            e.el2.coord_flag=0;
                        }
                        try{
                            e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI))-e.el2.coord2I));
                        }catch(NumberFormatException exc) {
                            e.el2.coord2F = 0;
                            e.el2.coord_flag = 0;
                        }
                    }
                }catch(Exception td){
                    td.printStackTrace();
                }
                system.get(system.size()-1).pairs.add(e);
            }else{
                Pair e = new Pair();
                e.id=globalList.get(i).pair;
                e.observer=globalList.get(i).nameOfObserver;
                e.pairWDS=globalList.get(i).pair;
                e.idDM=globalList.get(i).idDM;
                e.rho=globalList.get(i).rho;
                e.theta=globalList.get(i).theta;
                e.modyfyer[0]=globalList.get(i).modyfyer2[0];
                if(globalList.get(i).modyfyer2[1]!=0){
                    e.modyfyer[0]=globalList.get(i).modyfyer2[1];
                }
                try{
                    e.el1.coord_flag=11;
                    e.el1.coord1I=Integer.parseInt(globalList.get(i).data.substring(0,6));
                    try{
                        e.el1.coord1F=Integer.parseInt(globalList.get(i).data.substring(7,9));
                    }catch(NumberFormatException exc){
                        e.el1.coord1F=0;
                        e.el1.coord_flag=0;
                    }
                    try{
                        e.el1.coord2I=Integer.parseInt(globalList.get(i).data.substring(9,16));
                    }catch(NumberFormatException exc){
                        e.el1.coord2I=0;
                        e.el1.coord_flag=0;
                    }
                    try{
                        e.el1.coord2F=Integer.parseInt(globalList.get(i).data.substring(17,18));
                    }catch(NumberFormatException exc){
                        e.el1.coord2F=0;
                        e.el1.coord_flag=0;
                    }
                    if(e.el1.coord2I<0){
                        e.el1.coord2F*=-1;
                    }
                    if(globalList.get(i).rho!=0){
                        e.el2.coord_flag=12;
                        try{
                            e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
                        }catch(NumberFormatException exc){
                            e.el2.coord1I=0;
                            e.el2.coord_flag=0;
                        }
                        try{
                            e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                        }catch(NumberFormatException exc){
                            e.el2.coord1F=0;
                            e.el2.coord_flag=0;
                        }
                        try{
                            e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI));
                        }catch(NumberFormatException exc){
                            e.el2.coord2I=0;
                            e.el2.coord_flag=0;
                        }
                        try{
                            e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI))-e.el2.coord2I));
                        }catch(NumberFormatException exc){
                            e.el2.coord2F=0;
                            e.el2.coord_flag=0;
                        }
                    }
                }catch(Exception td){
                    td.printStackTrace();
                }
                e.clean();
                e.disp();
                system.get(sysNumber).pairs.add(e);
            }
            if(xLog*1000<=i){
                System.out.println(i+"/"+d+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                timePrev=System.nanoTime();
                xLog=(int) (i/1000+1);
            }
        }
    }
    public static void interprSCO(){//"log.txt"
        try{
            String fileName="log.txt";
            Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
//		System.out.println(listSCO.size());
//		System.out.println(system.size());
            int f=listSCO.size();
            int h;
            for(int i=0;i<f;i++){
                boolean systemExistence=false;
                for(int j=0;j<system.size();j++){
				/*System.out.println("a"+system.get(j).mainID);
				System.out.println("b"+system.get(j).identifyer);
				System.out.println("c"+system.get(j).observ);
				System.out.println("d"+system.get(j).mainID.substring(0, 10));-*/
                    if((listSCO.get(i).idWDS).equals(system.get(j).idWDS.substring(0, 10))){
                        boolean existence=false;
                        boolean observerFail=false;
                        iter++;
                        int number = 0;
                        Pair ee = new Pair();
                        ee.id=listSCO.get(i).pairWDS;
                        ee.clean();
                        ee.dispWithoutRepeatCheckerInWriter(j);
                        for(int k=0;k<system.get(j).pairs.size();k++){
                            if(system.get(j).pairs.get(k).id.equals(ee.id)){
                                observerFail=true;
                                if(listSCO.get(i).nameOfObserver!=null && listSCO.get(i).nameOfObserver!="" && system.get(j).pairs.get(k).observer!=null && system.get(j).pairs.get(k).observer.equals(listSCO.get(i).nameOfObserver)){
                                    existence=true;
                                    number=i;
                                    system.get(j).pairs.get(k).idHD=listSCO.get(i).idHD;
                                    system.get(j).pairs.get(k).idHIP=listSCO.get(i).idHIP;
                                    system.get(j).pairs.get(k).idADS=listSCO.get(i).idADS;
                                    system.get(j).pairs.get(k).idHyi=listSCO.get(i).idFlamsteed;
                                    system.get(j).pairs.get(k).idBayer=listSCO.get(i).idBayer;
                                    if(system.get(j).pairs.get(k).el1.coord1I==0 && system.get(j).pairs.get(k).el1.coord2I==0){
                                        try{
                                            system.get(j).pairs.get(k).el1.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
                                            system.get(j).pairs.get(k).el1.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
                                            system.get(j).pairs.get(k).el1.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
                                            system.get(j).pairs.get(k).el1.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
                                            system.get(j).pairs.get(k).el2.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
                                            system.get(j).pairs.get(k).el2.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
                                            system.get(j).pairs.get(k).el2.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
                                            system.get(j).pairs.get(k).el2.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
                                            system.get(j).pairs.get(k).el1.coord_flag=21;
                                            system.get(j).pairs.get(k).el2.coord_flag=21;
                                        }catch(Exception td){
                                        }
                                    }
                                    if(system.get(j).pairs.get(k).modyfyer[1]=='O'){
                                        system.get(j).pairs.get(k).modyfyer[2]='o';
                                        system.get(j).pairs.get(k).modyfyer[1]='o';
                                    }else{
                                        system.get(j).pairs.get(k).modyfyer[2]='o';
                                        //System.out.println("Exception y:"+system.get(j).identifyer+system.get(j).pairs.get(k).id);

                                    }
                                    break;
                                }
                            }
                        }
                        if(!existence){
                            if(!observerFail){
                                Pair e = new Pair();
                                e.id=ee.id;
                                e.el1=ee.el1;
                                e.el2=ee.el2;
                                e.pairWDS=listSCO.get(i).pairWDS;
                                e.idHD=listSCO.get(i).idHD;
                                e.idHIP=listSCO.get(i).idHIP;
                                e.idADS=listSCO.get(i).idADS;
                                e.idBayer=listSCO.get(i).idBayer;
                                e.idHyi=listSCO.get(i).idFlamsteed;
                                e.modyfyer[2]='o';
                                try{
                                    e.el1.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
                                    e.el1.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
                                    e.el1.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
                                    e.el1.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
                                    e.el2.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
                                    e.el2.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
                                    e.el2.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
                                    e.el2.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
                                    e.el1.coord_flag=21;
                                    e.el2.coord_flag=21;
                                }catch(Exception td){
                                }
                                e.clean();
                                system.get(j).pairs.add(e);
                                outer2.write("ExceptionPairExistence1: "+listSCO.get(i).idWDS+" "+listSCO.get(i).nameOfObserver+" "+listSCO.get(i).pairWDS+" "+(char)10);
                                outer2.flush();
                            }else{//������ �������������� ����������� - �������� ���������� � �� ���
                                Pair e = new Pair();
                                e.id="Aa-Ab";
                                e.el1.name="Aa";
                                e.el2.name="Ab";
                                e.pairWDS=listSCO.get(i).pairWDS;
                                e.observer=listSCO.get(i).nameOfObserver;
                                e.idHD=listSCO.get(i).idHD;
                                e.idHIP=listSCO.get(i).idHIP;
                                e.idADS=listSCO.get(i).idADS;
                                e.idBayer=listSCO.get(i).idBayer;
                                e.idHyi=listSCO.get(i).idFlamsteed;
                                e.modyfyer[2]='o';
                                try{
                                    e.el1.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
                                    e.el1.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
                                    e.el1.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
                                    e.el1.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
                                    e.el2.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
                                    e.el2.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
                                    e.el2.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
                                    e.el2.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
                                    e.el1.coord_flag=21;
                                    e.el2.coord_flag=21;
                                }catch(Exception td){
                                }
                                system.get(j).findCompOut("A");
                                system.get(j).pairs.add(e);
                                outer2.write("Close pair: "+listSCO.get(i).idWDS+" "+listSCO.get(i).nameOfObserver+" "+listSCO.get(i).pairWDS+" "+(char)10);
                                outer2.flush();
                            }
                        }
                        systemExistence=true;
                        break;
                    }
                }
                if(systemExistence==false){/*
				StarSystem s= new StarSystem();
				system.add(s);
				s.idWDS=listSCO.get(i).idWDS;
				s.identifyer=listSCO.get(i).idWDS+listSCO.get(i).nameOfObserver;
				s.data=listSCO.get(i).data;
				s.observ=listSCO.get(i).nameOfObserver;
				s.fake=false;
				outer2.write("ExceptionSystemExistence: "+s.identifyer+""+(char)10);
				structureEntities.Pair ee = new structureEntities.Pair();
				ee.id=listSCO.get(i).pairWDS;
				ee.clean();
				ee.dispWithoutRepeatCheckerInWriter(-1);
				structureEntities.Pair e = new structureEntities.Pair();
				e.id=ee.id;
				e.el1=ee.el1;
				e.el2=ee.el2;
				e.pairWDS=listSCO.get(i).pairWDS;
				e.idHD=listSCO.get(i).idHD;
				e.idHIP=listSCO.get(i).idHIP;
				e.idADS=listSCO.get(i).idADS;
				e.idBayer=listSCO.get(i).idBayer;
				e.idHyi=listSCO.get(i).idHyi;
				e.modyfyer[2]='o';
				//e.clean();
				s.pairs.add(e);
				//outer2.write(listSCO.get(i).pairWDS+""+(char)10);
				outer2.flush();*/
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
            int f=system.size();
            int h=listTDSC.size();
            for(int j=0;j<f;j++){
                int zzz=system.get(j).pairs.size();
                for(int k=0;k<zzz;k++){
                    if(system.get(j).pairs.get(k).idTDSC==null || system.get(j).pairs.get(k).idTDSC==""){

                    }else{
                        boolean systemExistence=false;
                        layer_1: for(int i=0;i<h;i++){
						/*System.out.println("a"+system.get(j).mainID);
						System.out.println("b"+system.get(j).identifyer);
						System.out.println("c"+system.get(j).observ);
						System.out.println("d"+system.get(j).mainID.substring(0, 10));-*/
                            try{
                                int u=Integer.parseInt(listTDSC.get(i).TDSCid);
                                int gfg=0;
                                if(system.get(j).pairs.get(k).idTDSC.charAt(0)!='"'){
                                    gfg=Integer.parseInt(system.get(j).pairs.get(k).idTDSC);
                                }
                                if(gfg==0){
                                    break;
                                }
                                if(u==gfg){
                                    iter++;
                                    system.get(j).pairs.get(k).modyfyer[3]='v';
                                    if(system.get(j).pairs.get(k).idHD==""){
                                        system.get(j).pairs.get(k).idHD=listTDSC.get(i).HD;
                                    }else{
                                        //if(!(system.get(j).pairs.get(k).idHD.contains(listTDSC.get(i).HD) || listTDSC.get(i).HD.contains(system.get(j).pairs.get(k).idHD))){
                                        //outer2.write("CompareError HD:"+system.get(j).pairs.get(k).idHD+"_"+listTDSC.get(i).HD+(char)10);
                                        //}
                                    }
                                    if(system.get(j).pairs.get(k).idHIP==""){
                                        system.get(j).pairs.get(k).idHIP=listTDSC.get(i).HIPid;
                                    }else{
                                        //if(!(system.get(j).pairs.get(k).idHIP.contains(listTDSC.get(i).HIPid) || listTDSC.get(i).HIPid.contains(system.get(j).pairs.get(k).idHIP))){
                                        //outer2.write("CompareError HIP:"+system.get(j).pairs.get(k).idHIP+"_"+listTDSC.get(i).HIPid+(char)10);
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
                h=system.size();
                upper:{ for(int j=0;j<h;j++){
                    if(system.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(system.get(j).CCDMid)){
                        for(int dd=0;dd<system.get(j).pairs.size();dd++){
                            //������� �������.
                            if(system.get(j).pairs.get(dd).pairCCDM.equals(listCCDM.get(i).pairCCDM)){
                                //iter++;
                                int k=dd;
                                if(system.get(j).pairs.get(k).idHD==""){
                                    if(listCCDM.get(i).HD!=""){
                                        system.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
                                        if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
                                            system.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
                                        }else{
                                            system.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
                                            system.get(j).pairs.get(k).el2.idHD=listCCDM.get(i).HD;
                                        }
                                    }
                                }
                                if(system.get(j).pairs.get(k).idHIP=="" ){
                                    if(listCCDM.get(i).HIP!=""){
                                        system.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
                                        if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
                                            system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
                                        }else{
                                            system.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).HIP;
                                            system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
                                        }
                                    }
                                }
                                if(system.get(j).pairs.get(k).idADS==""){
                                    system.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
                                }
                                if(system.get(j).pairs.get(k).idDM=="" ){
                                    if(listCCDM.get(i).DM!=""){
                                        system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
                                        if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
                                            system.get(j).pairs.get(k).el1.idDM=listCCDM.get(i).DM;
                                        }else{
                                            system.get(j).pairs.get(k).el2.idDM=listCCDM.get(i).DM;
                                            system.get(j).pairs.get(k).el1.idDM=listCCDM.get(i).DM;
                                        }
                                    }
                                }
                                if(listCCDM.get(i).astrometric){
                                    system.get(j).pairs.get(k).modyfyer[4]='a';
                                }else{
                                    system.get(j).pairs.get(k).modyfyer[4]='v';
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
                h=system.size();
                exists = false;
                layer1:{ for(int j=0;j<h;j++){
                    try{
                        if(listINT4.get(i).idWDS!= null && listINT4.get(i).idWDS!="" && !(system.get(j).idWDS.substring(0, 2).equals("  ")) && (listINT4.get(i).idWDS).equals(system.get(j).idWDS.substring(0, 10))){
                            exists = true;
                            for(int k=0;k<system.get(j).pairs.size();k++){
                                if(system.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd) || system.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd1)){
                                    if(system.get(j).pairs.get(k).idADS!=null && listINT4.get(i).name1.contains(system.get(j).pairs.get(k).idADS) && system.get(j).pairs.get(k).idADS!="" || system.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name1.contains(system.get(j).pairs.get(k).idDM) && system.get(j).pairs.get(k).idDM!=""){
                                        system.get(j).pairs.get(k).modyfyer[6]='i';
                                        break layer1;
                                    }else if(system.get(j).pairs.get(k).idBayer!=null && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).idBayer) && system.get(j).pairs.get(k).idBayer!="" || system.get(j).pairs.get(k).idHyi!=null && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).idHyi) && system.get(j).pairs.get(k).idHyi!=""){
                                        system.get(j).pairs.get(k).modyfyer[6]='i';
                                        if(system.get(j).pairs.get(k).idHD==""){
                                            system.get(j).pairs.get(k).idHD=listINT4.get(i).idHD;
                                        }
                                        if(system.get(j).pairs.get(k).idHIP==""){
                                            system.get(j).pairs.get(k).idHIP=listINT4.get(i).idHIP;
                                        }
                                        if(system.get(j).pairs.get(k).idHyi==""){
                                            system.get(j).pairs.get(k).idHyi=listINT4.get(i).idHyi;
                                        }
                                        if(system.get(j).pairs.get(k).idBayer==""){
                                            system.get(j).pairs.get(k).idHyi=listINT4.get(i).idBayer;
                                        }
                                        if(system.get(j).pairs.get(k).idDM==""){
                                            system.get(j).pairs.get(k).idDM=listINT4.get(i).idDM;
                                        }
                                        if(system.get(j).pairs.get(k).el1.coord1I==0 && system.get(j).pairs.get(k).el1.coord2I==0 && listINT4.get(i).rho!=-1){
                                            try{
                                                system.get(j).pairs.get(k).el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
                                                system.get(j).pairs.get(k).el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
                                                system.get(j).pairs.get(k).el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
                                                system.get(j).pairs.get(k).el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
                                                system.get(j).pairs.get(k).el1.coord_flag=31;
                                                if(system.get(j).pairs.get(k).el1.coord2I<0){
                                                    system.get(j).pairs.get(k).el1.coord2F*=-1;
                                                }
                                                if(system.get(j).pairs.get(k).el1.coord1I!=0 || system.get(j).pairs.get(k).el1.coord2I!=0){
                                                    system.get(j).pairs.get(k).el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(system.get(j).pairs.get(k).el1.coord1I,system.get(j).pairs.get(k).el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)));
                                                    system.get(j).pairs.get(k).el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(system.get(j).pairs.get(k).el1.coord1I,system.get(j).pairs.get(k).el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)))-system.get(j).pairs.get(k).el2.coord1I));
                                                    system.get(j).pairs.get(k).el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                                    system.get(j).pairs.get(k).el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-system.get(j).pairs.get(k).el2.coord2I));
                                                    system.get(j).pairs.get(k).el2.coord_flag=32;
                                                }
                                            }catch(Exception td){
                                                td.printStackTrace();
                                            }
                                        }
                                        break layer1;
                                    }else if(system.get(j).pairs.get(k).idHD!=null && listINT4.get(i).name3.contains(system.get(j).pairs.get(k).idHD) && system.get(j).pairs.get(k).idHD!="" || system.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name3.contains(system.get(j).pairs.get(k).idDM) && system.get(j).pairs.get(k).idDM!=""){
                                        system.get(j).pairs.get(k).modyfyer[6]='i';
                                        break layer1;
                                    }else if(system.get(j).pairs.get(k).idHIP!=null && system.get(j).pairs.get(k).idHIP!="" && listINT4.get(i).name4.contains(system.get(j).pairs.get(k).idHIP) || system.get(j).pairs.get(k).observer!=null && system.get(j).pairs.get(k).observer!="" && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).observer)){//discoverer 2!!!
                                        system.get(j).pairs.get(k).modyfyer[6]='i';
                                        break layer1;
                                    }else if(listINT4.get(i).rho!=0 && system.get(j).pairs.get(k).rho!=0 && listINT4.get(i).rho/system.get(j).pairs.get(k).rho>1/3 && listINT4.get(i).rho/system.get(j).pairs.get(k).rho<3){
                                        system.get(j).pairs.get(k).modyfyer[6]='i';
                                        break layer1;
                                    }
                                    System.out.println(listINT4.get(i).rho+" "+system.get(j).pairs.get(k).rho);
                                }
                            }
                            //���� ������, � ���� �� ���������, ������ ��� ���� ���� ���������
                            if(listINT4.get(i).dd.contains("Ab comp")){
                                Pair e = new Pair();
                                e.id="Aba-Abb";
                                e.el1.name="Aba";
                                e.el2.name="Abb";
//								e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
//								e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
//								e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
//								e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
                                e.pairWDS="";
                                e.observer=listINT4.get(i).dd1;
                                e.idHD=listINT4.get(i).idHD;
                                e.idHIP=listINT4.get(i).idHIP;
                                e.idADS=listINT4.get(i).idADS;
                                e.idHyi=listINT4.get(i).idHyi;
                                e.idDM=listINT4.get(i).idDM;
                                e.idBayer=listINT4.get(i).idBayer;
                                e.modyfyer[6]='i';
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
                                            e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
                                            e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                                            e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                            e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
                                            e.el2.coord_flag=32;
                                        }
                                        System.out.println(e.el1.coord2I);
                                    }catch(Exception td){
                                    }
                                }
                                system.get(j).findCompOut("Ab");
                                system.get(j).pairs.add(e);
                                outer2.write("CP: WDSid:"+system.get(j).identifyer +"    INT4 DD:"+listINT4.get(i).dd+" "+(char)10);
                                outer2.flush();
                                break layer1;
                            }
                            Pair e = new Pair();
                            e.id="Aa-Ab";
                            e.el1.name="Aa";
                            e.el2.name="Ab";
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
                                        e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
                                        e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                                        e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                        e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
                                        e.el2.coord_flag=32;
                                    }
                                }catch(Exception td){
                                }
                            }
                            e.pairWDS="";
                            e.observer=listINT4.get(i).dd;
                            e.idHD=listINT4.get(i).idHD;
                            e.idHIP=listINT4.get(i).idHIP;
                            e.idADS=listINT4.get(i).idADS;
                            e.idHyi=listINT4.get(i).idHyi;
                            e.idDM=listINT4.get(i).idDM;
                            e.idBayer=listINT4.get(i).idBayer;
                            e.modyfyer[6]='i';
                            system.get(j).findCompOut("A");
                            system.get(j).pairs.add(e);
                            outer2.write("CP: WDSid:"+system.get(j).identifyer +"    INT4 DD:"+listINT4.get(i).dd+" "+(char)10);
                            outer2.flush();
                            break layer1;
                        }
                    }catch(Exception e){
                    }
                }
                    if(exists == false){
                        StarSystem s= new StarSystem();
                        //System.out.println(listCCDM.get(i).ccdmID+"_");
                        //s.data=listCCDM.get(i).data;
                        Pair e = new Pair();
                        e.id="A-B";
                        e.el1.name="A";
                        e.el2.name="B";
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
                                    e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
                                    e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                                    e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
                                    e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
                                    e.el2.coord_flag=32;
                                }
                            }catch(Exception td){
                            }
                        }
                        e.pairWDS="";
                        e.observer=listINT4.get(i).dd;
                        e.idHD=listINT4.get(i).idHD;
                        e.idHIP=listINT4.get(i).idHIP;
                        e.idADS=listINT4.get(i).idADS;
                        e.idHyi=listINT4.get(i).idHyi;
                        e.idDM=listINT4.get(i).idDM;
                        e.idBayer=listINT4.get(i).idBayer;
                        e.modyfyer[6]='i';
                        s.pairs.add(e);
                        s.idWDS=listINT4.get(i).idWDS;
                        s.data=listINT4.get(i).data;
                        s.observ=s.pairs.get(0).observer;
                        system.add(s);
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
                h=system.size();
                layer1: for(int j=0;j<h;j++){
                    try{
                        if(system.get(j).data!=null && system.get(j).data!="" && (listCEV.get(i).WDScoord.substring(0,7)).equals(system.get(j).data.substring(0, 7)) && (listCEV.get(i).WDScoord.substring(8,14)).equals(system.get(j).data.substring(9, 15))){
                            boolean content=false;
                            for(int k=0;k<system.get(j).pairs.size();k++){
                                if(listCEV.get(i).Hyi.equals(system.get(j).pairs.get(k).idHyi) ||
                                        listCEV.get(i).idHIP.equals(system.get(j).pairs.get(k).idHIP) ||
                                        listCEV.get(i).Hyi.equals(system.get(j).pairs.get(k).idBayer)){//����� ������ ���� ������� ��� ����;
                                    content=true;
                                    system.get(j).pairs.get(k).modyfyer[7]='e';
                                    if(system.get(j).pairs.get(k).idHIP=="") {
                                        system.get(j).pairs.get(k).idHIP = listCEV.get(i).idHIP;
                                    }
                                    system.get(j).pairs.get(k).idCEV=listCEV.get(i).idCEV;
                                    System.out.println("CEV added:"+listCEV.get(i).idCEV);
                                    break;
                                }
                            }
                            if(!content){
                                Pair e = new Pair();
                                e.id="Aa-Ab";
                                e.el1.name="Aa";
                                e.el2.name="Ab";
                                e.idCEV=listCEV.get(i).idCEV;
                                e.idHyi=listCEV.get(i).Hyi;
                                e.idHIP=listCEV.get(i).idHIP;
                                int jj=0;
                                int kk=0;
                                for(int k=0;k<system.get(j).pairs.size();k++){
                                    if(system.get(j).pairs.get(k).el1.name=="A"){
                                        jj=j;
                                        kk=k;
                                    }
                                }
                                e.el1.coord1I=system.get(jj).pairs.get(kk).el1.coord1I;
                                e.el1.coord1F=system.get(jj).pairs.get(kk).el1.coord1F;
                                e.el1.coord2I=system.get(jj).pairs.get(kk).el1.coord2I;
                                e.el1.coord2F=system.get(jj).pairs.get(kk).el1.coord2F;
                                e.el2.coord1I=system.get(jj).pairs.get(kk).el2.coord1I;
                                e.el2.coord1F=system.get(jj).pairs.get(kk).el2.coord1F;
                                e.el2.coord2I=system.get(jj).pairs.get(kk).el2.coord2I;
                                e.el2.coord2F=system.get(jj).pairs.get(kk).el2.coord2F;
                                e.el1.coord_flag=41;
                                system.get(j).findCompOut("A");
                                system.get(j).pairs.add(e);
                                outer2.write("CP?: "+listCEV.get(i).WDScoord+"  " +listCEV.get(i).Hyi+(char)10);
                                outer2.flush();
                                e.modyfyer[7]='e';
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
                    //System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
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
                h=system.size();
                for(int j=0;j<h;j++){
                    for(int k=0;k<system.get(j).pairs.size();k++){
                        try{
                            if(listSB9.get(i).ADS!="" && system.get(j).pairs.get(k).idADS.equals(listSB9.get(i).ADS) ||
                                    listSB9.get(i).idFlamsteed!="" && system.get(j).pairs.get(k).idBayer.equals(listSB9.get(i).idFlamsteed) ||
                                    listSB9.get(i).HD!="" && system.get(j).pairs.get(k).idHD.equals(listSB9.get(i).HD)  ||
                                    listSB9.get(i).HIP!="" && system.get(j).pairs.get(k).idHIP.equals(listSB9.get(i).HIP) ||
                                    listSB9.get(i).DM!="" && system.get(j).pairs.get(k).idDM.equals(listSB9.get(i).DM)){
                                exists=true;
                                system.get(j).pairs.get(k).modyfyer[8]='s';
                                //System.out.println(system.get(j).pairs.get(k).idSB9);
                                if(system.get(j).pairs.get(k).idADS==""){
                                    system.get(j).pairs.get(k).idADS=listSB9.get(i).ADS;
                                }
                                if(system.get(j).pairs.get(k).idHIP==""){
                                    system.get(j).pairs.get(k).idHIP=listSB9.get(i).HIP;
                                }
                                if(system.get(j).pairs.get(k).idHD==""){
                                    system.get(j).pairs.get(k).idHD=listSB9.get(i).HD;
                                }
                                if(system.get(j).pairs.get(k).idDM==""){
                                    system.get(j).pairs.get(k).idDM=listSB9.get(i).DM;
                                }
                                if(system.get(j).pairs.get(k).idBayer==""){
                                    system.get(j).pairs.get(k).idBayer=listSB9.get(i).idFlamsteed;
                                }
                                system.get(j).pairs.get(k).idSB9=listSB9.get(i).SB9;
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
                        //System.out.println("added SB9 system "+avalValue);
                    }
                    StarSystem s= new StarSystem();
                    s.identifyer=listSB9.get(i).generateIdentifyer();
                    s.data=listSB9.get(i).data;
                    s.idWDS=listSB9.get(i).mainID;
                    boolean uniqueSystem=true;
					/*for(int jjj=0;jjj<hhh;jjj++){
						if(s.identifyer.substring(0,10).equals(system.get(jjj).identifyer.substring(0,10))){
							uniqueSystem=false;
							sysNumber=j;
							break;
						}
					}*/
                    if(uniqueSystem || !uniqueSystem){
                        system.add(s);
                        Pair e = new Pair();
                        e.id="AB";
                        s.pairs.add(e);
                        //
                        e.modyfyer[8]='s';
                        e.el1.name="A";
                        e.el2.name="B";
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
        int f=system.size();
        int h=listCCDMcoords.size();
        for(int i=0;i<f;i++){
            for(int j=0;j<h;j++){
                for(int k=0;k<system.get(i).pairs.size();k++){
                    if(system.get(i).pairs.get(k).idCCDM.equals(listCCDMcoords.get(j).ccdmID)){
                        if(system.get(i).pairs.get(k).pairCCDM.equals(listCCDMcoords.get(j).pairCCDM)){
                            if(system.get(i).pairs.get(k).el1.coord1I==0 && system.get(i).pairs.get(k).el1.coord2I==0){
                                try{
                                    //System.out.println("CCDMcoords concatenated");
                                    system.get(i).pairs.get(k).el1.coord1I=listCCDMcoords.get(j).el1_1I;
                                    system.get(i).pairs.get(k).el1.coord1F=listCCDMcoords.get(j).el1_1F;
                                    system.get(i).pairs.get(k).el1.coord2I=listCCDMcoords.get(j).el1_2I;
                                    system.get(i).pairs.get(k).el1.coord2F=listCCDMcoords.get(j).el1_2F;
                                    system.get(i).pairs.get(k).el2.coord1I=listCCDMcoords.get(j).el2_1I;
                                    system.get(i).pairs.get(k).el2.coord1F=listCCDMcoords.get(j).el2_1F;
                                    system.get(i).pairs.get(k).el2.coord2I=listCCDMcoords.get(j).el2_2I;
                                    system.get(i).pairs.get(k).el2.coord2F=listCCDMcoords.get(j).el2_2F;
                                    system.get(i).pairs.get(k).el1.coord_flag=listCCDMcoords.get(j).flag1;
                                    system.get(i).pairs.get(k).el2.coord_flag=listCCDMcoords.get(j).flag2;
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
            System.out.println("Wrong cashes borders!");
        }catch(Exception e){
            System.out.println("Wrong cashes borders!");
        }
    }
    public static void interprCCDMr(){//"log3.txt"
        int xLog=0;
        long timePrev=System.nanoTime();
        try{
            String fileName="log3.txt";
            Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
//		System.out.println(listSCO.size());
//		System.out.println(system.size());
            int f=listCCDM.size();
            int h;
            for(int i=0;i<f;i++){
                h=system.size();
                boolean systemExistence=false;
                upper: for(int j=0;j<h;j++){
                    for(int dd=0;dd<system.get(j).pairs.size();dd++){
                        boolean zzFixed=false;
                        if(listCCDM.get(i).pairCCDM!="ZZ" && listCCDM.get(i).wdsID!=null && system.get(j).idWDS!=null && system.get(j).idWDS.length()>10 && (listCCDM.get(i).wdsID).equals(system.get(j).idWDS.substring(0, 10)) ||
                                system.get(j).pairs.get(dd).idCCDM!=null && listCCDM.get(i).ccdmID.equals(system.get(j).pairs.get(dd).idCCDM) ||
                                system.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(system.get(j).CCDMid)){
                            systemExistence=true;
                            boolean existence=false;
                            iter++;
                            int number = 0;
                            Pair ee = new Pair();
                            ee.id=listCCDM.get(i).pairCCDM;
                            ee.pairCCDM=listCCDM.get(i).pairCCDM;
                            ee.idCCDM=listCCDM.get(i).ccdmID;
                            ee.observer=listCCDM.get(i).nameOfObserver;
                            ee.clean();
                            ee.dispWithoutRepeatCheckerInWriter(j);
                            for(int k=0;k<system.get(j).pairs.size();k++){
                                //	System.out.println("case"+j+ee.pairCCDM+"_"+system.get(j).pairs.get(k).pairCCDM);//general output
                                if(system.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM)){
                                    existence=true;
                                    number=i;
                                    if(system.get(j).pairs.get(k).idHD==""){
                                        if(listCCDM.get(i).HD!=""){
                                            system.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
                                            if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
                                                system.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
                                            }else{
                                                system.get(j).pairs.get(k).el2.idHD=listCCDM.get(i).HD;
                                            }
                                        }
                                    }
                                    if(system.get(j).pairs.get(k).idHIP=="" ){
                                        if(listCCDM.get(i).HIP!=""){
                                            system.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
                                            if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
                                                system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
                                            }else{
                                                system.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).HIP;
                                            }
                                        }
                                    }
                                    if(system.get(j).pairs.get(k).idADS==""){
                                        system.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
                                    }
                                    if(system.get(j).pairs.get(k).idDM=="" ){
                                        if(listCCDM.get(i).DM!=""){
                                            system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
                                            if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
                                                system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).DM;
                                            }else{
                                                system.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).DM;
                                            }
                                        }
                                    }
                                    system.get(j).pairs.get(k).idCCDM=listCCDM.get(i).ccdmID;
                                    if(listCCDM.get(i).astrometric){
                                        system.get(j).pairs.get(k).modyfyer[4]='a';
                                        //System.out.println("ASTROMETRIC!");
                                    }else{
                                        system.get(j).pairs.get(k).modyfyer[4]='v';
                                    }
                                    break upper;
                                }
                            }
                            if(!existence && listCCDM.get(i).pairCCDM!="ZZ"){
                                outer2.write("err: ccdmID:"+listCCDM.get(i).ccdmID+" pair:"+listCCDM.get(i).pairCCDM+" "+(char)10);
                                outer2.flush();
                                Pair eea = new Pair();
                                eea.id=listCCDM.get(i).pairCCDM;
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
                                if(eea.el1.name.equals("A")){
                                    if(zzFixed==false){
                                        ccdmFillZZ(eea.el1, i);
                                        zzFixed=true;
                                    }
                                }
                                if(listCCDM.get(i).astrometric){
                                    outer2.write("Close pair: ccdmID:"+listCCDM.get(i).ccdmID+" pair:"+listCCDM.get(i).pairCCDM+" "+(char)10);
                                    outer2.flush();
                                    boolean closeExists=false;
                                    for(int ij=0;ij<system.get(j).pairs.size();ij++){
                                        if(system.get(j).pairs.get(ij).el1.name.equals("Aa")){
                                            closeExists=true;
                                            break;
                                        }
                                    }
                                    if(closeExists){
                                        eea.id="Aaa-Aab";
                                        eea.el1.name="Aaa";
                                        eea.el2.name="Aab";
                                    }else{
                                        eea.id="Aa-Ab";
                                        eea.el1.name="Aa";
                                        eea.el2.name="Ab";
                                    }
                                    eea.modyfyer[4]='a';
                                    //System.out.println("astrometric!");
                                }else{
                                    eea.modyfyer[4]='v';
                                }
                                eea.clean();
                                eea.dispWithoutRepeatCheckerInWriter(-1);
                                system.get(j).pairs.add(eea);
                            }
                            break upper;
                        }
                    }
                }
                if(systemExistence==false){
                    StarSystem s= new StarSystem();
                    system.add(s);
                    s.identifyer=listCCDM.get(i).ccdmID+listCCDM.get(i).nameOfObserver;
                    //System.out.println(listCCDM.get(i).ccdmID+"_");
                    //s.data=listCCDM.get(i).data;
                    s.observ=listCCDM.get(i).nameOfObserver;
                    s.fake=true;
                    s.CCDMid=listCCDM.get(i).ccdmID;
                    s.data=s.CCDMid.substring(0,5)+"0.00"+s.CCDMid.substring(5,10)+"00.0";
                    Pair ee = new Pair();
                    ee.id=listCCDM.get(i).pairCCDM;
                    //ee.el1.coord1I=listCCDM.get(i).coord_I1_1;
                    //ee.el1.coord1F=listCCDM.get(i).coord_F1_1;
                    //ee.el1.coord2I=listCCDM.get(i).coord_I1_2;
                    //ee.el1.coord2F=listCCDM.get(i).coord_F1_2;
//				ee.el2.coord1I=listCCDM.get(i).coord_I2_1;
//				ee.el2.coord1F=listCCDM.get(i).coord_F2_1;
//				ee.el2.coord2I=listCCDM.get(i).coord_I2_2;
//				ee.el2.coord2F=listCCDM.get(i).coord_F2_2;
                    ee.clean();
                    ee.dispWithoutRepeatCheckerInWriter(-1);
                    Pair e = new Pair();
                    e.id=ee.id;
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
                    if(e.el1.name.equals("A")){
                        ccdmFillZZ(e.el1, i);
                    }
                    if(listCCDM.get(i).astrometric){
                        e.modyfyer[4]='a';
                        e.id="A-B";
                        e.el1.name="A";
                        e.el2.name="B";
                        outer2.write("ErrorSysEx1Astrmtr: "+s.identifyer+""+(char)10);
                    }else{
                        //e.modyfyer="v";
                        outer2.write("ErrorSysEx3: "+s.identifyer+""+(char)10);
                    }
                    s.pairs.add(e);
                    outer2.flush();
                }
                if(xLog*1000<=i){
                    //System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                    timePrev=System.nanoTime();
                    xLog=(int) (i/1000+1);
                }
            }
        }catch(Exception g){
            g.printStackTrace();
        }
    }

}
