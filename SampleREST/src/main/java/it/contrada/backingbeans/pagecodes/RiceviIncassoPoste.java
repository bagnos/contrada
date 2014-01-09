package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.FlussoEsito;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.businessdelegate.GestionePosteBD;
import it.contrada.businessdelegate.RicercaFlussoBD;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.DisposizioneIncassoRidRicezioneDTO;
import it.contrada.incassorid.dto.RicezioneFlussoIncassoRidDTO;
import it.contrada.poste.RendicontazioneIncassoPostaDTO;
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

public class RiceviIncassoPoste {

	private boolean visibleListPoste;
	private List<RendicontazioneIncassoPostaDTO> poste;
	private List<RendicontazioneIncassoPostaDTO> posteFiltrati;
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
	private String tipoDocumento;
	private List<SelectItem> tipoDocumentoItem = null;
	private FileInfo currentFile;
	private int fileProgress;

	public FileInfo getCurrentFile() {
		return currentFile;
	}

	public int getFileProgress() {
		return fileProgress;
	}

	public boolean isVisibleListPoste() {
		return visibleListPoste;
	}

	public void setVisibleListPoste(boolean visibleListPoste) {
		this.visibleListPoste = visibleListPoste;
	}

	public List<RendicontazioneIncassoPostaDTO> getIncassi() {
		return poste;
	}

	public void setPoste(List<RendicontazioneIncassoPostaDTO> poste) {
		this.poste = poste;
	}

	public List<RendicontazioneIncassoPostaDTO> getPosteFiltrati() {
		return posteFiltrati;
	}

	public void setPosteFiltrati(
			List<RendicontazioneIncassoPostaDTO> posteFiltrati) {
		this.posteFiltrati = posteFiltrati;
	}

	public int getNrDisposizioni() {
		if (posteFiltrati == null) {
			return 0;
		} else {
			return posteFiltrati.size();
		}

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

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getOnClickBrowse() {
		return onClickBrowse;
	}

	public void setOnClickBrowse(String onClickBrowse) {
		this.onClickBrowse = onClickBrowse;
	}

	public String getPathUpload() {
		return pathUpload;
	}

	public void setPathUpload(String pathUpload) {
		this.pathUpload = pathUpload;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public boolean isVisibleEsiti() {
		return visibleEsiti;
	}

	public void setVisibleEsiti(boolean visibleEsiti) {
		this.visibleEsiti = visibleEsiti;
	}

	public List<FlussoEsito> getEsiti() {
		return esiti;
	}

	public void setEsiti(List<FlussoEsito> esiti) {
		this.esiti = esiti;
	}

	public String getIntEsitiPrec() {
		return intEsitiPrec;
	}

	public void setIntEsitiPrec(String intEsitiPrec) {
		this.intEsitiPrec = intEsitiPrec;
	}

	public int getNrEsiti() {
		return nrEsiti;
	}

	public void setNrEsiti(int nrEsiti) {
		this.nrEsiti = nrEsiti;
	}

	public SimpleDateFormat getFormatddMMyy() {
		return formatddMMyy;
	}

	public void setFormatddMMyy(SimpleDateFormat formatddMMyy) {
		this.formatddMMyy = formatddMMyy;
	}

	public SimpleDateFormat getFormatddMMyyhhmm() {
		return formatddMMyyhhmm;
	}

	public void setFormatddMMyyhhmm(SimpleDateFormat formatddMMyyhhmm) {
		this.formatddMMyyhhmm = formatddMMyyhhmm;
	}

	public List<SelectItem> getTipoDocumentoItem() {
		return tipoDocumentoItem;
	}

	public void setTipoDocumentoItem(List<SelectItem> tipoDocumentoItem) {
		this.tipoDocumentoItem = tipoDocumentoItem;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public RiceviIncassoPoste() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		/*
		String dir = Configuration.getProperty("pathUploadIncassoPoste");
		dir = dir.replaceAll("<anno>", Integer.toString(GregorianCalendar
				.getInstance().get(GregorianCalendar.YEAR)));
		File file = new File(dir);
		if (!file.exists()) {
			if (!file.mkdirs()) {
				throw new ContradaExceptionNonBloccante(
						Errori.DIR_INCASSO_POSTA_NON_CREATA);
			}
		}
		setPathUpload(dir);*/

		formatddMMyy = new SimpleDateFormat("dd/MM/yyyy");
		formatddMMyyhhmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		nrEsiti = Integer.parseInt(Configuration.getProperty("maxEsitiPreaut"));

		tipoDocumentoItem = new ArrayList<SelectItem>();
		tipoDocumentoItem.add(new SelectItem(Costante.STRING_CD_TUTTI,
				Costante.DS_CAUSALE_ESITI_TUTTI));
		tipoDocumentoItem.add(new SelectItem(Costante.INCASSO_AUTOMATICO,
				"Incasso Automatico"));
		tipoDocumentoItem.add(new SelectItem(Costante.INCASSO_MANUALE,
				"Incasso Manuale"));

		loadEsiti();
	}

	public void eleboraOnClick(ActionEvent ev)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante,
			ParseException {
		InputFile inputFile = (InputFile) ev.getSource();

		if (inputFile.getFileInfo().isSaved()) {
			String file = inputFile.getFileInfo().getPhysicalPath();
			List<RendicontazioneIncassoPostaDTO> bolletttini = GestionePosteBD
					.rendicontaFlussoPoste(file);
			setPoste(bolletttini);
			setPosteFiltrati(bolletttini);
			if (bolletttini.isEmpty()) {
				setNote("Nessuna disposizione elaborata");
				setVisibleListPoste(false);
			} else {
				setVisibleListPoste(true);
			}

			loadEsiti();
			setTipoDocumento(Costante.DS_CAUSALE_ESITI_TUTTI);
			setNrDisposizioni(bolletttini.size());
		} else {
			throw new ContradaExceptionNonBloccante(
					Errori.FILE_RID_ESITI_NON_CARICATO);
		}

	}

	private void loadEsiti() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// effettua la ricerca degli n esiti precedenti

		setIntEsitiPrec(String.format(
				"Ultimi %d esiti di incasso poste scaricati", nrEsiti));

		List<FlussoEsitoDTO> flussiDTO = RicercaFlussoBD.ricercaFlussoEsito(
				TipoFlusso.BOLLETTINO, nrEsiti);

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
				flusso.setImFlusso(flussoDTO.getImFlusso());
				flussi.add(flusso);
			}

		}
		esiti = flussi;
		if (esiti != null && !(esiti.isEmpty())) {
			setVisibleEsiti(true);
		} else {
			setVisibleEsiti(false);
		}

	}

	public void tipoDocumentoOnChange(ValueChangeEvent ev) {
		if (ev.getNewValue() != null) {
			String tipoDocumento = ev.getNewValue().toString();
			posteFiltrati = new ArrayList<RendicontazioneIncassoPostaDTO>();
			if (tipoDocumento.equalsIgnoreCase(Costante.STRING_CD_TUTTI)) {
				posteFiltrati = poste;
			} else {
				for (RendicontazioneIncassoPostaDTO posta : poste) {
					if (posta.getTyDocumento().equalsIgnoreCase(tipoDocumento)) {
						posteFiltrati.add(posta);
					}
				}
			}
			setNrDisposizioni(posteFiltrati.size());
		}
	}

	public void progressListener(EventObject event) {
		InputFile ifile = (InputFile) event.getSource();
		fileProgress = ifile.getFileInfo().getPercent();
	}

	public void progressListener() {

	}

}
