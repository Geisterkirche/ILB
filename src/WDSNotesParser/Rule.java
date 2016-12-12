package WDSNotesParser;


import java.util.List;

/**
 * Created by Алекс on 27.07.2016.
 */
public class Rule {
    public static void ruleForIs(List<String> list){
        try {
            int k = list.indexOf(Dictionary.IS);
            String object = checkForCandidate(list.subList(0,k));
            if(object==""){
                return;
            }
            String knownProperty = containsProperty(list.subList(k+1, list.size()));
            if(!knownProperty.equals("")){
                boolean yORn=checkForNot(list);
                Base.addCast(object,knownProperty,yORn,WDSNotesParser.currentSystem,WDSNotesParser.currentPair);
            }
        }catch (Exception e){
            System.err.println("///////////////////");
            e.printStackTrace();
            System.err.println("///////////////////");
            System.err.println(list);
            System.err.println("///////////////////");
        }
    }
    public static void ruleForAka(List<String> list){
        try {
            int k = list.indexOf(Dictionary.AKA);
            if(k==1){
                if(list.size()>3){
                    Base.addEquation(list.get(2).toUpperCase()+" "+list.get(3).toUpperCase(),WDSNotesParser.currentSystem,WDSNotesParser.currentPair);
                }else if(list.size()>2){
                    Base.addEquation(list.get(2).toUpperCase(),WDSNotesParser.currentSystem,WDSNotesParser.currentPair);
                }
            }
        }catch (Exception e){
            System.err.println("///////////////////");
            e.printStackTrace();
            System.err.println("///////////////////");
            System.err.println(list);
            System.err.println("///////////////////");
        }
    }
    private static String containsProperty(List<String> list){
        String result="";
        for(String property:list){
            if(property.equals(Dictionary.BINARY)){
                result=" "+Dictionary.BINARY;
            }
            if(property.equals(Dictionary.ECLIPSING)){
                result+=" "+Dictionary.ECLIPSING;
            }
            if(property.equals(Dictionary.SPECTROSCOPIC)){
                result+=" "+Dictionary.SPECTROSCOPIC;
            }
            if(property.equals(Dictionary.SPECTROSCOPIC_BINARY)){
                result+=" "+Dictionary.SPECTROSCOPIC_BINARY;
            }
            if(property.equals(Dictionary.SEMIREGULAR)){
                result+=" "+Dictionary.SEMIREGULAR;
            }
            if(property.equals(Dictionary.VARIABLE)){
                result+=" "+Dictionary.VARIABLE;
                if(list.size()-list.indexOf(property)>2){
                    if(checkIfParasite(list.get(list.indexOf(property)+1)) && checkIfParasite(list.get(list.indexOf(property)+2))){
                        result+="["+list.get(list.indexOf(property)+1).toUpperCase()+" "+list.get(list.indexOf(property)+2).toUpperCase()+"]";
                    }
                }
            }
            if(property.equals(Dictionary.OPTICAL)){
                result+=" "+Dictionary.OPTICAL;
            }
        }
        return result;
    }
    public static String toLowerCase(String word){
        if(word.length()!=0 && word.charAt(word.length()-1)==','){
            word=word.substring(0,word.length()-1);
        }
        if(word.length()!=0 && word.charAt(word.length()-1)==';'){
            word=word.substring(0,word.length()-1);
        }
        if(word.toLowerCase().equals(Dictionary.IS)){
            return word.toLowerCase();
        }
        if(word.contains(":")){
            return word;
        }
        if(word.length()<2){
            return word;
        }

        return word.toLowerCase();
    }
    public static boolean checkForNot(List<String> list){
        for(String property:list) {
            if (property.equals(Dictionary.NO)) {
                return false;
            }
            if (property.equals(Dictionary.NEITHER)) {
                return false;
            }
            if (property.equals(Dictionary.NOT)) {
                return false;
            }
        }
        return true;
    }
    public static boolean checkIfParasite(String s){
        if(s.equals(Dictionary.STAR)){
            return false;
        }
        if(s.equals(Dictionary.AND)){
            return false;
        }
        if(s.equals(Dictionary.OR)){
            return false;
        }
        if(s.equals(Dictionary.COMPONENT)){
            return false;
        }
        if(s.equals(Dictionary.VELOCITY)){
            return false;
        }
        return true;
    }
    public static String checkForCandidate(List<String> list){
        String candidate="";
        for(int i=list.size()-1;i>=0;i--) {
            String property=list.get(i);
            if (property.equals(Dictionary.THIS)) {
                return property;
            }
            if (property.equals(Dictionary.SECONDARY)) {
                return property;
            }
            if (property.equals(Dictionary.PRIMARY)) {
                return property;
            }
            if (property.equals(Dictionary.PRINCIPAL)) {
                return property;
            }
            if (property.equals(Dictionary.PAIR)) {
                return property;
            }
            if(property.contains(",") && property.length()<6 && property.length()>3 && (property.charAt(1)==',' || property.charAt(2)==',') ){
                return property;
            }
            if(property.length()<3 && !property.toLowerCase().equals(property)){
                return property;
            }
        }
        return candidate;
    }
}
