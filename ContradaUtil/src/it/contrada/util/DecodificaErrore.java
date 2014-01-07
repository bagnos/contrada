package it.contrada.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DecodificaErrore {

	private static Properties prop;
	private static String errore="Temporanei problemi di collegamento";

	private static Properties getPropFile() throws IOException {
		if (prop == null) {
			prop = new Properties();
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("erroriContradaBackend.properties"); 
			prop.load(is);
			is.close();
		} 
		return prop;
	}

	public static String get5018() {
		try {
			String testo= getPropFile().getProperty("5018");
			return testo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return errore;
		}
	}
	
	public static String getError(String nrError) {
		try {
			String testo= getPropFile().getProperty(nrError);
			return testo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return errore;
		}
	}
}
