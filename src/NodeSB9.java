public class NodeSB9 {
	String SB9;
	String GCVSid="";
	String DM="";
	String Bayer="";
	String HD="";
	String HIP="";
	String ADS="";
	String HR="";
	public NodeSB9(String s, int index){
		//System.out.println(s);
		SB9=s.substring(0,index);
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
			Bayer=s.substring(i+1,s.length());
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
			Bayer=s.substring(i+1,s.length());
		}
		//System.out.println(DM+"_"+HIP+"_"+ADS+"_"+HR+"_"+HD+"_"+Bayer);
	}
}
