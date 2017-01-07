package lib.model.service;

/**
 * Created by Алекс on 24.11.2016.
 */
public interface SplitRule {
    boolean StringCorrespondsToFile(String string, int hour, int decade);
}
