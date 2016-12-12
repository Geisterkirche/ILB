package lib.model;

import java.util.ArrayList;

public class Component {
	public String nameInILB ="";
	public String nameCCDM="";
	public int coord1F;
	public int coord2F;
	public int coord2I;
	public int coord1I;
	public int coord_flag=0;
	public String newName="";
	public int hash;
	public boolean doNotShowBcsResolved;
	
	public String idHIP="";
	public String idHD="";
	public String idGCVS="";
	public String idSB9="";
	public String idADS="";

	public String idDM="";
	public ArrayList<String> keys = new ArrayList<String>();
	
	
	public Component(){
		doNotShowBcsResolved =false;
	}
	public long hash(){
		long hash =0;
		if(nameInILB.length()>1 && nameInILB.charAt(1)!='a' && nameInILB.charAt(1)!='b' && nameInILB.charAt(1)!='c' && nameInILB.charAt(1)!='d' && nameInILB.charAt(1)!='e' && nameInILB.charAt(1)!='f'){
			hash = -1000*(int)(nameInILB.charAt(0)-64)+(int)(nameInILB.charAt(1)-64);
		}else if(nameInILB.length()>1 && (nameInILB.charAt(1)=='a' || nameInILB.charAt(1)=='b' || nameInILB.charAt(1)=='c' || nameInILB.charAt(1)=='d' || nameInILB.charAt(1)=='e' || nameInILB.charAt(1)=='f')){
			for(int i = 0; i< nameInILB.length(); i++){
				hash +=(int)(nameInILB.charAt(i)-64)*1000^ nameInILB.length();
				hash*=1000;
			}
			return -hash;
		}else{
			hash = (int)(-nameInILB.charAt(0)+64);
		}
		return hash;
	}
	public static long hash(String name){
		long hash =0;
		if(name.length()>1 && name.charAt(1)!='a' && name.charAt(1)!='b' && name.charAt(1)!='c' && name.charAt(1)!='d' && name.charAt(1)!='e' && name.charAt(1)!='f'){
			hash = -1000*(int)(name.charAt(0)-64)+(int)(name.charAt(1)-64);
		}else if(name.length()>1 && (name.charAt(1)=='a' || name.charAt(1)=='b' || name.charAt(1)=='c' || name.charAt(1)=='d' || name.charAt(1)=='e' || name.charAt(1)=='f')){
			for(int i=0;i<name.length();i++){
				hash +=(int)(name.charAt(i)-64)*1000^name.length();
				hash*=1000;
			}
			return -hash;
		}else{
			hash = (long)(-name.charAt(0)+64);
		}
		return hash;
	}
}
