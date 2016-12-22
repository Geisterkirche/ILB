package ILBprocessing;

import ILBprocessing.configuration.SharedConstants;
import lib.tools.StatisticsCollector;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

/**
 * Created by Алекс on 21.03.2016.
 */
public class CustomWriter extends WDSparser implements SharedConstants{

    public CustomWriter(){
    }
    public static void write(String xxx){
        System.out.println("writing current cache in temp files");
        try{
            String fileName=FILE_NAME_DEFAULT+xxx+FILE_RESULT_FORMAT;
            Writer outer = new FileWriter(new File(OUTPUT_FOLDER+fileName));
            for(int k = 0; k< sysList.size(); k++){
                boolean ss=false;
                for(int j = 0; j< sysList.get(k).pairs.size(); j++,ss=true){
                    int nn=0;
                    if(ss){
                        nn=1;
                    }
                    int length=0;
                    int usedLength=0;
                    for(int ll = 0; ll< sysList.get(k).pairs.get(j).modifier.length; ll++){
                        if(sysList.get(k).pairs.get(j).modifier[ll]!=0){
                            length+=1;
                        }
                    }
                    for(int stage=nn;stage<4;stage++){
                        String x ="";
                        x=x+('J');
                        if(!sysList.get(k).data.contains("          ")){
                            x=x+(sysList.get(k).data);
                        }else{
                            String a1=""+Math.abs(sysList.get(k).pairs.get(0).el1.coord1I);
                            String a2=""+Math.abs(sysList.get(k).pairs.get(0).el1.coord1F);
                            String b1=""+Math.abs(sysList.get(k).pairs.get(0).el1.coord2I);
                            String b2=""+Math.abs(sysList.get(k).pairs.get(0).el1.coord2F);
                            while(a1.length()<6){
                                a1="0"+a1;
                            }
                            while(a2.length()<2){
                                a2="0"+a2;
                            }
                            while(b1.length()<6){
                                b1="0"+b1;
                            }
                            while(b2.length()<1){
                                b2="0"+b2;
                            }
                            x=x+a1+"."+a2;
                            boolean c2=true;
                            if(sysList.get(k).pairs.get(0).el1.coord2I<0){
                                c2=false;
                            }
                            if(c2==true){
                                x=x+"+";
                            }else{
                                x=x+"-";
                            }
                            x=x+b1+"."+b2;
                        }
                        String n;
                        switch(stage){
                            case 0:
                                n=(":s");
                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(sysList.get(k).coordinatesNotFoundInWDS){
                                    //x=x+"f";
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
                                x=x+(("          "));
                                break;
                            case 1:
                                n=(":p");
                                    n=n+(sysList.get(k).pairs.get(j).pairIdILB);
                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(sysList.get(k).coordinatesNotFoundInWDS){
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
							/*if(sysList.get(k).pairs.get(j).modifier.contains("O")){
								System.doNotShowBcsResolved.println("Exception x:"+sysList.get(k).wdsSystemID+" "+sysList.get(k).pairs.get(j).pairIdILB);
							}*/
                                //sysList.get(k).pairs.get(j).modifier=clearify(sysList.get(k).pairs.get(j).modifier);
                                as: for(int hk=0;hk<10;hk++){
                                    if(sysList.get(k).pairs.get(j).modifier[hk]!=0 && sysList.get(k).pairs.get(j).modifier[hk]!=' '){
                                        x=x+ sysList.get(k).pairs.get(j).modifier[hk];
                                        sysList.get(k).pairs.get(j).modifier[hk]=0;
                                        hk++;
                                        for(;hk<10;hk++){
                                            x=x+' ';
                                        }
                                        usedLength++;
                                        break as;
                                    }else{
                                        x=x+' ';
                                    }
                                }
                                break;
                            case 2:
                                n=(":c");
                                n=n+(sysList.get(k).pairs.get(j).el1.nameInILB);

                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(sysList.get(k).coordinatesNotFoundInWDS){
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
                                x=x+(("          "));
                                break;
                            case 3:
                                n=(":c");
                                    n=n+(sysList.get(k).pairs.get(j).el2.nameInILB);

                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(sysList.get(k).coordinatesNotFoundInWDS){
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
                                x=x+(("          "));
                                break;
                        }

                            if(sysList.get(k).idWDS!=""){
                                x=x+((""+ sysList.get(k).idWDS));
                            }else{
                                x=x+"        ";
                            }
                            if(sysList.get(k).pairs.get(j).observer!=null && sysList.get(k).pairs.get(j).observer!=""){
                                int counter = sysList.get(k).pairs.get(j).observer.length();
                                while(counter<8){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((" "+ sysList.get(k).pairs.get(j).observer));
                            }else{
                                x=x+"        ";
                            }
                        //
                        //
                        if(stage==1  || stage==0){
                            while(x.length()<65){
                                x=x+" ";
                            }
                            x=x+"|";
                            if(sysList.get(k).pairs.get(j).pairWDS!=""){
                                int counter = sysList.get(k).pairs.get(j).pairWDS.length();
                                while(counter<8){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((sysList.get(k).pairs.get(j).pairWDS)+"|");
                            }else{
                                int counter = sysList.get(k).pairs.get(j).el1.nameInILB.length()+ sysList.get(k).pairs.get(j).el2.nameInILB.length();
                                while(counter<8){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el1.nameInILB + sysList.get(k).pairs.get(j).el2.nameInILB +"|";
                            }

                            if(sysList.get(k).pairs.get(j).idHIP!=""){
                                StatisticsCollector.validateHIP(sysList.get(k).pairs.get(j).idHIP);
                                int counter = sysList.get(k).pairs.get(j).idHIP.length();
                                while(counter<6){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idHIP+"|";
                            }else{
                                x=x+"      |";
                            }

                            if(sysList.get(k).pairs.get(j).idHD!=""){
                                StatisticsCollector.validateHD(sysList.get(k).pairs.get(j).idHD);
                                int counter = sysList.get(k).pairs.get(j).idHD.length();
                                while(counter<6){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idHD+"|";
                            }else{
                                x=x+"      |";
                            }

                            if(sysList.get(k).pairs.get(j).idSB9!=""){
                                int counter = sysList.get(k).pairs.get(j).idSB9.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idSB9+"|";
                            }else{
                                x=x+"     |";
                            }
                            if(sysList.get(k).pairs.get(j).idCEV!=null && sysList.get(k).pairs.get(j).idCEV!=""){
                                int counter = sysList.get(k).pairs.get(j).idCEV.length();
                                while(counter<10){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idCEV+"|";
                            }else{
                                x=x+"          |";
                            }
                            if(sysList.get(k).pairs.get(j).idADS!=""){
                                int counter= sysList.get(k).pairs.get(j).idADS.length();
                                while(counter<6){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idADS+"|";
                            }else{
                                x=x+"      |";
                            }

                            if(sysList.get(k).pairs.get(j).idFlamsteed !=""){
                                int counter= sysList.get(k).pairs.get(j).idFlamsteed.length();
                                while(counter<10){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idFlamsteed +"|";
                            }else{
                                x=x+"          |";
                            }
                            if(sysList.get(k).pairs.get(j).idBayer!=""){
                                int counter = sysList.get(k).pairs.get(j).idBayer.length();
                                while(counter<10){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idBayer+"|";
                            }else{
                                x=x+"          |";
                            }
                            if(sysList.get(k).pairs.get(j).idDM!=""){
                                StatisticsCollector.validateDM(sysList.get(k).pairs.get(j).idDM);
                                int counter = sysList.get(k).pairs.get(j).idDM.length();
                                while(counter<12){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).idDM+"|";
                            }else{
                                x=x+"            |";
                            }
                            if(sysList.get(k).pairs.get(j).systemTDSC !=""){
                                int counter= sysList.get(k).pairs.get(j).systemTDSC.length();
                                while(counter<12){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((sysList.get(k).pairs.get(j).systemTDSC +"|"));
                            }else{
                                x=x+"            |";
                            }
                            if(sysList.get(k).pairs.get(j).pairTDSC!=""){
                                int counter = sysList.get(k).pairs.get(j).pairTDSC.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((sysList.get(k).pairs.get(j).pairTDSC+"|"));
                            }else{
                                x=x+"     |";
                            }
                            if(sysList.get(k).pairs.get(j).systemCCDM !=""){
                                int counter = sysList.get(k).pairs.get(j).systemCCDM.length();
                                while(counter<12){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((sysList.get(k).pairs.get(j).systemCCDM +"|"));
                            }else{
                                x=x+"            |";
                            }
                            if(sysList.get(k).pairs.get(j).pairCCDM!=""){
                                int counter = sysList.get(k).pairs.get(j).pairCCDM.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((sysList.get(k).pairs.get(j).pairCCDM+"|"));
                            }else{
                                x=x+"     |";
                            }
                        }
                        if(stage==2){
                            while(x.length()<67){
                                x=x+" ";
                            }
                            if(0==0) {
                                int counter = sysList.get(k).pairs.get(j).el1.nameInILB.length();
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                                for (int ite = 0; ite < sysList.get(k).pairs.get(j).el1.nameInILB.length(); ite++) {
                                    x = x + (sysList.get(k).pairs.get(j).el1.nameInILB.charAt(ite));
                                }
                            }else{
                                int counter = 0;
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                            }
                            x=x+"|";
                            if(sysList.get(k).pairs.get(j).el1.idHIP!=""){
                                StatisticsCollector.validateHIP(sysList.get(k).pairs.get(j).el1.idHIP);
                                int counter2 = sysList.get(k).pairs.get(j).el1.idHIP.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el1.idHIP+"|";
                            }else{
                                x=x+"      |";
                            }
                            if(sysList.get(k).pairs.get(j).el1.idHD!=""){
                                StatisticsCollector.validateHD(sysList.get(k).pairs.get(j).el1.idHD);
                                int counter2 = sysList.get(k).pairs.get(j).el1.idHD.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el1.idHD+"|";
                            }else{
                                x=x+"      |";
                            }
                            x=x+"     |          |      |          |          |";
                            if(sysList.get(k).pairs.get(j).el1.idDM!=""){
                                StatisticsCollector.validateDM(sysList.get(k).pairs.get(j).el1.idDM);
                                int counter3 = sysList.get(k).pairs.get(j).el1.idDM.length();
                                while(counter3<12){
                                    x=x+" ";
                                    counter3++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el1.idDM+"|";
                            }else{
                                x=x+"            |";
                            }
                            //asd

                        }
                        if(stage==3){
                            while(x.length()<67){
                                x=x+" ";
                            }
                            if(0==0) {
                                int counter = sysList.get(k).pairs.get(j).el2.nameInILB.length();
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                                for (int ite = 0; ite < sysList.get(k).pairs.get(j).el2.nameInILB.length(); ite++) {
                                    x = x + (sysList.get(k).pairs.get(j).el2.nameInILB.charAt(ite));
                                }
                            }else{
                                int counter = 0;
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                            }
                            x=x+"|";
                            if(sysList.get(k).pairs.get(j).el2.idHIP!=""){
                                StatisticsCollector.validateHIP(sysList.get(k).pairs.get(j).el2.idHIP);
                                int counter2 = sysList.get(k).pairs.get(j).el2.idHIP.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el2.idHIP+"|";
                            }else{
                                x=x+"      |";
                            }
                            if(sysList.get(k).pairs.get(j).el2.idHD!=""){
                                StatisticsCollector.validateHD(sysList.get(k).pairs.get(j).el2.idHD);
                                int counter2 = sysList.get(k).pairs.get(j).el2.idHD.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el2.idHD+"|";
                            }else{
                                x=x+"      |";
                            }
                            x=x+"     |          |      |          |          |";
                            if(sysList.get(k).pairs.get(j).el2.idDM!=""){
                                StatisticsCollector.validateDM(sysList.get(k).pairs.get(j).el2.idDM);
                                int counter3 = sysList.get(k).pairs.get(j).el2.idDM.length();
                                while(counter3<12){
                                    x=x+" ";
                                    counter3++;
                                }
                                x=x+ sysList.get(k).pairs.get(j).el2.idDM+"|";
                            }else{
                                x=x+"            |";
                            }
                        }
                        while(x.length()<186){
                            x=x+" ";
                        }
                        if(stage!=0  || stage==0){
                            if(sysList.get(k).pairs.get(j).idBayer!=""){
                                int counter = sysList.get(k).pairs.get(j).idBayer.length();//���� ����� ��������
                                x=x+((" "+ sysList.get(k).pairs.get(j).idBayer));
                            }else if(sysList.get(k).pairs.get(j).idFlamsteed !=""){
                                int counter = sysList.get(k).pairs.get(j).idFlamsteed.length();//���� ����� ��������
                                x=x+((" "+ sysList.get(k).pairs.get(j).idFlamsteed));
                            }else if(sysList.get(k).pairs.get(j).idSB9!=""){
                                int counter = sysList.get(k).pairs.get(j).idSB9.length();
                                x=x+(("SB9 "+ sysList.get(k).pairs.get(j).idSB9));
                            }else if(sysList.get(k).pairs.get(j).idCEV!=null && sysList.get(k).pairs.get(j).idCEV!=""){
                                int counter = sysList.get(k).pairs.get(j).idCEV.length();
                                x=x+(("CEV  "+ sysList.get(k).pairs.get(j).idCEV));
                            }else if(sysList.get(k).pairs.get(j).idADS!=""){
                                int counter = sysList.get(k).pairs.get(j).idADS.length();
                                x=x+(("ADS "+ sysList.get(k).pairs.get(j).idADS));
                            }else if(sysList.get(k).pairs.get(j).idHIP!=""){
                                int counter = sysList.get(k).pairs.get(j).idHIP.length();
                                x=x+(("HIP "+ sysList.get(k).pairs.get(j).idHIP));
                            }else if(sysList.get(k).pairs.get(j).idHD!="" && sysList.get(k).pairs.get(j).idHD!=null && (sysList.get(k).pairs.get(j).idHD.contains("+") || sysList.get(k).pairs.get(j).idHD.contains("-"))){
                                int counter = sysList.get(k).pairs.get(j).idHD.length();
                                x=x+(("HD "+ sysList.get(k).pairs.get(j).idHD));
                            }else if(sysList.get(k).pairs.get(j).idDM!="" && sysList.get(k).pairs.get(j).idDM!=null  && (sysList.get(k).pairs.get(j).idDM.contains("+") || sysList.get(k).pairs.get(j).idDM.contains("-"))){
                                int counter = sysList.get(k).pairs.get(j).idDM.length();
                                x=x+(("DM "+ sysList.get(k).pairs.get(j).idDM));
                            }else if(sysList.get(k).idWDS!=""){
                                int counter = sysList.get(k).idWDS.length();
                                x=x+(("WDS "+ sysList.get(k).idWDS));
                            }else if(sysList.get(k).pairs.get(j).systemCCDM !=""){
                                int counter = sysList.get(k).pairs.get(j).systemCCDM.length();
                                x=x+(("CCDM "+ sysList.get(k).pairs.get(j).systemCCDM));
                            }
                        }
                        x=coordPrint(stage, x,k,j);
                        /*if(stage==2){
                            while(x.length()<205){
                                x=x+" ";
                            }
                            boolean c2=true;
                            if(sysList.get(k).pairs.get(j).el1.coord2I<0){
                                c2=false;
                            }
                            String a1=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord1I);
                            String a2=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord1F);
                            String b1=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord2I);
                            String b2=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord2F);
                            while(a1.length()<6){
                                a1="0"+a1;
                            }
                            while(a2.length()<2){
                                a2="0"+a2;
                            }
                            while(b1.length()<6){
                                b1="0"+b1;
                            }
                            while(b2.length()<1){
                                b2="0"+b2;
                            }
                            if(a1!="000000" && b1!="000000"){
                                x=x+a1+"."+a2;
                                if(c2==true){
                                    x=x+"+";
                                }else{
                                    x=x+"-";
                                }
                                x=x+b1+"."+b2;
                                x=x+"  "+sysList.get(k).pairs.get(j).el1.coord_flag;
                            }else{
                                x=x+"*";
                            }
                        }
                        if(stage==3){
                            while(x.length()<205){
                                x=x+" ";
                            }
                            boolean c2=true;
                            if(sysList.get(k).pairs.get(j).el2.coord2I<0){
                                c2=false;
                            }
                            String a1=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord1I);
                            String a2=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord1F);
                            String b1=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord2I);
                            String b2=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord2F);
                            while(a1.length()<6){
                                a1="0"+a1;
                            }
                            while(a2.length()<2){
                                a2="0"+a2;
                            }
                            while(b1.length()<6){
                                b1="0"+b1;
                            }
                            while(b2.length()<1){
                                b2="0"+b2;
                            }
                            if(a1!="000000" && b1!="000000"){
                                x=x+a1+"."+a2;
                                if(c2==true){
                                    x=x+"+";
                                }else{
                                    x=x+"-";
                                }
                                x=x+b1+"."+b2;
                                x=x+"  "+sysList.get(k).pairs.get(j).el2.coord_flag;
                            }else{
                                x=x+"*";
                            }
                        }*/
                            //System.doNotShowBcsResolved.println(x);
                            if(stage==0){
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                            }
                            if(stage==1){
                                if(stage==1 && usedLength<length){
                                    stage--;
                                }
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                            }
                            if(stage==2){
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                            }
                            if(stage==3){
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                            }

                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static String coordPrint(int stage,String x, int k, int j){
        if(stage==2){
            while(x.length()<205){
                x=x+" ";
            }
            boolean c2=true;
            if(sysList.get(k).pairs.get(j).el1.coord2I<0){
                c2=false;
            }
            String a1=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord1I);
            String a2=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord1F);
            String b1=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord2I);
            String b2=""+Math.abs(sysList.get(k).pairs.get(j).el1.coord2F);
            while(a1.length()<6){
                a1="0"+a1;
            }
            while(a2.length()<2){
                a2="0"+a2;
            }
            while(b1.length()<6){
                b1="0"+b1;
            }
            while(b2.length()<1){
                b2="0"+b2;
            }
            if(a1!="000000" && b1!="000000"){
                x=x+a1+"."+a2;
                if(c2==true){
                    x=x+"+";
                }else{
                    x=x+"-";
                }
                x=x+b1+"."+b2;
                x=x+"  "+ sysList.get(k).pairs.get(j).el1.coord_flag;
            }else{
                x=x+"*";
            }
        }
        if(stage==3){
            while(x.length()<205){
                x=x+" ";
            }
            boolean c2=true;
            if(sysList.get(k).pairs.get(j).el2.coord2I<0){
                c2=false;
            }
            String a1=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord1I);
            String a2=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord1F);
            String b1=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord2I);
            String b2=""+Math.abs(sysList.get(k).pairs.get(j).el2.coord2F);
            while(a1.length()<6){
                a1="0"+a1;
            }
            while(a2.length()<2){
                a2="0"+a2;
            }
            while(b1.length()<6){
                b1="0"+b1;
            }
            while(b2.length()<1){
                b2="0"+b2;
            }
            if(a1!="000000" && b1!="000000"){
                x=x+a1+"."+a2;
                if(c2==true){
                    x=x+"+";
                }else{
                    x=x+"-";
                }
                x=x+b1+"."+b2;
                x=x+"  "+ sysList.get(k).pairs.get(j).el2.coord_flag;
            }else{
                x=x+"*";
            }
        }
        return x;
    }
}
