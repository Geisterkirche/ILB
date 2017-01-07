package lib.tools;

import java.util.ArrayList;


public class GlobalPoolOfIdentifiers {
	public static ArrayList<String> HD = new ArrayList<String>();
	public static ArrayList<String> DM = new ArrayList<String>();
	public static ArrayList<String> HIP = new ArrayList<String>();
	public static ArrayList<String> ADS = new ArrayList<String>();
	public static ArrayList<String> BAYER = new ArrayList<String>();
	public static ArrayList<String> FLAMSTEED = new ArrayList<String>();

	public static boolean likeFlamsteed(String temp){
		temp=temp.toUpperCase();
		if(temp.contains("AND") ||
				temp.contains("GEM".toUpperCase()) ||
				temp.contains("UMA".toUpperCase()) ||
				temp.contains("CMA".toUpperCase()) ||
				temp.contains("LIB".toUpperCase()) ||
				temp.contains("AQR".toUpperCase()) ||
				temp.contains("AUR".toUpperCase()) ||
				temp.contains("LUP".toUpperCase()) ||
				temp.contains("BOO".toUpperCase()) ||
				temp.contains("COM".toUpperCase()) ||
				temp.contains("CRV".toUpperCase()) ||
				temp.contains("HER".toUpperCase()) ||
				temp.contains("HYA".toUpperCase()) ||
				temp.contains("COL".toUpperCase()) ||
				temp.contains("CVN".toUpperCase()) ||
				temp.contains("VIR".toUpperCase()) ||
				temp.contains("DEL".toUpperCase()) ||
				temp.contains("DRA".toUpperCase()) ||
				temp.contains("MON".toUpperCase()) ||
				temp.contains("ARA".toUpperCase()) ||
				temp.contains("PIC".toUpperCase()) ||
				temp.contains("CAM".toUpperCase()) ||
				temp.contains("GRU".toUpperCase()) ||
				temp.contains("LEP".toUpperCase()) ||
				temp.contains("OPH".toUpperCase()) ||
				temp.contains("SER".toUpperCase()) ||
				temp.contains("DOR".toUpperCase()) ||
				temp.contains("IND".toUpperCase()) ||
				temp.contains("CAS".toUpperCase()) ||
				temp.contains("CAR".toUpperCase()) ||
				temp.contains("CET".toUpperCase()) ||
				temp.contains("CAP".toUpperCase()) ||
				temp.contains("PYX".toUpperCase()) ||
				temp.contains("PUP".toUpperCase()) ||
				temp.contains("CYG".toUpperCase()) ||
				temp.contains("LEC".toUpperCase()) ||
				temp.contains("VOL".toUpperCase()) ||
				temp.contains("LYR".toUpperCase()) ||
				temp.contains("VUL".toUpperCase()) ||
				temp.contains("UMI".toUpperCase()) ||
				temp.contains("EQU".toUpperCase()) ||
				temp.contains("LMI".toUpperCase()) ||
				temp.contains("CMI".toUpperCase()) ||
				temp.contains("MIC".toUpperCase()) ||
				temp.contains("MUS".toUpperCase()) ||
				temp.contains("ANT".toUpperCase()) ||
				temp.contains("NOR".toUpperCase()) ||
				temp.contains("ARI".toUpperCase()) ||
				temp.contains("OCT".toUpperCase()) ||
				temp.contains("AQL".toUpperCase()) ||
				temp.contains("ORI".toUpperCase()) ||
				temp.contains("PAV".toUpperCase()) ||
				temp.contains("VEL".toUpperCase()) ||
				temp.contains("PEG".toUpperCase()) ||
				temp.contains("PER".toUpperCase()) ||
				temp.contains("FOR".toUpperCase()) ||
				temp.contains("APS".toUpperCase()) ||
				temp.contains("CNC".toUpperCase()) ||
				temp.contains("CAE".toUpperCase()) ||
				temp.contains("PSC".toUpperCase()) ||
				temp.contains("LYN".toUpperCase()) ||
				temp.contains("CRB".toUpperCase()) ||
				temp.contains("SEX".toUpperCase()) ||
				temp.contains("RET".toUpperCase()) ||
				temp.contains("SCO".toUpperCase()) ||
				temp.contains("SCL".toUpperCase()) ||
				temp.contains("MEN".toUpperCase()) ||
				temp.contains("SGE".toUpperCase()) ||
				temp.contains("SGR".toUpperCase()) ||
				temp.contains("TEL".toUpperCase()) ||
				temp.contains("TAU".toUpperCase()) ||
				temp.contains("TRI".toUpperCase()) ||
				temp.contains("TUC".toUpperCase()) ||
				temp.contains("PHE".toUpperCase()) ||
				temp.contains("CHA".toUpperCase()) ||
				temp.contains("CEN".toUpperCase()) ||
				temp.contains("CEP".toUpperCase()) ||
				temp.contains("CIR".toUpperCase()) ||
				temp.contains("HOR".toUpperCase()) ||
				temp.contains("CRT".toUpperCase()) ||
				temp.contains("SCT".toUpperCase()) ||
				temp.contains("ERI".toUpperCase()) ||
				temp.contains("HYI".toUpperCase()) ||
				temp.contains("CRA".toUpperCase()) ||
				temp.contains("PSA".toUpperCase()) ||
				temp.contains("CRU".toUpperCase()) ||
				temp.contains("TRA".toUpperCase()) ||
				temp.contains("LAC".toUpperCase())){
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
		if((a.contains("+") || a.contains("-"))){
			return true;
		}
		return false;
	}

	public static String rebuildIdToUnifiedBase(String a){
		a=a.toUpperCase().replaceAll("    "," ").replaceAll("   "," ").replaceAll("  "," ").replaceAll("[^a-zA-Z0-9,+-]","");
		return a;
	}
	public static String rebuildIdForDM(String a){
		//remove all words after '+' or '-'. cut part with CP, CD etc
		a=rebuildIdToUnifiedBase(a);
		String[] s = a.split("\\+|\\-");
		if(a.contains("-")) {
			return "-"+s[s.length - 1];
		}else{
			return "+"+s[s.length - 1];
		}
	}
	@Deprecated
	public static  boolean containHD(String hd){
		if(HD.contains(hd)){
			return true;
		}
		return false;
	}
	@Deprecated
	public static  boolean validateHD(String hd){
		if(!containHD(hd)){
			HD.add(hd);
			return true;
		}else{
			//System.doNotShowBcsResolved.println("contain yet");
			return false;
		}
	}
	@Deprecated
	public static  boolean containDM(String hd){
		if(DM.contains(hd)){
			return true;
		}
		return false;
	}
	@Deprecated
	public static  boolean validateDM(String hd){
		if(!containDM(hd)){
			DM.add(hd);
			return true;
		}else{
			//System.doNotShowBcsResolved.println("contain yet");
			return false;
		}
	}
	@Deprecated
	public static  boolean containHIP(String hd){
		if(HIP.contains(hd)){
			return true;
		}
		return false;
	}
	@Deprecated
	public static boolean validateHIP(String hd){
		if(!containHIP(hd)){
			HIP.add(hd);
			return true;
		}else{
			//System.doNotShowBcsResolved.println("contain yet");
			return false;
		}
	}


	@Deprecated
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

	public static void printCount(){
		System.out.println("HIP size:"+HIP.size());
		System.out.println("HD size:"+HD.size());
		System.out.println("DM size:"+DM.size());
		System.out.println("ADS size:"+ADS.size());
		System.out.println("BAYER size:"+BAYER.size());
		System.out.println("FLAMSTEED size:"+FLAMSTEED.size());
	};
}
