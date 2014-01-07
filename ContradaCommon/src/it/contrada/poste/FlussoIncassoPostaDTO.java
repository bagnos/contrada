package it.contrada.poste;

import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.RateizzazioneDTO;

import java.io.Serializable;
import java.util.List;

public class FlussoIncassoPostaDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3688519925034134242L;
	private Long idFlusso;
	private String txNomeFile;
	private java.sql.Date dtScadenza;
	private List<IncassoPostaDTO> incassi;
	private List<IncassoPostaDTO> incassiPerAnag;
	private int anno;
	private int nrIndirizzi;
	private java.sql.Timestamp tsProduzione;
	private int nrIndirizziDaFlusso;
	private Long impFlusso;
	private Long impFlussoIncassato;
	private List<RateizzazioneDTO> rate;
	private boolean showRate;
	private OperazioneDTO operazione;
	
	
	

	
	public OperazioneDTO getOperazione() {
		return operazione;
	}

	public void setOperazione(OperazioneDTO operazione) {
		this.operazione = operazione;
	}

	public boolean isShowRate() {
		return showRate;
	}

	public void setShowRate(boolean showRate) {
		this.showRate = showRate;
	}
	
	public boolean getShowRate()
	{
		return showRate;	
	}

	public List<RateizzazioneDTO> getRate() {
		return rate;
	}

	public void setRate(List<RateizzazioneDTO> rate) {
		this.rate = rate;
	}

	public void setTsProduzione(java.sql.Timestamp tsProduzione) {
		this.tsProduzione = tsProduzione;
	}

	public Long getImpFlussoIncassato() {
		return impFlussoIncassato;
	}

	public void setImpFlussoIncassato(Long impFlussoIncassato) {
		this.impFlussoIncassato = impFlussoIncassato;
	}

	public Long getImpFlusso() {
		return impFlusso;
	}

	public void setImpFlusso(Long impFlusso) {
		this.impFlusso = impFlusso;
	}

	public int getNrIndirizziDaFlusso() {
		return nrIndirizziDaFlusso;
	}

	public void setNrIndirizziDaFlusso(int nrIndirizziDaFlusso) {
		this.nrIndirizziDaFlusso = nrIndirizziDaFlusso;
	}

	public java.sql.Timestamp getTsProduzione() {
		return tsProduzione;
	}

	public int getNrIndirizzi() {
		nrIndirizzi = 0;
		if (incassi != null) {
			nrIndirizzi = incassi.size();
		}
		return nrIndirizzi;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public List<IncassoPostaDTO> getIncassiPerAnag() {
		return incassiPerAnag;
	}

	public void setIncassiPerAnag(List<IncassoPostaDTO> incassiPerAnag) {
		this.incassiPerAnag = incassiPerAnag;
	}

	public List<IncassoPostaDTO> getIncassi() {
		return incassi;
	}

	public void setIncassi(List<IncassoPostaDTO> incassi) {
		this.incassi = incassi;
	}

	public Long getIdFlusso() {
		return idFlusso;
	}

	public void setIdFlusso(Long idFlusso) {
		this.idFlusso = idFlusso;
	}

	public String getTxNomeFile() {
		return txNomeFile;
	}

	public void setTxNomeFile(String txNomeFile) {
		this.txNomeFile = txNomeFile;
	}

	public java.sql.Date getDtScadenza() {
		return dtScadenza;
	}

	public void setDtScadenza(java.sql.Date dtScadenza) {
		this.dtScadenza = dtScadenza;
	}

}
