package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Costante;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

public class ListaPagantiAnnoPrecedente {

	private int anno;
	private List<AnagraficaDTO> anagrafiche;
	private List<SelectItem> annoItem;
	private boolean renderAnags;
	private int nrAnags;
	
	
	
	

	public int getNrAnags() {
		
		nrAnags=0;
		if (renderAnags)
		{
			nrAnags=anagrafiche.size();
		}
		return nrAnags;
	}

	public boolean isRenderAnags() {
		renderAnags=anagrafiche!=null && !(anagrafiche.isEmpty());
		return renderAnags;
	}

	public List<SelectItem> getAnnoItem() {
		return annoItem;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public ListaPagantiAnnoPrecedente() {
		annoItem = new ArrayList<SelectItem>();
		anno = Calendar.getInstance().get(Calendar.YEAR);
		annoItem.add(new SelectItem(anno));
		annoItem.add(new SelectItem(anno - 1));
	}
	
	public void recuperaAnagrafiche(ActionEvent e) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		anagrafiche=RicercaAnagraficaBD.ricercaPagantiAnnoPrecedente(anno, 1);
		
		
	}

}
