package lib.model;

import ILBprocessing.*;

import java.util.ArrayList;

public class Pair {
	public double rho=0;
	public double theta=0;

	public Component el1;
	public Component el2;

	public String pairIdILB="";
	public String pairWDS="";
	public String pairCCDM="";
	public String pairTDSC="";

	public String observer="";
	public String idHIP="";
	public String idHD="";

	public String idSB9="";
	public String idADS="";
	public String idFlamsteed ="";
	public String idCEV="";
	public String idBayer="";
	public String idDM="";	//+40 5210

	public char[] modifier;  // Vo

	public String idCCDM="";
	public String idTDSC="";
	public String surname="";
	public boolean out=false;
	public ArrayList<String> keys = new ArrayList<String>();

	public Pair(){
		el1=new Component();
		el1.doNotShowBcsResolved =false;
		el2=new Component();
		el2.doNotShowBcsResolved =false;
		modifier =new char[10];
		for(int i=0;i<10;i++){
			modifier[i]=0;
		}
	}
	//TODO: refactor this stuff
	public void nameComponentsByPairNameForWDS(){
		if(pairWDS.length()!=0){
			pairIdILB=pairWDS;
			int positionOfSpecialChar=0;
			boolean existSpecialChar=false;
			for(int i = 0; i< pairIdILB.length(); i++){
				if(pairIdILB.charAt(i)==',' || pairIdILB.charAt(i)=='-'){
					pairIdILB=pairIdILB.replaceFirst("\\,", "-");
					positionOfSpecialChar=i;
					existSpecialChar=true;
					break;
				}
			}
			//TODO: refactor this stuff
			//NEVER TOUCH THIS CODE! It's danger for life!!
			if(existSpecialChar){
				int n=numberExists(pairIdILB);
				//exist special symbol and we know where
				if(n!=0){
					if(positionOfSpecialChar>n){
						el1.nameInILB = pairIdILB.substring(0, n)+'a';
						el2.nameInILB = pairIdILB.substring(0, n)+'b';
						if(exception(el1.nameInILB)){
							el1.doNotShowBcsResolved =true;
						}
						if(exception(el2.nameInILB)){
							el2.doNotShowBcsResolved =true;
						}
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(el1.nameInILB.substring(0, el1.nameInILB.length()-1));
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(el2.nameInILB.substring(0, el2.nameInILB.length()-1));
						pairIdILB =el1.nameInILB +"-"+el2.nameInILB;
					}else{
						el1.nameInILB = pairIdILB.substring(0, positionOfSpecialChar);
						el2.nameInILB =""+ pairIdILB.charAt(0)+ pairIdILB.charAt(3);
						if(pairIdILB.charAt(4)=='1'){
							el2.nameInILB =el2.nameInILB +'a';
						}
						if(pairIdILB.charAt(4)=='2'){
							el2.nameInILB =el2.nameInILB +'b';
						}
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(el1.nameInILB.substring(0, el1.nameInILB.length()-1));
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(el2.nameInILB.substring(0, el2.nameInILB.length()-1));
					}
				}else{
					//special symbol not exist
					el1.nameInILB = pairIdILB.substring(0, positionOfSpecialChar);
					if(positionOfSpecialChar>1 && (pairIdILB.charAt(1)=='a' || pairIdILB.charAt(1)=='b')){//
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(pairIdILB.charAt(0)+"");
					}else if(positionOfSpecialChar>1){
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(pairIdILB.charAt(0)+ pairIdILB.charAt(1)+"");//
					}
					el2.nameInILB = pairIdILB.substring(positionOfSpecialChar+1, pairIdILB.length());
					if(pairIdILB.length()-positionOfSpecialChar>1){
						WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(pairIdILB.substring(positionOfSpecialChar+1, pairIdILB.length()-1));
					}
					try{
						if((el1.nameInILB.charAt(1)=='a' || el1.nameInILB.charAt(1)=='b')){
							WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(el1.nameInILB.substring(0, el1.nameInILB.length()-1));
						}
					}catch(Exception e){
					}
					WDSparser.sysList.get(WDSparser.sysList.size()-1).checkAndMarkComponentAsResolved(el2.nameInILB.substring(0, el2.nameInILB.length()-1));
					if(exception(el1.nameInILB)){
						el1.doNotShowBcsResolved =true;
					}
					if(exception(el2.nameInILB)){
						el2.doNotShowBcsResolved =true;
					}
				}
				pairIdILB =el1.nameInILB +"-"+el2.nameInILB;
			}else{
				el1.nameInILB =""+ pairIdILB.charAt(0);
				if(exception(el1.nameInILB)){
					el1.doNotShowBcsResolved =true;
				}
				el2.nameInILB =""+ pairIdILB.charAt(1);
				if(exception(el2.nameInILB)){
					el2.doNotShowBcsResolved =true;
				}
				pairIdILB = pairIdILB.charAt(0)+"-"+ pairIdILB.charAt(1);
			}
		}else{
			System.err.println("ERR12 FATAL Exception. Name of pair in WDS can't be empty");
		}
	}
	public int dispWithoutRepeatCheckerInWriter(int sysNumber){
		//TODO write here something (critical)
		return 0;
	}
	public int numberExists(String id){
		for(int i=0;i<id.length();i++){
			for(int n=48;n<57;n++){
				if(id.charAt(i)==n){
					return i;
				}
			}
		}
		return 0;
	}
	public boolean exception(String s){
		for(int i = 0; i< WDSparser.sysList.get(WDSparser.sysList.size()-1).resolvedComponents.size(); i++){
			if(s.equals(WDSparser.sysList.get(WDSparser.sysList.size()-1).resolvedComponents.get(i))){
				return true;
			}
		}
		return false;
	}
}
