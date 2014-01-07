package it.contrada.poste;

import java.io.Serializable;

public class RendicontazioneIncassoPostaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1976894220213979149L;
	private String idIncassoPoste;
	private int imBollettino;
	private java.sql.Date dtOperazione;
	private java.sql.Date dtValuta;
	private String tyDocumento;
	private String txSportello;
	private String tyAccettazione;
	private String tySostitutivo;
	private boolean incassatoAutomatico;
	private int idFlussoEsito;
	
	
	public int isIdFlussoEsito() {
		return idFlussoEsito;
	}
	public void setIdFlussoEsito(int idFlussoEsito) {
		this.idFlussoEsito = idFlussoEsito;
	}
	public boolean isIncassatoAutomatico() {
		return incassatoAutomatico;
	}
	public void setIncassatoAutomatico(boolean incassatoAutomatico) {
		this.incassatoAutomatico = incassatoAutomatico;
	}
	public String getIdIncassoPoste() {
		return idIncassoPoste;
	}
	public void setIdIncassoPoste(String idIncassoPoste) {
		this.idIncassoPoste = idIncassoPoste;
	}
	public int getImBollettino() {
		return imBollettino;
	}
	public void setImBollettino(int imBollettino) {
		this.imBollettino = imBollettino;
	}
	public java.sql.Date getDtOperazione() {
		return dtOperazione;
	}
	public void setDtOperazione(java.sql.Date dtOperazione) {
		this.dtOperazione = dtOperazione;
	}
	public java.sql.Date getDtValuta() {
		return dtValuta;
	}
	public void setDtValuta(java.sql.Date dtValuta) {
		this.dtValuta = dtValuta;
	}
	public String getTyDocumento() {
		return tyDocumento;
	}
	public void setTyDocumento(String tyDocumento) {
		this.tyDocumento = tyDocumento;
	}
	public String getTxSportello() {
		return txSportello;
	}
	public void setTxSportello(String txSportello) {
		this.txSportello = txSportello;
	}
	public String getTyAccettazione() {
		return tyAccettazione;
	}
	public void setTyAccettazione(String tyAccettazione) {
		this.tyAccettazione = tyAccettazione;
	}
	public String getTySostitutivo() {
		return tySostitutivo;
	}
	public void setTySostitutivo(String tySostitutivo) {
		this.tySostitutivo = tySostitutivo;
	}
	
	
}
