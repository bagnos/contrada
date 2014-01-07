package it.contrada.interfaces;

import it.contrada.dominio.dto.TipoIncassoDTO;
import it.contrada.dominio.dto.TipoStatoRidDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.incassorid.dto.IncassoRidDTO;

import java.util.List;

public interface IRicercaRid {
	public List<MembroRidDTO> ricercaPerNomeCognome(String nome, String Cognome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<MembroRidDTO> ricercaPerCognome(String Cognome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<MembroRidDTO> ricercaPerRid(int idRid) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<MembroRidDTO> ricercaPerCognomeParazile(String matchCognome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<MembroRidDTO> ricercaPerCognomeNomeParzaile(String matchCognome,String nome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoStatoRidDTO> elencaStati() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public RidDTO ricercaPerId(int idRid) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<TipoIncassoDTO> elencaTipoIncassiRid() throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	public List<RidDTO> ricercaPerStato (List<Integer> cdStato) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
	
}
