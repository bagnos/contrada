package it.contrada.pojo;

import java.util.List;

import it.contrada.dao.interfaces.IOperazioneDAO;
import it.contrada.dto.OperazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.util.DecodificaErrore;

/**
 * @author  S506273
 */
public class Operazione {

	/**
	 * @uml.property  name="operazioneDao"
	 * @uml.associationEnd  
	 */
	private IOperazioneDAO operazioneDao;

	/**
	 * @param operazioneDao
	 * @uml.property  name="operazioneDao"
	 */
	public void setOperazioneDao(IOperazioneDAO operazioneDao) {
		this.operazioneDao = operazioneDao;
	}

	public OperazioneDTO inserisciOperazione(String user, String dsOperazione)
			throws ContradaExceptionBloccante {
		try {
			OperazioneDTO operazione = new OperazioneDTO();
			operazione.setUser(user);
			operazione.setDsOperazione(dsOperazione);
			operazioneDao.inserisciOperazione(operazione);
			return operazione;
		} catch (Exception ex) {
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public List<OperazioneDTO> elencaUltimeOperazioni(int nrRows) throws ContradaExceptionNonBloccante {
		try {
			return operazioneDao.getUltimeOperazioni(nrRows);
		} catch (Exception ex) {
			throw new ContradaExceptionNonBloccante(DecodificaErrore.get5018(), ex);
		}
	}
}
