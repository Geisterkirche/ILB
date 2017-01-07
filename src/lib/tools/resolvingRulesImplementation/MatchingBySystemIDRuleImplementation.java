package lib.tools.resolvingRulesImplementation;

import ILBprocessing.MainEntryPoint;
import lib.model.Pair;
import lib.model.StarSystem;
import lib.model.service.Datasourse;
import lib.model.service.NodeForParsedCatalogue;

import java.util.ArrayList;

public class MatchingBySystemIDRuleImplementation extends MainEntryPoint {
    public static ArrayList<? extends NodeForParsedCatalogue> resolve(String key, ArrayList<? extends NodeForParsedCatalogue> list, Datasourse datasourceClass){
        int f=list.size();
        for(int i=0;i<f;i++){
            boolean alreadyMatched=false;
            boolean tooManyMatches=false;
            StarSystem matchedTo=null;
            for(int j = 0; j< sysList.size(); j++){
                    if(list.get(i).params.containsKey(key)){
                        if(sysList.get(j).params.containsKey(key)) {
                            if (list.get(i).params.get(key).equals(sysList.get(j).params.get(key))) {
                                if (!alreadyMatched) {
                                    alreadyMatched = true;
                                    matchedTo = sysList.get(j);
                                } else {
                                    tooManyMatches = true;
                                }
                            }
                        }

                }
            }
            if(!tooManyMatches && alreadyMatched){
                try{
                    Pair e = new Pair();
                    datasourceClass.getClass().newInstance().propagate(e,list.get(i));
                    matchedTo.pairs.add(e);
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
