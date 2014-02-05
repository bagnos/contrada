package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.RicercaFasceEtaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.Date;
import java.util.List;

public interface IRicercaAnagrafica {
	public boolean existByCodiceFiscale(String cdFiscale)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaPerNomeCognome(String nome, String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaPerCognome(String cognome)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaAnagraficaParzialePerCognome(
			String cognome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaAnagraficaParzialePerCognomeNome(
			String cognome, String nome) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public AnagraficaDTO ricercaPerCodiceAnagrafica(int cdAnag)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaPerCodiceFamiglia(int cdFamiglia)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<TipoStatoAnagraficaDTO> elencaStatiAnagrafica()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaFasciaEta(RicercaFasceEtaDTO criterio)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> elencaIndirizzoAnagraficaPrincipale()
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaPerResidenza(ParmResidenzaDTO residenza)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaPagantiAnnoPrecedente(int anno,
			int tipoTessera) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public boolean existAnagrafica(String cognome, String nome,
			java.util.Date dtNascita) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;;

	public List<AnagraficaDTO> ricercaVotanti(int idTipoTessera,
			Date dtElezione, int etaMin, int annoDa)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> elencoFazzoletti(int annoPagamentoDa)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaIndirizzoAnagraficaPrincipale(
			List<Integer> codiciFamiglia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante;

	public List<AnagraficaDTO> ricercaAnagraficheConPrincipale(List<Integer> statiAnagrafica)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
	
	public List<AnagraficaDTO> ricercaAnagrafichePerGestore(int idGestore)
			throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
}