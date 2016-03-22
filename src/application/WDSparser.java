package application;

import datasources.*;
import structureEntities.*;
import tools.*;
import tools.additionalEntities.StringPair;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;

public class WDSparser{
	public static int iter=0;
	public static ArrayList<NodeWDS> globalList = new ArrayList<NodeWDS>();
	public static ArrayList<NodeSCO> listSCO = new ArrayList<NodeSCO>();
	public static ArrayList<NodeCCDM> listCCDM = new ArrayList<NodeCCDM>();
	public static ArrayList<NodeTDSC> listTDSC = new ArrayList<NodeTDSC>();

	public static ArrayList<NodeCCDMcoords> listCCDMcoords = new ArrayList<NodeCCDMcoords>();
	public static ArrayList<NodeINT4> listINT4 = new ArrayList<NodeINT4>();
	public static ArrayList<NodeCEV> listCEV = new ArrayList<NodeCEV>();
	public static ArrayList<NodeGCVS> listGCVS = new ArrayList<NodeGCVS>();
	public static ArrayList<NodeSB9> listSB9 = new ArrayList<NodeSB9>();
	public static ArrayList<NodeSB9main> listSB9main = new ArrayList<NodeSB9main>();
	public static ArrayList<NodeHMXB> listHMXB = new ArrayList<NodeHMXB>();
	public static ArrayList<NodeLMXB> listLMXB = new ArrayList<NodeLMXB>();
	
	public static ArrayList<StarSystem> system= new ArrayList<StarSystem>();
	public static void main(String[] args){
		System.out.println("parse SCO");
		ParserFactory.parseSCO();
		System.out.println("parse TDSC");
		ParserFactory.parseTDSC();
		System.out.println("parse CEV");
		ParserFactory.parseCEV();
		System.out.println("parse GCVS");
		ParserFactory.parseGCVS();
		System.out.println("parse SB9");
		ParserFactory.parseSB9();
		System.out.println("parse CCDMcoords()");
		ParserFactory.parseCCDMcoords();

		BigFilesSplitterByHours.split();

		for(int i=0;i<24;i++){
			for(int j=0;j<6;j++){
				iteration(""+i+j);
				System.out.println(""+i+j);
			}
		}
		BigFilesSplitterByHours.concatenator();
		try{
			fix();
		}catch(Exception e ){
			e.printStackTrace();
		}
		System.out.println("All data saved. Thread terminated");
	}
	public static void iteration(String i){
		init();
		solve(i);
		StatisticsCollector.print();
	}
	public static void init(){
		globalList = new ArrayList<NodeWDS>();
		listCCDM = new ArrayList<NodeCCDM>();
		system= new ArrayList<StarSystem>();
		listTDSC = new ArrayList<NodeTDSC>();
		listINT4 = new ArrayList<NodeINT4>();
	}
	public static void solve(String i){
		ParserFactory.parseFile(i);
		InterpreterFactory.interpr();
		globalList.clear();
		
		System.out.println("Processing..");
		InterpreterFactory.interprSCO();

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

		collapse();
		restructNamesForComponents();
		CustomWriter.write(i);
	}
	public static void restructNamesForComponents(){
		for(int k=0;k<system.size();k++){
			for(int j=0;j<system.get(k).pairs.size();j++){
				int a=system.get(k).pairs.get(j).el1.hash();
				int b=system.get(k).pairs.get(j).el2.hash();
				system.get(k).hash.add(a);
				system.get(k).hash.add(b);
			}
			Collections.sort(system.get(k).hash);
			for(int i=1;i<system.get(k).hash.size();i++){
				if(system.get(k).hash.get(i-1).equals(system.get(k).hash.get(i))){
					system.get(k).hash.remove(i);
					i--;
				}
			}
			ArrayList<Integer> cacheHash = new ArrayList<Integer>();
			for(int i=0;i<system.get(k).hash.size();i++){
				if(!cacheHash.contains(system.get(k).hash.get(i))){
					cacheHash.add(system.get(k).hash.get(i));
				}
			}
			for(int h=0;h<system.get(k).pairs.size();h++){
				l1: for(int i=0;i<system.get(k).hash.size();i++){
					if(system.get(k).pairs.get(h).el1.name.length()>1){
						boolean ext=false;
						for(int z=0;z<26;z++){
							if(system.get(k).pairs.get(h).el1.name.contains(""+(char)('a'+z))){
								ext=true;
								break;
							}
						}
						if(ext){
							system.get(k).pairs.get(h).el1.newName=parent(k,cacheHash,system.get(k).pairs.get(h).el1.name.substring(0,system.get(k).pairs.get(h).el1.name.length()-1))+'A';
						}else{
							if(system.get(k).pairs.get(h).el1.hash()==cacheHash.get(i)){
								system.get(k).pairs.get(h).el1.newName=""+(i+1);
							}
						}
					}else {
						for(int ll=0;ll<26;ll++){
							for(int kk=0;kk<cacheHash.size();kk++){
								if(Component.hash(system.get(k).pairs.get(h).el1.name+(char)('A'+ll))==cacheHash.get(kk)){
									system.get(k).pairs.get(h).el1.newName=""+(kk+1)+"A";
									break l1;
								}
							}
						}
						for(int ll=0;ll<26;ll++){
							for(int kk=0;kk<cacheHash.size();kk++){
								if(Component.hash((char)('A'+ll)+system.get(k).pairs.get(h).el1.name)==cacheHash.get(kk)){
									system.get(k).pairs.get(h).el1.name=""+(kk+1)+"B";
									break l1;
								}
							}
						}
						for(int j=0;j<cacheHash.size();j++){
							if(Component.hash(system.get(k).pairs.get(h).el1.name)==cacheHash.get(j)){
								system.get(k).pairs.get(h).el1.newName=""+(j+1);
							}
						}
					}
				}
				l2: for(int i=0;i<system.get(k).hash.size();i++){
					
					if(system.get(k).pairs.get(h).el2.name.length()>1){
						boolean ext=false;
						for(int z=0;z<26;z++){
							if(system.get(k).pairs.get(h).el2.name.contains(""+(char)('a'+z))){
								ext=true;
								break;
							}
						}
						if(ext){
							system.get(k).pairs.get(h).el2.newName=parentB(k,cacheHash,system.get(k).pairs.get(h).el2.name.substring(0,system.get(k).pairs.get(h).el2.name.length()-1))+'B';
						}else{
							if(system.get(k).pairs.get(h).el2.hash()==cacheHash.get(i)){
								system.get(k).pairs.get(h).el2.newName=""+(i+1);
							}
						}
					}else if(system.get(k).pairs.get(h).el2.hash()==cacheHash.get(i)){
						for(int ll=0;ll<26;ll++){
							for(int kk=0;kk<cacheHash.size();kk++){
								if(Component.hash(system.get(k).pairs.get(h).el2.name+(char)('A'+ll))==cacheHash.get(kk)){
									system.get(k).pairs.get(h).el2.newName=""+(kk+1)+"A";
									break l2;
								}
							}
						}
						for(int ll=0;ll<26;ll++){
							for(int kk=0;kk<cacheHash.size();kk++){
								if(Component.hash((char)('A'+ll)+system.get(k).pairs.get(h).el2.name)==cacheHash.get(kk)){
									system.get(k).pairs.get(h).el2.newName=""+(kk+1)+"B";
									break l2;
								}
							}
						}
						for(int j=0;j<cacheHash.size();j++){
							if(Component.hash(system.get(k).pairs.get(h).el2.name)==cacheHash.get(j)){
								system.get(k).pairs.get(h).el2.newName=""+(j+1);
							}
						}
						break l2;
					}
				}
			}
		}
	}
	public static String parent(int k, ArrayList<Integer> cacheHash, String a){
			for(int i=0;i<system.get(k).hash.size();i++){
				if(a.length()>1){
					boolean ext=false;
					for(int z=0;z<26;z++){
						if(a.contains(""+(char)('a'+z))){
							ext=true;
							break;
						}
					}
					if(ext){
						a=parent(k,cacheHash,a.substring(0,a.length()-1))+'A';
						return a;
					}else{
						if(Component.hash(a)==cacheHash.get(i)){
							a=""+(i+1);
						}
					}
				}else if(Component.hash(a)==cacheHash.get(i)){
					for(int ll=0;ll<26;ll++){
						for(int kk=0;kk<cacheHash.size();kk++){
							if(Component.hash(a+(char)('A'+ll))==cacheHash.get(kk)){
								a=""+(kk+1)+"A";
								return a;
							}
						}
					}
					for(int ll=0;ll<26;ll++){
						for(int kk=0;kk<cacheHash.size();kk++){
							if(Component.hash((char)('A'+ll)+a)==cacheHash.get(kk)){
								a=""+(kk+1)+"B";
								return a;
							}
						}
					}
					for(int j=0;j<cacheHash.size();j++){
						if(Component.hash(a)==cacheHash.get(j)){
							a=""+(j+1);
						}
					}
					return a;
				}
			}
		//System.out.print("FASLDLAFSLASF");
		return "";
	}
	public static String parentB(int k, ArrayList<Integer> cacheHash, String a){
		for(int i=0;i<system.get(k).hash.size();i++){
			if(a.length()>1){
				boolean ext=false;
				for(int z=0;z<26;z++){
					if(a.contains(""+(char)('a'+z))){
						ext=true;
						break;
					}
				}
				if(ext){
					a=parent(k,cacheHash,a.substring(0,a.length()-1))+'A';
					return a;
				}else{
					if(Component.hash(a)==cacheHash.get(i)){
						a=""+(i+1);
					}
				}
			}else if(Component.hash(a)==cacheHash.get(i)){
				for(int ll=0;ll<26;ll++){
					for(int kk=0;kk<cacheHash.size();kk++){
						if(Component.hash(a+(char)('A'+ll))==cacheHash.get(kk)){
							a=""+(kk+1)+"A";
							return a;
						}
					}
				}
				for(int ll=0;ll<26;ll++){
					for(int kk=0;kk<cacheHash.size();kk++){
						if(Component.hash((char)('A'+ll)+a)==cacheHash.get(kk)){
							a=""+(kk+1)+"B";
							return a;
						}
					}
				}
				for(int j=0;j<cacheHash.size();j++){
					if(Component.hash(a)==cacheHash.get(j)){
						a=""+(j+1);
					}
				}
				return a;
			}
		}
	//System.out.println("FASLDLAFSLASF");
	return "";
}

	public static void collapse(){
		for(int i=0;i<system.size();i++){
			for(int j=0;j<system.get(i).pairs.size();j++){
				for(int k=0;k<j;k++){
					if(system.get(i).pairs.get(k).el1.name.equals(system.get(i).pairs.get(j).el1.name)){
						system.get(i).pairs.get(j).el1.out=true;
					}else if(system.get(i).pairs.get(k).el1.name.equals(system.get(i).pairs.get(j).el2.name)){
						system.get(i).pairs.get(j).el2.out=true;
					}
					if(system.get(i).pairs.get(k).el2.name.equals(system.get(i).pairs.get(j).el1.name)){
						system.get(i).pairs.get(j).el1.out=true;
					}else if(system.get(i).pairs.get(k).el2.name.equals(system.get(i).pairs.get(j).el2.name)){
						system.get(i).pairs.get(j).el2.out=true;
					}
				}
			}
		}
	}
	public static void fix() throws IOException{
		ArrayList<String> data=new ArrayList<String>();
		String fileName="ILB.txt";
		File dataFile = new File("C:/WDSparser/"+fileName);	
		FileReader in = new FileReader(dataFile);
		char c;
		String s="";
		long x=dataFile.length();
		for(long i=0;i<x;i++){
			c = (char) in.read();
			s+=c;
			if(c==10 || c==13){
				s=s.substring(0, s.length()-1);
				data.add(s);
				s="";
			}
		}
		System.out.println("half");
		for(int i=0;i<data.size();i++){
			for(int j=i-50;j<i;j++){
				try{
					if(data.get(i).substring(0,28)==(data.get(j).substring(0,28))){
						data.remove(i);
					} else if(data.get(i).contains("!")){
						data.remove(i);
					}
				}catch(Exception e){
					System.out.println(i+" exception ij "+j);
				}
			}
		}
		try{
			String fileName2="dataComplete.txt";
			Writer outer = new FileWriter(new File("C:/WDSparser/"+fileName2));
			for(int k=0;k<data.size();k++){
				outer.write(data.get(k)+""+(char)10);
				outer.flush();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static boolean nameReal(int k,int j, int stage){
		if(system.get(k).pairs.get(j).pairCCDM=="ZZ"){
			return false;
		}
		if(system.get(k).fake){
			//return false;
		}
		if(stage==2 && system.get(k).pairs.get(j).el1.name.length()>1){
			String a = system.get(k).pairs.get(j).el1.name;
			for(int i=0;i<a.length();i++){
				if(a.charAt(i)>64 && a.charAt(i)<91){
				}else{
					return true;
				}
			}
		}
		else if(stage==3 && system.get(k).pairs.get(j).el2.name.length()>1){
			String a = system.get(k).pairs.get(j).el2.name;
			for(int i=0;i<a.length();i++){
				if(a.charAt(i)>64 && a.charAt(i)<91){
				}else{
					return true;
				}
			}
		}else{
			return true;
		}
		return true;
	}
	public static void outputCCDM(){
		int zz=listCCDM.size();
		for(int i=0;i<zz;i++){
			//if(listCCDM.get(i).astrometric){
			if(true){
				//System.out.print(listCCDM.get(i).ADS);
				for(int z=0;z<10-listCCDM.get(i).ADS.length();z++){
					//System.out.print(" ");
				}
				//System.out.print(listCCDM.get(i).astrometric);
				//System.out.print(listCCDM.get(i).DM);
				for(int z=0;z<10-listCCDM.get(i).DM.length();z++){
					//System.out.print(" ");
				}
				//System.out.print(listCCDM.get(i).HD);
				for(int z=0;z<10-listCCDM.get(i).HD.length();z++){
					//System.out.print(" ");
				}
				//System.out.print(listCCDM.get(i).HIP);
				for(int z=0;z<10-listCCDM.get(i).HIP.length();z++){
					//System.out.print(" ");
				}
				//System.out.print(listCCDM.get(i).idsID);
				for(int z=0;z<10-listCCDM.get(i).idsID.length();z++){
					//System.out.print(" ");
				}
				//System.out.print(listCCDM.get(i).wdsID);
				for(int z=0;z<10-listCCDM.get(i).wdsID.length();z++){
					//System.out.print(" ");
				}
				//System.out.println(listCCDM.get(i).nameOfObserver);
			}
		}
	}
	public static void outputTDSC(){
		int zz=listTDSC.size();
//		for(int i=0;i<zz;i++){
//			if(true){
//				//System.out.print(listTDSC.get(i).CCDMidPAIR);
//				for(int z=0;z<10-listTDSC.get(i).CCDMidPAIR.length();z++){
//					//System.out.print(" ");
//				}
//				//System.out.print(listTDSC.get(i).pairTDSC);
//				for(int z=0;z<3-listTDSC.get(i).pairTDSC.length();z++){
//					//System.out.print(" ");
//				}
//				//System.out.print(listTDSC.get(i).HD);
//				for(int z=0;z<10-listTDSC.get(i).HD.length();z++){
//					//System.out.print(" ");
//				}
//				//System.out.print(listTDSC.get(i).HIPid);
//				for(int z=0;z<10-listTDSC.get(i).HIPid.length();z++){
//					//System.out.print(" ");
//				}
//				System.out.print(listTDSC.get(i).TDSCid);
//				for(int z=0;z<10-listTDSC.get(i).TDSCid.length();z++){
//					System.out.print(" ");
//				}
//				System.out.print(listTDSC.get(i).WDSid);
//				for(int z=0;z<10-listTDSC.get(i).WDSid.length();z++){
//					System.out.print(" ");
//				}
//				System.out.println();
//			}
//		}
	}
	public static String clearify(String ss){
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
	public static void interprWDS2() throws IOException{
		String fileName="logExtraPairsInWDS.txt";
		Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
		for(int i=0;i< system.size();i++){
			ArrayList<String> names = new ArrayList<String>();
			for(int j=0;j<system.get(i).pairs.size();j++){
				String comp1=system.get(i).pairs.get(j).el1.name;
				String comp2=system.get(i).pairs.get(j).el2.name;
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
					outer2.append(system.get(i).idWDS.substring(0, 11)+"_"+system.get(i).pairs.get(j).id+(char)(10));
					outer2.flush();
				}
			}
		}
	}
	public static void interprCCDM2() throws IOException{
		String fileName="logEx2.txt";
		
		Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
		int h=system.size();
		for(int i=0;i<h;i++){
			for(int j=0; j<h;j++){
				if(system.get(i).idWDS!="" && !system.get(i).idWDS.equals("          ") && system.get(i).idWDS.equals(system.get(j).idWDS)){
					if(system.get(i).CCDMid!=null && system.get(i).CCDMid!="" && system.get(j).CCDMid!=null && system.get(j).CCDMid!=""  && !system.get(i).CCDMid.equals(system.get(j).CCDMid)){
						outer2.append("WDS"+system.get(i).idWDS+"CCDM1"+system.get(j).CCDMid+"CCDM2"+system.get(i).CCDMid+(char)(10));
						outer2.flush();
					}
				}
				if(system.get(i).CCDMid!=null && system.get(i).CCDMid!="" && system.get(i).CCDMid.equals(system.get(j).CCDMid)){
					if(system.get(i).idWDS!=null && system.get(i).idWDS!="" && !system.get(i).idWDS.equals(system.get(j).idWDS)){
						outer2.append("CCDM"+system.get(i).CCDMid+"wds1"+system.get(j).idWDS+"wds2"+system.get(i).idWDS+(char)(10));
						outer2.flush();
					}
				}
			}
		}
	};
	public static void parseWCT2(){//"log2.txt"
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
						//System.out.println("asd"+star.parameter[0]+star.parameter[2]);
						sss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		int x =str.size();
		for(int i=0;i<x;i++){
			for(int j=i;j<x;j++){
				if(str.get(i).a!="" && str.get(i).a.equals(str.get(j).a)){
					if(!str.get(i).b.equals(str.get(j).b) && str.get(j).b!="" && str.get(i).b!=""){
						//System.out.print("DIFF_CCDM WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						//System.out.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
				if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).b)){
					if(!str.get(i).a.equals(str.get(j).a) && str.get(j).a!="" && str.get(i).a!=""){
						//System.out.print("DIFF_WDS  WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						//System.out.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
			}
		}
	}
	public static void parseWCT3(){//"log2.txt"
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
						//System.out.println("asd"+star.parameter[0]+star.parameter[2]);
						sss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
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
					//System.out.println(str.get(i).a);
				}
			}
		}
		//System.out.println("half");
		for(int i=0;i<x;i++){
			for(int j=0;j<x;j++){
				level1: if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).a) && i!=j){
					for(int k=0;k<x;k++){
						if(str.get(i).b.equals(str.get(k).b) && str.get(k).b.equals(str.get(k).a)){
							break level1;
						}
					}
					//System.out.println(str.get(i).a);
				}
			}
		}
	}
	public static void interprTDSC2() throws IOException{
		int xLog=0;
		long timePrev=System.nanoTime();
		String fileName3="logTDSCerrs.txt";
		Writer outer3 = new FileWriter(new File("C:/WDSparser/"+fileName3));
		int gg=system.size();
		int hh=listTDSC.size();
		for(int i=0;i<gg;i++){
			for(int j=0;j<hh;j++){
				if(listTDSC.get(j).WDSid!=null && listTDSC.get(j).WDSid!=""){
					if(listTDSC.get(j).TDSCid.equals(system.get(i).TDSCid)){
						if(listTDSC.get(j).WDSid!="          " && !system.get(i).identifyer.substring(0,10).equals(listTDSC.get(j).WDSid)){
							outer3.append(listTDSC.get(j).TDSCid+"_"+"WDSid in TDSC"+listTDSC.get(j).WDSid+"WDSid in WCT"+system.get(i).identifyer.substring(0,10)+(char)(10));
							outer3.flush();
						}
						break;
					}/*
					for(int h=0;h<system.get(i).pairs.size();h++){
						if(system.get(i).pairs.get(h).idTDSC.equals(listTDSC.get(j).TDSCid)){
							if(!system.get(i).identifyer.substring(0,10).equals(listTDSC.get(j).WDSid)){
								outer2.append("true"+listTDSC.get(j).TDSCid+"_"+"WDSid in TDSC"+listTDSC.get(j).WDSid+"WDSid in WCT"+system.get(i).identifyer.substring(0,10)+(char)(10));
								outer2.flush();
							}
						}
					}*/
				}
			}
			if(xLog*1000<=i){
				//System.out.println(i+"/"+gg+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
				timePrev=System.nanoTime();
				xLog=(int) (i/1000+1);	
			}
		}
	};


}