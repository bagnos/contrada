package it.contrada.pojo;

import it.contrada.dto.ParametriContradaDTO;
import it.contrada.enumcontrada.TipoMeseIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.incassorid.dto.Record10DTO;
import it.contrada.incassorid.dto.Record16DTO;
import it.contrada.incassorid.dto.Record17DTO;
import it.contrada.incassorid.dto.Record20DTO;
import it.contrada.incassorid.dto.Record30DTO;
import it.contrada.incassorid.dto.Record40DTO;
import it.contrada.incassorid.dto.Record50DTO;
import it.contrada.incassorid.dto.Record70DTO;
import it.contrada.incassorid.dto.RecordEFDTO;
import it.contrada.incassorid.dto.RecordIRDTO;
import it.contrada.util.BaseUtil;
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

public class FlussoRid {

	private static Properties prop;
	private FileOutputStream fos = null;
	private PrintStream scriviRid = null;
	private String DATA_CREAZIONE = null;
	private String RICEVENTE = null;
	private String MITTENTE = null;
	private String NOME_SUPPPORTO = null;
	private File file = null;
	private String nomeFileSemplice = null;

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

	public FlussoRid() {

	}

	public FlussoIncassoRidDTO creaFlussoRid(int mese, int anno,
			List<IncassoRidDTO> rids, ParametriContradaDTO parms)
			throws IOException, ContradaExceptionBloccante {

		apriFile(anno, mese);

		int i = 1;
		int totIncasso = 0;
		try {
			scriviRecordTesta(parms);
			for (IncassoRidDTO rid : rids) {
				scriviRecord_10(rid, parms, i);
				scriviRecord_16(parms, i);
				scriviRecord_17(rid, parms, i);
				scriviRecord_20(parms, i);
				scriviRecord_30(rid, i);
				// scriviRecord_40(rid, i);
				scriviRecord_50(rid, i, parms);
				scriviRecord_70(rid, i);
				i++;
				totIncasso += rid.getImRichiesto();
			}
			scriviRecordCoda(parms, rids.size(), totIncasso);
		} catch (Exception e) {
			e.printStackTrace();
			eliminaFile();
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		}

		FlussoIncassoRidDTO flusso = new FlussoIncassoRidDTO();
		flusso.setImFlusso(totIncasso);
		flusso.setNrIncassi(rids.size());
		flusso.setIncassi(rids);
		flusso.setNomeFile(file.getAbsolutePath());
		flusso.setNomeFileSemplice(nomeFileSemplice);

		chiudiFile();
		return flusso;

	}

	private void apriFile(int anno, int mese) throws IOException,
			ContradaExceptionBloccante {

		/*
		 * String rootDirectoy =
		 * getPropFile().getProperty("directoryIncassiRid"); File directory =
		 * new File(rootDirectoy); if (!directory.exists()) { if
		 * (!directory.mkdir()) { throw new
		 * ContradaExceptionBloccante(String.format(
		 * "impossibile creare la directory %s", rootDirectoy)); } }
		 */

		// String nomeFile = getNomeFileRidIncasso(anno, mese);
		String nomeFile = getNomeFileRidIncassoNoSuffix(anno, mese);

		// si salva il nome semplice
		nomeFileSemplice = nomeFile;

		// file = new File(directory, nomeFile);
		file = File.createTempFile(nomeFile, Constanti.EXT_FILE_RID);

		fos = new FileOutputStream(file);
		scriviRid = new PrintStream(fos);
	}

	public static String getNomeFileRidIncasso(int anno, int mese) {
		String nomeFile = String.format("Rid%d%2d.crm", anno, mese).replace(
				' ', '0');

		return nomeFile;
	}

	public static String getNomeFileRidIncassoNoSuffix(int anno, int mese) {
		String nomeFile = String.format("Rid%d%2d", anno, mese).replace(' ',
				'0');

		return nomeFile;
	}

	public static String getNomeFileConDirectoryRidIncasso(int anno, int mese)
			throws IOException {
		String rootDirectoy = getPropFile().getProperty("directoryIncassiRid");
		String nomeFile = getNomeFileRidIncasso(anno, mese);

		return rootDirectoy + File.separatorChar + nomeFile;
	}

	private void scriviRecordTesta(ParametriContradaDTO parms)
			throws IOException {
		/*
		 * RecTesta = " IR" & Param.Cod_Sia & Format(Param.Abi, "00000")
		 * Mem_Dati_Testa = Format(Day(date), "00") & Format(Month(date), "00")
		 * & Format(Right(Year(date), 2), "00") Mem_Data =
		 * Format(Right(Year(date), 4), "0000") & Format(Month(date), "00") &
		 * Format(Day(date), "00") Mem_Dati_Testa = Mem_Dati_Testa & "RID" &
		 * Mem_Data & "-" & Time & "******" RecTesta = RecTesta & Mem_Dati_Testa
		 * RecTesta = RecTesta & String(67, " ") RecTesta = RecTesta & " " '
		 * tipo incasso rid (commerciale) RecTesta = RecTesta & "E " ' Divisa
		 * EUR RecTesta = RecTesta & Format(Param.Abi, "00000")
		 */

		RecordIRDTO recIRDTO = new RecordIRDTO();

		SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
		Date date = GregorianCalendar.getInstance().getTime();
		DATA_CREAZIONE = format.format(date);
		recIRDTO.setDtCreazioneFlusso(DATA_CREAZIONE);

		MITTENTE = String.format("%s", parms.getCdSia());
		recIRDTO.setMittente(MITTENTE);

		RICEVENTE = String.format("%05d", parms.getCdAbi());
		recIRDTO.setRicevente(RICEVENTE);

		format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		NOME_SUPPPORTO = String.format("I%s", format.format(date));
		recIRDTO.setNomeSupporto(NOME_SUPPPORTO);

		recIRDTO.setTipoIncassoRid(" ");

		recIRDTO.setCodiceDivisa(getPropFile().getProperty("codiceDivisa"));

		scriviRid.println(recIRDTO.toString());

	}

	public void scriviRecord_10(IncassoRidDTO rid, ParametriContradaDTO parms,
			int i) throws IOException {

		SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
		/*
		 * Dim Wrk_Desc As String Wrk_Desc = "" RecFlusso = " 10" ' record 10
		 * RecFlusso = RecFlusso & Format(NumDisp, "0000000") ' progressivo rid
		 * all'interno del flusso RecFlusso = RecFlusso & "000000" ' data
		 * decorrenza garanzia RecFlusso = RecFlusso & "000000" ' data limite
		 * pagamento RecFlusso = RecFlusso & Left(txtDtVal.Text, 4) &
		 * Right(txtDtVal.Text, 2) ' data valuta RecFlusso = RecFlusso & "50000"
		 * ' causale = rid RecFlusso = RecFlusso & Format(ImportoRid,
		 * "0000000000000") RecFlusso = RecFlusso & "-" ' segnosempre "-"
		 * RecFlusso = RecFlusso & Format(Param.Abi, "00000") ' banca assuntrice
		 * RecFlusso = RecFlusso & Format(Param.Cab, "00000") ' sportello
		 * assuntrice RecFlusso = RecFlusso & Format(UCase(Param.ContoCC),
		 * "000000000000") 'conto ordinante RecFlusso = RecFlusso &
		 * Format(rsRid!CodAbi, "00000") 'codice ABI RecFlusso = RecFlusso &
		 * Format(rsRid!CodCab, "00000") 'codice CAB RecFlusso = RecFlusso &
		 * Right("0000000000000" & Trim(UCase(rsRid!NumConto)), 12) ' conto
		 * corrente debitore RecFlusso = RecFlusso & Param.Cod_Sia 'codice SIA
		 * RecFlusso = RecFlusso & "4" 'tipo codice 4 = codice cliente RecFlusso
		 * = RecFlusso & Format(rsRid!CodRid, "0000000000000000") RecFlusso =
		 * RecFlusso & "     " RecFlusso = RecFlusso & " " ' tipo rid =
		 * commerciale RecFlusso = RecFlusso & "E" ' divisa Print #1, RecFlusso
		 */

		Record10DTO rec10DTO = new Record10DTO();

		rec10DTO.setNumeroProgressivo(String.format("%s", i));

		rec10DTO.setDtDecorrenzaGaranzia("000000");

		rec10DTO.setDtLimitePagamento("000000");

		rec10DTO.setDtScadenza(format.format(rid.getDtValuta()));

		rec10DTO.setCausale(getPropFile().getProperty("casaleIncasso"));

		rec10DTO.setImporto(rid.getImRichiesto().toString());

		rec10DTO.setSegno(getPropFile().getProperty("segno"));

		rec10DTO.setAbiDomiciliaria(String.format("%s", parms.getCdAbi()));

		rec10DTO.setCabDomiciliaria(String.format("%s", parms.getCdCab()));

		rec10DTO.setContoOrdinante(String.format("%s", parms.getNrConto()));

		rec10DTO.setAbiAssuntrice(String.format("%s", rid.getCdAbi()));

		rec10DTO.setCabAssuntrice(String.format("%s", rid.getCdCab()));

		rec10DTO.setContoDebitore(String.format("%s", rid.getNrConto()));

		rec10DTO.setCodiceAzienda(parms.getCdSia());

		rec10DTO.setTipoCodice(getPropFile().getProperty(
				"tipoCodiceIndividuale"));

		rec10DTO.setCodiceClienteDebitore(String.format("%s", rid.getIdRid()));

		rec10DTO.setFlagDebitore(" ");

		rec10DTO.setTipoIncassoRid(" ");

		rec10DTO.setCodiceDivisa(getPropFile().getProperty("codiceDivisa"));

		scriviRid.println(rec10DTO.toString());
		;
		/*
		 * RecFlusso = " 50" ' record 50 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso Wrk_Desc =
		 * Left((Trim(Param.Intestazione) & String(28, " ")), 28) & "- Anno " &
		 * txtAnno.Text & " " & Format(Wrk_Mese, "00") ' cmbMese.ListIndex + 1,
		 * "00") Wrk_Desc = Wrk_Desc & " quota protettorato    " & cmbMese.Text
		 * RecFlusso = RecFlusso & Left(Trim(Wrk_Desc) & String(90, " "), 90)
		 * RecFlusso = RecFlusso & String(20, " ") Print #1, RecFlusso
		 */

	}

	public void scriviRecord_17(IncassoRidDTO rid, ParametriContradaDTO parms,
			int i) throws IOException {

		Record17DTO rec17DTO = new Record17DTO();

		rec17DTO.setNumeroProgressivo(String.format("%s", i));
		rec17DTO.setCdIban(rid.getCdIban().trim().toUpperCase());
		rec17DTO.setDtSottoscrizione("311213");
		
		if (rid.getNrMovimenti() > 1) {
			rec17DTO.setTipoSequenza("RCUR");
		} else {
			// solo un movimento presente in Incasso_Rid con 50010 (quello
			// appena inserito) quindi rappresenta prima disposizione per IBAN
			rec17DTO.setTipoSequenza("FRST");
		}

		scriviRid.println(rec17DTO.toString());
		;
		/*
		 * RecFlusso = " 50" ' record 50 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso Wrk_Desc =
		 * Left((Trim(Param.Intestazione) & String(28, " ")), 28) & "- Anno " &
		 * txtAnno.Text & " " & Format(Wrk_Mese, "00") ' cmbMese.ListIndex + 1,
		 * "00") Wrk_Desc = Wrk_Desc & " quota protettorato    " & cmbMese.Text
		 * RecFlusso = RecFlusso & Left(Trim(Wrk_Desc) & String(90, " "), 90)
		 * RecFlusso = RecFlusso & String(20, " ") Print #1, RecFlusso
		 */

	}

	public void scriviRecord_16(ParametriContradaDTO parms, int i)
			throws IOException {

		Record16DTO rec16DTO = new Record16DTO();

		rec16DTO.setNumeroProgressivo(String.format("%s", i));
		rec16DTO.setCdIbanOrdinante(BaseUtil.formatIban(parms.getCdPaese(),
				parms.getCheckDigit(), parms.getNrCin(),
				Integer.toString(parms.getCdAbi()),
				Integer.toString(parms.getCdCab()), parms.getNrConto()));
		rec16DTO.setIdCreditore(parms.getIdSeda());

		scriviRid.println(rec16DTO.toString());
		;
		/*
		 * RecFlusso = " 50" ' record 50 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso Wrk_Desc =
		 * Left((Trim(Param.Intestazione) & String(28, " ")), 28) & "- Anno " &
		 * txtAnno.Text & " " & Format(Wrk_Mese, "00") ' cmbMese.ListIndex + 1,
		 * "00") Wrk_Desc = Wrk_Desc & " quota protettorato    " & cmbMese.Text
		 * RecFlusso = RecFlusso & Left(Trim(Wrk_Desc) & String(90, " "), 90)
		 * RecFlusso = RecFlusso & String(20, " ") Print #1, RecFlusso
		 */

	}

	private void scriviRecord_20(ParametriContradaDTO parms, int i) {

		Record20DTO rec20DTO = new Record20DTO();

		rec20DTO.setNumeroProgressivo(i);

		rec20DTO.setSegmento1(parms.getTxIntestazione());

		rec20DTO.setSegmento2(parms.getTxIndirizzo());

		rec20DTO.setSegmento3(parms.getCdCap() + " " + parms.getTxLocalita()
				+ " " + parms.getTxProvincia());

		scriviRid.println(rec20DTO.toString());

		/*
		 * RecFlusso = " 20" ' record 20 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso RecFlusso =
		 * RecFlusso & Left(Trim(UCase(Param.Intestazione)) & String(30, " "),
		 * 30) RecFlusso = RecFlusso & Left(Param.Indirizzo & String(30, " "),
		 * 30) RecFlusso = RecFlusso & Left(Param.Cap & String(30, " "), 30)
		 * RecFlusso = RecFlusso & "                    " Print #1, RecFlusso
		 */

	}

	private void scriviRecord_30(IncassoRidDTO rid, int i) {

		/*
		 * RecFlusso = " 30" ' record 30 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso RecFlusso =
		 * RecFlusso & Left(Trim(UCase(rsRid!Intestatario)) & String(90, " "),
		 * 90) 'intestatario cliente RecFlusso = RecFlusso &
		 * Left(UCase(Trim(rsRid!CodFisc)) & String(16, " "), 16) 'codice
		 * fiscale cliente RecFlusso = RecFlusso & "    " Print #1, RecFlusso
		 */

		Record30DTO rec30DTO = new Record30DTO();

		rec30DTO.setNumeroProgressivo(i);

		rec30DTO.setSegmento1(String.format("%s %s", rid.getTxIntestatario(),
				rid.getCdFisc()));

		// rec30DTO.setSegmento2(rid.getCdFisc());

		scriviRid.println(rec30DTO.toString());
	}

	private void scriviRecord_40(IncassoRidDTO rid, int i) {

		/*
		 * RecFlusso = " 40" ' record 40 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso RecFlusso =
		 * RecFlusso & Left(Trim(Desc_Via) & String(30, " "), 30) 'indirizzo
		 * intestatario cliente RecFlusso = RecFlusso & Format(rsRid!CodCap,
		 * "00000") RecFlusso = RecFlusso & Left(UCase(Desc_Cap) & String(25,
		 * " "), 25) 'decodifica cap RecFlusso = RecFlusso & String(50, " ")
		 * Print #1, RecFlusso
		 */

		Record40DTO rec40DTO = new Record40DTO();

		rec40DTO.setNumeroProgressivo(i);

		if (rid.getTxIndirizzo() != null) {
			rec40DTO.setIndirizzo(rid.getTxIndirizzo().replaceAll("\t", "")
					.replaceAll("\n", ""));
		} else {
			rec40DTO.setIndirizzo("");
		}

		if (rid.getCdCap() != null) {
			rec40DTO.setCap(String.format("%s", rid.getCdCap()));
		} else {
			rec40DTO.setCap("");
		}

		if (rid.getDsComune() != null && rid.getCdProv() != null) {
			rec40DTO.setComuneProv(rid.getDsComune() + " " + rid.getCdProv());
		} else {
			rec40DTO.setComuneProv("");
		}

		scriviRid.println(rec40DTO.toString());

	}

	private void scriviRecord_50(IncassoRidDTO rid, int i,
			ParametriContradaDTO parms) {

		/*
		 * * RecFlusso = " 50" ' record 50 RecFlusso = RecFlusso &
		 * Format(NumDisp, "0000000") ' progressivo rid all'interno del flusso
		 * Wrk_Desc = Left((Trim(Param.Intestazione) & String(28, " ")), 28) &
		 * "- Anno " & txtAnno.Text & " " & Format(Wrk_Mese, "00") '
		 * cmbMese.ListIndex + 1, "00") Wrk_Desc = Wrk_Desc &
		 * " quota protettorato    " & cmbMese.Text RecFlusso = RecFlusso &
		 * Left(Trim(Wrk_Desc) & String(90, " "), 90) RecFlusso = RecFlusso &
		 * String(20, " ") Print #1, RecFlusso
		 */

		String DS_MESE;

		Record50DTO rec50DTO = new Record50DTO();

		rec50DTO.setNumeroProgressivo(i);

		DS_MESE = TipoMeseIncasso.lookUpMeseByOrdinal(rid.getNrMese())
				.toString();

		rec50DTO.setSegmento1(String.format(
				"%s - %s",
				parms.getTxIntestazione(),
				rid.getDsCausaleRid().replaceAll("<mese>", DS_MESE)
						.replaceAll("<anno>", rid.getNrAnno().toString())));

		scriviRid.println(rec50DTO.toString());

	}

	private void scriviRecord_70(IncassoRidDTO ir, int i) {

		/*
		 * RecFlusso = " 70" ' record 70 RecFlusso = RecFlusso & Format(NumDisp,
		 * "0000000") ' progressivo rid all'interno del flusso RecFlusso =
		 * RecFlusso & Param.Cod_Sia RecFlusso = RecFlusso & String(95, " ")
		 * Print #1, RecFlusso
		 */

		Record70DTO rec70DTO = new Record70DTO();

		rec70DTO.setNumeroProgressivo(i);

		rec70DTO.setCodiceRiferimento(ir.getIdFlussoAddebito().toString());

		scriviRid.println(rec70DTO.toString());

	}

	private void scriviRecordCoda(ParametriContradaDTO parms, int nrDisp,
			int totIncasso) throws IOException {
		/*
		 * RecCoda = " EF" & Param.Cod_Sia & Format(Param.Abi, "00000") '
		 * B157801030" RecCoda = RecCoda & Mem_Dati_Testa RecCoda = RecCoda &
		 * Format(NumDisp, "0000000") ' numero disposizioni all'interno del
		 * flusso RecCoda = RecCoda & Format(TotDisp, "000000000000000") RecCoda
		 * = RecCoda & "000000000000000" RecCoda = RecCoda & Format((NumDisp * 6
		 * + 2), "0000000") RecCoda = RecCoda & String(24, " ") RecCoda =
		 * RecCoda & "E" RecCoda = RecCoda & "      " Print #1, RecCoda
		 */
		RecordEFDTO recEF = new RecordEFDTO();

		recEF.setNrImportiNegativi(totIncasso);

		recEF.setNrImportiPositivi(0);

		recEF.setNrDisposizioni(nrDisp);

		recEF.setNrRecord(nrDisp * 6 + 2);

		recEF.setTipoIncassoRid(" ");

		recEF.setCodiceDivisa(getPropFile().getProperty("codiceDivisa"));

		recEF.setMittente(MITTENTE);

		recEF.setRicevente(RICEVENTE);

		recEF.setDataCreazioneFlusso(DATA_CREAZIONE);

		recEF.setNomeSupporto(NOME_SUPPPORTO);

		scriviRid.println(recEF.toString());

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
