package it.contrada.bean;

import it.contrada.dao.interfaces.IFlussoEsitiDAO;
import it.contrada.dao.interfaces.IFlussoPreautorizzazioniRidDAO;
import it.contrada.dao.interfaces.IFlussoRidIncassoDAO;
import it.contrada.dao.interfaces.IParametriContradaDAO;
import it.contrada.dao.interfaces.IRateizzazioneDAO;
import it.contrada.dao.interfaces.IRidDAO;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.ParametriContradaDTO;
import it.contrada.dto.RidDTO;
import it.contrada.enumcontrada.TipoCausaleIncasso;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.enumcontrada.TipoStatoRata;
import it.contrada.enumcontrada.TipoStatoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.DisposizioneIncassoRidRicezioneDTO;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.incassorid.dto.RicezioneFlussoIncassoRidDTO;
import it.contrada.interfaces.IGestioneFlusso;
import it.contrada.pojo.FlussoPreautorizzazioneRid;
import it.contrada.pojo.FlussoRid;
import it.contrada.pojo.FlussoRidXml;
import it.contrada.pojo.FlussoUtil;
import it.contrada.pojo.Operazione;
import it.contrada.pojo.RicezioneFlussoIncassiRid;
import it.contrada.pojo.RicezioneFlussoPreautorizzazioneRid;
import it.contrada.preautrid.dto.DisposizionePreautRicezioneDTO;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;
import it.contrada.preautrid.dto.RicezioneFlussoPreautorizzazioneDTO;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class GestioneFlusso
 */

public class GestioneFlusso implements IGestioneFlusso {

	@Autowired
	IRateizzazioneDAO rateizzazioneDAO;

	@Autowired
	IFlussoRidIncassoDAO flussoRidAddebitoDAO;

	@Autowired
	IParametriContradaDAO parametriContradaDAO;

	@Autowired
	IRidDAO ridDAO;

	@Autowired
	IFlussoPreautorizzazioniRidDAO flussoPreautorizzazioniRidDAO;

	@Autowired
	IFlussoEsitiDAO flussoEsitoDAO;

	@Autowired
	RicezioneFlussoPreautorizzazioneRid ricezionePreaut;

	@Autowired
	RicezioneFlussoIncassiRid ricezioneIncassiRid;

	@Autowired
	private Operazione operazioneBO;

	private static Log log = LogFactory.getLog(GestioneFlusso.class);

	public void setOperazioneBO(Operazione operazioneBO) {
		this.operazioneBO = operazioneBO;
	}

	public IRateizzazioneDAO getRateizzazioneDAO() {
		return rateizzazioneDAO;
	}

	public void setRateizzazioneDAO(IRateizzazioneDAO rateizzazioneDAO) {
		this.rateizzazioneDAO = rateizzazioneDAO;
	}

	public IFlussoRidIncassoDAO getFlussoRidAddebitoDAO() {
		return flussoRidAddebitoDAO;
	}

	public void setFlussoRidAddebitoDAO(
			IFlussoRidIncassoDAO flussoRidAddebitoDAO) {
		this.flussoRidAddebitoDAO = flussoRidAddebitoDAO;
	}

	public IParametriContradaDAO getParametriContradaDAO() {
		return parametriContradaDAO;
	}

	public void setParametriContradaDAO(
			IParametriContradaDAO parametriContradaDAO) {
		this.parametriContradaDAO = parametriContradaDAO;
	}

	public IRidDAO getRidDAO() {
		return ridDAO;
	}

	public void setRidDAO(IRidDAO ridDAO) {
		this.ridDAO = ridDAO;
	}

	public IFlussoPreautorizzazioniRidDAO getFlussoPreautorizzazioniRidDAO() {
		return flussoPreautorizzazioniRidDAO;
	}

	public void setFlussoPreautorizzazioniRidDAO(
			IFlussoPreautorizzazioniRidDAO flussoPreautorizzazioniRidDAO) {
		this.flussoPreautorizzazioniRidDAO = flussoPreautorizzazioniRidDAO;
	}

	public IFlussoEsitiDAO getFlussoEsitoDAO() {
		return flussoEsitoDAO;
	}

	public void setFlussoEsitoDAO(IFlussoEsitiDAO flussoEsitoDAO) {
		this.flussoEsitoDAO = flussoEsitoDAO;
	}

	public RicezioneFlussoPreautorizzazioneRid getRicezionePreaut() {
		return ricezionePreaut;
	}

	public void setRicezionePreaut(
			RicezioneFlussoPreautorizzazioneRid ricezionePreaut) {
		this.ricezionePreaut = ricezionePreaut;
	}

	public RicezioneFlussoIncassiRid getRicezioneIncassiRid() {
		return ricezioneIncassiRid;
	}

	public void setRicezioneIncassiRid(
			RicezioneFlussoIncassiRid ricezioneIncassiRid) {
		this.ricezioneIncassiRid = ricezioneIncassiRid;
	}

	public GestioneFlusso() {
		// TODO Auto-generated constructor stub
	}

	public FlussoIncassoRidDTO preparaFlussoIncassiRid(int anno, int mese,
			int tipoIncassoRid, java.sql.Date dtValuta, boolean formatXML)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			// si verifica esistenza del flusso per anno, mese e tipo incasso
			int rows = flussoRidAddebitoDAO.existFlusso(anno, mese,
					tipoIncassoRid);
			if (rows > 0) {
				throw new ContradaExceptionNonBloccante(
						DecodificaErrore.getError("11"));
			}

			// inserisce tutte le tessere in tabella rateizzazione per anno,
			// mese e tipo incasso rid
			int nrRowsRate = rateizzazioneDAO.insertRateizzazioniPerFlusso(
					anno, mese, tipoIncassoRid);
			if (nrRowsRate == 0) {
				FlussoIncassoRidDTO flussoRidDTO = new FlussoIncassoRidDTO();
				flussoRidDTO.setImFlusso(0);
				flussoRidDTO.setNrIncassi(0);
				return flussoRidDTO;
			}

			// si produce le informazioni elementari per creare il flusso, lo
			// stato rid è null
			OperazioneDTO operazioneDTO = operazioneBO.inserisciOperazione(
					FacesContext.getCurrentInstance().getExternalContext()
							.getUserPrincipal().getName(), String.format(
							Constanti.OPERAZIONE_INVIO_RID, TipoIncassoRid
									.valueOf(tipoIncassoRid).toString(), anno
									+ "/" + mese));

			int nrRowsFlusso = flussoRidAddebitoDAO.insertFlussoRid(anno, mese,
					dtValuta, tipoIncassoRid,
					FlussoRid.getNomeFileConDirectoryRidIncasso(anno, mese),
					operazioneDTO.getIdOperazione());

			if (nrRowsFlusso == 0) {
				throw new ContradaExceptionNonBloccante(
						DecodificaErrore.getError("9"));
			}

			nrRowsFlusso = flussoRidAddebitoDAO.insertIncassoRid(anno, mese,
					tipoIncassoRid, dtValuta);
			if (nrRowsFlusso == 0) {
				throw new ContradaExceptionNonBloccante(
						DecodificaErrore.getError("9"));
			}

			// si aggiorna l'identificativo dell'incasso presente nel flusso su
			// tutte le rateizzazione inserite precedentemente
			int nrRowsRid = rateizzazioneDAO.aggiornaRidFlussoAddebito(anno,
					mese);
			if (nrRowsRid != nrRowsRate) {
				throw new ContradaExceptionNonBloccante(
						DecodificaErrore.getError("10"));
			}

			// genera il flusso;
			FlussoIncassoRidDTO flussoRidDto = generaFlussoIncassiRid(anno,
					mese, tipoIncassoRid, formatXML);

			/*
			 * // si recupera le informazioni sui parametri della contrada
			 * ParametriContradaDTO params =
			 * parametriContradaDAO.getParametri(); if (params == null ||
			 * params.getCdSia() == null || params.getCdSia().trim().equals(""))
			 * { throw new ContradaExceptionBloccante(DecodificaErrore
			 * .getError("12")); }
			 * 
			 * // si recupera quelli i rid da invuare List<IncassoRidDTO>
			 * incassi = flussoRidAddebitoDAO .getIncassiDaInviare(anno, mese,
			 * tipoIncassoRid); if (incassi.isEmpty()) { return null; }
			 * 
			 * // si crea il file FlussoRid flusso = new FlussoRid();
			 * FlussoIncassoRidDTO flussoRidDto = flusso.creaFlussoRid(mese,
			 * anno, incassi, params);
			 */

			return flussoRidDto;
		}

		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public void eliminaFlussoIncassiRid(int anno, int mese, int incasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			String nomeFile = flussoRidAddebitoDAO.getNomeFileFlusso(anno,
					mese, incasso);
			if (nomeFile == null || nomeFile.trim() == "")
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("13"));

			File file = new File(nomeFile);
			file.deleteOnExit();
			/*
			 * if (!file.exists()) throw new
			 * ContradaExceptionBloccante(DecodificaErrore.getError("14"));
			 */

			int nrRows = 0;
			nrRows = rateizzazioneDAO.eliminaRateizzazioniDaFlusso(anno, mese,
					incasso);

			if (nrRows == 0) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("15"));
			}

			nrRows = flussoRidAddebitoDAO
					.eliminaIncassoRid(anno, mese, incasso);
			if (nrRows == 0) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("16"));
			}

			nrRows = flussoRidAddebitoDAO.eliminaFlussoRid(anno, mese, incasso);
			if (nrRows == 0) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("15"));
			}

		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<DisposizionePreautRicezioneDTO> riceviFlussoPreautorizzazioniRid(
			String nomeFile) throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			int causale;
			int idRid;
			int rowsAgg;
			java.sql.Date dtEsito;
			java.sql.Date dtInvio;
			int abi;
			int cab;
			String conto;
			String paese;
			int cinEuropeo;
			String dsCausale;
			String note;
			String intestazione;
			String cin;
			List<TipoCasualiPreautDTO> causali;
			TipoCasualiPreautDTO causaleDisp = null;
			TipoStatoRid tipoStatoRid = null;
			List<DisposizionePreautRicezioneDTO> disps = new ArrayList<DisposizionePreautRicezioneDTO>();

			SimpleDateFormat formatddMMyy = new SimpleDateFormat("ddMMyy");
			SimpleDateFormat formatddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

			File file = new File(nomeFile);

			if (!file.exists()) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("18"));

			}

			if (isFileEmpty(file)) {
				// lista vuota
				return new ArrayList<DisposizionePreautRicezioneDTO>();
			}

			// inserisco il file nella tabella flussi esito
			FlussoEsitoDTO flussoEsito = inserisciFlussoEsito(file,
					TipoFlusso.PREAUTORIZZAZIONE, new java.util.Date(),
					new java.util.Date());

			ParametriContradaDTO params = parametriContradaDAO.getParametri();
			if (params == null || params.getCdSia() == null
					|| params.getCdSia().trim().equals("")) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("12"));
			}

			causali = flussoPreautorizzazioniRidDAO.elencoCausaliPreaut();
			if (causali.isEmpty()) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("22"));
			}

			List<RicezioneFlussoPreautorizzazioneDTO> flussi = ricezionePreaut
					.decodificaFlessoEsitiPreautorizzazioneRid(nomeFile, params);
			for (RicezioneFlussoPreautorizzazioneDTO flusso : flussi) {

				for (DisposizionePreautRicezioneDTO disp : flusso
						.getDisposizioni()) {
					causale = Integer.parseInt(disp.getRecords12().getCausale()
							.trim());
					dsCausale = "";
					intestazione = null;
					disp.setIdCausale(causale);
					disp.setTipoStatoRid(null);

					for (TipoCasualiPreautDTO causaleDto : causali) {
						if (causaleDto.getCdCausale() == causale) {
							dsCausale = causaleDto.getDsCausale();
							causaleDisp = causaleDto;
							break;
						}
					}

					disp.setDsCausale(dsCausale);

					idRid = Integer.parseInt(disp.getRecords12()
							.getCodiceIndividuale());

					paese = disp.getRecords12().getCodicePaese();
					cinEuropeo = disp.getRecords12().getCheckDigit().trim()
							.isEmpty() ? 0 : Integer.parseInt(disp
							.getRecords12().getCheckDigit());
					abi = disp.getRecords12().getAbi().trim().isEmpty() ? 0
							: Integer.parseInt(disp.getRecords12().getAbi());
					cab = disp.getRecords12().getCab().trim().isEmpty() ? 0
							: Integer.parseInt(disp.getRecords12().getCab());
					conto = disp.getRecords12().getConto().trim();

					cin = disp.getRecords12().getCin().trim().isEmpty() ? "0"
							: disp.getRecords12().getCin();
					if (disp.getRecords40() != null
							&& disp.getRecords40().getIntestetarioConto() != null) {
						intestazione = disp.getRecords40()
								.getIntestetarioConto();
					}
					// si aggiorna il record nel flusso di ricezione
					dtEsito = flusso.getRecordAL().getDtCreazioneDisposizione()
							.trim().isEmpty() ? null : new Date(formatddMMyy
							.parse(flusso.getRecordAL()
									.getDtCreazioneDisposizione()).getTime());

					dtInvio = disp.getRecords12().getDataCreazioneFlusso()
							.trim().isEmpty() ? null : new Date(
							formatddMMyy.parse(
									disp.getRecords12()
											.getDataCreazioneFlusso())
									.getTime());

					note = String
							.format("flusso ricevuto il %s, inviato il %s, causale %s, %s",
									dtEsito != null ? formatddMMyyyy
											.format(dtEsito) : "",
									dtInvio != null ? formatddMMyyyy
											.format(dtInvio) : "", causale,
									dsCausale);

					// aggiorno l'esito dellla disposizione ricevuta
					flussoPreautorizzazioniRidDAO.aggiornaRicezioneFlusso(
							idRid, dtInvio, dtEsito, causale,
							flussoEsito.getIdFlussoEsito());

					// verifica aggiornamento stato rid
					if (causaleDisp.getIdStatoRidSucc() != null) {
						tipoStatoRid = TipoStatoRid
								.lookUpMeseByOrdinal(causaleDisp
										.getIdStatoRidSucc());
						rowsAgg = ridDAO.aggiornaStatoRidPerIdRid(idRid,
								tipoStatoRid, null);
						disp.setStato(tipoStatoRid.toString());

						if (rowsAgg == 0) {
							throw new ContradaExceptionBloccante(
									DecodificaErrore.getError("21"));
						}
						disp.setTipoStatoRid(tipoStatoRid);

					}

					// verifica aggiornamento coordinate
					if (causaleDisp.isAggiornaCoordinate()) {

						rowsAgg = ridDAO.aggiornaCoordinateBancarie(idRid, abi,
								cab, conto, paese, cinEuropeo, cin, note,
								intestazione);

					}
					// aggiorno cmq le note
					else {
						rowsAgg = ridDAO.aggiornaNote(idRid, note);
					}

					if (rowsAgg == 0) {
						throw new ContradaExceptionBloccante(
								DecodificaErrore.getError("21"));
					}

					// recupero alcune informazioni legate al rid
					RidDTO rid = ridDAO.getSchedaRid(idRid);
					disp.setIntestatario(rid.getIntestatarioRid());
					disp.setStato(rid.getDsStatoRid());

					disps.add(disp);

				}
			}

			flussoEsito.setNrDisp(disps.size());
			if (flussoEsitoDAO.aggiornaFlussoEsito(flussoEsito) == 0) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("26"));
			}
			return disps;

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	private boolean isFileEmpty(File file) throws IOException {

		FileReader reader = null;
		BufferedReader bReader = null;
		try {
			reader = new FileReader(file);

			bReader = new BufferedReader(reader);

			if (bReader.readLine().contains("Nessun oggetto trovato")) {
				return true;
			}
			return false;
		} finally {
			if (bReader != null) {
				bReader.close();
			}
			if (reader != null) {
				reader.close();
			}
		}
	}

	public List<RicezioneFlussoIncassoRidDTO> riceviFlussoIncassiRid(
			String nomeFile, java.util.Date dtDa, java.util.Date dtA)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			File file = new File(nomeFile);
			List<TipoCasualiIncassoRidDTO> causali;
			int causale;
			String dsCausale;
			TipoCasualiIncassoRidDTO causaleDisp = null;
			SimpleDateFormat formatddMMyy = new SimpleDateFormat("ddMMyy");
			// long idFlussoAddebito;
			Long idRid;
			java.sql.Date dtEsito;

			String note;
			TipoStatoRid tipoStatoRid;
			int rowRids = 0;
			int rowsAgg;
			java.sql.Date dtValuta;

			if (!file.exists()) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("18"));

			}

			if (isFileEmpty(file)) {
				// lista vuota
				return new ArrayList<RicezioneFlussoIncassoRidDTO>();
			}

			// inserisco il file nella tabella flussi esito
			FlussoEsitoDTO flussoEsito = inserisciFlussoEsito(file,
					TipoFlusso.RID, dtDa, dtA);

			ParametriContradaDTO params = parametriContradaDAO.getParametri();
			if (params == null || params.getCdSia() == null
					|| params.getCdSia().trim().equals("")) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("12"));
			}

			causali = flussoRidAddebitoDAO.elencoCausaliIncassoRid();
			if (causali.isEmpty()) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("22"));
			}

			List<RicezioneFlussoIncassoRidDTO> flussi = ricezioneIncassiRid
					.decodificaFlessoEsitiIncassiRid(nomeFile, params);

			for (RicezioneFlussoIncassoRidDTO flusso : flussi) {

				for (DisposizioneIncassoRidRicezioneDTO disp : flusso
						.getDisposizioni()) {

					causale = Integer.parseInt(disp.getRec10().getCausale());

					dsCausale = "";
					disp.setCdCausale(causale);
					disp.setTipoStatoRid(null);

					// cerco la descrizione della causale
					causaleDisp = null;
					for (TipoCasualiIncassoRidDTO causaleDto : causali) {
						if (causaleDto.getCdCausale() == causale) {
							dsCausale = causaleDto.getDsCausale();
							causaleDisp = causaleDto;
							break;
						}
					}
					disp.setDsCausale(dsCausale);

					idRid = Long.parseLong(disp.getRec10()
							.getCodiceClienteDebitore());

					disp.setIdRid(idRid.intValue());

					// INSERISCO LE ANGRAFICA ATTACCATE ALLA DISPOSIZIONE

					log.info("Elaboro rid " + idRid);
					RidDTO ridDTO = ridDAO.getRid(idRid.intValue());
					if (ridDTO == null) {
						log.warn("idrid non presente con membri:" + idRid);
						ridDTO = ridDAO.getSchedaRid(idRid.intValue());
					}

					disp.setIdStatoRid(ridDTO.getTipoStatoRid());
					disp.setMembri(ridDTO.getMembri());
					disp.setAbi(ridDTO.getAbi().toString());
					disp.setCab(ridDTO.getCab().toString());
					disp.setConto(ridDTO.getNumeroCC());
					/*
					 * dtValuta = new java.sql.Date(formatddMMyy.parse(
					 * disp.getRec10().getDtScadenzaOriginaria()) .getTime());
					 */

					dtValuta = new java.sql.Date(formatddMMyy.parse(
							disp.getRec10().getDtScadenzaOriginaria())
							.getTime());
					disp.setDtValuta(disp.getRec10().getDtScadenzaOriginaria());
					log.info("Elaborato dt valuta");

					// si aggiorna il record nel flusso di ricezione
					dtEsito = flusso.getRecIR().getDtCreazioneFlusso().trim()
							.isEmpty() ? null
							: new Date(formatddMMyy.parse(
									flusso.getRecIR().getDtCreazioneFlusso())
									.getTime());

					log.info("Elaboro dtEsito");

					note = String.format("%s, %s - %s", disp.getRec50()
							.getSegmento2(), causale, dsCausale);
					note = note.trim();
					long idFlussoAddebito = 0;
					if (!disp.getRec70().getCodiceRiferimento().trim()
							.equalsIgnoreCase(params.getCdSia().trim())) {
						idFlussoAddebito = Long.parseLong(disp.getRec70()
								.getCodiceRiferimento().trim());
					}

					if (idFlussoAddebito == 0) {

						// si aggiorna lo stato dell'incasso accedendo per idRid
						// e
						// data valuta
						log.info("Aggiorno esito rid per data valuta");
						rowRids = flussoRidAddebitoDAO.aggiornaEsitoRid(idRid,
								dtValuta, dtEsito, causale,
								flussoEsito.getIdFlussoEsito());
					} else {
						log.info("Aggiorno esito rid per cd riferimento");
						rowRids = flussoRidAddebitoDAO.aggiornaEsitoRid(
								idFlussoAddebito, dtEsito, causale,
								flussoEsito.getIdFlussoEsito());
						if (rowRids == 0) {
							idFlussoAddebito=0;
							log.info("Aggiorno esito rid per data valuta");
							rowRids = flussoRidAddebitoDAO.aggiornaEsitoRid(
									idRid, dtValuta, dtEsito, causale,
									flussoEsito.getIdFlussoEsito());
						}
					}

					if (rowRids == 0) {
						// numero flusso addebito non presente
						disp.setStato("Non Elaborata");
						continue;
					}
					disp.setStato("Elaborata");

					// inserisco l'id rid nella disposizione
					// disp.setIdRid(rateizzazioneDAO
					// .getIdRidFromIdFlusso(idFlussoAddebito));
					// recupero l'idAddebito dall'id rid e dalla data valuta
					if (idFlussoAddebito == 0) {
						log.info("recupero id flusso addebito");
						idFlussoAddebito = flussoRidAddebitoDAO
								.getIdFlussoAddebito(idRid, dtValuta);
					}

					// aggiornamento stato rid
					tipoStatoRid = TipoStatoRid.Attiva;
					if (causaleDisp != null
							&& causaleDisp.getIdStatoRidSucc() != null) {
						tipoStatoRid = TipoStatoRid
								.lookUpMeseByOrdinal(causaleDisp
										.getIdStatoRidSucc());
						rowsAgg = ridDAO.aggiornaStatoRidPerIdFlussoAddebito(
								causaleDisp.getIdStatoRidSucc(),
								idFlussoAddebito, note);
						if (rowsAgg == 0) {
							throw new ContradaExceptionBloccante(
									DecodificaErrore.getError("21"));
						}

					} else if (causaleDisp != null
							&& causaleDisp.getCdCausale() != TipoCausaleIncasso.CAUSALE_50010
									.getTipoCausaleIncasso()) {
						// aggiorno le note
						ridDAO.aggiornaNoteRidPerIdFlussoAddebito(
								idFlussoAddebito, note);
					}
					disp.setTipoStatoRid(tipoStatoRid);
					// aggiorno lo stato della rate in base all'esito
					if (causaleDisp != null) {
						rateizzazioneDAO.aggiornaStatoRata(
								causaleDisp.getIdStatoRataSucc(),
								idFlussoAddebito);
					} else {
						rateizzazioneDAO.aggiornaStatoRata(
								TipoStatoRata.Insoluta.getStatoRata(),
								idFlussoAddebito);
					}

				}
			}
			return flussi;

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<RidDTO> elencaRidDaAllineare()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RidDTO> rids = new ArrayList<RidDTO>();
		try {
			rids = ridDAO.getRidCensiti();

			return rids;
		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public FlussoPreautInviatoDTO preparaFlussoPreautorizzazioniRidSeda()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RidDTO> rids = null;
		List<Integer> stati = new ArrayList<Integer>();
		stati.add(TipoStatoRid.Attiva.getStatoRid());
		stati.add(TipoStatoRid.Censita.getStatoRid());

		try {
			rids = ridDAO.getRidPerStato(stati, 1);
			return generaFlussoPreautRid(rids, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ContradaExceptionNonBloccante(
					"errore perparazione flusso", e);
		}

	}

	public FlussoPreautInviatoDTO preparaFlussoPreautorizzazioniRid(
			List<RidDTO> rids) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RidDTO> ridsOK = new ArrayList<RidDTO>();

		try {
			// List<RidDTO> rids = elencaRidDaAllineare();
			if (flussoPreautorizzazioniRidDAO.getNrPreautorizzazioniToday() > 0) {
				throw new ContradaExceptionNonBloccante(
						"FLUSSO PREAUTORIZZAZIONI GIA' INVIATO NELLA DATA ODIERNA");
			}

			if (rids == null || rids.isEmpty()) {
				return null;
			}

			for (RidDTO rid : rids) {
				if (rid.isInvioPreaut()) {

					ridsOK.add(rid);
				}
			}
			rids = ridsOK;
			if (!rids.isEmpty()) {
				// recupero l'idrid da ogni oggetto rid
				List<Integer> idRids = new ArrayList<Integer>();
				for (RidDTO rid : rids) {

					idRids.add(rid.getNrRid());

				}

				// inserisco il rid nella tabella del flusso di
				// preautorizzazione
				int nrRowsIns = flussoPreautorizzazioniRidDAO
						.insertFlussoPreaut(idRids);

				// aggiorno lo stato dei rid in spedito in banca
				int nrRowsAgg = ridDAO.aggiornaStatoRidPerListRid(idRids,
						TipoStatoRid.Spedita_in_Banca, TipoStatoRid.Censita);

				if (nrRowsAgg != nrRowsIns) {
					throw new ContradaExceptionBloccante(
							DecodificaErrore.getError("17"));
				}

				// si crea il flusso .crm
				return generaFlussoPreautRid(rids, false);

			} else {
				return null;
			}

		} catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public FlussoEsitoDTO inserisciFlussoEsito(File file, TipoFlusso tipoFlusso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			FlussoEsitoDTO flussoEsito = FlussoUtil.getFromNomeFile(
					file.getName(), tipoFlusso);

			// verifica se alcuni esiti sono già stati scaricati
			/*
			 * java.sql.Date maxData =
			 * flussoEsitoDAO.getMaxDataFlusso(tipoFlusso);
			 * 
			 * if (maxData != null && maxData.after(flussoEsito.getDtDa())) {
			 * throw new ContradaExceptionNonBloccante(DecodificaErrore
			 * .getError("30")); }
			 */
			flussoEsito = flussoEsitoDAO.insertFlussoEsito(flussoEsito);

			return flussoEsito;
		} catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public FlussoEsitoDTO inserisciFlussoEsito(File file,
			TipoFlusso tipoFlusso, java.util.Date dtDa, java.util.Date dtA)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {
			FlussoEsitoDTO flussoEsito = FlussoUtil.getFromNomeFile(
					file.getName(), tipoFlusso, dtDa, dtA);

			// verifica se alcuni esiti sono già stati scaricati
			/*
			 * java.sql.Date maxData =
			 * flussoEsitoDAO.getMaxDataFlusso(tipoFlusso);
			 * 
			 * if (maxData != null && maxData.after(flussoEsito.getDtDa())) {
			 * throw new ContradaExceptionNonBloccante(DecodificaErrore
			 * .getError("30")); }
			 */
			flussoEsito = flussoEsitoDAO.insertFlussoEsito(flussoEsito);

			return flussoEsito;
		} catch (ContradaExceptionNonBloccante ex) {
			log.error(ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public FlussoIncassoRidDTO generaFlussoIncassiRid(int anno, int mese,
			int tipoIncassoRid, boolean formatXML)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		// si recupera le informazioni sui parametri della contrada
		try {
			List<TipoIncassoDTO> tipoIncassi = ridDAO.elencaTipoIncassi();
			ParametriContradaDTO params = null;
			for (TipoIncassoDTO inc : tipoIncassi) {
				if (inc.getIdTipoIncasso() == tipoIncassoRid) {
					if (inc.getCdIbanAccredito() != null) {
						// per questo flusso si utilizza un iban di accredito
						// diverso rispetto a quello standard
						params = new ParametriContradaDTO();
						params.setIdSeda(inc.getIdSeda());
						params.setIdSeda(inc.getIdSeda());
						params.setTxIntestazione(inc.getDenominazione());
						params.setCdIban(inc.getCdIbanAccredito());

					}
				}
			}
			if (params == null) {
				params = parametriContradaDAO.getParametri();
			}
			if (params == null || params.getCdSia() == null
					|| params.getCdSia().trim().equals("")) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("12"));
			}

			// si recupera quelli i rid da invuare
			List<IncassoRidDTO> incassi = flussoRidAddebitoDAO
					.getIncassiDaInviare(anno, mese, tipoIncassoRid);
			if (incassi.isEmpty()) {
				return null;
			}

			// si crea il file

			FlussoIncassoRidDTO flussoRidDto = null;
			if (!formatXML) {
				FlussoRid flusso = new FlussoRid();
				flussoRidDto = flusso
						.creaFlussoRid(mese, anno, incassi, params);
				flussoRidDto.setExtension(".crm");
			} else {
				FlussoRidXml flusso = new FlussoRidXml();
				flussoRidDto = flusso.creaFlussoRidXML(mese, anno, incassi,
						params);
				flussoRidDto.setExtension(".xml");
			}
			return flussoRidDto;
		} catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public FlussoPreautInviatoDTO generaFlussoPreautInviati(
			java.util.Date dtInvio) throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		// TODO Auto-generated method stub

		try {
			FlussoPreautInviatoDTO flusso = new FlussoPreautInviatoDTO();

			// si recuperano i rid inviati
			List<RidDTO> rids = flussoPreautorizzazioniRidDAO
					.gerRidPreautorizzatiInviati(dtInvio);

			// si genera il flusso
			flusso = generaFlussoPreautRid(rids, false);

			return flusso;

		} catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	private FlussoPreautInviatoDTO generaFlussoPreautRid(List<RidDTO> rids,
			boolean richiestaSeda) throws Exception {

		FlussoPreautInviatoDTO flusso = new FlussoPreautInviatoDTO();

		if (!rids.isEmpty()) {
			flusso.setRids(rids);
			ParametriContradaDTO params = parametriContradaDAO.getParametri();
			if (params == null) {
				throw new ContradaExceptionBloccante(
						DecodificaErrore.getError("12"));
			}

			FlussoPreautorizzazioneRid preRid = new FlussoPreautorizzazioneRid();
			if (richiestaSeda) {
				flusso = preRid.creaFlussoRidRichiestaDelegheSepa(rids, params);
			} else {
				flusso = preRid.creaFlussoRid(rids, params);
			}
		}
		return flusso;

	}

	public List<FlussoPreautInviatoDTO> getFlussoPreautorizzati(int anno)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoPreautorizzazioniRidDAO.getFlussoPreautorizzati(anno);
		} catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public FlussoPreautInviatoDTO generaFlussoPreautInviati(List<RidDTO> rids)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return generaFlussoPreautRid(rids, false);
		} catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public int eliminaFlussoPreautorizzazioni(java.util.Date dtInvio)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		try {
			return flussoPreautorizzazioniRidDAO
					.eliminaFlussoPreautorizzazioni(dtInvio);
		} catch (Throwable ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

}
