package utils;

public class StringUtils {
	public static boolean isEmpty(String s){
		if(s!=null&&!(s.trim().equals(""))){
			return false;
		}else{
			return true;
		}
	}
}
