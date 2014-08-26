package it.contrada.pojo;

import it.contrada.dto.ParametriContradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.preautrid.dto.DisposizionePreautRicezioneDTO;
import it.contrada.preautrid.dto.Record12DTO;
import it.contrada.preautrid.dto.Record30DTO;
import it.contrada.preautrid.dto.Record40DTO;
import it.contrada.preautrid.dto.Record45DTO;
import it.contrada.preautrid.dto.Record50DTO;
import it.contrada.preautrid.dto.Record70DTO;
import it.contrada.preautrid.dto.RecordALDTO;
import it.contrada.preautrid.dto.RecordEFDTO;
import it.contrada.preautrid.dto.RicezioneFlussoPreautorizzazioneDTO;
import it.contrada.util.DecodificaErrore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  S506273
 */
public class RicezioneFlussoPreautorizzazioneRid {

	SimpleDateFormat formatddMMyy;
	/**
	 * @uml.property  name="ricPreautDTO"
	 * @uml.associationEnd  
	 */
	RicezioneFlussoPreautorizzazioneDTO ricPreautDTO;

	public RicezioneFlussoPreautorizzazioneRid() {
		formatddMMyy = new SimpleDateFormat("ddMMyy");
		ricPreautDTO = new RicezioneFlussoPreautorizzazioneDTO();
	}

	public List<RicezioneFlussoPreautorizzazioneDTO> decodificaFlessoEsitiPreautorizzazioneRid(
			String nomeFile, ParametriContradaDTO parms)
			throws ContradaExceptionBloccante, IOException, ParseException {
		File file = new File(nomeFile);

		if (!file.exists()) {
			throw new ContradaExceptionBloccante(DecodificaErrore
					.getError("18"));

		}

		FileReader reader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(reader);
		String record = null;
		String tipoRecord;
		DisposizionePreautRicezioneDTO disposizione = null;
		int disp = 0;
		int nrRecord = 0;
		RicezioneFlussoPreautorizzazioneDTO flussoPreat=null;
		List<RicezioneFlussoPreautorizzazioneDTO> flussi = new ArrayList<RicezioneFlussoPreautorizzazioneDTO>();

		while ((record = bReader.readLine()) != null) {
			tipoRecord = record.substring(1, 3);
			nrRecord++;
			if (tipoRecord.equals("AL")) {
				flussoPreat = new RicezioneFlussoPreautorizzazioneDTO();

				flussoPreat.setRecordAL(decodificaRecord_AL(record, parms));
			} else if (tipoRecord.equals("12")) {
				disposizione = new DisposizionePreautRicezioneDTO();
				
				flussoPreat.getDisposizioni().add(disposizione);
				
				disposizione.setRecords12(decodificaRecord_12(record, parms));
			} else if (tipoRecord.equals("30")) {
				disposizione.setRecords30(decodificaRecord_30(record));
			} else if (tipoRecord.equals("40")) {
				disposizione.setRecords40(decodificaRecord_40(record));
			} else if (tipoRecord.equals("45")) {
				disposizione.setRecords45(decodificaRecord_45(record));
			} else if (tipoRecord.equals("50")) {
				disposizione.setRecords50(decodificaRecord_50(record));
			} else if (tipoRecord.equals("70")) {
				disposizione.setRecords70(decodificaRecord_70(record));
			} else if (tipoRecord.equals("EF")) {
				flussoPreat.setRecordEF(decodificaRecord_EF(record, disp,
						nrRecord));
				flussi.add(flussoPreat);
				
			}
			else if (tipoRecord.equals("XX"))
			{
				//non si fa niente
			}
			
			else {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("20")+ " " + tipoRecord);
			}
		}

		return flussi;
	}

	private RecordALDTO decodificaRecord_AL(String record,
			ParametriContradaDTO parms) throws ContradaExceptionBloccante {

		RecordALDTO recAL = new RecordALDTO();
		recAL.setMittente(record.substring(3, 8));
		recAL.setRicevente(record.substring(8, 13));
		recAL.setNomeSupporto(record.substring(19, 40));
		recAL.setDtCreazioneDisposizione(record.substring(13, 19));
		if (!recAL.getRicevente().equals(parms.getCdSia())) {
			throw new ContradaExceptionBloccante(DecodificaErrore
					.getError("19"));
		}
		return recAL;
	}

	private Record12DTO decodificaRecord_12(String record,
			ParametriContradaDTO parms) {
		Record12DTO rec12 = new Record12DTO();
		rec12.setNumeroProgressivo(record.substring(3, 10));
		rec12.setDataCreazioneDisposizione(record.substring(10, 16));
		rec12.setDataCreazioneFlusso(record.substring(16, 22));
		rec12.setCausale(record.substring(22, 33));
		rec12.setCodicePaese(record.substring(43, 45));
		rec12.setCheckDigit(record.substring(45, 47));
		rec12.setAbibancaAllineamento(record.substring(47, 52));
		rec12.setCin(record.substring(68, 69));
		rec12.setAbi(record.substring(69, 74));
		rec12.setCab(record.substring(74, 79));
		rec12.setConto(record.substring(79, 91));
		rec12.setCodiceAzienda(record.substring(91, 96));
		rec12.setTipoCodiceIndividuale(record.substring(96, 97));
		rec12.setCodiceIndividuale(Integer.valueOf(record.substring(97, 113).trim()).toString());
		return rec12;
	}

	private Record30DTO decodificaRecord_30(String record) {
		Record30DTO rec30 = new Record30DTO();
		rec30.setNumeroProgressivo(record.substring(3, 10));
		rec30.setSegmento1(record.substring(10, 40));
		rec30.setSegmento2(record.substring(40, 70));
		rec30.setSegmento3(record.substring(70, 84));
		rec30.setCdFiscaleSottoscrittore(record.substring(84, 100));
		rec30.setCdFiscaleIntestatario(record.substring(100, 116));
		return rec30;
	}

	private Record40DTO decodificaRecord_40(String record) {
		Record40DTO rec40 = new Record40DTO();
		rec40.setNumeroProgressivo(record.substring(3, 10));
		rec40.setIndirizzo(record.substring(10, 40));
		rec40.setCap(record.substring(40, 45));
		rec40.setLocalita(record.substring(45, 70));
		rec40.setRagioneSociale(record.substring(70, 120));
		return rec40;
	}

	private Record45DTO decodificaRecord_45(String record) {
		Record45DTO rec45 = new Record45DTO();
		rec45.setNumeroProgressivo(record.substring(3, 10));
		rec45.setCab(record.substring(10, 15));
		rec45.setContoAddebito(record.substring(15, 27));
		rec45.setCodiceIndividuale(record.substring(27, 43));
		rec45.setCodiceAzienda(record.substring(43, 48));
		rec45.setTipoCodiceIndividuale(record.substring(48, 49));
		rec45.setAbi(record.substring(49, 54));
		rec45.setCodicePaese(record.substring(54, 56));
		rec45.setCheckDigit(record.substring(56, 58));
		rec45.setCin(record.substring(58, 59));

		return rec45;
	}

	private Record50DTO decodificaRecord_50(String record) {
		Record50DTO rec50 = new Record50DTO();
		rec50.setNumeroProgressivo(record.substring(3, 10));
		rec50.setRiferimentiOriginari(record.substring(10, 101));
		return rec50;

	}

	private Record70DTO decodificaRecord_70(String record) {
		Record70DTO rec70 = new Record70DTO();
		rec70.setNumeroProgressivo(record.substring(3, 10));
		rec70.setCodiceRiferimento(record.substring(10, 25));
		rec70.setCarattereSpeciale(record.substring(25, 26));
		rec70.setNumeroRate(record.substring(26, 30));
		rec70.setImportoMassimoRata(record.substring(30, 39));
		rec70.setScadenzaPrimaRata(record.substring(39, 45));
		rec70.setScadenzaUltimaRata(record.substring(45, 51));
		rec70.setDescrizione(record.substring(51, 95));
		rec70.setFacoltaStorno(record.substring(95, 96));
		rec70.setCodiceDivisa(record.substring(96, 97));
		return rec70;
	}

	private RecordEFDTO decodificaRecord_EF(String record, Integer disp,
			Integer totRecord) {
		RecordEFDTO recEF = new RecordEFDTO();
		recEF.setMittente(record.substring(3, 8));
		recEF.setRicevente(record.substring(8, 13));
		recEF.setDataCreazione(record.substring(13, 19));
		recEF.setNomeSupporto(record.substring(19, 39));
		recEF.setNumeroDisposizioni(disp.toString());
		recEF.setNumeroTotaleRecord(totRecord.toString());

		return recEF;

	}

}
