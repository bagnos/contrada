package it.contrada.bean;

import it.contrada.dao.interfaces.IFlussoEsitiDAO;
import it.contrada.dao.interfaces.IFlussoIncassoPosteDAO;
import it.contrada.dao.interfaces.IRateizzazioneDAO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.enumcontrada.TipoStatoRata;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneFlusso;
import it.contrada.interfaces.IGestionePoste;
import it.contrada.pojo.Operazione;
import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.poste.IncassoPostaDTO;
import it.contrada.poste.RendicontazioneIncassoPostaDTO;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestionePoste
 */

public class GestionePoste implements IGestionePoste {

	/**
	 * @uml.property  name="flussoPostaDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	IFlussoIncassoPosteDAO flussoPostaDao;

	/**
	 * @uml.property  name="rateizzazioneDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	IRateizzazioneDAO rateizzazioneDao;

	/**
	 * @uml.property  name="flussoEsitoDAO"
	 * @uml.associationEnd  
	 */
	@Autowired
	IFlussoEsitiDAO flussoEsitoDAO;

	/**
	 * @uml.property  name="flussoEsito"
	 * @uml.associationEnd  
	 */
	@Autowired
	IGestioneFlusso flussoEsito;

	

	/**
	 * @uml.property  name="operazioneBO"
	 * @uml.associationEnd  
	 */
	@Autowired
	private Operazione operazioneBO;

	private static Log log = LogFactory.getLog(GestioneGestore.class);

	/**
	 * @return
	 * @uml.property  name="flussoEsito"
	 */
	public IGestioneFlusso getFlussoEsito() {
		return flussoEsito;
	}

	/**
	 * @param flussoEsito
	 * @uml.property  name="flussoEsito"
	 */
	public void setFlussoEsito(IGestioneFlusso flussoEsito) {
		this.flussoEsito = flussoEsito;
	}
	
	/**
	 * @param flussoPostaDao
	 * @uml.property  name="flussoPostaDao"
	 */
	public void setFlussoPostaDao(IFlussoIncassoPosteDAO flussoPostaDao) {
		this.flussoPostaDao = flussoPostaDao;
	}

	/**
	 * @param rateizzazioneDao
	 * @uml.property  name="rateizzazioneDao"
	 */
	public void setRateizzazioneDao(IRateizzazioneDAO rateizzazioneDao) {
		this.rateizzazioneDao = rateizzazioneDao;
	}

	/**
	 * @param flussoEsitoDAO
	 * @uml.property  name="flussoEsitoDAO"
	 */
	public void setFlussoEsitoDAO(IFlussoEsitiDAO flussoEsitoDAO) {
		this.flussoEsitoDAO = flussoEsitoDAO;
	}

	public void setFlussoEsito(GestioneFlusso flussoEsito) {
		this.flussoEsito = flussoEsito;
	}

	/**
	 * @param operazioneBO
	 * @uml.property  name="operazioneBO"
	 */
	public void setOperazioneBO(Operazione operazioneBO) {
		this.operazioneBO = operazioneBO;
	}

	private SimpleDateFormat formatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private static Properties prop;

	private static Properties getPropFile() throws IOException {
		if (prop == null) {
			prop = new Properties();
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("poste.properties");
			prop.load(is);
			is.close();
		}
		return prop;
	}

	/**
	 * Default constructor.
	 */
	public GestionePoste() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @throws ContradaExceptionNonBloccante
	 * @see IGestionePoste#produciFlussiIncassoPoste(int, java.sql.Date)
	 */
	@SuppressWarnings("unchecked")
	public FlussoIncassoPostaDTO produciFlussiIncassoPoste(int anno,
			java.sql.Date dtScadenza, List<Integer> tipoTessere, String nomeFile)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub

		int quotaTot = 0;
		String importo = null;
		int quota;
		Integer i = 4;
		int cdFamiglia;
		Integer a;
	
		
		String variA = null;
		FlussoIncassoPostaDTO flussoOut = new FlussoIncassoPostaDTO();

		try {

			// controlli formali
			verificaProduciFlussiIncassoPoste(anno, dtScadenza);

			List<IncassoPostaDTO> flussoEsteso = flussoPostaDao
					.getFlussiIncassoPosta(anno, dtScadenza, tipoTessere);
			List<IncassoPostaDTO> flussoRaggruppato = new ArrayList<IncassoPostaDTO>();
			flussoOut.setIncassiPerAnag(flussoEsteso);
			flussoOut.setIncassi(flussoRaggruppato);
			flussoOut.setDtScadenza(dtScadenza);
			flussoOut.setAnno(anno);

			if (flussoEsteso.isEmpty()) {
				return flussoOut;
			}

			NumberFormat nf = NumberFormat.getNumberInstance(Locale.ITALY);
			DecimalFormat df = (DecimalFormat) nf;
			String pattern = "###.00";
			df.applyPattern(pattern);

			IncassoPostaDTO flussoRaggruppatoItem = flussoEsteso.get(0);
			flussoRaggruppatoItem.setVAR02A(flussoEsteso.get(0)
					.getRIGADESTINATARIO2());
			flussoRaggruppato.add(flussoRaggruppatoItem);

			cdFamiglia = flussoEsteso.get(0).getIdFamiglia();

			for (IncassoPostaDTO flusso : flussoEsteso) {
				if (flusso.getIdFamiglia() == cdFamiglia) {

					quota = Integer.parseInt(flusso.getXRATAT());
					quotaTot += quota;
					importo = String.format("%s", df.format(quota / 100));
					variA = String.format("VAR%sA", String.format("%2s",
							i.toString()).replaceAll(" ", "0"));
					String strVariA = String.format("%s - %s",
							flusso.getNOME(), importo);
					Class[] args1 = new Class[1];
					args1[0] = String.class;

					flussoRaggruppatoItem.getClass().getDeclaredMethod(
							"set" + variA, args1).invoke(flussoRaggruppatoItem,
							new Object[] { strVariA });

					i++;
				} else {
					// metto il totale;
					String impTot = String.format("%s", df
							.format(quotaTot / 100));
					flussoRaggruppatoItem.setXRATAT(impTot);
					flussoRaggruppatoItem.setVAR03A(impTot);

					// inizializzo la riga;
					i = 5;
					quotaTot = 0;
					quota = Integer.parseInt(flusso.getXRATAT());
					quotaTot += quota;
					cdFamiglia = flusso.getIdFamiglia();
					importo = String.format("%s", df.format(quota / 100));

					flussoRaggruppatoItem = flusso;
					flussoRaggruppatoItem.setXRATAT(String.format("%s", df
							.format(quotaTot / 100)));
					flussoRaggruppatoItem.setVAR03A(String.format("%s", df
							.format(quotaTot / 100)));
					flussoRaggruppatoItem.setVAR04A(String.format("%s - %s",
							flusso.getNOME(), importo));
					flussoRaggruppatoItem.setVAR02A(flusso
							.getRIGADESTINATARIO2());
					flussoRaggruppato.add(flussoRaggruppatoItem);
				}

			}
			flussoOut.setTxNomeFile(nomeFile);
			return confermaInvioFlussiIncassoPoste(flussoOut);
		} catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public FlussoIncassoPostaDTO confermaInvioFlussiIncassoPoste(
			FlussoIncassoPostaDTO flusso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub

		try {
			// controlli formali
			verificaConfermaInvioFlussiIncassoPoste(flusso);

			// inserimento riferimento operazione
			OperazioneDTO operazioneDTO = operazioneBO.inserisciOperazione(
					FacesContext.getCurrentInstance().getExternalContext()
							.getUserPrincipal().getName(),
					Constanti.OPERAZIONE_INVIO_FLUSSO_POSTE);
			flusso.setOperazione(operazioneDTO);

			// si inserisce il flusso
			flussoPostaDao.insertFlussoIncassoPosta(flusso);
			for (IncassoPostaDTO incasso : flusso.getIncassi()) {
				incasso.setIdFlusso(flusso.getIdFlusso());

			}

			// inseriamo l'incassi
			flussoPostaDao.insertIncassoPosta(flusso.getIncassi());

			// inseriamo le rateizzazioni
			RateizzazioneDTO rata = new RateizzazioneDTO();
			for (IncassoPostaDTO incasso : flusso.getIncassiPerAnag()) {
				rata.setIdAnagrafica(incasso.getIdAnagrafica());
				rata.setIdFlussoPoste(incasso.getVCAMPOT());
				rata.setIdTessera(incasso.getIdTessera());
				rata.setIdRata(Constanti.ID_RATA_ESTEMPORANEA);
				rata.setTipoStatoRata(Constanti.RATA_INVIATA);
				rata.setImRata(incasso.getQuota());
				rata.setNrAnno(flusso.getAnno());

				rateizzazioneDao.insertRateizzazione(rata);

			}

			return flusso;
		} catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	private void verificaConfermaInvioFlussiIncassoPoste(
			FlussoIncassoPostaDTO flusso) throws ContradaExceptionNonBloccante {
		if (flusso.getDtScadenza() == null) {
			throw new ContradaExceptionNonBloccante(DecodificaErrore
					.getError("27"));
		}
		if (flusso.getTxNomeFile() == null || flusso.getTxNomeFile().isEmpty()) {
			throw new ContradaExceptionNonBloccante(DecodificaErrore
					.getError("27"));
		}
	}

	private void verificaProduciFlussiIncassoPoste(int anno,
			java.sql.Date dtScadenza) throws ContradaExceptionNonBloccante {
		if (dtScadenza == null) {
			throw new ContradaExceptionNonBloccante(DecodificaErrore
					.getError("27"));
		}

		if (anno != Calendar.getInstance().get(Calendar.YEAR)) {
			throw new ContradaExceptionNonBloccante(DecodificaErrore
					.getError("29"));
		}

	}

	public void eliminaFlusso(long idFlusso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			// si elimina le rate
			rateizzazioneDao.eliminaRataPerFlussoPosta(idFlusso);

			// si elimina l'incasso
			flussoPostaDao.eliminaIncassiPosta(idFlusso);

			// si elimina il flusso
			flussoPostaDao.eliminaFlussoIncassoPosta(idFlusso);
		} catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public List<RendicontazioneIncassoPostaDTO> rendicontaFlussoPoste(
			String pathFile) throws ContradaExceptionBloccante {
		String record = null;
		List<RendicontazioneIncassoPostaDTO> bollettini = new ArrayList<RendicontazioneIncassoPostaDTO>();
		File file = new File(pathFile);
		RendicontazioneIncassoPostaDTO recDTO = null;
		int rows = 0;

		try {
			// inserisco il file nella tabella flussi esito
			FlussoEsitoDTO flussoEsitoDTO = flussoEsito.inserisciFlussoEsito(
					file, TipoFlusso.BOLLETTINO);

			// si effettua il parsing del file relativo alle rendicontazioni,
			FileReader reader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);
			String delimitatore = getPropFile().getProperty("delimitatore");

			// ci posizioniamo alla seconda riga, nella prima c'è solo il cc
			// postale
			record = bReader.readLine();
			while ((record = bReader.readLine()) != null) {

				recDTO = parseRecordRendicontazione(record, delimitatore);
				if (recDTO != null) {
					bollettini.add(recDTO);
				}

			}

			// si effettuano gli aggiornamenti
			long imTotBollettino = 0;
			int rowsAffetcted = 0;
			for (RendicontazioneIncassoPostaDTO bollettino : bollettini) {

				rows = 0;
				// se premarcato si inserisce l'incasso, per quelli bianchi
				// occorre la rendicontazione manuale
				if (!bollettino.getTyDocumento().equalsIgnoreCase(
						Constanti.BOLL_BIANCO)) {

					rows = rateizzazioneDao.aggiornaStatoRataPerPosta(
							TipoStatoRata.Pagata.getStatoRata(), bollettino
									.getIdIncassoPoste(), bollettino
									.getDtOperazione());

				}

				bollettino.setIncassatoAutomatico(rows > 0);
				bollettino.setIdFlussoEsito(flussoEsitoDTO.getIdFlussoEsito());
				imTotBollettino += bollettino.getImBollettino();

				// si aggiorna/insersice nella tabelle delle rendiconazioni
				// ricevute il
				// bollettino ricevuto
				rowsAffetcted = flussoPostaDao
						.aggiornaRendicontazioneFlusso(bollettino);
				if (rowsAffetcted == 0) {
					// bollettino ai rendicontato
					flussoPostaDao.insertRendicontazioneFlussi(bollettino);
				}

			}
			flussoEsitoDTO.setNrDisp(bollettini.size());
			flussoEsitoDTO.setImFlusso(imTotBollettino);
			flussoEsitoDAO.aggiornaFlussoEsito(flussoEsitoDTO);
			return bollettini;

		} catch (FileNotFoundException e) {
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore
					.getError("29"), e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore
					.getError("5018"), e);
		}

	}

	private RendicontazioneIncassoPostaDTO parseRecordRendicontazione(
			String record, String delimitatore) throws IOException,
			ParseException {
		RendicontazioneIncassoPostaDTO recDTO = new RendicontazioneIncassoPostaDTO();
		String[] recs = record.split(delimitatore);
		if (recs[1].isEmpty()) {
			// ultima riga
			return null;
		}
		recDTO.setDtValuta(new java.sql.Date(formatddMMyyyy.parse(recs[1])
				.getTime()));
		recDTO.setDtOperazione(new java.sql.Date(formatddMMyyyy.parse(recs[2])
				.getTime()));
		recDTO.setTyDocumento(recs[3]);
		recDTO.setTxSportello(recs[4]);
		recDTO.setIdIncassoPoste(recs[5]);
		recDTO.setImBollettino(Integer.parseInt(recs[7].replace(",", "")));
		recDTO.setTyAccettazione(recs[8]);
		recDTO.setTySostitutivo(recs[10]);
		return recDTO;

	}

	public List<FlussoIncassoPostaDTO> ricercaFlussoPostalePerAnno(int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			List<FlussoIncassoPostaDTO> flusso = flussoPostaDao
					.getFlussoPostalePerAnno(anno);
			return flusso;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			throw new ContradaExceptionBloccante(DecodificaErrore
					.getError("5018"), e);
		}
	}
}
