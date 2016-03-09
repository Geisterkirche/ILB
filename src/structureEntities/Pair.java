package structureEntities;

import structureEntities.Component;
import application.*;

public class Pair {
	public double rho=0;
	public double theta=0;
	public String id;
	public String pairWDS="";
	public String pairCCDM="";
	public String pairTDSC="";
	public boolean fakeName=false;
	public String observer;
	public Component el1;
	public Component el2;
	public String idHIP="";
	public String idHD="";
	//public String idGCVS="";
	public String idSB9="";
	public String idADS="";
	public String idHyi="";
	public String idCEV="";
	public String idBayer="";
	public String idDM="";	//+40 5210
							//+00 5079
	public char[] modyfyer;  // V
								// N
	public String idCCDM="";
	public String idTDSC="";
	public boolean out=false;
	public Pair(){
		el1=new Component();
		el1.out=false;
		el2=new Component();
		el2.out=false;
		modyfyer=new char[10];
		for(int i=0;i<10;i++){
			modyfyer[i]=0;
		}
	}
	public void clean(){
		boolean clean=true;
		while(clean){
			for(int i=0;i<id.length();i++){
				if(id.charAt(i)==' '){
					if(i!=id.length()-1){
						id=id.substring(0, i)+id.substring(i+1, id.length());
						break;
					}else{
						id=id.substring(0, i);
						break;
					}
				}
			}
			clean=false;
			for(int i=0;i<id.length();i++){
				if(id.charAt(i)==' '){
					clean=true;
					break;
				}
			}
		}
	}
	public void disp(){
		if(id.length()!=0){
			int position=0;
			boolean exist=false;
			for(int i=0;i<id.length();i++){
				if(id.charAt(i)==',' || id.charAt(i)=='-'){
					id.replaceFirst(",", "-");
					position=i;
					exist=true;
					break;
				}
			}
			if(exist){
				int n=numberExists(id);
				if(n!=0){
					if(position>n){
						el1.name=id.substring(0, n)+'a';
						el2.name=id.substring(0, n)+'b';
						if(exception(el1.name)){
							el1.out=true;
						}
						if(exception(el2.name)){
							el2.out=true;
						}
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(el1.name.substring(0, el1.name.length()-1));
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(el2.name.substring(0, el2.name.length()-1));
						id=el1.name+"-"+el2.name;
					}else{
						el1.name=id.substring(0, position);
						el2.name=""+id.charAt(0)+id.charAt(3);
						if(id.charAt(4)=='1'){
							el2.name=el2.name+'a';
						}
						if(id.charAt(4)=='2'){
							el2.name=el2.name+'b';
						}
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(el1.name.substring(0, el1.name.length()-1));
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(el2.name.substring(0, el2.name.length()-1));
					}
				}else{
					el1.name=id.substring(0, position);
					if(position>1 && (id.charAt(1)=='a' || id.charAt(1)=='b')){//
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(id.charAt(0)+"");
					}else if(position>1){
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(id.charAt(0)+id.charAt(1)+"");//
					}
					el2.name=id.substring(position+1, id.length());
					if(id.length()-position>1){
						WDSparser.system.get(WDSparser.system.size()-1).findCompOut(id.substring(position+1, id.length()-1));
					}
					try{
						if((el1.name.charAt(1)=='a' || el1.name.charAt(1)=='b')){
							WDSparser.system.get(WDSparser.system.size()-1).findCompOut(el1.name.substring(0, el1.name.length()-1));
						}
					}catch(Exception e){
					}
					WDSparser.system.get(WDSparser.system.size()-1).findCompOut(el2.name.substring(0, el2.name.length()-1));
					if(exception(el1.name)){
						el1.out=true;
					}
					if(exception(el2.name)){
						el2.out=true;
					}
				}
				id=el1.name+"-"+el2.name;
			}else{
				el1.name=""+id.charAt(0);
				if(exception(el1.name)){
					el1.out=true;
				}
				el2.name=""+id.charAt(1);
				if(exception(el2.name)){
					el2.out=true;
				}
				id=id.charAt(0)+"-"+id.charAt(1);
			}
		}else{
			el1.name="A";
			if(exception(el1.name)){
				el1.out=true;
			}
			el2.name="B";
			if(exception(el2.name)){
				el2.out=true;
			}
			id="A-B";
		}
	}
	public int dispWithoutRepeatCheckerInWriter(int sysNumber){
		int gg=0;
		if(sysNumber==-1){
			sysNumber= WDSparser.system.size()-1;
		}
		if(id.contains("-")){
			System.out.println("Error in id:"+id);
			System.out.println("Error in id:"+pairCCDM+"_"+pairWDS+"_"+pairTDSC);
			id="AB";
			gg=1;
		}
		if(id.length()==1){
			System.out.println("Error in id:"+id);
			id="AB";
			gg=2;
		}
		if(id.length()!=0){
			int position=0;
			boolean exist=false;
			for(int i=0;i<id.length();i++){
				if(id.charAt(i)==',' || id.charAt(i)=='-'){
					id.replaceFirst(",", "-");
					position=i;
					exist=true;
					break;
				}
			}
			if(exist){
				int n=numberExists(id);
				if(n!=0){
					if(position>n){
						el1.name=id.substring(0, n)+'a';
						el2.name=id.substring(0, n)+'b';
						if(exception(el1.name)){
							el1.out=true;
						}
						if(exception(el2.name)){
							el2.out=true;
						}
						WDSparser.system.get(sysNumber).findCompOut(el1.name.substring(0, el1.name.length()-1));
						WDSparser.system.get(sysNumber).findCompOut(el2.name.substring(0, el2.name.length()-1));
						id=el1.name+"-"+el2.name;
					}else{
						el1.name=id.substring(0, position);
						el2.name=""+id.charAt(0)+id.charAt(3);
						try{
						if(id.charAt(4)=='1'){
							el2.name=el2.name+'a';
						}
						if(id.charAt(4)=='2'){
							el2.name=el2.name+'b';
						}
						WDSparser.system.get(sysNumber).findCompOut(el1.name.substring(0, el1.name.length()-1));
						WDSparser.system.get(sysNumber).findCompOut(el2.name.substring(0, el2.name.length()-1));
						}catch(Exception r){
							r.printStackTrace();
							System.out.println(el2.name);
						}
					}
				}else{
					el1.name=id.substring(0, position);
					el2.name=id.substring(position+1, id.length());
					if(exception(el1.name)){
						el1.out=true;
					}
					if(exception(el2.name)){
						el2.out=true;
					}
					WDSparser.system.get(sysNumber).findCompOut(el1.name.substring(0, el1.name.length()-1));
					WDSparser.system.get(sysNumber).findCompOut(el2.name.substring(0, el2.name.length()-1));
				}
				id=el1.name+"-"+el2.name;
			}else{
				el1.name=""+id.charAt(0);
				el2.name=""+id.charAt(1);
				if(exception(el1.name)){
					el1.out=true;
				}
				el2.name=""+id.charAt(1);
				if(exception(el2.name)){
					el2.out=true;
				}
				id=id.charAt(0)+"-"+id.charAt(1);
			}
		}else{
			el1.name="A";
			if(exception(el1.name)){
				el1.out=true;
			}
			el2.name="B";
			if(exception(el2.name)){
				el2.out=true;
			}
			id="A-B";
		}
		return gg;
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
		for(int i = 0; i< WDSparser.system.get(WDSparser.system.size()-1).exceptions.size(); i++){
			if(s.equals(WDSparser.system.get(WDSparser.system.size()-1).exceptions.get(i))){
				return true;
			}
		}
		return false;
	}
}
