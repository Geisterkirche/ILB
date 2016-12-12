package lib.tools;

import java.util.ArrayList;


public class StatisticsCollector {
	public static ArrayList<String> HD = new ArrayList<String>();
	public static ArrayList<String> DM = new ArrayList<String>();
	public static ArrayList<String> HIP = new ArrayList<String>();

	public static boolean containsFlamsteed(String temp){
		temp=temp.toUpperCase();
		if(temp.contains("And") ||
				temp.contains("Gem".toUpperCase()) ||
				temp.contains("UMa".toUpperCase()) ||
				temp.contains("CMa".toUpperCase()) ||
				temp.contains("Lib".toUpperCase()) ||
				temp.contains("Aqr".toUpperCase()) ||
				temp.contains("Aur".toUpperCase()) ||
				temp.contains("Lup".toUpperCase()) ||
				temp.contains("Boo".toUpperCase()) ||
				temp.contains("Com".toUpperCase()) ||
				temp.contains("Crv".toUpperCase()) ||
				temp.contains("Her".toUpperCase()) ||
				temp.contains("Hya".toUpperCase()) ||
				temp.contains("Col".toUpperCase()) ||
				temp.contains("CVn".toUpperCase()) ||
				temp.contains("Vir".toUpperCase()) ||
				temp.contains("Del".toUpperCase()) ||
				temp.contains("Dra".toUpperCase()) ||
				temp.contains("Mon".toUpperCase()) ||
				temp.contains("Ara".toUpperCase()) ||
				temp.contains("Pic".toUpperCase()) ||
				temp.contains("Cam".toUpperCase()) ||
				temp.contains("Gru".toUpperCase()) ||
				temp.contains("Lep".toUpperCase()) ||
				temp.contains("Oph".toUpperCase()) ||
				temp.contains("Ser".toUpperCase()) ||
				temp.contains("Dor".toUpperCase()) ||
				temp.contains("Ind".toUpperCase()) ||
				temp.contains("Cas".toUpperCase()) ||
				temp.contains("Car".toUpperCase()) ||
				temp.contains("Cet".toUpperCase()) ||
				temp.contains("Cap".toUpperCase()) ||
				temp.contains("Pyx".toUpperCase()) ||
				temp.contains("Pup".toUpperCase()) ||
				temp.contains("Cyg".toUpperCase()) ||
				temp.contains("Leo".toUpperCase()) ||
				temp.contains("Vol".toUpperCase()) ||
				temp.contains("Lyr".toUpperCase()) ||
				temp.contains("Vul".toUpperCase()) ||
				temp.contains("UMi".toUpperCase()) ||
				temp.contains("Equ".toUpperCase()) ||
				temp.contains("Lmi".toUpperCase()) ||
				temp.contains("Cmi".toUpperCase()) ||
				temp.contains("Mic".toUpperCase()) ||
				temp.contains("Mus".toUpperCase()) ||
				temp.contains("Ant".toUpperCase()) ||
				temp.contains("Nor".toUpperCase()) ||
				temp.contains("Ari".toUpperCase()) ||
				temp.contains("Oct".toUpperCase()) ||
				temp.contains("Aql".toUpperCase()) ||
				temp.contains("Ori".toUpperCase()) ||
				temp.contains("Pav".toUpperCase()) ||
				temp.contains("Vel".toUpperCase()) ||
				temp.contains("Peg".toUpperCase()) ||
				temp.contains("Per".toUpperCase()) ||
				temp.contains("For".toUpperCase()) ||
				temp.contains("Aps".toUpperCase()) ||
				temp.contains("Cnc".toUpperCase()) ||
				temp.contains("Cae".toUpperCase()) ||
				temp.contains("Psc".toUpperCase()) ||
				temp.contains("Lyn".toUpperCase()) ||
				temp.contains("CrB".toUpperCase()) ||
				temp.contains("Sex".toUpperCase()) ||
				temp.contains("Ret".toUpperCase()) ||
				temp.contains("Sco".toUpperCase()) ||
				temp.contains("Scl".toUpperCase()) ||
				temp.contains("Men".toUpperCase()) ||
				temp.contains("Sge".toUpperCase()) ||
				temp.contains("Sgr".toUpperCase()) ||
				temp.contains("Tel".toUpperCase()) ||
				temp.contains("Tau".toUpperCase()) ||
				temp.contains("Tri".toUpperCase()) ||
				temp.contains("Tuc".toUpperCase()) ||
				temp.contains("Phe".toUpperCase()) ||
				temp.contains("Cha".toUpperCase()) ||
				temp.contains("Cen".toUpperCase()) ||
				temp.contains("Cep".toUpperCase()) ||
				temp.contains("Cir".toUpperCase()) ||
				temp.contains("Hor".toUpperCase()) ||
				temp.contains("Crt".toUpperCase()) ||
				temp.contains("Sct".toUpperCase()) ||
				temp.contains("Eri".toUpperCase()) ||
				temp.contains("Hyi".toUpperCase()) ||
				temp.contains("CrA".toUpperCase()) ||
				temp.contains("PsA".toUpperCase()) ||
				temp.contains("Cru".toUpperCase()) ||
				temp.contains("TrA".toUpperCase()) ||
				temp.contains("Lac".toUpperCase())){
			return true;
		}
		return false;
	}
	public static boolean likeBayer(String a){
		a=a.toLowerCase();
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
			if(rebuildIdToUnifiedBase(a).length()>9){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}

	public static boolean likeDM(String a){
		a=a.toLowerCase();
		if((a.contains("bd") || a.contains("cd") || a.contains("cp")) && (a.contains("+") || a.contains("-"))){
				return true;
		}
		return false;
	}

	public static String rebuildIdToUnifiedBase(String a){
		a=a.toUpperCase().replaceAll("  "," ").replaceAll("  "," ").replaceAll("  "," ").replaceAll("  "," ");
		String[] z = a.split(" ");
		StringBuffer s = new StringBuffer();
		for(int i=0;i<z.length;i++){
			for(int j=0;j<z[i].length();j++){
				if((""+z[i].charAt(j)).equals((""+z[i].charAt(j)).replaceAll("\\w|\\d|\\+|\\-",""))){
					s.append(z[i].charAt(j));
				}
			}
			s.append(" ");
		}
		String ss = s.toString().substring(0,s.length()-1);
		return ss;
	}


	public static  boolean containHD(String hd){
		if(HD.contains(hd)){
			return true;
		}
		return false;
	}
	public static  boolean validateHD(String hd){
		if(!containHD(hd)){
			HD.add(hd);
			return true;
		}else{
			//System.doNotShowBcsResolved.println("contain yet");
			return false;
		}
	}
	public static  boolean containDM(String hd){
		if(DM.contains(hd)){
			return true;
		}
		return false;
	}
	public static  boolean validateDM(String hd){
		if(!containDM(hd)){
			DM.add(hd);
			return true;
		}else{
			//System.doNotShowBcsResolved.println("contain yet");
			return false;
		}
	}
	public static  boolean containHIP(String hd){
		if(HIP.contains(hd)){
			return true;
		}
		return false;
	}
	public static boolean validateHIP(String hd){
		if(!containHIP(hd)){
			HIP.add(hd);
			return true;
		}else{
			//System.doNotShowBcsResolved.println("contain yet");
			return false;
		}
	}

	public static void print(){
		System.out.println("HIP size:"+HIP.size());
		System.out.println("HD size:"+HD.size());
		System.out.println("DM size:"+DM.size());
	};

	public static String cutDM(String dm) {
		dm=dm.toLowerCase();
		if(dm.contains("bd")){
			dm=dm.replaceAll("bd","");
		}
		if(dm.contains("cd")){
			dm=dm.replaceAll("cd","");
		}
		if(dm.contains("cp")){
			dm=dm.replaceAll("cp","");
		}
		dm=dm.replaceAll("  "," ");
		dm=dm.replaceAll("  "," ");
		dm=dm.replaceAll("  "," ");
		dm=dm.replaceAll("  "," ");
		dm=dm.replaceAll("  "," ");
		if(dm.charAt(dm.length()-1)==' '){
			dm.substring(0,dm.length()-1);
		}
		if(dm.charAt(0)==' '){
			dm.substring(1);
		}
		return dm;
	}
}
