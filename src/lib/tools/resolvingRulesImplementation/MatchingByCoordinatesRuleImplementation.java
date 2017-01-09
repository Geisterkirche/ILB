package lib.tools.resolvingRulesImplementation;

import ILBprocessing.MainEntryPoint;
import lib.model.Pair;
import lib.model.service.Datasourse;
import lib.model.service.KeysDictionary;
import lib.model.service.MatchingParameters;
import lib.model.service.NodeForParsedCatalogue;

import java.util.ArrayList;

public class MatchingByCoordinatesRuleImplementation extends MainEntryPoint {
    public static ArrayList<? extends NodeForParsedCatalogue> resolve(ArrayList<? extends NodeForParsedCatalogue> list, Datasourse datasourceClass) {
        int f = list.size();
        for (int i = 0; i < f; i++) {
            boolean alreadyMatched = false;
            boolean tooManyMatches = false;
            Pair matchedTo = null;
            for (int j = 0; j < sysList.size(); j++) {
                for (int k = 0; k < sysList.get(j).pairs.size(); k++) {
                        if (corresponds(sysList.get(j).pairs.get(k),list.get(i))) {
                                if (!alreadyMatched) {
                                    alreadyMatched = true;
                                    matchedTo = sysList.get(j).pairs.get(k);
                                } else {
                                    tooManyMatches = true;
                                }
                    }
                }
            }
            if (!tooManyMatches && alreadyMatched) {
                try {
                    System.err.print("MATCH by coordinates!"+ list.get(i).source);
                    datasourceClass.getClass().newInstance().propagate(matchedTo, list.get(i));
                    list.remove(i);
                    i--;
                    f--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                if(tooManyMatches) {
                    System.err.print("UNRESOLVED! tooManyMatches:"+ list.get(i).source);
                }else{
                    System.err.print("UNRESOLVED! not found matches:"+ list.get(i).source);
                }
            }
        }
        return list;
    }
    public static boolean corresponds(Pair e, NodeForParsedCatalogue n){
        double x1 = e.el1.rightCoordX;
        double y1 = e.el1.rightCoordY;
        double x2 = Double.parseDouble(n.params.get(KeysDictionary.X));
        double y2 = Double.parseDouble(n.params.get(KeysDictionary.Y));

        double r = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));

        if(r<MatchingParameters.COORDINATES_MATCHING_LIMIT){
            //System.err.println();
            //System.err.println("rightCoord: "+ e.el1.rightCoordX+" | "+e.el1.rightCoordY);
            //System.err.println("matched to: "+ n.params.get(KeysDictionary.X)+" | "+n.params.get(KeysDictionary.Y));
            return true;
        }else {
            return false;
        }
    }
}
