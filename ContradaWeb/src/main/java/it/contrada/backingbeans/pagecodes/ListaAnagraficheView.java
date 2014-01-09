package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaCaricaBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.CaricaTesseraDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class ListaAnagraficheView {
	private List<SelectItem> tipoTessereItems;
	private List<SelectItem> caricheItems;
	private List<TesseraStampataDTO> anagrafiche;
	private boolean visibleAnagrafiche;
	private List<SelectItem> incassiItems;
	private Integer idTipoTessera;
	private Integer idTipoIncasso;
	private Integer idTipoCarica;
	private int anno;
	private List<SelectItem> annoItems;
	private boolean renderNote;
	private int nrAnagrafiche;

	public int getNrAnagrafiche() {
		nrAnagrafiche = 0;
		if (anagrafiche != null) {
			nrAnagrafiche = anagrafiche.size();
		}
		return nrAnagrafiche;
	}

	public boolean isRenderNote() {
		return renderNote;
	}

	public List<SelectItem> getAnnoItems() {
		return annoItems;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<SelectItem> getCaricheItems() {
		return caricheItems;
	}

	public List<TesseraStampataDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public Integer getIdTipoTessera() {
		return idTipoTessera;
	}

	public void setIdTipoTessera(Integer idTipoTessera) {
		this.idTipoTessera = idTipoTessera;
	}

	public Integer getIdTipoIncasso() {
		return idTipoIncasso;
	}

	public void setIdTipoIncasso(Integer idTipoIncasso) {
		this.idTipoIncasso = idTipoIncasso;
	}

	public Integer getIdTipoCarica() {
		return idTipoCarica;
	}

	public void setIdTipoCarica(Integer idTipoCarica) {
		this.idTipoCarica = idTipoCarica;
	}

	public List<SelectItem> getIncassiItems() {
		return incassiItems;
	}

	public boolean isVisibleAnagrafiche() {
		visibleAnagrafiche = anagrafiche != null && !anagrafiche.isEmpty();
		return visibleAnagrafiche;
	}

	public List<SelectItem> getTipoTessereItems() {
		return tipoTessereItems;
	}

	public ListaAnagraficheView() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub

		idTipoTessera = 1;

		initDDLAnni();
		initDDLTessere();
		initDDLTipoIncasso();
		initDDLCariche();
	}

	public void TipoTesseraValueChange(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			initDDLCariche();

		}
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		GregorianCalendar gCalendar = new GregorianCalendar();
		int annoInCorso = gCalendar.get(GregorianCalendar.YEAR);
		annoItems = new ArrayList<SelectItem>();

		int anniRendicontabili = Integer.parseInt(Configuration
				.getProperty("anniStampaTessere"));
		for (int i = 0; i <= anniRendicontabili; i++) {
			annoItems.add(new SelectItem(Integer.valueOf(annoInCorso - i),
					Integer.toString(annoInCorso - i)));
		}

	}

	public void confermaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante,
			IOException {

		if (idTipoIncasso == -1) {
			idTipoIncasso = null;
		}
		if (idTipoCarica == -1) {
			idTipoCarica = null;
		}

		anagrafiche = RicercaTipoTesseraBD.stampaTabulato(idTipoTessera,
				idTipoIncasso, idTipoCarica, anno);

	

	}

	private void initDDLTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoTessereItems = new ArrayList<SelectItem>();
		List<TipoTesseraDTO> tessere = RicercaTipoTesseraBD.elencaTipoTessera();
		for (TipoTesseraDTO tes : tessere) {
			tipoTessereItems.add(new SelectItem(tes.getIdTipoTessera(), tes
					.getDsTipoTessera()));
		}

	}

	private void initDDLCariche() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		idTipoCarica = -1;
		caricheItems = new ArrayList<SelectItem>();
		List<CaricaTesseraDTO> cariche = RicercaCaricaBD
				.ricercaPerTessera(idTipoTessera);
		caricheItems.add(new SelectItem(-1, ""));
		for (CaricaTesseraDTO carica : cariche) {
			caricheItems.add(new SelectItem(carica.getIdCarica(), carica
					.getDsCarica()));
		}

	}

	private void initDDLTipoIncasso() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		idTipoIncasso = -1;
		incassiItems = new ArrayList<SelectItem>();
		List<TipoIncassoDTO> incassi = RicercaIncassoBD.elencaTipoIncasso();
		incassiItems.add(new SelectItem(-1, ""));
		for (TipoIncassoDTO incasso : incassi) {
			incassiItems.add(new SelectItem(incasso.getIdTipoIncasso(), incasso
					.getDsTipoIncasso()));
		}

	}

	private String lookUpTessera(Integer idTipoTessera) {
		if (tipoTessereItems != null) {
			for (SelectItem item : tipoTessereItems) {
				if (item.getValue().equals(idTipoTessera)) {
					return item.getLabel();
				}
			}
		}
		return "";
	}

}
