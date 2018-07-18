package example.demo.hacker.rank.services.impl;

import org.springframework.stereotype.Service;

import example.demo.domain.Solucion;

@Service
public class SolucionService {

	
	public static String[] domainType(String[] domains) {
		String[] result = new String[domains.length];
		int i = 0;
		for(String string : domains) {
			String[] keys = string.split("\\.");
			String key = keys[keys.length-1];
			switch (key) {
			case "org":
				result[i] = "organization";
				i++;
				break;
			case "com":
				result[i] = "commercial";
				i++;
				break;
				
			case "net":
				result[i] = "network";
				i++;
				break;

			default:
				result[i] = "information";
				i++;
				break;
			}
		}
		return result;

	}
	
	public static void main(String[] args) {
	String[]	domains =  {"en.wiki.org", "codefights.com", "happy.net", "code.info"};
	
	String[] result = domainType(domains);
	}
	
	static Boolean findString(String s1, String s2, int n) {
		boolean result;
		if (s2.indexOf(s1.substring(n, n + 1)) == -1) {
			if (n < s1.length()-1) {
				result = findString(s1, s2, n + 1);
			} else {
				result = false;
			}

		} else {
			result = true;
		}
		return result;
	}

	static String twoStrings(String s1, String s2) {
		if (findString(s1, s2, 0)) {
			return "YES";
		} else {
			return "NO";
		}
	}
	
	public String existString(Solucion solucion) {
		return twoStrings(solucion.getString(), solucion.getString2());
	}

}
