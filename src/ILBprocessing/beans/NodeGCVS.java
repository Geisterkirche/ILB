package ILBprocessing.beans;

public class NodeGCVS {
	public String DM="";
	public String Bayer="";
	public String HD="";
	public String HIP="";
	public NodeGCVS(String s){

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
