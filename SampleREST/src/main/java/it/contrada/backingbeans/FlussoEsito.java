package it.contrada.backingbeans;

public class FlussoEsito {
	private String tsRicevuto;
	private String dtDa;
	private String dtA;
	private String nomeFile;
	private int nrDisp;
	private Long imFlusso;

	public Long getImFlusso() {
		return imFlusso;
	}

	public void setImFlusso(Long imFlusso) {
		this.imFlusso = imFlusso;
	}

	public int getNrDisp() {
		return nrDisp;
	}

	public void setNrDisp(int nrDisp) {
		this.nrDisp = nrDisp;
	}

	public String getTsRicevuto() {
		return tsRicevuto;
	}

	public void setTsRicevuto(String tsRicevuto) {
		this.tsRicevuto = tsRicevuto;
	}

	public String getDtDa() {
		return dtDa;
	}

	public void setDtDa(String dtDa) {
		this.dtDa = dtDa;
	}

	public String getDtA() {
		return dtA;
	}

	public void setDtA(String dtA) {
		this.dtA = dtA;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

}
