package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestionePosteBD;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.poste.FlussoIncassoPostaDTO;
import it.contrada.web.util.Configuration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class RicercaIncassoPoste {
	private int anno;
	private List<SelectItem> anni;
	private List<FlussoIncassoPostaDTO> flussi;
	private boolean renderFlussi;
	
	
	
	

	public boolean isRenderFlussi() {
		renderFlussi=flussi!=null && flussi.isEmpty()==false;
		return renderFlussi;
	}

	public List<FlussoIncassoPostaDTO> getFlussi() {
		return flussi;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<SelectItem> getAnni() {
		return anni;
	}

	public void cercaClick(ActionEvent e) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		flussi=GestionePosteBD.ricercaFlussoPostalePerAnno(anno);
	}

	public RicercaIncassoPoste() throws NumberFormatException, ContradaExceptionBloccante {
		initDDLAnni();
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		anni = new ArrayList<SelectItem>();
		SelectItem item = null;
		int baseYear = Integer.parseInt(Configuration
				.getProperty("nrAnniInterrogazioneAnagraficaIncassi"));
		int toYear = GregorianCalendar.getInstance().get(Calendar.YEAR);
		anno=toYear;
		anni.add(new SelectItem(null));
		for (int i = baseYear; i <= toYear; i++) {
			item = new SelectItem(i);
			anni.add(item);
		}
	}
	
	public void showHideRateClick(ActionEvent e)
	{
		FlussoIncassoPostaDTO flussoPosta=(FlussoIncassoPostaDTO)e.getComponent().getAttributes().get("flusso");
		flussoPosta.setShowRate(!flussoPosta.isShowRate());
	}
}
