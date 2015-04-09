package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dto.AnagMaxMinDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.RicercaFasceEtaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaAnagrafica;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaAnagraficaBD {

	private static IRicercaAnagrafica ricercaAnagrafica;
	private static Log log = LogFactory.getLog(RicercaAnagraficaBD.class);

	private RicercaAnagraficaBD() {
	}

	static {
		/*
		 * if (ricercaAnagrafica == null) { InitialContext ctx; try { ctx = new
		 * InitialContext(); ricercaAnagrafica = (IRicercaAnagrafica) ctx
		 * .lookup("ContradaEAR/RicercaAnagrafica/local"); } catch
		 * (NamingException e) { // TODO Auto-generated catch block
		 * log.error(e.getMessage(), e); throw new RuntimeException(e); }
		 * 
		 * }
		 */
		ricercaAnagrafica = ContradaPojoFactory.getRicercaAnagraficaIstance();
	}

	public static AnagraficaDTO ricercaAnagrafica(int cdAnag)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaAnagrafica.ricercaPerCodiceAnagrafica(cdAnag);

	}

	public static List<AnagraficaDTO> ricercaAnagraficaPerFamiglia(
			int cdFamiglia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaAnagrafica.ricercaPerCodiceFamiglia(cdFamiglia);
	}

	public static List<AnagraficaDTO> ricercaAnagraficaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaAnagrafica.ricercaPerCognome(cognome);
	}

	public static List<AnagraficaDTO> ricercaAnagraficaParzialePerCognome(
			String cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaAnagrafica.ricercaAnagraficaParzialePerCognome(cognome);

	}

	public static List<AnagraficaDTO> ricercaAnagraficaParzialePerCognomeNome(
			String cognome, String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaAnagrafica.ricercaAnagraficaParzialePerCognomeNome(
				cognome, nome);

	}

	public static List<AnagraficaDTO> ricercaAnagraficaPerCognomeNome(
			String cognome, String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaAnagrafica.ricercaPerNomeCognome(nome, cognome);
	}

	public static List<TipoStatoAnagraficaDTO> elencaStatiAnagrafica()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaAnagrafica.elencaStatiAnagrafica();
	}

	public static List<AnagraficaDTO> ricercaFasciaEta(
			RicercaFasceEtaDTO criterio) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		return ricercaAnagrafica.ricercaFasciaEta(criterio);
	}

	public static List<AnagraficaDTO> elencaIndirizzoAnagraficaProncipale()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		return ricercaAnagrafica.elencaIndirizzoAnagraficaPrincipale();
	}

	public static List<AnagraficaDTO> ricercaIndirizzoAnagraficaPrincipale(
			List<Integer> codiciFamiglia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		return ricercaAnagrafica
				.ricercaIndirizzoAnagraficaPrincipale(codiciFamiglia);
	}

	public static List<AnagraficaDTO> ricercaPerResidenza(
			ParmResidenzaDTO residenza) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaAnagrafica.ricercaPerResidenza(residenza);
	}

	public static List<AnagraficaDTO> ricercaPagantiAnnoPrecedente(int anno,
			int tipoTessera) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaAnagrafica
				.ricercaPagantiAnnoPrecedente(anno, tipoTessera);
	}

	public static List<AnagraficaDTO> ricercaVotanti(int idTipoTessera,
			Date dtElezione, int etaMin, int annoDa)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaAnagrafica.ricercaVotanti(idTipoTessera, dtElezione,
				etaMin, annoDa);
	}

	public static List<AnagraficaDTO> elencoFazzoletti(int annoPagamentoDa)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaAnagrafica.elencoFazzoletti(annoPagamentoDa);
	}
	
	public static List<AnagraficaDTO> elencoRitiroTessere()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaAnagrafica.elencoRitiroTessere();
	}

	public static List<AnagraficaDTO> ricercaAnagraficheConPrincipale(
			List<Integer> stato) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaAnagrafica.ricercaAnagraficheConPrincipale(stato);
	}

	public static List<AnagraficaDTO> ricercaAnagrafichePerGestore(int idGestore)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaAnagrafica.ricercaAnagrafichePerGestore(idGestore);
	}
	
	public static AnagMaxMinDTO getAnagraficheMaxMin()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaAnagrafica.getAnagraficheMaxMin();
	}

}
