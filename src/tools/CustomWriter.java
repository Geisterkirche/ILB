package tools;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import application.WDSparser;
import tools.service.SharedConstants;

/**
 * Created by Алекс on 21.03.2016.
 */
public class CustomWriter extends WDSparser implements SharedConstants{
    public CustomWriter(){

    }
    public static void write(String xxx){
        System.out.println("writing");
        try{
            String fileName=FILE_NAME_DEFAULT+xxx+FILE_RESULT_FORMAT;
            Writer outer = new FileWriter(new File(OUTPUT_FOLDER+fileName));
            for(int k=0;k<system.size();k++){
                boolean ss=false;
                for(int j=0;j<system.get(k).pairs.size();j++,ss=true){
                    int nn=0;
                    if(ss){
                        nn=1;
                    }
                    int length=0;
                    int usedLength=0;
                    for(int ll=0;ll<system.get(k).pairs.get(j).modyfyer.length;ll++){
                        if(system.get(k).pairs.get(j).modyfyer[ll]!=0){
                            length+=1;
                        }
                    }
                    for(int stage=nn;stage<4;stage++){
                        String x ="";
                        x=x+('J');
                        if(!system.get(k).data.contains("          ")){
                            x=x+(system.get(k).data);
                        }else{
                            String a1=""+Math.abs(system.get(k).pairs.get(0).el1.coord1I);
                            String a2=""+Math.abs(system.get(k).pairs.get(0).el1.coord1F);
                            String b1=""+Math.abs(system.get(k).pairs.get(0).el1.coord2I);
                            String b2=""+Math.abs(system.get(k).pairs.get(0).el1.coord2F);
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
                            if(system.get(k).pairs.get(0).el1.coord2I<0){
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
                                if(system.get(k).fake){
                                    //x=x+"f";
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
                                x=x+(("          "));
                                break;
                            case 1:
                                n=(":p");
                                for(int ite=0;ite<system.get(k).pairs.get(j).el1.newName.length();ite++){
                                    n=n+(system.get(k).pairs.get(j).el1.newName.charAt(ite));
                                }
                                n=n+("-");
                                for(int ite=0;ite<system.get(k).pairs.get(j).el2.newName.length();ite++){
                                    n=n+(system.get(k).pairs.get(j).el2.newName.charAt(ite));
                                }
                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(system.get(k).fake){
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
							/*if(system.get(k).pairs.get(j).modyfyer.contains("O")){
								System.out.println("Exception x:"+system.get(k).identifyer+" "+system.get(k).pairs.get(j).id);
							}*/
                                //system.get(k).pairs.get(j).modyfyer=clearify(system.get(k).pairs.get(j).modyfyer);
                                as: for(int hk=0;hk<10;hk++){
                                    if(system.get(k).pairs.get(j).modyfyer[hk]!=0 && system.get(k).pairs.get(j).modyfyer[hk]!=' '){
                                        x=x+system.get(k).pairs.get(j).modyfyer[hk];
                                        system.get(k).pairs.get(j).modyfyer[hk]=0;
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
                                for(int ite=0;ite<system.get(k).pairs.get(j).el1.newName.length();ite++){
                                    n=n+(system.get(k).pairs.get(j).el1.newName.charAt(ite));
                                }
                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(system.get(k).fake){
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
                                x=x+(("          "));
                                break;
                            case 3:
                                n=(":c");
                                for(int ite=0;ite<system.get(k).pairs.get(j).el2.newName.length();ite++){
                                    n=n+(system.get(k).pairs.get(j).el2.newName.charAt(ite));
                                }
                                while(n.length()<10){
                                    n=n+" ";
                                }
                                x=x+n;
                                if(system.get(k).fake){
                                    x=x+" ";
                                }else{
                                    x=x+" ";
                                }
                                x=x+(("          "));
                                break;
                        }

                            if(system.get(k).idWDS!=""){
                                x=x+((""+system.get(k).idWDS));
                            }else{
                                x=x+"        ";
                            }
                            if(system.get(k).pairs.get(j).observer!=null && system.get(k).pairs.get(j).observer!=""){
                                int counter = system.get(k).pairs.get(j).observer.length();
                                while(counter<8){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((" "+system.get(k).pairs.get(j).observer));
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
                            if(system.get(k).pairs.get(j).pairWDS!=""){
                                int counter = system.get(k).pairs.get(j).pairWDS.length();
                                while(counter<8){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((system.get(k).pairs.get(j).pairWDS)+"|");
                            }else{
                                int counter =system.get(k).pairs.get(j).el1.name.length()+system.get(k).pairs.get(j).el2.name.length();
                                while(counter<8){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).el1.name+system.get(k).pairs.get(j).el2.name+"|";
                            }

                            if(system.get(k).pairs.get(j).idHIP!=""){
                                StatisticsCollector.validateHIP(system.get(k).pairs.get(j).idHIP);
                                int counter = system.get(k).pairs.get(j).idHIP.length();
                                while(counter<6){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idHIP+"|";
                            }else{
                                x=x+"      |";
                            }

                            if(system.get(k).pairs.get(j).idHD!=""){
                                StatisticsCollector.validateHD(system.get(k).pairs.get(j).idHD);
                                int counter = system.get(k).pairs.get(j).idHD.length();
                                while(counter<6){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idHD+"|";
                            }else{
                                x=x+"      |";
                            }

                            if(system.get(k).pairs.get(j).idSB9!=""){
                                int counter =system.get(k).pairs.get(j).idSB9.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idSB9+"|";
                            }else{
                                x=x+"     |";
                            }
                            if(system.get(k).pairs.get(j).idCEV!=null && system.get(k).pairs.get(j).idCEV!=""){
                                int counter =system.get(k).pairs.get(j).idCEV.length();
                                while(counter<10){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idCEV+"|";
                            }else{
                                x=x+"          |";
                            }
                            if(system.get(k).pairs.get(j).idADS!=""){
                                int counter=system.get(k).pairs.get(j).idADS.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idADS+"|";
                            }else{
                                x=x+"     |";
                            }

                            if(system.get(k).pairs.get(j).idHyi!=""){
                                int counter=system.get(k).pairs.get(j).idHyi.length();
                                while(counter<10){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idHyi+"|";
                            }else{
                                x=x+"          |";
                            }
                            if(system.get(k).pairs.get(j).idBayer!=""){
                                int counter =system.get(k).pairs.get(j).idBayer.length();
                                while(counter<10){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idBayer+"|";
                            }else{
                                x=x+"          |";
                            }
                            if(system.get(k).pairs.get(j).idDM!=""){
                                StatisticsCollector.validateDM(system.get(k).pairs.get(j).idDM);
                                int counter = system.get(k).pairs.get(j).idDM.length();
                                while(counter<12){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+system.get(k).pairs.get(j).idDM+"|";
                            }else{
                                x=x+"            |";
                            }
                            if(system.get(k).pairs.get(j).idTDSC!=""){
                                int counter=system.get(k).pairs.get(j).idTDSC.length();
                                while(counter<12){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((system.get(k).pairs.get(j).idTDSC+"|"));
                            }else{
                                x=x+"            |";
                            }
                            if(system.get(k).pairs.get(j).pairTDSC!=""){
                                int counter = system.get(k).pairs.get(j).pairTDSC.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((system.get(k).pairs.get(j).pairTDSC+"|"));
                            }else{
                                x=x+"     |";
                            }
                            if(system.get(k).pairs.get(j).idCCDM!=""){
                                int counter = system.get(k).pairs.get(j).idCCDM.length();
                                while(counter<12){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((system.get(k).pairs.get(j).idCCDM+"|"));
                            }else{
                                x=x+"            |";
                            }
                            if(system.get(k).pairs.get(j).pairCCDM!=""){
                                int counter = system.get(k).pairs.get(j).pairCCDM.length();
                                while(counter<5){
                                    x=x+" ";
                                    counter++;
                                }
                                x=x+((system.get(k).pairs.get(j).pairCCDM+"|"));
                            }else{
                                x=x+"     |";
                            }
                        }
                        if(stage==2){
                            while(x.length()<67){
                                x=x+" ";
                            }
                            if(0==0) {
                                int counter = system.get(k).pairs.get(j).el1.name.length();
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                                for (int ite = 0; ite < system.get(k).pairs.get(j).el1.name.length(); ite++) {
                                    x = x + (system.get(k).pairs.get(j).el1.name.charAt(ite));
                                }
                            }else{
                                int counter = 0;
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                            }
                            x=x+"|";
                            if(system.get(k).pairs.get(j).el1.idHIP!=""){
                                StatisticsCollector.validateHIP(system.get(k).pairs.get(j).el1.idHIP);
                                int counter2 = system.get(k).pairs.get(j).el1.idHIP.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+system.get(k).pairs.get(j).el1.idHIP+"|";
                            }else{
                                x=x+"      |";
                            }
                            if(system.get(k).pairs.get(j).el1.idHD!=""){
                                StatisticsCollector.validateHD(system.get(k).pairs.get(j).el1.idHD);
                                int counter2 = system.get(k).pairs.get(j).el1.idHD.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+system.get(k).pairs.get(j).el1.idHD+"|";
                            }else{
                                x=x+"      |";
                            }
                            x=x+"     |          |     |          |          |";
                            if(system.get(k).pairs.get(j).el1.idDM!=""){
                                StatisticsCollector.validateDM(system.get(k).pairs.get(j).el1.idDM);
                                int counter3 = system.get(k).pairs.get(j).el1.idDM.length();
                                while(counter3<12){
                                    x=x+" ";
                                    counter3++;
                                }
                                x=x+system.get(k).pairs.get(j).el1.idDM+"|";
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
                                int counter = system.get(k).pairs.get(j).el2.name.length();
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                                for (int ite = 0; ite < system.get(k).pairs.get(j).el2.name.length(); ite++) {
                                    x = x + (system.get(k).pairs.get(j).el2.name.charAt(ite));
                                }
                            }else{
                                int counter = 0;
                                while(counter<7){
                                    x=x+" ";
                                    counter++;
                                }
                            }
                            x=x+"|";
                            if(system.get(k).pairs.get(j).el2.idHIP!=""){
                                StatisticsCollector.validateHIP(system.get(k).pairs.get(j).el2.idHIP);
                                int counter2 = system.get(k).pairs.get(j).el2.idHIP.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+system.get(k).pairs.get(j).el2.idHIP+"|";
                            }else{
                                x=x+"      |";
                            }
                            if(system.get(k).pairs.get(j).el2.idHD!=""){
                                StatisticsCollector.validateHD(system.get(k).pairs.get(j).el2.idHD);
                                int counter2 = system.get(k).pairs.get(j).el2.idHD.length();
                                while(counter2<6){
                                    x=x+" ";
                                    counter2++;
                                }
                                x=x+system.get(k).pairs.get(j).el2.idHD+"|";
                            }else{
                                x=x+"      |";
                            }
                            x=x+"     |          |     |          |          |";
                            if(system.get(k).pairs.get(j).el2.idDM!=""){
                                StatisticsCollector.validateDM(system.get(k).pairs.get(j).el2.idDM);
                                int counter3 = system.get(k).pairs.get(j).el2.idDM.length();
                                while(counter3<12){
                                    x=x+" ";
                                    counter3++;
                                }
                                x=x+system.get(k).pairs.get(j).el2.idDM+"|";
                            }else{
                                x=x+"            |";
                            }
                        }
                        while(x.length()<185){
                            x=x+" ";
                        }
                        if(stage!=0  || stage==0){
                            if(system.get(k).pairs.get(j).idBayer!=""){
                                int counter = system.get(k).pairs.get(j).idBayer.length();//���� ����� ��������
                                x=x+((" "+system.get(k).pairs.get(j).idBayer));
                            }else if(system.get(k).pairs.get(j).idHyi!=""){
                                int counter = system.get(k).pairs.get(j).idHyi.length();//���� ����� ��������
                                x=x+((" "+system.get(k).pairs.get(j).idHyi));
                            }else if(system.get(k).pairs.get(j).idSB9!=""){
                                int counter = system.get(k).pairs.get(j).idSB9.length();
                                x=x+(("SB9 "+system.get(k).pairs.get(j).idSB9));
                            }else if(system.get(k).pairs.get(j).idCEV!=null && system.get(k).pairs.get(j).idCEV!=""){
                                int counter = system.get(k).pairs.get(j).idCEV.length();
                                x=x+(("CEV  "+system.get(k).pairs.get(j).idCEV));
                            }else if(system.get(k).pairs.get(j).idADS!=""){
                                int counter = system.get(k).pairs.get(j).idADS.length();
                                x=x+(("ADS "+system.get(k).pairs.get(j).idADS));
                            }else if(system.get(k).pairs.get(j).idHIP!=""){
                                int counter = system.get(k).pairs.get(j).idHIP.length();
                                x=x+(("HIP "+system.get(k).pairs.get(j).idHIP));
                            }else if(system.get(k).pairs.get(j).idHD!="" && system.get(k).pairs.get(j).idHD!=null && (system.get(k).pairs.get(j).idHD.contains("+") || system.get(k).pairs.get(j).idHD.contains("-"))){
                                int counter = system.get(k).pairs.get(j).idHD.length();
                                x=x+(("HD "+system.get(k).pairs.get(j).idHD));
                            }else if(system.get(k).pairs.get(j).idDM!="" && system.get(k).pairs.get(j).idDM!=null  && (system.get(k).pairs.get(j).idDM.contains("+") || system.get(k).pairs.get(j).idDM.contains("-"))){
                                int counter = system.get(k).pairs.get(j).idDM.length();
                                x=x+(("DM "+system.get(k).pairs.get(j).idDM));
                            }else if(system.get(k).idWDS!=""){
                                int counter = system.get(k).idWDS.length();
                                x=x+(("WDS "+system.get(k).idWDS));
                            }else if(system.get(k).pairs.get(j).idCCDM!=""){
                                int counter = system.get(k).pairs.get(j).idCCDM.length();
                                x=x+(("CCDM "+system.get(k).pairs.get(j).idCCDM));
                            }
                        }
                        if(stage==2){
                            while(x.length()<205){
                                x=x+" ";
                            }
                            boolean c2=true;
                            if(system.get(k).pairs.get(j).el1.coord2I<0){
                                c2=false;
                            }
                            String a1=""+Math.abs(system.get(k).pairs.get(j).el1.coord1I);
                            String a2=""+Math.abs(system.get(k).pairs.get(j).el1.coord1F);
                            String b1=""+Math.abs(system.get(k).pairs.get(j).el1.coord2I);
                            String b2=""+Math.abs(system.get(k).pairs.get(j).el1.coord2F);
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
                                x=x+"  "+system.get(k).pairs.get(j).el1.coord_flag;
                            }else{
                                x=x+"*";
                            }
                        }
                        if(stage==3){
                            while(x.length()<205){
                                x=x+" ";
                            }
                            boolean c2=true;
                            if(system.get(k).pairs.get(j).el2.coord2I<0){
                                c2=false;
                            }
                            String a1=""+Math.abs(system.get(k).pairs.get(j).el2.coord1I);
                            String a2=""+Math.abs(system.get(k).pairs.get(j).el2.coord1F);
                            String b1=""+Math.abs(system.get(k).pairs.get(j).el2.coord2I);
                            String b2=""+Math.abs(system.get(k).pairs.get(j).el2.coord2F);
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
                                x=x+"  "+system.get(k).pairs.get(j).el2.coord_flag;
                            }else{
                                x=x+"*";
                            }
                        }
                        if(nameReal(k,j,stage)){
                            //System.out.println(x);
                            if(stage==0){
                                if(system.get(k).out==false){
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                                    system.get(k).out=true;
                                }
                            }
                            if(stage==1){
                                if(stage==1 && usedLength<length){
                                    stage--;
                                }
                                if(true || system.get(k).pairs.get(j).out==false){
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                                    system.get(k).pairs.get(j).out=true;
                                }
                            }
                            if(stage==2){
                                if(system.get(k).pairs.get(j).el1.out==false){
                                    system.get(k).pairs.get(j).el1.out=true;
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                                }
                            }
                            if(stage==3){
                                if(system.get(k).pairs.get(j).el2.out==false){
                                    system.get(k).pairs.get(j).el2.out=true;
                                    outer.write(x+""+(char)10);
                                    outer.flush();
                                }
                            }
                        }
                    }
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
