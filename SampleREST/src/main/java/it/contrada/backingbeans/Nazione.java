package it.contrada.backingbeans;

import it.contrada.businessdelegate.RicercaNazioneBD;
import it.contrada.dto.NazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;

import java.util.ArrayList;
import java.util.List;


import javax.faces.model.SelectItem;

public class Nazione {

	public Nazione() {
		// TODO Auto-generated constructor stub
	}

	private List<SelectItem> nazioni = null;
	private String value = null;

	public List<SelectItem> getNazioni() throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		SelectItem nazioneItem = null;

		if (nazioni == null) {

			nazioni = new ArrayList<SelectItem>();
			RicercaNazioneBD nazioneBD = new RicercaNazioneBD();
			List<NazioneDTO> nazioniDTO = nazioneBD.elencaNazione();
			nazioni.add(new SelectItem(null, null));
			if (nazioniDTO != null) {
				for (NazioneDTO nazioneDTO : nazioniDTO) {
					nazioneItem = new SelectItem(nazioneDTO.getCdNazione(),
							nazioneDTO.getDsNazione());
					nazioni.add(nazioneItem);
				}
			}

		}
		return nazioni;
	}

	

}
