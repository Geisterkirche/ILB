package WDSNotesParser;

/**
 * Created by Алекс on 06.09.2016.
 */
public class StorageEntity {
    public String system;
    public String pair;
    public boolean yORn;
    public String object;
    public String property;
    public StorageEntity(String object, String property, boolean yORn, String system, String pair){
        this.object=object;
        this.property=property;
        this.yORn=yORn;
        this.system=system;
        this.pair=pair;
    }
}
