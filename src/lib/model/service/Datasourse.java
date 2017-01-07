package lib.model.service;

import lib.model.Pair;

/**
 * Created by Алекс on 07.01.2017.
 */
public interface Datasourse {
    void propagate(Pair e, NodeForParsedCatalogue node) throws Exception;

}
