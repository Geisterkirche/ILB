package ILBprocessing.configuration;

/**
 * Created by Алекс on 21.03.2016.
 */
public interface SharedConstants {
    String FILE_NAME_DEFAULT = "DATA_RELEASE";
    String FILE_RESULT_FORMAT = ".txt";

    String INPUT_FOLDER = "C:/WDSparser/";
    String OUTPUT_FOLDER = "C:/WDSparser2/";

    boolean REMOVE_DUPLICATED_ENTITIES = false;

    String WDS_GENERATED_STUFF = "WDS";
    String CCDM_GENERATED_STUFF = "CCDM";
    String WCT_GENERATED_STUFF = "WCT";
    String INT4_GENERATED_STUFF = "INT";


    boolean WDS_SPLIT_BEFORE_PROCESSING=false;
    boolean CCDM_SPLIT_BEFORE_PROCESSING=false;
    boolean INT4_SPLIT_BEFORE_PROCESSING=false;
    boolean WCT_SPLIT_BEFORE_PROCESSING=false;
    String[] WDS_SOURCE_FILES = {"data06.txt", "data12.txt", "data18.txt", "data24.txt"};
    String[] CCDM_SOURCE_FILES = {"CCDMCORR.dat"};
    String[] WCT_SOURCE_FILES = {"dataWCT.dat"};
    String[] INT4_SOURCE_FILES = {"dataINT4.txt"};


    String SCO_SOURCE_FILE = "dataSCO.txt";
    String ILB_RESULT_FILE = "ILB.txt";

}
