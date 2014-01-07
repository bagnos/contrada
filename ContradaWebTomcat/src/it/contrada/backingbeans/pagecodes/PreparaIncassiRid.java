package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.businessdelegate.RicercaMeseBD;
import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.ConverterContrada;
import it.contrada.web.util.Costante;
import it.contrada.web.util.Errori;
import it.contrada.web.util.FacesUtils;
import it.contrada.web.util.FileTemp;
import it.contrada.web.util.LoadBundleLanguage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.mysql.jdbc.Messages;

public class PreparaIncassiRid {

	private int incassoRid;
	private int anno;
	private int mese;
	private java.util.Date dtValuta;
	private List<SelectItem> incassoRidItem;
	private List<SelectItem> annoItem;
	private List<SelectItem> meseItem;
	private boolean disabledPrepara;
	private boolean visibleListRid;
	private List<IncassoRidDTO> rids;
	private int nrDisposizioni;
	private String note;
	private boolean disabledElimina = true;
	private FlussoIncassoRidDTO flusso = null;
	private String keyDownloadFile;
	private String labelGeneraFlusso = null;
	private static Log log = LogFactory.getLog(PreparaIncassiRid.class);
	
	public String getLabelGeneraFlusso() {
		return labelGeneraFlusso;
	}

	public String getKeyDownloadFile() {

		return keyDownloadFile;
	}

	public boolean isDisabledElimina() {
		return disabledElimina;
	}

	public int getIncassoRid() {
		return incassoRid;
	}

	public void setIncassoRid(int incassoRid) {
		this.incassoRid = incassoRid;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public java.util.Date getDtValuta() {
		return dtValuta;
	}

	public void setDtValuta(java.util.Date dtValuta) {
		this.dtValuta = dtValuta;
	}

	public boolean isVisibleListRid() {
		visibleListRid = (rids != null && !(rids.isEmpty()));
		return visibleListRid;
	}

	public void setVisibleListRid(boolean visibleListRid) {
		this.visibleListRid = visibleListRid;
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

	public List<SelectItem> getIncassoRidItem() {
		return incassoRidItem;
	}

	public List<SelectItem> getAnnoItem() {
		return annoItem;
	}

	public List<SelectItem> getMeseItem() {
		return meseItem;
	}

	public boolean isDisabledPrepara() {
		return disabledPrepara;
	}

	public List<IncassoRidDTO> getRids() {
		return rids;
	}

	public PreparaIncassiRid() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		int anno = GregorianCalendar.getInstance().get(Calendar.YEAR);
		annoItem = new ArrayList<SelectItem>();
		annoItem.add(new SelectItem(anno));
		annoItem.add(new SelectItem(anno - 1));
		labelGeneraFlusso = LoadBundleLanguage.getMessage("prepara");

		incassoRidItem = new ArrayList<SelectItem>();
		meseItem = new ArrayList<SelectItem>();

		setTipoIncassi(RicercaRidBD.elencaTipoIncassiRid());

		incassoRid = TipoIncassoRid.PROTETTORATO.getIncasso();
		setMesi(incassoRid);
		GregorianCalendar dtValuta = (GregorianCalendar) GregorianCalendar
				.getInstance();
		int ggValuta = Integer.parseInt(Configuration
				.getProperty("giorniValuta"));
		dtValuta.add(Calendar.DAY_OF_MONTH, ggValuta);

		setDtValuta(new java.util.Date(dtValuta.getTimeInMillis()));
	}

	public void incassoRidOnChange(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		setMesi(Integer.parseInt(e.getNewValue().toString()));
	}

	private void setTipoIncassi(List<TipoIncassoDTO> tipoIncassi) {
		SelectItem item = null;
		for (TipoIncassoDTO incasso : tipoIncassi) {
			item = new SelectItem(incasso.getIdTipoIncasso(), incasso
					.getDsTipoIncasso());
			incassoRidItem.add(item);
		}
	}

	private void setMesi(int incassoRid) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		SelectItem item = null;
		meseItem = new ArrayList<SelectItem>();

		List<TipoMeseDTO> mesi = RicercaMeseBD.ricercaMese(incassoRid);

		for (TipoMeseDTO mese : mesi) {
			item = new SelectItem(mese.getIdMese(), mese.getDsMese());
			meseItem.add(item);
		}
	}

	public void preparaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		note = "";
		try {
			if (flusso == null) {
				flusso = GestioneFlussoBD.preparaFlussoIncassiRid(anno, mese,
						getIncassoRid(), ConverterContrada
								.valueOf(getDtValuta()));

			} else {
				// ristampa
				flusso = GestioneFlussoBD.generaFlussoIncassiRid(anno, mese,
						getIncassoRid());
			}
			rids = flusso.getIncassi();
			if (rids == null || rids.isEmpty()) {
				note = "nessuna disposizione da inviare";
				disabledElimina = true;				
				flusso = null;
			} else {
				labelGeneraFlusso = LoadBundleLanguage.getMessage("ristampa");
				disabledElimina = false;
				note = String.format("inviate %d diposizioni, importo %s",
						flusso.getNrIncassi(), ConverterContrada
								.convertToImporto(flusso.getImFlusso()));				
				PrintFile file = new PrintFile();
				file.setNomeFileCompleto(flusso.getNomeFile());
				file.setNomeFile(flusso.getNomeFileSemplice());

				keyDownloadFile = file.getNomeFile();

				((HttpServletRequest) FacesContext.getCurrentInstance()
						.getExternalContext().getRequest()).getSession()
						.setAttribute(keyDownloadFile, file);

				JavascriptContext.addJavascriptCall(FacesContext
						.getCurrentInstance(),
						"window.open('export.txt?nomeFile=" + keyDownloadFile
								+ "')");

			}
		} catch (Throwable ex) {
			if (ex.getCause() != null
					&& ex.getCause() instanceof ContradaExceptionNonBloccante) {
				setNote(ex.getCause().getMessage());
			} else {
				setNote(Errori.TEMP_PROB_COLL);
			}
			log.error(ex);
		}
	}

	public void eliminaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		note = "";
		GestioneFlussoBD.eliminaFlussoIncassiRid(anno, mese, getIncassoRid());

		note = String.format("eliminato flusso per anno %d e mese %d", anno,
				mese);
		rids = null;
		labelGeneraFlusso = LoadBundleLanguage.getMessage("prepara");
		flusso=null;
		disabledElimina = true;

	}

}
