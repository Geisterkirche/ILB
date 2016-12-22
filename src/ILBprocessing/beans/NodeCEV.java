package ILBprocessing.beans;

public class NodeCEV {
	public String WDScoord="";
	public String idFlamsteed ="";
	public String idCEV="";
	public String idHIP="";
	public boolean verifyed;
	public NodeCEV(String s){
		//System.doNotShowBcsResolved.println("CEV "+s);
		WDScoord = s.substring(123,131)+s.substring(132,138);
		idCEV = clearify(s.substring(0,10));
		idFlamsteed =clearify(s.substring(0,10));
		//for(int i = 0; i< WDSparser.listGCVS.size(); i++){
			/*if(WDSparser.listGCVS.get(i)){

			}*/
		//}
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
		if(a.charAt(0)=='.'){
			a="";
		}
		return a;
	}
}
