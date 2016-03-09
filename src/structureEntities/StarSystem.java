package structureEntities;

import structureEntities.Pair;

import java.util.ArrayList;


public class StarSystem {
	public String idWDS="";  //00409+3107TOK 378AB
						   //00409+3107RAO   3Ba,Bb
	public String identifyer="";//HDS3356
							 //BU  720
	public String data=""; //  055957.08+530946.2
	public String CCDMid="";
	public String TDSCid="";
	public String observ="";
	public boolean out=false;
	public boolean fake;
	public ArrayList<Integer> hash = new ArrayList<Integer>();
	public ArrayList<Pair> pairs= new ArrayList<Pair>();
	public ArrayList<String> exceptions= new ArrayList<String>();
	public void findCompOut(String s){
		for(int i=0;i<pairs.size();i++){
			if(pairs.get(i).el1.name.equals(s)){
				pairs.get(i).el1.out=true;
				return;
			}
			if(pairs.get(i).el2.name.equals(s)){
				pairs.get(i).el2.out=true;
				return;
			}
		}
		exceptions.add(s);
	}
}
