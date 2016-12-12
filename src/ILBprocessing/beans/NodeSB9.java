package ILBprocessing.beans;

import lib.tools.StatisticsCollector;

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
		//System.doNotShowBcsResolved.println(s);
		SB9=s.substring(0,index);
		//System.doNotShowBcsResolved.println("debug SB: "+ SB9);
		int i=index+1;
		String z="";
		while(s.charAt(i)!='|'){
			z=z+s.charAt(i);
			i++;
		}
		//System.doNotShowBcsResolved.println(z);
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
			if(StatisticsCollector.containsFlamsteed(idWDS2)){
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
			if(StatisticsCollector.containsFlamsteed(idWDS2)){
				idFlamsteed=clearify(idWDS2);
			}
		}
		//System.doNotShowBcsResolved.println(DM+"_"+HIP+"_"+ADS+"_"+HR+"_"+HD+"_"+Bayer);
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
