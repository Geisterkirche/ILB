package ILBprocessing.beans;

public class NodeCCDMcoords {
	public String pairCCDM;
	public String ccdmID;
	public int flag1;
	public int flag2;
	public int el1_1I;
	public int el1_1F;
	public int el1_2I;
	public int el1_2F;
	public int el2_1I;
	public int el2_1F;
	public int el2_2I;
	public int el2_2F;
	public NodeCCDMcoords(String s){
		try{
			x(s);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void x(String s) throws Exception{
		ccdmID=s.substring(0,10);
		if(s.charAt(11)=='A'){
			pairCCDM="ZZ";
		}else if(s.charAt(10)==' '){
			pairCCDM="A"+s.charAt(11);
		}else{
			pairCCDM=""+s.charAt(10)+s.charAt(11);
		}
		el1_1I=Integer.parseInt(s.substring(13,19));
		el1_1F=Integer.parseInt(s.substring(20,22));
		el1_2I=Integer.parseInt(s.substring(23,30));
		el1_2F=Integer.parseInt(s.substring(31,32));
		el2_1I=Integer.parseInt(s.substring(35,41));
		el2_1F=Integer.parseInt(s.substring(42,44));
		el2_2I=Integer.parseInt(s.substring(45,52));
		el2_2F=Integer.parseInt(""+s.charAt(53));
		//System.doNotShowBcsResolved.println(ccdmID+" "+pairCCDM);
		flag1=50+Integer.parseInt(""+s.charAt(33));
		flag2=flag1;
	}
}
