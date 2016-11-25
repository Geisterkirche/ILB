
public class NodeStar { 
	String identifyer;
	String mainID;
	String nameOfObserver;
	String pair;
	int number;
	boolean fake;
	String data; //  055957.08+530946.2
	String idDM;	//+40 5210
					//+00 5079
	char[] modyfyer2 = new char[2];// V
					// N
	
	public NodeStar(String s){
		fake=false;
		calculateIdDM(s);
		calculateModyfyer(s);
		calculateIdentifyer(s);
		calculateNumber(s);
		calculateNameOfObserver(s);
		calculatePair(s);
		calculateData(s);
		renameID();
	}
	public void calculateIdDM(String s){
		idDM=s.substring(98, 106);
		try{
			while(idDM.charAt(0)==' '){
				idDM=idDM.substring(1, idDM.length());
			}
		}catch(Exception e){
		}
	}
	public void calculateModyfyer(String s){
		String modyfyerX=s.substring(107, 112);
		modyfyer2[0]='v';
		if(modyfyerX.contains("C")){
			modyfyer2[0]='c';
		}
		if(modyfyerX.contains("L")){
			modyfyer2[0]='l';
		}
		if(modyfyerX.contains("S")){
			modyfyer2[0]='l';
		}
		if(modyfyerX.contains("U")){
			modyfyer2[0]='l';
		}
		if(modyfyerX.contains("Y")){
			modyfyer2[0]='l';
		}
		if(modyfyerX.contains("N")){
			//modyfyer+='*';//Звездочка обозначает, что есть статья в отдельном файле, которую мы потом будем обрабатывать=)
		}
		if(modyfyerX.contains("O")){//обязательно последнее условие
			modyfyer2[1]='o';
		}
		/*if(!(modyfyer.contains("l") || modyfyer.contains("O") || modyfyer.contains("o"))){
			modyfyer[1]='0';
		}*/
	}
	public void calculateIdentifyer(String s){
		identifyer=s.substring(0, 10);
		while(identifyer.charAt(0)==' '){
			identifyer=identifyer.substring(1, identifyer.length());
		}
	};
	public void calculateData(String s){
		data=s.substring(111, s.length());
		while(data.charAt(0)==' '){
			data=data.substring(1, data.length());
		}
		if(data.charAt(0)=='.'){
			data=identifyer.substring(0, 5)+"0.00"+identifyer.substring(5, 10)+"00.0";
			fake=true;
		}
	};
	public void calculateNameOfObserver(String s){
		nameOfObserver=s.substring(10, 17);
		while(nameOfObserver.charAt(0)==' '){
			nameOfObserver=nameOfObserver.substring(1, nameOfObserver.length());
		}
	}
	public void calculateNumber(String s){
		String ss=s.substring(32, 37);
		while(ss.charAt(0)==' '){
			ss=ss.substring(1, ss.length());
		}
		int k=ss.length();
		while(ss.charAt(k-1)==' '){
			ss=ss.substring(0, k-1);
			k--;
		}
		number=Integer.parseInt(ss);
	}
	public void calculatePair(String s){
		pair=s.substring(17, 22);
		try{
			while(pair.charAt(0)==' '){
				if(pair.length()>1){
					pair=pair.substring(1, pair.length());
				}else{
					break;
				}
			}
			while(pair.charAt(pair.length()-1)==' '){
				if(pair.length()>1){
					pair=pair.substring(0, pair.length()-1);
				}else{
					break;
				}
			}
			if(pair.equals("") || pair.equals(" ") || pair.equals("  ")){
				pair="AB";
			}
		}catch(Exception e){
		}
	}
	public void renameID(){
		mainID=identifyer+nameOfObserver+pair;
		while(mainID.charAt(mainID.length()-1)==' '){
			mainID=mainID.substring(0,mainID.length()-1);
		}
	}
}

