package ILBprocessing.beans;

import lib.tools.StatisticsCollector;

public class NodeORB6 {
	public String idWDS="";//00000-1930

	public String idWDS2akaDD="";//LTT 9831
	public String idFlamsteed="";
	public String idHIP="";
	public String idHD="";
	public String idADS="";
	public String idBayer="";


	public String idDM="";
	public String coordinates ="";//also data
	public String pairWDS="";
	public boolean pairWDSdefault=false;
	public String nameOfObserver="";

	public NodeORB6(String s) {
		coordinates =s.substring(0, 18);
		idWDS=s.substring(19, 29);
		idWDS2akaDD =s.substring(30, 45);
		if(StatisticsCollector.likeBayer(idWDS2akaDD)){
			idBayer= StatisticsCollector.rebuildIdToUnifiedBase(idWDS2akaDD);
		}else if(StatisticsCollector.containsFlamsteed(idWDS2akaDD)){
			idFlamsteed=StatisticsCollector.rebuildIdToUnifiedBase(idWDS2akaDD);
		}else if(StatisticsCollector.likeDM(idWDS2akaDD)){
			idDM=StatisticsCollector.cutDM(idWDS2akaDD);
		}else{
			nameOfObserver = s.substring(30, 37).toUpperCase().replaceAll(" ","");
			if(s.charAt(37)!=' ') {
				pairWDS = s.substring(37, 39);
			}else{
				pairWDS="AB";
				pairWDSdefault=true;
			}
		}
		idADS = (s.substring(45, 50)).replaceAll(" ","");
		if(idADS.equals(".")){
			idADS="";
		}
		idHD = (s.substring(51, 58)).replaceAll(" ", "");
		if(idHD.equals(".")) {
			idHD="";
		}
		idHIP = (s.substring(58, 65)).replaceAll(" ", "");
		if(idHIP.equals(".")) {
			idHIP="";
		}
	}
}
