package it.contrada.dao.interfaces;

import it.contrada.dto.OperazioneDTO;

import java.util.List;

public interface IOperazioneDAO {
	public int inserisciOperazione (OperazioneDTO operazione) throws Exception;;
	public List<OperazioneDTO> getUltimeOperazioni(int nRows) throws Exception;
}
