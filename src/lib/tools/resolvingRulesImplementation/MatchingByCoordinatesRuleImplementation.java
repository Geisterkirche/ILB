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
                    System.err.println("MATCH! "+ list.get(i).source);
                    datasourceClass.getClass().newInstance().propagate(matchedTo, list.get(i));
                    list.remove(i);
                    i--;
                    f--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
               // System.err.println("FAIL! "+tooManyMatches +" "+ alreadyMatched);
            }
        }
        return list;
    }
    public static boolean corresponds(Pair e, NodeForParsedCatalogue n){
        double x1 = Double.parseDouble(e.el1.coord1I+"."+e.el1.coord1F);
        double y1 = Double.parseDouble(e.el1.coord2I+"."+e.el1.coord2F);
        double x2 = Double.parseDouble(n.params.get(KeysDictionary.X));
        double y2 = Double.parseDouble(n.params.get(KeysDictionary.Y));

        double r = Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));

        if(r<MatchingParameters.COORDINATES_MATCHING_LIMIT){
            System.err.println(e.el1.coord1I+" " + e.el1.coord1F+" "+e.el1.coord2I+" " + e.el1.coord2F);
            System.err.println("x1: "+x1+"y1: "+y1);
            System.err.println("x2: "+x2+"y2: "+y2);
            System.err.println();
            return true;
        }else {
            return false;
        }
    }
}
