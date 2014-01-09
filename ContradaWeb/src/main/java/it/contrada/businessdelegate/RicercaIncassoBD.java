package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaIncasso;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaIncassoBD {
	private static IRicercaIncasso ricercaIncasso;
	private static Log log = LogFactory.getLog(RicercaIncassoBD.class);

	static {
		/*
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ricercaIncasso = (IRicercaIncasso) ctx
					.lookup("ContradaEAR/RicercaIncasso/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException (e);
		}*/
		ricercaIncasso=ContradaPojoFactory.getRicercaIncassoIstance();

	}

	public static List<TipoIncassoDTO> elencaTipoIncasso()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {

			return ricercaIncasso.elencaTipoIncasso();

		} catch (ContradaExceptionBloccante e)
		{
			log.error(e.getMessage(), e);
			throw e;
		}
		
		catch (ContradaExceptionNonBloccante e)
		{
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
