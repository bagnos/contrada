package it.contrada.dto;

import java.io.Serializable;

public class ParametriContradaDTO implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 3005300712066717149L;
	private String txIntestazione;
	private String txIndirizzo;
	private String txLocalita;
	private String txProvincia;
	private String cdCap;
	private String cdFisc;
	private String cdPiva;
	private String cdSia;
	private int cdAbi;
	private int cdCab;
	private String nrConto;
	private String nrCin;
	private int nrCCP;
	private String idSeda;
	private String checkDigit;
	private String cdPaese;
	
	
	
	public String getCheckDigit() {
		return checkDigit;
	}
	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}
	public String getCdPaese() {
		return cdPaese;
	}
	public void setCdPaese(String cdPaese) {
		this.cdPaese = cdPaese;
	}
	public String getIdSeda() {
		return idSeda;
	}
	public void setIdSeda(String idSeda) {
		this.idSeda = idSeda;
	}
	public String getTxIntestazione() {
		return txIntestazione;
	}
	public void setTxIntestazione(String txIntestazione) {
		this.txIntestazione = txIntestazione;
	}
	public String getTxIndirizzo() {
		return txIndirizzo;
	}
	public void setTxIndirizzo(String txIndirizzo) {
		this.txIndirizzo = txIndirizzo;
	}
	public String getTxLocalita() {
		return txLocalita;
	}
	public void setTxLocalita(String txLocalita) {
		this.txLocalita = txLocalita;
	}
	public String getTxProvincia() {
		return txProvincia;
	}
	public void setTxProvincia(String txProvincia) {
		this.txProvincia = txProvincia;
	}
	public String getCdCap() {
		return cdCap;
	}
	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}
	public String getCdFisc() {
		return cdFisc;
	}
	public void setCdFisc(String cdFisc) {
		this.cdFisc = cdFisc;
	}
	public String getCdPiva() {
		return cdPiva;
	}
	public void setCdPiva(String cdPiva) {
		this.cdPiva = cdPiva;
	}
	public String getCdSia() {
		return cdSia;
	}
	public void setCdSia(String cdSia) {
		this.cdSia = cdSia;
	}
	public int getCdAbi() {
		return cdAbi;
	}
	public void setCdAbi(int cdAbi) {
		this.cdAbi = cdAbi;
	}
	public int getCdCab() {
		return cdCab;
	}
	public void setCdCab(int cdCab) {
		this.cdCab = cdCab;
	}
	public String getNrConto() {
		return nrConto;
	}
	public void setNrConto(String nrConto) {
		this.nrConto = nrConto;
	}
	public String getNrCin() {
		return nrCin;
	}
	public void setNrCin(String nrCin) {
		this.nrCin = nrCin;
	}
	public int getNrCCP() {
		return nrCCP;
	}
	public void setNrCCP(int nrCCP) {
		this.nrCCP = nrCCP;
	}
}
