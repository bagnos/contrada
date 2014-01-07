package it.contrada.interfaces;

import it.contrada.dto.DistintaDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.enumcontrada.TipoIncassoRid;
import it.contrada.enumcontrada.TipoMeseIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IGestioneRateizzazione {

	public DistintaDTO inserisciRateizzazione(
			List<RateizzazioneDTO> rateizzazioni)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public void eliminaRateizzazione(RateizzazioneDTO rateizzazione)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	
}
