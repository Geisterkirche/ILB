package lib.tools.resolvingRulesImplementation;

import ILBprocessing.MainEntryPoint;
import lib.model.Pair;
import lib.model.service.Datasourse;
import lib.model.service.NodeForParsedCatalogue;

import java.util.ArrayList;

public class MatchingByIDRuleImplementation extends MainEntryPoint {

    public static ArrayList<? extends NodeForParsedCatalogue> resolve(String key, ArrayList<? extends NodeForParsedCatalogue> list, Datasourse datasourceClass){
            int f=list.size();
            for(int i=0;i<f;i++){
                boolean alreadyMatched=false;
                boolean tooManyMatches=false;
                Pair matchedTo=null;
                for(int j = 0; j< sysList.size(); j++){
                    for(int k=0;k<sysList.get(j).pairs.size();k++){
                        if(list.get(i).params.containsKey(key)){
                            if(sysList.get(j).pairs.get(k).params.containsKey(key)) {
                                if (list.get(i).params.get(key).equals(sysList.get(j).pairs.get(k).params.get(key))) {
                                    if (!alreadyMatched) {
                                        alreadyMatched = true;
                                        matchedTo = sysList.get(j).pairs.get(k);
                                    } else {
                                        tooManyMatches = true;
                                    }
                                }
                            }
                        }
                    }
                }
                if(!tooManyMatches && alreadyMatched){
                    try{
                        //System.err.println("MATCH! "+ key + " "+ list.get(i).params.get(key));
                        //System.err.println(list.get(i).source);
                        datasourceClass.getClass().newInstance().propagate(matchedTo,list.get(i));
                        list.remove(i);
                        i--;
                        f--;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

        return list;
    }
}
