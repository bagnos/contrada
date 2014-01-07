package it.contrada.bean;

import it.contrada.dto.OperazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaOperazione;
import it.contrada.pojo.Operazione;
import it.contrada.util.Constanti;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaOperazione
 */
/*
 * @Stateless
 * 
 * @Local( { IRicercaOperazione.class })
 * 
 * @Interceptors(SpringBeanAutowiringInterceptor.class)
 */
public class RicercaOperazione implements IRicercaOperazione {

	/**
	 * @uml.property  name="operazioneBO"
	 * @uml.associationEnd  
	 */
	@Autowired
	private Operazione operazioneBO;

	/**
	 * @param operazioneBO
	 * @uml.property  name="operazioneBO"
	 */
	public void setOperazioneBO(Operazione operazioneBO) {
		this.operazioneBO = operazioneBO;
	}

	/**
	 * Default constructor.
	 */
	public RicercaOperazione() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @throws ContradaExceptionBloccante
	 * @throws ContradaExceptionNonBloccante
	 * @see IRicercaOperazione#elencaUltimeOperazioni()
	 */
	public List<OperazioneDTO> elencaUltimeOperazioni()
			throws ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		return operazioneBO
				.elencaUltimeOperazioni(Constanti.LAST_NR_OPERAZIONI);
	}

}
