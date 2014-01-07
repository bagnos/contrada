package it.contrada.interfaces;

import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.List;

public interface IRicercaFamiglia {
public List<MembroFamigliaDTO> ricercaPerNomeCognome(String nome, String Cognome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
public List<MembroFamigliaDTO> ricercaPerCognome(String Cognome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
public List<MembroFamigliaDTO> ricercaPerCodice(int cdFamiglia) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
public List<MembroFamigliaDTO> ricercaMembroPerCognomeParziale(String matchCognome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
public List<MembroFamigliaDTO> ricercaParzialePerCognomeNome(String cognome, String nome) throws ContradaExceptionBloccante,ContradaExceptionNonBloccante;
}
