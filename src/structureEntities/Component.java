package structureEntities;

public class Component {
	public String name="";
	public String nameCCDM="";
	public int coord1F;
	public int coord2F;
	public int coord2I;
	public int coord1I;
	public int coord_flag=0;
	public String newName="";
	public int hash;
	public boolean out;
	
	public String idHIP="";
	public String idHD="";
	public String idGCVS="";
	public String idSB9="";
	public String idADS="";
	public String idHyi="";
	//public String idBayer="";
	public String idDM="";
	
	
	
	public Component(){
		out=false;
	}
	public int hash(){
		int hash =0;
		if(name.length()>1 && name.charAt(1)!='a' && name.charAt(1)!='b' && name.charAt(1)!='c' && name.charAt(1)!='d' && name.charAt(1)!='e' && name.charAt(1)!='f'){
			hash = -100*(int)(name.charAt(0)-64)+(int)(name.charAt(1)-64);
		}else if(name.length()>1 && (name.charAt(1)=='a' || name.charAt(1)=='b' || name.charAt(1)=='c' || name.charAt(1)=='d' || name.charAt(1)=='e' || name.charAt(1)=='f')){
			for(int i=0;i<name.length();i++){
				hash +=(int)(name.charAt(1)-64)*100^name.length();
				hash/=100;
			}
		}else{
			hash = (int)(name.charAt(0)-64);
		}
		return hash;
	}
	public static int hash(String name){
		int hash =0;
		if(name.length()>1 && name.charAt(1)!='a' && name.charAt(1)!='b' && name.charAt(1)!='c' && name.charAt(1)!='d' && name.charAt(1)!='e' && name.charAt(1)!='f'){
			hash = -100*(int)(name.charAt(0)-64)+(int)(name.charAt(1)-64);
		}else if(name.length()>1 && (name.charAt(1)=='a' || name.charAt(1)=='b' || name.charAt(1)=='c' || name.charAt(1)=='d' || name.charAt(1)=='e' || name.charAt(1)=='f')){
			for(int i=0;i<name.length();i++){
				hash +=(int)(name.charAt(1)-64)*100^name.length();
				hash/=100;
			}
		}else{
			hash = (int)(name.charAt(0)-64);
		}
		return hash;
	}
}
