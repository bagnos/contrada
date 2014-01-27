package it.contrada.bean;

import it.contrada.bean.utils.Utente;
import it.contrada.common.util.LogUtil;
import it.contrada.dao.interfaces.IAnagrafeDAO;
import it.contrada.dao.interfaces.IFamigliaDAO;
import it.contrada.dao.interfaces.IRidDAO;
import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.FamigliaDTO;
import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.RidDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.enumcontrada.TipoStatoAnagrafica;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneAnagrafica;
import it.contrada.interfaces.IGestioneFamiglia;
import it.contrada.pojo.Operazione;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Session Bean implementation class GestioneAnagrafica
 */

public class GestioneAnagrafica implements IGestioneAnagrafica {

	private static Log log = LogFactory.getLog(GestioneAnagrafica.class);

	/**
	 * @uml.property name="famigliaDao"
	 * @uml.associationEnd
	 */
	private IFamigliaDAO famigliaDao;

	/**
	 * @uml.property name="anagraficaDao"
	 * @uml.associationEnd
	 */
	private IAnagrafeDAO anagraficaDao;

	/**
	 * @uml.property name="tesseraDao"
	 * @uml.associationEnd
	 */
	private ITesseraDAO tesseraDao;

	/**
	 * @uml.property name="ridDao"
	 * @uml.associationEnd
	 */
	private IRidDAO ridDao;

	/**
	 * @uml.property name="operazioneBO"
	 * @uml.associationEnd
	 */
	private Operazione operazioneBO;

	/**
	 * @uml.property name="gestioneFamiglia"
	 * @uml.associationEnd
	 */
	private IGestioneFamiglia gestioneFamiglia;

	/**
	 * @return
	 * @uml.property name="famigliaDao"
	 */
	public IFamigliaDAO getFamigliaDao() {
		return famigliaDao;
	}

	/**
	 * @param famigliaDao
	 * @uml.property name="famigliaDao"
	 */
	public void setFamigliaDao(IFamigliaDAO famigliaDao) {
		this.famigliaDao = famigliaDao;
	}

	/**
	 * @return
	 * @uml.property name="anagraficaDao"
	 */
	public IAnagrafeDAO getAnagraficaDao() {
		return anagraficaDao;
	}

	/**
	 * @param anagraficaDao
	 * @uml.property name="anagraficaDao"
	 */
	public void setAnagraficaDao(IAnagrafeDAO anagraficaDao) {
		this.anagraficaDao = anagraficaDao;
	}

	/**
	 * @return
	 * @uml.property name="tesseraDao"
	 */
	public ITesseraDAO getTesseraDao() {
		return tesseraDao;
	}

	/**
	 * @param tesseraDao
	 * @uml.property name="tesseraDao"
	 */
	public void setTesseraDao(ITesseraDAO tesseraDao) {
		this.tesseraDao = tesseraDao;
	}

	/**
	 * @return
	 * @uml.property name="ridDao"
	 */
	public IRidDAO getRidDao() {
		return ridDao;
	}

	/**
	 * @param ridDao
	 * @uml.property name="ridDao"
	 */
	public void setRidDao(IRidDAO ridDao) {
		this.ridDao = ridDao;
	}

	/**
	 * @return
	 * @uml.property name="operazioneBO"
	 */
	public Operazione getOperazioneBO() {
		return operazioneBO;
	}

	/**
	 * @param operazioneBO
	 * @uml.property name="operazioneBO"
	 */
	public void setOperazioneBO(Operazione operazioneBO) {
		this.operazioneBO = operazioneBO;
	}

	/**
	 * @return
	 * @uml.property name="gestioneFamiglia"
	 */
	public IGestioneFamiglia getGestioneFamiglia() {
		return gestioneFamiglia;
	}

	/**
	 * @param gestioneFamiglia
	 * @uml.property name="gestioneFamiglia"
	 */
	public void setGestioneFamiglia(IGestioneFamiglia gestioneFamiglia) {
		this.gestioneFamiglia = gestioneFamiglia;
	}

	/**
	 * Default constructor.
	 */
	public GestioneAnagrafica() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @see IGestioneAnagrafica#aggiornaAnagrafica(AnagraficaDTO)
	 */
	public AnagraficaDTO aggiornaAnagrafica(AnagraficaDTO anagrafica,
			boolean nuovoRid) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		int rows = 0;
		try {

			OperazioneDTO operazioneDTO = operazioneBO.inserisciOperazione(
					Utente.getUser(), String.format(
							Constanti.OPERAZIONE_MODIFCA_ANAGRAFICA,
							anagrafica.getCognome(), anagrafica.getNome()));
			anagrafica.setOperazione(operazioneDTO);

			if (anagrafica.isNuovaFamiglia()) {

				anagrafica.setIdFamiglia(famigliaDao.inserisciFamiglia(null)
						.getIdFamiglia());
				LogUtil.logTraceMessage(log,
						"Famiglia Inserita:" + anagrafica.getIdFamiglia());
			}
			if (anagrafica.isNuovaFamiglia() || anagrafica.isCapoFamiglia()) {
				// aggiorno il capo famiglia
				FamigliaDTO famiglia = new FamigliaDTO();
				famiglia.setIdCapoFamiglia(anagrafica.getIdAnagrafica());
				famiglia.setIdFamiglia(anagrafica.getIdFamiglia());
				gestioneFamiglia.aggiornaFamiglia(famiglia);

				LogUtil.logTraceMessage(log, "capo famiglia aggiornato con "
						+ anagrafica.getIdAnagrafica());
			}

			// se lo stato è cessato, si aggiorna la famiglia a null
			if (anagrafica.getIdStatoAnagrafica() == TipoStatoAnagrafica.Cessata
					.getStatoAnagrafica()) {
				anagrafica.setIdFamiglia(null);

			}

			// aggiorno i dati anagrafici
			rows = anagraficaDao.aggiornaAnagrafica(anagrafica);

			if (rows == 0) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("1"));
			} else {

				LogUtil.logTraceMessage(log, "aggiornata l'anagrafica:"
						+ anagrafica.getIdAnagrafica());

				// verifica se l'anagrafica è stata disattivata
				/*
				 * if (anagrafica.getIdStatoAnagrafica() !=
				 * TipoStatoAnagrafica.Attiva .getStatoAnagrafica()) {
				 * disattivaTesseraAnagrafica(anagrafica); return anagrafica; }
				 */

				// verifica se è richiesto un nuovo rid
				if (nuovoRid) {

					inserisciRid(anagrafica);
					LogUtil.logTraceMessage(log, "Inserito nuovo rid:"
							+ anagrafica.getRid().getNrRid());
				}

				// aggiorno le tessere
				aggiornamentoTessere(anagrafica);
				LogUtil.logTraceMessage(
						log,
						"inserite, aggiornate ed eliminate le tessere si in Tessere che in Tessera_Storico");

				// elimino e reinserisco i recapiti
				// aggiornaRecapiti(anagrafica);
				LogUtil.logTraceMessage(log, "inserite ed eliminati i recapiti");

				// aggiorno indirizzo a tutta la famiglia, solo se l'anagrafica
				// non è stata cessata
				if (anagrafica.getIdStatoAnagrafica() != TipoStatoAnagrafica.Cessata
						.getStatoAnagrafica()) {
					anagraficaDao.aggiornaIndirizzoAnagrafica(
							anagrafica.getIdStrada(), anagrafica.getNrCivico(),
							anagrafica.getIdFamiglia());

					LogUtil.logTraceMessage(log,
							"aggiornato indirzzo a tutta la famiglia");
				}

				return anagrafica;
			}

		} catch (ContradaExceptionBloccante ex) {

			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante { // TODO
		// Auto-generated
		// method
		// stub
		try {

			verificaControlliFormaliInsAnagrafica(anagraficaDTO);

			OperazioneDTO operazioneDTO = operazioneBO
					.inserisciOperazione(Utente.getUser(),
							String.format(
									Constanti.OPERAZIONE_INSERT_ANAGRAFICA,
									anagraficaDTO.getCognome(),
									anagraficaDTO.getNome()));

			anagraficaDTO.setOperazione(operazioneDTO);

			int rows = anagraficaDao.existAnagrafica(anagraficaDTO.getNome(),
					anagraficaDTO.getCognome(), anagraficaDTO.getDtNascita());
			boolean exist = rows > 0;

			if (exist) {
				throw new ContradaExceptionBloccante("Anagrafica già presente");
			}

			if (anagraficaDTO.isNuovaFamiglia()) {

				anagraficaDTO.setIdFamiglia(famigliaDao.inserisciFamiglia(null)
						.getIdFamiglia());

				LogUtil.logTraceMessage(log, String.format(
						"Inserita nuova famiglia %S", anagraficaDTO));

			}

			anagraficaDTO = anagraficaDao.inserisciAnagrafica(anagraficaDTO);

			LogUtil.logTraceMessage(log,
					String.format("Anagrafica inserita, %S ", anagraficaDTO));

			/*
			 * if (anagraficaDTO.isNuovaFamiglia() ||
			 * anagraficaDTO.isCapoFamiglia()) { // aggiorno il capo famiglia
			 * FamigliaDTO famiglia = new FamigliaDTO();
			 * famiglia.setIdCapoFamiglia(anagraficaDTO.getIdAnagrafica());
			 * famiglia.setIdFamiglia(anagraficaDTO.getIdFamiglia());
			 * gestioneFamiglia.aggiornaFamiglia(famiglia);
			 * 
			 * LogUtil.logTraceMessage(log, String.format(
			 * "capo famiglia aggiornato con %S ", anagraficaDTO)); }
			 */

			/*
			 * if (anagraficaDTO.getRecapiti() != null &&
			 * !anagraficaDTO.getRecapiti().isEmpty()) { for (RecapitoDTO rec :
			 * anagraficaDTO.getRecapiti()) {
			 * rec.setIdAnagrafica(anagraficaDTO.getIdAnagrafica()); }
			 * recapitoDao.inserRecapiti(anagraficaDTO.getRecapiti()); }
			 * 
			 * 
			 * LogUtil.logTraceMessage(log, String.format(
			 * "recapiti inseriti per idAnagrafica %S ", anagraficaDTO));
			 */

			for (TesseraDTO tes : anagraficaDTO.getTessere()) {
				tes.setIdAnag(anagraficaDTO.getIdAnagrafica());

			}

			anagraficaDTO.setTessere(tesseraDao.insertTessere(anagraficaDTO
					.getTessere()));

			LogUtil.logTraceMessage(log, String.format(
					"tessere inserite per idAnagrafica %S ", anagraficaDTO));

			for (TesseraDTO tes : anagraficaDTO.getTessere()) {
				tesseraDao.insertTesseraStorico(tes.getIdTessera());

			}

			LogUtil.logTraceMessage(log, String.format(
					"tessere inserite in storico per idAnagrafica %S ",
					anagraficaDTO));

			return anagraficaDTO;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}
		// TODO Auto-generated catch block
		catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);

		}

	}

	private void verificaControlliFormaliInsAnagrafica(
			AnagraficaDTO anagraficaDTO) throws ContradaExceptionNonBloccante {
		if (anagraficaDTO.getIdStrada() == null
				|| anagraficaDTO.getIdStrada() == 0) {
			throw new ContradaExceptionNonBloccante("Strada non assegnata");
		}
		if (anagraficaDTO.isNuovaFamiglia() == false
				&& (anagraficaDTO.getIdFamiglia() == null || anagraficaDTO
						.getIdFamiglia() == 0)) {
			throw new ContradaExceptionNonBloccante("Famiglia non assegnata");
		}
		if (anagraficaDTO.getDtNascita() == null) {
			throw new ContradaExceptionNonBloccante(
					"Data di nascita non assegnata");
		}
		if (anagraficaDTO.getNome() == null
				|| anagraficaDTO.getNome().isEmpty()) {
			throw new ContradaExceptionNonBloccante("Nome non assegnato");
		}
		if (anagraficaDTO.getCognome() == null
				|| anagraficaDTO.getCognome().isEmpty()) {
			throw new ContradaExceptionNonBloccante("Cognome non assegnato");
		}
		if (anagraficaDTO.getTessere() == null
				|| anagraficaDTO.getTessere().isEmpty()) {
			throw new ContradaExceptionNonBloccante("Nessuna tessera assegnata");
		}

	}

	public AnagraficaDTO inserisciAnagraficaConRid(AnagraficaDTO anagraficaDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			inserisciRid(anagraficaDTO);

			// si effettua l'inserimento dell'anagrafica
			inserisciAnagrafica(anagraficaDTO);

			return anagraficaDTO;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	private void inserisciRid(AnagraficaDTO anagraficaDTO) throws Exception {
		RidDTO ridDTO = null;
		// si crea un nuovo rid

		ridDTO = anagraficaDTO.getRid();
		ridDao.insertRid(ridDTO);

		// si assegna il numero rid alle tessere con rid

		for (TesseraDTO tes : anagraficaDTO.getTessere()) {
			if (it.contrada.enumcontrada.TipoIncasso.RID.getIncasso() == tes
					.getIdTipoIncasso()) {
				tes.setIdRid(ridDTO.getNrRid());
			}
		}

	}

	/*
	 * private void disattivaTesseraAnagrafica(AnagraficaDTO anagrafica) throws
	 * Exception { List<TesseraDTO> tesserePresenti = tesseraDao
	 * .getTesserePerAnagrafica(anagrafica.getIdAnagrafica()); for (TesseraDTO
	 * tes : tesserePresenti) { tesseraDao.disattivaTessera(tes.getIdTessera());
	 * tes.setFgAttiva(false);
	 * tesseraDao.aggiornaTesseraStorico(tes.getIdTessera()); } }
	 */

	/*
	 * private void aggiornaRecapiti(AnagraficaDTO anagrafica) throws Exception
	 * { recapitoDao.eliminaRecapiti(anagrafica.getIdAnagrafica()); if
	 * (anagrafica.getRecapiti() != null && !anagrafica.getRecapiti().isEmpty())
	 * { recapitoDao.inserRecapiti(anagrafica.getRecapiti()); } }
	 */

	private void aggiornamentoTessere(AnagraficaDTO anagrafica)
			throws Exception {
		// aggiornamento tessere e tessere storico
		List<TesseraDTO> tesserePresenti = tesseraDao
				.getTesserePerAnagrafica(anagrafica.getIdAnagrafica());

		if (anagrafica.getIdStatoAnagrafica() == TipoStatoAnagrafica.Cessata
				.getStatoAnagrafica()
				|| anagrafica.getIdStatoAnagrafica() == TipoStatoAnagrafica.Sospesa
						.getStatoAnagrafica()) {

			//disattivazione tessere per sospesi o cessati
			for (TesseraDTO tessera : tesserePresenti) {

				// se l'anagrafica è cessata si disattivano anche le tessere correnti
				if (anagrafica.getIdStatoAnagrafica() == TipoStatoAnagrafica.Cessata
						.getStatoAnagrafica()) {
					tesseraDao.disattivaTessera(tessera.getIdTessera());
				}

				tesseraDao.disattivaTesseraStorico(tessera.getIdTessera());
			}

		} else {

			boolean trovata = false;
			List<TesseraDTO> tesseraDaAggiungere = new ArrayList<TesseraDTO>();

			for (TesseraDTO tessera : anagrafica.getTessere()) {
				trovata = false;

				for (TesseraDTO tesPres : tesserePresenti) {
					if (tesPres.getIdTessera() == tessera.getIdTessera()) {
						trovata = true;
						tesserePresenti.remove(tesPres);
						break;
					}
				}
				if (trovata) {
					// update tessera e tessera storico
					tesseraDao.aggiornaTessera(tessera);
					tesseraDao.aggiornaTesseraStorico(tessera.getIdTessera());
				} else {
					// non è tra quelle presenti, si inserisce
					tesseraDaAggiungere.add(tessera);
				}
			}

			if (!tesseraDaAggiungere.isEmpty()) {
				tesseraDao.insertTessere(tesseraDaAggiungere);
				for (TesseraDTO tes : tesseraDaAggiungere) {
					tesseraDao.insertTesseraStorico(tes.getIdTessera());
				}
			}

			for (TesseraDTO tes : tesserePresenti) {
				// verifico se la tessera è stata già incassata
				// if (tesseraDao.getTesseraRendicontata(tes.getIdTessera())
				// == 0) {
				// tesseraDao.eliminaTessera(tes.getIdTessera());
				// tesseraDao.eliminaTesseraStorico(tes.getIdTessera());
				// } else {
				// tessera da disattivare, cancellazione logica
				tesseraDao.disattivaTessera(tes.getIdTessera());				
				tesseraDao.disattivaTesseraStorico(tes.getIdTessera());
				// }
			}
		}

	}

	public int aggiornaGestore(List<AnagraficaDTO> anagrafiche)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			return anagraficaDao.aggiornaGestore(anagrafiche);
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public int aggiornaStatoAnagrafica(List<Integer> idAnagrafiche, int idStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			// si cessa l'anagrafica
			int i = anagraficaDao.aggiornaStatoAnagrafica(idAnagrafiche,
					idStato);

			// si disattiva tutte le tessere per anagrafiche cessate
			if (idStato == TipoStatoAnagrafica.Cessata.getStatoAnagrafica()) {
				for (int anag : idAnagrafiche) {
					tesseraDao.disattivaTesseraByAnagrafica(anag);
					tesseraDao.aggiornaTesseraStoricoByAnagrafica(anag);
				}
			}
			return i;

		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public int riattivaSospesi() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		return 0;
	}

}
