package it.contrada.preautrid.dto;

import java.io.Serializable;

public class RecordEFDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2614432373283036197L;
	private String mittente;
	private String ricevente;
	private String dataCreazione;
	private String nomeSupporto;
	private String numeroDisposizioni;
	private String numeroTotaleRecord;
	private String tipoRecord = "EF";
	private String campoNonDisponibile;

	public String getCampoNonDisponibile() {
		return campoNonDisponibile;
	}

	public void setCampoNonDisponibile(String campoNonDisponibile) {
		this.campoNonDisponibile = campoNonDisponibile;
	}

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

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getNomeSupporto() {
		return nomeSupporto;
	}

	public void setNomeSupporto(String nomeSupporto) {
		this.nomeSupporto = nomeSupporto;
	}

	public String getNumeroDisposizioni() {
		return numeroDisposizioni;
	}

	public void setNumeroDisposizioni(String numeroDisposizioni) {
		this.numeroDisposizioni = numeroDisposizioni;
	}

	public String getNumeroTotaleRecord() {
		return numeroTotaleRecord;
	}

	public void setNumeroTotaleRecord(String numeroTotaleRecord) {
		this.numeroTotaleRecord = numeroTotaleRecord;
	}

	@Override
	public String toString() {
		StringBuilder recCoda = new StringBuilder();
		recCoda.append(" ");
		recCoda.append(tipoRecord);
		recCoda.append(String.format("%5s", mittente));
		recCoda.append(String.format("%5s", ricevente).replaceAll(" ", "0"));
		recCoda.append(String.format("%5s", dataCreazione)
				.replaceAll(" ", "0"));
		recCoda.append(String.format("%20s", nomeSupporto));
		recCoda.append(String.format("%6s", ""));
		recCoda.append(String.format("%7s", numeroDisposizioni).replaceAll(" ",
				"0"));
		recCoda.append(String.format("%30s", ""));
		recCoda.append(String.format("%7s", numeroTotaleRecord).replaceAll(" ",
				"0"));
		recCoda.append(String.format("%25s", ""));
		recCoda.append(String.format("%6s", campoNonDisponibile));

		return recCoda.toString();
	}
}
