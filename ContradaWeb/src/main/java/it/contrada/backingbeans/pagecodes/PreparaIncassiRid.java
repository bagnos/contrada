package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.businessdelegate.RicercaMeseBD;
import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.enumcontrada.TipoMeseIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.util.BaseUtil;
import it.contrada.util.DecodificaMese;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.ConverterContrada;
import it.contrada.web.util.Errori;
import it.contrada.web.util.LoadBundleLanguage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.icesoft.faces.context.effects.JavascriptContext;

public class PreparaIncassiRid extends BaseView {

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
	private boolean formatXml;
	
	public boolean isFormatXml() {
		return formatXml;
	}

	public void setFormatXml(boolean formatXml) {
		this.formatXml = formatXml;
	}

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
		init();
	}
	
	private void init() throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		
		int anno = GregorianCalendar.getInstance().get(Calendar.YEAR);
		flusso=null;
		rids=null;
		disabledElimina=true;
		
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
		hideInfoMessage();
		note=null;
		formatXml=true;
		
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
	
	
	
	public void resetOnClick(ActionEvent e) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		init();
	}

	public void preparaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		note = "";
		
		hideInfoMessage();
		try {
			
			if (flusso == null) {
				flusso = GestioneFlussoBD.preparaFlussoIncassiRid(anno, mese,
						getIncassoRid(), ConverterContrada
								.valueOf(getDtValuta()),formatXml);
				

			} else {
				// ristampa
				flusso = GestioneFlussoBD.generaFlussoIncassiRid(anno, mese,
						getIncassoRid(),formatXml);
			}
			rids = flusso.getIncassi();
			if (rids == null || rids.isEmpty()) {
				note = "nessuna disposizione da inviare";
				disabledElimina = true;				
				flusso = null;
			} else {
				labelGeneraFlusso = LoadBundleLanguage.getMessage("ristampa");
				disabledElimina = false;
				
				note = String.format("Inviate %d diposizioni, importo %s",
						flusso.getNrIncassi(), BaseUtil.formatImporto(flusso.getImFlusso()));				
				
				PrintFile file = new PrintFile();
				file.setNomeFileCompleto(flusso.getNomeFile());
				file.setNomeFile(flusso.getNomeFileSemplice());
				file.setExtension(flusso.getExtension());
				writeInfoMessage(TipoGravitaMessage.SUCCESS, note);

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
			writeInfoMessage(TipoGravitaMessage.ERROR, note);
			log.error(ex);
		}
	}

	public void eliminaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		note = "";
		hideInfoMessage();
		GestioneFlussoBD.eliminaFlussoIncassiRid(anno, mese, getIncassoRid());

		note = String.format("Eliminato flusso per anno %d e mese %s", anno,
				TipoMeseIncasso.lookUpMeseByOrdinal(mese));
		rids = null;
		labelGeneraFlusso = LoadBundleLanguage.getMessage("prepara");
		flusso=null;
		disabledElimina = true;
		writeInfoMessage(TipoGravitaMessage.SUCCESS, note);

	}

}
