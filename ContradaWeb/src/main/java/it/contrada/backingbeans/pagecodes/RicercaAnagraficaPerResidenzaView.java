package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.model.StradarioBean;
import it.contrada.businessdelegate.GestioneAnagraficaBD;
import it.contrada.businessdelegate.GestioneGestoreBD;
import it.contrada.businessdelegate.GestioneStradarioBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaProvinciaBD;
import it.contrada.businessdelegate.RicercaStradaBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.CapDTO;
import it.contrada.dto.GestoreDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.ProvinciaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.FacesUtils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class RicercaAnagraficaPerResidenzaView {

	private List<SelectItem> provincieItems;

	private List<SelectItem> capItems;
	private Integer cdProvincia;
	private CapDTO cap;
	private StradaDTO strada;
	private List<CapDTO> caps;
	private String dsCap;

	private String note;
	private boolean renderedNote;
	private String localita;
	private List<AnagraficaDTO> anags;
	private List<SelectItem> vieItems;
	private List<StradaDTO> strade;

	private final Integer SIENA = 52;
	private boolean isRenderedAnags;
	private Integer nrAnags;
	private StradarioBean stradarioBean;
	private boolean assegnaGestore;
	private List<SelectItem> gestoriItems;
	private List<SelectItem> filroGestoriItems;
	private List<AnagraficaDTO> anagsConGestore = new ArrayList<AnagraficaDTO>();
	private Integer filtroGestore;
	private boolean renderConferma;
	private Integer filtroIncasso;
	private List<SelectItem> filtroIncassoItems;
	private List<SelectItem> localitaItem;
	private Integer idLocalita;
	
	
	public Integer getIdLocalita() {
		return idLocalita;
	}

	public void setIdLocalita(Integer idLocalita) {
		this.idLocalita = idLocalita;
	}

	public List<SelectItem> getLocalitaItem() {
		return localitaItem;
	}

	public void setLocalitaItem(List<SelectItem> localitaItem) {
		this.localitaItem = localitaItem;
	}

	public List<SelectItem> getFiltroIncassoItems() {
		return filtroIncassoItems;
	}

	public Integer getFiltroIncasso() {
		return filtroIncasso;
	}

	public void setFiltroIncasso(Integer filtroIncasso) {
		this.filtroIncasso = filtroIncasso;
	}

	public void setFiltroGestore(Integer filtroGestore) {
		this.filtroGestore = filtroGestore;
	}

	public boolean isRenderConferma() {
		return renderConferma;
	}

	public Integer getFiltroGestore() {
		return filtroGestore;
	}

	public void setFiltroGestore(int filtroGestore) {
		this.filtroGestore = filtroGestore;
	}

	public List<SelectItem> getFilroGestoriItems() {
		return filroGestoriItems;
	}

	public List<SelectItem> getGestoriItems() {
		return gestoriItems;
	}

	public boolean isAssegnaGestore() {
		return assegnaGestore;
	}

	public void setAssegnaGestore(boolean assegnaGestore) {
		this.assegnaGestore = assegnaGestore;
	}

	public StradarioBean getStradarioBean() {
		stradarioBean = (StradarioBean) FacesUtils.findBean("stradarioBean");
		return stradarioBean;

	}

	public Integer getNrAnags() {
		return nrAnags;
	}

	public boolean isRenderedAnags() {
		return isRenderedAnags;
	}

	public void setStradarioBean(StradarioBean stradarioBean) {
		this.stradarioBean = stradarioBean;
	}

	public List<SelectItem> getVieItems() {
		return vieItems;
	}

	public void setVieItems(List<SelectItem> vieItems) {
		this.vieItems = vieItems;
	}

	public List<AnagraficaDTO> getAnags() {
		return anags;
	}

	public void setAnags(List<AnagraficaDTO> anags) {
		this.anags = anags;
	}

	public String getLocalita() {
		return localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isRenderedNote() {
		return renderedNote;
	}

	public void setRenderedNote(boolean renderedNote) {
		this.renderedNote = renderedNote;
	}

	public List<SelectItem> getCapItems() {
		return capItems;
	}

	public String getDsCap() {
		return dsCap;
	}

	public void setDsCap(String dsCap) {
		this.dsCap = dsCap;
	}

	public Integer getCdProvincia() {
		return cdProvincia;
	}

	public void setCdProvincia(Integer cdProvincia) {
		this.cdProvincia = cdProvincia;
	}

	public List<SelectItem> getProvincieItems() {
		return provincieItems;
	}

	public RicercaAnagraficaPerResidenzaView()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		inizializzaProvince();
		inizializzaGestori();
		inizializzaIncassi();
		iniziallizzaLocalita();
		setCdProvincia(SIENA);

	}

	private void inizializzaProvince() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		RicercaProvinciaBD provBD = new RicercaProvinciaBD();
		List<ProvinciaDTO> provs = provBD.elenca();
		provincieItems = new ArrayList<SelectItem>();
		for (ProvinciaDTO prov : provs) {
			provincieItems.add(new SelectItem(prov.getCdProvincia(), prov
					.getDsProvincia()));
		}
	}

	public void cercaOnClick(ActionEvent a) {
		renderedNote = false;

		ParmResidenzaDTO parm = new ParmResidenzaDTO();

		if (cap != null) {
			parm.setCdCap(cap.getCdCap());
			parm.setCdComune(cap.getCdComune());
			parm.setCdProvincia(cap.getCdProv());
		}
		parm.setIdGestore(null);

		if (filtroIncasso != -1) {
			parm.setIdTipoIncasso(filtroIncasso);
		}

		if (filtroGestore != -2) {
			parm.setIdGestore(filtroGestore);
		}
		// String loc = getStradarioBean().getDsLocalita();
	
		if (idLocalita != null && idLocalita != -1) {
			parm.setIdLocalita(idLocalita);
		}
		String dsStrada = getStradarioBean().getDsStrada();
		if (dsStrada != null && !dsStrada.isEmpty() && strada != null
				&& strada.getIdStrada() != 0) {
			parm.setIdStrada(strada.getIdStrada());
		}

		try {
			anags = RicercaAnagraficaBD.ricercaPerResidenza(parm);
			isRenderedAnags = !(anags == null || anags.isEmpty());
			if (anags != null) {
				nrAnags = anags.size();
			}

		} catch (ContradaExceptionBloccante e) {
			// TODO Auto-generated catch block
			note = e.getMessage();
			renderedNote = true;
		} catch (ContradaExceptionNonBloccante e) {
			// TODO Auto-generated catch block
			note = e.getMessage();
			renderedNote = true;
		}

	}

	private void inizializzaGestori() throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		List<GestoreDTO> gestori = GestioneGestoreBD.elencaGestori();
		gestoriItems = new ArrayList<SelectItem>();
		filroGestoriItems = new ArrayList<SelectItem>();
		filroGestoriItems.add(new SelectItem(-2, "Mostra Tutto"));
		filroGestoriItems.add(new SelectItem(0, "Gestore non assegnato"));
		filroGestoriItems.add(new SelectItem(-1, "Gestore assegnato"));
		for (GestoreDTO gestore : gestori) {
			gestoriItems.add(new SelectItem(gestore.getIdGestore(), gestore
					.getCognome()
					+ " " + gestore.getNome()));
			filroGestoriItems.add(new SelectItem(gestore.getIdGestore(),
					gestore.getCognome() + " " + gestore.getNome()));
		}

	}

	private void inizializzaIncassi() throws ContradaExceptionNonBloccante,
			ContradaExceptionBloccante {
		List<TipoIncassoDTO> incassi = RicercaIncassoBD.elencaTipoIncasso();
		filtroIncassoItems = new ArrayList<SelectItem>();
		filtroIncassoItems.add(new SelectItem(-1, "Mostra Tutto"));

		for (TipoIncassoDTO incasso : incassi) {
			filtroIncassoItems.add(new SelectItem(incasso.getIdTipoIncasso(),
					incasso.getDsTipoIncasso()));

		}

	}

	private void iniziallizzaLocalita()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		
		idLocalita=-1;
		localitaItem = new ArrayList<SelectItem>();
		localitaItem
				.add(new SelectItem(-1, "--- selezionare una località ---"));
		if (cap != null) {
			List<LocalitaDTO> locs = RicercaStradaBD.recuperaLocalitaPerCap(cap
					.getCdCap(), cap.getCdProv(), cap.getCdComune());

			for (LocalitaDTO loc : locs) {
				localitaItem.add(new SelectItem(loc.getIdLocalita(), loc
						.getDsLocalita()));
			}
		}
	}
	
	public void localitaValueChange(ValueChangeEvent e) {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			strada=null;
			getStradarioBean().setDsStrada(null);
		}
	}

	public void annulla(ActionEvent e) {
		AnagraficaDTO anag = (AnagraficaDTO) e.getComponent().getAttributes()
				.get("anagrafica");
		anag.setModicaGestore(false);
	}

	public void conferma(ActionEvent e) {
		AnagraficaDTO anag = (AnagraficaDTO) e.getComponent().getAttributes()
				.get("anagrafica");
		for (SelectItem gestoreItem : gestoriItems) {
			if (gestoreItem.getValue() != null
					&& gestoreItem.getValue().toString().equals(
							anag.getIdGestore().toString())) {
				anag.setDsGestore(gestoreItem.getLabel());
				break;
			}
		}

		if (anagsConGestore.contains(anag)) {
			anagsConGestore.remove(anag);
		}
		anagsConGestore.add(anag);
		anag.setModicaGestore(false);
		renderConferma = !anagsConGestore.isEmpty();
	}

	public void assegnaGestoreOnClick(ActionEvent e) {
		AnagraficaDTO anag = (AnagraficaDTO) e.getComponent().getAttributes()
				.get("anagrafica");
		anag.setModicaGestore(true);
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
				strada = null;
				
				getStradarioBean().setDsLocalita(null);
				getStradarioBean().setDsStrada(null);
				
				if (autoComplete.getSelectedItem() != null) {
					CapDTO newCap = (CapDTO) autoComplete.getSelectedItem()
							.getValue();
					cap = newCap;
					iniziallizzaLocalita();
				} else {
					String matchCap = e.getNewValue().toString();
					caps = RicercaStradaBD.recuperaCapParziale(matchCap,
							getCdProvincia());
					capItems = new ArrayList<SelectItem>();
					if (caps.isEmpty()) {
						// stradarioBean.setDsCap(null);

					}
					for (CapDTO cap : caps) {
						capItems.add(new SelectItem(cap, cap.getDsCap()));

					}

				}

			}
		}

	}

	public void updateListStrade(ValueChangeEvent e)
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
					StradaDTO stradaNew = (StradaDTO) autoComplete
							.getSelectedItem().getValue();
					strada = stradaNew;
				} else {
					String matchVia = e.getNewValue().toString();
					
					if (idLocalita != null && idLocalita != -1) {
						strade = RicercaStradaBD.recuperaPerCapLocViaParziale(
								cap.getCdCap(), idLocalita, matchVia);
					} else {
						strade = RicercaStradaBD.getStradaPerCapViaParziale(cap
								.getCdCap(), matchVia);
					}
					vieItems = new ArrayList<SelectItem>();
					if (strade.isEmpty()) {
						// stradarioBean.setDsStrada(null);

					}
					for (StradaDTO strada : strade) {
						vieItems.add(new SelectItem(strada, strada
								.getDsStrada()));

					}

				}

			}
		}

	}

	public void confermaOnClick(ActionEvent a)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {
			GestioneAnagraficaBD.aggiornaGestore(anagsConGestore);
			this.note = "Operazione eseguita correttamente";
			renderedNote = true;
			renderConferma = false;

		} catch (ContradaExceptionBloccante e) {
			// TODO Auto-generated catch block
			note = e.getMessage();
			renderedNote = true;
		} catch (ContradaExceptionNonBloccante e) {
			// TODO Auto-generated catch block
			note = e.getMessage();
			renderedNote = true;
		}

	}

}
