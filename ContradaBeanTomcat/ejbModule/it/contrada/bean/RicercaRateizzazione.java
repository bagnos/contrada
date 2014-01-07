package it.contrada.bean;

import it.contrada.comparator.RateizzazioneAnagComparator;
import it.contrada.dao.interfaces.IRateizzazioneDAO;
import it.contrada.dominio.dto.TipoRateizzazioneDTO;
import it.contrada.dto.IncassoAnnualeDTO;
import it.contrada.dto.ParmIncassoDTO;
import it.contrada.dto.RateizzazioneDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaRateizzazione;
import it.contrada.util.DecodificaErrore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.metadata.Database;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Session Bean implementation class RicercaRateizzazione
 */

public class RicercaRateizzazione implements IRicercaRateizzazione {

	/**
	 * @uml.property  name="rateizzazioneDao"
	 * @uml.associationEnd  
	 */
	@Autowired
	private IRateizzazioneDAO rateizzazioneDao;

	private static Log log = LogFactory.getLog(RicercaRateizzazione.class);

	/**
	 * @param rateizzazioneDao
	 * @uml.property  name="rateizzazioneDao"
	 */
	public void setRateizzazioneDao(IRateizzazioneDAO rateizzazioneDao) {
		this.rateizzazioneDao = rateizzazioneDao;
	}

	/**
	 * Default constructor.
	 */
	public RicercaRateizzazione() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see IRicercaRateizzazione#elencaTipoRateizzazione()
	 */
	public List<TipoRateizzazioneDTO> elencaTipoRateizzazione()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoRateizzazioneDTO> tipoRateizzazione = null;
		try {

			tipoRateizzazione = rateizzazioneDao.getTipoRateizzazione();
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoRateizzazione;
	}

	public List<TipoRateizzazioneDTO> ricercaPerTessera(int idTessera)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoRateizzazioneDTO> tipoRateizzazione = null;
		try {

			tipoRateizzazione = rateizzazioneDao
					.getTipoRateizzazionePerTessera(idTessera);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoRateizzazione;
	}

	public List<TipoRateizzazioneDTO> ricercaPerTesseraIncasso(
			int idTipoTessera, int idTipoIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<TipoRateizzazioneDTO> tipoRateizzazione = null;
		try {

			tipoRateizzazione = rateizzazioneDao
					.getTipoRateizzazionePerTesseraIncasso(idTipoTessera,
							idTipoIncasso);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return tipoRateizzazione;
	}

	public List<RateizzazioneDTO> ricercaPerAnagrafica(int idAnagrafica)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RateizzazioneDTO> rateizzazioni = null;
		try {

			rateizzazioni = rateizzazioneDao
					.getRateizzazionePerAnagrafica(idAnagrafica);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return rateizzazioni;

	}

	public List<RateizzazioneDTO> ricercaRate(ParmIncassoDTO parmIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RateizzazioneDTO> rateizzazioni = null;
		try {

			rateizzazioni = rateizzazioneDao.getRate(parmIncasso);

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return rateizzazioni;
	}

	public List<RateizzazioneDTO> ricercaRateByAnagrafica(
			ParmIncassoDTO parmIncasso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RateizzazioneDTO> rateizzazioni = null;
		List<RateizzazioneDTO> rateizzazioniRaggrup = new ArrayList<RateizzazioneDTO>();
		try {

			rateizzazioni = rateizzazioneDao.getRate(parmIncasso);

			if (!rateizzazioni.isEmpty()) {
				Collections.sort(rateizzazioni,
						new RateizzazioneAnagComparator());
				rateizzazioniRaggrup = new ArrayList<RateizzazioneDTO>();
				int idAnag = rateizzazioni.get(0).getIdAnagrafica();
				RateizzazioneDTO rataRaggr=new RateizzazioneDTO();
				rataRaggr.setAnagrafica(rateizzazioni.get(0).getAnagrafica());
				rataRaggr.setIdAnagrafica(rateizzazioni.get(0).getIdAnagrafica());
				rataRaggr.setDsTessera(rateizzazioni.get(0).getDsTessera());
				rateizzazioniRaggrup.add(rataRaggr);
				for (RateizzazioneDTO rata:rateizzazioni)
				{
					if (rata.getIdAnagrafica()==idAnag)
					{
						rataRaggr.setImRata(rata.getIdRata());
						rataRaggr.setImRataIncassata(rata.getImRataIncassata());
					}
					else
					{						
						rataRaggr=new RateizzazioneDTO();
						rataRaggr.setAnagrafica(rata.getAnagrafica());
						rataRaggr.setIdAnagrafica(rata.getIdAnagrafica());
						rataRaggr.setDsTessera(rata.getDsTessera());
						idAnag=rata.getIdAnagrafica();
						rateizzazioniRaggrup.add(rataRaggr);
					}
				}
			}

			return rateizzazioniRaggrup;

		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		
	}

	public List<RateizzazioneDTO> ricercaPerAnagrafica(int idAnagrafica,
			int idTipoTessera) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RateizzazioneDTO> rateizzazioni = null;
		try {

			rateizzazioni = rateizzazioneDao.getRateizzazionePerAnagrafica(
					idAnagrafica, idTipoTessera);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return rateizzazioni;

	}

	public List<RateizzazioneDTO> ricercaPerAnagrafica(int idAnagrafica,
			Integer[] idTipoTessera, Integer annoDa, Integer annoA)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<RateizzazioneDTO> rateizzazioni = null;
		try {

			rateizzazioni = rateizzazioneDao.getRateizzazionePerAnagrafica(
					idAnagrafica, idTipoTessera, annoDa, annoA);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return rateizzazioni;

	}

	public List<IncassoAnnualeDTO> recuperaIncassoAnnuale(int anno,
			List<Integer> idTipoTessera) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		List<IncassoAnnualeDTO> incasso = null;
		try {

			incasso = rateizzazioneDao.getIncassoAnnuale(anno, idTipoTessera);
		}

		catch (Exception ex) {
			log.error(ex);
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), ex);
		}
		return incasso;
	}

}
