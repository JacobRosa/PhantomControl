package jacobrosa.phantomcontrol.utils;

public class Utils {
	
	public static boolean booleanFromString(String string) {
		if(string.equalsIgnoreCase("true"))
			return true;
		else if(string.equalsIgnoreCase("false"))
			return false;
		return false;
	}

}
