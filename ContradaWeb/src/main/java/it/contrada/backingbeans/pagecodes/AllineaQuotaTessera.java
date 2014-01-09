package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dto.TesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.event.ActionEvent;

public class AllineaQuotaTessera {

	private java.util.Date dtRiferimento;
	private String messaggio;
	private List<TesseraDTO> tessereAll;
	private boolean visibleAnagrafiche;
	private int nrAnags;

	public int getNrAnags() {
		return nrAnags;
	}

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public List<TesseraDTO> getTessereAll() {
		return tessereAll;
	}

	public String getMessaggio() {
		return messaggio;
	}

	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	public java.util.Date getDtRiferimento() {
		return dtRiferimento;
	}

	public void setDtRiferimento(java.util.Date dtRiferimento) {
		this.dtRiferimento = dtRiferimento;
	}

	public AllineaQuotaTessera() {
		// TODO Auto-generated constructor stub
		GregorianCalendar gc = new GregorianCalendar();

		GregorianCalendar gc1 = new GregorianCalendar(gc
				.get(GregorianCalendar.YEAR), GregorianCalendar.DECEMBER, 31);

		setDtRiferimento(gc1.getTime());
	}

	public void cercaTesseraClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		tessereAll = RicercaTipoTesseraBD
				.ricercaTessereDaAllineare(getDtRiferimento());
		visibleAnagrafiche = !tessereAll.isEmpty();
		nrAnags = tessereAll.size();
		if (visibleAnagrafiche) {
			setMessaggio("");
		} else {
			setMessaggio("Nessuna tessera da allineare alla data di riferimento.");
		}

	}

	public void allineaQuotaClick(ActionEvent av)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		int tessere = GestioneTesseraBD.allineaQuota(getDtRiferimento());
		setMessaggio("Operazione eseguita con successo, allineate " + tessere
				+ " tessere");
	}

}
