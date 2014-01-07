package it.contrada.bean;

import it.contrada.bean.utils.Utente;
import it.contrada.dao.interfaces.IRidDAO;
import it.contrada.dao.interfaces.ITesseraDAO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IGestioneRid;
import it.contrada.pojo.Operazione;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class InserisciRid
 */

public class GestioneRid implements IGestioneRid {
	private static Log log = LogFactory.getLog(GestioneRid.class);



	/**
	 * @uml.property  name="ridDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IRidDAO ridDao;

	/**
	 * @uml.property  name="tesseraDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private ITesseraDAO tesseraDao;
	
	/**
	 * @uml.property  name="operazioneBO"
	 * @uml.associationEnd  
	 */
	@Autowired
	private Operazione operazioneBO;
	
	

	/**
	 * @param ridDao
	 * @uml.property  name="ridDao"
	 */
	public void setRidDao(IRidDAO ridDao) {
		this.ridDao = ridDao;
	}

	/**
	 * @param tesseraDao
	 * @uml.property  name="tesseraDao"
	 */
	public void setTesseraDao(ITesseraDAO tesseraDao) {
		this.tesseraDao = tesseraDao;
	}

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
	public GestioneRid() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IInserisciRid#inserisciRid(RidDTO)
	 */
	public RidDTO inserisciRid(RidDTO ridDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			OperazioneDTO operazioneDTO=operazioneBO.inserisciOperazione(FacesContext.getCurrentInstance().getExternalContext()
					.getUserPrincipal().getName(), String.format(Constanti.OPERAZIONE_INSERT_RID, ridDTO.getIntestatarioRid()));
			ridDTO.setOperazione(operazioneDTO);
			
			ridDao.insertRid(ridDTO);

			if (log.isTraceEnabled()) {
				log.trace("Rid inserito, id=" + ridDTO.getNrRid()
						+ " intestatario " + ridDTO.getIntestatarioRid());
			}

			return ridDTO;
		}
		catch (ContradaExceptionBloccante ex) {
		
			log.error(ex);
			throw ex;
		}
		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}

	}

	public void aggiornaRid(RidDTO ridDTO) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		
		try {
			OperazioneDTO operazioneDTO=operazioneBO.inserisciOperazione(FacesContext.getCurrentInstance().getExternalContext()
					.getUserPrincipal().getName(), String.format(Constanti.OPERAZIONE_INSERT_RID, ridDTO.getIntestatarioRid()));
			ridDTO.setOperazione(operazioneDTO);
			
			//aggiorno la tabella rid
			ridDao.updateRid(ridDTO);

			if (log.isTraceEnabled()) {
				log.trace("Rid aggiornato, id=" + ridDTO.getNrRid()
						+ " intestatario " + ridDTO.getIntestatarioRid());
			}

			//imposto a null il numero rid tutte le tessere in essere che hanno numero rid 
			tesseraDao.annullaRid(ridDTO.getNrRid().intValue());
			if (log.isTraceEnabled()) {
				log.trace("Eliminato rid su tutte le tessere, id="
						+ ridDTO.getNrRid() + " intestatario "
						+ ridDTO.getIntestatarioRid());
			}
			
			//aggiorno le tessere dei memnri rid con il nuovo numero rid
			updateMembriRid(ridDTO);

			
		}
		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}

		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}

	public RidDTO inserisciRidConMembri(RidDTO ridDTO)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		try {

			OperazioneDTO operazioneDTO=operazioneBO.inserisciOperazione(Utente.getUser(), String.format(Constanti.OPERAZIONE_INSERT_RID, ridDTO.getIntestatarioRid()));
			ridDTO.setOperazione(operazioneDTO);
			
			//inserisco un nuovo rid
			ridDao.insertRid(ridDTO);

			if (log.isTraceEnabled()) {
				log.trace("Rid inserito, id=" + ridDTO.getNrRid()
						+ " intestatario " + ridDTO.getIntestatarioRid());
			}
			
			//aggiorno le tessere dei membri rid con il nuovo numero rid
			updateMembriRid(ridDTO);

			return ridDTO;
		}
		catch (ContradaExceptionBloccante ex) {
			log.error(ex);
			throw ex;
		}
		// TODO Auto-generated catch block
		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
	}
	
	private void updateMembriRid(RidDTO ridDTO) throws Exception
	{
		List<Integer> idTessere = new ArrayList<Integer>();
		
		if (!ridDTO.getMembri().isEmpty()) {

			for (MembroRidDTO membro : ridDTO.getMembri()) {

				idTessere.add(membro.getIdTessera());

			}
			int rows = tesseraDao.aggiornaRidTessera(idTessere, ridDTO
					.getNrRid().intValue());

			if (rows != idTessere.size()) {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("3"));
			}

			if (log.isTraceEnabled()) {
				log.trace("Tessere aggiornate con il nuovo rid, id="
						+ ridDTO.getNrRid() + " intestatario "
						+ ridDTO.getIntestatarioRid());
			}
		}
		return;
	}

	
	
}
