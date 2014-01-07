package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoTesseraDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaTessera {
	public List<TipoTesseraDTO> elencaTipoTessera()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TipoTesseraDTO> ricercaTipoTesseraPerIncasso(int idTipoIncasso)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TipoTesseraDTO> ricercaPerAnagraficaIncasso(long idAnag,
			int idTipoIncasso) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<TesseraDTO> ricercaTessereRendicontabiliManualmente(int idAnag,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<TesseraDTO> ricercaTessereRendicontabiliManualmente(int idAnag,
			int idTipoTessera, int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<TesseraDTO> ricercaTesserePerDistinta(int anno, int distinta)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer idTipoIncasso, List<Integer> idEsattori,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, List<Integer> idAnagrafica, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer idTipoIncasso, Integer idTipoCarica,
			int anno) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<TesseraStampataDTO> recuperaTessereDaStampare(
			int idTipoTessera, Integer[] idTipoIncasso, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TesseraStampataDTO> recuperaTessereDaStampareFineAnno(
			int idTipoTessera, Integer[] idTipoIncasso, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TesseraStampataDTO> stampaTabulato(int idTipoTessera,
			Integer idTipoIncasso, Integer idTipoCarica, int anno)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TesseraDTO> ricercaTessereNonPagantiUltimiAnni(
			int idTipoTessera, int annoDa,int annoA) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<TesseraDTO> ricercaTessereNonPagantiUltimiAnni(
			int idTipoTessera, int annoDa, int tipoIncasso,int annoA,Integer tipoEsattore)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TesseraDTO> ricercaTessereDaAllineare(java.sql.Date dataRif)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
}
