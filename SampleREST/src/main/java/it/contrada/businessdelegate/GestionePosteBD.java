package it.contrada.businessdelegate;

import java.util.List;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneFamiglia;
import it.contrada.interfaces.IGestionePoste;
import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.poste.IncassoPostaDTO;
import it.contrada.poste.RendicontazioneIncassoPostaDTO;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GestionePosteBD {
	private static IGestionePoste gestionePoste;
	private static Log log = LogFactory.getLog(GestionePosteBD.class);

	private GestionePosteBD() {
	}

	static {
		/*
		if (gestionePoste == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				gestionePoste = (IGestionePoste) ctx
						.lookup("ContradaEAR/GestionePoste/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}

		}*/
		gestionePoste=ContradaPojoFactory.getGestionePosteInstance();
	}

	public static FlussoIncassoPostaDTO produciFlussiIncassoPoste(int anno,
			java.sql.Date dtScadenza, List<Integer> tipoTessere, String nomeFile)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		FlussoIncassoPostaDTO incassi = gestionePoste
				.produciFlussiIncassoPoste(anno, dtScadenza, tipoTessere,
						nomeFile);
		return incassi;
	}

	public static FlussoIncassoPostaDTO confermaInvioFlussiIncassoPoste(
			FlussoIncassoPostaDTO flusso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		FlussoIncassoPostaDTO flussoOut = gestionePoste
				.confermaInvioFlussiIncassoPoste(flusso);
		return flussoOut;
	}

	public static void eliminaFlusso(long idFlusso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		gestionePoste.eliminaFlusso(idFlusso);
	}

	public static List<RendicontazioneIncassoPostaDTO> rendicontaFlussoPoste(
			String pathFile) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return gestionePoste.rendicontaFlussoPoste(pathFile);
	}

	public static List<FlussoIncassoPostaDTO> ricercaFlussoPostalePerAnno(
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return gestionePoste.ricercaFlussoPostalePerAnno(anno);
	}
}
