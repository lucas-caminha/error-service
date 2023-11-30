package br.com.error.utils;

public class Validador {
	
	public static boolean isNullOrEmpty(String s) {
		if(s == null || s.isEmpty())
			return true;
		return false;
	}
	
	public static boolean isNullOrEmpty(Integer s) {
		if(s == null)
			return true;
		return false;
	}

}
