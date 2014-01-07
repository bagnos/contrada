/*IbanDTO.java
 * Copyright (c) 2010, IBM India Ltd. All rights reserved.
 * @author IBM India
 */
package it.contrada.dto;

import java.io.Serializable;

/**
 * @author IBM India
 * @version 1.0
 * 
 */
public class IbanDTO implements Serializable {

	/**
	 * Default serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * codicePaese instance variable
	 */
	private String codicePaese;

	/**
	 * codiceControllo instance variable
	 */
	private String codiceControllo;

	/**
	 * codiceBban instance variable
	 */
	private String codiceBban;

	/**
	 * codiceAbi instance variable
	 */
	private Integer codiceAbi;

	/**
	 * codiceCab instance variable
	 */
	private Integer codiceCab;

	/**
	 * numeroContoCorrente instance variable
	 */
	private String numeroContoCorrente;

	/**
	 * @return the codicePaese
	 */
	public String getCodicePaese() {
		return codicePaese;
	}

	/**
	 * @param codicePaese
	 *            the codicePaese to set
	 */
	public void setCodicePaese(final String codicePaese) {
		this.codicePaese = codicePaese;
	}

	/**
	 * @return the codiceControllo
	 */
	public String getCodiceControllo() {
		return codiceControllo;
	}

	/**
	 * @param codiceControllo
	 *            the codiceControllo to set
	 */
	public void setCodiceControllo(final String codiceControllo) {
		this.codiceControllo = codiceControllo;
	}

	/**
	 * @return the codiceBban
	 */
	public String getCodiceBban() {
		return codiceBban;
	}

	/**
	 * @param codiceBban
	 *            the codiceBban to set
	 */
	public void setCodiceBban(final String codiceBban) {
		this.codiceBban = codiceBban;
	}

	/**
	 * @return the codiceAbi
	 */
	public Integer getCodiceAbi() {
		return codiceAbi;
	}

	/**
	 * @param codiceAbi
	 *            the codiceAbi to set
	 */
	public void setCodiceAbi(final Integer codiceAbi) {
		this.codiceAbi = codiceAbi;
	}

	/**
	 * @return the codiceCab
	 */
	public Integer getCodiceCab() {
		return codiceCab;
	}

	/**
	 * @param codiceCab
	 *            the codiceCab to set
	 */
	public void setCodiceCab(final Integer codiceCab) {
		this.codiceCab = codiceCab;
	}

	/**
	 * @return the numeroContoCorrente
	 */
	public String getNumeroContoCorrente() {
		return numeroContoCorrente;
	}

	/**
	 * @param numeroContoCorrente
	 *            the numeroContoCorrente to set
	 */
	public void setNumeroContoCorrente(String numeroContoCorrente) {
		this.numeroContoCorrente = numeroContoCorrente;
	}
	
	@Override
	public String toString()
	{
		return getCodicePaese() + getCodiceControllo()
		+ getCodiceBban();
	}

}
