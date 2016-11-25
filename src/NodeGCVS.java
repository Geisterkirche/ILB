
public class NodeGCVS {
	String GCVSid="";
	String DM="";
	String Bayer="";
	String HD="";
	String HIP="";
	String ADS="";
	String HR="";
	public NodeGCVS(String s){
		GCVSid=s.substring(0,4);
		int i=5;
		String z="";
		while(s.charAt(i)!=' '){
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
			Bayer=s.substring(i+1,s.length());
		}
	}
	public void addEff(String s){
		int i=5;
		String z="";
		while(s.charAt(i)!=' '){
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
			Bayer=s.substring(i+1,s.length());
		}
		//System.out.println(DM+"_"+HIP+"_"+ADS+"_"+HR+"_"+HD+"_"+Bayer);
	}
}
