package ILBprocessing.beans;

import lib.tools.StatisticsCollector;

import java.util.ArrayList;

public class NodeINT4 {
	public String idWDS;
	public String data;
	public String dd;
	public String dd1;
	public boolean orbit;
	public String name1="";///ADS or HR or DM or other
	public String name2="";///Discoverer's, Bayer, Flamsteed, GCVS, GJ, other
	public String name3="";///HD or DM
	public String name4="";///Hip
	public String idDM="";
	public String idADS="";
	public String idBayer="";
	public String idFlamsteed ="";
	public String idHIP="";
	public String idHD="";
	public boolean fakePair=true;
	public String modifyer="";
	public double theta=0;//-1
	public double rho=0;//-1
	public NodeINT4(ArrayList<String> s){
		fakePair=true;
		try{
			modifyer="i";
			data=s.get(0).substring(0, 18);
			dd=" "+s.get(0).substring(46, 53);
			if(dd.contains(".") || dd.contains("-") || dd.contains("+")){
				dd="";
			}
			while(data.charAt(0)==' '){
				data=data.substring(1, data.length());
			}
			if(data.charAt(0)=='.'){
				//coordinatesFromWDSasString=wdsSystemID.substring(0, 5)+"0.00"+wdsSystemID.substring(5, 10)+"00.0";
				//coordinatesNotFoundInWDS=true;
			}
			if(s.size()>0){
				parseWDS(s.get(0));
			}
			for(int i=1;i<s.size();i++){
				if(s.get(i).charAt(28)!='<'){
					fakePair=false;
				}
				if(s.get(i).length()>80 && s.get(i).charAt(31)!=' '){
					getRHO(s.get(i));
					break;
				}
			}
			//System.doNotShowBcsResolved.println(fakePair);
		}catch(Exception e){
			//e.printStackTrace();
		}
	}
	public void getRHO(String s){
		try{
			if(rho==0){
				rho=Double.parseDouble(s.substring(30,39))*Math.PI/60/10800;
			}
		}catch(Exception e){
			//e.printStackTrace();
			rho=0;
		}
		try{
			if(theta==0){
				theta=Double.parseDouble(s.substring(14,20));
			}
		}catch(Exception e){
			//e.printStackTrace();
			theta=0;
		}
	};
	public void parseWDS(String s){
		try{
			//System.doNotShowBcsResolved.println(name1+" "+name2+" "+name3+" "+name4);
			name1=s.substring(20,32);
			if(name1.startsWith("BD") || name1.startsWith("CD") || name1.startsWith("CP")){
				idDM=name1;//clearify(name1.substring(2,name1.length()));
			} else if(name1.startsWith("ADS")){
				idADS=clearify(name1);
			}
			dd1=s.substring(46,53);
			if((dd1.contains("+") || dd1.contains("-"))){
				dd1="";
			}
			//System.doNotShowBcsResolved.println(dd);
			name2=clearify(s.substring(41,71));
			if(name2.length()>10){
				name2="";
			}
			if(StatisticsCollector.likeFlamsteed(name2)){
				idFlamsteed =clearify(name2);
				if(idFlamsteed.length()>9){
					idFlamsteed ="";
				}
			}
			if(StatisticsCollector.likeBayer(name2)){
				idBayer=clearify(name2);
				if(idBayer.length()>9){
					idBayer="";
				}
			}
			if(StatisticsCollector.likeFlamsteed(dd1)){
				dd1="";
			}
			if(StatisticsCollector.likeBayer(dd1)){
				dd1="";
			}
			name3=s.substring(72,84);
			if(name3.contains("HD")){
				name3=clearify(s.substring(72,84));
				idHD=clearify(name3.substring(3,name3.length()));
				try{
					if(
						idHD.charAt(idHD.length()-2)!='0' &&
						idHD.charAt(idHD.length()-2)!='1' &&
						idHD.charAt(idHD.length()-2)!='2' &&
						idHD.charAt(idHD.length()-2)!='3' &&
						idHD.charAt(idHD.length()-2)!='4' &&
						idHD.charAt(idHD.length()-2)!='5' &&
						idHD.charAt(idHD.length()-2)!='6' &&
						idHD.charAt(idHD.length()-2)!='7' &&
						idHD.charAt(idHD.length()-2)!='8' &&
						idHD.charAt(idHD.length()-2)!='9'){
						idHD=idHD.substring(0,idHD.length()-2);
					}			
				}catch(Exception e){
				}	
			}else{
				if(name3.contains("DM")){
					name3=clearify(s.substring(72,84));
					idDM=name3;
				}
			}
			if(s.substring(85,88).equals("HIP") && s.charAt(88)==' '){
				name4=clearify(s.substring(89,95));
				idHIP=name4;
				try{
					if(
						idHIP.charAt(idHIP.length()-2)!='0' &&
						idHIP.charAt(idHIP.length()-2)!='1' &&
						idHIP.charAt(idHIP.length()-2)!='2' &&
						idHIP.charAt(idHIP.length()-2)!='3' &&
						idHIP.charAt(idHIP.length()-2)!='4' &&
						idHIP.charAt(idHIP.length()-2)!='5' &&
						idHIP.charAt(idHIP.length()-2)!='6' &&
						idHIP.charAt(idHIP.length()-2)!='7' &&
						idHIP.charAt(idHIP.length()-2)!='8' &&
						idHIP.charAt(idHIP.length()-2)!='9'){
						idHIP=idHIP.substring(0,idHIP.length()-2);
					}			
				}catch(Exception e){
				}
			}
			try{
				if(dd1.charAt(0)==' '){
					dd1="";
				}else{
				}
			}catch(Exception r){}
			idWDS=s.substring(104,114);
		}catch(Exception e){
		}
		try{
			if(s.charAt(117)=='o'){
				modifyer+="o";
			}
		}catch(Exception e){
			
		}
		
		//System.doNotShowBcsResolved.println(idWDS);
	}

		public String clearify(String a){
		for(int i=0;i<a.length();i++){
			if(a.charAt(0)==' ' || a.charAt(0)=='$' || a.charAt(0)=='\\'){
				a=a.substring(1,a.length());
				i--;
			}else{
				break;
			}
		}
		if(a.startsWith("ADS")){
			a=a.substring(3,a.length());
		}
		if(a.startsWith("HIP")){
			a=a.substring(3,a.length());
		}
		for(int i=1;i<a.length()-1;i++){
			if(a.charAt(i)=='$' || a.charAt(i)=='\\'){
				a=a.substring(0,i)+a.substring(i+1,a.length());
				i--;
			}
		}
		for(int i=0;i<a.length();i++){
			if(a.charAt(a.length()-1)==' ' || a.charAt(a.length()-1)=='$' || a.charAt(a.length()-1)=='\\'){
				a=a.substring(0,a.length()-1);
				i--;
			}else{
				break;
			}
		}
		if(a.charAt(0)=='.'){
			a="";
		}
		int counter=-1;
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)=='$'){
				if(counter==-1){
					counter=i;
				}else{
					a=a.substring(0,counter)+a.substring(counter+2,counter+5)+a.substring(i,a.length());
					break;
				}
			}
		}
		return a;
	}
	
}
