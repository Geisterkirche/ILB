package WDSNotesParser;

/**
 * Dictionary for WDSNotes parser module
 */
public interface Dictionary {
    String IS = "is";
    String AKA = "aka";

    String NOT = "not";
    String NO = "not";
    String NEITHER = "neither";

    String AND = "and";
    String OR = "or";
    String VELOCITY = "velocity";

    @Deprecated
    String PROPER = "proper";
    @Deprecated
    String MOTION = "motion";

    String ECLIPSING = "eclipsing";
    String SPECTROSCOPIC = "spectroscopic";
    String SPECTROSCOPIC_BINARY = "SB";
    String VARIABLE = "variable";
    String SEMIREGULAR = "semiregular";
    String BINARY = "binary";
    String OPTICAL = "optical";

    String THIS = "this";
    String PRIMARY = "primary";
    String PAIR = "pairNameXXXXXfromWDS";
    String SECONDARY = "secondary";
    String PRINCIPAL = "principal";

    String COMPONENT = "component";
    String STAR = "star";
}
