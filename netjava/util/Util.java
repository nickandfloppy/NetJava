package netjava.util;

public class Util {
	public static String Truncate(String value, int maxLength) {
		if (isEmptyString(value)) return value;
		return value.length() <= maxLength ? value : value.substring(0, maxLength - 3) + "...";
	}

	public static boolean isEmptyString(String string) {
		return string == null || string.isEmpty();
	}
}