package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaRid;
import it.contrada.util.DecodificaErrore;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaRidBD {
	private static IRicercaRid ricercaRid;
	private static Log log = LogFactory.getLog(RicercaRidBD.class);

	static {
		/*
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ricercaRid = (IRicercaRid) ctx
					.lookup("ContradaEAR/RicercaRid/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}*/
		ricercaRid=ContradaPojoFactory.getRicercaRidIstance();

	}

	public static List<MembroRidDTO> ricercaPerNomeCognome(String nome,
			String cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaRid.ricercaPerNomeCognome(nome, cognome);

	}

	public static List<MembroRidDTO> ricercaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaRid.ricercaPerCognome(cognome);

	}

	public static List<MembroRidDTO> ricercaPerCognomeParziale(
			String matchCognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaRid.ricercaPerCognomeParazile(matchCognome);

	}
	
	public static List<MembroRidDTO> ricercaPerCognomeNomeParziale(
			String matchCognome,String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaRid.ricercaPerCognomeNomeParzaile(matchCognome,nome);

	}

	public static List<TipoStatoRidDTO> elencaStati()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaRid.elencaStati();
	}

	public static RidDTO ricercaPerId(int idRid)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaRid.ricercaPerId(idRid);

	}

	public static List<TipoIncassoDTO> elencaTipoIncassiRid()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaRid.elencaTipoIncassiRid();

	}

	public static List<MembroRidDTO> ricercaPerRid(int idRid)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		// TODO Auto-generated method stub
		return ricercaRid.ricercaPerRid(idRid);
	}
	
	public static List<RidDTO> ricercaPerStato(List<Integer> cdStato)
	throws ContradaExceptionBloccante, ContradaExceptionNonBloccante
	{
		return ricercaRid.ricercaPerStato(cdStato);
	}

}
