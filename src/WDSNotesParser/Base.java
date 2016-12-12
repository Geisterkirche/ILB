package WDSNotesParser;

import java.util.ArrayList;

/**
 * Created by Алекс on 27.07.2016.
 */
public class Base {
    public static ArrayList<StorageEntity> storage = new ArrayList<StorageEntity>();
    public static void addCast(String object, String property, boolean yORn, String system, String pair){
        System.out.println(system+" "+pair+" rule1:"+yORn+" "+"["+object+"]"+" is "+property);
        storage.add(new StorageEntity(object, property, yORn, system, pair));
    }
    public static void addEquation(String object, String system, String pair){
        System.out.println(system+" "+pair+" rule2:" +"["+pair+"]"+" = "+object);
    }
}
