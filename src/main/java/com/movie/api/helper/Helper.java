package com.movie.api.helper;

public class Helper {
	
	
	public static int stringToInteger(String str) {
		int valor;
		
		try {
			valor = Integer.parseInt(str.trim());
		} catch (Exception e) {
			valor = 0;
		}

		return valor;
	}

}
