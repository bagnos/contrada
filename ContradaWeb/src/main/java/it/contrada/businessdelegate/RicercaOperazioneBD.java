package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.OperazioneDTO;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneRid;
import it.contrada.interfaces.IRicercaOperazione;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaOperazioneBD {

	private static IRicercaOperazione ricercaOperazione;
	private static Log log = LogFactory.getLog(RicercaOperazioneBD.class);

	static {
		if (ricercaOperazione == null) {
			ricercaOperazione=ContradaPojoFactory.getRicercaOperazioneInstance();
			/*
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				ricercaOperazione = (IRicercaOperazione) ctx
						.lookup("ContradaEAR/RicercaOperazione/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException (e);
			}*/

		}
	}
	public static List<OperazioneDTO> elencaUltimeOperazioni() throws ContradaExceptionNonBloccante
	{
		return ricercaOperazione.elencaUltimeOperazioni();
	}
	
}
