package it.contrada.pojo;

import it.contrada.dto.ParametriContradaDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.incassorid.dto.DisposizioneIncassoRidRicezioneDTO;
import it.contrada.incassorid.dto.Record10DTO;
import it.contrada.incassorid.dto.Record20DTO;
import it.contrada.incassorid.dto.Record30DTO;
import it.contrada.incassorid.dto.Record50DTO;
import it.contrada.incassorid.dto.Record70DTO;
import it.contrada.incassorid.dto.RecordEFDTO;
import it.contrada.incassorid.dto.RecordIRDTO;
import it.contrada.incassorid.dto.RicezioneFlussoIncassoRidDTO;
import it.contrada.util.DecodificaErrore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RicezioneFlussoIncassiRid {
	public List<RicezioneFlussoIncassoRidDTO> decodificaFlessoEsitiIncassiRid(
			String nomeFile, ParametriContradaDTO parms)
			throws ContradaExceptionBloccante, IOException {

		String record = null;
		String tipoRecord;

		File file = new File(nomeFile);
		

		if (!file.exists()) {
			throw new ContradaExceptionBloccante(DecodificaErrore
					.getError("23"));

		}

		FileReader reader = new FileReader(file);
		BufferedReader bReader = new BufferedReader(reader);

		RicezioneFlussoIncassoRidDTO flussoRid = null;
		DisposizioneIncassoRidRicezioneDTO disposizione = null;
		List<RicezioneFlussoIncassoRidDTO> flussi = new ArrayList<RicezioneFlussoIncassoRidDTO>();
		Record10DTO rec10 = null;

		while ((record = bReader.readLine()) != null) {
			tipoRecord = record.substring(1, 3);

			if (tipoRecord.equals("IR")) {
				flussoRid = new RicezioneFlussoIncassoRidDTO();
				flussoRid.setRecIR(decodificaRecord_IR(record));
				rec10 = null;
			} else if (tipoRecord.equals("10")) {
				disposizione = new DisposizioneIncassoRidRicezioneDTO();
				flussoRid.getDisposizioni().add(disposizione);
				rec10 = decodificaRecord_10(record);
				// verifico che non si tratta del promemoria contabile
				if (!rec10.getCausale().trim().equals("1600")) {
					disposizione.setRec10(decodificaRecord_10(record));
				}

			} else if (tipoRecord.equals("20")) {
				disposizione.setRec20(decodificaRecord_20(record));
			} else if (tipoRecord.equals("30")) {
				disposizione.setRec30(decodificaRecord_30(record));
			} else if (tipoRecord.equals("50")) {
				disposizione.setRec50(decodificaRecord_50(record));
			} else if (tipoRecord.equals("70")) {

				// verifico che non si tratta del promemoria contabile
				if (!rec10.getCausale().trim().equals("1600")) {
					disposizione.setRec70(decodificaRecord_70(record));
					// flussoRid.getDisposizioni().add(disposizione);
				}

			} else if (tipoRecord.equals("EF")) {
				flussoRid.setRecEF(decodificaRecord_EF(record));
				flussi.add(flussoRid);

			} else {
				throw new ContradaExceptionBloccante(DecodificaErrore
						.getError("20"));
			}

		}
		return flussi;
	}

	private RecordIRDTO decodificaRecord_IR(String record) {
		RecordIRDTO recIR = new RecordIRDTO();
		recIR.setMittente(record.substring(3, 8));
		recIR.setRicevente(record.substring(8, 13));
		recIR.setDtCreazioneFlusso(record.substring(13, 19));
		recIR.setNomeSupporto(record.substring(19, 39));
		recIR.setQualificatoreFlusso(record.substring(104, 111));
		recIR.setTipoIncassoRid(record.substring(112, 113));
		recIR.setCodiceDivisa(record.substring(113, 114));
		return recIR;
	}

	private Record10DTO decodificaRecord_10(String record) {
		Record10DTO rec10 = new Record10DTO();
		rec10.setNumeroProgressivo(record.substring(3, 10));
		rec10.setDtScadenzaEffettiva(record.substring(16, 22));
		rec10.setDtScadenzaOriginaria(record.substring(22, 28));
		rec10.setCausale(record.substring(28, 33));
		rec10.setImporto(Integer.valueOf(record.substring(33, 46)).toString());
		rec10.setSegno(record.substring(46, 47));
		rec10.setAbiDomiciliaria(record.substring(47, 52));
		rec10.setCabDomiciliaria(record.substring(52, 57));
		rec10.setAbiAssuntrice(record.substring(69, 74));
		rec10.setCabAssuntrice(record.substring(74, 79));
		rec10.setContoOrdinante(record.substring(79, 91));
		rec10.setCodiceAzienda(record.substring(91, 96));
		rec10.setTipoCodice(record.substring(96, 97));
		rec10.setCodiceClienteDebitore(record.substring(97, 113));
		rec10.setTipoIncassoRid(record.substring(118, 119));
		rec10.setCodiceDivisa(record.substring(119, 120));
		return rec10;
	}

	private Record20DTO decodificaRecord_20(String record) {
		Record20DTO rec20 = new Record20DTO();
		rec20.setNumeroProgressivo(Integer.valueOf(record.substring(3, 10)));
		rec20.setSegmento1(record.substring(10, 40).trim());
		rec20.setSegmento2(record.substring(40, 70).trim());
		rec20.setSegmento3(record.substring(70, 100).trim());
		return rec20;
	}

	private Record30DTO decodificaRecord_30(String record) {
		Record30DTO rec30 = new Record30DTO();
		rec30.setNumeroProgressivo(Integer.valueOf(record.substring(3, 10)));
		rec30.setSegmento1(record.substring(10, 40).trim());
		rec30.setSegmento2(record.substring(40, 70).trim());
		rec30.setSegmento3(record.substring(70, 100).trim());
		return rec30;
	}

	private Record50DTO decodificaRecord_50(String record) {
		Record50DTO rec50 = new Record50DTO();
		rec50.setNumeroProgressivo(Integer.valueOf(record.substring(3, 10)
				.trim()));
		rec50.setSegmento1(record.substring(10, 40).trim());
		rec50.setSegmento2(record.substring(40, 70).trim());

		return rec50;
	}

	private Record70DTO decodificaRecord_70(String record) {
		Record70DTO rec70 = new Record70DTO();
		rec70.setNumeroProgressivo(Integer.valueOf(record.substring(3, 10)));
		if (record.length() >= 25) {
			rec70.setCodiceRiferimento(record.substring(10, 25));
		}
		if (record.length() >= 96) {
			rec70.setFacoltaStornoAddebito(record.substring(95, 96));
		}

		return rec70;
	}

	private RecordEFDTO decodificaRecord_EF(String record) {
		RecordEFDTO recEF = new RecordEFDTO();

		recEF.setMittente(record.substring(3, 8));
		recEF.setRicevente(record.substring(8, 13));
		recEF.setDataCreazioneFlusso(record.substring(13, 19));
		recEF.setNomeSupporto(record.substring(19, 39));
		recEF.setNrDisposizioni(Integer.valueOf(record.substring(45, 52)));
		recEF.setNrImportiNegativi(Integer.valueOf(record.substring(52, 67)));
		recEF.setNrImportiPositivi(Integer.valueOf(record.substring(67, 82)));
		recEF.setNrRecord(Integer.valueOf(record.substring(82, 89)));
		recEF.setTipoIncassoRid(record.substring(112, 113));
		recEF.setCodiceDivisa(record.substring(113, 114));

		return recEF;
	}

}
