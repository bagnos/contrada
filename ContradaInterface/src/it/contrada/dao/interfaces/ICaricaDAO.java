package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoCaricaDTO;
import it.contrada.dto.CaricaTesseraDTO;

import java.util.List;

public interface ICaricaDAO {
	public List<CaricaTesseraDTO> getCaricaPerTipo(int idTipoTessera)  throws Exception;
	public List<TipoCaricaDTO> getCariche()  throws Exception;
	public int aggiornaCarica(List<TipoCaricaDTO> cariche) throws Exception;
}
