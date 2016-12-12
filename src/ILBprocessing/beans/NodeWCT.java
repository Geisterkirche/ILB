package ILBprocessing.beans;

public class NodeWCT {
	public String idWCT;
	public String[] parameter;
	public NodeWCT(String s){
		parameter = new String[6];
		idWCT=s;
		parseWCTstring(s);
	}
	public void parseWCTstring(String s){
		int parameterIndex=0;
		int parameterIndexCurrent=0;
		boolean closed=true;
		int n=0;
		String a = "";
		boolean prevChar=true;
		boolean currentChar=true;
		while(n<s.length()){
			prevChar=currentChar;
			if(s.charAt(n)=='"'){
				closed=!closed;
			}
			if(s.charAt(n)==' '){
				currentChar=true;
				if(!prevChar && closed){
					if(parameterIndexCurrent==5 || parameterIndexCurrent==6 || parameterIndexCurrent==7 || parameterIndexCurrent==8 || parameterIndexCurrent==9 || parameterIndexCurrent==4){
						parameter[parameterIndex]=a;
			//			System.doNotShowBcsResolved.print(parameter[parameterIndex]+"_");
						parameterIndex++;
					}
					a="";
					parameterIndexCurrent++;
				}
			}else{
				currentChar=false;
				a=a+s.charAt(n);
			}
			n++;
		}
		//				System.doNotShowBcsResolved.println();
		if(parameterIndexCurrent!=63){
			System.out.println("exc"+parameterIndexCurrent);
			System.out.println(s);
		}
		if(parameter[1].equals("\"\"")){
			parameter[1]="";
		}
		if(parameter[0].equals("\"\"")){
			parameter[0]="";
		}
		if(parameter[2].equals("\"\"")){
			parameter[2]="";
		}
		if(parameter[3].equals("\"\"")){
			parameter[3]="";
		}
		if(parameter[4].equals("\"\"")){
			parameter[4]="";
		}
		if(parameter[5].equals("\"\"")){
			parameter[5]="";
		}
		try{
			while(parameter[4].charAt(0)==' '){
				parameter[4]=parameter[4].substring(1,parameter[4].length());
			}
			if(parameter[4].length()>0){
				while(parameter[4].charAt(parameter[4].length()-1)==' '){
					parameter[4]=parameter[4].substring(0,parameter[4].length()-1);
					if(parameter[4].length()==0){
						break;
					}
				}
			}
		}catch(Exception r){
			parameter[4]="";
		}
	};
}
