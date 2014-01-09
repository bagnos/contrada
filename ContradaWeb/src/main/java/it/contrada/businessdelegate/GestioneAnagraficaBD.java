package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneAnagrafica;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneAnagraficaBD {
	private static IGestioneAnagrafica gestioneAnagrafica;
	private static Log log = LogFactory.getLog(GestioneAnagraficaBD.class);

	private GestioneAnagraficaBD() {
	}

	static {
		// if (gestioneAnagrafica == null) {
		gestioneAnagrafica = ContradaPojoFactory
				.getGestioneAnagraficaInstance();
		/*
		 * InitialContext ctx; try { ctx = new InitialContext();
		 * gestioneAnagrafica = (IGestioneAnagrafica) ctx
		 * .lookup("ContradaEAR/GestioneAnagrafica/local"); } catch
		 * (NamingException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage(), e); throw new RuntimeException(e); }
		 */

		// }
	}

	public static AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return gestioneAnagrafica.inserisciAnagrafica(anagraficaDTO);

	}

	public static AnagraficaDTO aggiornaAnagrafica(AnagraficaDTO anagraficaDTO,
			boolean nuovoRid) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return gestioneAnagrafica.aggiornaAnagrafica(anagraficaDTO, nuovoRid);

	}

	public static int aggiornaGestore(List<AnagraficaDTO> anagrafiche)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return gestioneAnagrafica.aggiornaGestore(anagrafiche);
	}

	public static AnagraficaDTO inserisciAnagraficaConRid(
			AnagraficaDTO anagraficaDTO) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return gestioneAnagrafica.inserisciAnagraficaConRid(anagraficaDTO);
	}

	public static int aggiornaStatoAnagrafica(List<Integer> idAnagrafiche,
			int idStato) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return gestioneAnagrafica.aggiornaStatoAnagrafica(idAnagrafiche,
				idStato);
	}
}
