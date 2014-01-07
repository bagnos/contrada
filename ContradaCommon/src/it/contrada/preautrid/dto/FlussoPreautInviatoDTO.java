package it.contrada.preautrid.dto;

import it.contrada.dto.OperazioneDTO;
import it.contrada.dto.RidDTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FlussoPreautInviatoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5766721253722176858L;
	private String nomeFile;
	private int nrDisposizioni;
	private OperazioneDTO operazione;
	private String nomeFileSemplice;
	private Date dtInvio;
	private List<RidDTO> rids;

	public void setRids(List<RidDTO> rids) {
		this.rids = rids;
	}

	public List<RidDTO> getRids() {
		return rids;
	}

	public Date getDtInvio() {
		return dtInvio;
	}

	public void setDtInvio(Date dtInvio) {
		this.dtInvio = dtInvio;
	}

	public void setNomeFileSemplice(String nomeFileSemplice) {
		this.nomeFileSemplice = nomeFileSemplice;
	}

	public String getNomeFileSemplice() {
		return nomeFileSemplice;
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

	public int getNrDisposizioni() {
		return nrDisposizioni;
	}

	public void setNrDisposizioni(int nrDisposizioni) {
		this.nrDisposizioni = nrDisposizioni;
	}

}
