package it.contrada.dao.interfaces;

import it.contrada.dominio.dto.TipoIncassoDTO;

import java.util.List;

public interface IIncassoDAO {
public List<TipoIncassoDTO> getTipoIncasso() throws Exception;
}
