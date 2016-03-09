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

public class WDSparser {
	static int iter=0;
	static ArrayList<NodeWDS> globalList = new ArrayList<NodeWDS>();
	static ArrayList<NodeSCO> listSCO = new ArrayList<NodeSCO>();
	static ArrayList<NodeCCDM> listCCDM = new ArrayList<NodeCCDM>();
	static ArrayList<NodeTDSC> listTDSC = new ArrayList<NodeTDSC>();
	
	static ArrayList<NodeCCDMcoords> listCCDMcoords = new ArrayList<NodeCCDMcoords>();
	static ArrayList<NodeINT4> listINT4 = new ArrayList<NodeINT4>();
	public static ArrayList<NodeCEV> listCEV = new ArrayList<NodeCEV>();
	public static ArrayList<NodeGCVS> listGCVS = new ArrayList<NodeGCVS>();
	static ArrayList<NodeSB9> listSB9 = new ArrayList<NodeSB9>();
	static ArrayList<NodeSB9main> listSB9main = new ArrayList<NodeSB9main>();
	static ArrayList<NodeHMXB> listHMXB = new ArrayList<NodeHMXB>();
	static ArrayList<NodeLMXB> listLMXB = new ArrayList<NodeLMXB>();
	
	public static ArrayList<StarSystem> system= new ArrayList<StarSystem>();
	public static void main(String[] args){
		System.out.println("parse SCO");
		parseSCO();
		System.out.println("parse TDSC");
		parseTDSC();
		System.out.println("parse CEV");
		parseCEV();
		System.out.println("parse GCVS");
		parseGCVS();
		System.out.println("parse SB9");
		parseSB9();
		System.out.println("parse CCDMcoords()");
		parseCCDMcoords();
		//splitterWDS();
		//splitterCCDM();
		//splitterWCT();
		for(int i=0;i<24;i++){
			for(int j=0;j<6;j++){
				iteration(""+i+j);
				System.out.println(""+i+j);
			}
		}
		concatenator();
		try{
			fix();
		}catch(Exception e ){
		}
		System.out.println("All data saved. Thread terminated");
	}
	public static void iteration(String i){
		init();
		solve(i);
		Statistica.print();
	}
	public static void init(){
		globalList = new ArrayList<NodeWDS>();
		listCCDM = new ArrayList<NodeCCDM>();
		system= new ArrayList<StarSystem>();
		listTDSC = new ArrayList<NodeTDSC>();
		listINT4 = new ArrayList<NodeINT4>();
	}
	public static void splitterWDS(){
		int iterator=0;
		try {
			for(int n=0;n<24;n++){
					for(int m=0;m<6;m++){
					String fileLogName="WDS"+n+m+".txt";
					Writer outer = new FileWriter(new File("C:/WDSparser/WDS/"+fileLogName));
					String[]fileName={"data06.txt","data12.txt","data18.txt","data24.txt"};
					//String[]fileName={"data06.txt"};
						for(int h=0;h<fileName.length;h++){
							File dataFile = new File("C:/WDSparser/"+fileName[h]);
							FileReader in = new FileReader(dataFile);
							char c;
							StringBuffer ss = new StringBuffer();
							long d=dataFile.length();
							for(long i=0;i<d;i++){
								c = (char) in.read();
								ss.append(c);
								if(c==10){
									String s=ss.toString();
									if(Integer.parseInt(""+s.charAt(0)+s.charAt(1)+s.charAt(2))==n*10+m){
										outer.write(s+'\n');
										outer.flush();
									}
									ss = new StringBuffer();
								}
								if(d-i<1){
									String s=ss.toString();
									if(Integer.parseInt(""+s.charAt(0)+s.charAt(1)+s.charAt(2))==n*10+m){
										outer.write(s+'\n');
										outer.flush();
									}
									ss = new StringBuffer();
								}
							}
						}
						System.out.println("Success. fileLength=");
					}
				}
			 } catch (Exception e) {
			  		System.out.println("����� ����!");
			  		e.printStackTrace();
			}
	}
	public static void splitterCCDM(){
		int iterator=0;
		try { 
			for(int n=0;n<24;n++){for(int m=0;m<6;m++){
				String fileLogName="CCDM"+n+m+".txt";
				Writer outer = new FileWriter(new File("C:/WDSparser/CCDM/"+fileLogName));
				  String fileName="CCDMCORR.dat";
					File dataFile = new File("C:/WDSparser/"+fileName);	
					FileReader in = new FileReader(dataFile);
					char c;
					long d = dataFile.length();
					StringBuffer ss = new StringBuffer();
					for(long i=0;i<d;i++){
						c = (char) in.read();
						ss.append(c);
						if(c==10){
							String s=ss.toString();
							if(Integer.parseInt(""+s.charAt(1)+s.charAt(2)+s.charAt(3))==n*10+m){
								outer.write(s+'\n');
								outer.flush();
							}
							ss = new StringBuffer();
						}
					}
					System.out.println("Success. fileLength="+dataFile.length());
				}}
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void splitterWCT(){
		int iterator=0;
		try { 
			for(int n=0;n<24;n++){for(int m=0;m<6;m++){
				String fileLogName="WCT"+n+m+".txt";
				Writer outer = new FileWriter(new File("C:/WDSparser/WCT/"+fileLogName));
				  String fileName="dataWCT.dat";
					File dataFile = new File("C:/WDSparser/"+fileName);	
					FileReader in = new FileReader(dataFile);
					char c;
					long d = dataFile.length();
					StringBuffer ss = new StringBuffer();
					for(long i=0;i<d;i++){
						c = (char) in.read();
						ss.append(c);
						if(c==10){
							String s=ss.toString();
							try{
								if((""+s.charAt(33)+s.charAt(34)).equals("\"\"") && Integer.parseInt(""+s.charAt(50)+s.charAt(51)+s.charAt(52))==n*10+m || Integer.parseInt(""+s.charAt(33)+s.charAt(34)+s.charAt(35))==n*10+m){
									outer.write(s);
									outer.flush();
								}
							}catch(Exception e){
							}
							ss = new StringBuffer();
						}
					}
					System.out.println("Success. fileLength="+dataFile.length());
				}}
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void splitterINT4(){
		int iterator=0;
		try { 
			for(int n=0;n<24;n++){for(int m=0;m<6;m++){
				String fileLogName="INT"+n+m+".txt";
				Writer outer = new FileWriter(new File("C:/WDSparser/INT/"+fileLogName));
				  String fileName="dataINT4.txt";
					File dataFile = new File("C:/WDSparser/"+fileName);	
					FileReader in = new FileReader(dataFile);
					char c;
					long d = dataFile.length();
					boolean writeMode =false;
					StringBuffer ss = new StringBuffer();
					for(long i=0;i<d;i++){
						c = (char) in.read();
						ss.append(c);
						if(c==10){
							String s=ss.toString();
							try{
								if(s.charAt(0)=='0' || s.charAt(0)=='1' || s.charAt(0)=='2'){
									if(Integer.parseInt(s.substring(0,3))==n*10+m){
										writeMode=true;
									}else{
										writeMode=false;
									}
								}
								if(writeMode){
									outer.write(s);
									outer.flush();
								}
							}catch(Exception e){
							}
							ss = new StringBuffer();
						}
					}
					System.out.println("Success. fileLength="+dataFile.length());
				}}
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void solve(String i){
		
		//System.out.println("������ ������ �������");
		parseFile(i);
		//System.out.println("�������������");
		interpr();
		globalList.clear();
		/*
		System.out.println("interprWDS2()");
		try {
			interprWDS2();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//System.out.println("system.size() "+system.size());
		
		////////////////////
		// ������ ������� //
		////////////////////
		
		System.out.println("Processing..");
		interprSCO();
		//listSCO.clear();
		System.out.println("system.size() "+system.size());
		////output();*/
		 
		////////////////////
		// ������ ������� //
		////////////////////
		
		System.out.println("parce WCT");
		parseWCT(i);

		System.out.println("parse INT4");
		parseINT4(i);
		//.out.println("�������������");
		//System.out.println("system.size() "+system.size());
		////interprWCT();
		////listWCT.clear();
		//outputWCT();
		
		/////////////////////
		//��������� �������//
		/////////////////////
		
		System.out.println("parse CCDMr");
		parseCCDM(i);
		System.out.println("preProcessing..");
		predInterprCCDM();
		System.out.println("Processing..");
		interprCCDMr();
		interprCCDMcoords();
		listCCDM.clear();
		//System.out.println("system.size() "+system.size());
		/////////////////////
		//  ����� �������  //
		/////////////////////
		
		System.out.println("Processing..");
		interprTDSC();
		System.out.println("system.size() "+system.size());
		//////////////////////
		//  ������ �������  //
		//////////////////////
		interprINT4();
		
		///////////////////////
		//  ������� �������  //
		//////////////////////
		interprCEV();

		
		///////////////////////
		//  ������� �������  //
		///////////////////////
		interprSB9(i);

		
		System.out.println("������");
		collapse();
		restructNamesForComponents();
		//System.out.println("������ � ����");
		write(i);
		System.out.println("��������� ��������� ������");

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
	public static void concatenator(){
		int componentCounter=0;
		int systemCounter=0;
		int pairCounter=0;
		try{
			String fileName2="ILB.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName2));
			String fileName;
			for(int i=0;i<24;i++){
				for(int j=0;j<6;j++){
					fileName="DATA_RELEASE"+i+j+".txt";
					File dataFile = new File("C:/WDSparser/"+fileName);	
					FileReader in = new FileReader(dataFile);
					char c;
					StringBuffer ss = new StringBuffer();
					long d = dataFile.length();
					for(long ii=0;ii<d;ii++){
						c=(char)in.read();
						ss.append(c);
						if(c==10){
							outer2.append(ss);
							outer2.flush();
							if(ss.toString().contains(":s")){
								systemCounter++;
							}else if(ss.toString().contains(":p")){
								pairCounter++;
							}else if(ss.toString().contains(":c")){
								componentCounter++;
							}else{
								System.out.println(ss);
							}
							ss = new StringBuffer();
						}
					}	
					System.out.println("File"+fileName+"successful concatenated");
					System.out.println("Systems :"+systemCounter);
					System.out.println("Pairs :"+pairCounter);
					System.out.println("Components :"+componentCounter);
				}
			}
		}catch(Exception e){
			
		}
	}
	public static void parseFile(String xxx){
		try {//String[]fileName={"data06.txt","data12.txt","data18.txt","data24.txt"};
		String[] fileName={"WDS"+xxx+".txt"};
			for(int h=0;h<fileName.length;h++){
				System.out.println("Stage:"+h);
				File dataFile = new File("C:/WDSparser/WDS/"+fileName[h]);
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d=dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						if(s.length()>10){
							s=s.substring(0, s.length()-2);
							NodeWDS star = new NodeWDS(s);
							globalList.add(star);
							ss = new StringBuffer();
						}else{
							ss = new StringBuffer();
						}
					}
					if(d-i<1){
						String s=ss.toString();
						NodeWDS star = new NodeWDS(s);
						globalList.add(star);
						ss = new StringBuffer();
					}
				}
			}
			System.out.println("Success. fileLength=");
		 } catch (Exception e) {
		  		//System.out.println("����� ����!");
		  		//e.printStackTrace();
		}
		/*		
		for(int i=0;i<globalList.size();i++){
			System.out.print(globalList.get(i).identifyer+"   ");
			System.out.print(globalList.get(i).number+"   ");
			System.out.print(globalList.get(i).pair+"   ");
			System.out.println(globalList.get(i).data+"   ");
		}
	*/	
	}
	public static void parseSCO(){
		try { 
			  String fileName="dataSCO.txt";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						NodeSCO star = new NodeSCO(s);
						listSCO.add(star);
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void parseCCDMcoords(){
		try { 
			  String fileName="CCDM_AK.txt";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						NodeCCDMcoords star = new NodeCCDMcoords(s);
						listCCDMcoords.add(star);
						ss = new StringBuffer();
					}
				}
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void parseCCDM(String xxx){
		try { 
			  String fileName="CCDM"+xxx+".txt";
				File dataFile = new File("C:/WDSparser/CCDM/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				long d = dataFile.length();
				StringBuffer ss = new StringBuffer();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						//s=s.substring(0, s.length()-2);
						//System.out.println(s);
						if(s.length()>1){
							NodeCCDM star = new NodeCCDM(s);
							listCCDM.add(star);
						}
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
			  System.out.print("$");
		}
	}
	public static void parseTDSC(){
		try { 
			  String fileName="dataTDSC.dat";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						if(s.charAt(7)==' ' && s.charAt(6)!='A'){
							NodeTDSC star = new NodeTDSC(s);
							listTDSC.add(star);
						}
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void parseGCVS(){
		try {
			String fileName="dataGCVS.dat";
			File dataFile = new File("C:/WDSparser/"+fileName);
			FileReader in = new FileReader(dataFile);
			char c;
			StringBuffer ss = new StringBuffer();
			long d = dataFile.length();
			for(long i=0;i<d;i++){
				c = (char) in.read();
				ss.append(c);
				if(c==10){
					String s=ss.toString();
					s=s.substring(0, s.length()-2);
					//System.out.println(s);
					NodeGCVS star = new NodeGCVS(s);
					listGCVS.add(star);
					ss = new StringBuffer();
				}
			}
			System.out.println("Success. fileLength="+dataFile.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void parseWCT(String xxx){//"log2.txt"
		try { 	
				String fileName2="log2.txt";
				Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName2));
				String fileName="WCT"+xxx+".txt";
				File dataFile = new File("C:/WDSparser/WCT/"+fileName);	
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
						int h=system.size();
						boolean systemExistence=false;
						for(int j=0;j<h;j++){
							if(system.get(j).idWDS.length()>9 && (star.parameter[0]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[0]!=""  || 
									(star.parameter[2]).equals(system.get(j).CCDMid) && star.parameter[2]!="" || 
									(star.parameter[4]).equals(system.get(j).TDSCid) && star.parameter[4]!=""){
								boolean existence=false;
								system.get(j).CCDMid=star.parameter[2];
								system.get(j).TDSCid=star.parameter[4];
								iter++;
								Pair ee = new Pair();
								ee.pairWDS=star.parameter[1];
								ee.pairCCDM=star.parameter[3];
								ee.pairTDSC=star.parameter[5];
								ee.idCCDM=star.parameter[2];//
								ee.idTDSC=star.parameter[4];//
								ee.modyfyer[1]='v';
								//System.out.println(star.parameter[0]+"_"+star.parameter[2]+"_"+star.parameter[4]);
								//System.out.println(s);
								if(ee.pairWDS=="" && star.parameter[0]!=""){
									ee.pairWDS="AB";
								}
								if(ee.pairWDS!=""){
									ee.id=ee.pairWDS;
								}else{
									if(ee.pairCCDM!=""){
										ee.id=ee.pairCCDM;
									}else{
										if(ee.pairTDSC!=""){
											ee.id=ee.pairTDSC;
										}else{
											ee.id="AB";
											if(ee.pairWDS!=""){
												ee.pairWDS="AB";
											}
											if(ee.pairTDSC!=""){
												ee.pairTDSC="AB";
											}
											if(ee.pairCCDM!=""){
												ee.pairCCDM="AB";
											}
											ee.fakeName=true;
											star.parameter[1]="AB";
											outer2.write("ExceptionPairNameDNE: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
											outer2.flush();
										}
									}
								}
								ee.clean();
								int err=ee.dispWithoutRepeatCheckerInWriter(j);	
								if(err==1){
									System.out.println("Error 1 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
								}else if(err==2){
									System.out.println("Error 2 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
								}
								for(int k=0;k<system.get(j).pairs.size();k++){
									//if(system.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!="" || system.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM) && ee.pairCCDM!="" || system.get(j).pairs.get(k).pairTDSC.equals(ee.pairTDSC) && ee.pairTDSC!=""){
									if(system.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!=""){
										existence=true;
										system.get(j).idWDS=star.parameter[0];//��� ��������
										system.get(j).pairs.get(k).idCCDM=star.parameter[2];//
										system.get(j).pairs.get(k).idTDSC=star.parameter[4];//
										system.get(j).pairs.get(k).pairWDS=star.parameter[1];
										system.get(j).pairs.get(k).pairCCDM=star.parameter[3];
										system.get(j).pairs.get(k).pairTDSC=star.parameter[5];
										system.get(j).pairs.get(k).modyfyer[1]='v';
										break;
									}
								}
								if(!existence){//�� �������
									outer2.write("ExceptionPairExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
									outer2.flush();
									Pair e = new Pair();
									e.id=ee.id+"!";
									e.el1=ee.el1;
									e.el2=ee.el2;
									e.el1.name+="!";
									e.el2.name+="!";
									e.pairWDS=star.parameter[1];
									e.pairCCDM=star.parameter[3];
									e.pairTDSC=star.parameter[5];
									e.idCCDM=star.parameter[2];
									e.idTDSC=star.parameter[4];
									e.modyfyer[1]='v';
									e.clean();
									system.get(j).pairs.add(e);
								}
								systemExistence=true;
								break;
							}else{
								try{
									if((star.parameter[2]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[2]!="" || (star.parameter[4]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[4]!=""){
										outer2.write("WarningSystemWDSExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
										outer2.flush();
										boolean existence=false;
										iter++;
										Pair ee = new Pair();
										ee.idCCDM=star.parameter[2];
										ee.idTDSC=star.parameter[4];
										ee.pairWDS=star.parameter[1];
										ee.pairCCDM=star.parameter[3];
										ee.pairTDSC=star.parameter[5];
										ee.modyfyer[1]='v';
										if(ee.pairWDS!=""){
											ee.id=ee.pairWDS;
										}else{
											if(ee.pairCCDM!=""){
												ee.id=ee.pairCCDM;
											}else{
												if(ee.pairTDSC!=""){
													ee.id=ee.pairTDSC;
												}else{
													ee.id="AB";
													ee.pairWDS="AB";
													ee.pairTDSC="AB";
													ee.pairCCDM="AB";
													star.parameter[1]="AB";
													outer2.write("ExceptionPairDoesNotExist: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
													outer2.flush();
												}
											}
										}
										ee.clean();
										int err=ee.dispWithoutRepeatCheckerInWriter(j);	
										if(err==1){
											System.out.println("Error 11 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
										}else if(err==2){
											System.out.println("Error 12 in id:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
										}			
										for(int k=0;k<system.get(j).pairs.size();k++){
											if(system.get(j).pairs.get(k).pairWDS.equals(ee.pairWDS) && ee.pairWDS!="" || system.get(j).pairs.get(k).pairWDS.equals(ee.pairCCDM) && ee.pairCCDM!="" || system.get(j).pairs.get(k).pairWDS.equals(ee.pairTDSC) && ee.pairTDSC!=""){
												existence=true;
												system.get(j).pairs.get(k).modyfyer[1]='v';
												system.get(j).pairs.get(k).pairCCDM=star.parameter[3];
												system.get(j).pairs.get(k).pairTDSC=star.parameter[5];
												break;
											}
										}
										if(!existence){//�� �������
											//System.out.println("Error:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
											outer2.write("ExceptionPairExistence: "+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]+(char)10);
											outer2.flush();
											Pair e = new Pair();
											e.id=ee.id;
											e.el1=ee.el1;
											e.el2=ee.el2;
											e.pairWDS=star.parameter[1];
											e.pairCCDM=star.parameter[3];
											e.pairTDSC=star.parameter[5];
											ee.idCCDM=star.parameter[2];
											ee.idTDSC=star.parameter[4];
											e.idTDSC=star.parameter[4];
											e.idCCDM=star.parameter[2];
											e.modyfyer[1]='v';
											ee.modyfyer[1]='v';
											e.clean();
											system.get(j).pairs.add(e);
										}
										systemExistence=true;
										break;
									}
								}catch(Exception e){
								}
							}
						}
						if(systemExistence==false){//�� �������
							outer2.write("ExceptionSystemExistence2: "+star.parameter[0]+""+(char)10);
							outer2.flush();
							StarSystem ss= new StarSystem();
							system.add(ss);
							ss.idWDS=star.parameter[0]+"           ";
							ss.CCDMid=star.parameter[2];
							ss.TDSCid=star.parameter[4];
							ss.identifyer=star.parameter[2];
							ss.data="                  ";
							ss.observ="";
							ss.fake=true;
							Pair ee = new Pair();
							ee.id=star.parameter[3];
							ee.clean();
							if(ee.id.length()==1){
								System.out.println(star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
							}
							int err=ee.dispWithoutRepeatCheckerInWriter(-1);
							Pair e = new Pair();
							e.id=ee.id;
							e.el1=ee.el1;
							e.el2=ee.el2;
							e.pairWDS=star.parameter[1];
							e.pairCCDM=star.parameter[3];
							e.pairTDSC=star.parameter[5];
							e.idTDSC=star.parameter[4];
							e.idCCDM=star.parameter[2];
							e.modyfyer[1]='v';
							ss.pairs.add(e);
						}
						sss = new StringBuffer();
						if(xLog*1000000<i){
							System.out.println(i+"/"+d+ "bytes readed in "+ (System.nanoTime()-timePrev)/1000000+"ms");
							timePrev=System.nanoTime();
							xLog=(int) (i/1000000+1);	
						}
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		
	}
	private static void parseCEV(){
		parseGCVS();
		try {
			  String fileName="dataCEV.txt";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						NodeCEV star = new NodeCEV(s);
						listCEV.add(star);
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	private static void parseINT4(String xxx) {
		try { 
			String fileName = "INT"+xxx+".txt";
				File dataFile = new File("C:/WDSparser/INT/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c=' ';
				char cPrev;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				ArrayList<String> s = new ArrayList<String>();
				for(long i=0;i<d;i++){
					cPrev=c;
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						if(c!=cPrev){
							String sloc=ss.toString();
							sloc=sloc.substring(0, sloc.length()-2);
							s.add(sloc);
						}else{
							NodeINT4 star = new NodeINT4(s);
							listINT4.add(star);
							s = new ArrayList<String>();
						}
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  	  e.printStackTrace();
		}
		for(int i=0; i<listINT4.size();i++){
			if(listINT4.get(i).fakePair){
				listINT4.remove(i);
				i--;
			}
		}
	}
	private static void parseSB9() {
		try { 
			  String fileName="dataSB9.dta";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				String prevString="           ";
				int indexPrev = 0;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-1);
						int index=0;
						while(s.charAt(index)!='|'){
							index++;
						}
						if(!s.substring(0,index).equals(prevString.substring(0,indexPrev))){
							NodeSB9 star = new NodeSB9(s, index);
							listSB9.add(star);
						}else{
							listSB9.get(listSB9.size()-1).addEff(s, index);
						}
						prevString=s;
						indexPrev=index;
						//System.out.println(s);
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}

		try { 
			  String fileName="dataSB9main.txt";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				char c;
					for(long i=0;i<d;i++){
						c = (char) in.read();
						ss.append(c);
						if(c==10){
							String s=ss.toString();
							if(s.length()>10){
								s=s.substring(0, s.length()-2);
								NodeSB9main star = new NodeSB9main(s);
								listSB9main.add(star);
								ss = new StringBuffer();
							}else{
								ss = new StringBuffer();
							}
						}
						if(d-i<1){
							String s=ss.toString();
							NodeSB9main star = new NodeSB9main(s);
							listSB9main.add(star);
							ss = new StringBuffer();
						}
					}
				
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		int counter=0;
		for(int i=0;i<listSB9main.size();i++){
			for(int j=0;j<listSB9.size();j++){
				if(listSB9main.get(i).SB9.equals(listSB9.get(j).SB9)){
					counter++;
					listSB9.get(j).mainID=listSB9main.get(i).mainID;
					listSB9.get(j).data=listSB9main.get(i).data;
					//System.out.println("SB9 counter = "+ counter);
				}
			}
		}
	}
	private static void parseLMXB() {
		try { 
			  String fileName="dataLMXB.dat";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						NodeLMXB star = new NodeLMXB(s);
						listLMXB.add(star);
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		  }
	}
	private static void parseHMXB() {
		try { 
			  String fileName="dataHMXB.dat";
				File dataFile = new File("C:/WDSparser/"+fileName);	
				FileReader in = new FileReader(dataFile);
				char c;
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						NodeHMXB star = new NodeHMXB(s);
						listHMXB.add(star);
						ss = new StringBuffer();
					}
				}
				System.out.println("Success. fileLength="+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		  }
	}
	public static void interpr(){
		int xLog=0;
		long timePrev=System.nanoTime();
		int d=globalList.size();
		for(int i=0;i<d;i++){
			StarSystem s= new StarSystem();
			s.identifyer=globalList.get(i).identifyer;
			s.data=globalList.get(i).data;
			s.observ=globalList.get(i).nameOfObserver;
			s.idWDS=globalList.get(i).mainID;
			s.fake=globalList.get(i).fake;
			boolean uniqueSystem=true;
			int sysNumber=0;
			int h=system.size();
			for(int j=0;j<h;j++){
				if(s.identifyer.substring(0,10).equals(system.get(j).identifyer.substring(0,10))){
					uniqueSystem=false;
					sysNumber=j;
					break;
				}
			}
			if(uniqueSystem){
				//System.out.println(system.size());
				system.add(s);
				Pair e = new Pair();
				e.id=globalList.get(i).pair;
				e.observer=globalList.get(i).nameOfObserver;
				e.pairWDS=globalList.get(i).pair;
				e.idDM=globalList.get(i).idDM;
				e.modyfyer[0]=globalList.get(i).modyfyer2[0];
				e.rho=globalList.get(i).rho;
				e.theta=globalList.get(i).theta;
				if(globalList.get(i).modyfyer2[1]!=0){
					e.modyfyer[0]=globalList.get(i).modyfyer2[1];
				}
				e.clean();
				e.disp();
				try{
					e.el1.coord_flag=11;
					e.el1.coord1I=Integer.parseInt(globalList.get(i).data.substring(0,6));
					try{
						e.el1.coord1F=Integer.parseInt(globalList.get(i).data.substring(7,9));
					}catch(NumberFormatException exc){
						e.el1.coord1F=0;
						e.el1.coord_flag=0;
					}
					try{
						e.el1.coord2I=Integer.parseInt(globalList.get(i).data.substring(9,16));
					}catch(NumberFormatException exc){
						e.el1.coord2I=0;
						e.el1.coord_flag=0;
					}
					try{
						e.el1.coord2F=Integer.parseInt(globalList.get(i).data.substring(17,18));
					}catch(NumberFormatException exc){
						e.el1.coord2F=0;
						e.el1.coord_flag=0;
					}
					if(e.el1.coord2I<0){
						e.el1.coord2F*=-1;
					}
					if(globalList.get(i).rho!=0){
						e.el2.coord_flag=12;
						try{
							e.el2.coord1I=(int)(long) Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
						}catch(NumberFormatException exc){
							e.el2.coord1I=0;
							e.el2.coord_flag=0;
						}
						try{
							e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
						}catch(NumberFormatException exc){
							e.el2.coord1F=0;
							e.el2.coord_flag=0;
						}
						try{
							e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI));
						}catch(NumberFormatException exc){
							e.el2.coord2I=0;
							e.el2.coord_flag=0;
						}
						try{
							e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI))-e.el2.coord2I));
						}catch(NumberFormatException exc) {
							e.el2.coord2F = 0;
							e.el2.coord_flag = 0;
						}
					}
				}catch(Exception td){
					td.printStackTrace();
				}
				system.get(system.size()-1).pairs.add(e);
			}else{
				Pair e = new Pair();
				e.id=globalList.get(i).pair;
				e.observer=globalList.get(i).nameOfObserver;
				e.pairWDS=globalList.get(i).pair;
				e.idDM=globalList.get(i).idDM;
				e.rho=globalList.get(i).rho;
				e.theta=globalList.get(i).theta;
				e.modyfyer[0]=globalList.get(i).modyfyer2[0];
				if(globalList.get(i).modyfyer2[1]!=0){
					e.modyfyer[0]=globalList.get(i).modyfyer2[1];
				}
				try{
					e.el1.coord_flag=11;
					e.el1.coord1I=Integer.parseInt(globalList.get(i).data.substring(0,6));
					try{
						e.el1.coord1F=Integer.parseInt(globalList.get(i).data.substring(7,9));
					}catch(NumberFormatException exc){
						e.el1.coord1F=0;
						e.el1.coord_flag=0;
					}
					try{
						e.el1.coord2I=Integer.parseInt(globalList.get(i).data.substring(9,16));
					}catch(NumberFormatException exc){
						e.el1.coord2I=0;
						e.el1.coord_flag=0;
					}
					try{
						e.el1.coord2F=Integer.parseInt(globalList.get(i).data.substring(17,18));
					}catch(NumberFormatException exc){
						e.el1.coord2F=0;
						e.el1.coord_flag=0;
					}
					if(e.el1.coord2I<0){
						e.el1.coord2F*=-1;
					}
					if(globalList.get(i).rho!=0){
						e.el2.coord_flag=12;
						try{
							e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
						}catch(NumberFormatException exc){
							e.el2.coord1I=0;
							e.el2.coord_flag=0;
						}
						try{
							e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+globalList.get(i).rho*Math.sin(globalList.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
						}catch(NumberFormatException exc){
							e.el2.coord1F=0;
							e.el2.coord_flag=0;
						}
						try{
							e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI));
						}catch(NumberFormatException exc){
							e.el2.coord2I=0;
							e.el2.coord_flag=0;
						}
						try{
							e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+globalList.get(i).rho*Math.cos(globalList.get(i).theta/180*Math.PI))-e.el2.coord2I));
						}catch(NumberFormatException exc){
							e.el2.coord2F=0;
							e.el2.coord_flag=0;
						}
					}
				}catch(Exception td){
					td.printStackTrace();
				}
				e.clean();
				e.disp();
				system.get(sysNumber).pairs.add(e);
			}
			if(xLog*1000<=i){
				System.out.println(i+"/"+d+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
				timePrev=System.nanoTime();
				xLog=(int) (i/1000+1);
			}
		}
	}
	public static void interprSCO(){//"log.txt"
		try{
			String fileName="log.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
//		System.out.println(listSCO.size());
//		System.out.println(system.size());
		int f=listSCO.size();
		int h;
		for(int i=0;i<f;i++){
			boolean systemExistence=false;
			for(int j=0;j<system.size();j++){
				/*System.out.println("a"+system.get(j).mainID);
				System.out.println("b"+system.get(j).identifyer);
				System.out.println("c"+system.get(j).observ);
				System.out.println("d"+system.get(j).mainID.substring(0, 10));-*/
				if((listSCO.get(i).idWDS).equals(system.get(j).idWDS.substring(0, 10))){
					boolean existence=false;
					boolean observerFail=false;
					iter++;
					int number = 0;
					Pair ee = new Pair();
					ee.id=listSCO.get(i).pairWDS;
					ee.clean();
					ee.dispWithoutRepeatCheckerInWriter(j);
					for(int k=0;k<system.get(j).pairs.size();k++){
						if(system.get(j).pairs.get(k).id.equals(ee.id)){
							observerFail=true;
							if(listSCO.get(i).nameOfObserver!=null && listSCO.get(i).nameOfObserver!="" && system.get(j).pairs.get(k).observer!=null && system.get(j).pairs.get(k).observer.equals(listSCO.get(i).nameOfObserver)){
								existence=true;
								number=i;
								system.get(j).pairs.get(k).idHD=listSCO.get(i).idHD;
								system.get(j).pairs.get(k).idHIP=listSCO.get(i).idHIP;
								system.get(j).pairs.get(k).idADS=listSCO.get(i).idADS;
								system.get(j).pairs.get(k).idHyi=listSCO.get(i).idFlamsteed;
								system.get(j).pairs.get(k).idBayer=listSCO.get(i).idBayer;
								if(system.get(j).pairs.get(k).el1.coord1I==0 && system.get(j).pairs.get(k).el1.coord2I==0){
									try{
										system.get(j).pairs.get(k).el1.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
										system.get(j).pairs.get(k).el1.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
										system.get(j).pairs.get(k).el1.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
										system.get(j).pairs.get(k).el1.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
										system.get(j).pairs.get(k).el2.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
										system.get(j).pairs.get(k).el2.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
										system.get(j).pairs.get(k).el2.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
										system.get(j).pairs.get(k).el2.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
										system.get(j).pairs.get(k).el1.coord_flag=21;
										system.get(j).pairs.get(k).el2.coord_flag=21;
									}catch(Exception td){
									}
								}
								if(system.get(j).pairs.get(k).modyfyer[1]=='O'){
									system.get(j).pairs.get(k).modyfyer[2]='o';
									system.get(j).pairs.get(k).modyfyer[1]='o';
								}else{
									system.get(j).pairs.get(k).modyfyer[2]='o';
									//System.out.println("Exception y:"+system.get(j).identifyer+system.get(j).pairs.get(k).id);
									
								}
								break;
							}
						}
					}
					if(!existence){
						if(!observerFail){
							Pair e = new Pair();
							e.id=ee.id;
							e.el1=ee.el1;
							e.el2=ee.el2;
							e.pairWDS=listSCO.get(i).pairWDS;
							e.idHD=listSCO.get(i).idHD;
							e.idHIP=listSCO.get(i).idHIP;
							e.idADS=listSCO.get(i).idADS;
							e.idBayer=listSCO.get(i).idBayer;
							e.idHyi=listSCO.get(i).idFlamsteed;
							e.modyfyer[2]='o';
							try{
								e.el1.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
								e.el1.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
								e.el1.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
								e.el1.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
								e.el2.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
								e.el2.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
								e.el2.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
								e.el2.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
								e.el1.coord_flag=21;
								e.el2.coord_flag=21;
							}catch(Exception td){
							}
							e.clean();
							system.get(j).pairs.add(e);
							outer2.write("ExceptionPairExistence1: "+listSCO.get(i).idWDS+" "+listSCO.get(i).nameOfObserver+" "+listSCO.get(i).pairWDS+" "+(char)10);
							outer2.flush();
						}else{//������ �������������� ����������� - �������� ���������� � �� ���
							Pair e = new Pair();
							e.id="Aa-Ab";
							e.el1.name="Aa";
							e.el2.name="Ab";
							e.pairWDS=listSCO.get(i).pairWDS;
							e.observer=listSCO.get(i).nameOfObserver;
							e.idHD=listSCO.get(i).idHD;
							e.idHIP=listSCO.get(i).idHIP;
							e.idADS=listSCO.get(i).idADS;
							e.idBayer=listSCO.get(i).idBayer;
							e.idHyi=listSCO.get(i).idFlamsteed;
							e.modyfyer[2]='o';
							try{
								e.el1.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
								e.el1.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
								e.el1.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
								e.el1.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
								e.el2.coord1I=Integer.parseInt(listSCO.get(i).data.substring(0,6));
								e.el2.coord1F=Integer.parseInt(listSCO.get(i).data.substring(7,9));
								e.el2.coord2I=Integer.parseInt(listSCO.get(i).data.substring(10,16));
								e.el2.coord2F=Integer.parseInt(listSCO.get(i).data.substring(17,18));
								e.el1.coord_flag=21;
								e.el2.coord_flag=21;
							}catch(Exception td){
							}
							system.get(j).findCompOut("A");
							system.get(j).pairs.add(e);
							outer2.write("Close pair: "+listSCO.get(i).idWDS+" "+listSCO.get(i).nameOfObserver+" "+listSCO.get(i).pairWDS+" "+(char)10);
							outer2.flush();
						}
					}
					systemExistence=true;
					break;
				}
			}
			if(systemExistence==false){/*
				StarSystem s= new StarSystem();
				system.add(s);
				s.idWDS=listSCO.get(i).idWDS;
				s.identifyer=listSCO.get(i).idWDS+listSCO.get(i).nameOfObserver;
				s.data=listSCO.get(i).data;
				s.observ=listSCO.get(i).nameOfObserver;
				s.fake=false;
				outer2.write("ExceptionSystemExistence: "+s.identifyer+""+(char)10);
				structureEntities.Pair ee = new structureEntities.Pair();
				ee.id=listSCO.get(i).pairWDS;
				ee.clean();
				ee.dispWithoutRepeatCheckerInWriter(-1);
				structureEntities.Pair e = new structureEntities.Pair();
				e.id=ee.id;
				e.el1=ee.el1;
				e.el2=ee.el2;
				e.pairWDS=listSCO.get(i).pairWDS;
				e.idHD=listSCO.get(i).idHD;
				e.idHIP=listSCO.get(i).idHIP;
				e.idADS=listSCO.get(i).idADS;
				e.idBayer=listSCO.get(i).idBayer;
				e.idHyi=listSCO.get(i).idHyi;
				e.modyfyer[2]='o';
				//e.clean();
				s.pairs.add(e);
				//outer2.write(listSCO.get(i).pairWDS+""+(char)10);
				outer2.flush();*/
			}
		}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	public static void interprTDSC(){//"log4.txt"
		try{
			//String fileName="logTDSC.txt";
			//Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
			int f=system.size();
			int h=listTDSC.size();
			for(int j=0;j<f;j++){
				int zzz=system.get(j).pairs.size();
				for(int k=0;k<zzz;k++){
					if(system.get(j).pairs.get(k).idTDSC==null || system.get(j).pairs.get(k).idTDSC==""){
						
					}else{
						boolean systemExistence=false;
						layer_1: for(int i=0;i<h;i++){
						/*System.out.println("a"+system.get(j).mainID);
						System.out.println("b"+system.get(j).identifyer);
						System.out.println("c"+system.get(j).observ);
						System.out.println("d"+system.get(j).mainID.substring(0, 10));-*/
						try{
							int u=Integer.parseInt(listTDSC.get(i).TDSCid);
							int gfg=0;
							if(system.get(j).pairs.get(k).idTDSC.charAt(0)!='"'){
								gfg=Integer.parseInt(system.get(j).pairs.get(k).idTDSC);
							}
							if(gfg==0){
								break;
							}
							if(u==gfg){
								iter++;
								system.get(j).pairs.get(k).modyfyer[3]='v';
								if(system.get(j).pairs.get(k).idHD==""){
									system.get(j).pairs.get(k).idHD=listTDSC.get(i).HD;
								}else{
									//if(!(system.get(j).pairs.get(k).idHD.contains(listTDSC.get(i).HD) || listTDSC.get(i).HD.contains(system.get(j).pairs.get(k).idHD))){
										//outer2.write("CompareError HD:"+system.get(j).pairs.get(k).idHD+"_"+listTDSC.get(i).HD+(char)10);
									//}
								}
								if(system.get(j).pairs.get(k).idHIP==""){
									system.get(j).pairs.get(k).idHIP=listTDSC.get(i).HIPid;
								}else{
									//if(!(system.get(j).pairs.get(k).idHIP.contains(listTDSC.get(i).HIPid) || listTDSC.get(i).HIPid.contains(system.get(j).pairs.get(k).idHIP))){
										//outer2.write("CompareError HIP:"+system.get(j).pairs.get(k).idHIP+"_"+listTDSC.get(i).HIPid+(char)10);
									//}
								}
								//outer2.write("Existence: HD "+listTDSC.get(i).HD+" CCDMidPAIR "+listTDSC.get(i).CCDMidPAIR+" TDSCid "+listTDSC.get(i).TDSCid+" "+(char)10);
								//outer2.flush();
								break layer_1;
							}
						}catch(Exception rf){
							rf.printStackTrace();	
						}
					}
					if(systemExistence==false){
						//outer2.write("ExceptionSysExistence: HD "+listTDSC.get(i).pairTDSC+"  "+listTDSC.get(i).TDSCid+" "+(char)10);
						//outer2.flush();
					}
				}
			}
		}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	public static void interprCCDM(){//"log3.txt"
		int xLog=0;
		int matches=0;
		long timePrev=System.nanoTime();
			try{
			int f=listCCDM.size();
			System.out.println("listCCDM.size()= "+listCCDM.size());
			int h;
			for(int i=0;i<f;i++){
				h=system.size();
				upper:{ for(int j=0;j<h;j++){
					if(system.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(system.get(j).CCDMid)){
						for(int dd=0;dd<system.get(j).pairs.size();dd++){
							//������� �������.
							if(system.get(j).pairs.get(dd).pairCCDM.equals(listCCDM.get(i).pairCCDM)){
									//iter++;
									int k=dd;
									if(system.get(j).pairs.get(k).idHD==""){
										if(listCCDM.get(i).HD!=""){
											system.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
											if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
												system.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
											}else{
												system.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
												system.get(j).pairs.get(k).el2.idHD=listCCDM.get(i).HD;
											}
										}
									}
									if(system.get(j).pairs.get(k).idHIP=="" ){
										if(listCCDM.get(i).HIP!=""){
											system.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
											if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
												system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
											}else{
												system.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).HIP;
												system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
											}
										}
									}
									if(system.get(j).pairs.get(k).idADS==""){
										system.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
									}
									if(system.get(j).pairs.get(k).idDM=="" ){
										if(listCCDM.get(i).DM!=""){
											system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
											if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
												system.get(j).pairs.get(k).el1.idDM=listCCDM.get(i).DM;
											}else{
												system.get(j).pairs.get(k).el2.idDM=listCCDM.get(i).DM;
												system.get(j).pairs.get(k).el1.idDM=listCCDM.get(i).DM;
											}
										}
									}
									if(listCCDM.get(i).astrometric){
										system.get(j).pairs.get(k).modyfyer[4]='a';
									}else{
										system.get(j).pairs.get(k).modyfyer[4]='v';
									}
									matches+=1;
									break upper;
							}
						}
					}
				}}
				if(xLog*1000<=i){
					System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
					timePrev=System.nanoTime();
					xLog=(int) (i/1000+1);	
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
		System.out.println("f= "+listCCDM.size());
		System.out.println("matches= "+matches);
	}
	private static void interprINT4() {//"logINT4.txt"
		int xLog=0;
		long timePrev=System.nanoTime();
		try{
			String fileName="logINT4.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
			int f=listINT4.size();
			int h;
			boolean exists = false;
			for(int i=0;i<f;i++){
				h=system.size();
				exists = false;
				layer1:{ for(int j=0;j<h;j++){
					try{
						if(listINT4.get(i).idWDS!= null && listINT4.get(i).idWDS!="" && !(system.get(j).idWDS.substring(0, 2).equals("  ")) && (listINT4.get(i).idWDS).equals(system.get(j).idWDS.substring(0, 10))){
							exists = true;
							for(int k=0;k<system.get(j).pairs.size();k++){
								if(system.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd) || system.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd1)){
									if(system.get(j).pairs.get(k).idADS!=null && listINT4.get(i).name1.contains(system.get(j).pairs.get(k).idADS) && system.get(j).pairs.get(k).idADS!="" || system.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name1.contains(system.get(j).pairs.get(k).idDM) && system.get(j).pairs.get(k).idDM!=""){
										system.get(j).pairs.get(k).modyfyer[6]='i';
										break layer1;
									}else if(system.get(j).pairs.get(k).idBayer!=null && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).idBayer) && system.get(j).pairs.get(k).idBayer!="" || system.get(j).pairs.get(k).idHyi!=null && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).idHyi) && system.get(j).pairs.get(k).idHyi!=""){
										system.get(j).pairs.get(k).modyfyer[6]='i';
										if(system.get(j).pairs.get(k).idHD==""){
											system.get(j).pairs.get(k).idHD=listINT4.get(i).idHD;
										}
										if(system.get(j).pairs.get(k).idHIP==""){
											system.get(j).pairs.get(k).idHIP=listINT4.get(i).idHIP;
										}
										if(system.get(j).pairs.get(k).idHyi==""){
											system.get(j).pairs.get(k).idHyi=listINT4.get(i).idHyi;
										}
										if(system.get(j).pairs.get(k).idBayer==""){
											system.get(j).pairs.get(k).idHyi=listINT4.get(i).idBayer;
										}
										if(system.get(j).pairs.get(k).idDM==""){
											system.get(j).pairs.get(k).idDM=listINT4.get(i).idDM;
										}
										if(system.get(j).pairs.get(k).el1.coord1I==0 && system.get(j).pairs.get(k).el1.coord2I==0 && listINT4.get(i).rho!=-1){
											try{
												system.get(j).pairs.get(k).el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
												system.get(j).pairs.get(k).el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
												system.get(j).pairs.get(k).el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
												system.get(j).pairs.get(k).el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
												system.get(j).pairs.get(k).el1.coord_flag=31;
												if(system.get(j).pairs.get(k).el1.coord2I<0){
													system.get(j).pairs.get(k).el1.coord2F*=-1;
												}
												if(system.get(j).pairs.get(k).el1.coord1I!=0 || system.get(j).pairs.get(k).el1.coord2I!=0){
													system.get(j).pairs.get(k).el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(system.get(j).pairs.get(k).el1.coord1I,system.get(j).pairs.get(k).el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)));
													system.get(j).pairs.get(k).el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(system.get(j).pairs.get(k).el1.coord1I,system.get(j).pairs.get(k).el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)))-system.get(j).pairs.get(k).el2.coord1I));
													system.get(j).pairs.get(k).el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
													system.get(j).pairs.get(k).el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(system.get(j).pairs.get(k).el1.coord2I,system.get(j).pairs.get(k).el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-system.get(j).pairs.get(k).el2.coord2I));
													system.get(j).pairs.get(k).el2.coord_flag=32;
												}
											}catch(Exception td){
												td.printStackTrace();
											}
										}
										break layer1;
									}else if(system.get(j).pairs.get(k).idHD!=null && listINT4.get(i).name3.contains(system.get(j).pairs.get(k).idHD) && system.get(j).pairs.get(k).idHD!="" || system.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name3.contains(system.get(j).pairs.get(k).idDM) && system.get(j).pairs.get(k).idDM!=""){
										system.get(j).pairs.get(k).modyfyer[6]='i';
										break layer1;
									}else if(system.get(j).pairs.get(k).idHIP!=null && system.get(j).pairs.get(k).idHIP!="" && listINT4.get(i).name4.contains(system.get(j).pairs.get(k).idHIP) || system.get(j).pairs.get(k).observer!=null && system.get(j).pairs.get(k).observer!="" && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).observer)){//discoverer 2!!!
										system.get(j).pairs.get(k).modyfyer[6]='i';
										break layer1;
									}else if(listINT4.get(i).rho!=0 && system.get(j).pairs.get(k).rho!=0 && listINT4.get(i).rho/system.get(j).pairs.get(k).rho>1/3 && listINT4.get(i).rho/system.get(j).pairs.get(k).rho<3){
										system.get(j).pairs.get(k).modyfyer[6]='i';
										break layer1;
									}
									System.out.println(listINT4.get(i).rho+" "+system.get(j).pairs.get(k).rho);
								}
							}
							//���� ������, � ���� �� ���������, ������ ��� ���� ���� ���������
							if(listINT4.get(i).dd.contains("Ab comp")){
								Pair e = new Pair();
								e.id="Aba-Abb";
								e.el1.name="Aba";
								e.el2.name="Abb";
//								e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
//								e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
//								e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
//								e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
								e.pairWDS="";
								e.observer=listINT4.get(i).dd1;
								e.idHD=listINT4.get(i).idHD;
								e.idHIP=listINT4.get(i).idHIP;
								e.idADS=listINT4.get(i).idADS;
								e.idHyi=listINT4.get(i).idHyi;
								e.idDM=listINT4.get(i).idDM;
								e.idBayer=listINT4.get(i).idBayer;
								e.modyfyer[6]='i';
								if(listINT4.get(i).rho!=-1){
									try{
										e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
										e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
										e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
										e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
										System.out.println("XXX"+listINT4.get(i).data);
										System.out.println(e.el1.coord2I);
										e.el1.coord_flag=31;
										if(e.el1.coord2I<0){
											e.el1.coord2F*=-1;
										}
										if(e.el1.coord1I!=0 || e.el1.coord2I!=0){
											e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
											e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
											e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
											e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
											e.el2.coord_flag=32;
										}
										System.out.println(e.el1.coord2I);
									}catch(Exception td){
									}
								}
								system.get(j).findCompOut("Ab");
								system.get(j).pairs.add(e);
								outer2.write("CP: WDSid:"+system.get(j).identifyer +"    INT4 DD:"+listINT4.get(i).dd+" "+(char)10);
								outer2.flush();
								break layer1;
							}
							Pair e = new Pair();
							e.id="Aa-Ab";
							e.el1.name="Aa";
							e.el2.name="Ab";
							if(listINT4.get(i).rho!=-1){
								try{
									e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
									e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
									e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
									e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
									e.el1.coord_flag=31;
									if(e.el1.coord2I<0){
										e.el1.coord2F*=-1;
									}
									if(e.el1.coord1I!=0 || e.el1.coord2I!=0){
										e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
										e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
										e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
										e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
										e.el2.coord_flag=32;
									}
								}catch(Exception td){
								}
							}
							e.pairWDS="";
							e.observer=listINT4.get(i).dd;
							e.idHD=listINT4.get(i).idHD;
							e.idHIP=listINT4.get(i).idHIP;
							e.idADS=listINT4.get(i).idADS;
							e.idHyi=listINT4.get(i).idHyi;
							e.idDM=listINT4.get(i).idDM;
							e.idBayer=listINT4.get(i).idBayer;
							e.modyfyer[6]='i';
							system.get(j).findCompOut("A");
							system.get(j).pairs.add(e);
							outer2.write("CP: WDSid:"+system.get(j).identifyer +"    INT4 DD:"+listINT4.get(i).dd+" "+(char)10);
							outer2.flush();
							break layer1;
						}
					}catch(Exception e){
					}
				}
				if(exists == false){
					StarSystem s= new StarSystem();				
					//System.out.println(listCCDM.get(i).ccdmID+"_");
					//s.data=listCCDM.get(i).data;
					Pair e = new Pair();
					e.id="A-B";
					e.el1.name="A";
					e.el2.name="B";
					if(listINT4.get(i).rho!=-1){
						try{
							e.el1.coord1I=Integer.parseInt(listINT4.get(i).data.substring(0,6));
							e.el1.coord1F=Integer.parseInt(listINT4.get(i).data.substring(7,9));
							e.el1.coord2I=Integer.parseInt(listINT4.get(i).data.substring(9,16));
							e.el1.coord2F=Integer.parseInt(listINT4.get(i).data.substring(17,18));
							e.el1.coord_flag=31;
							if(e.el1.coord2I<0){
								e.el1.coord2F*=-1;
							}
							if(e.el1.coord1I!=0 || e.el1.coord2I!=0){
								e.el2.coord1I=(int)(long)Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)));
								e.el2.coord1F=(int)(long)(100*(Converter.radToHrs(Converter.hrsToRad(e.el1.coord1I,e.el1.coord1F)+listINT4.get(i).rho*Math.sin(listINT4.get(i).theta/180*Math.PI)/Math.cos(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)))-e.el2.coord1I));
								e.el2.coord2I=(int)(long)Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI));
								e.el2.coord2F=(int)(long)(10*(Converter.radToGr(Converter.grToRad(e.el1.coord2I,e.el1.coord2F)+listINT4.get(i).rho*Math.cos(listINT4.get(i).theta/180*Math.PI))-e.el2.coord2I));
								e.el2.coord_flag=32;
							}
						}catch(Exception td){
						}
					}
					e.pairWDS="";
					e.observer=listINT4.get(i).dd;
					e.idHD=listINT4.get(i).idHD;
					e.idHIP=listINT4.get(i).idHIP;
					e.idADS=listINT4.get(i).idADS;
					e.idHyi=listINT4.get(i).idHyi;
					e.idDM=listINT4.get(i).idDM;
					e.idBayer=listINT4.get(i).idBayer;
					e.modyfyer[6]='i';
					s.pairs.add(e);
					s.idWDS=listINT4.get(i).idWDS;
					s.data=listINT4.get(i).data;
					s.observ=s.pairs.get(0).observer;
					system.add(s);	
					}
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	private static void interprCEV() {//"logCEV.txt"+
		int xLog=0;
		long timePrev=System.nanoTime();
		try{
			String fileName="logCEV.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
			int f=listCEV.size();
			int h;
			for(int i=0;i<f;i++){
				h=system.size();
				layer1: for(int j=0;j<h;j++){
					try{
						if(system.get(j).data!=null && system.get(j).data!="" && (listCEV.get(i).WDScoord.substring(0,7)).equals(system.get(j).data.substring(0, 7)) && (listCEV.get(i).WDScoord.substring(8,14)).equals(system.get(j).data.substring(9, 15))){
							boolean content=false;
							for(int k=0;k<system.get(j).pairs.size();k++){
								if(listCEV.get(i).Hyi.equals(system.get(j).pairs.get(k).idHyi) ||
										listCEV.get(i).idHIP.equals(system.get(j).pairs.get(k).idHIP) ||
										listCEV.get(i).Hyi.equals(system.get(j).pairs.get(k).idBayer)){//����� ������ ���� ������� ��� ����;
									content=true;
									system.get(j).pairs.get(k).modyfyer[7]='e';
									if(system.get(j).pairs.get(k).idHIP=="") {
										system.get(j).pairs.get(k).idHIP = listCEV.get(i).idHIP;
									}
									system.get(j).pairs.get(k).idCEV=listCEV.get(i).idCEV;
									System.out.println("CEV added:"+listCEV.get(i).idCEV);
									break;
								}
							}
							if(!content){
								Pair e = new Pair();
								e.id="Aa-Ab";
								e.el1.name="Aa";
								e.el2.name="Ab";
								e.idCEV=listCEV.get(i).idCEV;
								e.idHyi=listCEV.get(i).Hyi;
								e.idHIP=listCEV.get(i).idHIP;
								int jj=0;
								int kk=0;
								for(int k=0;k<system.get(j).pairs.size();k++){
									if(system.get(j).pairs.get(k).el1.name=="A"){
										jj=j;
										kk=k;
									}
								}
								e.el1.coord1I=system.get(jj).pairs.get(kk).el1.coord1I;
								e.el1.coord1F=system.get(jj).pairs.get(kk).el1.coord1F;
								e.el1.coord2I=system.get(jj).pairs.get(kk).el1.coord2I;
								e.el1.coord2F=system.get(jj).pairs.get(kk).el1.coord2F;
								e.el2.coord1I=system.get(jj).pairs.get(kk).el2.coord1I;
								e.el2.coord1F=system.get(jj).pairs.get(kk).el2.coord1F;
								e.el2.coord2I=system.get(jj).pairs.get(kk).el2.coord2I;
								e.el2.coord2F=system.get(jj).pairs.get(kk).el2.coord2F;
								e.el1.coord_flag=41;
								system.get(j).findCompOut("A");
								system.get(j).pairs.add(e);
								outer2.write("CP?: "+listCEV.get(i).WDScoord+"  " +listCEV.get(i).Hyi+(char)10);
								outer2.flush();
								e.modyfyer[7]='e';
								outer2.append("$!");
								outer2.flush();
								break layer1;
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				//outer2.append(listCEV.get(i).WDScoord+(char)10);
				//outer2.flush();
				if(xLog*1000<=i){
					//System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
					timePrev=System.nanoTime();
					xLog=(int) (i/1000+1);	
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	private static void interprSB9(String key1) {//"log8.txt"
		System.out.println("interpr SB9");
		try{
			String fileName="log8.txt";
			int f=listSB9.size();
			int h;
			for(int i=0;i<f;i++){
				boolean exists=false;
				h=system.size();
				for(int j=0;j<h;j++){
					for(int k=0;k<system.get(j).pairs.size();k++){
						try{
							if(listSB9.get(i).ADS!="" && system.get(j).pairs.get(k).idADS.equals(listSB9.get(i).ADS) ||
							listSB9.get(i).idFlamsteed!="" && system.get(j).pairs.get(k).idBayer.equals(listSB9.get(i).idFlamsteed) ||
							listSB9.get(i).HD!="" && system.get(j).pairs.get(k).idHD.equals(listSB9.get(i).HD)  ||
							listSB9.get(i).HIP!="" && system.get(j).pairs.get(k).idHIP.equals(listSB9.get(i).HIP) ||
							listSB9.get(i).DM!="" && system.get(j).pairs.get(k).idDM.equals(listSB9.get(i).DM)){
								exists=true;
								system.get(j).pairs.get(k).modyfyer[8]='s';
								//System.out.println(system.get(j).pairs.get(k).idSB9);
								if(system.get(j).pairs.get(k).idADS==""){
									system.get(j).pairs.get(k).idADS=listSB9.get(i).ADS;
								}
								if(system.get(j).pairs.get(k).idHIP==""){
									system.get(j).pairs.get(k).idHIP=listSB9.get(i).HIP;
								}
								if(system.get(j).pairs.get(k).idHD==""){
									system.get(j).pairs.get(k).idHD=listSB9.get(i).HD;
								}
								if(system.get(j).pairs.get(k).idDM==""){
									system.get(j).pairs.get(k).idDM=listSB9.get(i).DM;
								}
								if(system.get(j).pairs.get(k).idBayer==""){
									system.get(j).pairs.get(k).idBayer=listSB9.get(i).idFlamsteed;
								}
								system.get(j).pairs.get(k).idSB9=listSB9.get(i).SB9;
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
				apart: if(exists==false){
					String avalValue=listSB9.get(i).data.substring(0,3);
					if(avalValue.charAt(0)=='0'){
						avalValue=""+avalValue.charAt(1)+avalValue.charAt(2);
					}
					if(!key1.equals(avalValue)){	
						break apart;
					}else{
						//System.out.println("added SB9 system "+avalValue);
					}
					StarSystem s= new StarSystem();
					s.identifyer=listSB9.get(i).generateIdentifyer();
					s.data=listSB9.get(i).data;
					s.idWDS=listSB9.get(i).mainID;
					boolean uniqueSystem=true;
					/*for(int jjj=0;jjj<hhh;jjj++){
						if(s.identifyer.substring(0,10).equals(system.get(jjj).identifyer.substring(0,10))){
							uniqueSystem=false;
							sysNumber=j;
							break;
						}
					}*/
					if(uniqueSystem || !uniqueSystem){
						system.add(s);
						Pair e = new Pair();
						e.id="AB";
						s.pairs.add(e);
						//
						e.modyfyer[8]='s';
						e.el1.name="A";
						e.el2.name="B";
						try {
							e.el1.coord1I = Integer.parseInt(listSB9.get(i).data.substring(0, 6));
							e.el1.coord1F = Integer.parseInt(listSB9.get(i).data.substring(7, 9));
							e.el1.coord2I = Integer.parseInt(listSB9.get(i).data.substring(9, 16));
							e.el1.coord2F = Integer.parseInt(listSB9.get(i).data.substring(17, 18));
							e.el2.coord1I = Integer.parseInt(listSB9.get(i).data.substring(0, 6));
							e.el2.coord1F = Integer.parseInt(listSB9.get(i).data.substring(7, 9));
							e.el2.coord2I = Integer.parseInt(listSB9.get(i).data.substring(9, 16));
							e.el2.coord2F = Integer.parseInt(listSB9.get(i).data.substring(17, 18));
						}catch(Exception l){
							l.printStackTrace();
							System.out.println("for " + listSB9.get(i).data);
						}
						e.el1.coord_flag = 61;
						e.el2.coord_flag = 61;
						e.idSB9=listSB9.get(i).SB9;
						e.idADS=listSB9.get(i).ADS;
						e.idHIP=listSB9.get(i).HIP;
						e.idHD=listSB9.get(i).HD;
						e.idDM=listSB9.get(i).DM;
						e.idBayer=listSB9.get(i).idFlamsteed;
					}
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
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
	public static void write(String xxx){
		System.out.println("writing");
		try{
			String fileName="DATA_RELEASE"+xxx+".txt";
			Writer outer = new FileWriter(new File("C:/WDSparser/"+fileName));
			for(int k=0;k<system.size();k++){
				boolean ss=false;
				for(int j=0;j<system.get(k).pairs.size();j++,ss=true){
					int nn=0;
					if(ss){
						nn=1;
					}
					int length=0;
					int usedLength=0;
					for(int ll=0;ll<system.get(k).pairs.get(j).modyfyer.length;ll++){
						if(system.get(k).pairs.get(j).modyfyer[ll]!=0){
							length+=1;
						}
					}
					for(int stage=nn;stage<4;stage++){
						String x ="";
						x=x+('J');
						if(!system.get(k).data.contains("          ")){
							x=x+(system.get(k).data);
						}else{
							String a1=""+Math.abs(system.get(k).pairs.get(0).el1.coord1I);
							String a2=""+Math.abs(system.get(k).pairs.get(0).el1.coord1F);
							String b1=""+Math.abs(system.get(k).pairs.get(0).el1.coord2I);
							String b2=""+Math.abs(system.get(k).pairs.get(0).el1.coord2F);
							while(a1.length()<6){
								a1="0"+a1;
							}
							while(a2.length()<2){
								a2="0"+a2;
							}
							while(b1.length()<6){
								b1="0"+b1;
							}
							while(b2.length()<1){
								b2="0"+b2;
							}
								x=x+a1+"."+a2;
								boolean c2=true;
								if(system.get(k).pairs.get(0).el1.coord2I<0){
									c2=false;
								}
								if(c2==true){
									x=x+"+";
								}else{
									x=x+"-";
								}
								x=x+b1+"."+b2;
						}
						String n;
						switch(stage){
						case 0:
							n=(":s");
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								//x=x+"f";
								x=x+" ";
							}else{
								x=x+" ";
							}
							x=x+(("          "));
							break;
						case 1:
							n=(":p");
							for(int ite=0;ite<system.get(k).pairs.get(j).el1.newName.length();ite++){
								n=n+(system.get(k).pairs.get(j).el1.newName.charAt(ite));
							}
							n=n+("-");
							for(int ite=0;ite<system.get(k).pairs.get(j).el2.newName.length();ite++){
								n=n+(system.get(k).pairs.get(j).el2.newName.charAt(ite));
							}
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								x=x+" ";
							}else{
								x=x+" ";
							}
							/*if(system.get(k).pairs.get(j).modyfyer.contains("O")){
								System.out.println("Exception x:"+system.get(k).identifyer+" "+system.get(k).pairs.get(j).id);
							}*/
							//system.get(k).pairs.get(j).modyfyer=clearify(system.get(k).pairs.get(j).modyfyer);
							as: for(int hk=0;hk<10;hk++){
								if(system.get(k).pairs.get(j).modyfyer[hk]!=0 && system.get(k).pairs.get(j).modyfyer[hk]!=' '){
									x=x+system.get(k).pairs.get(j).modyfyer[hk];
									system.get(k).pairs.get(j).modyfyer[hk]=0;
									hk++;
									for(;hk<10;hk++){
										x=x+' ';
									}
									usedLength++;
									break as;
								}else{
									x=x+' ';
								}
							}
							break;
						case 2:
							n=(":c");
							for(int ite=0;ite<system.get(k).pairs.get(j).el1.newName.length();ite++){
								n=n+(system.get(k).pairs.get(j).el1.newName.charAt(ite));
							}
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								x=x+" ";
							}else{
								x=x+" ";
							}
							x=x+(("          "));
							break;
						case 3:
							n=(":c");
							for(int ite=0;ite<system.get(k).pairs.get(j).el2.newName.length();ite++){
								n=n+(system.get(k).pairs.get(j).el2.newName.charAt(ite));
							}
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								x=x+" ";
							}else{
								x=x+" ";
							}
							x=x+(("          "));
							break;
						}
						if(stage!=0 || stage==0){
							if(system.get(k).idWDS!=""){
								x=x+((""+system.get(k).idWDS));
							}else{
								x=x+"        ";
							}
							if(system.get(k).pairs.get(j).observer!=null && system.get(k).pairs.get(j).observer!=""){
								int counter = system.get(k).pairs.get(j).observer.length();
								while(counter<8){
									x=x+" ";
									counter++;
								}
								x=x+((" "+system.get(k).pairs.get(j).observer));
							}else{
								x=x+"        ";
							}
						}
						//
						//
						if(stage==1  || stage==0){
							while(x.length()<65){
								x=x+" ";
							}
							x=x+"|";
							if(system.get(k).pairs.get(j).pairWDS!=""){
								int counter = system.get(k).pairs.get(j).pairWDS.length();
								while(counter<8){
									x=x+" ";
									counter++;
								}
								x=x+((system.get(k).pairs.get(j).pairWDS)+"|");
							}else{
								int counter =system.get(k).pairs.get(j).el1.name.length()+system.get(k).pairs.get(j).el2.name.length();
								while(counter<8){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).el1.name+system.get(k).pairs.get(j).el2.name+"|";
							}

							if(system.get(k).pairs.get(j).idHIP!=""){
								Statistica.validateHIP(system.get(k).pairs.get(j).idHIP);
								int counter = system.get(k).pairs.get(j).idHIP.length();
								while(counter<6){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idHIP+"|";
							}else{
								x=x+"      |";
							}

							if(system.get(k).pairs.get(j).idHD!=""){
								Statistica.validateHD(system.get(k).pairs.get(j).idHD);
								int counter = system.get(k).pairs.get(j).idHD.length();
								while(counter<6){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idHD+"|";
							}else{
								x=x+"      |";
							}

							if(system.get(k).pairs.get(j).idSB9!=""){
								int counter =system.get(k).pairs.get(j).idSB9.length();
								while(counter<5){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idSB9+"|";
							}else{
								x=x+"     |";
							}
							if(system.get(k).pairs.get(j).idCEV!=null && system.get(k).pairs.get(j).idCEV!=""){
								int counter =system.get(k).pairs.get(j).idCEV.length();
								while(counter<10){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idCEV+"|";
							}else{
								x=x+"          |";
							}
							if(system.get(k).pairs.get(j).idADS!=""){
								int counter=system.get(k).pairs.get(j).idADS.length();
								while(counter<5){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idADS+"|";
							}else{
								x=x+"     |";
							}

							if(system.get(k).pairs.get(j).idHyi!=""){
								int counter=system.get(k).pairs.get(j).idHyi.length();
								while(counter<10){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idHyi+"|";
							}else{
								x=x+"          |";
							}
							if(system.get(k).pairs.get(j).idBayer!=""){
								int counter =system.get(k).pairs.get(j).idBayer.length();
								while(counter<10){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idBayer+"|";
							}else{
								x=x+"          |";
							}
							if(system.get(k).pairs.get(j).idDM!=""){
								Statistica.validateDM(system.get(k).pairs.get(j).idDM);
								int counter = system.get(k).pairs.get(j).idDM.length();
								while(counter<12){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idDM+"|";
							}else{
								x=x+"            |";
							}
							if(system.get(k).pairs.get(j).idTDSC!=""){
								int counter=system.get(k).pairs.get(j).idTDSC.length();
								while(counter<12){
									x=x+" ";
									counter++;
								}
								x=x+((system.get(k).pairs.get(j).idTDSC+"|"));
							}else{
								x=x+"            |";
							}
							if(system.get(k).pairs.get(j).pairTDSC!=""){
								int counter = system.get(k).pairs.get(j).pairTDSC.length();
								while(counter<5){
									x=x+" ";
									counter++;
								}
								x=x+((system.get(k).pairs.get(j).pairTDSC+"|"));
							}else{
								x=x+"     |";
							}
							if(system.get(k).pairs.get(j).idCCDM!=""){
								int counter = system.get(k).pairs.get(j).idCCDM.length();
								while(counter<12){
									x=x+" ";
									counter++;
								}
								x=x+((system.get(k).pairs.get(j).idCCDM+"|"));
							}else{
								x=x+"            |";
							}
							if(system.get(k).pairs.get(j).pairCCDM!=""){
								int counter = system.get(k).pairs.get(j).pairCCDM.length();
								while(counter<5){
									x=x+" ";
									counter++;
								}
								x=x+((system.get(k).pairs.get(j).pairCCDM+"|"));
							}else{
								x=x+"     |";
							}
						}
						if(stage==2){
							while(x.length()<67){
								x=x+" ";
							}
							if(0==0) {
								int counter = system.get(k).pairs.get(j).el1.name.length();
								while(counter<7){
									x=x+" ";
									counter++;
								}
								for (int ite = 0; ite < system.get(k).pairs.get(j).el1.name.length(); ite++) {
									x = x + (system.get(k).pairs.get(j).el1.name.charAt(ite));
								}
							}else{
								int counter = 0;
								while(counter<7){
									x=x+" ";
									counter++;
								}
							}
							x=x+"|";
							if(system.get(k).pairs.get(j).el1.idHIP!=""){
								Statistica.validateHIP(system.get(k).pairs.get(j).el1.idHIP);
								int counter2 = system.get(k).pairs.get(j).el1.idHIP.length();
								while(counter2<6){
									x=x+" ";
									counter2++;
								}
								x=x+system.get(k).pairs.get(j).el1.idHIP+"|";
							}else{
								x=x+"      |";
							}
							if(system.get(k).pairs.get(j).el1.idHD!=""){
								Statistica.validateHD(system.get(k).pairs.get(j).el1.idHD);
								int counter2 = system.get(k).pairs.get(j).el1.idHD.length();
								while(counter2<6){
									x=x+" ";
									counter2++;
								}
								x=x+system.get(k).pairs.get(j).el1.idHD+"|";
							}else{
								x=x+"      |";
							}
							x=x+"     |          |     |          |          |";
							if(system.get(k).pairs.get(j).el1.idDM!=""){
								Statistica.validateDM(system.get(k).pairs.get(j).el1.idDM);
								int counter3 = system.get(k).pairs.get(j).el1.idDM.length();
								while(counter3<12){
									x=x+" ";
									counter3++;
								}
								x=x+system.get(k).pairs.get(j).el1.idDM+"|";
							}else{
								x=x+"            |";
							}
							//asd

						}
						if(stage==3){
							while(x.length()<67){
								x=x+" ";
							}
							if(0==0) {
								int counter = system.get(k).pairs.get(j).el2.name.length();
								while(counter<7){
									x=x+" ";
									counter++;
								}
								for (int ite = 0; ite < system.get(k).pairs.get(j).el2.name.length(); ite++) {
									x = x + (system.get(k).pairs.get(j).el2.name.charAt(ite));
								}
							}else{
								int counter = 0;
								while(counter<7){
									x=x+" ";
									counter++;
								}
							}
							x=x+"|";
							if(system.get(k).pairs.get(j).el2.idHIP!=""){
								Statistica.validateHIP(system.get(k).pairs.get(j).el2.idHIP);
								int counter2 = system.get(k).pairs.get(j).el2.idHIP.length();
								while(counter2<6){
									x=x+" ";
									counter2++;
								}
								x=x+system.get(k).pairs.get(j).el2.idHIP+"|";
							}else{
								x=x+"      |";
							}
							if(system.get(k).pairs.get(j).el2.idHD!=""){
								Statistica.validateHD(system.get(k).pairs.get(j).el2.idHD);
								int counter2 = system.get(k).pairs.get(j).el2.idHD.length();
								while(counter2<6){
									x=x+" ";
									counter2++;
								}
								x=x+system.get(k).pairs.get(j).el2.idHD+"|";
							}else{
								x=x+"      |";
							}
							x=x+"     |          |     |          |          |";
							if(system.get(k).pairs.get(j).el2.idDM!=""){
								Statistica.validateDM(system.get(k).pairs.get(j).el2.idDM);
								int counter3 = system.get(k).pairs.get(j).el2.idDM.length();
								while(counter3<12){
									x=x+" ";
									counter3++;
								}
								x=x+system.get(k).pairs.get(j).el2.idDM+"|";
							}else{
								x=x+"            |";
							}
						}
						while(x.length()<185){
							x=x+" ";
						}
						if(stage!=0  || stage==0){
							if(system.get(k).pairs.get(j).idBayer!=""){
								int counter = system.get(k).pairs.get(j).idBayer.length();//���� ����� ��������
								x=x+((" "+system.get(k).pairs.get(j).idBayer));
							}else if(system.get(k).pairs.get(j).idHyi!=""){
								int counter = system.get(k).pairs.get(j).idHyi.length();//���� ����� ��������
								x=x+((" "+system.get(k).pairs.get(j).idHyi));
							}else if(system.get(k).pairs.get(j).idSB9!=""){
								int counter = system.get(k).pairs.get(j).idSB9.length();
								x=x+(("SB9 "+system.get(k).pairs.get(j).idSB9));
							}else if(system.get(k).pairs.get(j).idCEV!=null && system.get(k).pairs.get(j).idCEV!=""){
								int counter = system.get(k).pairs.get(j).idCEV.length();
								x=x+(("CEV  "+system.get(k).pairs.get(j).idCEV));
							}else if(system.get(k).pairs.get(j).idADS!=""){
								int counter = system.get(k).pairs.get(j).idADS.length();
								x=x+(("ADS "+system.get(k).pairs.get(j).idADS));
							}else if(system.get(k).pairs.get(j).idHIP!=""){
								int counter = system.get(k).pairs.get(j).idHIP.length();
								x=x+(("HIP "+system.get(k).pairs.get(j).idHIP));
							}else if(system.get(k).pairs.get(j).idHD!="" && system.get(k).pairs.get(j).idHD!=null && (system.get(k).pairs.get(j).idHD.contains("+") || system.get(k).pairs.get(j).idHD.contains("-"))){
								int counter = system.get(k).pairs.get(j).idHD.length();
								x=x+(("HD "+system.get(k).pairs.get(j).idHD));
							}else if(system.get(k).pairs.get(j).idDM!="" && system.get(k).pairs.get(j).idDM!=null  && (system.get(k).pairs.get(j).idDM.contains("+") || system.get(k).pairs.get(j).idDM.contains("-"))){
								int counter = system.get(k).pairs.get(j).idDM.length();
								x=x+(("DM "+system.get(k).pairs.get(j).idDM));
							}else if(system.get(k).idWDS!=""){
								int counter = system.get(k).idWDS.length();
								x=x+(("WDS "+system.get(k).idWDS));
							}else if(system.get(k).pairs.get(j).idCCDM!=""){
								int counter = system.get(k).pairs.get(j).idCCDM.length();
								x=x+(("CCDM "+system.get(k).pairs.get(j).idCCDM));
							}
						}
						if(stage==2){
							while(x.length()<205){
								x=x+" ";
							}
							boolean c2=true;
							if(system.get(k).pairs.get(j).el1.coord2I<0){
								c2=false;
							}
							String a1=""+Math.abs(system.get(k).pairs.get(j).el1.coord1I);
							String a2=""+Math.abs(system.get(k).pairs.get(j).el1.coord1F);
							String b1=""+Math.abs(system.get(k).pairs.get(j).el1.coord2I);
							String b2=""+Math.abs(system.get(k).pairs.get(j).el1.coord2F);
							while(a1.length()<6){
								a1="0"+a1;
							}
							while(a2.length()<2){
								a2="0"+a2;
							}
							while(b1.length()<6){
								b1="0"+b1;
							}
							while(b2.length()<1){
								b2="0"+b2;
							}
							if(a1!="000000" && b1!="000000"){
								x=x+a1+"."+a2;
								if(c2==true){
									x=x+"+";
								}else{
									x=x+"-";
								}
								x=x+b1+"."+b2;
								x=x+"  "+system.get(k).pairs.get(j).el1.coord_flag;
							}else{
								x=x+"*";
							}
						}
						if(stage==3){
							while(x.length()<205){
								x=x+" ";
							}
							boolean c2=true;
							if(system.get(k).pairs.get(j).el2.coord2I<0){
								c2=false;
							}
							String a1=""+Math.abs(system.get(k).pairs.get(j).el2.coord1I);
							String a2=""+Math.abs(system.get(k).pairs.get(j).el2.coord1F);
							String b1=""+Math.abs(system.get(k).pairs.get(j).el2.coord2I);
							String b2=""+Math.abs(system.get(k).pairs.get(j).el2.coord2F);
							while(a1.length()<6){
								a1="0"+a1;
							}
							while(a2.length()<2){
								a2="0"+a2;
							}
							while(b1.length()<6){
								b1="0"+b1;
							}
							while(b2.length()<1){
								b2="0"+b2;
							}
							if(a1!="000000" && b1!="000000"){
								x=x+a1+"."+a2;
								if(c2==true){
									x=x+"+";
								}else{
									x=x+"-";
								}
								x=x+b1+"."+b2;
								x=x+"  "+system.get(k).pairs.get(j).el2.coord_flag;
							}else{
								x=x+"*";
							}
						}
						if(nameReal(k,j,stage)){
							//System.out.println(x);
							if(stage==0){
								if(system.get(k).out==false){
									outer.write(x+""+(char)10);
									outer.flush();
									system.get(k).out=true;
								}
							}
							if(stage==1){
								if(stage==1 && usedLength<length){
									stage--;
								}
								if(true || system.get(k).pairs.get(j).out==false){
									outer.write(x+""+(char)10);
									outer.flush();
									system.get(k).pairs.get(j).out=true;
								}
							}
							if(stage==2){
								if(system.get(k).pairs.get(j).el1.out==false){
									system.get(k).pairs.get(j).el1.out=true;
									outer.write(x+""+(char)10);
									outer.flush();
								}
							}
							if(stage==3){
								if(system.get(k).pairs.get(j).el2.out==false){
									system.get(k).pairs.get(j).el2.out=true;
									outer.write(x+""+(char)10);
									outer.flush();
								}
							}
						}
					}
				}

			}
		}catch(Exception e){
			e.printStackTrace();
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
	public static void interprCCDMr(){//"log3.txt"
		int xLog=0;
		long timePrev=System.nanoTime();
		try{
			String fileName="log3.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
//		System.out.println(listSCO.size());
//		System.out.println(system.size());
		int f=listCCDM.size();
		int h;
		for(int i=0;i<f;i++){
			h=system.size();
			boolean systemExistence=false;
			upper: for(int j=0;j<h;j++){
				for(int dd=0;dd<system.get(j).pairs.size();dd++){
					boolean zzFixed=false;
					if(listCCDM.get(i).pairCCDM!="ZZ" && listCCDM.get(i).wdsID!=null && system.get(j).idWDS!=null && system.get(j).idWDS.length()>10 && (listCCDM.get(i).wdsID).equals(system.get(j).idWDS.substring(0, 10)) || 
						system.get(j).pairs.get(dd).idCCDM!=null && listCCDM.get(i).ccdmID.equals(system.get(j).pairs.get(dd).idCCDM) ||
						system.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(system.get(j).CCDMid)){
						systemExistence=true;
						boolean existence=false;
						iter++;
						int number = 0;
						Pair ee = new Pair();
						ee.id=listCCDM.get(i).pairCCDM;
						ee.pairCCDM=listCCDM.get(i).pairCCDM;
						ee.idCCDM=listCCDM.get(i).ccdmID;
						ee.observer=listCCDM.get(i).nameOfObserver;
						ee.clean();
						ee.dispWithoutRepeatCheckerInWriter(j);
						for(int k=0;k<system.get(j).pairs.size();k++){
						//	System.out.println("case"+j+ee.pairCCDM+"_"+system.get(j).pairs.get(k).pairCCDM);//general output
							if(system.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM)){
								existence=true;
								number=i;
								if(system.get(j).pairs.get(k).idHD==""){
									if(listCCDM.get(i).HD!=""){
										system.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
										if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
											system.get(j).pairs.get(k).el1.idHD=listCCDM.get(i).HD;
										}else{
											system.get(j).pairs.get(k).el2.idHD=listCCDM.get(i).HD;
										}
									}
								}
								if(system.get(j).pairs.get(k).idHIP=="" ){
									if(listCCDM.get(i).HIP!=""){
										system.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
										if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
											system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).HIP;
										}else{
											system.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).HIP;
										}
									}
								}
								if(system.get(j).pairs.get(k).idADS==""){
									system.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
								}
								if(system.get(j).pairs.get(k).idDM=="" ){
									if(listCCDM.get(i).DM!=""){
										system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
										if((""+listCCDM.get(i).componentInfo)==system.get(j).pairs.get(k).el1.name){
											system.get(j).pairs.get(k).el1.idHIP=listCCDM.get(i).DM;
										}else{
											system.get(j).pairs.get(k).el2.idHIP=listCCDM.get(i).DM;
										}
									}
								}
								system.get(j).pairs.get(k).idCCDM=listCCDM.get(i).ccdmID;
								if(listCCDM.get(i).astrometric){
									system.get(j).pairs.get(k).modyfyer[4]='a';
									//System.out.println("ASTROMETRIC!");
								}else{
									system.get(j).pairs.get(k).modyfyer[4]='v';
								}
								break upper;
							}
						}
						if(!existence && listCCDM.get(i).pairCCDM!="ZZ"){
							outer2.write("err: ccdmID:"+listCCDM.get(i).ccdmID+" pair:"+listCCDM.get(i).pairCCDM+" "+(char)10);
							outer2.flush();
							Pair eea = new Pair();
							eea.id=listCCDM.get(i).pairCCDM;
							eea.idCCDM=listCCDM.get(i).ccdmID;
							eea.pairCCDM=listCCDM.get(i).pairCCDM;
							eea.observer=listCCDM.get(i).nameOfObserver;
							eea.el1=ee.el1;
							eea.el2=ee.el2;
							eea.idHD=listCCDM.get(i).HD;
							eea.idHIP=listCCDM.get(i).HIP;
							eea.idADS=listCCDM.get(i).ADS;
							eea.idDM=listCCDM.get(i).DM;
							eea.el2.idDM=listCCDM.get(i).DM;
							eea.el2.idHIP=listCCDM.get(i).HIP;
							eea.el2.idHD=listCCDM.get(i).HD;
							if(eea.el1.name.equals("A")){
								if(zzFixed==false){
									ccdmFillZZ(eea.el1, i);
									zzFixed=true;
								}
							}
							if(listCCDM.get(i).astrometric){
								outer2.write("Close pair: ccdmID:"+listCCDM.get(i).ccdmID+" pair:"+listCCDM.get(i).pairCCDM+" "+(char)10);
								outer2.flush();
								boolean closeExists=false;
								for(int ij=0;ij<system.get(j).pairs.size();ij++){
									if(system.get(j).pairs.get(ij).el1.name.equals("Aa")){
										closeExists=true;
										break;
									}
								}
								if(closeExists){
									eea.id="Aaa-Aab";
									eea.el1.name="Aaa";
									eea.el2.name="Aab";
								}else{
									eea.id="Aa-Ab";
									eea.el1.name="Aa";
									eea.el2.name="Ab";	
								}
								eea.modyfyer[4]='a';
								//System.out.println("astrometric!");
							}else{
								eea.modyfyer[4]='v';
							}
							eea.clean();
							eea.dispWithoutRepeatCheckerInWriter(-1);
							system.get(j).pairs.add(eea);
						}
						break upper;
					}
				}
			}
			if(systemExistence==false){
				StarSystem s= new StarSystem();
				system.add(s);
				s.identifyer=listCCDM.get(i).ccdmID+listCCDM.get(i).nameOfObserver;
				//System.out.println(listCCDM.get(i).ccdmID+"_");
				//s.data=listCCDM.get(i).data;
				s.observ=listCCDM.get(i).nameOfObserver;
				s.fake=true;
				s.CCDMid=listCCDM.get(i).ccdmID;
				s.data=s.CCDMid.substring(0,5)+"0.00"+s.CCDMid.substring(5,10)+"00.0";
				Pair ee = new Pair();
				ee.id=listCCDM.get(i).pairCCDM;
				//ee.el1.coord1I=listCCDM.get(i).coord_I1_1;
				//ee.el1.coord1F=listCCDM.get(i).coord_F1_1;
				//ee.el1.coord2I=listCCDM.get(i).coord_I1_2;
				//ee.el1.coord2F=listCCDM.get(i).coord_F1_2;
//				ee.el2.coord1I=listCCDM.get(i).coord_I2_1;
//				ee.el2.coord1F=listCCDM.get(i).coord_F2_1;
//				ee.el2.coord2I=listCCDM.get(i).coord_I2_2;
//				ee.el2.coord2F=listCCDM.get(i).coord_F2_2;
				ee.clean();
				ee.dispWithoutRepeatCheckerInWriter(-1);
				Pair e = new Pair();
				e.id=ee.id;
				e.el1=ee.el1;
				e.el2=ee.el2;
				e.pairCCDM=listCCDM.get(i).pairCCDM;
				e.idCCDM=listCCDM.get(i).ccdmID;
				e.idHD=listCCDM.get(i).HD;
				e.idHIP=listCCDM.get(i).HIP;
				e.idADS=listCCDM.get(i).ADS;
				e.idDM=listCCDM.get(i).DM;
				e.el2.idDM=listCCDM.get(i).DM;
				e.el2.idHIP=listCCDM.get(i).HIP;
				e.el2.idHD=listCCDM.get(i).HD;
				if(e.el1.name.equals("A")){
					ccdmFillZZ(e.el1, i);
				}
				if(listCCDM.get(i).astrometric){
					e.modyfyer[4]='a';
						e.id="A-B";
						e.el1.name="A";
						e.el2.name="B";	
					outer2.write("ErrorSysEx1Astrmtr: "+s.identifyer+""+(char)10);
				}else{
					//e.modyfyer="v";
					outer2.write("ErrorSysEx3: "+s.identifyer+""+(char)10);
				}
				s.pairs.add(e);
				outer2.flush();
			}
			if(xLog*1000<=i){
				//System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
				timePrev=System.nanoTime();
				xLog=(int) (i/1000+1);	
			}
		}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	public static void ccdmFillZZ(Component el1, int i){
		try{
			while(i>=0){
				if(listCCDM.get(i).pairCCDM=="ZZ"){
					el1.idDM=listCCDM.get(i).DM;
					el1.idHIP=listCCDM.get(i).HIP;
					el1.idHD=listCCDM.get(i).HD;
					return;
				}
				i--;
			}
			System.out.println("Wrong cashes borders!");
		}catch(Exception e){
			System.out.println("Wrong cashes borders!");
		}
	}
	public static void predInterprCCDM(){
		int f=listCCDM.size();
		int h;
		for(int i=0;i<f;i++){
			if(listCCDM.get(i).pairCCDM!="ZZ"){
				try{
					if(listCCDM.get(i).pairCCDM.charAt(0)=='A'){
						int j=i-1;
						while(j>0){
							if(listCCDM.get(j).pairCCDM=="ZZ"){
								//listCCDM.get(i).coord_I1_1=listCCDM.get(j).coord_I2_1;
								//listCCDM.get(i).coord_I1_2=listCCDM.get(j).coord_I2_2;
								//listCCDM.get(i).coord_F1_1=listCCDM.get(j).coord_F2_1;
								//listCCDM.get(i).coord_F1_2=listCCDM.get(j).coord_F2_2;
								break;
							}
							j--;
						}
					}else{
						int j=i-1;
						while(j>0){
							if(listCCDM.get(j).pairCCDM.charAt(1)==listCCDM.get(i).pairCCDM.charAt(0)){
								//listCCDM.get(i).coord_I1_1=listCCDM.get(j).coord_I2_1;
								//listCCDM.get(i).coord_I1_2=listCCDM.get(j).coord_I2_2;
								//listCCDM.get(i).coord_F1_1=listCCDM.get(j).coord_F2_1;
								//listCCDM.get(i).coord_F1_2=listCCDM.get(j).coord_F2_2;
								break;
							}
							j--;
						}
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	public static void interprCCDMcoords(){
		int f=system.size();
		int h=listCCDMcoords.size();
		for(int i=0;i<f;i++){
			for(int j=0;j<h;j++){
				for(int k=0;k<system.get(i).pairs.size();k++){
					if(system.get(i).pairs.get(k).idCCDM.equals(listCCDMcoords.get(j).ccdmID)){
						if(system.get(i).pairs.get(k).pairCCDM.equals(listCCDMcoords.get(j).pairCCDM)){
							if(system.get(i).pairs.get(k).el1.coord1I==0 && system.get(i).pairs.get(k).el1.coord2I==0){
								try{
									//System.out.println("CCDMcoords concatenated");
									system.get(i).pairs.get(k).el1.coord1I=listCCDMcoords.get(j).el1_1I;
									system.get(i).pairs.get(k).el1.coord1F=listCCDMcoords.get(j).el1_1F;
									system.get(i).pairs.get(k).el1.coord2I=listCCDMcoords.get(j).el1_2I;
									system.get(i).pairs.get(k).el1.coord2F=listCCDMcoords.get(j).el1_2F;
									system.get(i).pairs.get(k).el2.coord1I=listCCDMcoords.get(j).el2_1I;
									system.get(i).pairs.get(k).el2.coord1F=listCCDMcoords.get(j).el2_1F;
									system.get(i).pairs.get(k).el2.coord2I=listCCDMcoords.get(j).el2_2I;
									system.get(i).pairs.get(k).el2.coord2F=listCCDMcoords.get(j).el2_2F;
									system.get(i).pairs.get(k).el1.coord_flag=listCCDMcoords.get(j).flag1;
									system.get(i).pairs.get(k).el2.coord_flag=listCCDMcoords.get(j).flag2;
								}catch(Exception td){
									td.printStackTrace();
								}
							}
						}
					}
				}
			}
		}
	}
}