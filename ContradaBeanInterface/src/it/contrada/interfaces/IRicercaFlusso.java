package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoCasualiIncassoRidDTO;
import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.preautrid.dto.RidEsitoPreautDTO;

import java.util.List;

public interface IRicercaFlusso {
	public List<FlussoEsitoDTO> ricercaFlusso(TipoFlusso flussoint, int nrLast) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RidEsitoPreautDTO> ricercaEsitoPreautPerData(java.sql.Date dtDa,java.sql.Date dtA)throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RidEsitoPreautDTO> ricercaEsitoPreaut(int idRid,java.sql.Date dtDa,java.sql.Date dtA)throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RidEsitoPreautDTO> ricercaEsitoPreautPerRid(int idRid)throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoCasualiPreautDTO> elencaCausaliPreaut() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoCasualiIncassoRidDTO> elencaCausaliIncassiRid() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<IncassoRidDTO> ricercaEsitoincassoRid(Integer anno,Integer mese, Integer tipoIncassoRid,Integer nrRid,Integer causaleIincasso) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	
}
