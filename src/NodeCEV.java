
public class NodeCEV {
	String WDScoord;
	String Hyi;
	public NodeCEV(String s){
		WDScoord = s.substring(123,131)+s.substring(132,138);
		Hyi=clearify(s.substring(0,10));
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
