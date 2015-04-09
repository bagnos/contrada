package it.contrada.bean;

import it.contrada.dao.AnagrafeDAO;
import it.contrada.dao.interfaces.IAnagrafeDAO;
import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dto.AnagMaxMinDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.RicercaFasceEtaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaAnagrafica;
import it.contrada.util.DecodificaErrore;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaAnagrafica
 */

public class RicercaAnagrafica implements IRicercaAnagrafica {

	private static Log log = LogFactory.getLog(AnagrafeDAO.class);

	/**
	 * @uml.property  name="anagDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IAnagrafeDAO anagDao;

	/**
	 * @param anagDao
	 * @uml.property  name="anagDao"
	 */
	public void setAnagDao(IAnagrafeDAO anagDao) {
		this.anagDao = anagDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaAnagrafica() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaAnagrafica#existByCodiceFiscale(String)
	 */
	public boolean existByCodiceFiscale(String cdFiscale)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub

		try {
			boolean esiste = anagDao.existAnagraficaByCdFisc(cdFiscale);
			return esiste;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public AnagraficaDTO ricercaPerCodiceAnagrafica(int cdAnag)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			AnagraficaDTO anagrafica = anagDao
					.getAnagraficaByCodiceAnagrafica(cdAnag);

			// la result map nella left join mette comunque un item null
			normalizzaAnagrafica(anagrafica);

			return anagrafica;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<AnagraficaDTO> ricercaPerCodiceFamiglia(int cdFamiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao
					.getAnagraficaByCodiceFamiglia(cdFamiglia);
			normalizzaAnagrafica(anags);
			return anags;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<AnagraficaDTO> anags = anagDao.getAnagraficaByCognome(cognome);

			normalizzaAnagrafica(anags);

			return anags;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}
	
	public List<AnagraficaDTO> ricercaAnagrafichePerGestore(int idGestore)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<AnagraficaDTO> anags = anagDao.getAnagraficaByGestore(idGestore);

			normalizzaAnagrafica(anags);

			return anags;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaPerNomeCognome(String nome, String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao.getAnagraficaByCognomeNome(
					cognome, nome);

			normalizzaAnagrafica(anags);

			return anags;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<TipoStatoAnagraficaDTO> elencaStatiAnagrafica()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return anagDao.getStatiAnagrafica();

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaFasciaEta(RicercaFasceEtaDTO criterio)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<AnagraficaDTO> anags = anagDao.getAnagraficaByFasce(criterio);
			normalizzaAnagrafica(anags);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<AnagraficaDTO> elencaIndirizzoAnagraficaPrincipale()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao
					.getIndirizzoAnagraficaPrincipale();
			normalizzaAnagrafica(anags);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaIndirizzoAnagraficaPrincipale(
			List<Integer> codiciFamiglia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao
					.getIndirizzoAnagraficaPrincipale(codiciFamiglia);
			normalizzaAnagrafica(anags);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaAnagraficaParzialePerCognome(
			String cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<AnagraficaDTO> anags = anagDao
					.getAnagraficaParzialePerCognome(cognome);
			normalizzaAnagrafica(anags);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	private void normalizzaAnagrafica(AnagraficaDTO anagrafica) {
		// la result map nella left join mette comunque un item null
		if (anagrafica != null) {
			if (anagrafica.getTessere() != null
					&& !anagrafica.getTessere().isEmpty()) {
				if (anagrafica.getTessere().get(0).getIdTessera() == 0) {
					anagrafica.setTessere(null);
				}
			}

			/*
			 * if (anagrafica.getRecapiti() != null &&
			 * !anagrafica.getRecapiti().isEmpty()) { if
			 * (anagrafica.getRecapiti().get(0).getIdRecapito() == 0) {
			 * anagrafica.setRecapiti(null); } }
			 */
		}

	}

	private void normalizzaAnagrafica(List<AnagraficaDTO> anagrafiche) {
		// la result map nella left join mette comunque un item null

		for (AnagraficaDTO anagrafica : anagrafiche) {
			normalizzaAnagrafica(anagrafica);
		}
	}

	public void annulla(ActionEvent e) {
	}

	public void conferma(ActionEvent e) {
	}

	public List<AnagraficaDTO> ricercaPerResidenza(ParmResidenzaDTO residenza)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<AnagraficaDTO> anags = anagDao
					.getAnagraficaPerResidenza(residenza);
			if (anags != null) {
				for (AnagraficaDTO anag : anags) {

					if (anag.getTessere() != null
							&& !anag.getTessere().isEmpty()) {
						for (TesseraDTO tes : anag.getTessere()) {
							anag.setTessera(tes.getDsTipoTessera());
							anag.setIncasso(tes.getDsIncasso());
							anag.setEsattore(tes.getDsEsattore());
							anag.setCarica(tes.getDsTipoCarica());
							anag.setQuota(tes.getQuota());
						}
					}
				}
			}
			return anags;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaPagantiAnnoPrecedente(int anno,
			int tipoTessera) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			return anagDao.getPagantiAnnoPrecedente(anno, tipoTessera);
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaAnagraficaParzialePerCognomeNome(
			String cognome, String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<AnagraficaDTO> anags = anagDao
					.getAnagraficaParzialePerCognomeNome(cognome, nome);
			normalizzaAnagrafica(anags);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public boolean existAnagrafica(String cognome, String nome,
			java.util.Date dtNascita) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			int rows = anagDao.existAnagrafica(nome, cognome, dtNascita);
			return (rows > 0);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	/**
	 * recupera tutti le anagrafiche per quella tipologia di tessera che hanno
	 * pagato l'anno in corso o quello precedente e che hanno un età maggiore di
	 * etaMin alla data dell'elezione.
	 */
	public List<AnagraficaDTO> ricercaVotanti(int idTipoTessera,
			Date dtElezione, int etaMin, int annoDa)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao.getVotanti(idTipoTessera,
					dtElezione, etaMin, annoDa);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> elencoFazzoletti(int annoPagamentoDa)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao.getFazzoletti(annoPagamentoDa);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<AnagraficaDTO> ricercaAnagraficheConPrincipale(List<Integer> statiAnagrafice)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			List<AnagraficaDTO> anags = anagDao.getAnagraficheConPrincipale(statiAnagrafice);
			return anags;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<AnagraficaDTO> elencoRitiroTessere()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return anagDao.getRitiroTessere();
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public AnagMaxMinDTO getAnagraficheMaxMin()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				try {
					return anagDao.getAnagraficheMaxMin();
				}

				catch (Exception ex) {
					log.error(ex);
					throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
				}
	}

	

}
