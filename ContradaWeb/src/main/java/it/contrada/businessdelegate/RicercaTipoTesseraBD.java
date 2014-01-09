package it.contrada.businessdelegate;

import it.contrada.bean.factory.ContradaPojoFactory;
import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.interfaces.IRicercaTessera;
import it.contrada.web.util.Errori;

import java.sql.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RicercaTipoTesseraBD {
	private static IRicercaTessera ricercaTessera;
	private static Log log = LogFactory.getLog(RicercaTipoTesseraBD.class);

	static {

		/*
		 * try { InitialContext ctx = new InitialContext(); ricercaTessera =
		 * (IRicercaTessera) ctx .lookup("ContradaEAR/RicercaTessera/local"); }
		 * catch (NamingException e1) { // TODO Auto-generated catch block
		 * log.error(e1.getMessage(), e1); throw new RuntimeException(e1); }
		 */
		ricercaTessera = ContradaPojoFactory.getRicercaTesseraIstance();
	}

	public static List<TipoTesseraDTO> elencaTipoTessera()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaTessera.elencaTipoTessera();

	}

	public static List<TipoTesseraDTO> ricercaTipoTesseraPerIncasso(
			int idTipoIncasso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaTessera.ricercaTipoTesseraPerIncasso(idTipoIncasso);

	}

	public static List<TesseraDTO> ricercaTessereRendicontabiliManualmente(
			int idAnag, int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		return ricercaTessera.ricercaTessereRendicontabiliManualmente(idAnag,
				anno);

	}

	public static List<TesseraDTO> ricercaTessereRendicontabiliManualmente(
			int idAnag, int idTipoTessera, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {

		return ricercaTessera.ricercaTessereRendicontabiliManualmente(idAnag,
				idTipoTessera, anno);

	}

	public static List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer idTipoIncasso, List<Integer> idEsattori,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaTessera.recuperaTessereDaStampare(idTipoTessera,
				idTipoIncasso, idEsattori, anno);

	}

	public static List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, List<Integer> idAnagrafica, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaTessera.recuperaTessereDaStampare(idTipoTessera,
				idAnagrafica, anno);
	}

	public static List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer idTipoIncasso, Integer idTipoCarica,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaTessera.recuperaTessereDaStampare(idTipoTessera,
				idTipoIncasso, idTipoCarica, anno);
	}

	public static List<TesseraDTO> ricercaTesserePerDistinta(int anno,
			int distinta) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		return ricercaTessera.ricercaTesserePerDistinta(anno, distinta);
	}

	public static List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer[] idTipoIncasso, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaTessera.recuperaTessereDaStampare(idTipoTessera,
				idTipoIncasso, anno);
	}

	public static List<TesseraStampataDTO> stampaTabulato(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaTessera.stampaTabulato(idTipoTessera, idTipoIncasso,
				idTipoCarica, anno);
	}

	public static List<TesseraDTO> ricercaTessereNonPagantiUltimiAnni(
			int idTipoTessera, int annoDa,int annoA)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		return ricercaTessera.ricercaTessereNonPagantiUltimiAnni(idTipoTessera,
				annoDa,annoA);
	}

	public static List<TesseraDTO> ricercaTessereNonPagantiUltimiAnni(
			int idTipoTessera, int annoDa, int tipoIncasso,int annoA,Integer tipoEsattore)
			throws ContradaExceptionNonBloccante, ContradaExceptionBloccante {
		return ricercaTessera.ricercaTessereNonPagantiUltimiAnni(idTipoTessera,
				annoDa, tipoIncasso,annoA,tipoEsattore);
	}

	public static List<TesseraStampataDTO> recuperaTessereDaStampareFineAnno(
			int idTipoTessera, Integer[] idTipoIncasso, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		return ricercaTessera.recuperaTessereDaStampareFineAnno(idTipoTessera,
				idTipoIncasso, anno);
	}

	public static List<TesseraDTO> ricercaTessereDaAllineare(java.util.Date dataRif)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante {
		java.sql.Date dataRifSql = new Date(dataRif.getTime());
		return ricercaTessera.ricercaTessereDaAllineare(dataRifSql);
	}

}
