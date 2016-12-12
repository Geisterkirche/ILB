package ILBprocessing.beans;

public class NodeWDS {
	public String wdsSystemID;
	public String nameOfObserver;//same as DD
	public String pairNameXXXXXfromWDS;
	public boolean coordinatesNotFoundInWDS;
	public String coordinatesFromWDSasString; //  055957.08+530946.2
	public String idDM;	//+40 5210 notNull
	public double theta;
	public double rho;
	public char[] modifier2 = new char[2];// V
	
	public NodeWDS(String s){
		coordinatesNotFoundInWDS =false;
		calculateIdDM(s);
		calculateModifier(s);
		calculateIdentifier(s);
		calculateNameOfObserver(s);
		calculatePair(s);
		calculateData(s);
	}
	public void calculateIdDM(String s){
		idDM=s.substring(98, 106);
		if(idDM.charAt(0)==' '){
			idDM="";
		}else{
			idDM=idDM.replaceAll("  "," ");
			idDM=idDM.replaceAll("  "," ");
			idDM=idDM.replaceAll("  "," ");
		}
	}
	public void calculateModifier(String s){
		String modifierYX=s.substring(107, 112);
		modifier2[0]='v';
		if(modifierYX.toUpperCase().contains("C")){
			modifier2[0]='c';
		}
		if(modifierYX.toUpperCase().contains("L")){
			modifier2[0]='l';
		}
		if(modifierYX.toUpperCase().contains("S")){
			modifier2[0]='l';
		}
		if(modifierYX.toUpperCase().contains("U")){
			modifier2[0]='l';
		}
		if(modifierYX.toUpperCase().contains("Y")){
			modifier2[0]='l';
		}
		if(modifierYX.toUpperCase().contains("O")){
			modifier2[1]='o';
		}
	}
	public void calculateIdentifier(String s){
		wdsSystemID =s.substring(0, 10);
	};
	public void calculateData(String s){
		coordinatesFromWDSasString =s.substring(111, s.length());
		while(coordinatesFromWDSasString.charAt(0)==' '){
			coordinatesFromWDSasString = coordinatesFromWDSasString.substring(1, coordinatesFromWDSasString.length());
		}
		if(coordinatesFromWDSasString.charAt(0)=='.'){
			coordinatesFromWDSasString = wdsSystemID.substring(0, 5)+"0.00"+ wdsSystemID.substring(5, 10)+"00.0";
			coordinatesNotFoundInWDS =true;
		}
		try {
			rho = Double.parseDouble(s.substring(52, 57)) * Math.PI / 60 / 10800;
			if(rho==-1){
				rho=0;
				System.err.println("found pair with rho=-1: "+wdsSystemID+nameOfObserver+pairNameXXXXXfromWDS);
			}
		}catch (Exception e){
			System.err.println("ERR10 Exception caught"+s.substring(52, 57));
			rho=0;
		}
		try{
			theta=Double.parseDouble(s.substring(42, 45));
		}catch (Exception e){
			System.err.println("ERR09 Exception caught"+s.substring(42, 45));
			theta=0;
			rho=0;
		}
	};
	public void calculateNameOfObserver(String s){
		nameOfObserver=s.substring(10, 17).toUpperCase().replaceAll(" ","");
	}
	public void calculatePair(String s){
		pairNameXXXXXfromWDS =s.substring(17, 22);
		try{
			pairNameXXXXXfromWDS=pairNameXXXXXfromWDS.replaceAll(" ","");
			if(pairNameXXXXXfromWDS.length()<2){
				pairNameXXXXXfromWDS ="AB";
			}
		}catch(Exception e){
			System.err.println("ERR11 FATAL Exception caught while parsing nameInILB for"+wdsSystemID+nameOfObserver+pairNameXXXXXfromWDS);
			e.printStackTrace();
		}
	}
}

