package it.contrada.preautrid.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecordALDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2380274379532210302L;
	private String mittente;
	private String ricevente;
	private String dtCreazioneDisposizione;
	private String nomeSupporto;
	private String tipoRecord = "AL";

	public String getMittente() {
		return mittente;
	}

	public void setMittente(String mittente) {
		this.mittente = mittente;
	}

	public String getRicevente() {
		return ricevente;
	}

	public void setRicevente(String ricevente) {
		this.ricevente = ricevente;
	}

	public String getDtCreazioneDisposizione() {
		return dtCreazioneDisposizione;
	}

	public void setDtCreazioneDisposizione(String dtCreazioneDisposizione) {
		this.dtCreazioneDisposizione = dtCreazioneDisposizione;
	}

	public String getNomeSupporto() {
		return nomeSupporto;
	}

	public void setNomeSupporto(String nomeSupporto) {
		this.nomeSupporto = nomeSupporto;
	}

	@Override
	public String toString() {
		StringBuilder recTesta = new StringBuilder();
		recTesta.append(" ");
		recTesta.append(tipoRecord);
		recTesta.append(String.format("%5s", mittente));
		recTesta.append(String.format("%5s", ricevente).replaceAll(" ", "0"));
		recTesta.append(String.format("%5s", dtCreazioneDisposizione)
				.replaceAll(" ", "0"));
		recTesta.append(String.format("%20s", nomeSupporto)); // siamo alla
		// posizione 39
		recTesta.append(String.format("%81s", "")); // siamo alla posizione 112
		return recTesta.toString();

	}

}
