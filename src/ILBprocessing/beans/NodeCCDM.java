package ILBprocessing.beans;

public class NodeCCDM{
    public String pairCCDM = "";
    public String nameOfObserver = "";
    public String wdsID = "";
    public String ccdmID = "";
    public String idsID = "";
    public String DM = "";
    public String HD = "";
    public String ADS = "";
    public String HIP = "";
    public char componentInfo;
    public int coord_I2_1 = 0;
    public int coord_I2_2 = 0;
    public int coord_F2_1 = 0;
    public int coord_F2_2 = 0;
    public boolean astrometric = false;

    public NodeCCDM(String s) {
        /*TODO: -003.69 0018.0 converts in -003.69+0018.0  can be wrong*/
        ccdmID = s.substring(1, 11);
        if (s.charAt(12) == 'A') {
            componentInfo = 'A';
            pairCCDM = "ZZ";
            parseCoordinates(s);
        } else if (s.charAt(11) == ' ') {
            pairCCDM = "A" + s.charAt(12);
            componentInfo = s.charAt(12);
            parseCoordinates(s);
        } else {
            pairCCDM = "" + s.charAt(11) + s.charAt(12);
            componentInfo = s.charAt(12);
            parseCoordinates(s);
        }
        if (s.charAt(14) == '%' || s.charAt(14) == '&') {
            astrometric = true;
        }
        try {
            HIP = s.substring(126, 132);
            while (HIP.charAt(0) == ' ') {
                HIP = HIP.substring(1, HIP.length());
            }
            while (HIP.charAt(HIP.length() - 1) == ' ') {
                HIP = HIP.substring(0, HIP.length() - 1);
            }
        } catch (Exception e) {
            HIP = "";
        }
        try {
            nameOfObserver = s.substring(15, 22);
        } catch (Exception e) {
            nameOfObserver = "";
        }
        try {
            DM = s.substring(77, 87);
            while (DM.charAt(0) == ' ') {
                DM = DM.substring(1, DM.length());
            }
            for (int i = 0; i < DM.length(); i++) {
                if (DM.charAt(i) == '.') {
                    DM = DM.substring(0, i);

                }
            }
            try {
                if (DM.charAt(2) != ' ' && DM.charAt(1) != ' ') {
                    DM = DM.substring(0, 3) + " " + DM.substring(3, DM.length());
                }
            } catch (Exception e) {
            }
            while (DM.charAt(DM.length() - 1) == ' ') {
                DM = DM.substring(0, DM.length() - 1);
            }
        } catch (Exception r) {
            DM = "";
        }
        try {
            HD = s.substring(98, 104);
            while (HD.charAt(0) == ' ') {
                HD = HD.substring(1, HD.length());
            }
            while (HD.charAt(HD.length() - 1) == ' ') {
                HD = HD.substring(0, HD.length() - 1);
            }
        } catch (Exception r) {
            HD = "";
        }
        try {
            if (s.charAt(106) == 'A') {
                ADS = s.substring(107, 112);
                while (ADS.charAt(0) == ' ') {
                    ADS = ADS.substring(1, ADS.length());
                }
                while (ADS.charAt(ADS.length() - 1) == ' ') {
                    ADS = ADS.substring(0, ADS.length() - 1);
                }
            }
        } catch (Exception r) {
            ADS = "";
        }
        try {
            if (s.charAt(113) != 'W') {
                idsID = s.substring(114, 125);
                while (idsID.charAt(0) == ' ') {
                    idsID = idsID.substring(1, idsID.length());
                }
                while (idsID.charAt(idsID.length() - 1) == ' ') {
                    idsID = idsID.substring(0, idsID.length() - 1);
                }
            } else {
                wdsID = s.substring(114, 125);
            }
        } catch (Exception r) {
            idsID = "";
            wdsID = "";
        }
    }

    public void parseCoordinates(String s) {
        coord_I2_1 = (int) Integer.parseInt(s.substring(1, 6));
        if (s.charAt(6) != ' ') {
            coord_I2_2 = (int) Integer.parseInt(s.substring(6, 11));
        } else {
            coord_I2_2 = (int) Integer.parseInt(s.substring(7, 11));
        }
        if (s.charAt(27) != ' ') {
            if (s.charAt(23) != ' ') {
                coord_I2_1 += (int) Integer.parseInt(s.substring(23, 27));
            } else {
                coord_I2_1 += (int) Integer.parseInt(s.substring(24, 27));
            }
            coord_F2_1 = (int) Integer.parseInt(s.substring(28, 30));
            if (s.charAt(30) != ' ') {
                coord_I2_2 += (int) Integer.parseInt(s.substring(30, 35));
            } else {
                coord_I2_2 += (int) Integer.parseInt(s.substring(31, 35));
            }
            coord_F2_2 = (int) Integer.parseInt("" + s.charAt(36));
        }
    }
}
