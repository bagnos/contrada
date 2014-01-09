package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.FlussoEsito;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.businessdelegate.RicercaFlussoBD;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.DisposizioneIncassoRidRicezioneDTO;
import it.contrada.preautrid.dto.DisposizionePreautRicezioneDTO;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.ContradaSelectItem;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;

public class RiceviAllArchivi {

	private boolean visibleListRid;
	private List<DisposizionePreautRicezioneDTO> rids;
	private List<DisposizionePreautRicezioneDTO> ridsFiltrati;
	private int nrDisposizioni;
	private String note;
	private boolean disabledRicevi;
	private String nomeFile;
	private String onClickBrowse;
	private String pathUpload;
	private String file;
	private boolean visibleEsiti;
	private List<FlussoEsito> esiti;
	private String intEsitiPrec;
	private int nrEsiti;
	private SimpleDateFormat formatddMMyy;
	private SimpleDateFormat formatddMMyyhhmm;
	private List<TipoCasualiPreautDTO> causali;
	private List<SelectItem> causaliItem = null;
	private int causale;
	
	private FileInfo currentFile;
	private int fileProgress;

	public FileInfo getCurrentFile() {
		return currentFile;
	}

	public int getFileProgress() {
		return fileProgress;
	}

	public List<SelectItem> getCausaliItem() {
		return causaliItem;
	}

	public void setCausaliItem(List<SelectItem> causaliItem) {
		this.causaliItem = causaliItem;
	}

	public void setCausale(int causale) {
		this.causale = causale;
	}

	public List<DisposizionePreautRicezioneDTO> getRidsFiltrati() {
		return ridsFiltrati;
	}

	public int getCausale() {
		return causale;
	}

	public String getIntEsitiPrec() {
		return intEsitiPrec;
	}

	public void setIntEsitiPrec(String intEsitiPrec) {
		this.intEsitiPrec = intEsitiPrec;
	}

	public List<FlussoEsito> getEsiti() {
		return esiti;
	}

	public boolean isVisibleEsiti() {
		visibleEsiti = !esiti.isEmpty();
		return visibleEsiti;
	}

	public String getFile() {
		return file;

	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPathUpload() {
		return pathUpload;
	}

	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}

	public String getOnClickBrowse() {
		StringBuilder sb = new StringBuilder();
		// sb.append("alert('cazzo');");
		sb.append("document.getElementById('myFile').click();");
		sb
				.append("document.getElementById('j_id1:j_id3:fileInput').value =document.getElementById('myFile').value;");
		onClickBrowse = sb.toString();
		return onClickBrowse;

	}

	public void setOnClickBrowse(String onClickBrowse) {
		this.onClickBrowse = onClickBrowse;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public boolean isVisibleListRid() {
		visibleListRid = (rids != null && !(rids.isEmpty()));
		return visibleListRid;
	}

	public void setVisibleListRid(boolean visibleListRid) {
		this.visibleListRid = visibleListRid;
	}

	public List<DisposizionePreautRicezioneDTO> getRids() {
		return rids;
	}

	public void setRids(List<DisposizionePreautRicezioneDTO> rids) {
		this.rids = rids;
	}

	public int getNrDisposizioni() {
		nrDisposizioni = 0;
		if (ridsFiltrati != null) {
			nrDisposizioni = ridsFiltrati.size();
		}

		return nrDisposizioni;
	}

	public void setNrDisposizioni(int nrDisposizioni) {
		this.nrDisposizioni = nrDisposizioni;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isDisabledRicevi() {
		return disabledRicevi;
	}

	public void setDisabledRicevi(boolean disabledRicevi) {
		this.disabledRicevi = disabledRicevi;
	}

	public RiceviAllArchivi() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub

		// imposta la directory di upload del file esiti
		/*
		String dir = Configuration.getProperty("pathUploadPreaut");
		dir = dir.replaceAll("<anno>", Integer.toString(GregorianCalendar
				.getInstance().get(GregorianCalendar.YEAR)));
		File file = new File(dir);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				throw new ContradaExceptionNonBloccante(
						Errori.DIR_PREAUT_NON_CREATA);
			}
		}
		setPathUpload(dir);*/

		formatddMMyy = new SimpleDateFormat("dd/MM/yyyy");
		formatddMMyyhhmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		nrEsiti = Integer.parseInt(Configuration.getProperty("maxEsitiPreaut"));

		causali = RicercaFlussoBD.elencaCausaliPreaut();
		causaliItem = ContradaSelectItem.valueCausaliItemOf(causali);

		loadEsiti();
	}

	public void eleboraOnClick(ActionEvent ev)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante,
			ParseException {
		InputFile inputFile = (InputFile) ev.getSource();

		if (inputFile.getFileInfo().isSaved()) {
			String file = inputFile.getFileInfo().getPhysicalPath();
			List<DisposizionePreautRicezioneDTO> disps = GestioneFlussoBD
					.riceviFlussoPreautorizzazioniRid(file);
			setRids(disps);
			ridsFiltrati = disps;

			if (disps.isEmpty()) {
				setNote("Nessuna disposizione elaborata");
			}

			loadEsiti();
		} else {
			throw new ContradaExceptionNonBloccante(
					Errori.FILE_PREAUT_NON_CARICATO);
		}

	}

	private void loadEsiti() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// effettua la ricerca degli n esiti precedenti

		setIntEsitiPrec(String.format(
				"Ultimi %d esiti di preautorizzazione scaricati", nrEsiti));

		List<FlussoEsitoDTO> flussiDTO = RicercaFlussoBD.ricercaFlussoEsito(
				TipoFlusso.PREAUTORIZZAZIONE, nrEsiti);

		List<FlussoEsito> flussi = new ArrayList<FlussoEsito>();
		if (flussiDTO != null && !(flussiDTO.isEmpty())) {
			for (FlussoEsitoDTO flussoDTO : flussiDTO) {
				FlussoEsito flusso = new FlussoEsito();
				flusso.setDtA(formatddMMyy.format(flussoDTO.getDtA()));
				flusso.setDtDa(formatddMMyy.format(flussoDTO.getDtDa()));
				flusso.setNomeFile(flussoDTO.getTxNomeFile());
				flusso.setTsRicevuto(formatddMMyyhhmm.format(flussoDTO
						.getTsRicevuto()));
				flusso.setNrDisp(flussoDTO.getNrDisp());
				flussi.add(flusso);
			}

		}
		esiti = flussi;

	}

	public void causaliOnChange(ValueChangeEvent ev) {
		if (ev.getNewValue() != null) {
			int causale = Integer.parseInt(ev.getNewValue().toString());
			ridsFiltrati = new ArrayList<DisposizionePreautRicezioneDTO>();
			if (causale == Costante.CD_CAUSALE_ESITI_TUTTI) {
				ridsFiltrati = rids;
			} else {
				for (DisposizionePreautRicezioneDTO ridEsito : rids) {
					if (ridEsito.getIdCausale() == causale) {
						ridsFiltrati.add(ridEsito);
					}
				}
			}
		}
	}
	
	public void progressListener(EventObject event) {
		InputFile ifile = (InputFile) event.getSource();
		fileProgress = ifile.getFileInfo().getPercent();
	}
	
	public void progressListener() {
		
	}

}
