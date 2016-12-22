package ILBprocessing.configuration;

import ILBprocessing.WDSparser;
import WDSNotesParser.Base;
import WDSNotesParser.Dictionary;
import WDSNotesParser.StorageEntity;

import java.util.ArrayList;

/**
 * Created by Алекс on 06.09.2016.
 */
public class NotesInterpreter {
    public static ArrayList<StorageEntity> storage;
    public static void interpreteNotes(){
        storage= Base.storage;
        for(int i=0;i<storage.size();i++){
            for(int sys = 0; sys< WDSparser.sysList.size(); sys++){
                if(WDSparser.sysList.get(sys).idWDS.equals(storage.get(i).system)){
                    for(int j = 0; j<WDSparser.sysList.get(sys).pairs.size(); j++){
                        //TODO here stuff with observer
                        if(WDSparser.sysList.get(sys).pairs.get(j).observer.equals(storage.get(i).pair.substring(0,7))){

                                if (storage.get(i).object.equals(Dictionary.PAIR)){
                                    WDSparser.sysList.get(sys).pairs.get(j).params.put(storage.get(i).property,""+storage.get(i).yORn);
                                }else if(storage.get(i).object.equals(Dictionary.PRIMARY)){
                                    WDSparser.sysList.get(sys).pairs.get(j).el1.params.put(storage.get(i).property,""+storage.get(i).yORn);
                                }else if(storage.get(i).object.equals(Dictionary.THIS)){
                                    WDSparser.sysList.get(sys).pairs.get(j).params.put(storage.get(i).property,""+storage.get(i).yORn);
                                }else if(storage.get(i).object.equals(Dictionary.PRINCIPAL)){
                                    WDSparser.sysList.get(sys).pairs.get(j).el1.params.put(storage.get(i).property,""+storage.get(i).yORn);
                                }else if(storage.get(i).object.equals(Dictionary.SECONDARY)){
                                    WDSparser.sysList.get(sys).pairs.get(j).el2.params.put(storage.get(i).property,""+storage.get(i).yORn);
                                }else{
                                    if(WDSparser.sysList.get(sys).pairs.get(j).el1.nameInILB.equals(storage.get(i).object)){
                                        WDSparser.sysList.get(sys).pairs.get(j).el1.params.put(storage.get(i).property,""+storage.get(i).yORn);
                                    }else if(WDSparser.sysList.get(sys).pairs.get(j).el2.nameInILB.equals(storage.get(i).object)){
                                        WDSparser.sysList.get(sys).pairs.get(j).el2.params.put(storage.get(i).property,""+storage.get(i).yORn);
                                    }
                                }

                            Base.storage.remove(i);
                        }
                    }
                }
            }
        }
    }
}
