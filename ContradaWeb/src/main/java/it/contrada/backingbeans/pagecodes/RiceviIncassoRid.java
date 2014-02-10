package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.FlussoEsito;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.businessdelegate.RicercaFlussoBD;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.DisposizioneIncassoRidRicezioneDTO;
import it.contrada.incassorid.dto.RicezioneFlussoIncassoRidDTO;
import it.contrada.mail.RidMail;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.ContradaSelectItem;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;

public class RiceviIncassoRid extends BaseView {

	private boolean visibleListRid;
	private List<DisposizioneIncassoRidRicezioneDTO> rids;
	private List<DisposizioneIncassoRidRicezioneDTO> ridsFiltrati;
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
	private List<TipoCasualiIncassoRidDTO> causali;
	private List<SelectItem> causaliItem = null;
	private int causale;
	private FileInfo currentFile;
	private int fileProgress;
	private boolean selectAll;
	
	

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public FileInfo getCurrentFile() {
		return currentFile;
	}

	public int getFileProgress() {
		return fileProgress;
	}

	public boolean isVisibleListRid() {
		return visibleListRid;
	}

	public void setVisibleListRid(boolean visibleListRid) {
		this.visibleListRid = visibleListRid;
	}

	public List<DisposizioneIncassoRidRicezioneDTO> getRids() {
		return rids;
	}

	public void setRids(List<DisposizioneIncassoRidRicezioneDTO> rids) {
		this.rids = rids;
	}

	public List<DisposizioneIncassoRidRicezioneDTO> getRidsFiltrati() {
		return ridsFiltrati;
	}

	public void setRidsFiltrati(
			List<DisposizioneIncassoRidRicezioneDTO> ridsFiltrati) {
		this.ridsFiltrati = ridsFiltrati;
	}

	public int getNrDisposizioni() {
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

	public List<TipoCasualiIncassoRidDTO> getCausali() {
		return causali;
	}

	public void setCausali(List<TipoCasualiIncassoRidDTO> causali) {
		this.causali = causali;
	}

	public List<SelectItem> getCausaliItem() {
		return causaliItem;
	}

	public void setCausaliItem(List<SelectItem> causaliItem) {
		this.causaliItem = causaliItem;
	}

	public int getCausale() {
		return causale;
	}

	public void setCausale(int causale) {
		this.causale = causale;
	}

	public RiceviIncassoRid() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		/*
		 * String dir = Configuration.getProperty("pathUploadIncassoRid"); dir =
		 * dir.replaceAll("<anno>", Integer.toString(GregorianCalendar
		 * .getInstance().get(GregorianCalendar.YEAR))); File file = new
		 * File(dir); if (!file.exists()) { if (!file.mkdirs()) { throw new
		 * ContradaExceptionNonBloccante( Errori.DIR_INCASSO_RID_NON_CREATA); }
		 * } setPathUpload(dir);
		 */

		formatddMMyy = new SimpleDateFormat("dd/MM/yyyy");
		formatddMMyyhhmm = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		nrEsiti = Integer.parseInt(Configuration.getProperty("maxEsitiPreaut"));

		causali = RicercaFlussoBD.elencaCausaliIncassiRid();
		causaliItem = ContradaSelectItem.valueCausaliIncassoItemOf(causali);

		loadEsiti();
	}

	public void selectAllClick(ValueChangeEvent e) {
		if (e != null) {
			boolean sel = (Boolean) e.getNewValue();
			for (DisposizioneIncassoRidRicezioneDTO disp :ridsFiltrati) {
				disp.setSelezionato(sel);
			}
		}
	}

	public void inviaMailClick(ActionEvent e) {

		try {
			List<DisposizioneIncassoRidRicezioneDTO> mails = new ArrayList<DisposizioneIncassoRidRicezioneDTO>();
			for (DisposizioneIncassoRidRicezioneDTO rid : ridsFiltrati) {

				if (rid.isSelezionato()) {

					mails.add(rid);

				}
			}
			if (!mails.isEmpty()) {
				RidMail.InviaMailSospesiRataRid(mails);
				writeInfoMessage(TipoGravitaMessage.SUCCESS, "Mail inviate correttamente");
			}
			else
			{
				writeInfoMessage(TipoGravitaMessage.ERROR, "Nessun elemento selezionato.");
			}
		} catch (Exception ex) {
			writeErrorMessage("Errore nell'invio della mail", ex);

		}

	}

	public void eleboraOnClick(ActionEvent ev)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante,
			ParseException, IOException {
		try {
			InputFile inputFile = (InputFile) ev.getSource();

			if (inputFile.getFileInfo().isSaved()) {
				currentFile = inputFile.getFileInfo();

				String file = currentFile.getPhysicalPath();
				List<RicezioneFlussoIncassoRidDTO> flussi = GestioneFlussoBD
						.riceviFlussoIncassiRid(file);

				List<DisposizioneIncassoRidRicezioneDTO> dispIncassi = new ArrayList<DisposizioneIncassoRidRicezioneDTO>();

				for (RicezioneFlussoIncassoRidDTO flusso : flussi) {

					dispIncassi.addAll(flusso.getDisposizioni());
				}

				setRids(dispIncassi);
				ridsFiltrati = dispIncassi;

				if (dispIncassi.isEmpty()) {
					setNote("Nessuna disposizione elaborata");
					writeInfoMessage(TipoGravitaMessage.INFO,
							"Nessuna disposizione elaborata");
					setVisibleListRid(false);
				} else {
					setVisibleListRid(true);

					loadEsiti();
					setCausale(Costante.CD_CAUSALE_ESITI_TUTTI);
					setNrDisposizioni(dispIncassi.size());
					writeInfoMessage(TipoGravitaMessage.SUCCESS, String.format(
							"Elaborate %d disposizioni", dispIncassi.size()));
				}
			} else {
				writeInfoMessage(TipoGravitaMessage.ERROR,
						Errori.FILE_RID_ESITI_NON_CARICATO);

			}
		} catch (Exception e) {
			writeErrorMessage("Errore elborazione ricezione flussi rid", e);
		}

	}

	private void loadEsiti() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// effettua la ricerca degli n esiti precedenti

		setIntEsitiPrec(String.format(
				"Ultimi %d esiti di incasso rid scaricati", nrEsiti));

		List<FlussoEsitoDTO> flussiDTO = RicercaFlussoBD.ricercaFlussoEsito(
				TipoFlusso.RID, nrEsiti);

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
		if (esiti != null && !(esiti.isEmpty())) {
			setVisibleEsiti(true);
		} else {
			setVisibleEsiti(false);
		}

	}

	public void causaliOnChange(ValueChangeEvent ev) {
		if (ev.getNewValue() != null) {
			int causale = Integer.parseInt(ev.getNewValue().toString());
			ridsFiltrati = new ArrayList<DisposizioneIncassoRidRicezioneDTO>();
			if (causale == Costante.CD_CAUSALE_ESITI_TUTTI) {
				ridsFiltrati = rids;
			} else {
				for (DisposizioneIncassoRidRicezioneDTO ridEsito : rids) {
					if (ridEsito.getCdCausale() == causale) {
						ridsFiltrati.add(ridEsito);
					}
				}
			}
			setNrDisposizioni(ridsFiltrati.size());
		}
	}

	public void progressListener(EventObject event) {
		InputFile ifile = (InputFile) event.getSource();
		fileProgress = ifile.getFileInfo().getPercent();
	}

	public void progressListener() {

	}

}
