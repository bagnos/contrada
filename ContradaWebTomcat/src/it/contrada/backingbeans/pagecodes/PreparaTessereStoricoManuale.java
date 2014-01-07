package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Errori;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class PreparaTessereStoricoManuale {

	private int anno;
	private List<SelectItem> anni;
	private String messaggio;
	private Integer idAnagrafica;
	private List<AnagraficaDTO> anagrafiche;
	private boolean visibleAnagrafiche;

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public void setVisibleAnagrafiche(boolean visibleAnagrafiche) {
		this.visibleAnagrafiche = visibleAnagrafiche;
	}

	public List<AnagraficaDTO> getAnagrafiche() {
		return anagrafiche;
	}

	public void setAnagrafiche(List<AnagraficaDTO> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}

	public Integer getIdAnagrafica() {
		return idAnagrafica;
	}

	public void setIdAnagrafica(Integer idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
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

	public void setAnni(List<SelectItem> anni) {
		this.anni = anni;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public PreparaTessereStoricoManuale() {
		// TODO Auto-generated constructor stub
		GregorianCalendar gCalendar = new GregorianCalendar();
		int annoInCorso = gCalendar.get(GregorianCalendar.YEAR);
		anni = new ArrayList<SelectItem>();
		anni.add(new SelectItem(Integer.valueOf(annoInCorso - 1), Integer
				.toString(annoInCorso - 1)));
		anni.add(new SelectItem(Integer.valueOf(annoInCorso), Integer
				.toString(annoInCorso)));
		setAnno(annoInCorso);
		anagrafiche = new ArrayList<AnagraficaDTO>();
		setVisibleAnagrafiche(false);
	}

	public void preparaAnnoClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		List<Integer> anags=new ArrayList<Integer>();
		if (getAnagrafiche().isEmpty())
		{
			throw new ContradaExceptionBloccante(Errori.ANAGS_NON_PRES);
		}
		
		for (AnagraficaDTO anag: getAnagrafiche())
		{
			anags.add(anag.getIdAnagrafica());
		}
		
		//int tessere = GestioneTesseraBD.preparaTessereStoricoManuale(anno, anags);
		//setMessaggio("Operazione eseguita con successo, preparate " + tessere
		//		+ "  tessere");
	}

	public void aggiungiAnagraficaClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		AnagraficaDTO anagraficheAdd = RicercaAnagraficaBD
				.ricercaAnagrafica(getIdAnagrafica());
		if (anagraficheAdd!=null) {
			getAnagrafiche().add(anagraficheAdd);
			setVisibleAnagrafiche(true);
		}
	}

	public void eliminaAnagraficaOnClick(ActionEvent e) {
		int idAnagrafica = Integer.valueOf(e.getComponent().getAttributes()
				.get("idAnagrafica").toString());
		AnagraficaDTO recToDelete = null;
		for (AnagraficaDTO rec : getAnagrafiche()) {
			if (rec.getIdAnagrafica()==idAnagrafica) {
				recToDelete = rec;
				break;
			}
		}
		if (recToDelete != null) {
			getAnagrafiche().remove(recToDelete);
		}
		setVisibleAnagrafiche(!anagrafiche.isEmpty());
	}

}
