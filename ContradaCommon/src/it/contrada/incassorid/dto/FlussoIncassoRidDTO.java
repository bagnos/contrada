package it.contrada.incassorid.dto;

import it.contrada.dto.OperazioneDTO;

import java.io.Serializable;
import java.util.List;

public class FlussoIncassoRidDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5427641518547136101L;
	private int nrIncassi;
	private int imFlusso;
	private String nomeFile;
	
	private OperazioneDTO operazione;
	private String nomeFileSemplice;
	
	
	
	public String getNomeFileSemplice() {
		return nomeFileSemplice;
	}
	public void setNomeFileSemplice(String nomeFileSemplice) {
		this.nomeFileSemplice = nomeFileSemplice;
	}
	public OperazioneDTO getOperazione() {
		return operazione;
	}
	public void setOperazione(OperazioneDTO operazione) {
		this.operazione = operazione;
	}
	public String getNomeFile() {
		return nomeFile;
	}
	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}
	private List<IncassoRidDTO> incassi;
	
	public List<IncassoRidDTO> getIncassi() {
		return incassi;
	}
	public void setIncassi(List<IncassoRidDTO> incassi) {
		this.incassi = incassi;
	}
	public int getNrIncassi() {
		return nrIncassi;
	}
	public void setNrIncassi(int nrIncassi) {
		this.nrIncassi = nrIncassi;
	}
	public int getImFlusso() {
		return imFlusso;
	}
	public void setImFlusso(int imFlusso) {
		this.imFlusso = imFlusso;
	}
	
}
