package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.CapDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaStrada;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaStradaBD {

	private static IRicercaStrada ricercaStrada;
	private static Log log = LogFactory.getLog(RicercaStradaBD.class);

	static {
		/*
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ricercaStrada = (IRicercaStrada) ctx
					.lookup("ContradaEAR/RicercaStrada/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}*/
		ricercaStrada=ContradaPojoFactory.getRicercaStradaIstance();

	}

	public List<StradaDTO> getStradaPerCap(String cdCap)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		

			return ricercaStrada.recuperaPerCap(cdCap);
		
	}

	public static List<StradaDTO> getStradaPerCapViaParziale(String cdCap,
			String matchVia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaStrada.recuperaPerCapViaParziale(cdCap, matchVia);

	}
	
	public static List<StradaDTO> getStradaPerCapViaParziale(String cdCap,
			Integer idLocalita,String matchVia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaStrada.recuperaPerCapLocViaParziale(cdCap,idLocalita,matchVia);

	}
	
	public static List<StradaDTO> recuperaPerCapLocViaParziale (String cdCap,Integer idLoc,String matchVia) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante
	{
		return ricercaStrada.recuperaPerCapLocViaParziale(cdCap, idLoc, matchVia);
	}
	 
	public static List<CapDTO> recuperaCapParziale(String matchCap,int cdProvincia) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		return ricercaStrada.recuperaCapParziale(matchCap,cdProvincia);
	}
	
	public static List<LocalitaDTO> recuperaLocalitaPerCap(String cdCap,
			int cdProvincia, int cdComune) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaStrada.recuperaLocalitaPerCap(cdCap, cdProvincia, cdComune);
	}
}
