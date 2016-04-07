package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.RicezioneFlussoIncassoRidDTO;
import it.contrada.interfaces.IGestioneFlusso;
import it.contrada.preautrid.dto.DisposizionePreautRicezioneDTO;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestioneFlussoBD {
	private static IGestioneFlusso gestioneFlusso;
	private static Log log = LogFactory.getLog(GestioneFlussoBD.class);

	private GestioneFlussoBD() {
	}

	static {
		/*
		 * if (gestioneFlusso == null) { InitialContext ctx; try { ctx = new
		 * InitialContext(); gestioneFlusso = (IGestioneFlusso) ctx
		 * .lookup("ContradaEAR/GestioneFlusso/local"); } catch (NamingException
		 * e) { // TODO Auto-generated catch block log.error(e.getMessage(), e);
		 * throw new RuntimeException (e); }
		 * 
		 * }
		 */
		gestioneFlusso = ContradaPojoFactory.getGestioneFlussoInstance();
	}

	public static List<RidDTO> elencaRidDaAllineare()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return gestioneFlusso.elencaRidDaAllineare();
	}

	public static FlussoPreautInviatoDTO preparaFlussoPreautRid(
			List<RidDTO> rids) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return gestioneFlusso.preparaFlussoPreautorizzazioniRid(rids);
	}

	public static List<DisposizionePreautRicezioneDTO> riceviFlussoPreautorizzazioniRid(
			String nomeFile) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return gestioneFlusso.riceviFlussoPreautorizzazioniRid(nomeFile);
	}

	
	
	public static FlussoIncassoRidDTO preparaFlussoIncassiRid(int anno,
			int mese, int tipoIncasso, java.sql.Date dtValuta,boolean xml)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return gestioneFlusso.preparaFlussoIncassiRid(anno, mese, tipoIncasso,
				dtValuta,xml);
	}

	public static List<RicezioneFlussoIncassoRidDTO> riceviFlussoIncassiRid(
			String nomeFile) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return gestioneFlusso.riceviFlussoIncassiRid(nomeFile);
	}

	public static void eliminaFlussoIncassiRid(int anno, int mese, int incasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		gestioneFlusso.eliminaFlussoIncassiRid(anno, mese, incasso);
	}

	public static FlussoIncassoRidDTO generaFlussoIncassiRid(int anno,
			int mese, int tipoIncasso,boolean xml) throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		return gestioneFlusso.generaFlussoIncassiRid(anno, mese, tipoIncasso,xml);
	}
	
	

	public static FlussoPreautInviatoDTO generaFlussoPreautInviati(
			List<RidDTO> rids) throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		return gestioneFlusso.generaFlussoPreautInviati(rids);
	}

	public static FlussoPreautInviatoDTO generaFlussoPreautInviati(
			java.util.Date dtInvio) throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		return gestioneFlusso.generaFlussoPreautInviati(dtInvio);
	}

	public static List<FlussoPreautInviatoDTO> getFlussoPreautorizzati(int anno)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		return gestioneFlusso.getFlussoPreautorizzati(anno);
	}

	public static int eliminaFlussoPreautorizzazioni(java.util.Date dtInvio)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		return gestioneFlusso.eliminaFlussoPreautorizzazioni(dtInvio);
	}

}
