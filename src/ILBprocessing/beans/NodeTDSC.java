package ILBprocessing.beans;

public class NodeTDSC {
	public String TDSCid="";
	public String pairTDSC="";
	public String WDSid="";
	public String HIPid="";
	public String CCDMidPAIR="";
	public String HD="";
	public NodeTDSC(String s){
		TDSCid=s.substring(0,5);
		while(TDSCid.charAt(0)==' '){
			TDSCid=TDSCid.substring(1,TDSCid.length());
		}
		while(TDSCid.charAt(TDSCid.length()-1)==' '){
			TDSCid=TDSCid.substring(0,TDSCid.length()-1);
		}
		if(s.charAt(7)==' '){
			pairTDSC=""+'A'+s.charAt(6);
		}else{
			pairTDSC=s.substring(6,7);
		}
		WDSid=s.substring(130,140);
		try{
			HIPid=s.substring(143,148);
			while(HIPid.charAt(0)==' '){
				HIPid=HIPid.substring(1, HIPid.length());
			}
			while(HIPid.charAt(HIPid.length()-1)==' '){
				HIPid=HIPid.substring(0, HIPid.length()-1);
			}
		}catch(Exception e){
			HIPid="";
		}
		CCDMidPAIR=s.substring(149,151);
		if(CCDMidPAIR.charAt(1)==' ' && CCDMidPAIR.charAt(0)==' '){
			CCDMidPAIR="";
		}else{
			if(CCDMidPAIR.charAt(0)!=' '){
				if(CCDMidPAIR.charAt(0)=='A'){
					CCDMidPAIR="A"+CCDMidPAIR.charAt(1);
				}else{
					CCDMidPAIR="AB";
				}
			}
		}
		try{
		HD=s.substring(152,158);
			while(HD.charAt(0)==' '){
				HD=HD.substring(1, HD.length());
			}
			while(HD.charAt(HD.length()-1)==' '){
				HD=HD.substring(0, HD.length()-1);
			}
		}catch(Exception e){
			HD="";
		}
	}
}