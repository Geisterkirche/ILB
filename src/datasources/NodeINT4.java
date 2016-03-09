package datasources;

import java.util.ArrayList;

import javax.net.ssl.SSLContext;

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
	public String idHyi="";
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
				//data=identifyer.substring(0, 5)+"0.00"+identifyer.substring(5, 10)+"00.0";
				//fake=true;
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
			//System.out.println(fakePair);
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
			//System.out.println(name1+" "+name2+" "+name3+" "+name4);
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
			//System.out.println(dd);
			name2=clearify(s.substring(41,71));
			if(name2.length()>10){
				name2="";
			}
			if(likeBayer(name2)){
				idHyi=clearify(name2);
				if(idHyi.length()>9){
					idHyi="";
				}
			}
			if(likeHyi(name2)){
				idBayer=clearify(name2);
				if(idBayer.length()>9){
					idBayer="";
				}
			}
			if(likeBayer(dd1)){
				dd1="";
			}
			if(likeHyi(dd1)){
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
		
		//System.out.println(idWDS);
	}
	public boolean likeBayer(String a){
		if(		a.contains("alp") ||
				a.contains("bet") ||
				a.contains("gam") ||
				a.contains("del") ||
				a.contains("eps") ||
				a.contains("zet") ||
				a.contains("eta") ||
				a.contains("the") ||
				a.contains("iot") ||
				a.contains("kap") ||
				a.contains("lam") ||
				a.contains("mu") ||
				a.contains("nu") ||
				a.contains("xi") ||
				a.contains("omi") ||
				a.contains("pi") ||
				a.contains("rho") ||
				a.contains("sig") ||
				a.contains("tau") ||
				a.contains("ups") ||
				a.contains("phi") ||
				a.contains("chi") ||
				a.contains("psi") ||
				a.contains("ome")){
			if(a.length()>9){
				return false;
			}else{
				return true;
			}
			//pairWDS="AB";//������������ �������
		}
		return false;
	}
	public boolean likeHyi(String a){
		if(a.contains("And") ||
				a.contains("Gem") ||
				a.contains("UMa") ||
				a.contains("CMa") ||
				a.contains("Lib") ||
				a.contains("Aqr") ||
				a.contains("Aur") ||
				a.contains("Lup") ||
				a.contains("Boo") ||
				a.contains("Com") ||
				a.contains("Crv") ||
				a.contains("Her") ||
				a.contains("Hya") ||
				a.contains("Col") ||
				a.contains("CVn") ||
				a.contains("Vir") ||
				a.contains("Del") ||
				a.contains("Dra") ||
				a.contains("Mon") ||
				a.contains("Ara") ||
				a.contains("Pic") ||
				a.contains("Cam") ||
				a.contains("Gru") ||
				a.contains("Lep") ||
				a.contains("Oph") ||
				a.contains("Ser") ||
				a.contains("Dor") ||
				a.contains("Ind") ||
				a.contains("Cas") ||
				a.contains("Car") ||
				a.contains("Cet") ||
				a.contains("Cap") ||
				a.contains("Pyx") ||
				a.contains("Pup") ||
				a.contains("Cyg") ||
				a.contains("Leo") ||
				a.contains("Vol") ||
				a.contains("Lyr") ||
				a.contains("Vul") ||
				a.contains("UMi") ||
				a.contains("Equ") ||
				a.contains("Lmi") ||
				a.contains("Cmi") ||
				a.contains("Mic") ||
				a.contains("Mus") ||
				a.contains("Ant") ||
				a.contains("Nor") ||
				a.contains("Ari") ||
				a.contains("Oct") ||
				a.contains("Aql") ||
				a.contains("Ori") ||
				a.contains("Pav") ||
				a.contains("Vel") ||
				a.contains("Peg") ||
				a.contains("Per") ||
				a.contains("For") ||
				a.contains("Aps") ||
				a.contains("Cnc") ||
				a.contains("Cae") ||
				a.contains("Psc") ||
				a.contains("Lyn") ||
				a.contains("CrB") ||
				a.contains("Sex") ||
				a.contains("Ret") ||
				a.contains("Sco") ||
				a.contains("Scl") ||
				a.contains("Men") ||
				a.contains("Sge") ||
				a.contains("Sgr") ||
				a.contains("Tel") ||
				a.contains("Tau") ||
				a.contains("Tri") ||
				a.contains("Tuc") ||
				a.contains("Phe") ||
				a.contains("Cha") ||
				a.contains("Cen") ||
				a.contains("Cep") ||
				a.contains("Cir") ||
				a.contains("Hor") ||
				a.contains("Crt") ||
				a.contains("Sct") ||
				a.contains("Eri") ||
				a.contains("Hyi") ||
				a.contains("CrA") ||
				a.contains("PsA") ||
				a.contains("Cru") ||
				a.contains("TrA") ||
				a.contains("Lac")){
			if(a.length()>9){
				return false;
			}else{
				return true;
			}
		}
		return false;
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
