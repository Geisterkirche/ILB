package ILBprocessing.beans;

import lib.model.service.KeysDictionary;
import lib.model.service.NodeForParsedCatalogue;
import lib.tools.GlobalPoolOfIdentifiers;

public class NodeORB6FINALIZED extends NodeForParsedCatalogue {

	public String chameleonID ="";//DD or flamsteed or DM or Bayer
	public String coordinates ="";//also data
	public String pairWDS="";
	public boolean pairWDSdefault=false;

	public NodeORB6FINALIZED(String s) {
        source=s;
		coordinates =s.substring(0, 18);
        params.put(KeysDictionary.WDSSYSTEM,s.substring(19, 29));

		//Stupid place in SCO
		chameleonID =s.substring(30, 45);
		if(GlobalPoolOfIdentifiers.likeBayer(chameleonID)){
            params.put(KeysDictionary.BAYER,GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(chameleonID));
			GlobalPoolOfIdentifiers.BAYER.add(params.get(KeysDictionary.BAYER));
		}else if(GlobalPoolOfIdentifiers.likeFlamsteed(chameleonID)){
            params.put(KeysDictionary.FLAMSTEED, GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(chameleonID));
			GlobalPoolOfIdentifiers.FLAMSTEED.add(params.get(KeysDictionary.FLAMSTEED));
		}else if(GlobalPoolOfIdentifiers.likeDM(chameleonID)){
            params.put(KeysDictionary.DM,GlobalPoolOfIdentifiers.rebuildIdForDM(chameleonID));
			GlobalPoolOfIdentifiers.DM.add(params.get(KeysDictionary.DM));
		}else{
            params.put(KeysDictionary.OBSERVER, GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(s.substring(30, 45)));
			if(s.charAt(37)!=' ') {
				pairWDS = s.substring(37, 39);
			}else{
				pairWDS="AB";
				pairWDSdefault=true;
			}
		}

		//ADS
		String idADS = (s.substring(45, 50)).replaceAll(" ","");
		if(idADS.equals(".")){
			idADS="";
		}else{
			idADS= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(idADS);
            params.put(KeysDictionary.ADS,idADS);
			GlobalPoolOfIdentifiers.ADS.add(params.get(KeysDictionary.ADS));
		}

		//HD
        String idHD = (s.substring(51, 58)).replaceAll(" ", "");
		if(idHD.equals(".")) {
			idHD="";
		}else{
			idHD= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(idHD);
            params.put(KeysDictionary.HD,idHD);
			GlobalPoolOfIdentifiers.HD.add(params.get(KeysDictionary.HD));
		}

		//HIP
        String idHIP = (s.substring(58, 65)).replaceAll(" ", "");
		if(idHIP.equals(".")) {
			idHIP="";
		}else{
			idHIP= GlobalPoolOfIdentifiers.rebuildIdToUnifiedBase(idHIP);
            params.put(KeysDictionary.HIP,idHIP);
			GlobalPoolOfIdentifiers.HIP.add(params.get(KeysDictionary.HIP));
		}
	}
}
