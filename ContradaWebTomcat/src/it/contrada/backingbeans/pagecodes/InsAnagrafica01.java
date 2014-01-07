package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneAnagraficaBD;
import it.contrada.businessdelegate.GestioneGestoreBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaCapBD;
import it.contrada.businessdelegate.RicercaCaricaBD;
import it.contrada.businessdelegate.RicercaComuneBD;
import it.contrada.businessdelegate.RicercaEsattoreBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaProvinciaBD;
import it.contrada.businessdelegate.RicercaRegioneBD;
import it.contrada.businessdelegate.RicercaStradaBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.common.util.ConverterTypes;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.CapDTO;
import it.contrada.dto.CaricaTesseraDTO;
import it.contrada.dto.ComuneDTO;
import it.contrada.dto.EsattoreDTO;
import it.contrada.dto.GestoreDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.RecapitoDTO;
import it.contrada.dto.RegioneDTO;
import it.contrada.dto.RidDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.enumcontrada.TipoIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.styles.StyleBean;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.HelperSession;
import it.contrada.web.util.RicercaAnagraficaUtil;
import it.contrada.web.util.VerificaDatiAnagraficiClientePojo;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.ext.HtmlInputText;
import com.icesoft.faces.component.ext.HtmlSelectOneMenu;
import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class InsAnagrafica01 {

	private int tipoRecapito;
	private String valueRecapito;

	private Boolean visibleTessere;
	private Boolean visibleModalita;
	private final String FORMAT_IT = "€ ###,##0.00";

	private Date dtNascita;

	private List<SelectItem> provincieNascitaPerStato = null;
	private List<SelectItem> comuniNascitaPerProvincia = null;
	private List<SelectItem> regioniPerStato = null;
	private List<SelectItem> statiAnagrafica = null;
	private List<SelectItem> provincieResPerStato = null;
	private List<SelectItem> comuniResidenzaPerProvincia = null;
	private List<SelectItem> capItemPerComuneResidenza;
	private List<SelectItem> stradePerCap;
	private List<SelectItem> elencoTipoRecapiti;
	private List<SelectItem> elencoTipoIncasso;
	private List<SelectItem> elencoTipoEsattore;
	private List<SelectItem> elencoTipoTessera;
	private List<SelectItem> elencoModalitaPagamento;
	private List<SelectItem> elencoTipoCarica;
	private List<SelectItem> elencoTipoCarica_modificata;
	private List<SelectItem> elencoTipoIncasso_modificata;
	private List<SelectItem> elencoTipoRateizzazione_modifica;
	private List<SelectItem> elencoTipoTessera_modifica;
	private List<SelectItem> gestoriItem;
	private String ricFamByCognome;
	private Date dtFazzoletto;
	List<CaricaTesseraDTO> caricheTessereDTO;

	public Date getDtFazzoletto() {
		return dtFazzoletto;
	}

	public void setDtFazzoletto(Date dtFazzoletto) {
		this.dtFazzoletto = dtFazzoletto;
		anagrafica.setDtFazzoletto(ConverterTypes.valueOf(dtFazzoletto));
	}

	public String getRicFamByCognome() {
		return ricFamByCognome;
	}

	public void setRicFamByCognome(String ricFamByCognome) {
		this.ricFamByCognome = ricFamByCognome;

	}

	private boolean popupAnag;

	public boolean isPopupAnag() {
		return popupAnag;
	}

	public List<SelectItem> getGestoriItem() {
		return gestoriItem;
	}

	public List<SelectItem> getElencoTipoTessera_modifica() {
		return elencoTipoTessera_modifica;
	}

	public void setElencoTipoTessera_modifica(
			List<SelectItem> elencoTipoTesseraModifica) {
		elencoTipoTessera_modifica = elencoTipoTesseraModifica;
	}

	private List<RecapitoDTO> recapiti;
	private List<TipoTesseraDTO> tessere;
	private List<CaricaTesseraDTO> caricheModificate;
	private List<StradaDTO> listVieDTO;

	private AnagraficaDTO anagrafica;
	private Boolean aggiornamento;

	private int incasso;
	private Boolean visibleRid;
	private Boolean visibleEsattore;
	private int esattore;
	private int tipoTessera;
	private int modalitaPagamento;

	private Integer nrRid;
	private Integer tipoCarica;
	private String quota;
	private String clientClickCercaFamiglia;
	private String clientClickCercaRid;
	private Boolean nuovoRid;
	private String clientClickNuovoRid;
	private boolean ridFromInsertAnag;
	private int nrCivico;
	private boolean disabledRid;
	private boolean disabledFamiglia;
	private boolean readOnlyRid;

	private boolean visibleCariche;
	private boolean modifica;
	private int idTesseraDaModificare;
	private TesseraDTO tesDaModificare;
	private boolean visibleCaricheModificate;
	private List<SelectItem> listVie;
	private String valueVia;
	private List<MembroFamigliaDTO> famiglie;
	private List<SelectItem> famiglieItem;
	private String dsFamiglia;
	private List<MembroRidDTO> membriRid;
	private List<SelectItem> membriRidItem;
	private String dsNominativoRid;
	private boolean ridPopupVisible;
	private boolean visibleFamigliaPopup;
	private List<SelectItem> localitaItem;
	private boolean mostraAggiungiTessera;
	private String hideShowTessera;
	private String imgHideShowTessera;
	private String messaggio;
	private boolean mostraAggiungiRecapito;
	private String hideShowRecapito;
	private String imgHideShowRecapito;
	private boolean visibleIndietro;
	private String urlIndietro;
	private boolean renderPopFamiglia;

	public boolean isRenderPopFamiglia() {
		return renderPopFamiglia;
	}

	public boolean isVisibleIndietro() {
		return visibleIndietro;
	}

	public String getHideShowRecapito() {
		return hideShowRecapito;
	}

	public String getImgHideShowRecapito() {
		return imgHideShowRecapito;
	}

	public boolean isMostraAggiungiRecapito() {
		return mostraAggiungiRecapito;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public String getImgHideShowTessera() {
		return imgHideShowTessera;
	}

	public String getHideShowTessera() {
		return hideShowTessera;
	}

	public void setHideShowTessera(String hideShowTessera) {
		this.hideShowTessera = hideShowTessera;
	}

	public boolean isMostraAggiungiTessera() {
		return mostraAggiungiTessera;
	}

	public void setMostraAggiungiTessera(boolean mostraAggiungiTessera) {
		this.mostraAggiungiTessera = mostraAggiungiTessera;
	}

	public List<SelectItem> getLocalitaItem() {
		return localitaItem;
	}

	public void setLocalitaItem(List<SelectItem> localitaItem) {
		this.localitaItem = localitaItem;
	}

	public boolean isVisibleFamigliaPopup() {
		return visibleFamigliaPopup;
	}

	public void setVisibleFamigliaPopup(boolean visibleFamigliaPopup) {
		this.visibleFamigliaPopup = visibleFamigliaPopup;
	}

	public boolean isRidPopupVisible() {
		return ridPopupVisible;
	}

	public void setRidPopupVisible(boolean ridPopupVisible) {
		this.ridPopupVisible = ridPopupVisible;
	}

	public List<MembroRidDTO> getMembriRid() {
		return membriRid;
	}

	public void setMembriRid(List<MembroRidDTO> membriRid) {
		this.membriRid = membriRid;
	}

	public List<SelectItem> getMembriRidItem() {
		return membriRidItem;
	}

	public void setMembriRidItem(List<SelectItem> membriRidItem) {
		this.membriRidItem = membriRidItem;
	}

	public String getDsNominativoRid() {
		return dsNominativoRid;
	}

	public void setDsNominativoRid(String dsNominativoRid) {
		this.dsNominativoRid = dsNominativoRid;
	}

	public String getDsFamiglia() {
		return dsFamiglia;
	}

	public void setDsFamiglia(String dsFamiglia) {
		this.dsFamiglia = dsFamiglia;
	}

	public List<MembroFamigliaDTO> getFamiglie() {
		return famiglie;
	}

	public void setFamiglie(List<MembroFamigliaDTO> famiglie) {
		this.famiglie = famiglie;
	}

	public List<SelectItem> getFamiglieItem() {
		return famiglieItem;
	}

	public void setFamiglieItem(List<SelectItem> famiglieItem) {
		this.famiglieItem = famiglieItem;
	}

	public String getValueVia() {
		return valueVia;
	}

	public void setValueVia(String valueVia) {
		this.valueVia = valueVia;
	}

	public List<SelectItem> getListVie() {
		return listVie;
	}

	public void setListVie(List<SelectItem> listVie) {
		this.listVie = listVie;
	}

	public boolean isVisibleCaricheModificate() {
		if (caricheModificate == null || caricheModificate.isEmpty())
			return false;
		else
			return true;
	}

	public List<SelectItem> getElencoTipoRateizzazione_modifica() {
		return elencoTipoRateizzazione_modifica;
	}

	public void setElencoTipoRateizzazione_modifica(
			List<SelectItem> elencoTipoRateizzazioneModifica) {
		elencoTipoRateizzazione_modifica = elencoTipoRateizzazioneModifica;
	}

	public List<SelectItem> getElencoTipoIncasso_modificata() {
		return elencoTipoIncasso_modificata;
	}

	public void setElencoTipoIncasso_modificata(
			List<SelectItem> elencoTipoIncassoModificata) {
		elencoTipoIncasso_modificata = elencoTipoIncassoModificata;
	}

	public List<SelectItem> getElencoTipoCarica_modificata() {
		return elencoTipoCarica_modificata;
	}

	public void setElencoTipoCarica_modificata(
			List<SelectItem> elencoTipoCaricaModificata) {
		elencoTipoCarica_modificata = elencoTipoCaricaModificata;
	}

	public boolean isModifica() {
		modifica = isAggiornamento();
		return modifica;
	}

	public boolean isVisibleCariche() {
		// visibleCariche = getTipoCarica() != null;
		return visibleCariche;
	}

	public List<TipoTesseraDTO> getTessere() {
		return tessere;
	}

	public void setTessere(List<TipoTesseraDTO> tessere) {
		this.tessere = tessere;
	}

	public boolean isReadOnlyRid() {
		return readOnlyRid;
	}

	public void setReadOnlyRid(boolean readOnlyRid) {
		this.readOnlyRid = readOnlyRid;
	}

	public boolean isAggiornamento() {

		if (this.aggiornamento == null) {
			this.aggiornamento = HelperSession
					.getFromRequest(Costante.PARM_MOD_ANAG) != null;

		}
		return this.aggiornamento;
	}

	public boolean getAggiornamento() {

		return isAggiornamento();
	}

	private boolean isReadOnlyStatoAnag;

	public boolean isReadOnlyStatoAnag() {
		isReadOnlyStatoAnag = !isAggiornamento();
		return isReadOnlyStatoAnag;

	}

	public boolean isDisabledFamiglia() {
		return disabledFamiglia;
	}

	public void setDisabledFamiglia(boolean disabledFamiglia) {
		this.disabledFamiglia = disabledFamiglia;
	}

	public boolean isDisabledRid() {
		disabledRid = isNuovoRid();

		return disabledRid;
	}

	public void setDisabledRid(boolean disabledRid) {
		this.disabledRid = disabledRid;
	}

	public Date getDtNascita() {
		return dtNascita;
	}

	public void setDtNascita(Date dtNascita) {
		this.dtNascita = dtNascita;
		anagrafica.setDtNascita(ConverterTypes.valueOf(dtNascita));
	}

	public Integer getNrRid() {
		if (isNuovoRid()) {
			this.getAnagrafica().getRid().setNrRid(null);
		}
		return this.getAnagrafica().getRid().getNrRid();
	}

	public void setNrRid(Integer nrRid) {
		if (this.getAnagrafica().getRid() != null)
			this.getAnagrafica().getRid().setNrRid(nrRid);
	}

	public AnagraficaDTO getAnagrafica() {

		if (isAggiornamento()) {

			anagrafica = (AnagraficaDTO) HelperSession
					.getFromRequest(Costante.SESSION_ANAG_DTO);
			if (anagrafica.getTessere() != null
					&& !anagrafica.getTessere().isEmpty()) {
				if (anagrafica.getTessere().get(0).getIdTessera() == 0) {
					anagrafica.setTessere(null);
				}
			}

			if (anagrafica.getRid() == null) {
				anagrafica.setRid(new RidDTO());
			}

			dtNascita = ConverterTypes.valueOf(anagrafica.getDtNascita());
			dtFazzoletto = ConverterTypes.valueOf(anagrafica.getDtFazzoletto());

			if (anagrafica.getIdLocalita() == null) {
				anagrafica.setIdLocalita(-1);
			}

		}

		if (anagrafica == null) {
			inizializzaAnagrafica();
		}

		return anagrafica;
	}

	public void setAnagrafica(AnagraficaDTO anagrafica) {
		this.anagrafica = anagrafica;
	}

	public int getNrCivico() {
		return nrCivico;
	}

	public void setNrCivico(int nrCivico) {
		this.nrCivico = nrCivico;
	}

	public boolean isRidFromInsertAnag() {
		return ridFromInsertAnag;
	}

	public void setRidFromInsertAnag(boolean ridFromInsertAnag) {
		this.ridFromInsertAnag = ridFromInsertAnag;
	}

	public String getClientClickNuovoRid() {
		return clientClickNuovoRid;
	}

	public boolean isNuovoRid() {
		if (anagrafica.getRid() != null) {
			return anagrafica.getRid().isNuovoRid();
		} else {
			return false;
		}
	}

	public void setNuovoRid(boolean nuovoRid) {
		this.nuovoRid = nuovoRid;
	}

	public boolean isVisibleModalita() {
		if (elencoModalitaPagamento == null
				|| elencoModalitaPagamento.isEmpty() == true) {
			visibleModalita = false;
		} else {
			visibleModalita = true;
		}
		return visibleModalita;
	}

	public void setVisibleModalita(boolean visibleModalita) {
		this.visibleModalita = visibleModalita;
	}

	public String getClientClickCercaRid() {
		return clientClickCercaRid;
	}

	public String getClientClickCercaFamiglia() {
		return clientClickCercaFamiglia;
	}

	public String getQuota() {
		if (this.quota == null || this.quota.trim() == "") {
			if (FacesContext.getCurrentInstance().getExternalContext()
					.getSessionMap().containsKey("CaricheTesseraDTO")) {
				List<CaricaTesseraDTO> cariche = (List<CaricaTesseraDTO>) FacesContext
						.getCurrentInstance().getExternalContext()
						.getSessionMap().get("CaricheTesseraDTO");

				if (cariche != null) {
					for (CaricaTesseraDTO carica : cariche) {
						if (carica.getIdCarica() == tipoCarica) {
							quota = Integer.toString(carica.getImTessera());
							break;
						}

					}
				}

			}
		}
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	public List<SelectItem> getElencoTipoCarica()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoTipoCarica == null) {
			elencoTipoCarica = recuperaCaricaPerTessera(tipoTessera);
		}
		return elencoTipoCarica;
	}

	public void setElencoTipoCarica(List<SelectItem> elencoTipoCarica) {
		this.elencoTipoCarica = elencoTipoCarica;
	}

	public Integer getTipoCarica() {
		return tipoCarica;
	}

	public void setTipoCarica(Integer tipoCarica) {
		this.tipoCarica = tipoCarica;
	}

	public boolean isVisibleTessere() {
		if (anagrafica.getTessere() != null
				&& anagrafica.getTessere().isEmpty() == false) {
			visibleTessere = true;
		} else {
			visibleTessere = false;
		}
		return visibleTessere;
	}

	public int getModalitaPagamento() {
		return modalitaPagamento;
	}

	public void setModalitaPagamento(int modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public int getTipoTessera() {
		return tipoTessera;
	}

	public void setTipoTessera(int tipoTessera) {
		this.tipoTessera = tipoTessera;
	}

	public List<SelectItem> getElencoModalitaPagamento()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoModalitaPagamento == null) {
			// elencoModalitaPagamento = recuperaTipoRateizzazione(tipoTessera);
			elencoModalitaPagamento = recuperaTipoRateizzazionePerTesseraIncasso(
					tipoTessera, incasso);
		}
		return elencoModalitaPagamento;
	}

	public void setElencoModalitaPagamento(
			List<SelectItem> elencoModalitaPagamento) {
		this.elencoModalitaPagamento = elencoModalitaPagamento;
	}

	public List<SelectItem> getElencoTipoTessera()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoTipoTessera == null) {
			elencoTipoTessera = recuperaTipoTessera();
		}
		return elencoTipoTessera;
	}

	public void setElencoTipoTessera(List<SelectItem> elencoTipoTessera) {
		this.elencoTipoTessera = elencoTipoTessera;
	}

	public List<SelectItem> getElencoTipoEsattore()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoTipoEsattore == null) {
			elencoTipoEsattore = recuperaEsattori();
		}

		return elencoTipoEsattore;
	}

	public void setElencoTipoEsattore(List<SelectItem> elencoTipoEsattore) {
		this.elencoTipoEsattore = elencoTipoEsattore;
	}

	public int getEsattore() {
		return esattore;
	}

	public void setEsattore(int esattore) {
		this.esattore = esattore;
	}

	public boolean isVisibleEsattore() {

		if (incasso == TipoIncasso.ESATTORE.getIncasso()) {
			visibleEsattore = true;
		} else {
			visibleEsattore = false;
		}
		return visibleEsattore;
	}

	public void setVisibleEsattore(boolean visibleEsattore) {
		this.visibleEsattore = visibleEsattore;
	}

	public boolean isVisibleRid() {
		if (incasso == TipoIncasso.RID.getIncasso()) {
			visibleRid = true;
		} else {
			visibleRid = false;
		}
		return visibleRid;
	}

	public void setVisibleRid(boolean visibleRid) {
		this.visibleRid = visibleRid;
	}

	public int getIncasso() {
		return incasso;
	}

	public void setIncasso(int incasso) {
		this.incasso = incasso;
	}

	public String getValueRecapito() {
		return valueRecapito;
	}

	public void setValueRecapito(String valueRecapito) {
		this.valueRecapito = valueRecapito;
	}

	public int getTipoRecapito() {
		return tipoRecapito;
	}

	public void setTipoRecapito(int tipoRecapito) {
		this.tipoRecapito = tipoRecapito;
	}

	public List<SelectItem> getElencoTipoIncasso()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoTipoIncasso == null) {
			elencoTipoIncasso = recuperaTipoIncassi();
		}
		return elencoTipoIncasso;
	}

	/*
	 * public List<SelectItem> getElencoTipoRecapiti() throws
	 * ContradaExceptionBloccante, ContradaExceptionNonBloccante { if
	 * (elencoTipoRecapiti == null) { elencoTipoRecapiti =
	 * recuperaTipoRecapiti(); } return elencoTipoRecapiti; }
	 */

	public List<SelectItem> getStradePerCap()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// stradePerCap = getStradePerCap(capPost);
		if (stradePerCap == null) {
			stradePerCap = recuperaStradePerCap(anagrafica.getCapPost());
		}
		return stradePerCap;

	}

	public List<SelectItem> getStatiAnagrafica()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// stradePerCap = getStradePerCap(capPost);
		if (statiAnagrafica == null) {
			statiAnagrafica = elencaStatiAnagrafica();
		}
		return statiAnagrafica;

	}

	public List<SelectItem> getCapComuniResidenza()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// capItemPerComuneResidenza = getCaps(provinciaResidenza,
		// comuneResidenza);
		if (capItemPerComuneResidenza == null) {
			capItemPerComuneResidenza = recuperaCaps(anagrafica
					.getProvinciaResidenza(), anagrafica.getComuneResidenza());
		}
		return capItemPerComuneResidenza;
	}

	public List<SelectItem> getComuniResidenzaPerProvincia()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// comuniResidenzaPerProvincia =
		// getComuniPerProvincia(provinciaResidenza);
		if (comuniResidenzaPerProvincia == null) {
			comuniResidenzaPerProvincia = recuperaComuniPerProvincia(anagrafica
					.getProvinciaResidenza());
		}
		return comuniResidenzaPerProvincia;
	}

	public List<SelectItem> getRegioniPerStato()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// regioniPerStato = getRegioniPerStato(statoResidenza);
		if (regioniPerStato == null) {
			regioniPerStato = recuperaRegioniPerStato(anagrafica
					.getStatoResidenza());
		}
		return regioniPerStato;
	}

	public List<SelectItem> getProvincieResPerStato()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// provinciePerRegione = getProvinciePerRegione(regioneResidenza);
		if (provincieResPerStato == null) {
			provincieResPerStato = recuperaProvinciePerStato(anagrafica
					.getStatoResidenza());
		}
		return provincieResPerStato;
	}

	public int getProvinciaNascita() {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().containsKey("cdProvinciaNas")
				&& FacesContext.getCurrentInstance().getExternalContext()
						.getRequestMap().get("cdProvinciaNas") != null)

		{
			anagrafica.setProvinciaNascita(Integer.parseInt(FacesContext
					.getCurrentInstance().getExternalContext().getRequestMap()
					.get("cdProvinciaNas").toString()));
		}
		return anagrafica.getProvinciaNascita().intValue();

	}

	public List<SelectItem> getProvincieNascitaPerStato()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (provincieNascitaPerStato == null) {
			provincieNascitaPerStato = recuperaProvinciePerStato(anagrafica
					.getStatoNascita());
		}
		return provincieNascitaPerStato;
	}

	public int getProvinciaResidenza() {
		if (FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap().containsKey("cdProvinciaRes")
				&& FacesContext.getCurrentInstance().getExternalContext()
						.getRequestMap().get("cdProvinciaRes") != null)

		{
			anagrafica.setProvinciaResidenza(Integer.parseInt(FacesContext
					.getCurrentInstance().getExternalContext().getRequestMap()
					.get("cdProvinciaRes").toString()));
		}
		return anagrafica.getProvinciaResidenza();
	}

	public InsAnagrafica01() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub

		// Javascript js = new Javascript();
		// nuova famiglia
		/*
		 * String idNrFamiglia = js.getClientId("txtNrFamiglia"); String
		 * idNuovaFamiglia = js.getClientId("chkFamiglia"); StringBuilder sb =
		 * new StringBuilder(); sb.append("if (document.getElementById('" +
		 * idNuovaFamiglia + "').value==checked)");
		 * sb.append("document.getElementById('" + idNrFamiglia +
		 * "').value=='');");
		 * 
		 * this.clientClickCercaFamiglia=sb.toString();
		 */

		// nuovo rid;
		/*
		 * String idNrRid = js.getClientId("txtNrRid"); sb = new
		 * StringBuilder(); String idNuovoRid = js.getClientId("chkrID"); sb =
		 * new StringBuilder(); sb.append("if (document.getElementById('" +
		 * idNuovoRid + "').value==checked)");
		 * sb.append("document.getElementById('" + idNrRid + "').value=='');");
		 * 
		 * this.clientClickNuovoRid=sb.toString();
		 */

		/*
		 * if (getAnagrafica()!=null &&
		 * HelperSession.getFromRequest(Costante.SESSION_ANAG_DTO) != null) {
		 * setAnagrafica((AnagraficaDTO) HelperSession
		 * .getFromRequest(Costante.SESSION_ANAG_DTO));
		 * setDtNascita(ConverterTypes.valueOf(getAnagrafica().getDtNascita()));
		 * //inizializzaView(); } else { inizializzaView(); }
		 */

		// HelperSession.putInSession(Costante.SESSION_ANAG_DTO, anagrafica);

		inizializzaBean();
	}

	private void inizializzaBean() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		ridPopupVisible = false;
		visibleFamigliaPopup = false;

		setIncasso(Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getInitParameter("incasso")));

		setTipoTessera(Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getInitParameter("tipoTessera")));

		setTipoCarica(Integer.parseInt(FacesContext.getCurrentInstance()
				.getExternalContext().getInitParameter("tipoCarica")));

		visibleCariche = true;

		mostraAggiungiTessera = false;

		if (isAggiornamento()) {

			/*
			 * urlIndietro = "ANAGRAFICA_MODIFICATA"; anagrafica =
			 * (AnagraficaDTO) HelperSession
			 * .getFromRequest(Costante.SESSION_ANAG_DTO); if
			 * (anagrafica.getTessere() != null &&
			 * !anagrafica.getTessere().isEmpty()) { if
			 * (anagrafica.getTessere().get(0).getIdTessera() == 0) {
			 * anagrafica.setTessere(null); } }
			 * 
			 * if (anagrafica.getRid() == null) { anagrafica.setRid(new
			 * RidDTO()); }
			 * 
			 * dtNascita = ConverterTypes.valueOf(anagrafica.getDtNascita());
			 * 
			 * if (anagrafica.getIdLocalita() == null) {
			 * anagrafica.setIdLocalita(-1); }
			 */
			mostraAggiungiTessera = false;
		} else {
			// mostra aggiungi tessera

			inizializzaAnagrafica();
		}

		iniziallizzaLocalita(getAnagrafica().getCapPost(), getAnagrafica()
				.getProvinciaResidenza(), getAnagrafica().getComuneResidenza());
		iniziallizzaGestori();

	}

	public List<SelectItem> getComuniNascitaPerProvincia()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (comuniNascitaPerProvincia == null) {
			comuniNascitaPerProvincia = recuperaComuniPerProvincia(anagrafica
					.getProvinciaNascita());
		}
		return comuniNascitaPerProvincia;
	}

	private List<SelectItem> recuperaTipoIncassi()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem tipoRecapitoItem = null;
		RicercaIncassoBD incassoBD = new RicercaIncassoBD();

		List<SelectItem> tipoIncassiItem = new ArrayList<SelectItem>();
		List<TipoIncassoDTO> tipoIncassiDTO = incassoBD.elencaTipoIncasso();
		if (tipoIncassiDTO != null) {
			for (TipoIncassoDTO tipoIncassoDTO : tipoIncassiDTO) {
				tipoRecapitoItem = new SelectItem(tipoIncassoDTO
						.getIdTipoIncasso(), tipoIncassoDTO.getDsTipoIncasso());

				tipoIncassiItem.add(tipoRecapitoItem);

			}
		}

		return tipoIncassiItem;

	}

	/*
	 * private List<SelectItem> recuperaTipoRecapiti() throws
	 * ContradaExceptionBloccante, ContradaExceptionNonBloccante { SelectItem
	 * tipoRecapitoItem = null; RicercaRecapitoBD recapitoBD = new
	 * RicercaRecapitoBD();
	 * 
	 * List<SelectItem> tipoRecapitiItem = new ArrayList<SelectItem>();
	 * List<TipoRecapitoDTO> tipoRecapitiDTO = recapitoBD.elenca(); if
	 * (tipoRecapitiDTO != null) { for (TipoRecapitoDTO tipoRecapitoDTO :
	 * tipoRecapitiDTO) { tipoRecapitoItem = new SelectItem(tipoRecapitoDTO
	 * .getIdTipoRecapito(), tipoRecapitoDTO .getDsTipoRecapito());
	 * tipoRecapitiItem.add(tipoRecapitoItem);
	 * 
	 * } }
	 * 
	 * return tipoRecapitiItem;
	 * 
	 * }
	 */

	private List<SelectItem> recuperaTipoTessera()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem tipoTesseraItem = null;
		RicercaTipoTesseraBD tesseraBD = new RicercaTipoTesseraBD();

		List<SelectItem> tipoTessereItem = new ArrayList<SelectItem>();
		setTessere(tesseraBD.elencaTipoTessera());
		if (getTessere() != null) {
			for (TipoTesseraDTO tipoTesseraDTO : getTessere()) {
				tipoTesseraItem = new SelectItem(tipoTesseraDTO
						.getIdTipoTessera(), tipoTesseraDTO.getDsTipoTessera());
				tipoTessereItem.add(tipoTesseraItem);

			}
		}

		return tipoTessereItem;

	}

	private List<SelectItem> recuperaTipoTesseraPerINcasso(int idTipoIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem tipoTesseraItem = null;
		RicercaTipoTesseraBD tesseraBD = new RicercaTipoTesseraBD();

		List<SelectItem> tipoTessereItem = new ArrayList<SelectItem>();
		setTessere(tesseraBD.ricercaTipoTesseraPerIncasso(idTipoIncasso));
		if (getTessere() != null) {
			for (TipoTesseraDTO tipoTesseraDTO : getTessere()) {
				tipoTesseraItem = new SelectItem(tipoTesseraDTO
						.getIdTipoTessera(), tipoTesseraDTO.getDsTipoTessera());
				tipoTessereItem.add(tipoTesseraItem);

			}
		}

		return tipoTessereItem;

	}

	private List<SelectItem> recuperaCaricaPerTessera(int tipoTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem tipoCaricaItem = null;

		List<SelectItem> tipoCaricheItem = new ArrayList<SelectItem>();
		caricheTessereDTO = RicercaCaricaBD.ricercaPerTessera(tipoTessera);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.put("CaricheTesseraDTO", caricheTessereDTO);
		if (caricheTessereDTO != null) {
			for (CaricaTesseraDTO caricaTesseraDTO : caricheTessereDTO) {
				tipoCaricaItem = new SelectItem(caricaTesseraDTO.getIdCarica(),
						caricaTesseraDTO.getDsCarica());
				tipoCaricheItem.add(tipoCaricaItem);

			}
		}

		return tipoCaricheItem;

	}

	private List<SelectItem> recuperaCaricaPerTesseraModificata(int tipoTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem tipoCaricaItem = null;

		List<SelectItem> tipoCaricheItem = new ArrayList<SelectItem>();
		caricheModificate = RicercaCaricaBD.ricercaPerTessera(tipoTessera);

		if (caricheModificate != null && !caricheModificate.isEmpty()) {
			for (CaricaTesseraDTO caricaTesseraDTO : caricheModificate) {
				tipoCaricaItem = new SelectItem(caricaTesseraDTO.getIdCarica(),
						caricaTesseraDTO.getDsCarica());
				tipoCaricheItem.add(tipoCaricaItem);

			}
		}

		return tipoCaricheItem;

	}

	private List<SelectItem> recuperaTipoRateizzazione(int idTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem tipoRateizzazioneItem = null;
		RicercaTipoRateizzazioneBD rateizzazioneBD = new RicercaTipoRateizzazioneBD();

		List<SelectItem> tipoRateizzazioniItem = new ArrayList<SelectItem>();
		List<TipoRateizzazioneDTO> tipoRateizzazioniDTO = rateizzazioneBD
				.ricercaPerTessera(idTessera);

		if (tipoRateizzazioniDTO != null) {
			for (TipoRateizzazioneDTO tipoRateizzazioneDTO : tipoRateizzazioniDTO) {
				tipoRateizzazioneItem = new SelectItem(tipoRateizzazioneDTO
						.getIdTipoRateizzazione(), tipoRateizzazioneDTO
						.getDsTipoRateizzazione());
				tipoRateizzazioniItem.add(tipoRateizzazioneItem);

			}
		}

		return tipoRateizzazioniItem;

	}

	private List<SelectItem> recuperaTipoRateizzazionePerTesseraIncasso(
			int idTessera, int idIncasso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		SelectItem tipoRateizzazioneItem = null;
		RicercaTipoRateizzazioneBD rateizzazioneBD = new RicercaTipoRateizzazioneBD();

		List<SelectItem> tipoRateizzazioniItem = new ArrayList<SelectItem>();
		List<TipoRateizzazioneDTO> tipoRateizzazioniDTO = rateizzazioneBD
				.ricercaPerTesseraIncasso(idTessera, idIncasso);

		if (tipoRateizzazioniDTO != null) {
			for (TipoRateizzazioneDTO tipoRateizzazioneDTO : tipoRateizzazioniDTO) {
				tipoRateizzazioneItem = new SelectItem(tipoRateizzazioneDTO
						.getIdTipoRateizzazione(), tipoRateizzazioneDTO
						.getDsTipoRateizzazione());
				tipoRateizzazioniItem.add(tipoRateizzazioneItem);

			}
		}

		return tipoRateizzazioniItem;

	}

	/*
	 * private List<SelectItem> recuperaTipoRateizzazione(int idTessera, int
	 * idIncasso) throws ContradaExceptionBloccante,
	 * ContradaExceptionNonBloccante { SelectItem tipoRateizzazioneItem = null;
	 * RicercaTipoRateizzazioneBD rateizzazioneBD = new
	 * RicercaTipoRateizzazioneBD();
	 * 
	 * List<SelectItem> tipoRateizzazioniItem = new ArrayList<SelectItem>();
	 * List<TipoRateizzazioneDTO> tipoRateizzazioniDTO = rateizzazioneBD
	 * .ricercaPerTesseraIncasso(idTessera, idIncasso);
	 * 
	 * if (tipoRateizzazioniDTO != null) { for (TipoRateizzazioneDTO
	 * tipoRateizzazioneDTO : tipoRateizzazioniDTO) { tipoRateizzazioneItem =
	 * new SelectItem(tipoRateizzazioneDTO .getIdTipoRateizzazione(),
	 * tipoRateizzazioneDTO .getDsTipoRateizzazione());
	 * tipoRateizzazioniItem.add(tipoRateizzazioneItem);
	 * 
	 * } }
	 * 
	 * return tipoRateizzazioniItem;
	 * 
	 * }
	 */
	private List<SelectItem> recuperaEsattori()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem esattoreItem = null;
		RicercaEsattoreBD esattoreBD = new RicercaEsattoreBD();

		List<SelectItem> esattoriItem = new ArrayList<SelectItem>();
		List<EsattoreDTO> esattoriDTO = esattoreBD.elenca();
		if (esattoriDTO != null) {
			for (EsattoreDTO esattoreDTO : esattoriDTO) {
				esattoreItem = new SelectItem(esattoreDTO.getIdEsattore(),
						esattoreDTO.getDsEsattore());
				esattoriItem.add(esattoreItem);

			}
		}

		return esattoriItem;

	}

	private List<SelectItem> recuperaProvinciePerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		if (cdIsoStato == null) {
			return null;
		}

		// String
		// cdIsoStato=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("statoDefault");
		SelectItem provinciaItem = null;
		RicercaProvinciaBD provinciaBD = new RicercaProvinciaBD();

		List<SelectItem> provincieItemPerStato = new ArrayList<SelectItem>();
		List<ProvinciaDTO> provincieDTO = provinciaBD
				.ricercaPerStato(cdIsoStato);
		if (provincieDTO != null) {
			for (ProvinciaDTO provinciaDTO : provincieDTO) {
				provinciaItem = new SelectItem(provinciaDTO.getCdProvincia(),
						provinciaDTO.getDsProvincia());
				provincieItemPerStato.add(provinciaItem);

			}
		}

		return provincieItemPerStato;
	}

	private List<SelectItem> recuperaComuniPerProvincia(Integer provincia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		if (provincia == null) {
			return null;
		}

		SelectItem comuneItem = null;

		List<SelectItem> comuniPerProvincia = new ArrayList<SelectItem>();

		List<ComuneDTO> comuniDTO = RicercaComuneBD
				.ricercaPerProvincia(provincia);

		if (comuniDTO != null) {
			for (ComuneDTO comuneDTO : comuniDTO) {

				comuneItem = new SelectItem(comuneDTO.getCdComune(), comuneDTO
						.getDsComune());

				comuniPerProvincia.add(comuneItem);

			}

		}

		return comuniPerProvincia;
	}

	private List<SelectItem> recuperaStradePerCap(String cdCap)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		SelectItem stradaItem = null;

		List<SelectItem> stradePerCap = new ArrayList<SelectItem>();

		RicercaStradaBD stradaBD = new RicercaStradaBD();
		List<StradaDTO> stradeDTO = stradaBD.getStradaPerCap(cdCap);

		if (stradeDTO != null) {
			for (StradaDTO stradaDTO : stradeDTO) {

				stradaItem = new SelectItem(stradaDTO.getIdStrada(), stradaDTO
						.getDsStrada());

				stradePerCap.add(stradaItem);

			}

		}

		return stradePerCap;
	}

	private List<SelectItem> elencaStatiAnagrafica()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem statoAnagraficaItem = null;

		List<SelectItem> statiAnagraficaItem = new ArrayList<SelectItem>();

		List<TipoStatoAnagraficaDTO> statiAnagraficheDTO = RicercaAnagraficaBD
				.elencaStatiAnagrafica();

		if (statiAnagraficheDTO != null) {
			for (TipoStatoAnagraficaDTO statoAnagraficheDTO : statiAnagraficheDTO) {

				statoAnagraficaItem = new SelectItem(statoAnagraficheDTO
						.getIdStatoAnagrafica(), statoAnagraficheDTO
						.getDsStatoAnagrafica());

				statiAnagraficaItem.add(statoAnagraficaItem);

			}

		}

		return statiAnagraficaItem;
	}

	public void statoNascitaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		String stato;

		Integer cdProvincia = 0;
		Integer cdComune = 0;
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {

			if (null != event.getNewValue()) {

				stato = event.getNewValue().toString();
				provincieNascitaPerStato = recuperaProvinciePerStato(stato);
				if (!provincieNascitaPerStato.isEmpty()) {
					cdProvincia = Integer.parseInt(provincieNascitaPerStato
							.get(0).getValue().toString());
					anagrafica.setProvinciaNascita(cdProvincia);

					comuniNascitaPerProvincia = recuperaComuniPerProvincia(cdProvincia);
					if (!comuniNascitaPerProvincia.isEmpty()) {
						cdComune = Integer.parseInt(comuniNascitaPerProvincia
								.get(0).getValue().toString());
						anagrafica.setComuneNascita(cdComune);
					}
				}
			} else {
				provincieNascitaPerStato = null;
				comuniNascitaPerProvincia = null;
				anagrafica.setProvinciaNascita(null);
				anagrafica.setComuneNascita(null);
			}
		}
	}

	public void provinciaNascitaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		int cdProvincia = 0;
		int cdComune = 0;
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {

			if (null != event.getNewValue()) {

				cdProvincia = Integer.parseInt(event.getNewValue().toString());
				anagrafica.setProvinciaNascita(cdProvincia);

				comuniNascitaPerProvincia = recuperaComuniPerProvincia(cdProvincia);
				if (!comuniNascitaPerProvincia.isEmpty()) {
					cdComune = Integer.parseInt(comuniNascitaPerProvincia
							.get(0).getValue().toString());
					anagrafica.setComuneNascita(cdComune);
				}
			}
		}
	}

	public void nazioneResidenzaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		String stato;

		int cdProvincia = 0;
		int cdComune = 0;
		String cap = "";

		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {

			if (null != event.getNewValue()) {

				stato = event.getNewValue().toString();
				provincieResPerStato = recuperaProvinciePerStato(stato);
				if (!provincieResPerStato.isEmpty()) {
					cdProvincia = Integer.parseInt(provincieResPerStato.get(0)
							.getValue().toString());
					anagrafica.setProvinciaResidenza(cdProvincia);
				}

				comuniResidenzaPerProvincia = recuperaComuniPerProvincia(cdProvincia);
				if (!comuniResidenzaPerProvincia.isEmpty()) {
					cdComune = Integer.parseInt(comuniResidenzaPerProvincia
							.get(0).getValue().toString());
					anagrafica.setComuneResidenza(cdComune);

				}

				capItemPerComuneResidenza = recuperaCaps(cdProvincia, cdComune);
				if (!capItemPerComuneResidenza.isEmpty()) {
					cap = capItemPerComuneResidenza.get(0).getValue()
							.toString();
					anagrafica.setCapPost(cap);
				}

				// stradePerCap = recuperaStradePerCap(cap);

			}
		}
	}

	public void provinciaResidenzaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		int cdProvincia = 0;
		int cdComune = 0;
		String cap = "";
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {

			if (null != event.getNewValue()) {

				cdProvincia = Integer.parseInt(event.getNewValue().toString());

				comuniResidenzaPerProvincia = recuperaComuniPerProvincia(cdProvincia);
				if (!comuniResidenzaPerProvincia.isEmpty()) {
					anagrafica.setComuneResidenza(Integer
							.parseInt(comuniResidenzaPerProvincia.get(0)
									.getValue().toString()));

				}

				capItemPerComuneResidenza = recuperaCaps(cdProvincia,
						anagrafica.getComuneResidenza());
				if (!capItemPerComuneResidenza.isEmpty()) {
					anagrafica.setCapPost(capItemPerComuneResidenza.get(0)
							.getValue().toString());

				}

				// stradePerCap = recuperaStradePerCap(cap);
				iniziallizzaLocalita(capItemPerComuneResidenza.get(0)
						.getValue().toString(), cdProvincia, Integer
						.parseInt(comuniResidenzaPerProvincia.get(0).getValue()
								.toString()));
				anagrafica.setIdLocalita(-1);
				anagrafica.setIdStrada(null);
				anagrafica.setDsStrada(null);

			}
		}

	}

	public void tipoIncassoValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {
				Configuration conf = new Configuration();
				int tipoIncasso = Integer.parseInt(event.getNewValue()
						.toString());
				if (tipoIncasso == conf
						.getIntProperty(Costante.TIPO_INCASSO_RID)) {

					setVisibleEsattore(false);
				} else if (tipoIncasso == conf
						.getIntProperty(Costante.TIPO_INCASSO_ESATTORE)) {

					setVisibleEsattore(true);
				}

				elencoTipoTessera = recuperaTipoTesseraPerINcasso(tipoIncasso);
				if (elencoTipoTessera != null
						&& elencoTipoTessera.isEmpty() == false) {
					tipoTessera = Integer.parseInt(elencoTipoTessera.get(0)
							.getValue().toString());
				} else {
					tipoTessera = 0;
				}
				// elencoModalitaPagamento =
				// recuperaTipoRateizzazione(tipoTessera);
				elencoModalitaPagamento = recuperaTipoRateizzazionePerTesseraIncasso(
						tipoTessera, incasso);
				elencoTipoCarica = recuperaCaricaPerTessera(tipoTessera);

			}
		}

	}

	public void tipoTesseraValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		/*
		 * if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
		 * event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES); event.queue(); }
		 */// else {
		if (null != event.getNewValue()) {

			int tipoTessera = Integer.parseInt(event.getNewValue().toString());
			// elencoModalitaPagamento = recuperaTipoRateizzazione(tipoTessera);
			elencoModalitaPagamento = recuperaTipoRateizzazionePerTesseraIncasso(
					tipoTessera, incasso);

			elencoTipoCarica = recuperaCaricaPerTessera(tipoTessera);

			if (elencoTipoCarica == null || elencoTipoCarica.isEmpty()) {

				Integer quotaTessera = getQuotaFromTipoTessera(tipoTessera);
				if (quotaTessera != null) {
					setQuota(quotaTessera.toString());
					formatImporto();

					/*
					 * ((HtmlInputText) FacesUtils.findComponent("txtQuota"))
					 * .setSubmittedValue(quotaTessera.toString());
					 */

					// setQuota(quotaTessera.toString());
				}
				visibleCariche = false;
			} else {
				visibleCariche = true;

				((HtmlSelectOneMenu) FacesUtils.findComponent("tipoCarica"))
						.setSubmittedValue(elencoTipoCarica.get(0).getValue());

				setQuota(Integer.toString(caricheTessereDTO.get(0)
						.getImTessera()));

				formatImporto();

			}

		}
		// }

	}

	public void tipoCaricaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (null != event.getNewValue()) {

			// int quota = ((CaricaTesseraDTO)
			// event.getNewValue()).getImTessera();
			int idCarica = Integer.parseInt(event.getNewValue().toString());
			caricheTessereDTO = (List<CaricaTesseraDTO>) FacesContext
					.getCurrentInstance().getExternalContext().getSessionMap()
					.get("CaricheTesseraDTO");

			if (caricheTessereDTO != null) {
				for (CaricaTesseraDTO carica : caricheTessereDTO) {
					if (carica.getIdCarica() == idCarica) {
						setQuota(Integer.toString(carica.getImTessera()));

						break;
					}

				}
			} else {
				// nessuna carica, la quota si recupera dal tipo tessera.
				Integer quotaTessera = getQuotaFromTipoTessera(getTipoTessera());
				if (quotaTessera != null) {
					setQuota(quotaTessera.toString());
				}
			}

			formatImporto();

			/*
			 * Double importo = ((Integer.valueOf(quota)).doubleValue()) / 100;
			 * 
			 * NumberFormat nf=NumberFormat.getNumberInstance(new Locale("it"));
			 * 
			 * DecimalFormat formatter =(DecimalFormat)nf;
			 * formatter.applyPattern(FORMAT_IT);
			 * 
			 * String strImporto = formatter.format(importo);
			 * 
			 * ((HtmlInputText) FacesUtils.findComponent("txtQuota"))
			 * .setSubmittedValue(strImporto);
			 */

		}

	}

	private void formatImporto() {
		Double importo = ((Integer.valueOf(getQuota())).doubleValue()) / 100;

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("it"));

		DecimalFormat formatter = (DecimalFormat) nf;
		formatter.applyPattern(FORMAT_IT);

		String strImporto = formatter.format(importo);

		((HtmlInputText) FacesUtils.findComponent("txtQuota"))
				.setSubmittedValue(strImporto);

	}

	private Integer getQuotaFromTipoTessera(int idTipoTessera) {
		if (getTessere() == null || getTessere().isEmpty()) {
			return null;
		}

		for (TipoTesseraDTO tessera : getTessere()) {
			if (tessera.getIdTipoTessera() == idTipoTessera) {
				return tessera.getImTessera();
			}
		}
		return null;
	}

	public void comuniResidenzaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		int cdComune = 0;
		int cdProvincia = 0;
		String cap = "";
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {

				cdComune = Integer.parseInt(event.getNewValue().toString());
				FacesContext.getCurrentInstance().getExternalContext()
						.getRequestMap().put("cdComuneRes", cdComune);
				cdProvincia = anagrafica.getProvinciaResidenza();

				capItemPerComuneResidenza = recuperaCaps(cdProvincia, cdComune);
				if (!capItemPerComuneResidenza.isEmpty()) {
					cap = capItemPerComuneResidenza.get(0).getValue()
							.toString();
					anagrafica.setCapPost(cap);
				}

				// stradePerCap = recuperaStradePerCap(cap);
				iniziallizzaLocalita(cap, cdProvincia, cdComune);
				anagrafica.setIdLocalita(-1);
				anagrafica.setIdStrada(null);
				anagrafica.setDsStrada(null);

			}
		}

	}

	public void capResidenzaValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		String cap = "";
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {

				cap = event.getNewValue().toString();
				iniziallizzaLocalita(cap, anagrafica.getProvinciaResidenza(),
						anagrafica.getComuneResidenza());
				anagrafica.setIdLocalita(-1);
				anagrafica.setDsStrada(null);
				anagrafica.setIdStrada(null);
				// stradePerCap = recuperaStradePerCap(cap);

			}
		}
	}

	public void nuovoRidValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);

			event.queue();
		} else {
			if (null != event.getNewValue()) {
				setNrRid(null);
				setDsNominativoRid(null);
			}

			/*
			 * boolean check = boolean.parseboolean(event.getNewValue()
			 * .toString()); if (check) {
			 * 
			 * setDisabledRid(true); } else { setDisabledRid(false); }
			 */
			/*
			 * if (check) { PortletSession portletSession = HelperSession
			 * .getSessionPortlet(); if (portletSession != null) {
			 * portletSession.setAttribute("nrRid", null,
			 * PortletSession.APPLICATION_SCOPE); } //
			 * SessionRenderer.render("NrRid"); }
			 */
			// }

		}

	}

	public void nuovaFamValueChange(ValueChangeEvent event)
			throws AbortProcessingException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {

				boolean check = Boolean.parseBoolean(event.getNewValue()
						.toString());
				anagrafica.setNuovaFamiglia(check);
				if (check) {
					anagrafica.setIdFamiglia(null);
					setDisabledFamiglia(true);
					setDsFamiglia(null);
				} else {
					setDisabledFamiglia(false);
				}
				/*
				 * if (check) { PortletSession portletSession = HelperSession
				 * .getSessionPortlet(); if (portletSession != null) {
				 * portletSession.setAttribute("nrFamiglia", null,
				 * PortletSession.APPLICATION_SCOPE);
				 * 
				 * } // SessionRenderer.render("NrRid"); }
				 */
			}
		}

	}

	private List<SelectItem> recuperaCaps(int cdProvincia, int cdComune)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		SelectItem capItem = null;

		List<SelectItem> capsItem = new ArrayList<SelectItem>();
		List<CapDTO> caps = RicercaCapBD.recuperaCap(cdProvincia, cdComune);
		if (caps != null) {
			for (CapDTO cap : caps) {
				capItem = new SelectItem(cap.getCdCap());
				capsItem.add(capItem);
			}
		}
		return capsItem;

	}

	private List<SelectItem> recuperaRegioniPerStato(String cdIsoStato)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		// String
		// cdIsoStato=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("statoDefault");
		SelectItem regioneItem = null;
		RicercaRegioneBD regioneBD = new RicercaRegioneBD();

		List<SelectItem> regioniItemPerStato = new ArrayList<SelectItem>();
		List<RegioneDTO> regioniDTO = regioneBD.ricercaPerStato(cdIsoStato);
		if (regioniDTO != null) {
			for (RegioneDTO regioneDTO : regioniDTO) {
				regioneItem = new SelectItem(regioneDTO.getCdRegione(),
						regioneDTO.getDsRegione());
				regioniItemPerStato.add(regioneItem);

			}
		}

		return regioniItemPerStato;
	}

	public void addRidOnClick(ActionEvent event) {
	}

	public void cercaRidOnClick(ActionEvent event) {
	}

	public void eliminaTesseraOnClick(ActionEvent e) {

		TesseraDTO tesToDelete = (TesseraDTO) e.getComponent().getAttributes()
				.get("tessera");

		if (tesToDelete != null) {
			anagrafica.getTessere().remove(tesToDelete);
		}

	}

	public void addTesseraOnClick(ActionEvent event)
			throws ContradaExceptionNonBloccante {

		if (anagrafica.getTessere() == null) {
			anagrafica.setTessere(new ArrayList<TesseraDTO>());
		} else {
			for (TesseraDTO tessera : anagrafica.getTessere()) {
				if (tessera.getIdTessera() == tipoTessera) {
					throw new ContradaExceptionNonBloccante(
							Costante.TESSERA_PRESENTE);
				}
			}
		}

		TesseraDTO tessera = new TesseraDTO();
		tessera.setIdTipoRateizzazione(modalitaPagamento);
		tessera.setIdTessera(tipoTessera);
		tessera.setQuota(Integer.parseInt(quota));
		tessera.setIdTipoIncasso(getIncasso());
		tessera.setIdTipoTessera(getTipoTessera());
		tessera.setFgAttiva(true);
		if (anagrafica.getIdAnagrafica() != 0) {
			tessera.setIdAnag(anagrafica.getIdAnagrafica());
		}

		int prova = TipoIncasso.ESATTORE.getIncasso();

		tessera.setDsEsattore("");

		if (TipoIncasso.ESATTORE.getIncasso() == getIncasso()) {
			tessera.setIdTipoEsattore(getEsattore());
			for (SelectItem item : elencoTipoEsattore) {
				if (item.getValue().toString().equals(
						Integer.toString(getEsattore()))) {
					tessera.setDsEsattore(item.getLabel());
					break;
				}
			}
		}

		tessera.setDsRid("");

		if (TipoIncasso.RID.getIncasso() == getIncasso()) {

			// if (isNuovoRid()) tessera.setDsRid("nuovo"); else {

			if (anagrafica.getRid().getNrRid() != null) {
				tessera.setDsRid(anagrafica.getRid().getNrRid().toString());
				tessera.setIdRid(anagrafica.getRid().getNrRid());
			}

			// else { throw new
			// ContradaExceptionNonBloccante(Errori.RID_NON_PRES); }

			// }

		}

		for (SelectItem item : elencoModalitaPagamento) {
			if (item.getValue().toString().equals(
					Integer.toString(modalitaPagamento))) {
				tessera.setDsTipoRateizzazione(item.getLabel());
				break;
			}

		}

		for (SelectItem item : elencoTipoIncasso) {
			if (item.getValue().toString().equals(
					Integer.toString(getIncasso()))) {
				tessera.setDsIncasso(item.getLabel());
				break;
			}

		}

		for (SelectItem item : elencoTipoTessera) {
			if (item.getValue().toString().equals(
					Integer.toString(getTipoTessera()))) {
				tessera.setDsTipoTessera(item.getLabel());
				break;
			}

		}

		if (visibleCariche) {
			tessera.setIdTipoCarica(getTipoCarica());
			for (SelectItem item : elencoTipoCarica) {
				if (item.getValue().toString().equals(
						Integer.toString(getTipoCarica()))) {
					tessera.setDsTipoCarica(item.getLabel());
					break;
				}

			}
		}

		anagrafica.getTessere().add(tessera);
	}

	public void cercaFamigliaOnClick(ActionEvent event) {
	}

	public void addFamigliaOnClick(ActionEvent event) {
	}

	public String indietroOnClick() throws IOException {

		if (isAggiornamento()) {
			FacesUtils.redirectToUrl("RicercaAnagrafica.iface");
			return null;
		} else
			return null;
	}

	public String proseguiOnClick() throws IOException,
			ContradaExceptionBloccante {

		messaggio = null;

		try {
			getAnagrafica().setUtente(FacesUtils.getUser());

			if (getAnagrafica().isNuovaFamiglia() == false
					&& getAnagrafica().getIdFamiglia() == null) {
				messaggio = Errori.FAMIGLIA_REQUIRED;
				return null;
			}

			if (getAnagrafica().getTessere() == null
					|| getAnagrafica().getTessere().isEmpty()) {
				messaggio = Errori.TESSERE_NON_INSERITE;
				return null;
			}

			// aggiungo i membri rid partendo dalla tessere
			addMembriToAnagrafica(getAnagrafica());

			if (getAnagrafica().getIdLocalita() != null
					&& getAnagrafica().getIdLocalita() == -1) {
				getAnagrafica().setIdLocalita(null);
			}

			if (getAnagrafica().getIdGestore() != null
					&& getAnagrafica().getIdGestore() == -1) {
				getAnagrafica().setIdGestore(null);
			}

			if (isNuovoRid() && isAggiornamento() == false) {
				// mettiamo in sessione anagDTO
				HelperSession.putInSession(Costante.SESSION_ANAG_DTO,
						anagrafica);

				FacesUtils.redirectToUrl("../Rid/InserimentoRid.iface?"
						+ Costante.RID_FROM_INSERT_ANAG + "=true");

				return null;
				// return "NUOVO_RID";
			} else {
				if (anagrafica.getComuneNascita() != null
						&& anagrafica.getComuneNascita() == 0) {
					anagrafica.setComuneNascita(null);
				}
				if (anagrafica.getProvinciaNascita() != null
						&& anagrafica.getProvinciaNascita() == 0) {
					anagrafica.setProvinciaNascita(null);
				}
				if (anagrafica.getStatoNascita() != null
						&& anagrafica.getStatoNascita().trim().equals("")) {
					anagrafica.setStatoNascita(null);
				}

				if (getAnagrafica().getTessere() != null) {
					for (TesseraDTO tes : getAnagrafica().getTessere()) {
						if (tes.getIdRid() != null && tes.getIdRid() == 0) {
							tes.setIdRid(null);
						}
					}
				}

				// verifica se siamo in modalità modifica

				if (isAggiornamento()) {
					// invocare Aggiornamento Anagrafica

					GestioneAnagraficaBD.aggiornaAnagrafica(anagrafica, false);
					// FacesUtils.redirectToUrl("RicercaAnagrafica.iface");

					FacesUtils.redirectToUrl("RicercaAnagrafica.iface");
					return null;

				} else {
					// siamo in inserimento
					anagrafica = GestioneAnagraficaBD
							.inserisciAnagrafica(anagrafica);
					popupAnag = true;

				}

			}
		} catch (ContradaExceptionNonBloccante e) {
			// TODO: handle exception
			messaggio = e.getMessage();
		} catch (ContradaExceptionBloccante e) {
			// TODO: handle exception
			messaggio = e.getMessage();
		}
		return null;

	}

	private void addMembriToAnagrafica(AnagraficaDTO anag) {
		List<TesseraDTO> tessere = anag.getTessere();

		anag.getRid().setMembri(new ArrayList<MembroRidDTO>());

		MembroRidDTO membro = null;

		for (TesseraDTO tessera : tessere) {

			if (it.contrada.enumcontrada.TipoIncasso.RID.getIncasso() == tessera
					.getIdTipoIncasso()) {
				membro = new MembroRidDTO();
				membro.setCognome(anag.getCognome());
				membro.setNome(anag.getNome());
				membro.setQuota(tessera.getQuota());
				membro.setTessera(tessera.getDsTipoTessera());
				membro.setModalita(tessera.getDsTipoRateizzazione());
				membro.setIdAnagrafica(anag.getIdAnagrafica());
				membro.setIdTessera(tessera.getIdTessera());
				anag.getRid().getMembri().add(membro);
			}
		}

	}

	public void modificaTesseraOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		tesDaModificare = (TesseraDTO) e.getComponent().getAttributes().get(
				"idTessera");

		tesDaModificare.setModificata(true);

		setElencoTipoTessera_modifica(recuperaTipoTesseraPerINcasso(tesDaModificare
				.getIdTipoIncasso()));
		setElencoTipoCarica_modificata(recuperaCaricaPerTesseraModificata(tesDaModificare
				.getIdTipoTessera()));
		setElencoTipoIncasso_modificata(recuperaTipoIncassi());

		setElencoTipoRateizzazione_modifica(recuperaTipoRateizzazionePerTesseraIncasso(
				tesDaModificare.getIdTipoTessera(), tesDaModificare
						.getIdTipoIncasso()));

	}

	public void commit(ActionEvent e) {
		if (tesDaModificare != null) {
			tesDaModificare.setModificata(false);
		}

	}

	public void cancel(ActionEvent e) {
		if (tesDaModificare != null) {
			tesDaModificare.setModificata(false);
		}
	}

	public void caricaModificataValueChange(ValueChangeEvent e) {

		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			int idTipoCarica = Integer.parseInt(e.getNewValue().toString());
			for (SelectItem item : getElencoTipoCarica_modificata()) {
				if (idTipoCarica == Integer
						.parseInt(item.getValue().toString())) {
					tesDaModificare.setDsTipoCarica(item.getLabel());

					tesDaModificare.setQuota(getQuotaPerCarica(idTipoCarica,
							caricheModificate));

					/*
					 * HtmlInputText control=((HtmlInputText) HelperSession
					 * .findComponent("txtQuotaTesseraModificata"));
					 * control.setSubmittedValue
					 * (getQuotaPerCarica(idTipoCarica,caricheModificate));
					 */

					// ((HtmlOutputText)HelperSession.findComponent("lblQuotaTesseraModificata")).setSubmittedValue(tesDaModificare.getQuota());

					break;
				}

			}
		}
	}

	public void incassoModificatoValueChange(ValueChangeEvent e)
			throws NumberFormatException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {
			int idIncassoNew = Integer.parseInt(e.getNewValue().toString());
			setElencoTipoTessera_modifica(recuperaTipoTesseraPerINcasso(tesDaModificare
					.getIdTipoIncasso()));
			tesDaModificare.setIdTipoTessera(Integer
					.parseInt(getElencoTipoTessera_modifica().get(0).getValue()
							.toString()));
			tesDaModificare.setDsTipoTessera(getElencoTipoTessera_modifica()
					.get(0).getLabel());

			setElencoTipoRateizzazione_modifica(recuperaTipoRateizzazionePerTesseraIncasso(
					tesDaModificare.getIdTipoTessera(), idIncassoNew));
			tesDaModificare.setIdTipoRateizzazione(Integer
					.parseInt(getElencoTipoRateizzazione_modifica().get(0)
							.getValue().toString()));
			tesDaModificare
					.setDsTipoRateizzazione(getElencoTipoRateizzazione_modifica()
							.get(0).getLabel());

			setElencoTipoCarica_modificata(recuperaCaricaPerTesseraModificata(tesDaModificare
					.getIdTipoTessera()));

			if (idIncassoNew != TipoIncasso.ESATTORE.getIncasso()) {
				tesDaModificare.setIdTipoEsattore(null);
				tesDaModificare.setDsEsattore(null);
			}
			if (idIncassoNew != TipoIncasso.RID.getIncasso()) {
				tesDaModificare.setIdRid(null);
				tesDaModificare.setDsRid(null);
			}

			if (getElencoTipoCarica_modificata() != null
					&& !getElencoTipoCarica_modificata().isEmpty()) {
				tesDaModificare
						.setDsTipoCarica(getElencoTipoCarica_modificata()
								.get(0).getLabel());
				tesDaModificare.setIdTipoCarica(Integer
						.parseInt(getElencoTipoCarica_modificata().get(0)
								.getValue().toString()));

				tesDaModificare.setQuota(getQuotaPerCarica(tesDaModificare
						.getIdTipoCarica(),

				caricheModificate));
			} else {
				Integer quotaTessera = getQuotaFromTipoTessera(tesDaModificare
						.getIdTipoTessera());
				tesDaModificare.setDsTipoCarica("");
				if (quotaTessera != null) {
					tesDaModificare.setQuota(quotaTessera);
				}
			}

			for (SelectItem item : getElencoTipoIncasso_modificata()) {
				if (idIncassoNew == Integer
						.parseInt(item.getValue().toString())) {
					tesDaModificare.setDsIncasso(item.getLabel());

				}

			}
		}
	}

	public void tipoTesseraModificataValueChange(ValueChangeEvent e)
			throws NumberFormatException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			for (SelectItem item : getElencoTipoTessera_modifica()) {
				if (Integer.parseInt(item.getValue().toString()) == Integer
						.parseInt(e.getNewValue().toString())) {
					tesDaModificare.setDsTipoTessera(item.getLabel());
					break;
				}
			}

			setElencoTipoRateizzazione_modifica(recuperaTipoRateizzazionePerTesseraIncasso(
					Integer.parseInt(e.getNewValue().toString()),
					tesDaModificare.getIdTipoIncasso()));
			tesDaModificare.setIdTipoRateizzazione(Integer
					.parseInt(getElencoTipoRateizzazione_modifica().get(0)
							.getValue().toString()));
			tesDaModificare
					.setDsTipoRateizzazione(getElencoTipoRateizzazione_modifica()
							.get(0).getLabel());
			setElencoTipoCarica_modificata(recuperaCaricaPerTesseraModificata(tesDaModificare
					.getIdTipoTessera()));
			if (getElencoTipoCarica_modificata() != null
					&& !getElencoTipoCarica_modificata().isEmpty()) {
				tesDaModificare
						.setDsTipoCarica(getElencoTipoCarica_modificata()
								.get(0).getLabel());
				tesDaModificare.setIdTipoCarica(Integer
						.parseInt(getElencoTipoCarica_modificata().get(0)
								.getValue().toString()));

				tesDaModificare.setQuota(getQuotaPerCarica(tesDaModificare
						.getIdTipoCarica(), caricheModificate));
			} else {
				Integer quotaTessera = getQuotaFromTipoTessera(tesDaModificare
						.getIdTipoTessera());
				tesDaModificare.setDsTipoCarica("");
				tesDaModificare.setIdTipoCarica(null);
				if (quotaTessera != null) {
					tesDaModificare.setQuota(quotaTessera);

				}
			}
		}

	}

	private Integer getQuotaPerCarica(int idCarica,
			List<CaricaTesseraDTO> cariche) {
		Integer quota = null;
		if (cariche != null) {
			for (CaricaTesseraDTO carica : cariche) {
				if (carica.getIdCarica() == idCarica) {
					quota = carica.getImTessera();
					break;
				}

			}
		} else {
			// nessuna carica, la quota si recupera dal tipo tessera.
			quota = getQuotaFromTipoTessera(getTipoTessera());

		}
		return quota;
	}

	public void esattoreModificatoValueChange(ValueChangeEvent e)
			throws NumberFormatException, ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		for (SelectItem item : getElencoTipoEsattore()) {
			if (Integer.parseInt(e.getNewValue().toString()) == Integer
					.parseInt(item.getValue().toString())) {
				tesDaModificare.setDsEsattore(item.getLabel());
			}
		}
	}

	public void rateizzazioneModificataValueChange(ValueChangeEvent e) {
		for (SelectItem item : getElencoTipoRateizzazione_modifica()) {
			if (Integer.parseInt(e.getNewValue().toString()) == Integer
					.parseInt(item.getValue().toString())) {
				tesDaModificare.setDsTipoRateizzazione(item.getLabel());
			}
		}
	}

	public void updateListVie(ValueChangeEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		if (!ev.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			ev.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			ev.queue();
		} else {
			if (ev.getNewValue() != null
					&& !ev.getNewValue().toString().isEmpty()) {

				SelectInputText autoComplete = (SelectInputText) ev
						.getComponent();

				SelectItem item = null;
				if (autoComplete.getItemList() != null) {

					Iterator<SelectItem> items = autoComplete.getItemList();

					while (items.hasNext()) {

						SelectItem itemSelected = items.next();
						if (itemSelected.getLabel().equals(ev.getNewValue())) {
							item = itemSelected;
							break;
						}
					}
				}

				if (item != null) {
					StradaDTO strada = (StradaDTO) item.getValue();
					getAnagrafica().setIdStrada(strada.getIdStrada());

				} else {
					String matchVia = ev.getNewValue().toString();
					if (getAnagrafica().getIdLocalita() != -1
							&& getAnagrafica().getIdLocalita() != null) {
						listVieDTO = RicercaStradaBD
								.recuperaPerCapLocViaParziale(getAnagrafica()
										.getCapPost(), getAnagrafica()
										.getIdLocalita(), matchVia);

					} else {
						listVieDTO = RicercaStradaBD
								.getStradaPerCapViaParziale(getAnagrafica()
										.getCapPost(), matchVia);

					}

					listVie = new ArrayList<SelectItem>();

					for (StradaDTO strada : listVieDTO) {
						listVie
								.add(new SelectItem(strada, strada
										.getDsStrada()));
					}

				}

			}
		}
	}

	public void updateListFamiglie(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			if (e.getNewValue() != null
					&& !e.getNewValue().toString().isEmpty()) {
				SelectInputText autoComplete = (SelectInputText) e
						.getComponent();

				SelectItem item = null;
				/*
				 * if (autoComplete.getSelectedItem() != null) { item=
				 * autoComplete.getSelectedItem(); // fire effect to draw
				 * attention //valueChangeEffect.setFired(false); }
				 */

				if (autoComplete.getItemList() != null) {

					Iterator<SelectItem> items = autoComplete.getItemList();

					while (items.hasNext()) {

						SelectItem itemSelected = items.next();
						if (itemSelected.getLabel().equals(e.getNewValue())) {
							item = itemSelected;
							break;
						}
					}
				}

				if (item != null) {
					MembroFamigliaDTO famiglia = (MembroFamigliaDTO) item
							.getValue();

					getAnagrafica().setIdFamiglia(famiglia.getIdFamiglia());
					// aggiornamento residenza

					getAnagrafica().setComuneResidenza(famiglia.getCdComune());
					getAnagrafica().setDsStrada(famiglia.getDsStrada());

					getAnagrafica().setIdLocalita(famiglia.getIdLocalita());
					if (getAnagrafica().getIdLocalita() == null) {
						getAnagrafica().setIdLocalita(-1);
					}
					getAnagrafica().setCapPost(famiglia.getCdCap());
					getAnagrafica().setProvinciaResidenza(
							famiglia.getCdProvincia());
					getAnagrafica().setNrCivico(famiglia.getNrCivico());
					getAnagrafica().setStatoResidenza(famiglia.getCdIsoStato());
					getAnagrafica().setIdStrada(famiglia.getIdStrada());

					iniziallizzaLocalita(getAnagrafica().getCapPost(),
							getAnagrafica().getProvinciaResidenza(),
							getAnagrafica().getComuneResidenza());

					comuniResidenzaPerProvincia = null;
					capItemPerComuneResidenza = null;
					provincieResPerStato = null;

				} else {
					String matchCognome = e.getNewValue().toString();
					famiglie = RicercaAnagraficaUtil
							.getFamigliaByAutocomplete(matchCognome);

					famiglieItem = new ArrayList<SelectItem>();
					if (famiglie.isEmpty()) {
						setDsFamiglia("");

					}
					for (MembroFamigliaDTO famiglia : famiglie) {
						famiglieItem.add(new SelectItem(famiglia, famiglia
								.getNominativo()));

					}

				}

			}
		}

	}

	private void iniziallizzaLocalita(String cap, int provincia, int comune)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		List<LocalitaDTO> locs = RicercaStradaBD.recuperaLocalitaPerCap(cap,
				provincia, comune);
		localitaItem = new ArrayList<SelectItem>();
		localitaItem
				.add(new SelectItem(-1, "--- selezionare una località ---"));
		for (LocalitaDTO loc : locs) {
			localitaItem.add(new SelectItem(loc.getIdLocalita(), loc
					.getDsLocalita()));
		}

	}

	private void iniziallizzaGestori() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		List<GestoreDTO> gestori = GestioneGestoreBD.elencaGestori();
		gestoriItem = new ArrayList<SelectItem>();
		gestoriItem.add(new SelectItem(-1, "--- selezionare un gestore ---"));
		for (GestoreDTO gestore : gestori) {
			gestoriItem.add(new SelectItem(gestore.getIdGestore(), gestore
					.getCognome()
					+ " " + gestore.getNome()));
		}
	}

	public void updateListMembriRid(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			if (e.getNewValue() != null
					&& !e.getNewValue().toString().isEmpty()) {
				SelectInputText autoComplete = (SelectInputText) e
						.getComponent();

				SelectItem item = null;
				if (autoComplete.getItemList() != null) {

					Iterator<SelectItem> items = autoComplete.getItemList();

					while (items.hasNext()) {

						SelectItem itemSelected = items.next();
						if (itemSelected.getLabel().equals(e.getNewValue())) {
							item = itemSelected;
							break;
						}
					}
				}

				if (item != null) {
					MembroRidDTO rid = (MembroRidDTO) item.getValue();
					getAnagrafica().getRid().setNrRid(rid.getIdRid());
				} else {
					String matchCognome = e.getNewValue().toString();
					membriRid = RicercaAnagraficaUtil
							.getMembriRidByAutocomplete(matchCognome);
					membriRidItem = new ArrayList<SelectItem>();
					if (membriRid.isEmpty()) {
						setDsNominativoRid("");

					}
					for (MembroRidDTO rid : membriRid) {
						membriRidItem.add(new SelectItem(rid, rid
								.getNominativo()));

					}

				}

			}
		}

	}

	public void verificaCodiceFiscale(FacesContext context,
			UIComponent toValidate, Object value) {
		String cdFisc = (String) value;

		VerificaDatiAnagraficiClientePojo anag = new VerificaDatiAnagraficiClientePojo();
		if (!anag.verificaFormaleCodiceFiscale(cdFisc)) {
			FacesMessage message = new FacesMessage(
					FacesMessage.SEVERITY_ERROR,
					Errori.CODICE_FISCALE_NON_CORRETTO,
					Errori.CODICE_FISCALE_NON_CORRETTO);

			((UIInput) toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context), message);

		}

	}

	public void localitaValueChange(ValueChangeEvent e) {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			anagrafica.setIdStrada(null);
			anagrafica.setDsStrada(null);
		}
	}

	public void closeFamigliaPopup(ActionEvent ev) {
		visibleFamigliaPopup = false;
	}

	public void closeAnagraficaPopup(ActionEvent ev) throws IOException {
		popupAnag = false;
		FacesUtils.redirectToUrl("GestioneAnagrafica.iface");
	}

	public void openFamigliaPopup(ActionEvent ev) {
		visibleFamigliaPopup = true;
	}

	public void closeRidPopup(ActionEvent ev) {
		setRidPopupVisible(false);
	}

	public void openRidPopup(ActionEvent ev) {
		setRidPopupVisible(true);
	}

	public void hideShowRecapitoClick(ActionEvent e) {
		mostraAggiungiRecapito = !mostraAggiungiRecapito;
		if (mostraAggiungiRecapito) {
			hideShowRecapito = "Nascondi aggiiungi recapito";
			imgHideShowRecapito = ((StyleBean) FacesUtils.findBean("styleBean"))
					.getHide();
		} else {
			hideShowRecapito = "Mostra aggiiungi recapito";
			imgHideShowRecapito = ((StyleBean) FacesUtils.findBean("styleBean"))
					.getShow();
		}
	}

	private void inizializzaAnagrafica() {
		anagrafica = new AnagraficaDTO();
		String stato = FacesContext.getCurrentInstance().getExternalContext()
				.getInitParameter("statoDefault");
		anagrafica.setStatoNascita(stato);
		anagrafica.setStatoResidenza(stato);
		anagrafica.setProvinciaNascita(Integer.parseInt(FacesContext
				.getCurrentInstance().getExternalContext().getInitParameter(
						"provinciaNascitaDefault")));
		anagrafica.setComuneNascita(Integer.parseInt(FacesContext
				.getCurrentInstance().getExternalContext().getInitParameter(
						"comuneNascitaDefault")));
		anagrafica.setComuneResidenza(Integer.parseInt(FacesContext
				.getCurrentInstance().getExternalContext().getInitParameter(
						"comuneResidenzaDefault")));
		anagrafica.setProvinciaResidenza(Integer.parseInt(FacesContext
				.getCurrentInstance().getExternalContext().getInitParameter(
						"provinciaResidenzaDefault")));
		anagrafica.setCapPost(FacesContext.getCurrentInstance()
				.getExternalContext().getInitParameter("capResidenzaDefault"));
		anagrafica.setIdStatoAnagrafica(Integer.parseInt(FacesContext
				.getCurrentInstance().getExternalContext().getInitParameter(
						"statoAnagraficaDefault")));
		anagrafica.setIdLocalita(-1);
		anagrafica.setRid(new RidDTO());

	}

	public void cercaFamiglia(ActionEvent e) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		famiglie = RicercaAnagraficaUtil
				.getFamigliaByAutocomplete(ricFamByCognome);
	}

	public void showHidePopFamiglia(ActionEvent e) {
		this.renderPopFamiglia = !this.renderPopFamiglia;
	}

}
