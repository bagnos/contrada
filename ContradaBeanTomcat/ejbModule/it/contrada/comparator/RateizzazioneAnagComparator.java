package it.contrada.comparator;

import it.contrada.dto.RateizzazioneDTO;

import java.util.Comparator;

public class RateizzazioneAnagComparator implements Comparator<RateizzazioneDTO> {

	public int compare(RateizzazioneDTO rat1, RateizzazioneDTO rat2) {
		// TODO Auto-generated method stub
		return rat1.getIdAnagrafica()-rat2.getIdAnagrafica();
	}

}
