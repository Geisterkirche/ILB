package ILBprocessing;

import ILBprocessing.beans.NodeCCDMPair;
import ILBprocessing.beans.NodeORB6FINALIZED;
import ILBprocessing.datasources.CCDMDS;
import ILBprocessing.datasources.ORB6DS;
import ILBprocessing.datasources.WDSDS;
import lib.model.Pair;
import lib.model.StarSystem;
import lib.model.service.KeysDictionary;
import lib.tools.resolvingRulesImplementation.MatchingByAnglesImplementation;
import lib.tools.resolvingRulesImplementation.MatchingByCoordinatesRuleImplementation;
import lib.tools.resolvingRulesImplementation.MatchingByIDRuleImplementation;
import lib.tools.resolvingRulesImplementation.MatchingBySystemIDRuleImplementation;

import java.util.ArrayList;

public class InterpreterFactory extends MainEntryPoint {
    public static void interprWDS(){
        int xLog=0;
        long timePrev=System.nanoTime();
        int d= listWDS.size();
        for(int i=0;i<d;i++){
            //create coordinatesNotFoundInWDS sysList
            StarSystem s= new StarSystem();
            s.data= listWDS.get(i).coordinatesFromWDSasString;
            s.params.put(KeysDictionary.WDSSYSTEM,listWDS.get(i).params.get(KeysDictionary.WDSSYSTEM));
            s.coordinatesNotFoundInWDS = listWDS.get(i).coordinatesNotFoundInWDS;

            //check if such sysList not exists
            boolean uniqueSystem=true;
            int sysNumber=0;
            int h= sysList.size();
            for(int j=0;j<h;j++){
                if(s.params.get(KeysDictionary.WDSSYSTEM).equals(sysList.get(j).params.get(KeysDictionary.WDSSYSTEM))){
                    uniqueSystem=false;
                    sysNumber=j;
                    break;
                }
            }
            if(uniqueSystem){
                sysList.add(s);
                try {
                    Pair e = new Pair();
                    new WDSDS().propagate(e, listWDS.get(i));
                    s.pairs.add(e);
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }else{
                try {
                    Pair e = new Pair();
                    new WDSDS().propagate(e, listWDS.get(i));
                    sysList.get(sysNumber).pairs.add(e);
                }catch (Exception e){
                    System.err.println(e.getMessage());
                }
            }

            if(xLog*500<=i){
                System.out.println("............snap:"+i+"/"+d+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
                timePrev=System.nanoTime();
                xLog=(int) (i/500+1);
            }
        }
    }
    public static void interprSCO(){
        listSCO= (ArrayList<NodeORB6FINALIZED>)MatchingByIDRuleImplementation.resolve(KeysDictionary.OBSERVER,listSCO, new ORB6DS());
        listSCO= (ArrayList<NodeORB6FINALIZED>)MatchingByIDRuleImplementation.resolve(KeysDictionary.DM,listSCO, new ORB6DS());
        listSCO= (ArrayList<NodeORB6FINALIZED>)MatchingBySystemIDRuleImplementation.resolve(KeysDictionary.WDSSYSTEM,listSCO, new ORB6DS());
    }
    public static void interprCCDM(){
        System.err.println("call:interprCCDM()");
        int f = listCCDMPairs.size();
        System.err.println("Total list size:"+listCCDMPairs.size());
        System.err.println("RULE 1 EXECUTION STARTED");
        System.err.println("MatchingByAnglesImplementation.resolve(listCCDMPairs, new CCDMDS())");
        listCCDMPairs= (ArrayList<NodeCCDMPair>) MatchingByAnglesImplementation.resolve(listCCDMPairs, new CCDMDS());
        System.err.println("Not correspond to rules:"+listCCDMPairs.size());
        System.err.println("");
        System.err.println("");
        System.err.println("");
        System.err.println("RULE 2 EXECUTION STARTED");
        System.err.println("MatchingByAnglesImplementation.resolveOnlyByRho(listCCDMPairs, new CCDMDS())");
        listCCDMPairs= (ArrayList<NodeCCDMPair>) MatchingByAnglesImplementation.resolveOnlyByRho(listCCDMPairs, new CCDMDS());
        System.err.println("Not correspond to rules:"+listCCDMPairs.size()+" from "+f);
        System.err.println("");
        System.err.println("");
        System.err.println("RULE 3 EXECUTION STARTED");
        System.err.println("MatchingByCoordinatesRuleImplementation.resolve(listCCDMPairs, new CCDMDS()");
        listCCDMPairs= (ArrayList<NodeCCDMPair>) MatchingByCoordinatesRuleImplementation.resolve(listCCDMPairs, new CCDMDS());
        System.err.println("Not correspond to rules:"+listCCDMPairs.size()+" from "+f);
   }
}
