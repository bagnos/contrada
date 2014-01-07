package it.contrada.dao.interfaces;

import it.contrada.dto.NazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface INazioneDAO {
	public List<NazioneDTO> getNazione()  throws Exception;

}
