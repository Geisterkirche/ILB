package ILBprocessing.datasources;

import ILBprocessing.beans.NodeORB6;
import lib.model.Pair;

/**
 * Created by Алекс on 25.11.2016.
 */
public class ORB6DS {
    //TODO: make Map
    public static void propagate(Pair e, NodeORB6 node){
        e.modifier[1]='o';
        e.idHIP=node.idHIP;
        e.idADS=node.idADS;
        e.idBayer=node.idBayer;
        e.idFlamsteed=node.idFlamsteed;
        e.idDM=node.idDM;
        e.idHD=node.idHD;
    }
}
