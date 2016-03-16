package it.othala.payment.paypal.dto;

import java.io.Serializable;

public class DeliveryAddressDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	private Integer idAddress;
	private String userId;
	private String nome;
	private String cognome;
	private String via;
	private String comune;
	private String cap;
	private String provincia;
	private String nazione;
	private String tel;
	private String etichetta;
	public String getEtichetta() {
		return etichetta;
	}

	public void setEtichetta(String etichetta) {
		this.etichetta = etichetta;
	}

	private String nomeCompleto;

	public String getNomeCompleto() {
		nomeCompleto = nome + " " + cognome;
		return nomeCompleto;
	}

	public Integer getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(Integer idAddress) {
		this.idAddress = idAddress;
	}

}
