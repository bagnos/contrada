/*
 * VerificaDatiAnagraficiClienteBO.java
 * version  1.0
 * Copyright (c) 2010, IBM India Ltd. All rights reserved.
 */
package it.contrada.web.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificaDatiAnagraficiClientePojo {
	private static final Map<Character, Integer> EVEN_POSITION_MAP = new HashMap<Character, Integer>(
			35);
	/* used for odd postion's character numeric equivalent */
	private static final Map<Character, Integer> ODD_POSITION_MAP = new HashMap<Character, Integer>(
			35);
	/* used for remainder(resto) character equivalent */
	private static final Map<Integer, Character> RESTO_MAP = new HashMap<Integer, Character>(
			35);

	static {
		// load maps
		initializeMap();
	}

	private static void initializeMap() {

		ODD_POSITION_MAP.put('0', 1);
		ODD_POSITION_MAP.put('1', 0);
		ODD_POSITION_MAP.put('2', 5);
		ODD_POSITION_MAP.put('3', 7);
		ODD_POSITION_MAP.put('4', 9);
		ODD_POSITION_MAP.put('5', 13);
		ODD_POSITION_MAP.put('6', 15);
		ODD_POSITION_MAP.put('7', 17);
		ODD_POSITION_MAP.put('8', 19);
		ODD_POSITION_MAP.put('9', 21);
		ODD_POSITION_MAP.put('A', 1);
		ODD_POSITION_MAP.put('B', 0);
		ODD_POSITION_MAP.put('C', 5);
		ODD_POSITION_MAP.put('D', 7);
		ODD_POSITION_MAP.put('E', 9);
		ODD_POSITION_MAP.put('F', 13);
		ODD_POSITION_MAP.put('G', 15);
		ODD_POSITION_MAP.put('H', 17);
		ODD_POSITION_MAP.put('I', 19);
		ODD_POSITION_MAP.put('J', 21);
		ODD_POSITION_MAP.put('K', 2);
		ODD_POSITION_MAP.put('L', 4);
		ODD_POSITION_MAP.put('M', 18);
		ODD_POSITION_MAP.put('N', 20);
		ODD_POSITION_MAP.put('O', 11);
		ODD_POSITION_MAP.put('P', 3);
		ODD_POSITION_MAP.put('Q', 6);
		ODD_POSITION_MAP.put('R', 8);
		ODD_POSITION_MAP.put('S', 12);
		ODD_POSITION_MAP.put('T', 14);
		ODD_POSITION_MAP.put('U', 16);
		ODD_POSITION_MAP.put('V', 10);
		ODD_POSITION_MAP.put('W', 22);
		ODD_POSITION_MAP.put('X', 25);
		ODD_POSITION_MAP.put('Y', 24);
		ODD_POSITION_MAP.put('Z', 23);

		EVEN_POSITION_MAP.put('0', 0);
		EVEN_POSITION_MAP.put('1', 1);
		EVEN_POSITION_MAP.put('2', 2);
		EVEN_POSITION_MAP.put('3', 3);
		EVEN_POSITION_MAP.put('4', 4);
		EVEN_POSITION_MAP.put('5', 5);
		EVEN_POSITION_MAP.put('6', 6);
		EVEN_POSITION_MAP.put('7', 7);
		EVEN_POSITION_MAP.put('8', 8);
		EVEN_POSITION_MAP.put('9', 9);
		EVEN_POSITION_MAP.put('A', 0);
		EVEN_POSITION_MAP.put('B', 1);
		EVEN_POSITION_MAP.put('C', 2);
		EVEN_POSITION_MAP.put('D', 3);
		EVEN_POSITION_MAP.put('E', 4);
		EVEN_POSITION_MAP.put('F', 5);
		EVEN_POSITION_MAP.put('G', 6);
		EVEN_POSITION_MAP.put('H', 7);
		EVEN_POSITION_MAP.put('I', 8);
		EVEN_POSITION_MAP.put('J', 9);
		EVEN_POSITION_MAP.put('K', 10);
		EVEN_POSITION_MAP.put('L', 11);
		EVEN_POSITION_MAP.put('M', 12);
		EVEN_POSITION_MAP.put('N', 13);
		EVEN_POSITION_MAP.put('O', 14);
		EVEN_POSITION_MAP.put('P', 15);
		EVEN_POSITION_MAP.put('Q', 16);
		EVEN_POSITION_MAP.put('R', 17);
		EVEN_POSITION_MAP.put('S', 18);
		EVEN_POSITION_MAP.put('T', 19);
		EVEN_POSITION_MAP.put('U', 20);
		EVEN_POSITION_MAP.put('V', 21);
		EVEN_POSITION_MAP.put('W', 22);
		EVEN_POSITION_MAP.put('X', 23);
		EVEN_POSITION_MAP.put('Y', 24);
		EVEN_POSITION_MAP.put('Z', 25);

		RESTO_MAP.put(0, 'A');
		RESTO_MAP.put(1, 'B');
		RESTO_MAP.put(2, 'C');
		RESTO_MAP.put(3, 'D');
		RESTO_MAP.put(4, 'E');
		RESTO_MAP.put(5, 'F');
		RESTO_MAP.put(6, 'G');
		RESTO_MAP.put(7, 'H');
		RESTO_MAP.put(8, 'I');
		RESTO_MAP.put(9, 'J');
		RESTO_MAP.put(10, 'K');
		RESTO_MAP.put(11, 'L');
		RESTO_MAP.put(12, 'M');
		RESTO_MAP.put(13, 'N');
		RESTO_MAP.put(14, 'O');
		RESTO_MAP.put(15, 'P');
		RESTO_MAP.put(16, 'Q');
		RESTO_MAP.put(17, 'R');
		RESTO_MAP.put(18, 'S');
		RESTO_MAP.put(19, 'T');
		RESTO_MAP.put(20, 'U');
		RESTO_MAP.put(21, 'V');
		RESTO_MAP.put(22, 'W');
		RESTO_MAP.put(23, 'X');
		RESTO_MAP.put(24, 'Y');
		RESTO_MAP.put(25, 'Z');
	}

	public boolean verificaFormaleCodiceFiscale(final String codiceFiscale) {

		// local variables holding sum of even/odd positions characters numeral
		// equivalents
		int totalEvenPos = 0;
		int totalOddPos = 0;
		// flags used for branching
		boolean codiceFiscalePersonaGiuridica = false;
		boolean codiceFiscalePersonaFisica = false;

		// check for emptiness
		if (codiceFiscale == null || codiceFiscale.trim() == "") {
			return false;
		}

		// local convenient access variables
		String codiFiscale = codiceFiscale.trim().toUpperCase();
		final int codFscleLength = codiFiscale.length();

		// depending on input string length perform various operations
		if (codFscleLength != 16) { // length greater than 16
			return false;
		}

		if (codFscleLength == 16) { // length equal to 16
			if (false == regexValidate(codiFiscale, "[A-Z0-9]*")) {
				return false;
			}

			else {
				// length equal to 16 and composed of only alphanumeric
				// characters
				codiceFiscalePersonaFisica = true;
			}
		}

		// depending on flags already set do branching
		if (codiceFiscalePersonaFisica) { // if codFscleLength == 16
			// and containing only alphaNumeric characters

			/*
			 * Mapping alphaNumeric characters of input string 'codiFiscale'
			 * based on predefined algorithm to numeric equivalent & calculating
			 * the sum of even/odd positions
			 */

			for (int position = 1; position <= 15; position++) {

				int index = position - 1;
				// check for even/odd position
				if (position % 2 != 0) {
					totalOddPos = totalOddPos
							+ ODD_POSITION_MAP.get(codiFiscale.charAt(index));
				} else {
					totalEvenPos = totalEvenPos
							+ EVEN_POSITION_MAP.get(codiFiscale.charAt(index));
				}
			}// end for

			final int resto = (totalEvenPos + totalOddPos) % 26;

			final char cIN = RESTO_MAP.get(resto);

			// Matching 16th character of input string 'codiFiscale' to CIN
			if (cIN != codiFiscale.charAt(15)) {
				// verification failed
				// eccezione
				return false;
			}

		}
		return true;
	}

	private boolean regexValidate(final String text, final String regex) {

		// pattern matching with input text
		Matcher m = patternMatcher(text, regex);

		return m.matches();
	}

	private Matcher patternMatcher(final String inputVariable,
			final String regex) {
		// pattern matching with input String
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(inputVariable);

		return m;
	}

}
