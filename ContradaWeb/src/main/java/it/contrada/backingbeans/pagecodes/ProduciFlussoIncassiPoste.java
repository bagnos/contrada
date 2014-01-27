package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestionePosteBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.enumcontrada.TipoIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.poste.IncassoPostaDTO;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.ConverterContrada;
import it.contrada.web.util.LoadBundleLanguage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class ProduciFlussoIncassiPoste {

	private boolean visibleListPoste;
	private List<IncassoPostaDTO> incassiPoste;
	private int nrDisposizioni;
	private java.util.Date dtScadenza;
	private int anno;
	private boolean disabledPrepara;
	private boolean visibleExportPoste;
	private String nomeFile;
	private String note;
	private boolean disabledElimina;
	private boolean disabledConferma;
	private FlussoIncassoPostaDTO flusso;
	private int tipoFlusso;
	private List<SelectItem> pagamenti;
	private List<Integer> pagamentiSelected;
	private List<Integer> tessereManuali;
	private List<TesseraDTO> tessere;
	private boolean visibleTessere;
	private Integer idAnagrafica;

	public boolean isVisibleTessere() {
		visibleTessere = (tessere != null && !tessere.isEmpty());
		return visibleTessere;
	}

	public List<TesseraDTO> getTessere() {
		return tessere;
	}

	public Integer getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Integer idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}

	public List<Integer> getTessereManuali() {
		return tessereManuali;
	}

	public void setTessereManuali(List<Integer> tessereManuali) {
		this.tessereManuali = tessereManuali;
	}

	public List<Integer> getPagamentiSelected() {
		return pagamentiSelected;
	}

	public void setPagamentiSelected(List<Integer> pagamentiSelected) {
		this.pagamentiSelected = pagamentiSelected;
	}

	public List<SelectItem> getPagamenti() {
		return pagamenti;
	}

	public int getTipoFlusso() {
		return tipoFlusso;
	}

	public void setTipoFlusso(int tipoFlusso) {
		this.tipoFlusso = tipoFlusso;
	}

	public boolean isDisabledConferma() {
		return disabledConferma;
	}

	public void setDisabledConferma(boolean disabledConferma) {
		this.disabledConferma = disabledConferma;
	}

	public boolean isDisabledElimina() {
		return disabledElimina;
	}

	public void setDisabledElimina(boolean disabledElimina) {
		this.disabledElimina = disabledElimina;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public boolean isVisibleExportPoste() {
		return visibleExportPoste;
	}

	public void setVisibleExportPoste(boolean visibleExportPoste) {
		this.visibleExportPoste = visibleExportPoste;
	}

	public boolean isDisabledPrepara() {
		return disabledPrepara;
	}

	public void setDisabledPrepara(boolean disabledPrepara) {
		this.disabledPrepara = disabledPrepara;
	}

	public boolean isVisibleListPoste() {
		return visibleListPoste;
	}

	public void setVisibleListPoste(boolean visibleListPoste) {
		this.visibleListPoste = visibleListPoste;
	}

	public List<IncassoPostaDTO> getIncassiPoste() {
		return incassiPoste;
	}

	public void setIncassiPoste(List<IncassoPostaDTO> incassiPoste) {
		this.incassiPoste = incassiPoste;
	}

	public int getNrDisposizioni() {
		return nrDisposizioni;
	}

	public void setNrDisposizioni(int nrDisposizioni) {
		this.nrDisposizioni = nrDisposizioni;
	}

	public java.util.Date getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(java.util.Date dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public ProduciFlussoIncassiPoste() throws NumberFormatException,
			ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		setAnno(Calendar.getInstance().get(Calendar.YEAR));

		GregorianCalendar dtScadenzaGreg = (GregorianCalendar) GregorianCalendar
				.getInstance();
		int ggScadenza = Integer.parseInt(Configuration
				.getProperty("giorniScadenza"));
		dtScadenzaGreg.add(Calendar.DAY_OF_MONTH, ggScadenza);
		setDtScadenza(new java.util.Date(dtScadenzaGreg.getTimeInMillis()));

		String strData = format.format(new java.util.Date(Calendar
				.getInstance().getTimeInMillis()));
		String pathFile = Configuration.getProperty("pathExportIncassiPoste");
		setNomeFile(pathFile.replace("<anno>", Integer.toString(anno)).replace(
				"<ggMMyyyy>", strData));
		setDisabledElimina(true);
		setDisabledConferma(true);

		initTipoIncasso();
	}

	public void preparaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<Integer> tipoTessere = new ArrayList<Integer>();
		tipoTessere.add(1);
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");

		if (tipoFlusso == 1) {
			// solo pagamenti di bollettini e mav
			pagamentiSelected = new ArrayList<Integer>();
			pagamentiSelected.add(TipoIncasso.BOLLETTINO.getIncasso());
			pagamentiSelected.add(TipoIncasso.MAV.getIncasso());
		}
		if (tipoFlusso == 3) {
			pagamentiSelected = null;
		}

		nomeFile = String.format("POSTE%s", format.format(getDtScadenza()));

		// tessere manuali
		tessereManuali = null;
		if (tessere != null && !tessere.isEmpty()) {
			tessereManuali = new ArrayList<Integer>();
			for (TesseraDTO tes : tessere) {
				tessereManuali.add(tes.getIdTessera());
			}
		}

		flusso = GestionePosteBD.produciFlussiIncassoPoste(getAnno(),
				ConverterContrada.valueOf(getDtScadenza()), tipoTessere,
				nomeFile, pagamentiSelected, tessereManuali);

		if (flusso == null || flusso.getIncassi().isEmpty()) {
			setNote(LoadBundleLanguage.getMessage("DATI_NON_PRESENTI"));
		} else {
			setIncassiPoste(flusso.getIncassi());
			setVisibleListPoste(!incassiPoste.isEmpty());
			setDisabledConferma(incassiPoste.isEmpty());
			setDisabledElimina(false);
			setVisibleExportPoste(true);
			setNrDisposizioni(flusso.getIncassi().size());
		}

	}

	public void confermaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		visibleExportPoste = true;
		disabledPrepara = true;

		flusso.setTxNomeFile(nomeFile);

		flusso = GestionePosteBD.confermaInvioFlussiIncassoPoste(flusso);
		setVisibleExportPoste(true);
		setDisabledElimina(false);
		setDisabledConferma(true);

	}

	public void eliminaAnagraficaOnClick(ActionEvent e) {
		TesseraDTO tes = (TesseraDTO) e.getComponent().getAttributes()
				.get("tessera");
		tessere.remove(tes);
	}

	public void aggiungiAnagraficaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (tessere == null) {
			tessere = new ArrayList<TesseraDTO>();
		}

		if (idAnagrafica != null) {
			AnagraficaDTO anag = RicercaAnagraficaBD
					.ricercaAnagrafica(idAnagrafica);
			if (anag.getTessere() != null) {
				for (TesseraDTO tes : anag.getTessere()) {
					// per ora solo per il protettorato;
					if (tes.getIdTipoTessera() == 1) {
						// verifico se la tessera è già presenta
						for (TesseraDTO tesPresente : tessere) {
							if (tesPresente.getIdTessera() == tes
									.getIdTessera()) {
								// tessera già presente
								return;
							}
						}

						tes.setCognome(anag.getCognome());
						tes.setNome(anag.getNome());
						tessere.add(0, tes);
					}
				}
			}
			idAnagrafica=null;
		}
	}

	public void eliminaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		GestionePosteBD.eliminaFlusso(flusso.getIdFlusso());
		flusso = null;
		setDisabledConferma(true);
		setDisabledPrepara(false);
		setDisabledElimina(true);
		setVisibleListPoste(false);
		setVisibleExportPoste(false);

	}

	private void initTipoIncasso() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		pagamenti = new ArrayList<SelectItem>();
		List<TipoIncassoDTO> tipoIncassi = RicercaIncassoBD.elencaTipoIncasso();

		for (TipoIncassoDTO tt : tipoIncassi) {
			pagamenti.add(new SelectItem(tt.getIdTipoIncasso(), tt
					.getDsTipoIncasso()));
		}
		tipoFlusso = 1;
	}

}
