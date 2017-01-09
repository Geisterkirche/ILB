package ILBprocessing.beans.helpers;

import ILBprocessing.MainEntryPoint;
import ILBprocessing.beans.NodeCCDMPair;
import lib.model.service.KeysDictionary;
import lib.tools.ConverterFINALIZED;
import lib.tools.GlobalPoolOfIdentifiers;

import java.util.ArrayList;

public class NodeCCDMComponent{
    public String source = "";
    public String pairCCDM = "";
    public String nameOfObserver = "";
    public String wdsID = "";
    public String ccdmID = "";
    public String DM = "";
    public String HD = "";
    public String ADS = "";
    public String HIP = "";
    public char componentInfo;
    public String coord_I2_1fake = "";// in 00149-3209 00149
    public String coord_I2_2fake ="";// in 00149-3209 -3209
    public double coord_F2_1fake = 0;
    public double coord_F2_2fake = 0;
    public boolean astrometric = false;

    public NodeCCDMComponent(String s) {
        source=s;
        ccdmID = s.substring(1, 11);
        if (s.charAt(12) == 'A') {
            componentInfo = 'A';
        } else if (s.charAt(11) == ' ') {
            pairCCDM = "A" + s.charAt(12);
            componentInfo = s.charAt(12);
        } else {
            pairCCDM = "" + s.charAt(11) + s.charAt(12);
            componentInfo = s.charAt(12);
        }
        parseCoordinates(s);
        if (s.charAt(14) == '%' || s.charAt(14) == '&') {
            astrometric = true;
        }
        HIP= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(s.substring(126, 132));
        if(HIP.length()>1)GlobalPoolOfIdentifiers.HIP.add(HIP);

        nameOfObserver= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(s.substring(15, 22));

        DM= GlobalPoolOfIdentifiers.rebuildIdForDM(s.substring(77, 87));
        if(DM.length()>1)GlobalPoolOfIdentifiers.DM.add(DM);

        HD= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(s.substring(98, 104));
        if(HD.length()>1)GlobalPoolOfIdentifiers.HD.add(HD);

        if (s.charAt(106) == 'A') {
            ADS= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(s.substring(107, 112));
            if(ADS.length()>1)GlobalPoolOfIdentifiers.ADS.add(ADS);
        }
        wdsID = s.substring(114, 125).replaceAll("N","+").replaceAll("S","-");
    }
    public void parseCoordinates(String s) {//refactored. finished method
        try {
            coord_I2_1fake = s.substring(1, 6);
            if (s.charAt(6) != ' ') {
                coord_I2_2fake = s.substring(6, 11);
            } else {
                coord_I2_2fake = s.substring(7, 11);
            }

            if (s.charAt(27) != ' ') {
                if (s.charAt(23) != ' ') {
                    coord_F2_1fake=Double.parseDouble(s.substring(23, 30));
                }else{
                    coord_F2_1fake=Double.parseDouble(s.substring(24, 30));
                }

                if (s.charAt(30) != ' ') {
                    coord_F2_2fake=Double.parseDouble(s.substring(30, 37));
                }else{
                    coord_F2_2fake=Double.parseDouble(s.substring(31, 37));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.err.println("causedBy:"+s);
        }
    }
    public static void translateToPairs(ArrayList<NodeCCDMComponent> listComp,ArrayList<NodeCCDMPair> listPair){
        for(int i=0;i<listComp.size();i++){
            if(listComp.get(i).componentInfo!='A'){
                NodeCCDMPair e = new NodeCCDMPair();
                e.params.put(KeysDictionary.CCDMSYSTEM,listComp.get(i).ccdmID);
                char target = listComp.get(i).pairCCDM.charAt(0);
                for(int j=0;j<listComp.size();j++){
                    if(listComp.get(j).componentInfo==target){
                        propagateToPairNode(e,listComp.get(j));
                        break;
                    }
                }
                propagateToPairNode(e,listComp.get(i));
                e.source=listComp.get(i).source;
                e.params.put(KeysDictionary.THETA,""+listComp.get(i).source.substring(46,49));
                e.params.put(KeysDictionary.RHO,""+listComp.get(i).source.substring(50,55));
                MainEntryPoint.listCCDMPairs.add(e);
            }
        }
    }
    public static void propagateToPairNode(NodeCCDMPair e, NodeCCDMComponent c){
        if(c.nameOfObserver.length()>1){
            e.params.put(KeysDictionary.OBSERVER,c.nameOfObserver);
        }
        e.params.put(KeysDictionary.CCDMSYSTEM,c.ccdmID);
        //TODO:propagate astrometric flag
        if(c.HIP.length()>1){
            e.params.put(KeysDictionary.HIP,c.HIP);
        }
        if(c.DM.length()>1){
            e.params.put(KeysDictionary.DM,c.DM);
        }
        if(c.HD.length()>1){
            e.params.put(KeysDictionary.HD,c.HD);
        }
        if(c.ADS.length()>1){
            e.params.put(KeysDictionary.ADS,c.ADS);
        }
        if(c.wdsID.length()>1){
            e.params.put(KeysDictionary.WDSSYSTEM,c.wdsID);
        }
        if(!e.params.containsKey(KeysDictionary.COORD_I1_1)){
            e.params.put(KeysDictionary.COORD_I1_1,""+c.coord_I2_1fake);
            e.params.put(KeysDictionary.COORD_I1_2,""+c.coord_I2_2fake);
            e.params.put(KeysDictionary.COORD_F1_1,""+c.coord_F2_1fake);
            e.params.put(KeysDictionary.COORD_F1_2,""+c.coord_F2_2fake);
            //050612.55+372122.2
            //00018+6437
            float h = Integer.parseInt(c.coord_I2_1fake)/10*100+Integer.parseInt(c.coord_I2_1fake)%10*6;
            double x = ConverterFINALIZED.hrsToRad(h,c.coord_F2_1fake*100)/Math.PI*180;
            double y = ConverterFINALIZED.grToRad(Double.parseDouble(c.coord_I2_2fake)*100,c.coord_F2_2fake*10)/Math.PI*180;

            e.params.put(KeysDictionary.X,""+x);
            e.params.put(KeysDictionary.Y,""+y);
        }else{
            e.params.put(KeysDictionary.COORD_I2_1,""+c.coord_I2_1fake);
            e.params.put(KeysDictionary.COORD_I2_2,""+c.coord_I2_2fake);
            e.params.put(KeysDictionary.COORD_F2_1,""+c.coord_F2_1fake);
            e.params.put(KeysDictionary.COORD_F2_2,""+c.coord_F2_2fake);
           // ConverterFINALIZED.calculateRhoTheta(e);
        }
    }
}
