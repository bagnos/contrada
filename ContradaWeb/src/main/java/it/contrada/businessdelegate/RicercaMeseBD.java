package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaMese;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaMeseBD {
	private static IRicercaMese ricercaMese;
	private static Log log = LogFactory.getLog(RicercaProvinciaBD.class);

	static {
		/*
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ricercaMese = (IRicercaMese) ctx
					.lookup("ContradaEAR/RicercaMese/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}*/
		ricercaMese=ContradaPojoFactory.getRicercaMeseIstance();

	}

	public static List<TipoMeseDTO> ricercaMese(int incasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			return ricercaMese.ricercaMese(incasso);
		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public static List<TipoMeseDTO> elencaMesi()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			return ricercaMese.elencaMesi();
		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

}
