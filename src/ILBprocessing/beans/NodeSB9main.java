package ILBprocessing.beans;

public class NodeSB9main {
	public String mainID;
	public String data; //  055957.08+530946.2
	public String SB9;
	public NodeSB9main(String s){
		int index=0;
		try{
			while(s.charAt(index)!='|'){
				index++;
			}

		SB9=s.substring(0,index);
		int prevIndex = index;
		index++;
		while(s.charAt(index)!='|'){
			index++;
		}
		mainID=s.substring(prevIndex+1,index);
		prevIndex = index;
		index++;
		while(s.charAt(index)!='|'){
			index++;
		}
		String fakeData = s.substring(prevIndex+1,index)+"000";
		int i=0;
		while (fakeData.charAt(i)!='+' && fakeData.charAt(i)!='-'){
			i++;
		}
		i++;

		data= fakeData.substring(0,6)+'.'+fakeData.substring(6,8)+fakeData.charAt(i-1)+fakeData.substring(i,i+6)+'.'+fakeData.substring(i+6,i+7);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
