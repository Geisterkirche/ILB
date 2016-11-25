
public class NodeCCDM {
	public String pairCCDM="";
	public String nameOfObserver="";
	public String wdsID="";
	public String ccdmID="";
	public String idsID="";
	public String DM="";
	public String HD="";
	public String ADS="";
	public String HIP="";
	public boolean astrometric = false;
	public NodeCCDM(String s){
		ccdmID=s.substring(1,11);
		if(s.charAt(12)=='A'){
			pairCCDM="AB";
		}else if(s.charAt(11)==' '){
			pairCCDM="A"+s.charAt(12);
		}else{
			pairCCDM=""+s.charAt(11)+s.charAt(12);
		}
		if(s.charAt(14)=='%' ||s.charAt(14)=='&'){
			astrometric=true;
		}
		try{
			HIP=s.substring(126,132);
			while(HIP.charAt(0)==' '){
				HIP=HIP.substring(1, HIP.length());
			}
			while(HIP.charAt(HIP.length()-1)==' '){
				HIP=HIP.substring(0, HIP.length()-1);
			}
		}catch(Exception e){
			HIP="";
		}
		try{
			nameOfObserver=s.substring(15,22);
		}catch(Exception e){
			nameOfObserver="";
		}
		try{
			DM=s.substring(77,87);
			while(DM.charAt(0)==' '){
				DM=DM.substring(1, DM.length());
			}
			while(DM.charAt(DM.length()-1)==' '){
				DM=DM.substring(0, DM.length()-1);
			}
		}catch(Exception r){
			DM="";
		}
		try{
			HD=s.substring(98,104);
			while(HD.charAt(0)==' '){
				HD=HD.substring(1, HD.length());
			}
			while(HD.charAt(HD.length()-1)==' '){
				HD=HD.substring(0, HD.length()-1);
			}
		}catch(Exception r){
			HD="";
		}
		try{
			if(s.charAt(106)=='A'){
				ADS=s.substring(107,112);
				while(ADS.charAt(0)==' '){
					ADS=ADS.substring(1, ADS.length());
				}
				while(ADS.charAt(ADS.length()-1)==' '){
					ADS=ADS.substring(0, ADS.length()-1);
				}
			}
		}catch(Exception r){
			ADS="";
		}
		try{
			if(s.charAt(113)!='W'){
				idsID=s.substring(114,125);
				while(idsID.charAt(0)==' '){
					idsID=idsID.substring(1, idsID.length());
				}
				while(idsID.charAt(idsID.length()-1)==' '){
					idsID=idsID.substring(0, idsID.length()-1);
				}
			}else{
				wdsID=s.substring(114,125);
			}
		}catch(Exception r){
			idsID="";
			wdsID="";
		}
	}
}
