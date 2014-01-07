package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoCasualiPreautDTO;
import it.contrada.dto.RidDTO;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;

import java.util.Date;
import java.util.List;

public interface IFlussoPreautorizzazioniRidDAO {
	public int insertFlussoPreaut(List<Integer> rid) throws Exception;
	public int aggiornaRicezioneFlusso(int idRid, java.sql.Date dtInvio,java.sql.Date dtEsito, int causale, int idFileEsito) throws Exception;
	public List<TipoCasualiPreautDTO> elencoCausaliPreaut();
	public List<RidDTO> gerRidPreautorizzatiInviati(java.util.Date dtInvio) throws Exception;
	public List<FlussoPreautInviatoDTO> getFlussoPreautorizzati (int anno) throws Exception;
	public int getNrPreautorizzazioniToday() throws Exception;
	public int eliminaFlussoPreautorizzazioni(java.util.Date dtInvio) throws Exception;
}
