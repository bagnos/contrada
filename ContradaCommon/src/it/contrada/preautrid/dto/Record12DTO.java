package it.contrada.preautrid.dto;

import java.io.Serializable;

public class Record12DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7394881472688079731L;
	private String numeroProgressivo;
	private String dataCreazioneDisposizione;
	private String dataCreazioneFlusso;
	private String causale;
	private String codicePaese;
	private String checkDigit;
	private String AbibancaAllineamento;
	private String cin;
	private String abi;
	private String cab;
	private String conto;
	private String codiceAzienda;
	private String tipoCodiceIndividuale;
	private String codiceIndividuale;
	private String tipoRecord = "12";
	private String iban = null;
	
	private String tipoMandato;
	private String tipoIncasso;
	
	
	
	
	public String getTipoIncasso() {
		return tipoIncasso;
	}

	public void setTipoIncasso(String tipoIncasso) {
		this.tipoIncasso = tipoIncasso;
	}

	public String getTipoMandato() {
		return tipoMandato;
	}

	public void setTipoMandato(String tipoMandato) {
		this.tipoMandato = tipoMandato;
	}

	

	StringBuilder buildIban = new StringBuilder();
	public String getIban() {

		buildIban.setLength(0);
		buildIban.append(codicePaese);		
		buildIban.append(cin);		
		buildIban.append(checkDigit);
		buildIban.append(abi);
		buildIban.append(cab);		
		buildIban.append(conto);
		iban = buildIban.toString();
		return iban;
	}

	public String getNumeroProgressivo() {
		return numeroProgressivo;
	}

	public void setNumeroProgressivo(String numeroProgressivo) {
		this.numeroProgressivo = numeroProgressivo;
	}

	public String getDataCreazioneDisposizione() {
		return dataCreazioneDisposizione;
	}

	public void setDataCreazioneDisposizione(String dataCreazioneDisposizione) {
		this.dataCreazioneDisposizione = dataCreazioneDisposizione;
	}

	public String getDataCreazioneFlusso() {
		return dataCreazioneFlusso;
	}

	public void setDataCreazioneFlusso(String dataCreazioneFlusso) {
		this.dataCreazioneFlusso = dataCreazioneFlusso;
	}

	public String getCausale() {
		return causale;
	}

	public void setCausale(String causale) {
		this.causale = causale;
	}

	public String getCodicePaese() {
		return codicePaese;
	}

	public void setCodicePaese(String codicePaese) {
		this.codicePaese = codicePaese;
	}

	public String getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(String checkDigit) {
		this.checkDigit = checkDigit;
	}

	public String getAbibancaAllineamento() {
		return AbibancaAllineamento;
	}

	public void setAbibancaAllineamento(String abibancaAllineamento) {
		AbibancaAllineamento = abibancaAllineamento;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getAbi() {
		return abi;
	}

	public void setAbi(String abi) {
		this.abi = abi;
	}

	public String getCab() {
		return cab;
	}

	public void setCab(String cab) {
		this.cab = cab;
	}

	public String getConto() {
		return conto;
	}

	public void setConto(String conto) {
		this.conto = conto;
	}

	public String getCodiceAzienda() {
		return codiceAzienda;
	}

	public void setCodiceAzienda(String codiceAzienda) {
		this.codiceAzienda = codiceAzienda;
	}

	public String getTipoCodiceIndividuale() {
		return tipoCodiceIndividuale;
	}

	public void setTipoCodiceIndividuale(String tipoCodiceIndividuale) {
		this.tipoCodiceIndividuale = tipoCodiceIndividuale;
	}

	public String getCodiceIndividuale() {
		return codiceIndividuale;
	}

	public void setCodiceIndividuale(String codiceIndividuale) {
		this.codiceIndividuale = codiceIndividuale;
	}

	@Override
	public String toString() {
		StringBuilder rec12 = new StringBuilder();
		rec12.append(" ");
		rec12.append(tipoRecord);
		rec12.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec12.append(dataCreazioneDisposizione);
		rec12.append(tipoMandato);
		rec12.append(String.format("%4s", ""));
		rec12.append(causale);
		rec12.append(String.format("%10s", ""));
		rec12.append(String.format("%2s", codicePaese));
		rec12.append(String.format("%2s", checkDigit).replaceAll(" ", "0"));
		rec12.append(String.format("%5s", AbibancaAllineamento).replaceAll(" ",
				"0"));
		rec12.append(String.format("%16s", ""));
		rec12.append(cin);
		rec12.append(String.format("%5s", abi).replaceAll(" ", "0"));
		rec12.append(String.format("%5s", cab).replaceAll(" ", "0"));
		rec12.append(String.format("%12s", conto).replaceAll(" ", "0"));
		rec12.append(codiceAzienda);
		rec12.append(tipoCodiceIndividuale);
		rec12.append(String.format("%16s", codiceIndividuale).replaceAll(" ",
				"0"));
		rec12.append(tipoIncasso);
		rec12.append(String.format("%2s", ""));
		return rec12.toString();
	}
	
	
	public String toStringSeda() {
		StringBuilder rec12 = new StringBuilder();
		rec12.append(" ");
		rec12.append(tipoRecord);
		rec12.append(String.format("%7s", numeroProgressivo).replaceAll(" ",
				"0"));
		rec12.append(dataCreazioneDisposizione);		
		rec12.append(String.format("%12s", ""));
		rec12.append(causale);
		rec12.append(String.format("%10s", ""));
		rec12.append(String.format("%2s", ""));
		rec12.append(String.format("%2s", ""));
		rec12.append(String.format("%5s", AbibancaAllineamento).replaceAll(" ",
				"0"));
		rec12.append(String.format("%16s", ""));
		rec12.append(" ");		
		rec12.append(String.format("%5s", abi).replaceAll(" ", "0"));
		rec12.append(String.format("%5s", ""));
		rec12.append(String.format("%12s", ""));
		rec12.append(codiceAzienda);
		rec12.append(tipoCodiceIndividuale);
		rec12.append(String.format("%16s", codiceIndividuale).replaceAll(" ",
				"0"));		
		rec12.append(String.format("%6s", ""));
		return rec12.toString();
	}

}
