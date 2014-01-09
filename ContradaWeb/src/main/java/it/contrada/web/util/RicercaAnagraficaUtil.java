package it.contrada.web.util;

import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.businessdelegate.RicercaFamigliaBD;
import it.contrada.businessdelegate.RicercaRidBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.MembroFamigliaDTO;
import it.contrada.dto.MembroRidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;

import java.util.ArrayList;
import java.util.List;

public class RicercaAnagraficaUtil {
	private RicercaAnagraficaUtil() {
	}

	public static List<AnagraficaDTO> getAnagraficaByAutocomplete(
			String anagrafica) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {
		List<AnagraficaDTO> anagrafiche = new ArrayList<AnagraficaDTO>();
		if (anagrafica == null || anagrafica.isEmpty()) {
			return anagrafiche;
		}

		String[] nomi = anagrafica.split(" ");
		if (nomi.length == 1 || nomi[1].isEmpty()) {
			anagrafiche = RicercaAnagraficaBD
					.ricercaAnagraficaParzialePerCognome(nomi[0]);
		} else if (nomi.length==2) {
			anagrafiche = RicercaAnagraficaBD
					.ricercaAnagraficaParzialePerCognomeNome(nomi[0], nomi[1]);
		}
		else
		{
			//consideriamo i primi n-1 elementi come il cognone (es. Del Toro Simone), l'nesimo come il nome
			String cognome="";
			for (int i=0;i<=nomi.length-2;i++)
			{
				cognome+=nomi[i]+" ";
			}
			anagrafiche =RicercaAnagraficaBD
			.ricercaAnagraficaParzialePerCognomeNome(cognome.trim(), nomi[nomi.length-1]);
		}
		return anagrafiche;
	}

	public static List<MembroFamigliaDTO> getFamigliaByAutocomplete(
			String famiglia) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		List<MembroFamigliaDTO> famiglie = new ArrayList<MembroFamigliaDTO>();

		if (famiglia == null || famiglia.isEmpty()) {
			return famiglie;
		}

		String[] nomi = famiglia.split(" ");
		if (nomi.length == 1 || nomi[1].isEmpty()) {
			famiglie = RicercaFamigliaBD.ricercaPerCognomeParziale(famiglia);
		} else {
			famiglie = RicercaFamigliaBD
					.ricercaParzialePerCognomeNome(nomi[0], nomi[1]);
		}
		return famiglie;
	}
	
	public static List<MembroRidDTO> getMembriRidByAutocomplete(
			String nominativo) throws ContradaExceptionBloccante,
			ContradaExceptionNonBloccante {

		List<MembroRidDTO> membri = new ArrayList<MembroRidDTO>();

		if (nominativo == null || nominativo.isEmpty()) {
			return membri;
		}

		String[] nomi = nominativo.split(" ");
		if (nomi.length == 1 || nomi[1].isEmpty()) {
			membri = RicercaRidBD.ricercaPerCognomeParziale(nominativo);
		} else {
			membri = RicercaRidBD.ricercaPerCognomeNomeParziale(nomi[0], nomi[1]);
		}
		return membri;
	}

}
