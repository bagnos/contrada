package it.contrada.incassorid.dto;

import java.io.Serializable;

public class Record10DTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7839539204917193045L;
	private String tipoRecord = "10";
	private String numeroProgressivo;
	private String dtScadenzaEffettiva;
	private String dtScadenzaOriginaria;
	private String causale;
	private String importo;
	private String segno;
	private String abiDomiciliaria;
	private String cabDomiciliaria;
	private String abiAssuntrice;
	private String cabAssuntrice;
	private String contoOrdinante;
	private String codiceAzienda;
	private String tipoCodice;
	private String codiceClienteDebitore;
	private String tipoIncassoRid;
	private String codiceDivisa;
	private String contoDebitore;
	private String flagDebitore;
	private String dtLimitePagamento;
	private String dtDecorrenzaGaranzia;
	private String dtScadenza;
	
	

	public String getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(String dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

	public String getDtDecorrenzaGaranzia() {
		return dtDecorrenzaGaranzia;
	}

	public void setDtDecorrenzaGaranzia(String dtDecorrenzaGaranzia) {
		this.dtDecorrenzaGaranzia = dtDecorrenzaGaranzia;
	}

	public String getDtLimitePagamento() {
		return dtLimitePagamento;
	}

	public void setDtLimitePagamento(String dtLimitePagamento) {
		this.dtLimitePagamento = dtLimitePagamento;
	}

	public String getFlagDebitore() {
		return flagDebitore;
	}

	public void setFlagDebitore(String flagDebitore) {
		this.flagDebitore = flagDebitore;
	}

	public String getContoDebitore() {
		return contoDebitore;
	}

	public void setContoDebitore(String contoDebitore) {
		this.contoDebitore = contoDebitore;
	}

	public String getTipoRecord() {
		return tipoRecord;
	}

	public void setTipoRecord(String tipoRecord) {
		this.tipoRecord = tipoRecord;
	}

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	public String getDtScadenzaEffettiva() {
		return dtScadenzaEffettiva;
	}

	public void setDtScadenzaEffettiva(String dtScadenzaEffettiva) {
		this.dtScadenzaEffettiva = dtScadenzaEffettiva;
	}

	public String getDtScadenzaOriginaria() {
		return dtScadenzaOriginaria;
	}

	public void setDtScadenzaOriginaria(String dtScadenzaOriginaria) {
		this.dtScadenzaOriginaria = dtScadenzaOriginaria;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getImporto() {
		return importo;
	}

	public void setImporto(String importo) {
		this.importo = importo;
	}

	public String getSegno() {
		return segno;
	}

	public void setSegno(String segno) {
		this.segno = segno;
	}

	public String getAbiDomiciliaria() {
		return abiDomiciliaria;
	}

	public void setAbiDomiciliaria(String abiDomiciliaria) {
		this.abiDomiciliaria = abiDomiciliaria;
	}

	public String getCabDomiciliaria() {
		return cabDomiciliaria;
	}

	public void setCabDomiciliaria(String cabDomiciliaria) {
		this.cabDomiciliaria = cabDomiciliaria;
	}

	public String getAbiAssuntrice() {
		return abiAssuntrice;
	}

	public void setAbiAssuntrice(String abiAssuntrice) {
		this.abiAssuntrice = abiAssuntrice;
	}

	public String getCabAssuntrice() {
		return cabAssuntrice;
	}

	public void setCabAssuntrice(String cabAssuntrice) {
		this.cabAssuntrice = cabAssuntrice;
	}

	public String getContoOrdinante() {
		return contoOrdinante;
	}

	public void setContoOrdinante(String contoOrdinante) {
		this.contoOrdinante = contoOrdinante;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getTipoCodice() {
		return tipoCodice;
	}

	public void setTipoCodice(String tipoCodice) {
		this.tipoCodice = tipoCodice;
	}

	public String getCodiceClienteDebitore() {
		return codiceClienteDebitore;
	}

	public void setCodiceClienteDebitore(String codiceClienteDebitore) {
		this.codiceClienteDebitore = codiceClienteDebitore;
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

	@Override
	public String toString() {
		StringBuilder rec10 = new StringBuilder();
		rec10.append(" ");
		rec10.append(String.format("%2s", tipoRecord));
		rec10.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec10.append(String.format("%6s", dtDecorrenzaGaranzia).replaceAll(" ",
				"0"));
		rec10.append(String.format("%6s", dtLimitePagamento).replaceAll(" ",
				"0"));
		rec10.append(String.format("%6s", dtScadenza).replaceAll(" ", "0"));
		rec10.append(String.format("%5s", causale).replaceAll(" ", "0"));
		rec10.append(String.format("%13s", importo).replaceAll(" ", "0"));
		rec10.append(segno);
		rec10
				.append(String.format("%5s", abiDomiciliaria).replaceAll(" ",
						"0"));
		rec10
				.append(String.format("%5s", cabDomiciliaria).replaceAll(" ",
						"0"));
		rec10
				.append(String.format("%12s", contoOrdinante).replaceAll(" ",
						"0"));
		rec10.append(String.format("%5s", abiAssuntrice).replaceAll(" ", "0"));
		rec10.append(String.format("%5s", cabAssuntrice).replaceAll(" ", "0"));
		rec10.append(String.format("%12s", contoDebitore).replaceAll(" ", "0"));
		rec10.append(String.format("%5s", codiceAzienda));
		rec10.append(tipoCodice);
		rec10.append(String.format("%16s", codiceClienteDebitore).replaceAll(
				" ", "0"));
		rec10.append(flagDebitore);
		rec10.append("    ");
		rec10.append(tipoIncassoRid);
		rec10.append(codiceDivisa);
		return rec10.toString();

	}
}
