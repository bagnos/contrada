package it.contrada.interfaces;

import it.contrada.dto.CapDTO;
import it.contrada.dto.LocalitaDTO;
import it.contrada.dto.StradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaStrada {
	public List<StradaDTO> recuperaPerCap (String cdCap) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante; 
	public List<StradaDTO> recuperaPerCapViaParziale (String cdCap,String matchVia) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<StradaDTO> recuperaPerCapLocViaParziale (String cdCap,Integer idLoc,String matchVia) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<CapDTO> recuperaCapParziale (String matchCap,int cdProvincia) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<LocalitaDTO> recuperaLocalitaPerCap (String cdCap, int cdProvincia, int cdComune) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
} 
