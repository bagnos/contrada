package it.contrada.pojo;

import it.contrada.dto.ParametriContradaDTO;
import it.contrada.dto.RidDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.preautrid.dto.FlussoPreautInviatoDTO;
import it.contrada.preautrid.dto.Record12DTO;
import it.contrada.preautrid.dto.Record30DTO;
import it.contrada.preautrid.dto.Record40DTO;
import it.contrada.preautrid.dto.Record70DTO;
import it.contrada.preautrid.dto.RecordALDTO;
import it.contrada.preautrid.dto.RecordEFDTO;
import it.contrada.preautrid.dto.RecordXXDTO;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

public class FlussoPreautorizzazioneRid {

	private static Properties prop;
	private FileOutputStream fos = null;
	private PrintStream scriviRid = null;
	private String DATA_CREAZIONE = null;
	private String RICEVENTE = null;
	private String MITTENTE = null;
	private String NOME_SUPPPORTO = null;
	private File file = null;
	private SimpleDateFormat formatddMMyy;
	private String CURRENT_DATE_ddMMyy;
	private String nomeFileSemplice;

	private static Properties getPropFile() throws IOException {
		if (prop == null) {
			prop = new Properties();
			InputStream is = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(Constanti.NOME_FILE_PROPERTIES_FLUSSO);
			prop.load(is);
			is.close();
		}
		return prop;
	}

	public FlussoPreautorizzazioneRid() {
		formatddMMyy = new SimpleDateFormat("ddMMyy");
		CURRENT_DATE_ddMMyy = formatddMMyy.format(GregorianCalendar
				.getInstance().getTime());
	}

	public FlussoPreautInviatoDTO creaFlussoRidRichiestaDelegheSepa(
			List<RidDTO> rids, ParametriContradaDTO parms) throws IOException,
			ContradaExceptionBloccante {

		apriFile();

		int i = 0;

		try {
			scriviRecordTesta(parms);
			for (RidDTO rid : rids) {
				scriviRecord_12(rid, parms, i + 1, true);
				scriviRecord_70(rid, i + 1, true);
				i++;
			}
			scriviRecordCoda(i);
		} catch (Exception e) {
			eliminaFile();
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}

		FlussoPreautInviatoDTO flusso = new FlussoPreautInviatoDTO();
		flusso.setNrDisposizioni(i);
		flusso.setNomeFile(file.getPath());
		flusso.setNomeFileSemplice(nomeFileSemplice);
		flusso.setRids(rids);
		flusso.setDtInvio(GregorianCalendar.getInstance().getTime());

		chiudiFile();
		return flusso;

	}

	public FlussoPreautInviatoDTO creaFlussoRid(List<RidDTO> rids,
			ParametriContradaDTO parms) throws IOException,
			ContradaExceptionBloccante {

		apriFile();

		int i = 0;

		try {
			scriviRecordTesta(parms);
			for (RidDTO rid : rids) {
				scriviRecord_12(rid, parms, i + 1, false);

				scriviRecord_30(rid, i + 1);
				scriviRecord_40(rid, i + 1);

				scriviRecord_70(rid, i + 1, false);
				scriviRecord_XX(rid, parms, i + 1);
				i++;

			}
			scriviRecordCoda(i);
		} catch (Exception e) {
			eliminaFile();
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}

		FlussoPreautInviatoDTO flusso = new FlussoPreautInviatoDTO();
		flusso.setNrDisposizioni(i);
		flusso.setNomeFile(file.getPath());
		flusso.setNomeFileSemplice(nomeFileSemplice);
		flusso.setRids(rids);
		flusso.setDtInvio(GregorianCalendar.getInstance().getTime());

		chiudiFile();
		return flusso;

	}

	private void apriFile() throws IOException, ContradaExceptionBloccante {
		/*
		 * String rootDirectoy = getPropFile().getProperty(
		 * "directoryPreautorizzazioniRidInviati"); File directory = new
		 * File(rootDirectoy.replaceAll("<anno>", Integer .valueOf(
		 * GregorianCalendar.getInstance().get(
		 * GregorianCalendar.YEAR)).toString())); if (!directory.exists()) { if
		 * (!directory.mkdir()) { throw new
		 * ContradaExceptionBloccante(String.format(
		 * "impossibile creare la directory %s", rootDirectoy)); } }
		 */

		String nomeFile = getNomeFilePreautRidNoSuffix();

		// si salva il nome semplice
		nomeFileSemplice = nomeFile;

		// file = new File(directory, nomeFile);
		file = File.createTempFile(nomeFile, Constanti.EXT_FILE_RID);

		fos = new FileOutputStream(file);
		scriviRid = new PrintStream(fos);
	}

	public String getNomeFilePreautRid() {
		String nomeFile = String.format("PreRid%s.crm", CURRENT_DATE_ddMMyy);

		return nomeFile;
	}

	public String getNomeFilePreautRidNoSuffix() {
		String nomeFile = String.format("PreRid%s", CURRENT_DATE_ddMMyy);

		return nomeFile;
	}

	private void scriviRecordTesta(ParametriContradaDTO parms) {
		/*
		 * RecTesta = " AL" & Left(Param.Cod_Sia & "     ", 5) &
		 * Format(Param.Abi, "00000") Wrk_Data = Format(Day(date), "00") &
		 * Format(Month(date), "00") & Format(Right(Year(date), 2), "00")
		 * Mem_Dati_Testa = Wrk_Data Mem_Dati_Testa = Mem_Dati_Testa & "PR" &
		 * date & Time & "******" RecTesta = RecTesta & Mem_Dati_Testa RecTesta
		 * = RecTesta & String(70, " ") RecTesta = RecTesta & Format(Param.Abi,
		 * "00000")
		 */

		RecordALDTO recALDTO = new RecordALDTO();

		SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
		Date date = GregorianCalendar.getInstance().getTime();
		DATA_CREAZIONE = format.format(date);
		recALDTO.setDtCreazioneDisposizione(DATA_CREAZIONE);

		MITTENTE = String.format("%s", parms.getCdSia());
		recALDTO.setMittente(MITTENTE);

		RICEVENTE = String.format("%d", parms.getCdAbi());
		recALDTO.setRicevente(RICEVENTE);

		format = new SimpleDateFormat("dd/MM/yyyyhh:mm:ss");
		NOME_SUPPPORTO = String.format("PR%s", format.format(date));
		recALDTO.setNomeSupporto(NOME_SUPPPORTO);

		scriviRid.println(recALDTO.toString());

	}

	private void scriviRecord_12(RidDTO rid, ParametriContradaDTO parms, int i,
			boolean richiestaSeda) throws IOException {

		/*
		 * RecCorpo = " 12" & Format(NumDisp, "0000000") & Format(Wrk_Data,
		 * "000000") & String(12, " ") RecCorpo = RecCorpo & Format(90211,
		 * "00000") ' causale movimento RecCorpo = RecCorpo & String(10, " ") If
		 * IsNull(rsRid!Paese) Then RecCorpo = RecCorpo & "  " ' Codice Paese
		 * IBAN Else RecCorpo = RecCorpo & Left(UCase(rsRid!Paese) & "  ", 2)
		 * End If If IsNull(rsRid!ChkDigit) Then RecCorpo = RecCorpo & "  " '
		 * Chk Digit IBAN Else If rsRid!ChkDigit = 0 Then RecCorpo = RecCorpo &
		 * "  " Else RecCorpo = RecCorpo & Format(rsRid!ChkDigit, "00") End If
		 * 
		 * End If RecCorpo = RecCorpo & Format(Param.Abi, "00000") RecCorpo =
		 * RecCorpo & String(16, " ") If IsNull(rsRid!Paese) Then RecCorpo =
		 * RecCorpo & " " ' Cin IBAN Else RecCorpo = RecCorpo &
		 * Left(UCase(rsRid!Cin) & "  ", 1) End If RecCorpo = RecCorpo &
		 * Format(rsRid!CodAbi, "00000") RecCorpo = RecCorpo &
		 * Format(rsRid!CodCab, "00000") RecCorpo = RecCorpo &
		 * Left(Trim(UCase(rsRid!NumConto)) & Vuota, 12) RecCorpo = RecCorpo &
		 * Left(Param.Cod_Sia & "     ", 5) & "4" RecCorpo = RecCorpo &
		 * Format(rsRid!Codrid, "0000000000000000") RecCorpo = RecCorpo &
		 * "       " Print #1, RecCorpo
		 */
		it.contrada.preautrid.dto.Record12DTO rec12DTO = new Record12DTO();

		rec12DTO.setNumeroProgressivo(String.format("%d", i));

		rec12DTO.setDataCreazioneDisposizione(CURRENT_DATE_ddMMyy);

		if (richiestaSeda) {
			rec12DTO.setCausale("91211");
		} else {
			rec12DTO.setCausale(getPropFile().getProperty(
					"causaleRichiestaAddebito"));
		}

		if (rid.getPaese() != null) {

			rec12DTO.setCodicePaese(rid.getPaese().trim());
		} else {

			rec12DTO.setCodicePaese("");
		}

		if (rid.getCin() != null) {

			rec12DTO.setCheckDigit(rid.getCin().toString().trim());
		} else {

			rec12DTO.setCheckDigit("0");
		}

		rec12DTO.setAbibancaAllineamento(String.format("%d", parms.getCdAbi()).trim());

		if (rid.getCinAbi() != null) {

			rec12DTO.setCin(rid.getCinAbi().trim());
		} else {

			rec12DTO.setCin("");
		}

		rec12DTO.setAbi(rid.getAbi().toString().trim());

		rec12DTO.setCab(rid.getCab().toString().trim());

		rec12DTO.setConto(rid.getNumeroCC().trim());

		rec12DTO.setCodiceAzienda(parms.getCdSia().trim());

		rec12DTO.setTipoCodiceIndividuale(getPropFile().getProperty(
				"tipoCodiceIndividuale").trim());

		rec12DTO.setCodiceIndividuale(rid.getNrRid().toString().trim());

		rec12DTO.setTipoIncasso("RCUR");
		rec12DTO.setTipoMandato("CORSEDEM");
		
 
		if (richiestaSeda)
		{
			scriviRid.println(rec12DTO.toStringSeda().replaceAll("\t", "")
					.replaceAll("\n", ""));
		}
		else
		{
		scriviRid.println(rec12DTO.toString().replaceAll("\t", "")
				.replaceAll("\n", ""));
		}

	}

	private void scriviRecord_XX(RidDTO rid, ParametriContradaDTO parms, int i)
			throws IOException {
		RecordXXDTO recordXX = new RecordXXDTO();
		recordXX.setNumeroProgressivo(String.format("%d", i));
		recordXX.setIdSeda(parms.getIdSeda());
		recordXX.setNomeCreditore(parms.getTxIntestazione());
		scriviRid.println(recordXX.toString());
	}

	private void scriviRecord_30(RidDTO rid, int i) {

		/*
		 * RecCorpo = " 30" & Format(NumDisp, "0000000") RecCorpo = RecCorpo &
		 * Left(Trim(UCase(rsRid!Intestatario)) & Vuota, 30) RecCorpo = RecCorpo
		 * & Left(Trim(UCase(Wrk_Indirizzo)) & Vuota, 30) RecCorpo = RecCorpo &
		 * String(14, " ") RecCorpo = RecCorpo & Left(Trim(rsRid!CodFisc) &
		 * Vuota, 16) RecCorpo = RecCorpo & Left(Trim(rsRid!CodFisc) & Vuota,
		 * 16) RecCorpo = RecCorpo & "    "
		 * 
		 * Print #1, RecCorpo
		 */
		Record30DTO rec30DTO = new Record30DTO();

		rec30DTO.setNumeroProgressivo(String.format("%d", i));

		rec30DTO.setCdFiscaleIntestatario(rid.getCdFiscale());
		rec30DTO.setCdFiscaleSottoscrittore(rid.getCdFiscale());
		rec30DTO.setSegmento1(rid.getIntestatarioRid());

		rec30DTO.setSegmento2("");
		rec30DTO.setSegmento3("");
		/*
		 * rec30DTO.setSegmento2(rid.getVia() + " " + rid.getNumeroCivico());
		 * rec30DTO.setSegmento3(rid.getCapPost() + " " + rid.getLocalita() +
		 * " " + rid.getProvincia());
		 */

		scriviRid.println(rec30DTO.toString());
	}

	private void scriviRecord_40(RidDTO rid, int i) {

		/*
		 * RecCorpo = " 40" & Format(NumDisp, "0000000") RecCorpo = RecCorpo &
		 * Left(Trim(UCase(Wrk_Indirizzo)) & Vuota, 30) RecCorpo = RecCorpo &
		 * Left(Trim(Wrk_Cap) & Vuota, 5) RecCorpo = RecCorpo &
		 * Left(Trim(UCase(wrk_Localita)) & Vuota, 25) RecCorpo = RecCorpo &
		 * Left(Trim(UCase(rsRid!Intestatario)) & Vuota, 50) Print #1, RecCorpo
		 */

		Record40DTO rec40DTO = new Record40DTO();

		rec40DTO.setNumeroProgressivo(String.format("%d", i));

		rec40DTO.setIndirizzo("");
		rec40DTO.setCap("");

		// rec40DTO.setIndirizzo(rid.getVia() + " " + rid.getNumeroCivico());

		// rec40DTO.setCap(rid.getCapPost());

		rec40DTO.setLocalita("");
		// rec40DTO.setLocalita(rid.getLocalita() + " " + rid.getProvincia());

		rec40DTO.setIntestetarioConto(rid.getIntestatarioRid());

		scriviRid.println(rec40DTO.toString());

	}

	private void scriviRecord_70(RidDTO rid, int i, boolean richiestaSeda)
			throws IOException {

		/*
		 * 
		 * RecCorpo = " 70" & Format(NumDisp, "0000000") RecCorpo = RecCorpo &
		 * Format(rsRid!Codrid, "000000000000000") RecCorpo = RecCorpo & "%" &
		 * "0000" RecCorpo = RecCorpo & "000000000" 'importo massimo rata
		 * RecCorpo = RecCorpo & "000000" 'scadenza prima rata RecCorpo =
		 * RecCorpo & "000000" 'scadenza ultima rata RecCorpo = RecCorpo &
		 * String(44, " ") 'sottocampo descrizione 'modifica simone: dopo la psd
		 * (Luglio 2010) la facoltà di storno è passata ad 8 RecCorpo = RecCorpo
		 * & "8" 'facoltà di storno 'RecCorpo = RecCorpo & "1" 'facoltà di
		 * storno RecCorpo = RecCorpo & "E   " 'Euro RecCorpo = RecCorpo &
		 * String(20, " ") 'Codice autorizzazione Print #1, RecCorpo
		 */

		Record70DTO rec70DTO = new Record70DTO();

		rec70DTO.setCarattereSpeciale("%");

		rec70DTO.setNumeroRate("0");

		rec70DTO.setImportoMassimoRata("0");

		rec70DTO.setScadenzaPrimaRata("0");

		rec70DTO.setScadenzaUltimaRata("0");

		rec70DTO.setFacoltaStorno(getPropFile().getProperty("facoltaStorno"));

		rec70DTO.setCodiceDivisa(getPropFile().getProperty("codiceDivisa"));

		rec70DTO.setNumeroProgressivo(String.format("%d", i));

		rec70DTO.setCodiceRiferimento(String.format("%d", rid.getNrRid()));

		if (richiestaSeda) {
			scriviRid.println(rec70DTO.toStringRichiestaSeda().replaceAll("\t", "")
					.replaceAll("\n", ""));
		} else {
			scriviRid.println(rec70DTO.toString());
		}
	}

	private void scriviRecordCoda(int nrDisp) {
		/*
		 * RecCoda = " EF" & Left(Param.Cod_Sia & "     ", 5) &
		 * Format(Param.Abi, "00000") ' RecCoda = RecCoda & Mem_Dati_Testa
		 * RecCoda = RecCoda & Format(NumDisp, "0000000") ' numero disposizioni
		 * all'interno del flusso RecCoda = RecCoda & String(30, " ") RecCoda =
		 * RecCoda & Format((NumDisp * 4 + 2), "0000000") RecCoda = RecCoda &
		 * String(25, " ") RecCoda = RecCoda & Format(Day(date), "00") &
		 * Format(Month(date), "00") & Format(Right(Year(date), 2), "00") Print
		 * #1, RecCoda
		 */
		RecordEFDTO recEFDTO = new RecordEFDTO();

		recEFDTO.setNumeroDisposizioni(String.format("%07d", nrDisp));

		recEFDTO.setNumeroTotaleRecord(String.format("%07d", nrDisp * 4 + 2));

		recEFDTO.setMittente(MITTENTE);
		recEFDTO.setRicevente(RICEVENTE);
		recEFDTO.setDataCreazione(DATA_CREAZIONE);
		recEFDTO.setNomeSupporto(NOME_SUPPPORTO);

		recEFDTO.setCampoNonDisponibile(CURRENT_DATE_ddMMyy);

		scriviRid.println(recEFDTO.toString());

	}

	private void chiudiFile() throws IOException {
		scriviRid.close();
		fos.close();
	}

	private void eliminaFile() throws IOException {
		scriviRid.close();
		fos.close();
		file.delete();
	}

}
