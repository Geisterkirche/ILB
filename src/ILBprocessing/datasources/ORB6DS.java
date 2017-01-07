package ILBprocessing.datasources;

import ILBprocessing.beans.NodeORB6FINALIZED;
import lib.model.Pair;
import lib.model.service.Datasourse;
import lib.model.service.KeysDictionary;
import lib.model.service.NodeForParsedCatalogue;

/**
 * Created by Алекс on 25.11.2016.
 */
public class ORB6DS implements Datasourse {
    @Override
    public void propagate(Pair e, NodeForParsedCatalogue nodeRaw) throws Exception{
        e.modifier[1]='o';
        if(nodeRaw instanceof NodeORB6FINALIZED) {
            NodeORB6FINALIZED node = (NodeORB6FINALIZED) nodeRaw;
            if(node.params.containsKey(KeysDictionary.HIP) && !node.params.get(KeysDictionary.HIP).equals(""))  e.params.put(KeysDictionary.HIP,node.params.get(KeysDictionary.HIP));
            if(node.params.containsKey(KeysDictionary.ADS) && !node.params.get(KeysDictionary.ADS).equals("")) e.params.put(KeysDictionary.ADS,node.params.get(KeysDictionary.ADS));
            if(node.params.containsKey(KeysDictionary.FLAMSTEED) && !node.params.get(KeysDictionary.FLAMSTEED).equals("")) e.params.put(KeysDictionary.FLAMSTEED,node.params.get(KeysDictionary.FLAMSTEED));
            if(node.params.containsKey(KeysDictionary.BAYER) && !node.params.get(KeysDictionary.BAYER).equals("")) e.params.put(KeysDictionary.BAYER,node.params.get(KeysDictionary.BAYER));
            if(node.params.containsKey(KeysDictionary.DM) && !node.params.get(KeysDictionary.DM).equals("")) e.params.put(KeysDictionary.DM,node.params.get(KeysDictionary.DM));
            if(node.params.containsKey(KeysDictionary.HD) && !node.params.get(KeysDictionary.HD).equals("")) e.params.put(KeysDictionary.HD,node.params.get(KeysDictionary.HD));
            if(node.params.containsKey(KeysDictionary.OBSERVER) && !node.params.get(KeysDictionary.OBSERVER).equals("")) e.params.put(KeysDictionary.OBSERVER, node.params.get(KeysDictionary.OBSERVER));
        }else{
            throw new Exception("illegal use of ORB6DS");
        }
    }
}
