package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaMeseBD;
import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.RicercaFasceEtaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.servlets.GeneratePdf;
import it.contrada.web.report.PdfReport;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.icesoft.faces.context.effects.JavascriptContext;
import com.itextpdf.text.DocumentException;

public class RicercaFasceEta {

	private Integer eta;
	private int mese;
	private java.util.Date dtDa;
	private java.util.Date dtA;
	private List<AnagraficaDTO> anagrafiche;
	private boolean visibleAnagrafiche;
	private List<SelectItem> mesiItem;
	private String sesso;
	private int nrAnagrafiche; 

	public int getNrAnagrafiche() {
		if (anagrafiche != null && !(anagrafiche.isEmpty())) {
			nrAnagrafiche = anagrafiche.size();
		} else {
			nrAnagrafiche = 0;
		}
		return nrAnagrafiche;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public List<SelectItem> getSessiItem() {
		return sessiItem;
	}

	public void setSessiItem(List<SelectItem> sessiItem) {
		this.sessiItem = sessiItem;
	}

	private List<SelectItem> sessiItem;

	public boolean isVisibleAnagrafiche() {
		visibleAnagrafiche = anagrafiche != null && !(anagrafiche.isEmpty());
		return (visibleAnagrafiche);
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public java.util.Date getDtDa() {
		return dtDa;
	}

	public void setDtDa(java.util.Date dtDa) {
		this.dtDa = dtDa;
	}

	public java.util.Date getDtA() {
		return dtA;
	}

	public void setDtA(java.util.Date dtA) {
		this.dtA = dtA;
	}

	public int getMese() {
		return mese;
	}

	public void setMese(int mese) {
		this.mese = mese;
	}

	public List<SelectItem> getMesiItem() {
		return mesiItem;
	}

	public void setMesiItem(List<SelectItem> meseItem) {
		this.mesiItem = meseItem;
	}

	public Integer getEta() {
		return eta;
	}

	public void setEta(Integer eta) {
		this.eta = eta;
	}

	public RicercaFasceEta() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated constructor stub

		SelectItem item = null;
		mesiItem = new ArrayList<SelectItem>();
		sessiItem = new ArrayList<SelectItem>();
		sessiItem.add(new SelectItem(""));
		sessiItem.add(new SelectItem("M"));
		sessiItem.add(new SelectItem("F"));

		List<TipoMeseDTO> mesi = RicercaMeseBD.elencaMesi();
		mesiItem.add(new SelectItem(0, ""));
		for (TipoMeseDTO mese : mesi) {
			item = new SelectItem(mese.getIdMese(), mese.getDsMese());
			mesiItem.add(item);
		}
		// rimuovo recupero
		mesiItem.remove(mesiItem.size() - 1);
		
		
		
	}

	public void ricercaAnagraficaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		RicercaFasceEtaDTO criterio = new RicercaFasceEtaDTO();

		if (dtA != null && dtA != null) {
			java.sql.Date dtDaSql = new Date(dtDa.getTime());
			java.sql.Date dtASql = new Date(dtA.getTime());
			criterio.setDtA(dtASql);
			criterio.setDtDa(dtDaSql);
		}

		if (mese > 0) {
			criterio.setMese(mese);
		}
		if (eta != null && eta > 0) {
			criterio.setEta(eta);
		}
		if (sesso != "") {
			criterio.setSesso(sesso);
		}
		
		anagrafiche=RicercaAnagraficaBD.ricercaFasciaEta(criterio);

	}
	
	public void stampaPdf(ActionEvent e) throws DocumentException, IOException
	{
		PdfReport report=new PdfReport();
		
		PrintFile file = report.generaPdfEtichette(anagrafiche);	
		
		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession()
				.setAttribute(file.getNomeFile(), file);

		JavascriptContext.addJavascriptCall(FacesContext
				.getCurrentInstance(),
				"window.open('etichette.pdf?nomeFile=" + file.getNomeFile()
						+ "')");
	}
}
