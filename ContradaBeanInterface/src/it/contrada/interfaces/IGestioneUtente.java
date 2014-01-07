package it.contrada.interfaces;

import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

public interface IGestioneUtente {
	public void cambiaPassword(String utente,String vecchiaPsw,String nuovaPsw,String confNuovaPsw) throws ContradaExceptionBloccante, ContradaExceptionNonBloccante;
}
