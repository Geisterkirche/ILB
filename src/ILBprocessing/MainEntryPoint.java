package ILBprocessing;

import ILBprocessing.beans.NodeCCDMPair;
import ILBprocessing.beans.NodeORB6FINALIZED;
import ILBprocessing.beans.NodeWDSFINALIZED;
import ILBprocessing.configuration.SharedConstants;
import lib.model.StarSystem;
import lib.model.service.SplitRule;
import lib.tools.BigFilesSplitterByHours;
import lib.tools.GlobalPoolOfIdentifiers;

import java.util.ArrayList;

public class MainEntryPoint implements SharedConstants{
    public static int iter = 0;
    public static ArrayList<NodeWDSFINALIZED> listWDS = new ArrayList<NodeWDSFINALIZED>();
    public static ArrayList<NodeORB6FINALIZED> listSCO = new ArrayList<NodeORB6FINALIZED>();
    public static ArrayList<NodeCCDMPair> listCCDMPairs = new ArrayList<NodeCCDMPair>();

    public static ArrayList<StarSystem> sysList = new ArrayList<StarSystem>();

    public static void main(String[] args) {
       // System.out.println("parse WDSNotes");
       // new MainEntryPoint().mainParserNotes("D:/wdsnewnotes_main.txt");

        ParserFactory.parseSCO();
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

     //   BigFilesSplitterByHours.concatenator();
        postProcessing();
    }

    public static void split(){
        System.out.println(" ");
        System.out.println("Splitting large files on 10min-zones");
        if(WDS_SPLIT_BEFORE_PROCESSING) {
            System.out.println("WDS splitting enabled");
            SplitRule splitRule = (string, hour, decade) -> {
                if(Integer.parseInt("" + string.charAt(0) + string.charAt(1) + string.charAt(2)) == hour * 10 + decade){
                    return true;
                }
                return false;
            };
            BigFilesSplitterByHours.splitLargeFile(WDS_SOURCE_FILES,WDS_GENERATED_STUFF,splitRule);
            System.out.println("WDS splitting finished");
        }
        if(CCDM_SPLIT_BEFORE_PROCESSING) {
            System.out.println("CCDM splitting enabled");
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
            System.out.println("WCT splitting enabled");
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
            System.out.println("WCT splitting finished");
        }
        if(INT4_SPLIT_BEFORE_PROCESSING) {
            System.out.println("INT4 splitting enabled");
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
            System.out.println("INT4 splitting finished");
        }
    }

    public static void iteration(String i) {
        init();
        solve(i);
        GlobalPoolOfIdentifiers.printCount();
    }
    public static void init() {
        listWDS = new ArrayList<NodeWDSFINALIZED>();
        sysList = new ArrayList<StarSystem>();
        listCCDMPairs = new ArrayList<NodeCCDMPair>();
    }
    public static void solve(String i) {
        ParserFactory.parseFile(i);
        ParserFactory.parseCCDM(i);

        InterpreterFactory.interprWDS();
        //NotesInterpreter.interpreteNotes();

        System.out.println("Processing..");
        InterpreterFactory.interprSCO();
/*
        ParserFactory.parseWCT(i);
        ParserFactory.parseINT4(i);
        *//*

        InterpreterFactory.predInterprCCDM();
        InterpreterFactory.interprCCDMr();
        InterpreterFactory.interprCCDMcoords();
        listCCDMComponents.clear();

        InterpreterFactory.interprTDSC();
        InterpreterFactory.interprINT4();
        InterpreterFactory.interprCEV();
        InterpreterFactory.interprSB9(i);
*/
        if(REMOVE_DUPLICATED_ENTITIES) {
           // EntitiesListTools.removeDuplicatedEntities(sysList);
        }
        CustomWriter.write(i);
    }

    public static void postProcessing(){

        System.err.println("fail on matching SCO nodes: "+listSCO.size());
        for(int i=0;i<listSCO.size();i++){
            System.err.println(""+listSCO.get(i).source);
        }
    }
}
/*
	@Deprecated
	private static void outputCCDM(){
		int zz=listCCDMComponents.size();
		for(int i=0;i<zz;i++){
			//if(listCCDMComponents.get(i).astrometric){
			if(true){
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).ADS);
				for(int z=0;z<10-listCCDMComponents.get(i).ADS.length();z++){
					//System.doNotShowBcsResolved.printCount(" ");
				}
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).astrometric);
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).DM);
				for(int z=0;z<10-listCCDMComponents.get(i).DM.length();z++){
					//System.doNotShowBcsResolved.printCount(" ");
				}
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).HD);
				for(int z=0;z<10-listCCDMComponents.get(i).HD.length();z++){
					//System.doNotShowBcsResolved.printCount(" ");
				}
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).HIP);
				for(int z=0;z<10-listCCDMComponents.get(i).HIP.length();z++){
					//System.doNotShowBcsResolved.printCount(" ");
				}
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).idsID);
				for(int z=0;z<10-listCCDMComponents.get(i).idsID.length();z++){
					//System.doNotShowBcsResolved.printCount(" ");
				}
				//System.doNotShowBcsResolved.printCount(listCCDMComponents.get(i).wdsID);
				for(int z=0;z<10-listCCDMComponents.get(i).wdsID.length();z++){
					//System.doNotShowBcsResolved.printCount(" ");
				}
				//System.doNotShowBcsResolved.println(listCCDMComponents.get(i).nameOfObserver);
			}
		}
	}
	@Deprecated
	private static void outputTDSC(){
		int zz=listTDSC.size();
//		for(int i=0;i<zz;i++){
//			if(true){
//				//System.doNotShowBcsResolved.printCount(listTDSC.get(i).CCDMidPAIR);
//				for(int z=0;z<10-listTDSC.get(i).CCDMidPAIR.length();z++){
//					//System.doNotShowBcsResolved.printCount(" ");
//				}
//				//System.doNotShowBcsResolved.printCount(listTDSC.get(i).pairTDSC);
//				for(int z=0;z<3-listTDSC.get(i).pairTDSC.length();z++){
//					//System.doNotShowBcsResolved.printCount(" ");
//				}
//				//System.doNotShowBcsResolved.printCount(listTDSC.get(i).HD);
//				for(int z=0;z<10-listTDSC.get(i).HD.length();z++){
//					//System.doNotShowBcsResolved.printCount(" ");
//				}
//				//System.doNotShowBcsResolved.printCount(listTDSC.get(i).HIPid);
//				for(int z=0;z<10-listTDSC.get(i).HIPid.length();z++){
//					//System.doNotShowBcsResolved.printCount(" ");
//				}
//				System.doNotShowBcsResolved.printCount(listTDSC.get(i).TDSCid);
//				for(int z=0;z<10-listTDSC.get(i).TDSCid.length();z++){
//					System.doNotShowBcsResolved.printCount(" ");
//				}
//				System.doNotShowBcsResolved.printCount(listTDSC.get(i).WDSid);
//				for(int z=0;z<10-listTDSC.get(i).WDSid.length();z++){
//					System.doNotShowBcsResolved.printCount(" ");
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
		Writer outer2 = new FileWriter(new File("C:/MainEntryPoint/"+fileName));
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
		
		Writer outer2 = new FileWriter(new File("C:/MainEntryPoint/"+fileName));
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
				Writer outer2 = new FileWriter(new File("C:/MainEntryPoint/"+fileName2));
				String fileName="dataWCT.dat";
				File dataFile = new File("C:/MainEntryPoint/"+fileName);
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
						//System.doNotShowBcsResolved.printCount("DIFF_CCDM WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						//System.doNotShowBcsResolved.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
				if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).b)){
					if(!str.get(i).a.equals(str.get(j).a) && str.get(j).a!="" && str.get(i).a!=""){
						//System.doNotShowBcsResolved.printCount("DIFF_WDS  WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
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
				Writer outer2 = new FileWriter(new File("C:/MainEntryPoint/"+fileName2));
				String fileName="dataWCT.dat";
				File dataFile = new File("C:/MainEntryPoint/"+fileName);
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
		Writer outer3 = new FileWriter(new File("C:/MainEntryPoint/"+fileName3));
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
						if(sysList.get(i).pairs.get(h).systemTDSC.equals(listTDSC.get(j).TDSCid)){
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
