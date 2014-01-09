package it.contrada.web.util;

import it.contrada.exceptions.ContradaExceptionBloccante;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Configuration {

	static Properties properties = null;
	private static Log log = LogFactory.getLog(Configuration.class);
	private static Configuration conf;
	
	static {
		if (properties == null) {
			properties = new Properties();
			InputStream is = null;
			try {				
				is=Thread.currentThread().getContextClassLoader().getResourceAsStream(Costante.PROP_FILE);
				properties.load(is);
			} catch (IOException e) {

			} finally {
				try {
					if (is != null) {
						is.close();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block

				}
			}
		}

	}

	public Configuration() throws ContradaExceptionBloccante {
		// TODO Auto-generated constructor stub
	}
	
	public static Configuration getConfiguration() throws ContradaExceptionBloccante
	{
		if (conf==null)
		{
			conf = new Configuration();
		}
		return conf;
	}
	

	public static String getProperty(String key) throws ContradaExceptionBloccante {
		String value = null;
		
		if (properties != null) {
			if (properties.containsKey(key)) {
				value = properties.getProperty(key);
				return value;
			} else {
				log.error(key + " non presente");
				throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
			}

		} else { 
			log.error(Errori.FILE_PROP_NON_PRES);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}
	}
	
	public int getIntProperty(String key) throws ContradaExceptionBloccante
	{
		return Integer.parseInt(getProperty(key));
	}
}
