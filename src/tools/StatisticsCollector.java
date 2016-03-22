package tools;

import java.util.ArrayList;


public class StatisticsCollector {
	public static ArrayList<String> HD = new ArrayList<String>();
	public static ArrayList<String> DM = new ArrayList<String>();
	public static ArrayList<String> HIP = new ArrayList<String>();
	public static  boolean containHD(String hd){
		if(HD.contains(hd)){
			return true;
		}
		return false;
	}
	public static  boolean validateHD(String hd){
		if(!containHD(hd)){
			HD.add(hd);
			return true;
		}else{
			//System.out.println("contain yet");
			return false;
		}
	}
	public static  boolean containDM(String hd){
		if(DM.contains(hd)){
			return true;
		}
		return false;
	}
	public static  boolean validateDM(String hd){
		if(!containDM(hd)){
			DM.add(hd);
			return true;
		}else{
			//System.out.println("contain yet");
			return false;
		}
	}
	public static  boolean containHIP(String hd){
		if(HIP.contains(hd)){
			return true;
		}
		return false;
	}
	public static boolean validateHIP(String hd){
		if(!containHIP(hd)){
			HIP.add(hd);
			return true;
		}else{
			//System.out.println("contain yet");
			return false;
		}
	}
	public static void print(){
		System.out.println("HIP size:"+HIP.size());
		System.out.println("HD size:"+HD.size());
		System.out.println("DM size:"+DM.size());
	};
}
