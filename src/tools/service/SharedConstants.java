package tools.service;

/**
 * Created by Алекс on 21.03.2016.
 */
public interface SharedConstants {
    public static final String FILE_NAME_DEFAULT = "DATA_RELEASE";
    public static final String FILE_RESULT_FORMAT= ".txt";

    public static final String INPUT_FOLDER= "C:/WDSparser/";
    public static final String OUTPUT_FOLDER= "C:/WDSparser2/";

    public static final String WDS_GENERATED_STUFF= OUTPUT_FOLDER+"WDS/";
    public static final String CCDM_GENERATED_STUFF= OUTPUT_FOLDER+"CCDM/";
    public static final String WCT_GENERATED_STUFF= OUTPUT_FOLDER+"WCT/";
    public static final String INT4_GENERATED_STUFF= OUTPUT_FOLDER+"INT/";


    public static final String[] WDS_SOURCE_FILES = {"data06.txt","data12.txt","data18.txt","data24.txt"};
    public static final String CCDM_SOURCE_FILE = "CCDMCORR.dat";
    public static final String WCT_SOURCE_FILE = "dataWCT.dat";
    public static final String INT4_SOURCE_FILE = "dataINT4.txt";
    public static final String ILB_RESULT_FILE = "ILB.txt";
}
