package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.interfaces.IRicercaFlusso;
import it.contrada.preautrid.dto.RidEsitoPreautDTO;

import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaFlussoBD {

	private static IRicercaFlusso ricercaFlusso;
	private static Log log = LogFactory.getLog(RicercaFlussoBD.class);

	private RicercaFlussoBD() {
	}

	static {
		/*
		if (ricercaFlusso == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				ricercaFlusso = (IRicercaFlusso) ctx
						.lookup("ContradaEAR/RicercaFlusso/local");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e.getMessage(), e);
				throw new RuntimeException(e);
			}

		}*/
		ricercaFlusso=ContradaPojoFactory.getRicercaFlussoIstance();
	}

	public static List<FlussoEsitoDTO> ricercaFlussoEsito(TipoFlusso flusso,
			int esitiMax) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaFlusso.ricercaFlusso(flusso, esitiMax);
	}

	public static List<RidEsitoPreautDTO> ricercaEsitoPreautPerData(Date dtDa,
			Date dtA) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		java.sql.Date dtSqlDa = new java.sql.Date(dtDa.getTime());
		java.sql.Date dtSqlA = new java.sql.Date(dtA.getTime());
		;

		return ricercaFlusso.ricercaEsitoPreautPerData(dtSqlDa, dtSqlA);

	}
	
	public static List<RidEsitoPreautDTO> ricercaEsito(int idRid,Date dtDa,
			Date dtA) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		java.sql.Date dtSqlDa = new java.sql.Date(dtDa.getTime());
		java.sql.Date dtSqlA = new java.sql.Date(dtA.getTime());
		;

		return ricercaFlusso.ricercaEsitoPreaut(idRid, dtSqlDa, dtSqlA);

	}


	public static List<RidEsitoPreautDTO> ricercaEsitoPreautPerRid(int idRid)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub

		return ricercaFlusso.ricercaEsitoPreautPerRid(idRid);

	}

	public static List<TipoCasualiPreautDTO> elencaCausaliPreaut()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub

		return ricercaFlusso.elencaCausaliPreaut();

	}
	
	public static List<TipoCasualiIncassoRidDTO> elencaCausaliIncassiRid() throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		return ricercaFlusso.elencaCausaliIncassiRid();
	}
	
	public static List<IncassoRidDTO> ricercaEsitoIncassoRid(Integer anno,
			Integer mese, Integer tipoIncassoRid, Integer nrRid,
			Integer causaleIncasso) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		return ricercaFlusso.ricercaEsitoincassoRid(anno, mese, tipoIncassoRid, nrRid, causaleIncasso);
	}
}
