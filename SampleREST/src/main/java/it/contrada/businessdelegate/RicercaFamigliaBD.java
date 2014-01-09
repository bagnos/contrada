package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaFamiglia;
import it.contrada.web.util.Errori;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaFamigliaBD {
	private static IRicercaFamiglia ricercaFamiglia;
	private static Log log = LogFactory.getLog(RicercaFamigliaBD.class);

	static {
		/*
		if (ricercaFamiglia == null) {
			InitialContext ctx;
			try {
				ctx = new InitialContext();
				ricercaFamiglia = (IRicercaFamiglia) ctx
						.lookup("ContradaEAR/RicercaFamiglia/local");

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				log.error(e);
				throw new RuntimeException(e);
			}

		}*/
		ricercaFamiglia=ContradaPojoFactory.getRicercaFamigliaIstance();
	}

	private RicercaFamigliaBD() {
	}

	public static List<MembroFamigliaDTO> ricercaPerCognomeParziale(
			String matchCognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaFamiglia.ricercaMembroPerCognomeParziale(matchCognome);

	}
	
	public static List<MembroFamigliaDTO> ricercaParzialePerCognomeNome(
			String cognome, String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaFamiglia.ricercaParzialePerCognomeNome(cognome, nome);

	}

	public static List<MembroFamigliaDTO> ricercaPerCdFamiglia(int cdFamiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {

			return ricercaFamiglia.ricercaPerCodice(cdFamiglia);

		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public static List<MembroFamigliaDTO> ricercaPerNomeCognome(String nome,
			String cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		try {

			return ricercaFamiglia.ricercaPerNomeCognome(nome, cognome);

		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}

	public static List<MembroFamigliaDTO> ricercaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		try {

			return ricercaFamiglia.ricercaPerCognome(cognome);

		} catch (ContradaExceptionBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (ContradaExceptionNonBloccante e) {
			log.error(e.getMessage(), e);
			throw e;
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage(), e1);
			throw new ContradaExceptionBloccante(Errori.TEMP_PROB_COLL);
		}

	}
}
