package it.contrada.dto;

import java.io.Serializable;

public class IncassoAnnualeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3199712050997712376L;
	private String dsIncasso;
	private int incassato;
	private int quota;
	private int anno;
	private double percentuale;

	public double getPercentuale() {
		return percentuale;
	}

	public String getDsIncasso() {
		return dsIncasso;
	}

	public void setDsIncasso(String dsIncasso) {
		this.dsIncasso = dsIncasso;
	}

	public int getIncassato() {
		return incassato;
	}

	public void setIncassato(int incassato) {
		this.incassato = incassato;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

}
