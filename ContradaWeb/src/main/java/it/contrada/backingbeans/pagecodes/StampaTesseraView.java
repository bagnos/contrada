package it.contrada.backingbeans.pagecodes;

import it.contrada.backingbeans.model.StampaTesseraBean;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaEsattoreBD;
import it.contrada.businessdelegate.RicercaIncassoBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.EsattoreDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.enumcontrada.TipoIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.enumcontrada.TipoGravitaMessage;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;
import it.contrada.web.util.LoadBundleLanguage;
import it.contrada.web.util.RicercaAnagraficaUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.icesoft.faces.component.selectinputtext.SelectInputText;

public class StampaTesseraView extends BaseView {
	private List<SelectItem> tipoTessereItems;
	private List<SelectItem> annoItems;
	private List<SelectItem> esattoriItems;
	private List<SelectItem> anagraficheItems;
	private StampaTesseraBean stampaTesseraBean;
	private List<AnagraficaDTO> anagraficheTable = new ArrayList<AnagraficaDTO>();
	private List<AnagraficaDTO> anagrafiche;
	private boolean visibleAnagrafiche;
	private List<SelectItem> incassiItems;
	private String dsTipoTessera = null;
	private int nrTessere;
	private String heightCellContenuto = "height:180px";
	private String heightCellMargine = "height:41px";
	private String widthCellContenuto = "width:320px";
	private String barcodeFont = null;
	private boolean ascending;
	private String sortColumnName;
	private static String COGNOME_COLUMN = "cognome";
	private static String FAMIGLIA_COLUMN = "idFamiglia";

	public String getSortColumnName() {
		return sortColumnName;
	}

	public void setSortColumnName(String sortColumnName) {
		this.sortColumnName = sortColumnName;
	}

	public boolean isAscending() {
		return ascending;
	}

	public void setAscending(boolean ascending) {
		this.ascending = ascending;
	}

	public int getNrTessere() {
		if (getStampaTesseraBean() != null
				&& getStampaTesseraBean().getTessere() != null) {
			nrTessere = getStampaTesseraBean().getTessere().size();
		} else {
			nrTessere = 0;
		}
		return nrTessere;
	}

	public void setNrTessere(int nrTessere) {
		this.nrTessere = nrTessere;
	}

	public List<SelectItem> getIncassiItems() {
		return incassiItems;
	}

	public boolean isVisibleAnagrafiche() {
		visibleAnagrafiche = anagraficheTable != null
				&& !anagraficheTable.isEmpty();
		return visibleAnagrafiche;
	}

	public List<AnagraficaDTO> getAnagraficheTable() {
		return anagraficheTable;
	}

	public StampaTesseraBean getStampaTesseraBean() {
		// stampaTesseraBean=
		// (StampaTesseraBean)FacesUtils.findBean("stampaTesseraBean");
		return stampaTesseraBean;
	}

	public void setStampaTesseraBean(StampaTesseraBean stampaTesseraBean) {
		this.stampaTesseraBean = stampaTesseraBean;
	}

	public List<SelectItem> getAnagraficheItems() {
		return anagraficheItems;
	}

	public List<SelectItem> getEsattoriItems() {
		return esattoriItems;
	}

	public List<SelectItem> getAnnoItems() {
		return annoItems;
	}

	public List<SelectItem> getTipoTessereItems() {
		return tipoTessereItems;
	}

	public StampaTesseraView() throws NumberFormatException,
			ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		initDDLAnni();
		initDDLTessere();
		initDDLTipoIncasso();
		initEsattore();
	}

	public void addAnagraficaOnClick(ActionEvent ev)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		AnagraficaDTO anagManuale = null;
		if (getStampaTesseraBean().getAnagSel() != null) {
			// autocompletamento
			anagManuale = getStampaTesseraBean().getAnagSel();

		} else {
			// ricerca Manuale
			anagManuale = RicercaAnagraficaBD
					.ricercaAnagrafica(getStampaTesseraBean().getIdAnagrafica());

		}

		// verifica se già presente
		if (anagManuale != null) {
			for (AnagraficaDTO a : anagraficheTable) {
				if (a.getIdAnagrafica() == anagManuale.getIdAnagrafica()) {
					return;
				}

			}

			// aggiungo anagrafica alle tessere da stampare
			anagraficheTable.add(0, anagManuale);
			getStampaTesseraBean().setAnagSel(null);
			getStampaTesseraBean().setDsAnagrafica(null);
			getStampaTesseraBean().setIdAnagrafica(null);
		}

	}

	public void tipoIncassoValueChange(ValueChangeEvent e) {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {
			int idTipoIncasso = (Integer) e.getNewValue();
			if (idTipoIncasso == TipoIncasso.ESATTORE.getIncasso()) {
				getStampaTesseraBean().setEsattore(-1);
				getStampaTesseraBean().setVisibleEsattore(true);
			} else {

				getStampaTesseraBean().setVisibleEsattore(false);
			}
		}

	}

	public void tipoStampaValueChange(ValueChangeEvent e) {
		// reset tabella
		anagraficheTable = null;
		getStampaTesseraBean().setTessere(null);
		getStampaTesseraBean().setVisibleTessere(false);
	}

	public void updateListAnagrafiche(ValueChangeEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		if (!e.getPhaseId().equals(PhaseId.UPDATE_MODEL_VALUES)) {
			e.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			e.queue();
		} else {

			if (e.getNewValue() != null
					&& !e.getNewValue().toString().isEmpty()) {
				SelectInputText autoComplete = (SelectInputText) e
						.getComponent();
				if (autoComplete.getSelectedItem() != null) {
					AnagraficaDTO anagrafica = (AnagraficaDTO) autoComplete
							.getSelectedItem().getValue();

					getStampaTesseraBean().setAnagSel(anagrafica);
					getStampaTesseraBean().setIdAnagrafica(
							anagrafica.getIdAnagrafica());

				} else {
					anagrafiche = RicercaAnagraficaUtil
							.getAnagraficaByAutocomplete(e.getNewValue()
									.toString());
					anagraficheItems = new ArrayList<SelectItem>();
					if (anagrafiche.isEmpty()) {
						getStampaTesseraBean().setAnagSel(null);
						getStampaTesseraBean().setIdAnagrafica(null);
					}
					for (AnagraficaDTO anagrafica : anagrafiche) {
						anagraficheItems.add(new SelectItem(anagrafica,
								anagrafica.getIntestatario()));

					}

				}
			} else {
				anagraficheItems = null;
				getStampaTesseraBean().setAnagSel(null);
			}
		}
	}

	public void confermaOnClick(ActionEvent e)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante,
			IOException {
		List<Integer> idAnagrafiche;
		int idTipoTessera = getStampaTesseraBean().getTipoTessera();
		int anno = getStampaTesseraBean().getAnno();
		List<TesseraStampataDTO> tessere = null;
		List<Integer> esattori = null;
		Integer tipoIncasso = null;

		getStampaTesseraBean().setRenderNote(false);
		dsTipoTessera = lookUpTessera(getStampaTesseraBean().getTipoTessera());

		if (anagraficheTable != null && !anagraficheTable.isEmpty()) {
			// stampa tessere per anagrafica
			idAnagrafiche = new ArrayList<Integer>();
			for (AnagraficaDTO anag : anagraficheTable) {
				idAnagrafiche.add(anag.getIdAnagrafica());
			}
			tessere = RicercaTipoTesseraBD.recuperaTessereDaStampare(
					idTipoTessera, idAnagrafiche, anno);
		} else {
			// stampa tessera non per anagrafica
			if (getStampaTesseraBean().getTipoIncasso() == TipoIncasso.ESATTORE
					.getIncasso()
					&& getStampaTesseraBean().getEsattore() != -1) {
				// tutti gli esattori
				esattori = new ArrayList<Integer>();
				esattori.add(getStampaTesseraBean().getEsattore());
			}
			if (getStampaTesseraBean().getTipoIncasso() != -1) {
				tipoIncasso = getStampaTesseraBean().getTipoIncasso();
			}
			// tessere fine anno , bo,cp ed rd che hanno pagato
			if (tipoIncasso != null && tipoIncasso == 100) {
				Integer[] tipoIncassi = { 1, 4, 6 };
				tessere = RicercaTipoTesseraBD
						.recuperaTessereDaStampareFineAnno(idTipoTessera,
								tipoIncassi, anno);
			} else {
				
				
				tessere = RicercaTipoTesseraBD.recuperaTessereDaStampare(
						idTipoTessera, tipoIncasso, esattori, anno);
				
				/*
				tessere = RicercaTipoTesseraBD
				.recuperaTessereDaStampareFineAnno(idTipoTessera,
						new Integer[]{tipoIncasso}, anno);*/
			}
		}

		getStampaTesseraBean().setTessere(tessere);

		getStampaTesseraBean().setVisibleTessere(
				tessere != null && !tessere.isEmpty());

		if (tessere != null && !tessere.isEmpty()) {
			anagraficheTable = null;
			createPDF(tessere);
		} else {

			getStampaTesseraBean().setNote(
					LoadBundleLanguage.getMessage("tessereNonPresenti"));
			getStampaTesseraBean().setRenderNote(true);
			writeInfoMessage(TipoGravitaMessage.INFO, getStampaTesseraBean().getNote());
		}
		
		//imposto in sessione le tessere per la stampa pdf da servlet
		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				Costante.TESSERE_PDF_SESSION, tessere);

	}

	public void eliminaAnagraficaOnClick(ActionEvent e) {
		AnagraficaDTO anag = (AnagraficaDTO) e.getComponent().getAttributes()
				.get("anagrafica");
		anagraficheTable.remove(anag);
		getStampaTesseraBean().setRenderNote(false);
	}

	private void initDDLAnni() throws NumberFormatException,
			ContradaExceptionBloccante {
		GregorianCalendar gCalendar = new GregorianCalendar();
		int annoInCorso = gCalendar.get(GregorianCalendar.YEAR);
		annoItems = new ArrayList<SelectItem>();

		int anniRendicontabili = Integer.parseInt(Configuration
				.getProperty("anniStampaTessere"));
		for (int i = 0; i <= anniRendicontabili; i++) {
			annoItems.add(new SelectItem(Integer.valueOf(annoInCorso - i),
					Integer.toString(annoInCorso - i)));
		}

	}

	private void initDDLTessere() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		tipoTessereItems = new ArrayList<SelectItem>();
		List<TipoTesseraDTO> tessere = RicercaTipoTesseraBD.elencaTipoTessera();
		for (TipoTesseraDTO tes : tessere) {
			tipoTessereItems.add(new SelectItem(tes.getIdTipoTessera(), tes
					.getDsTipoTessera()));
		}

	}

	private void initEsattore() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		esattoriItems = new ArrayList<SelectItem>();
		List<EsattoreDTO> esattori = RicercaEsattoreBD.elenca();
		esattoriItems.add(new SelectItem(-1, "-- Seleziona Esattore --"));
		for (EsattoreDTO esattore : esattori) {
			esattoriItems.add(new SelectItem(esattore.getIdEsattore(), esattore
					.getDsEsattore()));
		}

	}

	private void initDDLTipoIncasso() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		incassiItems = new ArrayList<SelectItem>();
		List<TipoIncassoDTO> incassi = RicercaIncassoBD.elencaTipoIncasso();
		if (incassi != null && !incassi.isEmpty()) {
			incassiItems.add(new SelectItem(-1, "-- Tutti --"));
			for (TipoIncassoDTO incasso : incassi) {
				incassiItems.add(new SelectItem(incasso.getIdTipoIncasso(),
						incasso.getDsTipoIncasso()));
			}
			incassiItems.add(new SelectItem(100, "Fine Anno, RD-CP-BO"));
		}

	}
	
	

	private void createPDF(List<TesseraStampataDTO> tessere)
			throws IOException, ContradaExceptionBloccante {

		// PrintWriter writer= response.getWriter();

		barcodeFont = Configuration.getProperty("fontBarcode");

		StringBuilder writer = new StringBuilder();

		// tabella esterna
		writer
				.append("<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" ><meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" >");
		writer
				.append("<style type=\"text/css\"> @page {margin-top: 56px; margin-left: 75px; margin-right:68px; margin-bottom:68px}</style>");

		writer
				.append("<body style=\"margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-size: 10px; color: blue;\">");

		writer

		.append("<table style=\"padding: 0px;\">");
		int i = 1;
		int tesserePerPagina = 4;
		for (TesseraStampataDTO tes : tessere) {
			if ((i) % tesserePerPagina == 0) {
				// cambia pagina

				scriviRigaContenuto(writer, tes);
				writer.append("</table>");
				writer.append("<p style=\"page-break-after:always\"></p>");
				writer.append("<table style=\"padding: 0px;\">");

			} else {
				scriviRigaContenuto(writer, tes);
				scriviRigaMargine(writer);

			}
			i++;
		}

		if ((i) % tesserePerPagina != 0) {
			writer.append("</table>");
		}
		writer.append("</body></html>");

		// String str1=writer.toString();
		// String str = "<html><body>ssss</body></html>";

		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				Costante.HTML_PDF, writer.toString());

	}

	private void scriviRigaMargine(StringBuilder writer) throws IOException {

		writer.append("<tr>");

		// celle contenuto
		writer.append(String.format("<td style=\"%s;%s;\">", heightCellMargine,
				widthCellContenuto));
		writer.append("</td>");

		writer.append(String.format("<td style=\"%s;%s;\">", heightCellMargine,
				widthCellContenuto));
		writer.append("</td>");

		writer.append("</tr>");
	}

	private void scriviRigaContenuto(StringBuilder writer,
			TesseraStampataDTO tes) throws IOException {

		writer.append("<tr>");
		String tesseraAnno = String.format("%s-%s", Long.toString(tes
				.getIdTessera()), Integer.toString(getStampaTesseraBean()
				.getAnno()));

		// cella contenuto esattore

		writer
				.append(String
						.format(
								"<td style=\"%s;%s;padding:15px;text-align:left;vertical-align:bottom;font-size:12px;color:black\">",
								heightCellContenuto, widthCellContenuto));
		writer.append(String.format(" <font face='%s' size='6'>%s</font></br>",
				barcodeFont, tesseraAnno));
		writer.append(String.format("Nr. Tes. %s - Nr. Anag %s - ", Long
				.toString(tes.getIdTessera()), Long.toString(tes
				.getIdAnagrafica())));
		writer.append(String.format("Nr. Fam. %s </br>", Integer.toString(tes
				.getIdFamiglia())));
		writer.append(String.format("%s %s</br>", tes.getCognome(), tes
				.getNome()));
		writer.append(String.format("%s</br> %s</br>", tes.getIndirizzo(), tes
				.getCapProvincia()));

		if (tes.getFisso() != null && !tes.getFisso().isEmpty()) {
			writer.append(String.format("%s ", tes.getFisso()));
		}
		if (tes.getMail() != null && !tes.getMail().isEmpty()) {
			writer.append(String.format("%s ", tes.getMail()));
		}
		if (tes.getCell() != null && !tes.getCell().isEmpty()) {
			writer.append(String.format("%s ", tes.getCell()));
		}
		if (tes.getNote() != null && !tes.getNote().isEmpty()) {
			writer.append(String.format("</br>%s ", tes.getNote()));
		}
		if (tes.getEsattore() != null && !tes.getEsattore().isEmpty()) {
			writer
					.append(String.format("</br>Esattore:%s ", tes
							.getEsattore()));
		}

		writer.append(String.format("</br>%s ANNO %s", dsTipoTessera
				.toUpperCase(), getStampaTesseraBean().getAnno()));

		double importo = tes.getQuota() / 100;
		writer
				.append(String.format("</br>%s - %.2f", tes.getCarica(),
						importo));

		writer.append("</td>");

		// cella contenuto protettore
		writer
				.append(String
						.format(
								"<td style=\"%s;%s;padding:15px;text-align:left;vertical-align:bottom;color:blue;font-size:12px;font-weight:bold;\">",
								heightCellContenuto, widthCellContenuto));
		writer.append(String.format("%s %s </br>", tes.getCognome()
				.toUpperCase(), tes.getNome().toUpperCase()));
		writer.append(String.format("%s ANNO %s", dsTipoTessera.toUpperCase(),
				getStampaTesseraBean().getAnno()));
		writer
				.append(String
						.format(
								"<span style=\"font-size:8px;font-weight:bold;\"></br>%s %s </span>",
								tes.getCarica().toUpperCase(), Long
										.toString(tes.getIdAnagrafica())));
		writer.append("</td>");

	}

	private String lookUpTessera(Integer idTipoTessera) {
		if (tipoTessereItems != null) {
			for (SelectItem item : tipoTessereItems) {
				if (item.getValue().equals(idTipoTessera)) {
					return item.getLabel();
				}
			}
		}
		return "";
	}

	protected void sort() {
	        Comparator<TesseraStampataDTO> comparator = new Comparator<TesseraStampataDTO>() {
	            public int compare(TesseraStampataDTO t1, TesseraStampataDTO t2) {	                
	                if (sortColumnName == null) {
	                    return 0;
	                }
	                if (sortColumnName.equals(COGNOME_COLUMN)) {
	                    return ascending ?
	                            new Integer(t1.getCognome()).compareTo(new Integer(t2.getCognome())) :
	                            new Integer(t2.getCognome()).compareTo(new Integer(t1.getCognome()));
	                } else if (sortColumnName.equals(FAMIGLIA_COLUMN)) {
	                    return ascending ?new Integer(new Integer(t1.getIdFamiglia()).compareTo(new Integer(t2.getIdFamiglia()))) :
	                    	new Integer(new Integer(t2.getIdFamiglia()).compareTo(new Integer(t1.getIdFamiglia())));
	                } 
	                 else return 0;
	        
	            }
	        };
	        
	        Collections.sort(getStampaTesseraBean().getTessere(), comparator);
	    }
}
