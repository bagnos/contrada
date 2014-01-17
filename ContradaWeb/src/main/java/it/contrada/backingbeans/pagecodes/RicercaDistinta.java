package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneDistintaBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dto.DistintaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class RicercaDistinta {
	private List<SelectItem> annoItems;
	private int anno;
	private List<DistintaDTO> distinta;
	private boolean visibleDistinta;
	private List<TesseraDTO> tessere;
	private int nrDistinte;
	private boolean renderPopupDettaglio;
	private int nrDistinta;
	private String dataDistinta;
	private String message;
	
	
	

	public String getMessage() {
		return message;
	}

	public String getDataDistinta() {
		return dataDistinta;
	}

	public int getNrDistinta() {
		return nrDistinta;
	}

	public boolean isRenderPopupDettaglio() {
		return renderPopupDettaglio;
	}

	public int getNrDistinte() {
		nrDistinte = 0;
		if (distinta != null) {
			nrDistinte = distinta.size();
		}

		return nrDistinte;
	}

	public List<TesseraDTO> getTessere() {
		return tessere;
	}

	public void setTessere(List<TesseraDTO> tessere) {
		this.tessere = tessere;
	}

	public boolean isVisibleDistinta() {
		visibleDistinta = (distinta != null && !distinta.isEmpty());

		return visibleDistinta;

	}

	public List<DistintaDTO> getDistinta() {
		return distinta;
	}

	public List<SelectItem> getAnnoItems() {
		return annoItems;
	}

	public void setAnnoItems(List<SelectItem> annoItems) {
		this.annoItems = annoItems;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public RicercaDistinta() throws NumberFormatException,
			ContradaExceptionBloccante {
		initDDLAnni();
		annoValueChange(null);		
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		GregorianCalendar gCalendar = new GregorianCalendar();
		anno = gCalendar.get(GregorianCalendar.YEAR);
		annoItems = new ArrayList<SelectItem>();

		int anniRendicontabili = Integer.parseInt(Configuration
				.getProperty("anniStampaTessere"));
		for (int i = 0; i <= anniRendicontabili; i++) {
			annoItems.add(new SelectItem(Integer.valueOf(anno - i), Integer
					.toString(anno - i)));
		}

	}

	public void annoValueChange(ValueChangeEvent e)
			throws ContradaExceptionBloccante {
		distinta = GestioneDistintaBD.ricercaDistintaPerAnno(anno);
		renderPopupDettaglio=false;
		message="";
		if (distinta==null || distinta.isEmpty())
		{
			message="Nessuna distinta presente";
		}
	}

	public void stampaOnClick(ActionEvent e) {

	}

	public void dettaglioOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		DistintaDTO distinta = (DistintaDTO) e.getComponent().getAttributes()
				.get("distinta");
		
		nrDistinta=distinta.getNrDistinta();
		tessere = RicercaTipoTesseraBD.ricercaTesserePerDistinta(distinta
				.getNrAnno(), distinta.getNrDistinta());
		renderPopupDettaglio=true;
		
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		
	}
	
	public void chiudiPopupClick(ActionEvent e)
	{
		renderPopupDettaglio=false;
	}
}
