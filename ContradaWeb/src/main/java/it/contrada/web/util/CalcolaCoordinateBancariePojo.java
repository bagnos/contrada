/*
 * CalcolaCoordinateBancariePojo.java
 * version  1.0
 * Copyright (c) 2010, IBM India Ltd. All rights reserved.
 */
package it.contrada.web.util;

import it.contrada.dto.IbanDTO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * implementation class CalcolaCoordinateBancariePojo
 * 
 * @author IBM India
 * @version 1.0
 */

public class CalcolaCoordinateBancariePojo {

	/** logger instance */

	private static List<Integer> listaPari = null;

	private static List<Integer> listaDispari = null;

	private static List<Character> vocabolarioNumerico = null;

	private static List<Character> vocabolarioAlfabetico = null;

	static {
		// load maps
		initVocabolario();
	}

	/**
	 * The method adds param to list.
	 * 
	 * 
	 * @return
	 */
	private static void initVocabolario() {
		listaPari = new ArrayList<Integer>();
		listaPari.add(new Integer(0));
		listaPari.add(new Integer(1));
		listaPari.add(new Integer(2));
		listaPari.add(new Integer(3));
		listaPari.add(new Integer(4));
		listaPari.add(new Integer(5));
		listaPari.add(new Integer(6));
		listaPari.add(new Integer(7));
		listaPari.add(new Integer(8));
		listaPari.add(new Integer(9));
		listaPari.add(new Integer(10));
		listaPari.add(new Integer(11));
		listaPari.add(new Integer(12));
		listaPari.add(new Integer(13));
		listaPari.add(new Integer(14));
		listaPari.add(new Integer(15));
		listaPari.add(new Integer(16));
		listaPari.add(new Integer(17));
		listaPari.add(new Integer(18));
		listaPari.add(new Integer(19));
		listaPari.add(new Integer(20));
		listaPari.add(new Integer(21));
		listaPari.add(new Integer(22));
		listaPari.add(new Integer(23));
		listaPari.add(new Integer(24));
		listaPari.add(new Integer(25));
		listaPari.add(new Integer(26));
		listaPari.add(new Integer(27));
		listaPari.add(new Integer(28));

		listaDispari = new ArrayList<Integer>();
		listaDispari.add(new Integer(1));
		listaDispari.add(new Integer(0));
		listaDispari.add(new Integer(5));
		listaDispari.add(new Integer(7));
		listaDispari.add(new Integer(9));
		listaDispari.add(new Integer(13));
		listaDispari.add(new Integer(15));
		listaDispari.add(new Integer(17));
		listaDispari.add(new Integer(19));
		listaDispari.add(new Integer(21));
		listaDispari.add(new Integer(2));
		listaDispari.add(new Integer(4));
		listaDispari.add(new Integer(18));
		listaDispari.add(new Integer(20));
		listaDispari.add(new Integer(11));
		listaDispari.add(new Integer(3));
		listaDispari.add(new Integer(6));
		listaDispari.add(new Integer(8));
		listaDispari.add(new Integer(12));
		listaDispari.add(new Integer(14));
		listaDispari.add(new Integer(16));
		listaDispari.add(new Integer(10));
		listaDispari.add(new Integer(22));
		listaDispari.add(new Integer(25));
		listaDispari.add(new Integer(24));
		listaDispari.add(new Integer(23));
		listaDispari.add(new Integer(27));
		listaDispari.add(new Integer(28));
		listaDispari.add(new Integer(26));

		vocabolarioNumerico = new ArrayList<Character>();
		vocabolarioNumerico.add('0');
		vocabolarioNumerico.add('1');
		vocabolarioNumerico.add('2');
		vocabolarioNumerico.add('3');
		vocabolarioNumerico.add('4');
		vocabolarioNumerico.add('5');
		vocabolarioNumerico.add('6');
		vocabolarioNumerico.add('7');
		vocabolarioNumerico.add('8');
		vocabolarioNumerico.add('9');

		vocabolarioAlfabetico = new ArrayList<Character>();
		vocabolarioAlfabetico.add('A');
		vocabolarioAlfabetico.add('B');
		vocabolarioAlfabetico.add('C');
		vocabolarioAlfabetico.add('D');
		vocabolarioAlfabetico.add('E');
		vocabolarioAlfabetico.add('F');
		vocabolarioAlfabetico.add('G');
		vocabolarioAlfabetico.add('H');
		vocabolarioAlfabetico.add('I');
		vocabolarioAlfabetico.add('J');
		vocabolarioAlfabetico.add('K');
		vocabolarioAlfabetico.add('L');
		vocabolarioAlfabetico.add('M');
		vocabolarioAlfabetico.add('N');
		vocabolarioAlfabetico.add('O');
		vocabolarioAlfabetico.add('P');
		vocabolarioAlfabetico.add('Q');
		vocabolarioAlfabetico.add('R');
		vocabolarioAlfabetico.add('S');
		vocabolarioAlfabetico.add('T');
		vocabolarioAlfabetico.add('U');
		vocabolarioAlfabetico.add('V');
		vocabolarioAlfabetico.add('W');
		vocabolarioAlfabetico.add('X');
		vocabolarioAlfabetico.add('Y');
		vocabolarioAlfabetico.add('Z');
		vocabolarioAlfabetico.add('-');
		vocabolarioAlfabetico.add('.');
		vocabolarioAlfabetico.add(' ');
	}

	/**
	 * @param codicePaese
	 * @param codiceAbi
	 * @param codiceCab
	 * @param numeroContoCorrente
	 * @throws ComponentNonBlockingException
	 * @return String
	 */

	public IbanDTO calcolaIban(String codicePaese, final Integer codiceAbi,
			final Integer codiceCab,  String numeroContoCorrente) {

		IbanDTO dto = null;
		if (codicePaese == null || codicePaese.trim() == "") {
			codicePaese = IConstants.CONSTANT_VAL_IT;
		}
		if (numeroContoCorrente!=null)
		{
			numeroContoCorrente=numeroContoCorrente.toUpperCase();
		}

		dto = verificaFormato(codicePaese, codiceAbi, codiceCab,
				numeroContoCorrente);

		dto.setCodiceBban(calcolaBban(dto.getCodicePaese(), dto.getCodiceAbi(),
				dto.getCodiceCab(), dto.getNumeroContoCorrente()));

		dto.setCodiceControllo(calcolaCodiceControlloIban(dto.getCodicePaese(),
				dto.getCodiceBban()));

		return dto;

	}

	/**
	 * This method is used for validating the input fields.
	 * 
	 * @param codicePaese
	 * @param codiceAbi
	 * @param codiceCab
	 * @param numeroContoCorrente
	 * @throws ComponentNonBlockingException
	 * @return IbanDTO
	 */
	public IbanDTO verificaFormato(final String codicePaese,
			final Integer codiceAbi, final Integer codiceCab,
			final String numeroContoCorrente) {

		IbanDTO dto = new IbanDTO();
		StringBuffer sb = null;

		if (codicePaese == null
				|| !(IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(codicePaese.trim()))) {

		} else {
			dto.setCodicePaese(codicePaese);
		}

		if (codiceAbi > 0
				&& codiceAbi.toString().length() <= IConstants.CONSTANT_5) {

			String strCodiceAbi = codiceAbi.toString();

			int strCodiceAbiLength = strCodiceAbi.length();
			sb = new StringBuffer(codiceAbi.toString());
			while (strCodiceAbiLength < IConstants.CONSTANT_5) {
				sb.insert(0, IConstants.CONSTANT_VAL_0);
				strCodiceAbiLength = sb.length();
			}
			strCodiceAbi = sb.toString();

			dto.setCodiceAbi(Integer.parseInt(strCodiceAbi));
		}

		if (codiceCab > 0
				&& codiceCab.toString().length() <= IConstants.CONSTANT_5) {

			String strCodiceCab = codiceCab.toString();

			int strCodiceCabLength = strCodiceCab.length();
			sb = new StringBuffer(codiceCab.toString());
			while (strCodiceCabLength < IConstants.CONSTANT_5) {
				sb.insert(0, IConstants.CONSTANT_VAL_0);
				strCodiceCabLength = sb.length();
			}
			strCodiceCab = sb.toString();
			dto.setCodiceCab(Integer.parseInt(strCodiceCab));
		}

		if (numeroContoCorrente!=null && numeroContoCorrente.toString().length() <= IConstants.CONSTANT_12) {

			String strNumeroContoCorrente = numeroContoCorrente.toString();

			int strNumeroContoCorrenteLength = strNumeroContoCorrente.length();
			sb = new StringBuffer(strNumeroContoCorrente);
			while (strNumeroContoCorrenteLength < IConstants.CONSTANT_12) {
				sb.insert(0, IConstants.CONSTANT_VAL_0);
				strNumeroContoCorrenteLength = sb.length();
			}
			strNumeroContoCorrente = sb.toString();
			dto.setNumeroContoCorrente(strNumeroContoCorrente);
		}

		return dto;
	}

	/**
	 * @param codicePaese
	 * @param codiceAbi
	 * @param codiceCab
	 * @param numeroContoCorrente
	 * @throws ComponentNonBlockingException
	 * @return String
	 */
	private String calcolaBban(String codicePaese, final Integer codiceAbi,
			final Integer codiceCab, final String numeroContoCorrente) {

		StringBuffer sb = null;

		if (!(IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(codicePaese.trim()))) {
			codicePaese = IConstants.CONSTANT_VAL_IT;
		}
		String strCodiceAbi = null;
		if (codiceAbi > 0
				&& codiceAbi.toString().length() <= IConstants.CONSTANT_5) {

			strCodiceAbi = codiceAbi.toString();

			int strCodiceAbiLength = strCodiceAbi.length();
			sb = new StringBuffer(codiceAbi.toString());
			while (strCodiceAbiLength < IConstants.CONSTANT_5) {
				sb.insert(0, IConstants.CONSTANT_VAL_0);
				strCodiceAbiLength = sb.length();
			}
			strCodiceAbi = sb.toString();
		}
		String strCodiceCab = null;
		if (codiceCab > 0
				&& codiceCab.toString().length() <= IConstants.CONSTANT_5) {

			strCodiceCab = codiceCab.toString();

			int strCodiceCabLength = strCodiceCab.length();
			sb = new StringBuffer(codiceCab.toString());
			while (strCodiceCabLength < IConstants.CONSTANT_5) {
				sb.insert(0, IConstants.CONSTANT_VAL_0);
				strCodiceCabLength = sb.length();
			}
			strCodiceCab = sb.toString();
		}
		String strNumeroContoCorrente = null;
		if (numeroContoCorrente.toString().length() <= IConstants.CONSTANT_12) {
			strNumeroContoCorrente = numeroContoCorrente.toString();

			int strNumeroContoCorrenteLength = strNumeroContoCorrente.length();
			sb = new StringBuffer(strNumeroContoCorrente);
			while (strNumeroContoCorrenteLength < IConstants.CONSTANT_12) {
				sb.insert(0, IConstants.CONSTANT_VAL_0);
				strNumeroContoCorrenteLength = sb.length();
			}
			strNumeroContoCorrente = sb.toString();
		}

		String sCodice = strCodiceAbi + strCodiceCab + strNumeroContoCorrente;

		int sCodiceLength = sCodice.length();
		int somma = 0;
		for (int k = 0; k < sCodiceLength; k++) {

			Integer position = null;

			if (Character.isDigit(sCodice.charAt(k))) {
				position = vocabolarioNumerico.indexOf(sCodice.charAt(k));
			} else {
				position = vocabolarioAlfabetico.indexOf(sCodice.charAt(k));
			}

			/* MODIFICA: prima faceva una somma solo nel caso di pari */
			if (k % 2 == 0)
				somma += listaDispari.get(position);
			else
				somma += listaPari.get(position);

			/*
			 * if (k % 2 == 0) { somma += listaDispari.get(position) +
			 * listaPari.get(position);
			 */
		}

		String codiceBban = vocabolarioAlfabetico.get(somma % 26) + sCodice;

		return codiceBban;
	}

	/**
	 * @param codicePaese
	 * @param codiceBban
	 * @throws ComponentNonBlockingException
	 * @return String
	 */
	private String calcolaCodiceControlloIban(final String codicePaese,
			final String codiceBban) {

		StringBuffer sb = null;

		if (!(IConstants.CONSTANT_VAL_IT.equalsIgnoreCase(codicePaese.trim()))) {

		}

		sb = new StringBuffer();
		sb.append(codiceBban);
		sb.append(codicePaese);
		sb.append("00");
		String sCodice = sb.toString().toUpperCase();

		String sAlfa = IConstants.CONSTANT_VAL_SALFA;

		int sCodiceLength = sCodice.length();
		sb = new StringBuffer();
		for (int k = 0; k < sCodiceLength; k++) {
			char currentCharacter = sCodice.charAt(k);

			if ((sAlfa.indexOf(currentCharacter)) >= 0) {

				int p = sAlfa.indexOf(currentCharacter);
				sb.append(p + IConstants.CONSTANT_10);

			} else {
				sb.append(currentCharacter);

			}

		}
		String sRisultato = sb.toString();

		StringBuffer sbResto = new StringBuffer();
		StringBuffer sbInterno = new StringBuffer();
		String sResto = IConstants.CONSTANT_EMPTY_STRING;
		BigInteger sRestoInteger = null;
		BigInteger bigInteger97 = new BigInteger(IConstants.STRING_97);
		BigInteger remainder = null;
		BigInteger wholePart = null;
		int sRisultatoLength = sRisultato.length();
		for (int i = 0; i < sRisultatoLength; i++) {

			sbResto.append(sRisultato.charAt(i));

			sResto = sbResto.toString();
			sRestoInteger = new BigInteger(sResto);
			if (sRestoInteger.compareTo(bigInteger97) < 0) {
				sbInterno.append(IConstants.CONSTANT_VAL_0);
			}

			else if (sRestoInteger.compareTo(bigInteger97) >= 0) {
				remainder = sRestoInteger.mod(bigInteger97);

				wholePart = sRestoInteger.divide(bigInteger97);

				sResto = remainder.toString();

				sbInterno.append(wholePart);
			}
		}

		BigInteger controlCode = new BigInteger(IConstants.STRING_98)
				.subtract(new BigInteger(sResto));

		String codiceControllo = String.valueOf(controlCode);

		if (codiceControllo.length() != IConstants.CONSTANT_2) {
			sb = new StringBuffer();
			sb.append(IConstants.CONSTANT_VAL_0);
			sb.append(codiceControllo);
			codiceControllo = sb.toString();
		}

		return codiceControllo;
	}

}
