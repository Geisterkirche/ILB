package lib.tools.resolvingRulesImplementation;

import ILBprocessing.MainEntryPoint;
import lib.model.Pair;
import lib.model.service.Datasourse;
import lib.model.service.KeysDictionary;
import lib.model.service.MatchingParameters;
import lib.model.service.NodeForParsedCatalogue;

import java.util.ArrayList;

/**
 * Created by Алекс on 08.01.2017.
 */
public class MatchingByAnglesImplementation extends MainEntryPoint {
    public static ArrayList<? extends NodeForParsedCatalogue> resolve(ArrayList<? extends NodeForParsedCatalogue> list, Datasourse datasourceClass) {
        int f = list.size();
        for (int i = 0; i < f; i++) {
            boolean alreadyMatched = false;
            boolean tooManyMatches = false;
            Pair matchedTo = null;
            for (int j = 0; j < sysList.size(); j++) {
                for (int k = 0; k < sysList.get(j).pairs.size(); k++) {
                    if (fastAngleValidator(sysList.get(j).pairs.get(k),list.get(i))) {
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
                    System.err.print("MATCH both angles! "+ list.get(i).source);
                    datasourceClass.getClass().newInstance().propagate(matchedTo, list.get(i));
                    list.remove(i);
                    i--;
                    f--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                if(tooManyMatches) {
                    System.err.print("  tooManyMatches"+ list.get(i).source);
                }else{
                    System.err.print("  notCorrespondsToCriteria"+ list.get(i).source);
                }
            }
        }
        return list;
    }
    public static boolean fastAngleValidator(Pair e, NodeForParsedCatalogue n){
        if(MatchingByCoordinatesRuleImplementation.corresponds(e,n)){
            try {
                double r1 = Double.parseDouble(n.params.get(KeysDictionary.RHO));
                double r2 = Double.parseDouble(e.params.get(KeysDictionary.RHO));
                double t1 = Double.parseDouble(n.params.get(KeysDictionary.THETA));
                double t2 = Double.parseDouble(e.params.get(KeysDictionary.THETA));
                double func = (r1 - r2) * (r1 - r2) / Math.max(r1, r2) + (t1 - t2) * (t1 - t2) / Math.max(t1, t2)/2;
                if (func<MatchingParameters.ANGLE_MATCHING_LIMIT) {
                    System.err.println("resolved: "+r1+" :"+r2+" and "+t1+" : "+t2);
                    return true;
                }
            }catch (Exception e1){
                System.err.print("not enough data: "+n.source);
            }
        }
        return false;
    }


    public static ArrayList<? extends NodeForParsedCatalogue> resolveOnlyByRho(ArrayList<? extends NodeForParsedCatalogue> list, Datasourse datasourceClass) {
        int f = list.size();
        for (int i = 0; i < f; i++) {
            boolean alreadyMatched = false;
            boolean tooManyMatches = false;
            Pair matchedTo = null;
            for (int j = 0; j < sysList.size(); j++) {
                for (int k = 0; k < sysList.get(j).pairs.size(); k++) {
                    if (fastAngleValidatorOnlyForRho(sysList.get(j).pairs.get(k),list.get(i))) {
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
                    System.err.print("MATCH 1 angle"+ list.get(i).source);
                    datasourceClass.getClass().newInstance().propagate(matchedTo, list.get(i));
                    list.remove(i);
                    i--;
                    f--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                if(tooManyMatches) {
                    System.err.print("  tooManyMatches "+ list.get(i).source);
                }else{
                    System.err.print("  notCorrespondsToCriteria "+ list.get(i).source);
                }
            }
        }
        return list;
    }
    public static boolean fastAngleValidatorOnlyForRho(Pair e, NodeForParsedCatalogue n){
        if(MatchingByCoordinatesRuleImplementation.corresponds(e,n)){
            try {
                double r1 = Double.parseDouble(n.params.get(KeysDictionary.RHO));
                double r2 = Double.parseDouble(e.params.get(KeysDictionary.RHO));
                double func = (r1 - r2) * (r1 - r2) / Math.max(r1, r2)*2;
                if (func<MatchingParameters.ANGLE_MATCHING_LIMIT) {
                    System.err.println("resolved only by RHO: "+r1+" :"+r2);
                    return true;
                }
            }catch (Exception e1){
                System.err.print("not enough data: "+n.source);
            }
        }
        return false;
    }
}
