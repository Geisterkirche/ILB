package lib.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class StarSystem {
	public String idWDS="";  //00409+3107
	public String wdsSystemID ="";//HDS3356
							 //BU  720
	public String data=""; //  055957.08+530946.2
	public String CCDMid="";
	public String TDSCid="";
	public String observ="";
	public boolean coordinatesNotFoundInWDS;
	public Map<Integer,String> hash = new HashMap<Integer,String>();
	public ArrayList<Pair> pairs= new ArrayList<Pair>();
	public ArrayList<String> resolvedComponents = new ArrayList<String>();
	public void checkAndMarkComponentAsResolved(String s){
		for(int i=0;i<pairs.size();i++){
			if(pairs.get(i).el1.nameInILB.equals(s)){
				pairs.get(i).el1.doNotShowBcsResolved =true;
				return;
			}
			if(pairs.get(i).el2.nameInILB.equals(s)){
				pairs.get(i).el2.doNotShowBcsResolved =true;
				return;
			}
		}
		resolvedComponents.add(s);
	}
}
