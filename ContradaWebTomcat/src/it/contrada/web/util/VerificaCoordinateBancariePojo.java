/*
 * VerificaCoordinateBancariePojo.java
 * version  1.0
 * Copyright (c) 2011, IBM India Ltd. All rights reserved.
 */
package it.contrada.web.util;

import it.contrada.dto.IbanDTO;

/**
 * pojo class VerificaCoordinateBancariePojo
 * 
 * @author IBM India
 * @version 1.0
 */

public class VerificaCoordinateBancariePojo {

	private CalcolaCoordinateBancariePojo calcolaCoordinateBancariePojo;
	
	public VerificaCoordinateBancariePojo()
	{
		calcolaCoordinateBancariePojo=new CalcolaCoordinateBancariePojo();
	}

	public boolean verificaIban(final String codiceIban) {

		if (codiceIban == null) {
			return false;
		}
		IbanDTO ibanDTO = verificaFormato(codiceIban);
		if (ibanDTO == null)
			return false;

		Integer codiceAbi = estraiAbi(ibanDTO);

		Integer codiceCab = estraiCab(ibanDTO);

		String codiceConto = estraiConto(ibanDTO);
		String codicePaese = ibanDTO.getCodicePaese();

		String codiceIbanCalcolato = calcolaCoordinateBancariePojo.calcolaIban(
				codicePaese, codiceAbi, codiceCab, codiceConto).toString();

		if ((codiceIbanCalcolato != null)
				&& (codiceIban.trim().equals(codiceIbanCalcolato.trim()))) {

			return true;
		} else {

			return false;
		}

	}

	/**
	 * @param ibanDTO
	 * @return Long
	 * @throws ComponentBlockingException
	 */
	private String estraiConto(final IbanDTO ibanDTO) {
		Long cdConto = null;
		String stringCdConto=null;
		if ((ibanDTO.getCodicePaese() != null)
				&& (IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(ibanDTO.getCodicePaese()
						.trim()))) {
			stringCdConto = (ibanDTO.getCodiceBban()).substring(11, 23);
			
			

		}
		return stringCdConto;
		
	}

	/**
	 * @param ibanDTO
	 * @return Integer
	 * @throws ComponentBlockingException
	 */
	private Integer estraiCab(final IbanDTO ibanDTO) {
		Integer cdCab = null;
		if ((ibanDTO.getCodicePaese() != null)
				&& (IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(ibanDTO.getCodicePaese()
						.trim()))) {
			String stringCdCab = (ibanDTO.getCodiceBban()).substring(6, 11);

			try {
				cdCab = Integer.parseInt(stringCdCab);
			} catch (NumberFormatException e) {

			}

		}
		return cdCab;
	}

	/**
	 * @param ibanDTO
	 * @return Integer
	 * @throws ComponentBlockingException
	 */
	private Integer estraiAbi(final IbanDTO ibanDTO) {
		Integer cdAbi = null;
		if ((ibanDTO.getCodicePaese() != null)
				&& (IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(ibanDTO.getCodicePaese()
						.trim()))) {
			String stringCdAbi = (ibanDTO.getCodiceBban()).substring(1, 6);

			cdAbi = Integer.parseInt(stringCdAbi);

		}
		return cdAbi;
	}

	/**
	 * @param codiceIban
	 * @return IbanDTO
	 * @throws ComponentBlockingException
	 */
	private IbanDTO verificaFormato(final String codiceIban) {

		String initial_string = codiceIban.substring(0, 2);
		if (!(IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(initial_string.trim()))) {
			return null;
		}
		if (codiceIban.length() != 27) {
			return null;
		}
		IbanDTO ibanDTO = new IbanDTO();
		ibanDTO.setCodicePaese(initial_string);
		ibanDTO.setCodiceControllo(codiceIban.substring(2, 4));
		ibanDTO.setCodiceBban(codiceIban.substring(4, 27));

		return ibanDTO;
	}

	/**
	 * @return the calcolaCoordinateBancariePojo
	 */
	public CalcolaCoordinateBancariePojo getCalcolaCoordinateBancariePojo() {
		return calcolaCoordinateBancariePojo;
	}

	/**
	 * @param calcolaCoordinateBancariePojo
	 *            the calcolaCoordinateBancariePojo to set
	 */
	public void setCalcolaCoordinateBancariePojo(
			CalcolaCoordinateBancariePojo calcolaCoordinateBancariePojo) {
		this.calcolaCoordinateBancariePojo = calcolaCoordinateBancariePojo;
	}
}
