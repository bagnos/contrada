package it.contrada.backingbeans.model;

import it.contrada.dto.CapDTO;

public class StradarioBean {

	private int tipoInserimento;
	private CapDTO cap;
	private String dsCap;
	private String dsStrada;
	private String dsLocalita;
	private int cdProvincia;
	private boolean assegnaGestore;
	private Integer cdLocalita;
	private Integer cdComune;
	private String cdCap;
	private String cdStato;
	private String dsProvincia;
	private String cdSiglaProvincia;
	
	

	public String getDsProvincia() {
		return dsProvincia;
	}

	public void setDsProvincia(String dsProvincia) {
		this.dsProvincia = dsProvincia;
	}

	public String getCdSiglaProvincia() {
		return cdSiglaProvincia;
	}

	public void setCdSiglaProvincia(String cdSiglaProvincia) {
		this.cdSiglaProvincia = cdSiglaProvincia;
	}

	public String getCdStato() {
		return cdStato;
	}

	public void setCdStato(String cdStato) {
		this.cdStato = cdStato;
	}

	public String getCdCap() {
		return cdCap;
	}

	public void setCdCap(String cdCap) {
		this.cdCap = cdCap;
	}

	public Integer getCdComune() {
		return cdComune;
	}

	public void setCdComune(Integer cdComune) {
		this.cdComune = cdComune;
	}

	public Integer getCdLocalita() {
		return cdLocalita;
	}

	public void setCdLocalita(Integer cdLocalita) {
		this.cdLocalita = cdLocalita;
	}

	public boolean isAssegnaGestore() {
		return assegnaGestore;
	}

	public void setAssegnaGestore(boolean assegnaGestore) {
		this.assegnaGestore = assegnaGestore;
	}

	public int getCdProvincia() {
		return cdProvincia;
	}

	public void setCdProvincia(int cdProvincia) {
		this.cdProvincia = cdProvincia;
	}

	public String getDsStrada() {
		return dsStrada;
	}

	public void setDsStrada(String dsStrada) {
		this.dsStrada = dsStrada;
	}

	public String getDsLocalita() {
		return dsLocalita;
	}

	public void setDsLocalita(String dsLocalita) {
		this.dsLocalita = dsLocalita;
	}

	public String getDsCap() {
		return dsCap;
	}

	public void setDsCap(String dsCap) {
		this.dsCap = dsCap;
	}

	public CapDTO getCap() {
		return cap;
	}

	public void setCap(CapDTO cap) {
		this.cap = cap;
	}

	public int getTipoInserimento() {
		return tipoInserimento;
	}

	public void setTipoInserimento(int tipoInserimento) {
		this.tipoInserimento = tipoInserimento;
	}

	public StradarioBean() {
		// TODO Auto-generated constructor stub
	}

}
