package ILBprocessing;

import ILBprocessing.beans.*;
import lib.model.StarSystem;
import lib.service.SplitRule;
import lib.tools.*;
import ILBprocessing.configuration.NotesInterpreter;

import java.io.*;
import java.util.ArrayList;
import ILBprocessing.configuration.SharedConstants;

public class WDSparser implements SharedConstants{
    public static int iter = 0;
    public static ArrayList<NodeWDS> listWDS = new ArrayList<NodeWDS>();
    public static ArrayList<NodeORB6> listSCO = new ArrayList<NodeORB6>();
    public static ArrayList<NodeCCDM> listCCDM = new ArrayList<NodeCCDM>();
    public static ArrayList<NodeTDSC> listTDSC = new ArrayList<NodeTDSC>();

    public static ArrayList<NodeCCDMcoords> listCCDMcoords = new ArrayList<NodeCCDMcoords>();
    public static ArrayList<NodeINT4> listINT4 = new ArrayList<NodeINT4>();
    public static ArrayList<NodeCEV> listCEV = new ArrayList<NodeCEV>();
    public static ArrayList<NodeGCVS> listGCVS = new ArrayList<NodeGCVS>();
    public static ArrayList<NodeSB9> listSB9 = new ArrayList<NodeSB9>();
    public static ArrayList<NodeSB9main> listSB9main = new ArrayList<NodeSB9main>();

    public static ArrayList<StarSystem> sysList = new ArrayList<StarSystem>();

    public static void main(String[] args) {
       // System.out.println("parse WDSNotes");
       // new WDSNotesParser().mainParserNotes("D:/wdsnewnotes_main.txt");

        //System.out.println("parse not splitted catalogs");

        ParserFactory.parseSCO();
        System.out.println("parse SCO");

        /* System.doNotShowBcsResolved.println("parse TDSC");
        ParserFactory.parseTDSC();
        System.doNotShowBcsResolved.println("parse GCVS");
        ParserFactory.parseGCVS();
        System.doNotShowBcsResolved.println("parse CEV");
        ParserFactory.parseCEV();
        System.doNotShowBcsResolved.println("parse SB9");
        ParserFactory.parseSB9();
        System.doNotShowBcsResolved.println("parse CCDMcoords()");
        ParserFactory.parseCCDMcoords();
        */
        System.out.println(" ");
        System.out.println("Splitting large files on 10min-zones");
        split();

        System.out.println("Start processing...");
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.println("zone " + i + "h" + j + "(" + (i * 6 + j) + " from 144 (each zone - 10min))");
                iteration("" + i + j);
                System.out.println("");
                System.out.println("");
            }
        }
        BigFilesSplitterByHours.concatenator();
        try {
            //fix();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("All coordinatesFromWDSasString saved. Thread terminated");

        System.err.println(listSCO.size());
        for(int i=0;i<listSCO.size();i++){
            System.err.println(""+listSCO.get(i).idWDS+" "+listSCO.get(i).idWDS2akaDD+" "+listSCO.get(i).idDM);
        }
    }

    public static void split(){
        if(WDS_SPLIT_BEFORE_PROCESSING) {
            SplitRule splitRule = (string, hour, decade) -> {
                if(Integer.parseInt("" + string.charAt(0) + string.charAt(1) + string.charAt(2)) == hour * 10 + decade){
                    return true;
                }
                return false;
            };
            BigFilesSplitterByHours.splitLargeFile(WDS_SOURCE_FILES,WDS_GENERATED_STUFF,splitRule);
            System.out.println("WDS spliting finished");
        }

        if(CCDM_SPLIT_BEFORE_PROCESSING) {
            SplitRule splitRule = (string, hour, decade) -> {
                if(Integer.parseInt("" + string.charAt(1) + string.charAt(2) + string.charAt(3)) == hour * 10 + decade){
                    return true;
                }
                return false;
            };
            BigFilesSplitterByHours.splitLargeFile(CCDM_SOURCE_FILES,CCDM_GENERATED_STUFF,splitRule);
            System.out.println("CCDM spliting finished");
        }
        if(WCT_SPLIT_BEFORE_PROCESSING) {
            SplitRule splitRule = (string, hour, decade) -> {
                try {
                    if (("" + string.charAt(33) + string.charAt(34)).equals("\"\"") && Integer.parseInt("" + string.charAt(50) + string.charAt(51) + string.charAt(52)) == hour * 10 + decade || (""+string.charAt(50) + string.charAt(51)).equals("\"\"") && Integer.parseInt("" + string.charAt(33) + string.charAt(34) + string.charAt(35)) == hour * 10 + decade) {
                        return true;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
                return false;
            };
            BigFilesSplitterByHours.splitLargeFile(WCT_SOURCE_FILES,WCT_GENERATED_STUFF,splitRule);
            System.out.println("WCT spliting finished");
        }
        if(INT4_SPLIT_BEFORE_PROCESSING) {
            SplitRule splitRule = (string, hour, decade) -> {
                try {
                    if (string.length()>2 && string.charAt(0)!=' ' && Integer.parseInt("" + string.charAt(0) + string.charAt(1) + string.charAt(2)) == hour * 10 + decade) {
                        return true;
                    }
                }catch(Exception e){
                    System.err.print("ERR16: extra line: "+string);
                }
                return false;
            };
            BigFilesSplitterByHours.splitLargeFile(INT4_SOURCE_FILES,INT4_GENERATED_STUFF,splitRule);
            System.out.println("INT4 spliting finished");
        }
    }

    public static void iteration(String i) {
        init();
        solve(i);
        StatisticsCollector.print();
    }

    public static void init() {
        listWDS = new ArrayList<NodeWDS>();
        listCCDM = new ArrayList<NodeCCDM>();
        sysList = new ArrayList<StarSystem>();
        listTDSC = new ArrayList<NodeTDSC>();
        listINT4 = new ArrayList<NodeINT4>();
    }

    public static void solve(String i) {
        ParserFactory.parseFile(i);
        InterpreterFactory.interpr();
        NotesInterpreter.interpreteNotes();
        listWDS.clear();

        System.out.println("Processing..");
        InterpreterFactory.interprSCO();
/*
        ParserFactory.parseWCT(i);
        ParserFactory.parseINT4(i);
        ParserFactory.parseCCDM(i);

        InterpreterFactory.predInterprCCDM();
        InterpreterFactory.interprCCDMr();
        InterpreterFactory.interprCCDMcoords();
        listCCDM.clear();

        InterpreterFactory.interprTDSC();
        InterpreterFactory.interprINT4();
        InterpreterFactory.interprCEV();
        InterpreterFactory.interprSB9(i);
*/
        if(REMOVE_DUPLICATED_ENTITIES) {
            EntitiesListTools.removeDuplicatedEntities(sysList);
        }
        restructNamesForComponents();
        CustomWriter.write(i);
    }



    public static void restructNamesForComponents() {
        for (int k = 0; k < sysList.size(); k++) {
            for (int j = 0; j < sysList.get(k).pairs.size(); j++) {
                int a = (int) sysList.get(k).pairs.get(j).el1.hash();
                int b = (int) sysList.get(k).pairs.get(j).el2.hash();
                if (!sysList.get(k).hash.containsKey(a)) {
                    sysList.get(k).hash.put(a, sysList.get(k).pairs.get(j).el1.nameInILB);
                }
                if (!sysList.get(k).hash.containsKey(b)) {
                    sysList.get(k).hash.put(b, sysList.get(k).pairs.get(j).el2.nameInILB);
                }
            }
            ArrayList<Integer> cacheHash = new ArrayList<Integer>(sysList.get(k).hash.keySet());
            cacheHash = ConverterFINALIZED.sortByValue(sysList.get(k).hash);
            ArrayList<String> names = new ArrayList<String>();
            for (int i = 0; i < cacheHash.size(); i++) {
                names.add(sysList.get(k).hash.get(cacheHash.get(i)));
                //System.doNotShowBcsResolved.print(names.get(i)+"_");
            }

            for (int j = 0; j < sysList.get(k).pairs.size(); j++) {
                sysList.get(k).pairs.get(j).el1.newName = "" + (names.indexOf(sysList.get(k).pairs.get(j).el1.nameInILB) + 1);
                sysList.get(k).pairs.get(j).el2.newName = "" + (names.indexOf(sysList.get(k).pairs.get(j).el2.nameInILB) + 1);
                try {
                    if ((names.indexOf(sysList.get(k).pairs.get(j).el1.nameInILB.substring(0, sysList.get(k).pairs.get(j).el1.nameInILB.length() - 1)) + 1) != 0) {
                        sysList.get(k).pairs.get(j).surname = "" + (names.indexOf(sysList.get(k).pairs.get(j).el1.nameInILB.substring(0, sysList.get(k).pairs.get(j).el1.nameInILB.length() - 1)) + 1);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static void fix() throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        String fileName = "ILB.txt";
        File dataFile = new File("C:/WDSparser/" + fileName);
        FileReader in = new FileReader(dataFile);
        char c;
        String s = "";
        long x = dataFile.length();
        for (long i = 0; i < x; i++) {
            c = (char) in.read();
            s += c;
            if (c == 10 || c == 13) {
                s = s.substring(0, s.length() - 1);
                data.add(s);
                s = "";
            }
        }
        System.out.println("half");
        for (int i = 0; i < data.size(); i++) {
            for (int j = i - 50; j < i; j++) {
                try {
                    if (data.get(i).substring(0, 28) == (data.get(j).substring(0, 28))) {
                        data.remove(i);
                        System.out.println(i + "AHTUNG!!!" + j);
                    } else if (data.get(i).contains("!")) {
                        data.remove(i);
                        System.out.println(i + "AHTUNG!!!" + j);
                    }
                } catch (Exception e) {
                    System.out.println(i + " exception ij " + j);
                }
            }
        }
        try {
            String fileName2 = "dataComplete.txt";
            Writer outer = new FileWriter(new File("C:/WDSparser/" + fileName2));
            for (int k = 0; k < data.size(); k++) {
                outer.write(data.get(k) + "" + (char) 10);
                outer.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean nameReal(int k, int j, int stage) {
        if (sysList.get(k).pairs.get(j).pairCCDM == "ZZ") {
            return false;
        }
        if (sysList.get(k).coordinatesNotFoundInWDS) {
            //return false;
        }
        if (stage == 2 && sysList.get(k).pairs.get(j).el1.nameInILB.length() > 1) {
            String a = sysList.get(k).pairs.get(j).el1.nameInILB;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > 64 && a.charAt(i) < 91) {
                } else {
                    return true;
                }
            }
        } else if (stage == 3 && sysList.get(k).pairs.get(j).el2.nameInILB.length() > 1) {
            String a = sysList.get(k).pairs.get(j).el2.nameInILB;
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) > 64 && a.charAt(i) < 91) {
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
        return true;
    }
}
/*
	@Deprecated
	private static void outputCCDM(){
		int zz=listCCDM.size();
		for(int i=0;i<zz;i++){
			//if(listCCDM.get(i).astrometric){
			if(true){
				//System.doNotShowBcsResolved.print(listCCDM.get(i).ADS);
				for(int z=0;z<10-listCCDM.get(i).ADS.length();z++){
					//System.doNotShowBcsResolved.print(" ");
				}
				//System.doNotShowBcsResolved.print(listCCDM.get(i).astrometric);
				//System.doNotShowBcsResolved.print(listCCDM.get(i).DM);
				for(int z=0;z<10-listCCDM.get(i).DM.length();z++){
					//System.doNotShowBcsResolved.print(" ");
				}
				//System.doNotShowBcsResolved.print(listCCDM.get(i).HD);
				for(int z=0;z<10-listCCDM.get(i).HD.length();z++){
					//System.doNotShowBcsResolved.print(" ");
				}
				//System.doNotShowBcsResolved.print(listCCDM.get(i).HIP);
				for(int z=0;z<10-listCCDM.get(i).HIP.length();z++){
					//System.doNotShowBcsResolved.print(" ");
				}
				//System.doNotShowBcsResolved.print(listCCDM.get(i).idsID);
				for(int z=0;z<10-listCCDM.get(i).idsID.length();z++){
					//System.doNotShowBcsResolved.print(" ");
				}
				//System.doNotShowBcsResolved.print(listCCDM.get(i).wdsID);
				for(int z=0;z<10-listCCDM.get(i).wdsID.length();z++){
					//System.doNotShowBcsResolved.print(" ");
				}
				//System.doNotShowBcsResolved.println(listCCDM.get(i).nameOfObserver);
			}
		}
	}
	@Deprecated
	private static void outputTDSC(){
		int zz=listTDSC.size();
//		for(int i=0;i<zz;i++){
//			if(true){
//				//System.doNotShowBcsResolved.print(listTDSC.get(i).CCDMidPAIR);
//				for(int z=0;z<10-listTDSC.get(i).CCDMidPAIR.length();z++){
//					//System.doNotShowBcsResolved.print(" ");
//				}
//				//System.doNotShowBcsResolved.print(listTDSC.get(i).pairTDSC);
//				for(int z=0;z<3-listTDSC.get(i).pairTDSC.length();z++){
//					//System.doNotShowBcsResolved.print(" ");
//				}
//				//System.doNotShowBcsResolved.print(listTDSC.get(i).HD);
//				for(int z=0;z<10-listTDSC.get(i).HD.length();z++){
//					//System.doNotShowBcsResolved.print(" ");
//				}
//				//System.doNotShowBcsResolved.print(listTDSC.get(i).HIPid);
//				for(int z=0;z<10-listTDSC.get(i).HIPid.length();z++){
//					//System.doNotShowBcsResolved.print(" ");
//				}
//				System.doNotShowBcsResolved.print(listTDSC.get(i).TDSCid);
//				for(int z=0;z<10-listTDSC.get(i).TDSCid.length();z++){
//					System.doNotShowBcsResolved.print(" ");
//				}
//				System.doNotShowBcsResolved.print(listTDSC.get(i).WDSid);
//				for(int z=0;z<10-listTDSC.get(i).WDSid.length();z++){
//					System.doNotShowBcsResolved.print(" ");
//				}
//				System.doNotShowBcsResolved.println();
//			}
//		}
	}
	@Deprecated
	private static String clearify(String ss){
		String s=ss;
		for(int i=0;i<s.length();i++){
			char c= s.charAt(i);
			for(int j=i+1;j<s.length();j++){
				if(s.charAt(j)==c){
					s=s.substring(0,j)+s.substring(j+1,s.length());
					j--;
					i=-1;
					break;
				}
			}
		}
		return s;
	}
	@Deprecated
	private static void interprWDS2() throws IOException{
		String fileName="logExtraPairsInWDS.txt";
		Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
		for(int i = 0; i< sysList.size(); i++){
			ArrayList<String> names = new ArrayList<String>();
			for(int j = 0; j< sysList.get(i).pairs.size(); j++){
				String comp1= sysList.get(i).pairs.get(j).el1.nameInILB;
				String comp2= sysList.get(i).pairs.get(j).el2.nameInILB;
				boolean comp1exists=false;
				boolean comp2exists=false;
				for(int k = 0;k<names.size();k++){
					if(comp1.equals(names.get(k))){
						comp1exists=true;
					}
					if(comp2.equals(names.get(k))){
						comp2exists=true;
					}
				}
				if(comp1exists==false){
					names.add(comp1);
				}
				if(comp2exists==false){
					names.add(comp2);
				}
				if(comp1exists==true && comp2exists==true){
					outer2.append(sysList.get(i).idWDS.substring(0, 11)+"_"+ sysList.get(i).pairs.get(j).pairIdILB+(char)(10));
					outer2.flush();
				}
			}
		}
	}
	@Deprecated
	private static void interprCCDM2() throws IOException{
		String fileName="logEx2.txt";
		
		Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
		int h= sysList.size();
		for(int i=0;i<h;i++){
			for(int j=0; j<h;j++){
				if(sysList.get(i).idWDS!="" && !sysList.get(i).idWDS.equals("          ") && sysList.get(i).idWDS.equals(sysList.get(j).idWDS)){
					if(sysList.get(i).CCDMid!=null && sysList.get(i).CCDMid!="" && sysList.get(j).CCDMid!=null && sysList.get(j).CCDMid!=""  && !sysList.get(i).CCDMid.equals(sysList.get(j).CCDMid)){
						outer2.append("WDS"+ sysList.get(i).idWDS+"CCDM1"+ sysList.get(j).CCDMid+"CCDM2"+ sysList.get(i).CCDMid+(char)(10));
						outer2.flush();
					}
				}
				if(sysList.get(i).CCDMid!=null && sysList.get(i).CCDMid!="" && sysList.get(i).CCDMid.equals(sysList.get(j).CCDMid)){
					if(sysList.get(i).idWDS!=null && sysList.get(i).idWDS!="" && !sysList.get(i).idWDS.equals(sysList.get(j).idWDS)){
						outer2.append("CCDM"+ sysList.get(i).CCDMid+"wds1"+ sysList.get(j).idWDS+"wds2"+ sysList.get(i).idWDS+(char)(10));
						outer2.flush();
					}
				}
			}
		}
	};
	@Deprecated
	private static void parseWCT2(){//"log2.txt"
		ArrayList<StringPair> str = new ArrayList<StringPair>();
		try { 	
				String fileName2="logWCT.txt";
				Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName2));
				String fileName="dataWCT.dat";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				int xLog=0;
				long timePrev=System.nanoTime();
				StringBuffer sss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					sss.append(c);
					if(c==10){
						String s=sss.toString();
						s=s.substring(0, s.length()-2);
						NodeWCT star = new NodeWCT(s);
						str.add(new StringPair(star.parameter[0],star.parameter[2]));
						//System.doNotShowBcsResolved.println("asd"+star.parameter[0]+star.parameter[2]);
						sss = new StringBuffer();
					}
				}
				System.doNotShowBcsResolved.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		int x =str.size();
		for(int i=0;i<x;i++){
			for(int j=i;j<x;j++){
				if(str.get(i).a!="" && str.get(i).a.equals(str.get(j).a)){
					if(!str.get(i).b.equals(str.get(j).b) && str.get(j).b!="" && str.get(i).b!=""){
						//System.doNotShowBcsResolved.print("DIFF_CCDM WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						//System.doNotShowBcsResolved.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
				if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).b)){
					if(!str.get(i).a.equals(str.get(j).a) && str.get(j).a!="" && str.get(i).a!=""){
						//System.doNotShowBcsResolved.print("DIFF_WDS  WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						//System.doNotShowBcsResolved.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
			}
		}
	}
	@Deprecated
	private static void parseWCT3(){//"log2.txt"
		ArrayList<StringPair> str = new ArrayList<StringPair>();
		try { 	
				String fileName2="logWCT.txt";
				Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName2));
				String fileName="dataWCT.dat";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				int xLog=0;
				long timePrev=System.nanoTime();
				StringBuffer sss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					sss.append(c);
					if(c==10){
						String s=sss.toString();
						s=s.substring(0, s.length()-2);
						NodeWCT star = new NodeWCT(s);
						str.add(new StringPair(star.parameter[0],star.parameter[2]));
						//System.doNotShowBcsResolved.println("asd"+star.parameter[0]+star.parameter[2]);
						sss = new StringBuffer();
					}
				}
				System.doNotShowBcsResolved.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		int x =str.size();
		for(int i=0;i<x;i++){
			for(int j=0;j<x;j++){
				level1: if(str.get(i).a!="" && str.get(i).a.equals(str.get(j).b) && i!=j){
					for(int k=0;k<x;k++){
						if(str.get(i).a.equals(str.get(k).a) && str.get(k).a.equals(str.get(k).b)){
							break level1;
						}
					}
					//System.doNotShowBcsResolved.println(str.get(i).a);
				}
			}
		}
		//System.doNotShowBcsResolved.println("half");
		for(int i=0;i<x;i++){
			for(int j=0;j<x;j++){
				level1: if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).a) && i!=j){
					for(int k=0;k<x;k++){
						if(str.get(i).b.equals(str.get(k).b) && str.get(k).b.equals(str.get(k).a)){
							break level1;
						}
					}
					//System.doNotShowBcsResolved.println(str.get(i).a);
				}
			}
		}
	}
	@Deprecated
	private static void interprTDSC2() throws IOException{
		int xLog=0;
		long timePrev=System.nanoTime();
		String fileName3="logTDSCerrs.txt";
		Writer outer3 = new FileWriter(new File("C:/WDSparser/"+fileName3));
		int gg= sysList.size();
		int hh=listTDSC.size();
		for(int i=0;i<gg;i++){
			for(int j=0;j<hh;j++){
				if(listTDSC.get(j).WDSid!=null && listTDSC.get(j).WDSid!=""){
					if(listTDSC.get(j).TDSCid.equals(sysList.get(i).TDSCid)){
						if(listTDSC.get(j).WDSid!="          " && !sysList.get(i).wdsSystemID.substring(0,10).equals(listTDSC.get(j).WDSid)){
							outer3.append(listTDSC.get(j).TDSCid+"_"+"WDSid in TDSC"+listTDSC.get(j).WDSid+"WDSid in WCT"+ sysList.get(i).wdsSystemID.substring(0,10)+(char)(10));
							outer3.flush();
						}
						break;
					}
					for(int h=0;h<sysList.get(i).pairs.size();h++){
						if(sysList.get(i).pairs.get(h).idTDSC.equals(listTDSC.get(j).TDSCid)){
							if(!sysList.get(i).wdsSystemID.substring(0,10).equals(listTDSC.get(j).WDSid)){
								outer2.append("true"+listTDSC.get(j).TDSCid+"_"+"WDSid in TDSC"+listTDSC.get(j).WDSid+"WDSid in WCT"+sysList.get(i).wdsSystemID.substring(0,10)+(char)(10));
								outer2.flush();
							}
						}
					}
				}
			}
			if(xLog*1000<=i){
				//System.doNotShowBcsResolved.println(i+"/"+gg+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
				timePrev=System.nanoTime();
				xLog=(int) (i/1000+1);	
			}
		}
	};

*/
