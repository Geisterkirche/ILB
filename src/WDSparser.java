import java.io.BufferedWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;

public class WDSparser {
	static int iter=0;
	static ArrayList<NodeStar> globalList = new ArrayList<NodeStar>();
	//для WCT лист не нужен, там все интерпретируется сразу;
	static ArrayList<NodeSCO> listSCO = new ArrayList<NodeSCO>();
	static LinkedList<NodeCCDM> listCCDM = new LinkedList<NodeCCDM>();
	static LinkedList<NodeTDSC> listTDSC = new LinkedList<NodeTDSC>();

	static ArrayList<NodeINT4> listINT4 = new ArrayList<NodeINT4>();
	static ArrayList<NodeCEV> listCEV = new ArrayList<NodeCEV>();
	static ArrayList<NodeGCVS> listGCVS = new ArrayList<NodeGCVS>();
	static ArrayList<NodeSB9> listSB9 = new ArrayList<NodeSB9>();
	static ArrayList<NodeHMXB> listHMXB = new ArrayList<NodeHMXB>();
	static ArrayList<NodeLMXB> listLMXB = new ArrayList<NodeLMXB>();
	
	public static ArrayList<StarSystem> system= new ArrayList<StarSystem>();
	
	public static void main(String[] args){
		
		//parseFile();
		//interpr();
		//globalList.clear();
		//interprCCDM();
		//try {
			//parseWCT3();
			//interprCCDM2();
			//parseCCDM();
			//interprCCDM2();
			
			//parseTDSC();
			//interprTDSC2();
		//} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		/*for(int i=48;i<57;i++){
			System.out.print((char)i);
		}/*
		for(int i=0;i<100;i++){
			System.out.println(('1'==i)+""+i);
		}*/
		////////////////////
		// ПЕРВЫЙ КАТАЛОГ //
		////////////////////
		
		System.out.println("парсим первый каталог");
		parseFile();
		System.out.println("Интерпретация");
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
		
		System.out.println("system.size() "+system.size());
		
		////////////////////
		// ВТОРОЙ КАТАЛОГ //
		////////////////////
		System.out.println("парсим второй каталог");
		parseSCO();
		System.out.println("Интерпретация");
		interprSCO();
		listSCO.clear();
		System.out.println("system.size() "+system.size());
		////output();
		 
		////////////////////
		// ТРЕТИЙ КАТАЛОГ //
		////////////////////
		
		System.out.println("парсим WCT");
		parseWCT();
		System.out.println("Интерпретация");
		System.out.println("system.size() "+system.size());
		////interprWCT();
		////listWCT.clear();
		//outputWCT();
		
		/////////////////////
		//ЧЕТВЕРТЫЙ КАТАЛОГ//
		/////////////////////
		
		System.out.println("парсим CCDMr");
		parseCCDM();
		System.out.println("Интерпретация");
		interprCCDMr();
		listCCDM.clear();
		System.out.println("system.size() "+system.size());
		/////////////////////
		//  ПЯТЫЙ КАТАЛОГ  //
		/////////////////////
		
		System.out.println("парсим TDSC");
		parseTDSC();
		System.out.println("Интерпретация");
		interprTDSC();
		listTDSC.clear();
		System.out.println("system.size() "+system.size());
		//////////////////////
		//  Шестой КАТАЛОГ  //
		//////////////////////
		System.out.println("парсим INT4");
		parseINT4();
		System.out.println("Интерпретация");
		interprINT4();
		
		///////////////////////
		//  Седьмой КАТАЛОГ  //
		//////////////////////
		/*
		System.out.println("парсим CEV");
		parseCEV();
		System.out.println("Интерпретация");
		interprCEV();*/
		
		///////////////////////
		//  восьмой КАТАЛОГ  //
		///////////////////////
		System.out.println("парсим GCVS");
		parseGCVS();
		System.out.println("Интерпретация");
		interprGCVS();
		
		///////////////////////
		//  девятый КАТАЛОГ  //
		///////////////////////
		System.out.println("парсим SB9");
		parseSB9();
		System.out.println("Интерпретация");
		interprSB9();
		
		////////////////////////////
		//  одиннадцатый КАТАЛОГ  //
		////////////////////////////
		/*
		System.out.println("парсим HMXB");
		parseHMXB();
		*/
		////////////////////////////
		//  двеннадцатый КАТАЛОГ  //
		////////////////////////////
		/*/
		System.out.println("парсим LMXB");
		parseLMXB();		-+/
		///////////////////
		//  ЗАКЛЮЧЕНИЕ  ///
		///////////////////
		*/

		
		System.out.println("Сжатие");
		collapse();
		System.out.println("Запись в файл");
		write();
		System.out.println("Программа закончила работу");

	}
	public static void parseFile(){
		try {//String[]fileName={"data06.txt","data12.txt","data18.txt","data24.txt"};
		String[] fileName={"data06.txt"};
			for(int h=0;h<fileName.length;h++){
				System.out.println("Stage:"+h);
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
						s=s.substring(0, s.length()-2);
						NodeStar star = new NodeStar(s);
						globalList.add(star);
						ss = new StringBuffer();
					}
					if(d-i<1){
						String s=ss.toString();
						NodeStar star = new NodeStar(s);
						globalList.add(star);
						ss = new StringBuffer();
					}
				}
			}
			System.out.println("Файлик успешно прочитан!");
		 } catch (Exception e) {
		  		System.out.println("Плохи дела!");
		  		e.printStackTrace();
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void parseCCDM(){
		try { 
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
						s=s.substring(0, s.length()-2);
						//System.out.println(s);
						NodeCCDM star = new NodeCCDM(s);
						listCCDM.add(star);
						ss = new StringBuffer();
					}
				}
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
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
				String prevString="           ";
				StringBuffer ss = new StringBuffer();
				long d = dataFile.length();
				for(long i=0;i<d;i++){
					c = (char) in.read();
					ss.append(c);
					if(c==10){
						String s=ss.toString();
						s=s.substring(0, s.length()-1);
						if(!s.substring(0,4).equals(prevString.substring(0,4))){
							NodeGCVS star = new NodeGCVS(s);
							listGCVS.add(star);
						}else{
							listGCVS.get(listGCVS.size()-1).addEff(s);
						}
						prevString=s;
						//System.out.println(s);
						ss = new StringBuffer();
					}
				}
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	public static void parseWCT(){//"log2.txt"
		try { 	
				String fileName2="log2.txt";
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
						int h=system.size();
						boolean systemExistence=false;
						for(int j=0;j<h;j++){
							if((star.parameter[0]).equals(system.get(j).idWDS.substring(0, 10)) && star.parameter[0]!=""  || 
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
										system.get(j).idWDS=star.parameter[0];//под вопросом
										system.get(j).pairs.get(k).idCCDM=star.parameter[2];//
										system.get(j).pairs.get(k).idTDSC=star.parameter[4];//
										system.get(j).pairs.get(k).pairWDS=star.parameter[1];
										system.get(j).pairs.get(k).pairCCDM=star.parameter[3];
										system.get(j).pairs.get(k).pairTDSC=star.parameter[5];
										system.get(j).pairs.get(k).modyfyer[1]='v';
										break;
									}
								}
								if(!existence){//не заходит
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
										if(!existence){//не заходит
											System.out.println("Error:"+star.parameter[0]+"_"+star.parameter[1]+"_"+star.parameter[2]+"_"+star.parameter[3]+"_"+star.parameter[4]+"_"+star.parameter[5]);
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
						if(systemExistence==false){//не заходил
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		
	}
	private static void parseCEV(){
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
	}
	private static void parseINT4() {
		try { 
			  String fileName="dataINT4.txt";
				File dataFile = new File("C:/WDSparser/"+fileName);	
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
							//System.out.println(s);
						}else{
							NodeINT4 star = new NodeINT4(s);
							listINT4.add(star);
							ss = new StringBuffer();
							s = new ArrayList<String>();
						}
					}
				}
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
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
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
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
				if(globalList.get(i).modyfyer2[1]!=0){
					e.modyfyer[0]=globalList.get(i).modyfyer2[1];
				}
				e.clean();
				e.disp();
				system.get(system.size()-1).pairs.add(e);
			}else{
				Pair e = new Pair();
				e.id=globalList.get(i).pair;
				e.observer=globalList.get(i).nameOfObserver;
				e.pairWDS=globalList.get(i).pair;
				e.idDM=globalList.get(i).idDM;
				e.modyfyer[0]=globalList.get(i).modyfyer2[0];
				if(globalList.get(i).modyfyer2[1]!=0){
					e.modyfyer[0]=globalList.get(i).modyfyer2[1];
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
								system.get(j).pairs.get(k).idHyi=listSCO.get(i).idHyi;
								system.get(j).pairs.get(k).idBayer=listSCO.get(i).idBayer;
								if(system.get(j).pairs.get(k).modyfyer[1]=='O'){
									system.get(j).pairs.get(k).modyfyer[2]='o';
									system.get(j).pairs.get(k).modyfyer[1]='o';
								}else{
									system.get(j).pairs.get(k).modyfyer[2]='o';
									System.out.println("Exception y:"+system.get(j).identifyer+system.get(j).pairs.get(k).id);
									
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
							e.idHyi=listSCO.get(i).idHyi;
							e.modyfyer[2]='o';
							e.clean();
							system.get(j).pairs.add(e);
							outer2.write("ExceptionPairExistence1: "+listSCO.get(i).idWDS+" "+listSCO.get(i).nameOfObserver+" "+listSCO.get(i).pairWDS+" "+(char)10);
							outer2.flush();
						}else{//случай несовпадающего открывателя - разбивка компонента а на два
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
							e.idHyi=listSCO.get(i).idHyi;
							e.modyfyer[2]='o';
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
			if(systemExistence==false){
				StarSystem s= new StarSystem();
				system.add(s);
				s.idWDS=listSCO.get(i).idWDS;
				s.identifyer=listSCO.get(i).idWDS+listSCO.get(i).nameOfObserver;
				s.data=listSCO.get(i).data;
				s.observ=listSCO.get(i).nameOfObserver;
				s.fake=false;
				outer2.write("ExceptionSystemExistence: "+s.identifyer+""+(char)10);
				Pair ee = new Pair();
				ee.id=listSCO.get(i).pairWDS;
				ee.clean();
				ee.dispWithoutRepeatCheckerInWriter(-1);
				Pair e = new Pair();
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
				outer2.flush();
			}
		}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	public static void interprTDSC(){//"log4.txt"
		int xLog=0;
		long timePrev=System.nanoTime();
		try{
			String fileName="logTDSC.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
			int f=listTDSC.size();
			int h;
			for(int i=0;i<f;i++){
				h=system.size();
				boolean systemExistence=false;
				for(int j=0;j<h;j++){
					/*System.out.println("a"+system.get(j).mainID);
					System.out.println("b"+system.get(j).identifyer);
					System.out.println("c"+system.get(j).observ);
					System.out.println("d"+system.get(j).mainID.substring(0, 10));-*/
					try{
						int u=Integer.parseInt(listTDSC.get(i).TDSCid);
						for(int k=0;k<system.get(j).pairs.size();k++){
							int gfg=Integer.parseInt(system.get(j).pairs.get(k).idTDSC);
							if(system.get(j).pairs.get(k).idTDSC!=null && system.get(j).pairs.get(k).idTDSC!="" &&  u==gfg){
								iter++;
								system.get(j).pairs.get(k).modyfyer[3]='v';
								if(system.get(j).pairs.get(k).idHD==""){
									system.get(j).pairs.get(k).idHD=listTDSC.get(i).HD;
								}else{
									if(!(system.get(j).pairs.get(k).idHD.contains(listTDSC.get(i).HD) || listTDSC.get(i).HD.contains(system.get(j).pairs.get(k).idHD))){
										outer2.write("CompareError HD:"+system.get(j).pairs.get(k).idHD+"_"+listTDSC.get(i).HD+(char)10);
									}
								}
								if(system.get(j).pairs.get(k).idHIP==""){
									system.get(j).pairs.get(k).idHIP=listTDSC.get(i).HIPid;
								}else{
									if(!(system.get(j).pairs.get(k).idHIP.contains(listTDSC.get(i).HIPid) || listTDSC.get(i).HIPid.contains(system.get(j).pairs.get(k).idHIP))){
										outer2.write("CompareError HIP:"+system.get(j).pairs.get(k).idHIP+"_"+listTDSC.get(i).HIPid+(char)10);
									}
								}
								//outer2.write("Existence: HD "+listTDSC.get(i).HD+" CCDMidPAIR "+listTDSC.get(i).CCDMidPAIR+" TDSCid "+listTDSC.get(i).TDSCid+" "+(char)10);
								//outer2.flush();
								break;
							}
						}
					}catch(Exception rf){
						//rf.printStackTrace();	
					}
				}
				if(systemExistence==false){
					outer2.write("ExceptionSysExistence: HD "+listTDSC.get(i).pairTDSC+"  "+listTDSC.get(i).TDSCid+" "+(char)10);
					outer2.flush();
				}
				if(xLog*1000<=i){
					System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
					timePrev=System.nanoTime();
					xLog=(int) (i/1000+1);	
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	public static void interprWCT(){
		/*
		try{
			String fileName="log2.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
//		System.out.println(listSCO.size());
//		System.out.println(system.size());
		int f=listWCT.size();
		int h;
		for(int i=0;i<f;i++){
			h=system.size();
			boolean systemExistence=false;
			for(int j=0;j<h;j++){
				/*System.out.println("a"+system.get(j).mainID);
				System.out.println("b"+system.get(j).identifyer);
				System.out.println("c"+system.get(j).observ);
				System.out.println("d"+system.get(j).mainID.substring(0, 10));-
				if((listWCT.get(i).parameter[0]).equals(system.get(j).mainID.substring(0, 10))){
					boolean existence=false;
					system.get(j).CCDMid=listWCT.get(i).parameter[2];
					system.get(j).TDSCid=listWCT.get(i).parameter[4];
					iter++;
					int number = 0;
					Pair ee = new Pair();
					ee.id=listWCT.get(i).parameter[1];
					ee.clean();
					ee.dispWithoutRepeatCheckerInWriter(j);
					for(int k=0;k<system.get(j).pairs.size();k++){
						if(system.get(j).pairs.get(k).id.equals(ee.id)){
							existence=true;
							number=i;
							system.get(j).pairs.get(k).pairCCDM=listWCT.get(i).parameter[3];
							system.get(j).pairs.get(k).pairTDSC=listWCT.get(i).parameter[5];
							break;
						}
					}
					if(!existence){
						outer2.write("ExceptionPairExistence: "+listWCT.get(i).parameter[0]+" "+listWCT.get(i).parameter[1]+" "+(char)10);
						outer2.flush();
					}
					systemExistence=true;
					break;
				}
			}
			if(systemExistence==false){
				outer2.write("ExceptionSystemExistence: "+listWCT.get(i).parameter[0]+""+(char)10);
				outer2.flush();
			}
		}
		}catch(Exception g){
			g.printStackTrace();
		}*/
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
							//система поймана.
							if(system.get(j).pairs.get(dd).pairCCDM.equals(listCCDM.get(i).pairCCDM)){
									//iter++;
									int k=dd;
									if(system.get(j).pairs.get(k).idHD==""){
										system.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
									}
									if(system.get(j).pairs.get(k).idHIP=="" ){
										system.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
									}
									if(system.get(j).pairs.get(k).idADS==""){
										system.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
									}
									if(system.get(j).pairs.get(k).idDM==""){
										system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
									}
									system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
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
			for(int i=0;i<f;i++){
				h=system.size();
				layer1:{ for(int j=0;j<h;j++){
					try{
						if(listINT4.get(i).idWDS!= null && listINT4.get(i).idWDS!="" && (listINT4.get(i).idWDS).equals(system.get(j).idWDS.substring(0, 10))){
							for(int k=0;k<system.get(j).pairs.size();k++){
								if(system.get(j).pairs.get(k).observer.equals(listINT4.get(i).dd)){
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
										break layer1;
									}else if(system.get(j).pairs.get(k).idHD!=null && listINT4.get(i).name3.contains(system.get(j).pairs.get(k).idHD) && system.get(j).pairs.get(k).idHD!="" || system.get(j).pairs.get(k).idDM!=null && listINT4.get(i).name3.contains(system.get(j).pairs.get(k).idDM) && system.get(j).pairs.get(k).idDM!=""){
										system.get(j).pairs.get(k).modyfyer[6]='i';
										break layer1;
									}else if(system.get(j).pairs.get(k).idHIP!=null && system.get(j).pairs.get(k).idHIP!="" && listINT4.get(i).name4.contains(system.get(j).pairs.get(k).idHIP) || system.get(j).pairs.get(k).observer!=null && system.get(j).pairs.get(k).observer!="" && listINT4.get(i).name2.contains(system.get(j).pairs.get(k).observer)){//discoverer 2!!!
										system.get(j).pairs.get(k).modyfyer[6]='i';
										break layer1;
									}
								}
							}
							//цикл прошел, а чуда не случилось, значит эту пару надо подмачить
							if(listINT4.get(i).dd.contains("Ab comp")){
								Pair e = new Pair();
								e.id="Aba-Abb";
								e.el1.name="Aba";
								e.el2.name="Abb";
								e.pairWDS="";
								e.observer=listINT4.get(i).dd;
								e.idHD=listINT4.get(i).idHD;
								e.idHIP=listINT4.get(i).idHIP;
								e.idADS=listINT4.get(i).idADS;
								e.idHyi=listINT4.get(i).idHyi;
								e.idDM=listINT4.get(i).idDM;
								e.idBayer=listINT4.get(i).idBayer;
								e.modyfyer[6]='i';
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
				if(xLog*1000<=i){
					System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
					timePrev=System.nanoTime();
					xLog=(int) (i/1000+1);	
				}
				//outer2.append("Sys"+listINT4.get(i).idWDS+(char)(10));
				//outer2.flush();
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	private static void interprCEV() {//"logCEV.txt"
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
										listCEV.get(i).Hyi.equals(system.get(j).pairs.get(k).idBayer)){//здесь должно быть условие для пары;
									content=true;
									system.get(j).pairs.get(k).modyfyer[7]='e';
									System.out.println("asd");
									break;
								}
							}
							if(!content){
								Pair e = new Pair();
								e.id="Aa-Ab";
								e.el1.name="Aa";
								e.el2.name="Ab";
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
					System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
					timePrev=System.nanoTime();
					xLog=(int) (i/1000+1);	
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	private static void interprGCVS() {//"log7.txt"
		int xLog=0;
		long timePrev=System.nanoTime();
		try{
			String fileName="log7.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
			int f=listGCVS.size();
			int h;
			for(int i=0;i<f;i++){
				boolean exists = false;
				h=system.size();
				layer1: for(int j=0;j<h;j++){
					for(int k=0;k<system.get(j).pairs.size();k++){
						try{
							if(listGCVS.get(i).ADS!="" && system.get(j).pairs.get(k).idADS.equals(listGCVS.get(i).ADS) ||
							listGCVS.get(i).Bayer!="" && system.get(j).pairs.get(k).idBayer.equals(listGCVS.get(i).Bayer) ||	
							listGCVS.get(i).HD!="" && system.get(j).pairs.get(k).idHD.equals(listGCVS.get(i).HD)  ||
							listGCVS.get(i).HIP!="" && system.get(j).pairs.get(k).idHIP.equals(listGCVS.get(i).HIP) ||
							listGCVS.get(i).DM!="" && system.get(j).pairs.get(k).idDM.equals(listGCVS.get(i).DM)){
								system.get(j).pairs.get(k).idGCVS=listGCVS.get(i).GCVSid;
								//System.out.println(system.get(j).pairs.get(k).idGCVS);
								system.get(j).pairs.get(k).modyfyer[9]='G';
								if(system.get(j).pairs.get(k).idADS==""){
									system.get(j).pairs.get(k).idADS=listGCVS.get(i).ADS;
								}
								if(system.get(j).pairs.get(k).idHIP==""){
									system.get(j).pairs.get(k).idHIP=listGCVS.get(i).HIP;
								}
								if(system.get(j).pairs.get(k).idHD==""){
									system.get(j).pairs.get(k).idHD=listGCVS.get(i).HD;
								}
								if(system.get(j).pairs.get(k).idDM==""){
									system.get(j).pairs.get(k).idDM=listGCVS.get(i).DM;
								}
								if(system.get(j).pairs.get(k).idBayer==""){
									system.get(j).pairs.get(k).idBayer=listGCVS.get(i).Bayer;
								}	
								exists = true;
								break layer1;
							}
						}catch(Exception e){
							outer2.append(e.getMessage()+(char)10);
							outer2.flush();
						}
					}
					if(xLog*1000<=i){
						System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
						timePrev=System.nanoTime();
						xLog=(int) (i/1000+1);	
					}
				}
				if(!exists){
					outer2.append(listGCVS.get(i).HIP+listGCVS.get(i).Bayer+listGCVS.get(i).DM+(char)10);
					outer2.flush();	
				}
			}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
	private static void interprSB9() {//"log8.txt"
		int xLog=0;
		long timePrev=System.nanoTime();
		try{
			String fileName="log8.txt";
			Writer outer2 = new FileWriter(new File("C:/WDSparser/"+fileName));
			int f=listSB9.size();
			int h;
			for(int i=0;i<f;i++){
				boolean exists = false;
				h=system.size();
				layer1: for(int j=0;j<h;j++){
					for(int k=0;k<system.get(j).pairs.size();k++){
						try{
							if(listSB9.get(i).ADS!="" && system.get(j).pairs.get(k).idADS.equals(listSB9.get(i).ADS) ||
							listSB9.get(i).Bayer!="" && system.get(j).pairs.get(k).idBayer.equals(listSB9.get(i).Bayer) ||	
							listSB9.get(i).HD!="" && system.get(j).pairs.get(k).idHD.equals(listSB9.get(i).HD)  ||
							listSB9.get(i).HIP!="" && system.get(j).pairs.get(k).idHIP.equals(listSB9.get(i).HIP) ||
							listSB9.get(i).DM!="" && system.get(j).pairs.get(k).idDM.equals(listSB9.get(i).DM)){
								system.get(j).pairs.get(k).idSB9=listSB9.get(i).SB9;
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
									system.get(j).pairs.get(k).idBayer=listSB9.get(i).Bayer;
								}	
								exists = true;
								break layer1;
							}
						}catch(Exception e){
							outer2.append(e.getMessage()+(char)10);
							outer2.flush();
						}
					}
					if(xLog*1000<=i){
						System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
						timePrev=System.nanoTime();
						xLog=(int) (i/1000+1);	
					}
				}
				if(!exists){
					outer2.append(listSB9.get(i).HIP+listSB9.get(i).Bayer+listSB9.get(i).DM+(char)10);
					outer2.flush();	
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
	public static void write(){
		try{
			String fileName="DATA_RELEASE.txt";
			Writer outer = new FileWriter(new File("C:/WDSparser/"+fileName));
			for(int k=0;k<system.size();k++){
				boolean ss=false;
				for(int j=0;j<system.get(k).pairs.size();j++,ss=true){
					int nn=0;
					if(ss){
						nn=1;
					}
					for(int stage=nn;stage<4;stage++){
						String x ="";
						x=x+('J');
						if(!system.get(k).data.contains("   ")){
							x=x+(system.get(k).data);
						}else{
							if(system.get(k).CCDMid!=""){
								try{
									String data=system.get(k).CCDMid.substring(0, 5)+"0.00"+system.get(k).CCDMid.substring(5, 10)+"00.0";
									system.get(k).fake=true;
									x=x+(data);
								}catch(Exception r){
								}
							}else{
								if(system.get(k).TDSCid!=""){
									try{
										String data=system.get(k).TDSCid.substring(0, 5)+"0.00"+system.get(k).TDSCid.substring(5, 10)+"00.0";
										system.get(k).fake=true;
										x=x+(data);
									}catch(Exception r){
									}
								}
							}
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
								x=x+"f";
							}else{
								x=x+" ";
							}
							x=x+(("          "));
							break;
						case 1:
							n=(":p"+system.get(k).pairs.get(j).id);
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								x=x+"f";
							}else{
								x=x+" ";
							}
							/*if(system.get(k).pairs.get(j).modyfyer.contains("O")){
								System.out.println("Exception x:"+system.get(k).identifyer+" "+system.get(k).pairs.get(j).id);
							}*/
							//system.get(k).pairs.get(j).modyfyer=clearify(system.get(k).pairs.get(j).modyfyer);
							for(int hk=0;hk<10;hk++){
								if(system.get(k).pairs.get(j).modyfyer[hk]!=0){
									x=x+system.get(k).pairs.get(j).modyfyer[hk];
								}else{
									x=x+' ';
								}
							}
							break;
						case 2:
							n=(":c"+system.get(k).pairs.get(j).el1.name);
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								x=x+"f";
							}else{
								x=x+" ";
							}
							x=x+(("          "));
							break;
						case 3:
							n=":c"+system.get(k).pairs.get(j).el2.name;
							while(n.length()<10){
								n=n+" ";
							}
							x=x+n;
							if(system.get(k).fake){
								x=x+"f";
							}else{
								x=x+" ";
							}
							x=x+(("          "));
							break;
						}
						if(system.get(k).identifyer!=""){
							x=x+((""+system.get(k).identifyer));
						}else{
							x=x+"        ";
						}
						if(system.get(k).pairs.get(j).observer!=null){
							int counter = system.get(k).pairs.get(j).observer.length();
							while(counter<8){
								x=x+" ";
								counter++;
							}
							x=x+((" "+system.get(k).pairs.get(j).observer));
						}else{
							x=x+"        ";
						}
						
						///
						//
						///
						//
						//
						//
						if(stage==1){
							while(x.length()<62){
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
								x=x+"        |";
							}
							
							if(system.get(k).pairs.get(j).idHIP!=""){
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
								int counter = system.get(k).pairs.get(j).idHD.length();
								while(counter<6){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idHD+"|";
							}else{
								x=x+"      |";
							}
							
							if(system.get(k).pairs.get(j).idGCVS!=""){
								int counter =system.get(k).pairs.get(j).idGCVS.length(); 
								while(counter<5){
									x=x+" ";
									counter++;
								}
								x=x+system.get(k).pairs.get(j).idGCVS+"|";
							}else{
								x=x+"     |";
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
								if(system.get(k).pairs.get(j).out==false){
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
		String fileName="dataBuffer.txt";
		File dataFile = new File("C:/WDSparser/"+fileName);	
		FileReader in = new FileReader(dataFile);
		char c;
		String s="";
		for(int i=0;i<dataFile.length();i++){
			c = (char) in.read();
			s+=c;
			if(c==10 || c==13){
				s=s.substring(0, s.length()-1);
				data.add(s);
				System.out.println(s);
				s="";
			}
		}
		for(int i=0;i<data.size();i++){
			for(int j=i-500;j<i;j++){
				try{
					if(data.get(i).substring(0,28).equals(data.get(j).substring(0,28))){
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
		return false;
	}
	public static void outputCCDM(){
		int zz=listCCDM.size();
		for(int i=0;i<zz;i++){
			//if(listCCDM.get(i).astrometric){
			if(true){
				System.out.print(listCCDM.get(i).ADS);
				for(int z=0;z<10-listCCDM.get(i).ADS.length();z++){
					System.out.print(" ");
				}
				System.out.print(listCCDM.get(i).astrometric);
				System.out.print(listCCDM.get(i).DM);
				for(int z=0;z<10-listCCDM.get(i).DM.length();z++){
					System.out.print(" ");
				}
				System.out.print(listCCDM.get(i).HD);
				for(int z=0;z<10-listCCDM.get(i).HD.length();z++){
					System.out.print(" ");
				}
				System.out.print(listCCDM.get(i).HIP);
				for(int z=0;z<10-listCCDM.get(i).HIP.length();z++){
					System.out.print(" ");
				}
				System.out.print(listCCDM.get(i).idsID);
				for(int z=0;z<10-listCCDM.get(i).idsID.length();z++){
					System.out.print(" ");
				}
				System.out.print(listCCDM.get(i).wdsID);
				for(int z=0;z<10-listCCDM.get(i).wdsID.length();z++){
					System.out.print(" ");
				}
				System.out.println(listCCDM.get(i).nameOfObserver);
			}
		}
	}
	public static void outputTDSC(){
		int zz=listTDSC.size();
		for(int i=0;i<zz;i++){
			if(true){
				System.out.print(listTDSC.get(i).CCDMidPAIR);
				for(int z=0;z<10-listTDSC.get(i).CCDMidPAIR.length();z++){
					System.out.print(" ");
				}
				System.out.print(listTDSC.get(i).pairTDSC);
				for(int z=0;z<3-listTDSC.get(i).pairTDSC.length();z++){
					System.out.print(" ");
				}
				System.out.print(listTDSC.get(i).HD);
				for(int z=0;z<10-listTDSC.get(i).HD.length();z++){
					System.out.print(" ");
				}
				System.out.print(listTDSC.get(i).HIPid);
				for(int z=0;z<10-listTDSC.get(i).HIPid.length();z++){
					System.out.print(" ");
				}
				System.out.print(listTDSC.get(i).TDSCid);
				for(int z=0;z<10-listTDSC.get(i).TDSCid.length();z++){
					System.out.print(" ");
				}
				System.out.print(listTDSC.get(i).WDSid);
				for(int z=0;z<10-listTDSC.get(i).WDSid.length();z++){
					System.out.print(" ");
				}
				System.out.println();
			}
		}
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
		ArrayList<StrStr> str = new ArrayList<StrStr>(); 
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
						str.add(new StrStr(star.parameter[0],star.parameter[2]));
						//System.out.println("asd"+star.parameter[0]+star.parameter[2]);
						sss = new StringBuffer();
					}
				}
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
		  } catch (Exception e) {
		  		e.printStackTrace();
		}
		int x =str.size();
		for(int i=0;i<x;i++){
			for(int j=i;j<x;j++){
				if(str.get(i).a!="" && str.get(i).a.equals(str.get(j).a)){
					if(!str.get(i).b.equals(str.get(j).b) && str.get(j).b!="" && str.get(i).b!=""){
						System.out.print("DIFF_CCDM WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						System.out.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
				if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).b)){
					if(!str.get(i).a.equals(str.get(j).a) && str.get(j).a!="" && str.get(i).a!=""){
						System.out.print("DIFF_WDS  WDS1 "+str.get(i).a+" CCDM1 "+str.get(i).b+"      ");
						System.out.println("WDS2 "+str.get(j).a+" CCDM2 "+str.get(j).b);
					}
				}
			}
		}
	}
	public static void parseWCT3(){//"log2.txt"
		ArrayList<StrStr> str = new ArrayList<StrStr>(); 
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
						str.add(new StrStr(star.parameter[0],star.parameter[2]));
						//System.out.println("asd"+star.parameter[0]+star.parameter[2]);
						sss = new StringBuffer();
					}
				}
				System.out.println("Файлик успешно прочитан!"+dataFile.length());
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
					System.out.println(str.get(i).a);
				}
			}
		}
		System.out.println("half");
		for(int i=0;i<x;i++){
			for(int j=0;j<x;j++){
				level1: if(str.get(i).b!="" && str.get(i).b.equals(str.get(j).a) && i!=j){
					for(int k=0;k<x;k++){
						if(str.get(i).b.equals(str.get(k).b) && str.get(k).b.equals(str.get(k).a)){
							break level1;
						}
					}
					System.out.println(str.get(i).a);
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
				System.out.println(i+"/"+gg+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
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
					if(listCCDM.get(i).wdsID!=null && system.get(j).idWDS!=null && system.get(j).idWDS.length()>10 && (listCCDM.get(i).wdsID).equals(system.get(j).idWDS.substring(0, 10)) || 
						system.get(j).pairs.get(dd).idCCDM!=null && listCCDM.get(i).ccdmID.equals(system.get(j).pairs.get(dd).idCCDM) ||
						system.get(j).CCDMid!=null && listCCDM.get(i).ccdmID.equals(system.get(j).CCDMid)){
						systemExistence=true;
						boolean existence=false;
						iter++;
						int number = 0;
						Pair ee = new Pair();
						ee.id=listCCDM.get(i).pairCCDM;
						ee.pairCCDM=listCCDM.get(i).pairCCDM;
						ee.observer=listCCDM.get(i).nameOfObserver;
						ee.clean();
						ee.dispWithoutRepeatCheckerInWriter(j);
						for(int k=0;k<system.get(j).pairs.size();k++){
						//	System.out.println("case"+j+ee.pairCCDM+"_"+system.get(j).pairs.get(k).pairCCDM);//general output
							if(system.get(j).pairs.get(k).pairCCDM.equals(ee.pairCCDM)){
								existence=true;
								number=i;
								if(system.get(j).pairs.get(k).idHD==""){
									system.get(j).pairs.get(k).idHD=listCCDM.get(i).HD;
								}else{
									if(system.get(j).pairs.get(k).idHD!=listCCDM.get(i).HD && !listCCDM.get(i).HD.contains(system.get(j).pairs.get(k).idHD) && !system.get(j).pairs.get(k).idHD.contains(listCCDM.get(i).HD)){
										outer2.write("CompareError HD:"+system.get(j).pairs.get(k).idHD+"_"+listCCDM.get(i).HD+(char)(10));
									}
								}
								if(system.get(j).pairs.get(k).idHIP=="" ){
									system.get(j).pairs.get(k).idHIP=listCCDM.get(i).HIP;
								}else{									
									if(system.get(j).pairs.get(k).idHIP!=listCCDM.get(i).HIP  && !listCCDM.get(i).HIP.contains(system.get(j).pairs.get(k).idHIP) && !system.get(j).pairs.get(k).idHIP.contains(listCCDM.get(i).HIP)){
										try{
											if(Integer.parseInt(system.get(j).pairs.get(k).idHIP)-Integer.parseInt(listCCDM.get(i).HIP)<2){
												outer2.write("CompareWarning HIP:"+system.get(j).pairs.get(k).idHIP+"_"+listCCDM.get(i).HIP+(char)(10));
											}else{
												outer2.write("CompareError HIP:"+system.get(j).pairs.get(k).idHIP+"_"+listCCDM.get(i).HIP+(char)(10));
											}
										}catch(NumberFormatException e){
											outer2.write("CompareWarning HIP:"+system.get(j).pairs.get(k).idHIP+"_"+listCCDM.get(i).HIP+(char)(10));
										}
									}
								}
								if(system.get(j).pairs.get(k).idADS==""){
									system.get(j).pairs.get(k).idADS=listCCDM.get(i).ADS;
								}else{
									if(system.get(j).pairs.get(k).idADS!=listCCDM.get(i).ADS && !listCCDM.get(i).ADS.contains(system.get(j).pairs.get(k).idADS) && !system.get(j).pairs.get(k).idADS.contains(listCCDM.get(i).ADS)){
										outer2.write("CompareError ADS:"+system.get(j).pairs.get(k).idADS+"_"+listCCDM.get(i).ADS+(char)(10));
									}
								}
								if(system.get(j).pairs.get(k).idDM==""){
									system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
								}else{
									if(listCCDM.get(i).DM==""){
										
									}else if(system.get(j).pairs.get(k).idDM!=listCCDM.get(i).DM.substring(0,listCCDM.get(i).DM.length()-2) && !listCCDM.get(i).DM.contains(system.get(j).pairs.get(k).idDM) && !system.get(j).pairs.get(k).idDM.contains(listCCDM.get(i).DM)){
										outer2.write("CompareWarning DM:"+system.get(j).pairs.get(k).idDM+"_"+listCCDM.get(i).DM+(char)(10));
										if(system.get(j).pairs.get(k).idDM!=listCCDM.get(i).DM){
											system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
										}
									}
								}
								system.get(j).pairs.get(k).idDM=listCCDM.get(i).DM;
								if(listCCDM.get(i).astrometric){
									system.get(j).pairs.get(k).modyfyer[4]='a';
									//System.out.println("ASTROMETRIC!");
								}else{
									system.get(j).pairs.get(k).modyfyer[4]='v';
								}
								break upper;
							}
						}
						if(!existence){
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
							if(listCCDM.get(i).astrometric){
								outer2.write("Close pair: ccdmID:"+listCCDM.get(i).ccdmID+" pair:"+listCCDM.get(i).pairCCDM+" "+(char)10);
								outer2.flush();
								eea.id="Aa-Ab";
								eea.el1.name="Aa";
								eea.el2.name="Ab";
								eea.modyfyer[4]='a';
								System.out.println("astrometric!");
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
				if(listCCDM.get(i).astrometric){
					e.modyfyer[4]='a';	
					System.out.println("ASTROMeTRIC");
					outer2.write("ErrorSysEx1Astrmtr: "+s.identifyer+""+(char)10);
				}else{
					//e.modyfyer="v";
					outer2.write("ErrorSysEx3: "+s.identifyer+""+(char)10);
				}
				s.pairs.add(e);
				outer2.flush();
			}
			if(xLog*1000<=i){
				System.out.println(i+"/"+f+ "interpreted "+ (System.nanoTime()-timePrev)/1000000+"ms");
				timePrev=System.nanoTime();
				xLog=(int) (i/1000+1);	
			}
		}
		}catch(Exception g){
			g.printStackTrace();
		}
	}
}