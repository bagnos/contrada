package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestionePosteBD;
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
			ContradaExceptionBloccante {
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
	}

	public void preparaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<Integer> tipoTessere = new ArrayList<Integer>();
		tipoTessere.add(1);
		SimpleDateFormat format=new SimpleDateFormat("ddMMyyyy");
		
		nomeFile=String.format("POSTE%s", format.format(getDtScadenza()));
		
		flusso = GestionePosteBD.produciFlussiIncassoPoste(getAnno(),
				ConverterContrada.valueOf(getDtScadenza()), tipoTessere,nomeFile);

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

	public void eliminaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		GestionePosteBD.eliminaFlusso(flusso.getIdFlusso());
		flusso=null;
		setDisabledConferma(true);
		setDisabledPrepara(false);
		setDisabledElimina(true);
		setVisibleListPoste(false);
		setVisibleExportPoste(false);

	}

}
