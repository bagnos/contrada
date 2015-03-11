package it.contrada.backingbeans.pagecodes;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public class ListaRitiroTessere {

	private List<AnagraficaDTO> anags;
	private int nrAnagrafiche;
	private boolean visibleAnagrafiche;
	private int nrAnagraficheRitirate;

	public int getNrAnagraficheRitirate() {
		return nrAnagraficheRitirate;
	}

	public boolean isVisibleAnagrafiche() {
		return visibleAnagrafiche;
	}

	public int getNrAnagrafiche() {
		return nrAnagrafiche;
	}

	public List<AnagraficaDTO> getAnags() {
		return anags;
	}

	public ListaRitiroTessere() throws NumberFormatException,
			ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		anags = RicercaAnagraficaBD.elencoRitiroTessere();
		visibleAnagrafiche = !anags.isEmpty();
		nrAnagrafiche = anags.size();
		nrAnagraficheRitirate=0;
		for (AnagraficaDTO a:anags)
		{
			if (a.getDtRitiroTessera()!=null)
			{
				nrAnagraficheRitirate++;
			}
		}

	}

}
