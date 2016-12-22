package lib.model;

public class Pair extends Component{

	public double rho=0;
	public double theta=0;

	public Component el1;//reference to pair or component
	public Component el2;//reference to pair or component

	public String pairIdILB="";//AB and the same
	public String pairWDS="";//AB and the same
	public String pairCCDM="";//AB and the same
	public String pairTDSC="";//AB and the same

	public String observer="";

	public String idFlamsteed ="";
	public String idBayer="";
	public String idCEV="";

	public char[] modifier;  // [VO_____]

	public String systemCCDM ="";
	public String systemTDSC ="";

	public Pair(){
		el1=new Component();
		el2=new Component();
		modifier =new char[10];
		for(int i=0;i<10;i++){
			modifier[i]=0;
		}
	}
	//TODO: refactor this stuff
	public void nameComponentsByPairNameForWDS(){
		if(pairWDS.length()!=0){
			pairIdILB=pairWDS;
			int positionOfSpecialChar=0;
			boolean existSpecialChar=false;
			for(int i = 0; i< pairIdILB.length(); i++){
				if(pairIdILB.charAt(i)==',' || pairIdILB.charAt(i)=='-'){
					pairIdILB=pairIdILB.replaceFirst("\\,", "-");
					positionOfSpecialChar=i;
					existSpecialChar=true;
					break;
				}
			}
			//TODO: refactor this stuff
			//NEVER TOUCH THIS CODE! It's danger for life!!
			if(existSpecialChar){
				int n=numberExists(pairIdILB);
				//exist special symbol and we know where
				if(n!=0){
					if(positionOfSpecialChar>n){
						el1.nameInILB = pairIdILB.substring(0, n)+'a';
						el2.nameInILB = pairIdILB.substring(0, n)+'b';
						pairIdILB =el1.nameInILB +"-"+el2.nameInILB;
					}else{
						el1.nameInILB = pairIdILB.substring(0, positionOfSpecialChar);
						el2.nameInILB =""+ pairIdILB.charAt(0)+ pairIdILB.charAt(3);
						if(pairIdILB.charAt(4)=='1'){
							el2.nameInILB =el2.nameInILB +'a';
						}
						if(pairIdILB.charAt(4)=='2'){
							el2.nameInILB =el2.nameInILB +'b';
						}
					}
				}else{
					//special symbol not exist
					el1.nameInILB = pairIdILB.substring(0, positionOfSpecialChar);
					el2.nameInILB = pairIdILB.substring(positionOfSpecialChar+1, pairIdILB.length());
				}
				pairIdILB =el1.nameInILB +"-"+el2.nameInILB;
			}else{
				el1.nameInILB =""+ pairIdILB.charAt(0);
				el2.nameInILB =""+ pairIdILB.charAt(1);
				pairIdILB = pairIdILB.charAt(0)+"-"+ pairIdILB.charAt(1);
			}
		}else{
			System.err.println("ERR12 FATAL Exception. Name of pair in WDS can't be empty");
		}
	}

	public int numberExists(String id){
		for(int i=0;i<id.length();i++){
			for(int n=48;n<57;n++){
				if(id.charAt(i)==n){
					return i;
				}
			}
		}
		return 0;
	}

}
