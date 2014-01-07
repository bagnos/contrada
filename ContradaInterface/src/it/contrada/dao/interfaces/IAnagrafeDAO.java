package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoStatoAnagraficaDTO;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.ParmResidenzaDTO;
import it.contrada.dto.RicercaFasceEtaDTO;

import java.util.Date;
import java.util.List;

public interface IAnagrafeDAO {
	public boolean existAnagraficaByCdFisc(String cdFiscale) throws Exception;
	public int existAnagrafica(String nome,String cognome, java.util.Date dtNascita) throws Exception;
	public AnagraficaDTO inserisciAnagrafica(AnagraficaDTO anagraficaDTO) throws Exception ;
	public List<AnagraficaDTO> getAnagraficaByCognomeNome(String cognome, String nome) throws Exception ;
	public List<AnagraficaDTO> getAnagraficaByCognome(String cognome) throws Exception ;
	
	public AnagraficaDTO getAnagraficaByCodiceAnagrafica(long cdAnag) throws Exception ;
	public List<AnagraficaDTO> getAnagraficaByCodiceFamiglia(long cdFamiglia) throws Exception ;
	public int aggiornaAnagrafica(AnagraficaDTO anagraficaDTO) throws Exception ;
	public int aggiornaStatoAnagrafica(List<Integer> idAnagrafiche, int idStato) throws Exception ;
	public int aggiornaGestore(List<AnagraficaDTO> anagrafiche) throws Exception ;
	public int aggiornaIndirizzoAnagrafica(long idStrada,String nrCivico, int idFamiglia) throws Exception ;
	public List<TipoStatoAnagraficaDTO> getStatiAnagrafica() throws Exception ;
	public List<AnagraficaDTO> getAnagraficaByFasce(RicercaFasceEtaDTO criterio) throws Exception ;
	public List<AnagraficaDTO> getIndirizzoAnagraficaPrincipale() throws Exception ;
	public List<AnagraficaDTO> getIndirizzoAnagraficaPrincipale(List<Integer> codiciFamiglia) throws Exception ;
	public List<AnagraficaDTO> getAnagraficaParzialePerCognome(String cognome) throws Exception ;
	public List<AnagraficaDTO> getAnagraficaParzialePerCognomeNome(String cognome,String nome) throws Exception ;
	public List<AnagraficaDTO> getAnagraficaPerResidenza(ParmResidenzaDTO parm) throws Exception ;
	public List<AnagraficaDTO> getPagantiAnnoPrecedente(int anno,int tipoTessera) throws Exception ;
	public List<AnagraficaDTO> getVotanti(int idTipoTessera,Date dtElezione,int etaMin,int annoDa) throws Exception ;
	public List<AnagraficaDTO> getFazzoletti(int annoPagamentoDa) throws Exception ;
	public List<AnagraficaDTO> getAnagraficheConPrincipale(List<Integer> statiAnagrafica) throws Exception ;
	
}
