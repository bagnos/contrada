package it.contrada.incassorid.dto;

import java.io.Serializable;

public class RecordEFDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -796473430242100536L;
	private String tipoRecord = "EF";
	private String mittente;
	private String ricevente;
	private String dataCreazioneFlusso;
	private String nomeSupporto;
	private String campoDisposizione;
	private int nrDisposizioni;
	private int nrImportiNegativi;
	private int nrImportiPositivi;
	private int nrRecord;
	private String tipoIncassoRid;
	private String codiceDivisa;
	private String CampoNonDisponibile;

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
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

	public String getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(String dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getNomeSupporto() {
		return nomeSupporto;
	}

	public void setNomeSupporto(String nomeSupporto) {
		this.nomeSupporto = nomeSupporto;
	}

	public String getCampoDisposizione() {
		return campoDisposizione;
	}

	public void setCampoDisposizione(String campoDisposizione) {
		this.campoDisposizione = campoDisposizione;
	}

	public int getNrDisposizioni() {
		return nrDisposizioni;
	}

	public void setNrDisposizioni(int nrDisposizioni) {
		this.nrDisposizioni = nrDisposizioni;
	}

	public int getNrImportiNegativi() {
		return nrImportiNegativi;
	}

	public void setNrImportiNegativi(int nrImportiNegativi) {
		this.nrImportiNegativi = nrImportiNegativi;
	}

	public int getNrImportiPositivi() {
		return nrImportiPositivi;
	}

	public void setNrImportiPositivi(int nrImportiPositivi) {
		this.nrImportiPositivi = nrImportiPositivi;
	}

	public int getNrRecord() {
		return nrRecord;
	}

	public void setNrRecord(int nrRecord) {
		this.nrRecord = nrRecord;
	}

	public String getTipoIncassoRid() {
		return tipoIncassoRid;
	}

	public void setTipoIncassoRid(String tipoIncassoRid) {
		this.tipoIncassoRid = tipoIncassoRid;
	}

	public String getCodiceDivisa() {
		return codiceDivisa;
	}

	public void setCodiceDivisa(String codiceDivisa) {
		this.codiceDivisa = codiceDivisa;
	}

	public String getCampoNonDisponibile() {
		return CampoNonDisponibile;
	}

	public void setCampoNonDisponibile(String campoNonDisponibile) {
		CampoNonDisponibile = campoNonDisponibile;
	}

	@Override
	public String toString() {
		StringBuilder recCoda = new StringBuilder();
		recCoda.append(" ");
		recCoda.append(tipoRecord);

		recCoda.append(String.format("%5s", mittente));
		recCoda.append(String.format("%5s", ricevente).replaceAll(" ", "0"));
		recCoda.append(String.format("%6s", dataCreazioneFlusso).replaceAll(
				" ", "0"));
		recCoda.append(String.format("%20s", nomeSupporto.toUpperCase())); // siamo alla
																// posizione 39

		recCoda.append(String.format("%6s", ""));
		recCoda.append(String.format("%7s", nrDisposizioni)
				.replaceAll(" ", "0"));
		recCoda.append(String.format("%15s", nrImportiNegativi).replaceAll(" ",
				"0"));
		recCoda.append(String.format("%15s", nrImportiPositivi).replaceAll(" ",
				"0"));
		recCoda.append(String.format("%7s", nrRecord).replaceAll(" ", "0"));
		recCoda.append(String.format("%23s", ""));
		recCoda.append(String.format("%s",tipoIncassoRid));
		recCoda.append(String.format("%s",codiceDivisa));
		recCoda.append(String.format("%6s", "")); // siamo alla posizione 120

		return recCoda.toString();
	}
}
