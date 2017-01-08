package ILBprocessing.datasources;

import ILBprocessing.beans.NodeCCDMPair;
import lib.model.Pair;
import lib.model.service.Datasourse;
import lib.model.service.NodeForParsedCatalogue;

/**
 * Created by Алекс on 25.11.2016.
 */
public class CCDMDS implements Datasourse {
    @Override
    public void propagate(Pair e, NodeForParsedCatalogue nodeRaw) throws Exception{
        if(nodeRaw instanceof NodeCCDMPair) {
            NodeCCDMPair node = (NodeCCDMPair)nodeRaw;
            //e.params.put(KeysDictionary.OBSERVER,node.params.get(KeysDictionary.OBSERVER));

        }else{
            throw new Exception("illegal use of WDSDS");
        }
    }
}