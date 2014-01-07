package it.contrada.dao.interfaces;

import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.FamigliaDTO;
import it.contrada.dto.MembroFamigliaDTO;

import java.util.List;

public interface IFamigliaDAO {
	public FamigliaDTO inserisciFamiglia(Integer idCapoFamiglia) throws Exception;
	public List<MembroFamigliaDTO> getMembroPerCognome (String cognome) throws Exception;;
	public List<MembroFamigliaDTO> getMembro (String cognome, String nome) throws Exception;;
	public List<MembroFamigliaDTO> getMembro (int cdFamiglia) throws Exception;;
	public int aggiornaFamiglia (FamigliaDTO famiglia) throws Exception;;
	public List<MembroFamigliaDTO> getMembroPerCognomeParziale(String matchCognome) throws Exception;;
	public List<MembroFamigliaDTO> getAnagraficaParzialePerCognomeNome(String cognome,String nome) throws Exception ;
}
