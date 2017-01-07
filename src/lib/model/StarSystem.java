package lib.model;

import java.util.ArrayList;
import java.util.HashMap;


public class StarSystem {
							 //BU  720
	public String data=""; //  055957.08+530946.2
	public String CCDMid="";
	public String TDSCid="";

	public HashMap<String,String> params = new HashMap<String,String>();
	public boolean coordinatesNotFoundInWDS;
	public ArrayList<Pair> pairs= new ArrayList<Pair>();

}
