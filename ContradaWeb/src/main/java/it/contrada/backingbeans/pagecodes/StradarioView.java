package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.model.StradarioBean;
import it.contrada.businessdelegate.GestioneStradarioBD;
import it.contrada.businessdelegate.RicercaComuneBD;
import it.contrada.businessdelegate.RicercaNazioneBD;
import it.contrada.businessdelegate.RicercaProvinciaBD;
import it.contrada.businessdelegate.RicercaStradaBD;
import it.contrada.dto.CapDTO;
import it.contrada.dto.ComuneDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.NazioneDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class StradarioView extends BaseView {

	private StradarioBean stradarioBean;

	private List<SelectItem> capItems;
	private List<CapDTO> caps;
	private List<SelectItem> provincieItem;
	private String note;
	private boolean renderedNote;
	private String localita;
	private List<SelectItem> localitaItem;
	private boolean disabledItemLoc;
	private boolean disabledStrada;
	private List<SelectItem> comuneItem;
	private final int MAX_CAP = 99999;
	private final int MIN_CAP = 0;
	private List<SelectItem> statiItems;
	

	public List<SelectItem> getStatiItems() {
		return statiItems;
	}

	public List<SelectItem> getComuneItem() {
		return comuneItem;
	}

	public boolean isDisabledStrada() {
		disabledStrada = getStradarioBean().getCap() == null;

		return disabledStrada;
	}

	public boolean isDisabledItemLoc() {

		if (localitaItem == null || localitaItem.isEmpty()) {
			disabledItemLoc = true;
		} else {
			disabledItemLoc = false;
		}

		return disabledItemLoc;
	}

	public List<SelectItem> getLocalitaItem() {
		return localitaItem;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public boolean isRenderedNote() {
		return renderedNote;
	}

	public String getNote() {
		return note;
	}

	public StradarioBean getStradarioBean() {
		return stradarioBean;
	}

	public void setStradarioBean(StradarioBean stradarioBean) {
		this.stradarioBean = stradarioBean;
	}

	public List<SelectItem> getProvincieItem() {
		return provincieItem;
	}

	public List<SelectItem> getCapItems() {
		return capItems;
	}

	public StradarioView() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		// via
		iniziallizzaStati();
		iniziallizzaProvincia();

	}

	public void provinciaChange(ValueChangeEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!ev.getPhaseId().equals(PhaseId.INVOKE_APPLICATION)) {
			ev.setPhaseId(PhaseId.INVOKE_APPLICATION);
			ev.queue();
		} else {
			if (ev.getNewValue() != null) {
				resetStradarioBean();
				if (getStradarioBean().getTipoInserimento() == 3) {
					// INSERIMENTO CAP
					iniziallizzaComuni((Integer) ev.getNewValue());
				}
			}

		}

	}

	public void tipoCensimentoValueChange(ValueChangeEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!ev.getPhaseId().equals(PhaseId.INVOKE_APPLICATION)) {
			ev.setPhaseId(PhaseId.INVOKE_APPLICATION);
			ev.queue();
		} else {
			if (ev.getNewValue() != null) {
				resetStradarioBean();
				if (getStradarioBean().getTipoInserimento() == 3) {
					// INSERIMENTO CAP
					iniziallizzaComuni(getStradarioBean().getCdProvincia());
				}
			}
		}

	}

	private void resetStradarioBean() {
		getStradarioBean().setCap(null);
		getStradarioBean().setCdLocalita(null);
		getStradarioBean().setDsCap(null);
		getStradarioBean().setDsLocalita(null);
		getStradarioBean().setDsStrada(null);
		getStradarioBean().setCdComune(null);
		note = null;
		localitaItem = null;
	}

	public void confermaOnClick(ActionEvent a) {
		try {
			renderedNote = false;
			int iCap = 0;

			if (stradarioBean.getTipoInserimento() == 1) {
				// censimento strada
				StradaDTO strada = new StradaDTO();
				strada.setCdCap(getStradarioBean().getCap().getCdCap());
				strada.setCdComune(getStradarioBean().getCap().getCdComune());
				strada.setCdProvincia(getStradarioBean().getCap().getCdProv());
				// strada.setDsLocalita(getStradarioBean().getDsLocalita());
				if (getStradarioBean().getCdLocalita() == 0) {
					strada.setIdLocalita(null);
				} else {
					strada.setIdLocalita(getStradarioBean().getCdLocalita());
				}
				strada.setDsStrada(getStradarioBean().getDsStrada());

				GestioneStradarioBD.inserisciStrada(strada);

			} else if (stradarioBean.getTipoInserimento() == 2) {
				// censimento località
				LocalitaDTO loc = new LocalitaDTO();
				loc.setCdCap(getStradarioBean().getCap().getCdCap());
				loc.setCdComune(getStradarioBean().getCap().getCdComune());
				loc.setCdProvincia(getStradarioBean().getCap().getCdProv());
				loc.setDsLocalita(getStradarioBean().getDsLocalita());
				GestioneStradarioBD.inserisciLocalita(loc);
			} else if (stradarioBean.getTipoInserimento() == 3) {
				// cap
				if (isValidCap()) {

					CapDTO cap = new CapDTO();
					cap.setCdCap(String.format("%5s",
							getStradarioBean().getCdCap().toString())
							.replaceAll(" ", "0"));
					cap.setCdComune(getStradarioBean().getCdComune());
					cap.setCdProv(getStradarioBean().getCdProvincia());
					GestioneStradarioBD.inserisciCap(cap);
				} else {
					return;
				}
			}
			else if (stradarioBean.getTipoInserimento()==4)
			{
				ProvinciaDTO prov=new ProvinciaDTO();
				prov.setCdIsoStato(stradarioBean.getCdStato());
				prov.setDsProvincia(stradarioBean.getDsProvincia());
				prov.setCdSiglaProv(stradarioBean.getCdSiglaProvincia());
				prov.setCdCap(stradarioBean.getCdCap());
				if (stradarioBean.getCdStato()!="IT")
				{
					prov.setCdSiglaProv("");
				}
				GestioneStradarioBD.inserisciProvincia(prov);
				iniziallizzaProvincia();
				
 				
			}
			note = "Operazione eseguita correttamente";
		} catch (ContradaExceptionBloccante e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			note = e.getMessage();
		} catch (ContradaExceptionNonBloccante e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			note = e.getMessage();
		}
		renderedNote = true;
	}

	private void iniziallizzaProvincia() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		RicercaProvinciaBD provincia = new RicercaProvinciaBD();
		List<ProvinciaDTO> provincie = provincia.elenca();
		provincieItem = new ArrayList<SelectItem>();
		for (ProvinciaDTO prv : provincie) {
			provincieItem.add(new SelectItem(prv.getCdProvincia(), prv
					.getDsProvincia()));
		}

	}

	private void iniziallizzaStati() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		RicercaNazioneBD ricNazione=new RicercaNazioneBD();
		List<NazioneDTO> nazioni = ricNazione.elencaNazione();
		statiItems = new ArrayList<SelectItem>();
		for (NazioneDTO naz : nazioni) {
			statiItems.add(new SelectItem(naz.getCdNazione(), naz.getDsNazione()));
		}

	}

	private boolean isValidCap() {
		int iCap = 0;
		if (getStradarioBean().getCdCap() == null
				|| getStradarioBean().getCdCap().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(
					"idPortlet:idForm:cdCap",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Cap Obbligatorio", null));
			return false;
		}
		try {
			iCap = Integer.parseInt(getStradarioBean().getCdCap());
		} catch (NumberFormatException e) {
			FacesContext.getCurrentInstance().addMessage(
					"idPortlet:idForm:cdCap",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Cap deve essere un numero", null));
			return false;
		}
		if (iCap <= MIN_CAP || iCap > MAX_CAP) {
			FacesContext.getCurrentInstance().addMessage(
					"idPortlet:idForm:cdCap",
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Cap deve essere un numero >0 e <99999", null));
			return false;
		}
		return true;

	}

	private void iniziallizzaComuni(int cdProvincia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		List<ComuneDTO> comuni = RicercaComuneBD
				.ricercaPerProvincia(cdProvincia);
		comuneItem = new ArrayList<SelectItem>();
		for (ComuneDTO com : comuni) {
			comuneItem
					.add(new SelectItem(com.getCdComune(), com.getDsComune()));
		}

	}

	private void iniziallizzaLocalita() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		getStradarioBean().setCdLocalita(null);
		if (getStradarioBean().getTipoInserimento() == 1) {
			if (getStradarioBean().getCap() != null) {
				List<LocalitaDTO> locs = RicercaStradaBD
						.recuperaLocalitaPerCap(getStradarioBean().getCap()
								.getCdCap(),
								stradarioBean.getCap().getCdProv(),
								stradarioBean.getCap().getCdComune());
				localitaItem = new ArrayList<SelectItem>();
				localitaItem.add(new SelectItem(null,
						"--- selezionare una località ---"));
				for (LocalitaDTO loc : locs) {
					localitaItem.add(new SelectItem(loc.getIdLocalita(), loc
							.getDsLocalita()));
				}
			}
		}

	}

	public void updateListCap(ValueChangeEvent e)
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
					CapDTO newCap = (CapDTO) autoComplete.getSelectedItem()
							.getValue();
					getStradarioBean().setCap(newCap);
					iniziallizzaLocalita();

				} else {
					String matchCap = e.getNewValue().toString();
					caps = RicercaStradaBD.recuperaCapParziale(matchCap,
							getStradarioBean().getCdProvincia());
					capItems = new ArrayList<SelectItem>();
					if (caps.isEmpty()) {
						getStradarioBean().setDsCap("");

					}
					for (CapDTO cap : caps) {
						capItems.add(new SelectItem(cap, cap.getDsCap()));

					}

				}

			}
		}

	}

}
