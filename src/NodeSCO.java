public class NodeSCO {
	String idWDS;//00000-1930
	
	String idWDS2;//LTT 9831
				  //I   699AB
				  //CHR 122Aa,Ab
	String idHyi="";
	String idHIP="";
	String idHD="";
	//
	String idCD="";
	String idBD="";
	String idCP="";
	//
	String idADS="";
	String idBayer="";
	String idJ2000="";
	String pairWDS="";
	String nameOfObserver="";
	String data="";
	public NodeSCO(String s) {
		calculateNameOfObserver(s);
		calculateIdWDS(s);
		calculateIdHIP(s);
		calculateIdADS(s);
		calculateIdHD(s);
		calculateIdJ2000(s);
		calculateData(s);
		calculatePair(s);
		//System.out.println(idWDS+" "+ idJ2000+"  "+pairWDS+"  "+idHyi+"  "+idHIP+"  "+idHD+"  "+idBayer);
	}
	public void calculateData(String s){
		data = s.substring(0,18);
	}
	public void calculateNameOfObserver(String s){
		 nameOfObserver=s.substring(30, 37);
//		 if(!nameOfObserver.contains("..")){
//			 nameOfObserver=s.substring(30, 38);
//		 }
	}
	public void calculateIdWDS(String s){
		idWDS=s.substring(19, 30);
		while(idWDS.charAt(0)==' '){
			idWDS=idWDS.substring(1, idWDS.length());
		}
		while(idWDS.charAt(idWDS.length()-1)==' '){
			idWDS=idWDS.substring(0, idWDS.length()-1);
		}
		idWDS2=s.substring(30, 45);
		if(		idWDS2.contains("alp") ||
				idWDS2.contains("bet") ||
				idWDS2.contains("gam") ||
				idWDS2.contains("del") ||
				idWDS2.contains("eps") ||
				idWDS2.contains("zet") ||
				idWDS2.contains("eta") ||
				idWDS2.contains("the") ||
				idWDS2.contains("iot") ||
				idWDS2.contains("kap") ||
				idWDS2.contains("lam") ||
				idWDS2.contains("mu") ||
				idWDS2.contains("nu") ||
				idWDS2.contains("xi") ||
				idWDS2.contains("omi") ||
				idWDS2.contains("pi") ||
				idWDS2.contains("rho") ||
				idWDS2.contains("sig") ||
				idWDS2.contains("tau") ||
				idWDS2.contains("ups") ||
				idWDS2.contains("phi") ||
				idWDS2.contains("chi") ||
				idWDS2.contains("psi") ||
				idWDS2.contains("ome")){
			idBayer=idWDS2.substring(0, idWDS2.length());
			//pairWDS="AB";//сомнительное решение
		}else{
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
				idHyi=clearify(idWDS2);
			}else{
				if(idWDS2.length()>8){
					if(idWDS2.charAt(7)=='A' || idWDS2.charAt(7)=='B' || idWDS2.charAt(7)=='C' || idWDS2.charAt(7)=='D' || idWDS2.charAt(7)=='E' || idWDS2.charAt(7)=='F' || idWDS2.charAt(7)=='G' || idWDS2.charAt(7)=='H' || idWDS2.charAt(7)=='I' || idWDS2.charAt(7)=='J' || idWDS2.charAt(7)=='K' ||idWDS2.charAt(7)=='L' ||idWDS2.charAt(7)=='M' ||idWDS2.charAt(7)=='N' ||idWDS2.charAt(7)=='O' ||idWDS2.charAt(7)=='P' ||idWDS2.charAt(7)=='Q' ||idWDS2.charAt(7)=='R' ||idWDS2.charAt(7)=='S' ||idWDS2.charAt(7)=='T' ||idWDS2.charAt(7)=='U' ||idWDS2.charAt(7)=='V' ||idWDS2.charAt(7)=='W' ||idWDS2.charAt(7)=='X' ||idWDS2.charAt(7)=='Y' ||idWDS2.charAt(7)=='Z'){
						calculatePair(s);
					}else{
						pairWDS="AB";
					}
					if(idWDS2.charAt(7)==' ' && idWDS2.charAt(8)==' '){
						calculatePair(s);
					}
				}
				try{
					nameOfObserver=clearify(idWDS2.substring(0, 7));
				}catch(Exception e){
					try{
					nameOfObserver=clearify(idWDS2.substring(0, 6));
					}catch(Exception ee){
						System.out.println(idWDS2);
						nameOfObserver=clearify(idWDS2);
					}
				}
			}
		}
	}
	public void calculateIdHIP(String s){
		idHIP=clearify(s.substring(58, 65));
	}
	public void calculateIdADS(String s){
		try{
			idADS=clearify(s.substring(45, 50));
		}catch(Exception e){
			idADS="";
		}
	}
	public void calculateIdHD(String s){
		idHD=clearify(s.substring(51, 58));
	}
	public void calculateIdJ2000(String s){
		idJ2000=s.substring(0, 18);
	}
	public void calculatePair(String s){
		idWDS2=s.substring(30, 45);
		if(idWDS2.contains("BD") &&(idWDS2.contains("+") || idWDS2.contains("-"))){
			idBD=s.substring(30, 45);
			nameOfObserver=idWDS2;
			pairWDS="AB";
		}else if(idWDS2.contains("CD")&&(idWDS2.contains("+") || idWDS2.contains("-"))){
			idCD=s.substring(30, 45);
			nameOfObserver=idWDS2;
			pairWDS="AB";
		}else if(idWDS2.contains("CP")&&(idWDS2.contains("+") || idWDS2.contains("-"))){
			idCP=s.substring(30, 45);
			nameOfObserver=idWDS2;
			pairWDS="AB";
		}
		if(pairWDS!=null){
			return;
		}
		pairWDS=s.substring(37, 45);
		boolean clean=true;
		while(clean){
			for(int i=0;i<pairWDS.length();i++){
				if(pairWDS.charAt(i)==' '){
					if(i!=pairWDS.length()-1){
						pairWDS=pairWDS.substring(0, i)+pairWDS.substring(i+1, pairWDS.length());
						break;
					}else{
						pairWDS=pairWDS.substring(0, i);
						break;
					}
				}
			}
			clean=false;
			for(int i=0;i<pairWDS.length();i++){
				if(pairWDS.charAt(i)==' '){
					clean=true;
					break;
				}
			}
		}
		if(pairWDS.length()<2){
			if(pairWDS.length()==1 && pairWDS!="p"){
				if(pairWDS.charAt(0)=='A' || pairWDS.charAt(0)=='B' || pairWDS.charAt(0)=='C' || pairWDS.charAt(0)=='D' || pairWDS.charAt(0)=='E' || pairWDS.charAt(0)=='F' || pairWDS.charAt(0)=='G' || pairWDS.charAt(0)=='H' || pairWDS.charAt(0)=='I' || pairWDS.charAt(0)=='J' || pairWDS.charAt(0)=='K' ||pairWDS.charAt(0)=='L' ||pairWDS.charAt(0)=='M' ||pairWDS.charAt(0)=='N' ||pairWDS.charAt(0)=='O' ||pairWDS.charAt(0)=='P' ||pairWDS.charAt(0)=='Q' ||pairWDS.charAt(0)=='R' ||pairWDS.charAt(0)=='S' ||pairWDS.charAt(0)=='T' ||pairWDS.charAt(0)=='U' ||pairWDS.charAt(0)=='V' ||pairWDS.charAt(0)=='W' ||pairWDS.charAt(0)=='X' ||pairWDS.charAt(0)=='Y' ||pairWDS.charAt(0)=='Z'){
					pairWDS=pairWDS+"a,"+pairWDS+"b";
				}else{
					pairWDS="AB";
				}
			}else{
				pairWDS="AB";
			}
		}
	}
	public String clearify(String a){
		for(int i=0;i<a.length();i++){
			if(a.charAt(0)==' '){
				a=a.substring(1,a.length());
				i--;
			}else{
				break;
			}
		}
		for(int i=0;i<a.length();i++){
			if(a.charAt(a.length()-1)==' '){
				a=a.substring(0,a.length()-1);
				i--;
			}else{
				break;
			}
		}
		int counter=0;
		for(int i=0;i<a.length();i++){
			if(a.charAt(i)==' '){
				counter++;
				if(counter==2){
					a=a.substring(0,i);
					break;
				}
			}
		}
		if(a.charAt(0)=='.'){
			a="";
		}
		return a;
	}
}
