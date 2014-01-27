package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneRateizzazioneBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.DistintaDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.enumcontrada.TipoRata;
import it.contrada.enumcontrada.TipoStatoRata;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.ConverterContrada;
import it.contrada.web.util.Errori;
import it.contrada.web.util.RicercaAnagraficaUtil;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class RendicontazioneManuale extends BaseView {
	private List<SelectItem> anni;
	
	private String idAnagrafica;
	private List<AnagraficaDTO> anagrafiche;
	private boolean visibleAnagrafiche;
	private List<TesseraDTO> tessere;
	private String nome;
	private String cognome;
	private int anno;
	private int nrTessere;
	private int incassato;
	private boolean modifica;
	private Integer tempQuota;
	private Integer incassoPrevisto;
	private Integer daIncassare;
	private Integer tessera;
	private List<SelectItem> tessereItem;
	private List<SelectItem> anagraficheItem;
	private String dsAnagraficaTessera;
	private int totQuotaIncassata;
	private boolean disRendManuale;
	private String note;
	private boolean renderStampaDistinta;
	private DistintaDTO distinta;

	public DistintaDTO getDistinta() {
		return distinta;
	}

	public boolean isRenderStampaDistinta() {
		return renderStampaDistinta;
	}

	public void setRenderStampaDistinta(boolean renderStampaDistinta) {
		this.renderStampaDistinta = renderStampaDistinta;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isDisRendManuale() {
		return disRendManuale;
	}

	public void setDisRendManuale(boolean disRendManuale) {
		this.disRendManuale = disRendManuale;
	}

	public int getTotQuotaIncassata() {
		return totQuotaIncassata;
	}

	public void setTotQuotaIncassata(int totQuotaIncassata) {
		this.totQuotaIncassata = totQuotaIncassata;
	}

	public String getDsAnagraficaTessera() {
		return dsAnagraficaTessera;
	}

	public void setDsAnagraficaTessera(String dsAnagraficaTessera) {
		this.dsAnagraficaTessera = dsAnagraficaTessera;
	}

	public List<SelectItem> getAnagraficheItem() {
		return anagraficheItem;
	}

	public void setAnagraficheItem(List<SelectItem> anagraficheItem) {
		this.anagraficheItem = anagraficheItem;
	}

	public Integer getTessera() {
		return tessera;
	}

	public void setTessera(Integer tessera) {
		this.tessera = tessera;
	}

	public List<SelectItem> getTessereItem() {
		return tessereItem;
	}

	public void setTessereItem(List<SelectItem> tessereItem) {
		this.tessereItem = tessereItem;
	}

	public Integer getDaIncassare() {
		daIncassare = getIncassoPrevisto() - getIncassato();
		if (daIncassare < 0) {
			daIncassare = 0;

		}
		return daIncassare;
	}

	public void setDaIncassare(Integer daIncassare) {
		this.daIncassare = daIncassare;
	}

	public Integer getIncassoPrevisto() {
		incassoPrevisto = 0;

		for (TesseraDTO tessera : getTessere()) {

			incassoPrevisto += tessera.getQuota();

		}
		return incassoPrevisto;

	}

	public void setIncassoPrevisto(Integer incassoPrevisto) {
		this.incassoPrevisto = incassoPrevisto;
	}

	public Integer getTempQuota() {
		return tempQuota;
	}

	public void setTempQuota(Integer tempQuota) {
		this.tempQuota = tempQuota;
	}

	public boolean isModifica() {
		return modifica;
	}

	public void setModifica(boolean modifica) {
		this.modifica = modifica;
	}

	public int getNrTessere() {
		nrTessere = getTessere().size();
		return nrTessere;
	}

	public int getIncassato() {

		incassato = 0;
		for (TesseraDTO tessera : getTessere()) {

			incassato += tessera.getQuotaIncassata();

		}
		return incassato;

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<TesseraDTO> getTessere() {
		return tessere;
	}

	public void setTessere(List<TesseraDTO> tessere) {
		this.tessere = tessere;
	}

	
	public String getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(String idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public void setAnagrafiche(List<AnagraficaDTO> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public void setVisibleAnagrafiche(boolean visibleAnagrafiche) {
		this.visibleAnagrafiche = visibleAnagrafiche;
	}

	public List<SelectItem> getAnni() {
		return anni;
	}

	public void setAnni(List<SelectItem> anni) {
		this.anni = anni;
	}

	public RendicontazioneManuale() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		GregorianCalendar gCalendar = new GregorianCalendar();
		int annoInCorso = gCalendar.get(GregorianCalendar.YEAR);
		anni = new ArrayList<SelectItem>();
		tessereItem = new ArrayList<SelectItem>();
		int anniRendicontabili = Integer.parseInt(Configuration
				.getProperty("nrAnniReportRendicontabili"));
		for (int i = 0; i <= anniRendicontabili; i++) {
			anni.add(new SelectItem(Integer.valueOf(annoInCorso - i), Integer
					.toString(annoInCorso - i)));
		}

		setAnno(annoInCorso);
		tessere = new ArrayList<TesseraDTO>();
		setVisibleAnagrafiche(false);
		setModifica(false);
		prepareDDLTessere();

	}

	public void aggiungiAnagraficaClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {

			
			if (getIdAnagrafica() == null || getIdAnagrafica().isEmpty()) {				
				writeInfoMessage(TipoGravitaMessage.ERROR, Errori.ANAG_REQUIRED);				
				return;
			}
			Integer idAnagrafica = Integer.parseInt(getIdAnagrafica());

			List<TesseraDTO> tesseraAdd = new ArrayList<TesseraDTO>();

			if (getTessera() != null && getTessera() != -1) {
				tesseraAdd = RicercaTipoTesseraBD
						.ricercaTessereRendicontabiliManualmente(idAnagrafica,
								getTessera(), getAnno());
			} else {
				tesseraAdd = RicercaTipoTesseraBD
						.ricercaTessereRendicontabiliManualmente(idAnagrafica,
								getAnno());
			}
			for (TesseraDTO tes : tesseraAdd) {
				for (TesseraDTO tes_pres : getTessere()) {

					if (tes_pres.getIdTessera() == tes.getIdTessera())
						throw new ContradaExceptionNonBloccante(
								Errori.TESSERA_GIA_PRES + ", id tessera="
										+ tes.getIdTessera());
				}
				tes.setIncasso(tes.getQuota() - tes.getQuotaIncassata());

				if (tes.getIncasso() < 0) {
					tes.setIncasso(0);

				}

			}
			if (tesseraAdd != null && !tesseraAdd.isEmpty()) {

				
				getTessere().addAll(0, tesseraAdd);
			} else {
				writeInfoMessage(TipoGravitaMessage.ERROR, Errori.TESSERE_NON_PRES);				
			}

			setVisibleAnagrafiche(!getTessere().isEmpty());
			setIdAnagrafica(null);
			setDsAnagraficaTessera(null);
		} catch (ContradaExceptionNonBloccante e) {
			writeInfoMessage(TipoGravitaMessage.ERROR, e.getMessage());			
		}

	}

	public void eliminaTesseraOnClick(ActionEvent e) {
		int idTessera = Integer.valueOf(e.getComponent().getAttributes().get(
				"idTessera").toString());
		TesseraDTO recToDelete = null;
		for (TesseraDTO rec : getTessere()) {
			if (rec.getIdTessera() == idTessera) {
				recToDelete = rec;
				break;
			}
		}
		if (recToDelete != null) {
			getTessere().remove(recToDelete);
			if (recToDelete.getIncasso() != null) {
				totQuotaIncassata -= recToDelete.getIncasso();
			}
		}
		setVisibleAnagrafiche(!getTessere().isEmpty());
	}
	
	public void annoChange(ValueChangeEvent e)
	{
		annullaClick(null);
	}
	
	public void annullaClick(ActionEvent e)
	{		
		getTessere().clear();
		visibleAnagrafiche=false;
		hideInfoMessage();		
		
	}

	public void confermaAnnoClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<RateizzazioneDTO> rateizzazioni = new ArrayList<RateizzazioneDTO>();
		RateizzazioneDTO rat = null;
		renderStampaDistinta=false;

		if (getTessere() == null || getTessere().isEmpty()) {
			return;
		}

		for (TesseraDTO tessera : getTessere()) {
			if (tessera.getIncasso() != null && tessera.getIncasso() > 0) {
				rat = new RateizzazioneDTO();
				rat.setIdTessera(tessera.getIdTessera());
				rat.setIdRata(TipoRata.ESTEMPORANEA.getRata());
				rat.setImRata(tessera.getIncasso());
				rat.setNrAnno(getAnno());
				rat.setIdRid(tessera.getIdRid());
				rat.setIdEsattore(tessera.getIdTipoEsattore());
				rat.setIdIncasso(tessera.getIdTipoIncasso());
				rat.setTipoStatoRata(TipoStatoRata.Pagata.getStatoRata());
				rat.setNote(tessera.getDsNote());
				rat.setNrBollettinoManuale(tessera.getNrBollettino());
				rateizzazioni.add(rat);
			}
		}
		if (!rateizzazioni.isEmpty()) {
			 
		     distinta = GestioneRateizzazioneBD
					.inserisciRateizzazione(rateizzazioni);
		    
		     java.util.Date date= new java.util.Date();
		     distinta.setTsInserimento(new Timestamp(date.getTime()));		     
		     distinta.setTxTimeStamp(ConverterContrada.convertDateToString(date));

			
		     
		     writeInfoMessage(TipoGravitaMessage.SUCCESS, String.format("Distinta numero %d,%s", distinta
						.getNrDistinta(), distinta.getTxOper()));
		     
			renderStampaDistinta=true;

			tessere = new ArrayList<TesseraDTO>();
			setTotQuotaIncassata(0);
			setVisibleAnagrafiche(false);
			setIdAnagrafica(null);
			setDsAnagraficaTessera(null);

			for (TesseraDTO tessera : getTessere()) {
				if (tessera.getIncasso() != null) {
					tessera.setQuotaIncassata(tessera.getQuotaIncassata()
							+ tessera.getIncasso());
				}
			}
		} else {			
			writeInfoMessage(TipoGravitaMessage.INFO, "Nessuna tessera rendicontata, inserire una quota incasso maggiore di zero");
		}

	}

	public void modificaTesseraOnClick(ActionEvent e) {
		int idTessera = Integer.valueOf(e.getComponent().getAttributes().get(
				"idTessera").toString());
		setModifica(true);
		for (TesseraDTO tessera : getTessere()) {
			if (tessera.getIdTessera() == idTessera) {
				tessera.setIncasso(tessera.getQuota()
						- tessera.getQuotaIncassata());
				tessera.setModificata(true);
				if (tessera.getIncasso() < 0) {
					tessera.setIncasso(0);
				}
			}
		}
	}

	public void commit(ActionEvent e) throws ContradaExceptionNonBloccante {
		int idTessera = Integer.valueOf(e.getComponent().getAttributes().get(
				"idTessera").toString());

		totQuotaIncassata = 0;
		for (TesseraDTO tessera : getTessere()) {
			if (tessera.getIdTessera() == idTessera) {

				// tessera.setQuotaIncassata(tessera.getQuotaIncassata()+tessera.getQuota());
				// setModifica(false);
				tessera.setModificata(false);

				break;
			}
			totQuotaIncassata += tessera.getIncasso();
		}
	}

	public void cancel(ActionEvent e) {
		int idTessera = Integer.valueOf(e.getComponent().getAttributes().get(
				"idTessera").toString());
		for (TesseraDTO tessera : getTessere()) {
			if (tessera.getIdTessera() == idTessera) {
				tessera.setIncasso(0);
				tessera.setDsNote(null);
				// tessera.setQuotaIncassata(tessera.getQuotaIncassata()+tessera.getQuota());
				// setModifica(false);
				tessera.setModificata(false);

				break;
			}
		}
	}

	private void prepareDDLTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		List<TipoTesseraDTO> tessere = RicercaTipoTesseraBD.elencaTipoTessera();
		tessereItem.add(new SelectItem(-1, ""));
		for (TipoTesseraDTO tes : tessere) {
			tessereItem.add(new SelectItem(tes.getIdTipoTessera(), tes
					.getDsTipoTessera()));
		}
	}

	public void updateListAnagrafiche(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			if (e.getNewValue() != null
					&& !e.getNewValue().toString().isEmpty()) {
				SelectInputText autoComplete = (SelectInputText) e
						.getComponent();
				if (autoComplete.getSelectedItem() != null) {
					AnagraficaDTO anagrafica = (AnagraficaDTO) autoComplete
							.getSelectedItem().getValue();
					setIdAnagrafica(Integer.toString(anagrafica
							.getIdAnagrafica()));
				} else {

					anagrafiche = RicercaAnagraficaUtil
							.getAnagraficaByAutocomplete(e.getNewValue()
									.toString());
					anagraficheItem = new ArrayList<SelectItem>();
					
					if (anagrafiche.isEmpty()) {
						setDsAnagraficaTessera("");

					}
					for (AnagraficaDTO anagrafica : anagrafiche) {
						anagraficheItem.add(new SelectItem(anagrafica,
								anagrafica.getIntestatario()));

					}

				}
			}
		}
	}

}
