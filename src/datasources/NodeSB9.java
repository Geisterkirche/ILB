package datasources;

public class NodeSB9 {
	public String SB9;
	public String GCVSid="";
	public String DM="";
	public String idFlamsteed="";
	public String HD="";
	public String HIP="";
	public String ADS="";
	public String HR="";

	public String mainID;
	public String data; //  055957.08+530946.2
	public NodeSB9(String s, int index){
		//System.out.println(s);
		SB9=s.substring(0,index);
		//System.out.println("debug SB: "+ SB9);
		int i=index+1;
		String z="";
		while(s.charAt(i)!='|'){
			z=z+s.charAt(i);
			i++;
		}
		//System.out.println(z);
		if(z.contains("CP") || z.contains("CD") || z.contains("BD")){
			DM=s.substring(i+1,s.length());
		}else if(z.equals("HIP")){
			HIP=s.substring(i+1,s.length());
		}else if(z.equals("ADS")){
			ADS=s.substring(i+1,s.length());
		}else if(z.equals("HR")){
			HR=s.substring(i+1,s.length());
		}else if(z.equals("HD")){
			HD=s.substring(i+1,s.length());
		}else{
			String idWDS2= s.substring(i+1,s.length());
			if(idWDS2.contains("And") ||
					idWDS2.contains("Gem") ||
					idWDS2.contains("UMa") ||
					idWDS2.contains("CMa") ||
					idWDS2.contains("Lib") ||
					idWDS2.contains("Aqr") ||
					idWDS2.contains("Aur") ||
					idWDS2.contains("Lup") ||
					idWDS2.contains("Boo") ||
					idWDS2.contains("Com") ||
					idWDS2.contains("Crv") ||
					idWDS2.contains("Her") ||
					idWDS2.contains("Hya") ||
					idWDS2.contains("Col") ||
					idWDS2.contains("CVn") ||
					idWDS2.contains("Vir") ||
					idWDS2.contains("Del") ||
					idWDS2.contains("Dra") ||
					idWDS2.contains("Mon") ||
					idWDS2.contains("Ara") ||
					idWDS2.contains("Pic") ||
					idWDS2.contains("Cam") ||
					idWDS2.contains("Gru") ||
					idWDS2.contains("Lep") ||
					idWDS2.contains("Oph") ||
					idWDS2.contains("Ser") ||
					idWDS2.contains("Dor") ||
					idWDS2.contains("Ind") ||
					idWDS2.contains("Cas") ||
					idWDS2.contains("Car") ||
					idWDS2.contains("Cet") ||
					idWDS2.contains("Cap") ||
					idWDS2.contains("Pyx") ||
					idWDS2.contains("Pup") ||
					idWDS2.contains("Cyg") ||
					idWDS2.contains("Leo") ||
					idWDS2.contains("Vol") ||
					idWDS2.contains("Lyr") ||
					idWDS2.contains("Vul") ||
					idWDS2.contains("UMi") ||
					idWDS2.contains("Equ") ||
					idWDS2.contains("Lmi") ||
					idWDS2.contains("Cmi") ||
					idWDS2.contains("Mic") ||
					idWDS2.contains("Mus") ||
					idWDS2.contains("Ant") ||
					idWDS2.contains("Nor") ||
					idWDS2.contains("Ari") ||
					idWDS2.contains("Oct") ||
					idWDS2.contains("Aql") ||
					idWDS2.contains("Ori") ||
					idWDS2.contains("Pav") ||
					idWDS2.contains("Vel") ||
					idWDS2.contains("Peg") ||
					idWDS2.contains("Per") ||
					idWDS2.contains("For") ||
					idWDS2.contains("Aps") ||
					idWDS2.contains("Cnc") ||
					idWDS2.contains("Cae") ||
					idWDS2.contains("Psc") ||
					idWDS2.contains("Lyn") ||
					idWDS2.contains("CrB") ||
					idWDS2.contains("Sex") ||
					idWDS2.contains("Ret") ||
					idWDS2.contains("Sco") ||
					idWDS2.contains("Scl") ||
					idWDS2.contains("Men") ||
					idWDS2.contains("Sge") ||
					idWDS2.contains("Sgr") ||
					idWDS2.contains("Tel") ||
					idWDS2.contains("Tau") ||
					idWDS2.contains("Tri") ||
					idWDS2.contains("Tuc") ||
					idWDS2.contains("Phe") ||
					idWDS2.contains("Cha") ||
					idWDS2.contains("Cen") ||
					idWDS2.contains("Cep") ||
					idWDS2.contains("Cir") ||
					idWDS2.contains("Hor") ||
					idWDS2.contains("Crt") ||
					idWDS2.contains("Sct") ||
					idWDS2.contains("Eri") ||
					idWDS2.contains("Hyi") ||
					idWDS2.contains("CrA") ||
					idWDS2.contains("PsA") ||
					idWDS2.contains("Cru") ||
					idWDS2.contains("TrA") ||
					idWDS2.contains("Lac")){
				idFlamsteed=clearify(idWDS2);
			}
		}
	}
	public void addEff(String s, int index){
		int i=index+1;
		String z="";
		while(s.charAt(i)!='|'){
			z=z+s.charAt(i);
			i++;
		}
		if(z.contains("CP") || z.contains("CD") || z.contains("BD")){
			DM=s.substring(i+1,s.length());
		}else if(z.equals("HIP")){
			HIP=s.substring(i+1,s.length());
		}else if(z.equals("ADS")){
			ADS=s.substring(i+1,s.length());
		}else if(z.equals("HR")){
			HR=s.substring(i+1,s.length());
		}else if(z.equals("HD")){
			HD=s.substring(i+1,s.length());
		}else{
			String idWDS2= s.substring(i+1,s.length());
			if(idWDS2.contains("And") ||
					idWDS2.contains("Gem") ||
					idWDS2.contains("UMa") ||
					idWDS2.contains("CMa") ||
					idWDS2.contains("Lib") ||
					idWDS2.contains("Aqr") ||
					idWDS2.contains("Aur") ||
					idWDS2.contains("Lup") ||
					idWDS2.contains("Boo") ||
					idWDS2.contains("Com") ||
					idWDS2.contains("Crv") ||
					idWDS2.contains("Her") ||
					idWDS2.contains("Hya") ||
					idWDS2.contains("Col") ||
					idWDS2.contains("CVn") ||
					idWDS2.contains("Vir") ||
					idWDS2.contains("Del") ||
					idWDS2.contains("Dra") ||
					idWDS2.contains("Mon") ||
					idWDS2.contains("Ara") ||
					idWDS2.contains("Pic") ||
					idWDS2.contains("Cam") ||
					idWDS2.contains("Gru") ||
					idWDS2.contains("Lep") ||
					idWDS2.contains("Oph") ||
					idWDS2.contains("Ser") ||
					idWDS2.contains("Dor") ||
					idWDS2.contains("Ind") ||
					idWDS2.contains("Cas") ||
					idWDS2.contains("Car") ||
					idWDS2.contains("Cet") ||
					idWDS2.contains("Cap") ||
					idWDS2.contains("Pyx") ||
					idWDS2.contains("Pup") ||
					idWDS2.contains("Cyg") ||
					idWDS2.contains("Leo") ||
					idWDS2.contains("Vol") ||
					idWDS2.contains("Lyr") ||
					idWDS2.contains("Vul") ||
					idWDS2.contains("UMi") ||
					idWDS2.contains("Equ") ||
					idWDS2.contains("Lmi") ||
					idWDS2.contains("Cmi") ||
					idWDS2.contains("Mic") ||
					idWDS2.contains("Mus") ||
					idWDS2.contains("Ant") ||
					idWDS2.contains("Nor") ||
					idWDS2.contains("Ari") ||
					idWDS2.contains("Oct") ||
					idWDS2.contains("Aql") ||
					idWDS2.contains("Ori") ||
					idWDS2.contains("Pav") ||
					idWDS2.contains("Vel") ||
					idWDS2.contains("Peg") ||
					idWDS2.contains("Per") ||
					idWDS2.contains("For") ||
					idWDS2.contains("Aps") ||
					idWDS2.contains("Cnc") ||
					idWDS2.contains("Cae") ||
					idWDS2.contains("Psc") ||
					idWDS2.contains("Lyn") ||
					idWDS2.contains("CrB") ||
					idWDS2.contains("Sex") ||
					idWDS2.contains("Ret") ||
					idWDS2.contains("Sco") ||
					idWDS2.contains("Scl") ||
					idWDS2.contains("Men") ||
					idWDS2.contains("Sge") ||
					idWDS2.contains("Sgr") ||
					idWDS2.contains("Tel") ||
					idWDS2.contains("Tau") ||
					idWDS2.contains("Tri") ||
					idWDS2.contains("Tuc") ||
					idWDS2.contains("Phe") ||
					idWDS2.contains("Cha") ||
					idWDS2.contains("Cen") ||
					idWDS2.contains("Cep") ||
					idWDS2.contains("Cir") ||
					idWDS2.contains("Hor") ||
					idWDS2.contains("Crt") ||
					idWDS2.contains("Sct") ||
					idWDS2.contains("Eri") ||
					idWDS2.contains("Hyi") ||
					idWDS2.contains("CrA") ||
					idWDS2.contains("PsA") ||
					idWDS2.contains("Cru") ||
					idWDS2.contains("TrA") ||
					idWDS2.contains("Lac")){
				idFlamsteed=clearify(idWDS2);
			}
		}
		//System.out.println(DM+"_"+HIP+"_"+ADS+"_"+HR+"_"+HD+"_"+Bayer);
	}
	public String generateIdentifyer(){
		String x;
		try{
			x= mainID.substring(0, 5)+"0.00"+mainID.substring(5, 10)+"00.0";
		}catch(Exception e){
			//e.printStackTrace();
			x="0000000000";
		}
		return x;
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
		int counter=0;
		if(a.charAt(0)=='.'){
			a="";
		}
		return a;
	}
}
