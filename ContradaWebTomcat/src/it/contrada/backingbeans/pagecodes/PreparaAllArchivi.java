package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.dao.AnagrafeDAO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;
import it.contrada.web.util.LoadBundleLanguage;
import it.contrada.web.util.VerificaCoordinateBancariePojo;
import it.contrada.web.util.VerificaDatiAnagraficiClientePojo;

import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icesoft.faces.context.effects.JavascriptContext;

public class PreparaAllArchivi {
	private boolean visibleListRid;
	private List<RidDTO> rids;
	private int nrDisposizioni;
	private String note;
	private boolean disabledPrepara;
	private String keyDownloadFile;
	private boolean rigenera;
	private Date dtInvio;
	FlussoPreautInviatoDTO preautDTO = null;
	private String labelCmdFlusso;
	private boolean visibleElimina;
	private static Log log = LogFactory.getLog(PreparaAllArchivi.class);

	public boolean isVisibleElimina() {
		return visibleElimina;
	}

	public String getLabelCmdFlusso() {
		return labelCmdFlusso;
	}

	public boolean isRigenera() {
		return rigenera;
	}

	public String getKeyDownloadFile() {
		return keyDownloadFile;
	}

	public boolean isDisabledPrepara() {
		disabledPrepara = isVisibleListRid();
		return disabledPrepara;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNrDisposizioni() {
		nrDisposizioni = rids.size();
		return nrDisposizioni;

	}

	public List<RidDTO> getRids() {
		return rids;

	}

	public void setRids(List<RidDTO> rids) {
		this.rids = rids;
	}

	public boolean isVisibleListRid() {
		visibleListRid = !(rids == null || rids.isEmpty());
		return visibleListRid;
	}

	public void setVisibleListRid(boolean visibleListRid) {
		this.visibleListRid = visibleListRid;
	}

	public PreparaAllArchivi() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		rids = GestioneFlussoBD.elencaRidDaAllineare();
		labelCmdFlusso = "Prepara";
		VerificaDatiAnagraficiClientePojo datiAnag = new VerificaDatiAnagraficiClientePojo();
		VerificaCoordinateBancariePojo cord = new VerificaCoordinateBancariePojo();
		for (RidDTO rid : rids) {
			rid.setInvioPreaut(true);
			if (!datiAnag.verificaFormaleCodiceFiscale(rid.getCdFiscale())) {
				rid.setInvioPreaut(false);
				rid.setNoteInvioPreaut("KO:Codice Fiscale assente/errato");
				continue;
			}
			if (!cord.verificaIban(rid.getIban())) {
				rid.setInvioPreaut(false);
				rid.setNoteInvioPreaut("KO:Codice Iban assente/errato");
				continue;
			}
			if (rid.getIntestatarioRid() == null) {
				rid.setNoteInvioPreaut("KO:Intestatario assente");
				continue;
			}
		}
	}

	public void preparaOnClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		try {
			if (preautDTO == null) {
				// si genera il flusso per la prima volta
				preautDTO = GestioneFlussoBD.preparaFlussoPreautRid(rids);
			} else {
				// ristampa il flusso e basta
				preautDTO = GestioneFlussoBD
						.generaFlussoPreautInviati(preautDTO.getRids());
			}
			if (preautDTO == null) {
				setNote("Nessuna preautorizzazione inviata");

			} else {
				labelCmdFlusso = "Ristampa";
				visibleElimina=true;
				dtInvio = preautDTO.getDtInvio();
				setNote(String.format("Inserite %d disposizioni nel flusso ",
						preautDTO.getNrDisposizioni()));

				genereFilePreaut(preautDTO);

			}
		} catch (Throwable e) {
			if (e.getCause() != null
					&& e.getCause() instanceof ContradaExceptionNonBloccante) {
				setNote(e.getCause().getMessage());
			} else {
				setNote(Errori.TEMP_PROB_COLL);
			}
			log.error(e);
			
		}
	}

	public void eliminaRidOnClick(ActionEvent av) {
		RidDTO rid = (RidDTO) (av.getComponent().getAttributes().get("rid"));

		rids.remove(rid);

	}

	private void genereFilePreaut(FlussoPreautInviatoDTO preautDTO) {
		PrintFile file = new PrintFile();
		file.setNomeFileCompleto(preautDTO.getNomeFile());
		file.setNomeFile(preautDTO.getNomeFileSemplice());

		keyDownloadFile = file.getNomeFile();

		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				keyDownloadFile, file);

		JavascriptContext.addJavascriptCall(FacesContext.getCurrentInstance(),
				"window.open('export.txt?nomeFile=" + keyDownloadFile + "')");
	}

	

}
