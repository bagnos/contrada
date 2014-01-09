package it.contrada.backingbeans;

import it.contrada.businessdelegate.RicercaComuneBD;
import it.contrada.dto.ComuneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class Comune {

	private List<SelectItem> comuniPerStatoDefault = null;

	public Comune() {
		// TODO Auto-generated constructor stub
	}

	public List<SelectItem> getComuniPerStatoDefault()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		
		//String cdIsoStato=FacesContext.getCurrentInstance().getExternalContext().getInitParameter("statoDefault");
		Configuration conf=new Configuration();
		String cdIsoStato=conf.getProperty(Costante.STATO_DEFAULT);
		SelectItem comuneItem = null;

		comuniPerStatoDefault = new ArrayList<SelectItem>();
	
		List<ComuneDTO> comuniDTO = RicercaComuneBD.ricercaPerStato(cdIsoStato);
		if (comuniDTO != null) {
			for (ComuneDTO comuneDTO : comuniDTO) {
				comuneItem = new SelectItem(comuneDTO.getCdComune(), comuneDTO
						.getDsComune());
				comuniPerStatoDefault.add(comuneItem);
				
			}
		}

		return comuniPerStatoDefault;
	}

}
