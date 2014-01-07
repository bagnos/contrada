package it.contrada.dto;

import java.io.Serializable;
import java.util.List;

public class GestoreDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5570155775395995817L;
	private Integer idGestore;
	private String nome;
	private String cognome;
	private List<Integer> anagrafiche;
	
	
	public List<Integer> getAnagrafiche() {
		return anagrafiche;
	}
	public void setAnagrafiche(List<Integer> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}
	public Integer getIdGestore() {
		return idGestore;
	}
	public void setIdGestore(Integer idGestore) {
		this.idGestore = idGestore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	
}
