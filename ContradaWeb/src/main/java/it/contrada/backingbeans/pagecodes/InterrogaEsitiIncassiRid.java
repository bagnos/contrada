package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.GestioneFlussoBD;
import it.contrada.businessdelegate.RicercaFlussoBD;
import it.contrada.businessdelegate.RicercaMeseBD;
import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.web.util.Costante;
 

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.icesoft.faces.context.effects.JavascriptContext;

public class InterrogaEsitiIncassiRid {

	private int anno;
	private int mese;
	private int nrRid;
	private List<SelectItem> anniItem;
	private List<SelectItem> mesiItem;
	private int causale;
	private List<SelectItem> causaliItem;
	private List<IncassoRidDTO> esiti;
	private int tipoIncasso;
	private List<SelectItem> tipoIncassiItem;
	private boolean visibleEsiti;
	private String note;
	private int nrIncassi;
	private double importo;
	private double importoIncassato;
	private boolean renderFlusso;
	private Integer annoInt;
	private Integer meseInt;
	private Integer tipoIncassoInt;
	
	

	public boolean isRenderFlusso() {
		return renderFlusso;
	}

	public int getNrIncassi() {
		return nrIncassi;
	}

	public double getImporto() {
		return importo;
	}

	public double getImportoIncassato() {
		return importoIncassato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isVisibleEsiti() {
		visibleEsiti=(esiti!=null && !(esiti.isEmpty()));
		return visibleEsiti;
	}

	public void setVisibleEsiti(boolean visibleEsiti) {
		this.visibleEsiti = visibleEsiti;
	}

	public int getTipoIncasso() {
		return tipoIncasso;
	}

	public void setTipoIncasso(int tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

	public List<SelectItem> getTipoIncassiItem() {
		return tipoIncassiItem;
	}

	public void setTipoIncassiItem(List<SelectItem> tipoIncassiItem) {
		this.tipoIncassiItem = tipoIncassiItem;
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

	public int getNrRid() {
		return nrRid;
	}

	public void setNrRid(int nrRid) {
		this.nrRid = nrRid;
	}

	public List<SelectItem> getAnniItem() {
		return anniItem;
	}

	public void setAnniItem(List<SelectItem> anniItem) {
		this.anniItem = anniItem;
	}

	public List<SelectItem> getMesiItem() {
		return mesiItem;
	}

	public void setMesiItem(List<SelectItem> mesiItem) {
		this.mesiItem = mesiItem;
	}

	public int getCausale() {
		return causale;
	}

	public void setCausale(int causale) {
		this.causale = causale;
	}

	public List<SelectItem> getCausaliItem() {
		return causaliItem;
	}

	public void setCausaliItem(List<SelectItem> causaliItem) {
		this.causaliItem = causaliItem;
	}

	public List<IncassoRidDTO> getEsiti() {
		return esiti;
	}

	public void setEsiti(List<IncassoRidDTO> esiti) {
		this.esiti = esiti;
	}

	public InterrogaEsitiIncassiRid() throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub
		int anno=GregorianCalendar.getInstance().get(Calendar.YEAR);
		anniItem=new ArrayList<SelectItem>();	
		for (int i=anno;i>=anno-10;i--)
		{
			anniItem.add(new SelectItem(i));
		}
		setAnno(anno);
		
		List<TipoMeseDTO> mesi=RicercaMeseBD.elencaMesi();
		
		mesiItem=new ArrayList<SelectItem>();
		mesiItem.add(new SelectItem(0,""));	
		for (TipoMeseDTO mese:mesi)
		{
			mesiItem.add(new SelectItem(mese.getIdMese(),mese.getDsMese()));
		}
		
		
		List<TipoIncassoDTO> tipoIncassi=RicercaRidBD.elencaTipoIncassiRid();
		tipoIncassiItem=new ArrayList<SelectItem>();
		tipoIncassiItem.add(new SelectItem(0,""));
		for (TipoIncassoDTO incasso:tipoIncassi)
		{
			tipoIncassiItem.add(new SelectItem(incasso.getIdTipoIncasso(),incasso.getDsTipoIncasso()));
		}
		
		
		causaliItem=new ArrayList<SelectItem>();
		List<TipoCasualiIncassoRidDTO> causali=RicercaFlussoBD.elencaCausaliIncassiRid();
		causaliItem.add(new SelectItem(0,""));
		for (TipoCasualiIncassoRidDTO causale:causali)
		{
			causaliItem.add(new SelectItem(causale.getCdCausale(),causale.getCdCausale()+"-"+causale.getDsCausale()));
		}
		
		
		
	}

	public void cercaClick(ActionEvent ev) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		annoInt=anno>0?anno:null;
		meseInt=mese>0?mese:null;
		Integer causaleInt=causale>0?causale:null;
		tipoIncassoInt=tipoIncasso>0?tipoIncasso:null;
		Integer nrRidInt=nrRid>0?nrRid:null;
		setNote(null);
		renderFlusso=false;
		List<IncassoRidDTO> esitiIncassi=RicercaFlussoBD.ricercaEsitoIncassoRid(annoInt, meseInt, tipoIncassoInt, nrRidInt, causaleInt);
		
		if (esitiIncassi!=null && !esitiIncassi.isEmpty())
		{
			importo=0;
			importoIncassato=0;
			nrIncassi=esitiIncassi.size();
			for (IncassoRidDTO incasso:esitiIncassi)
			{
				importo+=incasso.getImRichiesto();
				if (incasso.getIdRidEsito().equals(Costante.INCASSO_RID_PAGATO))
				{
					importoIncassato+=incasso.getImRichiesto();
				}
			}
			importo=importo/100;
			importoIncassato=importoIncassato/100;
			
			if (annoInt!=null && meseInt!=null && tipoIncassoInt!=null)
			{
				//si sta cercando un flusso
				renderFlusso=true;
			}
		}
		else
		{
			setNote("Dati non presenti");
		}
		setEsiti(esitiIncassi);
	}
	
	public void generaFlusso(ActionEvent e) throws ContradaExceptionNonBloccante, ContradaExceptionBloccante
	{
		FlussoIncassoRidDTO flusso=GestioneFlussoBD.generaFlussoIncassiRid(annoInt,meseInt,tipoIncassoInt);
		PrintFile file = new PrintFile();
		file.setNomeFileCompleto(flusso.getNomeFile());
		file.setNomeFile(flusso.getNomeFileSemplice());

		String keyDownloadFile = file.getNomeFile();

		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession()
				.setAttribute(keyDownloadFile, file);

		JavascriptContext.addJavascriptCall(FacesContext
				.getCurrentInstance(),
				"window.open('export.txt?nomeFile=" + keyDownloadFile
						+ "')");


	}

}
