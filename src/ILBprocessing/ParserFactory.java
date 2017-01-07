package ILBprocessing;

import ILBprocessing.beans.helpers.NodeCCDMComponent;
import ILBprocessing.beans.NodeORB6FINALIZED;
import ILBprocessing.beans.NodeWDSFINALIZED;
import lib.model.service.KeysDictionary;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Алекс on 21.03.2016.
 */
public class ParserFactory extends MainEntryPoint {
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
                            NodeWDSFINALIZED star = new NodeWDSFINALIZED(s);
                            listWDS.add(star);
                            ss = new StringBuffer();
                        }else{
                            ss = new StringBuffer();
                        }
                    }
                    if(d-i<1){
                        String s=ss.toString();
                        NodeWDSFINALIZED star = new NodeWDSFINALIZED(s);
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
        System.out.println("parse SCO");
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
                    NodeORB6FINALIZED star = new NodeORB6FINALIZED(s);
                    listSCO.add(star);
                    ss = new StringBuffer();
                }
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void parseCCDM(String xxx){
        try {
            String[] fileName={"CCDM"+xxx+".txt"};
            File dataFile = new File(OUTPUT_FOLDER+CCDM_GENERATED_STUFF+"/"+fileName[0]);
            FileReader in = new FileReader(dataFile);
            char c;
            long d = dataFile.length();
            StringBuffer ss = new StringBuffer();
            ArrayList<NodeCCDMComponent> listCCDMComponents = new ArrayList<NodeCCDMComponent>();
            String currentSystem="initial";
            for(long i=0;i<d;i++){
                c = (char) in.read();
                ss.append(c);
                if(c==10){
                    String s=ss.toString();
                    if(s.length()>1){
                        try {
                            NodeCCDMComponent star = new NodeCCDMComponent(s);
                            if(currentSystem.equals(star.ccdmID)) {
                                listCCDMComponents.add(star);
                            }else{
                                if(!currentSystem.equals("initial")) {
                                    NodeCCDMComponent.translateToPairs(listCCDMComponents,MainEntryPoint.listCCDMPairs);
                                }
                                listCCDMComponents = new ArrayList<NodeCCDMComponent>();
                                listCCDMComponents.add(star);
                                currentSystem=star.ccdmID;
                            }
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    ss = new StringBuffer();
                }
            }
            NodeCCDMComponent.translateToPairs(listCCDMComponents,listCCDMPairs);
            for(int i=0;i<5;i++){
                System.out.print(MainEntryPoint.listCCDMPairs.get(i).params.get(KeysDictionary.CCDMSYSTEM));
                System.out.print(" " + MainEntryPoint.listCCDMPairs.get(i).params.get(KeysDictionary.COORD_F1_1));
                System.out.print(" " + MainEntryPoint.listCCDMPairs.get(i).params.get(KeysDictionary.COORD_F2_1));
                if(MainEntryPoint.listCCDMPairs.get(i).params.containsKey(KeysDictionary.OBSERVER)) System.out.print(" " + MainEntryPoint.listCCDMPairs.get(i).params.get(KeysDictionary.OBSERVER));
                System.out.println();
            }
            System.out.println("Success. fileLength="+dataFile.length());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}