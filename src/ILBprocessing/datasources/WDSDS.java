package ILBprocessing.datasources;

import ILBprocessing.beans.NodeWDSFINALIZED;
import lib.model.Pair;
import lib.model.service.Datasourse;
import lib.model.service.KeysDictionary;
import lib.model.service.NodeForParsedCatalogue;
import lib.tools.ConverterFINALIZED;

/**
 * Created by Алекс on 25.11.2016.
 */
public class WDSDS implements Datasourse {
    @Override
    public void propagate(Pair e, NodeForParsedCatalogue nodeRaw) throws Exception{
        if(nodeRaw instanceof NodeWDSFINALIZED) {
            NodeWDSFINALIZED node = (NodeWDSFINALIZED)nodeRaw;

            e.params.put(KeysDictionary.DM,node.idDM);
            e.params.put(KeysDictionary.OBSERVER,node.params.get(KeysDictionary.OBSERVER));

            e.rho= node.rho;
            e.theta= node.theta;
            e.modifier[0]= node.modifier2[0];
            if(node.modifier2[1]!=0){
                e.modifier[0]= node.modifier2[1];
            }
            e.pairWDS= node.pairNameXXXXXfromWDS.replaceAll(" ","");
            e.nameComponentsByPairNameForWDS();
            parseCoordinatesDirectlyFromWDS(e,node);
        }else{
            throw new Exception("illegal use of WDSDS");
        }
    }
    public static void parseCoordinatesDirectlyFromWDS(Pair e, NodeWDSFINALIZED node){
        //global error tracker. Should be never thrown;
        try{
            //
            //

            //here can't be exception XXXXXX.YY-YYYYYY.Y
            e.el1.coord1I=Integer.parseInt(node.coordinatesFromWDSasString.substring(0,6));
            e.el1.coord_flag=11;

            // YYYYYY.XX-YYYYYY.Y sometimes missing
            try{
                e.el1.coord1F=Integer.parseInt(node.coordinatesFromWDSasString.substring(7,9));
            }catch(NumberFormatException exc1){
                System.err.println("ERR01 caught while parsing "+ node.coordinatesFromWDSasString);
                try {
                    e.el1.coord1F=Integer.parseInt(node.coordinatesFromWDSasString.substring(7,8));
                    e.el1.coord_flag=-11;
                }catch(NumberFormatException exc2){
                    System.err.println("ERR01b caught while parsing "+ node.coordinatesFromWDSasString);
                    e.el1.coord1F = 0;
                    e.el1.coord_flag=-11;
                }
            }

            // YYYYYY.YY-XXXXXX.Y should be always clear
            try{
                e.el1.coord2I=Integer.parseInt(node.coordinatesFromWDSasString.substring(9,16));
            }catch(NumberFormatException exc) {
                System.err.println("ERR02 caught while parsing "+ node.coordinatesFromWDSasString);
                e.el1.coord2I = 0;
                e.el1.coord_flag = -11;
            }

            // YYYYYY.YY-YYYYYY.X sometimes missing
            try{
                e.el1.coord2F=Integer.parseInt(node.coordinatesFromWDSasString.substring(17,18));
            }catch(Exception exc){
                System.err.println("ERR03 caught while parsing "+ node.coordinatesFromWDSasString);
                e.el1.coord2F=0;
                e.el1.coord_flag=-11;
            }

            if(node.rho!=0){
                e.el2.coord_flag=12;
                try{
                    e.el2.coord1I=(int)(long) ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+ node.rho*Math.sin(node.theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)));
                }catch(NumberFormatException exc){
                    System.err.println("ERR04 caught while processing with e.el2.coord1I "+ node.coordinatesFromWDSasString);
                    e.el2.coord1I=0;
                    e.el2.coord_flag=-12;
                }
                try{
                    e.el2.coord1F=(int)(long)(100*(ConverterFINALIZED.radToHrs(ConverterFINALIZED.hrsToRad(e.el1.coord1I,e.el1.coord1F)+ node.rho*Math.sin(node.theta/180*Math.PI)/Math.cos(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
                }catch(NumberFormatException exc){
                    System.err.println("ERR05 caught while processing with e.el2.coord1F "+ node.coordinatesFromWDSasString);
                    e.el2.coord1F=0;
                    e.el2.coord_flag=-12;
                }
                try{
                    e.el2.coord2I=(int)(long) ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+ node.rho*Math.cos(node.theta/180*Math.PI));
                }catch(NumberFormatException exc){
                    System.err.println("ERR06 caught while processing with e.el2.coord2I "+ node.coordinatesFromWDSasString);
                    e.el2.coord2I=0;
                    e.el2.coord_flag=-12;
                }
                try{
                    e.el2.coord2F=(int)(long)(10*(ConverterFINALIZED.radToGr(ConverterFINALIZED.grToRad(e.el1.coord2I,e.el1.coord2F)+ node.rho*Math.cos(node.theta/180*Math.PI))-e.el2.coord2I));
                }catch(NumberFormatException exc){
                    System.err.println("ERR07 caught while processing with e.el2.coord2F "+ node.coordinatesFromWDSasString);
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
            System.err.println("ERR08 FATAL error caught"+ node.coordinatesFromWDSasString);
            e.el1.coord_flag=-1;
            td.printStackTrace();
        }
    }
}