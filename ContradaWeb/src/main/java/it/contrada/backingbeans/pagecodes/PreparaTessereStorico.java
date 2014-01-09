package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

public class PreparaTessereStorico {

	private int anno;
	private List<SelectItem> anni;
	private String messaggio;
	
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

	public PreparaTessereStorico() {
		// TODO Auto-generated constructor stub
		GregorianCalendar gCalendar=new GregorianCalendar();		
		int annoInCorso=gCalendar.get(GregorianCalendar.YEAR);
		anni=new ArrayList<SelectItem>();
		anni.add(new SelectItem(Integer.valueOf(annoInCorso-1), Integer.toString(annoInCorso-1)));
		anni.add(new SelectItem(Integer.valueOf(annoInCorso), Integer.toString(annoInCorso)));
		setAnno(annoInCorso);
	}
	
	public void preparaAnnoClick(ActionEvent av) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		//int tessere=GestioneTesseraBD.preparaTessereStorico(getAnno());
		//setMessaggio("Operazione eseguita con successo, preparate " +tessere +"  tessere");
	}

}
