package it.contrada.incassorid.dto;

import java.io.Serializable;

public class RecordIRDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8470492404033980460L;
	private String tipoRecord = "IR";
	private String mittente;
	private String ricevente;
	private String abiBancaAssuntrice;
	private String dtCreazioneFlusso;
	private String nomeSupporto;
	private String campoDisposizione;
	private String qualificatoreFlusso;
	private String tipoIncassoRid;
	private String codiceDivisa;
	private String campoDisposizione1;

	public String getRicevente() {
		return ricevente;
	}

	public void setRicevente(String ricevente) {
		this.ricevente = ricevente;
	}

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

	public String getAbiBancaAssuntrice() {
		return abiBancaAssuntrice;
	}

	public void setAbiBancaAssuntrice(String abiBancaAssuntrice) {
		this.abiBancaAssuntrice = abiBancaAssuntrice;
	}

	public String getDtCreazioneFlusso() {
		return dtCreazioneFlusso;
	}

	public void setDtCreazioneFlusso(String dtCreazioneFlusso) {
		this.dtCreazioneFlusso = dtCreazioneFlusso;
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

	public String getQualificatoreFlusso() {
		return qualificatoreFlusso;
	}

	public void setQualificatoreFlusso(String qualificatoreFlusso) {
		this.qualificatoreFlusso = qualificatoreFlusso;
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

	public String getCampoDisposizione1() {
		return campoDisposizione1;
	}

	public void setCampoDisposizione1(String campoDisposizione1) {
		this.campoDisposizione1 = campoDisposizione1;
	}

	@Override
	public String toString() {
		StringBuilder recTesta = new StringBuilder();
		recTesta.append(" ");
		recTesta.append(tipoRecord);
		recTesta.append(String.format("%5s", mittente));
		recTesta.append(String.format("%5s", ricevente).replaceAll(" ", "0"));
		recTesta.append(String.format("%6s", dtCreazioneFlusso).replaceAll(" ",
				"0"));
		recTesta.append(String.format("%20s", nomeSupporto)); // siamo alla
																// posizione 39
		recTesta.append(String.format("%73s", "")); // siamo alla posizione 112
		recTesta.append(String.format("%s", tipoIncassoRid)); // siamo alla
																// posizione 113
		recTesta.append(String.format("%s", codiceDivisa)); // siamo alla
															// posizione 114
		recTesta.append(" ");
		recTesta.append(String.format("%5s", "")); // siamo alla posizione 120
		return recTesta.toString();

	}

}
