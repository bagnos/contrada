package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoMeseDTO;
import it.contrada.enumcontrada.TipoIncassoRid;

import java.util.List;

public interface IMeseDAO {
	public List<TipoMeseDTO> ricercaMese(int incasso) throws Exception;
	public List<TipoMeseDTO> elencaMese() throws Exception;
}
