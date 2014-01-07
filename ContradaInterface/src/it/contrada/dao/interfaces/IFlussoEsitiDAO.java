package it.contrada.dao.interfaces;

import it.contrada.dto.FlussoEsitoDTO;
import it.contrada.enumcontrada.TipoFlusso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.preautrid.dto.RidEsitoPreautDTO;

import java.sql.Date;
import java.util.List;



public interface IFlussoEsitiDAO {
	public FlussoEsitoDTO insertFlussoEsito(FlussoEsitoDTO flussoEsito) throws Exception;
	public int aggiornaFlussoEsito(FlussoEsitoDTO flussoEsito) throws Exception;
	public List<FlussoEsitoDTO> getFlussoEsito(TipoFlusso flusso,int nrLast) throws Exception;
	public List<RidEsitoPreautDTO> getEsitoPreautPerRid(int idRid)throws Exception;
	public List<RidEsitoPreautDTO> getEsitoPreautPerData(java.sql.Date dtDa,java.sql.Date dtA)throws Exception;
	public List<RidEsitoPreautDTO> getEsitoPreaut(int idRid,Date dtDa, Date dtA) throws Exception;
	public List<IncassoRidDTO> getEsitoincassoRid(Integer anno,Integer mese, Integer tipoIncassoRid,Integer nrRid,Integer causaleIincasso) throws Exception;
	public java.sql.Date getMaxDataFlusso(TipoFlusso tipoFlusso) throws Exception;
}
