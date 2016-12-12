package lib.tools;

import lib.model.StarSystem;

import java.util.ArrayList;

/**
 * Created by Алекс on 25.11.2016.
 */
public class EntitiesListTools {
    public static void removeDuplicatedEntities(ArrayList<StarSystem> sysList) {
        for (int i = 0; i < sysList.size(); i++) {
            for (int j = 0; j < sysList.get(i).pairs.size(); j++) {
                for (int k = 0; k < j; k++) {
                    if (sysList.get(i).pairs.get(k).el1.nameInILB.equals(sysList.get(i).pairs.get(j).el1.nameInILB)) {
                        sysList.get(i).pairs.get(j).el1.doNotShowBcsResolved = true;
                    } else if (sysList.get(i).pairs.get(k).el1.nameInILB.equals(sysList.get(i).pairs.get(j).el2.nameInILB)) {
                        sysList.get(i).pairs.get(j).el2.doNotShowBcsResolved = true;
                    }
                    if (sysList.get(i).pairs.get(k).el2.nameInILB.equals(sysList.get(i).pairs.get(j).el1.nameInILB)) {
                        sysList.get(i).pairs.get(j).el1.doNotShowBcsResolved = true;
                    } else if (sysList.get(i).pairs.get(k).el2.nameInILB.equals(sysList.get(i).pairs.get(j).el2.nameInILB)) {
                        sysList.get(i).pairs.get(j).el2.doNotShowBcsResolved = true;
                    }
                }
            }
        }
    }
}
