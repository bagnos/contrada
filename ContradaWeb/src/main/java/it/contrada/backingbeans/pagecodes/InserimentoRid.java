package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneAnagraficaBD;
import it.contrada.businessdelegate.GestioneRidBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaCapBD;
import it.contrada.businessdelegate.RicercaComuneBD;
import it.contrada.businessdelegate.RicercaProvinciaBD;
import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.businessdelegate.RicercaStradaBD;
import it.contrada.businessdelegate.RicercaTipoRateizzazioneBD;
import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.CapDTO;
import it.contrada.dto.ComuneDTO;
import it.contrada.dto.IbanDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.RidDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.enumcontrada.TipoStatoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.CalcolaCoordinateBancariePojo;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.HelperSession;
import it.contrada.web.util.LoadBundleLanguage;
import it.contrada.web.util.RicercaAnagraficaUtil;
import it.contrada.web.util.VerificaCoordinateBancariePojo;
import it.contrada.web.util.VerificaDatiAnagraficiClientePojo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class InserimentoRid extends BaseView {

	private List<SelectItem> elencoTipoStatoRid;

	private List<SelectItem> provincieResidenza;

	private List<SelectItem> comuniResidenzaPerProvincia;

	private List<SelectItem> capComuniResidenza;

	private List<SelectItem> stradePerCap;

	private List<SelectItem> elencoModalitaPag;

	private boolean ridFromInsertAnag;
	private boolean statoDisabled;
	private AnagraficaDTO anag;

	public AnagraficaDTO getAnag() {
		return anag;
	}

	private boolean visibleAnagrafiche;
	private RidDTO rid;
	private String desAnag;
	private Integer nrAnag;

	private boolean renderMessIban;
	private List<StradaDTO> vie;
	private String dsStrada;
	private boolean visibleAnagraficaPopup;
	private String dsAnagraficaInRid;
	private List<AnagraficaDTO> anagrafiche;
	private List<SelectItem> anagraficheItem;
	private Boolean modificaRid;
	private boolean popupRid;
	private List<TesseraDTO> tessere;
	private boolean visibleTessere;

	public boolean isVisibleTessere() {
		return visibleTessere;
	}

	public List<TesseraDTO> getTessere() {
		return tessere;
	}

	public boolean isPopupRid() {
		return popupRid;
	}

	public Boolean getModificaRid() {
		return modificaRid;
	}

	public List<SelectItem> getAnagraficheItem() {
		return anagraficheItem;
	}

	public String getDsAnagraficaInRid() {
		return dsAnagraficaInRid;
	}

	public void setDsAnagraficaInRid(String dsAnagraficaInRid) {
		this.dsAnagraficaInRid = dsAnagraficaInRid;
	}

	public boolean isVisibleAnagraficaPopup() {
		return visibleAnagraficaPopup;
	}

	public void setVisibleAnagraficaPopup(boolean visibleAnagraficaPopup) {
		this.visibleAnagraficaPopup = visibleAnagraficaPopup;
	}

	public String getDsStrada() {
		return dsStrada;
	}

	public void setDsStrada(String dsStrada) {
		this.dsStrada = dsStrada;
	}

	public List<StradaDTO> getVie() {
		return vie;
	}

	public void setVie(List<StradaDTO> vie) {
		this.vie = vie;
	}

	public boolean isRenderMessIban() {
		return renderMessIban;
	}

	public InserimentoRid() throws ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub

		rid = new RidDTO();

		rid.setMembri(new ArrayList<MembroRidDTO>());
		rid.setProvinciaResidenza(Integer.parseInt(HelperSession
				.getInitParameter(Costante.PROVINCIA_NASCITA_DEFAULT)
				.toString()));
		rid.setComuneResidenza(Integer.parseInt(HelperSession.getInitParameter(
				Costante.COMUNE_RESIDENZA_DEFAULT).toString()));
		rid.setCapPost(HelperSession.getInitParameter(
				Costante.CAP_RESIDENZA_DEFAULT).toString());

		rid.setTipoStatoRid(TipoStatoRid.Censita.getStatoRid());
		rid.setPaese(Costante.CONSTANT_VAL_IT);

		if (isRidFromInsertAnag()) {

			anag = (AnagraficaDTO) HelperSession
					.getFromSession(Costante.SESSION_ANAG_DTO);

			if (anag.getTessere() == null) {
				throw new ContradaExceptionNonBloccante(
						Costante.TESSERE_NON_PRESENTI);
			}

			rid.setMembri(anag.getRid().getMembri());
		}

		else if (isModificaRid()) {

			rid = (RidDTO) HelperSession
					.getFromRequest(Costante.SESSION_RID_DTO);

		} else {
			// inserimento nuovo rid senza passare dall'anagrafica
		}

	}

	public Integer getNrAnag() {
		return nrAnag;
	}

	public void setNrAnag(Integer nrAnag) {
		this.nrAnag = nrAnag;
	}

	public String getDesAnag() {
		return desAnag;
	}

	public void setDesAnag(String desAnag) {
		this.desAnag = desAnag;
	}

	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	private String quota;

	public RidDTO getRid() {
		return rid;
	}

	public void setRid(RidDTO rid) {
		this.rid = rid;
	}

	public boolean isVisibleAnagrafiche() {

		if (getRid().getMembri() == null || getRid().getMembri().isEmpty()) {
			visibleAnagrafiche = false;
		} else {
			visibleAnagrafiche = true;
		}

		return visibleAnagrafiche;
	}

	public boolean isStatoDisabled() {

		if (!isModificaRid()) {
			statoDisabled = true;
		}
		return statoDisabled;
	}

	public void setStatoDisabled(boolean statoDisabled) {
		this.statoDisabled = statoDisabled;
	}

	public boolean isRidFromInsertAnag() {
		if (isInserimentoFromAnag()) {
			ridFromInsertAnag = true;
		}
		return ridFromInsertAnag;
	}

	public void setRidFromInsertAnag(boolean ridFromInsertAnag) {
		this.ridFromInsertAnag = ridFromInsertAnag;
	}

	public List<SelectItem> getElencoModalitaPag()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoModalitaPag == null) {
			elencoModalitaPag = recuperaTipoRateizzazione();
		}
		return elencoModalitaPag;

	}

	public List<SelectItem> getStradePerCap()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (stradePerCap == null) {
			stradePerCap = recuperaStradePerCap(rid.getCapPost());
		}
		return stradePerCap;

	}

	public List<SelectItem> getCapComuniResidenza()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (capComuniResidenza == null) {
			capComuniResidenza = recuperaCaps(rid.getProvinciaResidenza(),
					rid.getComuneResidenza());
		}
		return capComuniResidenza;
	}

	private List<SelectItem> recuperaStradePerCap(String cdCap)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		SelectItem stradaItem = null;

		List<SelectItem> stradePerCap = new ArrayList<SelectItem>();

		RicercaStradaBD stradaBD = new RicercaStradaBD();
		List<StradaDTO> stradeDTO = stradaBD.getStradaPerCap(cdCap);

		if (stradeDTO != null) {
			for (StradaDTO stradaDTO : stradeDTO) {

				stradaItem = new SelectItem(stradaDTO.getIdStrada(),
						stradaDTO.getDsStrada());

				stradePerCap.add(stradaItem);

			}

		}

		return stradePerCap;
	}

	public List<SelectItem> getComuniResidenzaPerProvincia()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (comuniResidenzaPerProvincia == null) {
			comuniResidenzaPerProvincia = recuperaComuniPerProvincia(rid
					.getProvinciaResidenza());
		}
		return comuniResidenzaPerProvincia;

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

	private List<SelectItem> recuperaTipoRateizzazione()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		int idTessera = 1;
		SelectItem tipoRateizzazioneItem = null;
		RicercaTipoRateizzazioneBD rateizzazioneBD = new RicercaTipoRateizzazioneBD();

		List<SelectItem> tipoRateizzazioniItem = new ArrayList<SelectItem>();
		List<TipoRateizzazioneDTO> tipoRateizzazioniDTO = rateizzazioneBD
				.ricercaPerTessera(idTessera);

		if (tipoRateizzazioniDTO != null) {
			for (TipoRateizzazioneDTO tipoRateizzazioneDTO : tipoRateizzazioniDTO) {
				tipoRateizzazioneItem = new SelectItem(
						tipoRateizzazioneDTO.getIdTipoRateizzazione(),
						tipoRateizzazioneDTO.getDsTipoRateizzazione());
				tipoRateizzazioniItem.add(tipoRateizzazioneItem);

			}
		}

		return tipoRateizzazioniItem;

	}

	public List<SelectItem> getProvincieResidenza()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (provincieResidenza == null) {
			provincieResidenza = recuperaProvincie();
		}
		return provincieResidenza;
	}

	public List<SelectItem> getElencoTipoStatoRid()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (elencoTipoStatoRid == null) {
			elencoTipoStatoRid = recuperaStatiRid();
		}

		return elencoTipoStatoRid;
	}

	private List<SelectItem> recuperaStatiRid()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		// String
		// cdIsoStato=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("statoDefault");
		SelectItem statoRidItem = null;
		RicercaRidBD ridBD = new RicercaRidBD();

		List<SelectItem> statiRidItem = new ArrayList<SelectItem>();
		List<TipoStatoRidDTO> statiRidDTO = ridBD.elencaStati();
		if (statiRidDTO != null) {
			for (TipoStatoRidDTO ridDTO : statiRidDTO) {
				statoRidItem = new SelectItem(ridDTO.getIdStatoRid(),
						ridDTO.getDsStatoRid());
				statiRidItem.add(statoRidItem);

			}
		}

		return statiRidItem;
	}

	private List<SelectItem> recuperaProvincie()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		// String
		// cdIsoStato=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("statoDefault");
		SelectItem provinciaItem = null;
		RicercaProvinciaBD provinciaBD = new RicercaProvinciaBD();

		List<SelectItem> provincieItemPerStato = new ArrayList<SelectItem>();
		List<ProvinciaDTO> provincieDTO = provinciaBD.elenca();

		if (provincieDTO != null) {
			for (ProvinciaDTO provinciaDTO : provincieDTO) {
				provinciaItem = new SelectItem(provinciaDTO.getCdProvincia(),
						provinciaDTO.getDsProvincia());
				provincieItemPerStato.add(provinciaItem);

			}
		}

		return provincieItemPerStato;
	}

	private List<SelectItem> recuperaComuniPerProvincia(int provincia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		SelectItem comuneItem = null;

		List<SelectItem> comuniPerProvincia = new ArrayList<SelectItem>();

		List<ComuneDTO> comuniDTO = RicercaComuneBD
				.ricercaPerProvincia(provincia);

		if (comuniDTO != null) {
			for (ComuneDTO comuneDTO : comuniDTO) {

				comuneItem = new SelectItem(comuneDTO.getCdComune(),
						comuneDTO.getDsComune());

				comuniPerProvincia.add(comuneItem);

			}

		}

		return comuniPerProvincia;
	}

	public void provinciaResidenzaValueChange(ValueChangeEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		int cdProvincia = 0;
		int cdComune = 0;
		String cap = "";
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {

				cdProvincia = Integer.parseInt(event.getNewValue().toString());
				/*
				 * FacesContext.getCurrentInstance().getExternalContext()
				 * .getRequestMap().put("cdProvinciaRes", cdProvincia);
				 */

				comuniResidenzaPerProvincia = recuperaComuniPerProvincia(cdProvincia);
				if (!comuniResidenzaPerProvincia.isEmpty()) {
					cdComune = Integer.parseInt(comuniResidenzaPerProvincia
							.get(0).getValue().toString());
					/*
					 * FacesContext.getCurrentInstance().getExternalContext()
					 * .getRequestMap().put("cdComuneRes", cdComune);
					 */
				}

				capComuniResidenza = recuperaCaps(cdProvincia, cdComune);
				if (!capComuniResidenza.isEmpty()) {
					cap = capComuniResidenza.get(0).getValue().toString();
					/*
					 * FacesContext.getCurrentInstance().getExternalContext()
					 * .getRequestMap().put("capPost", cap);
					 */
				}

				// stradePerCap = recuperaStradePerCap(cap);
			}
		}

	}

	public void comuniResidenzaValueChange(ValueChangeEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		int cdComune = 0;
		int cdProvincia = 0;
		String cap = "";
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {

				cdComune = Integer.parseInt(event.getNewValue().toString());
				/*
				 * FacesContext.getCurrentInstance().getExternalContext()
				 * .getRequestMap().put("cdComuneRes", cdComune);
				 */
				cdProvincia = rid.getProvinciaResidenza();

				capComuniResidenza = recuperaCaps(cdProvincia, cdComune);
				if (!capComuniResidenza.isEmpty()) {
					cap = capComuniResidenza.get(0).getValue().toString();

				}

			}
		}
	}

	public void capResidenzaValueChange(ValueChangeEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		String cap = "";
		if (!event.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else {
			if (null != event.getNewValue()) {

				cap = event.getNewValue().toString();

			}
		}
	}

	public void eliminaAnagraficaOnClick(ActionEvent e) {
		int idAnag = Integer.parseInt(e.getComponent().getAttributes()
				.get("idAnagrafica").toString());
		List<MembroRidDTO> recsToDelete = new ArrayList<MembroRidDTO>();

		for (MembroRidDTO rec : getRid().getMembri()) {
			if (rec.getIdAnagrafica() == idAnag) {
				recsToDelete.add(rec);

			}
		}
		if (!recsToDelete.isEmpty()) {
			getRid().getMembri().removeAll(recsToDelete);
		}
	}

	public void aggiungiAnagraficaOnClick(ActionEvent event)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		int sizeMembri = 0;
		String errore = null;

		AnagraficaDTO anag = RicercaAnagraficaBD.ricercaAnagrafica(getNrAnag());
		if (anag == null) {
			throw new ContradaExceptionNonBloccante(Errori.ANAG_NON_PRES);
		}

		if (rid.getMembri() != null) {
			sizeMembri = rid.getMembri().size();
		}

		addMembriFromAnagrafica(anag, rid.getMembri());

		if (rid.getMembri().size() == sizeMembri) {
			// tessere non presenti
			errore = LoadBundleLanguage
					.getMessage("errore.tessereConRidNonPresenti");
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, errore,
							errore));
		}

	}

	private boolean isInserimentoFromAnag() {

		String isFromInsertAnag = null;
		Map<String, String> maps = FacesUtils.getQueryString();
		if (maps != null && maps.containsKey(Costante.RID_FROM_INSERT_ANAG)) {

			isFromInsertAnag = maps.get(Costante.RID_FROM_INSERT_ANAG)
					.toString();
			if (isFromInsertAnag != null
					&& Boolean.parseBoolean(isFromInsertAnag) == true) {
				return true;

			}
		}

		return false;

	}

	private boolean verificaControlliFormali() {
		String errore = null;
		if (rid.getPaese() == null || rid.getPaese().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					"idPortlet:idForm:txtPaese",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Campo Obbligatorio", "Campo Obbligatorio"));
			return false;
		}
		rid.setPaese(rid.getPaese().toUpperCase().trim());

		if (rid.getCin() == null || rid.getCin().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					"idPortlet:idForm:txtCin",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Campo Obbligatorio", "Campo Obbligatorio"));

			return false;
		}
		rid.setCin(rid.getCin().toUpperCase().trim());

		if (rid.getCinAbi() == null && !rid.getCinAbi().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					"idPortlet:idForm:txtCinAbi",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Campo Obbligatorio", "Campo Obbligatorio"));
			return false;
		}

		rid.setCinAbi(rid.getCinAbi().toUpperCase().trim());
		rid.setNumeroCC(rid.getNumeroCC().toUpperCase().trim());

		VerificaCoordinateBancariePojo cord = new VerificaCoordinateBancariePojo();
		String codiceIban = rid.getPaese() + rid.getCin() + rid.getCinAbi()
				+ String.format("%5s", rid.getAbi()).replace(" ", "0")
				+ String.format("%5s", rid.getCab()).replace(" ", "0")
				+ String.format("%12s", rid.getNumeroCC()).replace(" ", "0");

		codiceIban = codiceIban.toUpperCase();
		if (!cord.verificaIban(codiceIban)) {

			errore = LoadBundleLanguage
					.getMessage("errore.codiceIbanNonCorretto");
			writeInfoMessage(TipoGravitaMessage.ERROR, errore);
			return false;

		}

		if (rid.getMembri().isEmpty()) {
			errore = LoadBundleLanguage.getMessage("errore.anagNonPres");

			writeInfoMessage(TipoGravitaMessage.ERROR, errore);
			return false;
		}

		return true;
	}

	private boolean isModificaRid() {

		if (modificaRid == null) {
			modificaRid = HelperSession.getFromRequest(Costante.PARM_MOD_RID) != null;
		}

		return modificaRid;

	}

	public String proseguiOnClick() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante, IOException {
		renderMessIban = false;

		rid.setUser(FacesUtils.getUser());

		if (!verificaControlliFormali()) {
			return null;
		}

		if (isRidFromInsertAnag()) {
			anag.setRid(rid);
			anag = GestioneAnagraficaBD.inserisciAnagraficaConRid(anag);

			rid.setNrRid(anag.getRid().getNrRid());
			popupRid = true;

		} else {
			// o sono in modifica oppure in inserimento
			if (isModificaRid()) {
				GestioneRidBD.aggiornaRid(getRid());
				FacesUtils.redirectToUrl("CercaRidModale.iface");
				return null;
			} else {
				// inserimento nuovo rid
				setRid(GestioneRidBD.inserisciRidConMembri(getRid()));
				popupRid = true;

			}
		}
		return null;
	}

	public String indietroOnClick() throws IOException {

		// HelperSession.removeFromSession(Costante.BACK_BEAN_INS_RID);
		FacesUtils.redirectToUrl("CercaRidModale.iface");
		return null;
	}

	private void addMembriFromAnagrafica(AnagraficaDTO anag,
			List<MembroRidDTO> membri) {
		List<TesseraDTO> tessere = anag.getTessere();
		boolean tesseraPresente;
		if (tessere == null) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							Errori.TESSERE_NON_PRES, Errori.TESSERE_NON_PRES));
			return;
		}

		if (membri == null) {
			membri = new ArrayList<MembroRidDTO>();
		}

		MembroRidDTO membro = null;
		for (TesseraDTO tessera : tessere) {

			tesseraPresente = false;

			if (it.contrada.enumcontrada.TipoIncasso.RID.getIncasso() == tessera
					.getIdTipoIncasso()) {

				for (MembroRidDTO membroItem : getRid().getMembri()) {
					if (membroItem.getIdTessera() == tessera.getIdTessera()) {
						tesseraPresente = true;
						break;
					}
				}

				if (tesseraPresente == false) {

					membro = new MembroRidDTO();
					membro.setCognome(anag.getCognome());
					membro.setNome(anag.getNome());
					membro.setQuota(tessera.getQuota());
					membro.setTessera(tessera.getDsTipoTessera());
					membro.setModalita(tessera.getDsTipoRateizzazione());
					membro.setIdAnagrafica(anag.getIdAnagrafica());
					membro.setIdTessera(tessera.getIdTessera());
					membri.add(membro);

				}
			}
		}

	}

	public void calcolaIban(ActionEvent e) {
		CalcolaCoordinateBancariePojo calcCor = new CalcolaCoordinateBancariePojo();
		if (rid.getPaese() == null || rid.getPaese().isEmpty()) {
			rid.setPaese("IT");
		}
		IbanDTO iban = calcCor.calcolaIban(rid.getPaese(), rid.getAbi(),
				rid.getCab(), rid.getNumeroCC().toUpperCase());
		rid.setCinAbi(iban.getCodiceBban().substring(0, 1));
		rid.setCin(iban.getCodiceControllo());
		rid.setPaese(iban.getCodicePaese());
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
					setNrAnag(anagrafica.getIdAnagrafica());
				} else {
					anagrafiche = RicercaAnagraficaUtil
							.getAnagraficaByAutocomplete(e.getNewValue()
									.toString());

					anagraficheItem = new ArrayList<SelectItem>();
					if (anagrafiche.isEmpty()) {
						setDsAnagraficaInRid("");

					}
					for (AnagraficaDTO anagrafica : anagrafiche) {
						anagraficheItem.add(new SelectItem(anagrafica,
								anagrafica.getIntestatario()));

					}

				}
			}
		}
	}

	public void closeAnagraficaPopup(ActionEvent ev) {
		setVisibleAnagraficaPopup(false);
	}

	public void closeRidPopup(ActionEvent e) throws IOException {
		popupRid = false;
		if (isInserimentoFromAnag()) {
			FacesUtils.redirectToUrl("../Anagrafica/GestioneAnagrafica.iface");
		} else {
			FacesUtils.redirectToUrl("InserimentoRid.iface");
		}
	}

	public void openAnagraficaPopup(ActionEvent ev) {
		setVisibleAnagraficaPopup(true);
	}

	public void updateListVie(ValueChangeEvent e)
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
					StradaDTO strada = (StradaDTO) autoComplete
							.getSelectedItem().getValue();
					getRid().setIdStrada(strada.getIdStrada());
				} else {
					String matchVia = e.getNewValue().toString();
					vie = RicercaStradaBD.getStradaPerCapViaParziale(getRid()
							.getCapPost(), matchVia);
					stradePerCap = new ArrayList<SelectItem>();
					if (vie.isEmpty()) {
						setDsStrada("");

					}
					for (StradaDTO via : vie) {
						stradePerCap
								.add(new SelectItem(via, via.getDsStrada()));

					}

				}

			}
		}
	}
}
