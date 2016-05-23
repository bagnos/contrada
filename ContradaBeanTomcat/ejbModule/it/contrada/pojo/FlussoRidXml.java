package it.contrada.pojo;

import it.contrada.dto.ParametriContradaDTO;
import it.contrada.enumcontrada.TipoMeseIncasso;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.incassorid.dto.FlussoIncassoRidDTO;
import it.contrada.incassorid.dto.IncassoRidDTO;
import it.contrada.util.BaseUtil;
import it.contrada.util.Constanti;
import it.contrada.util.DecodificaErrore;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class FlussoRidXml {

	private static Properties prop;
	private FileOutputStream fos = null;
	private PrintStream scriviRid = null;
	private File file = null;
	private String nomeFileSemplice = null;
	private StringBuilder sb = new StringBuilder();

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

	public FlussoRidXml() {

	}

	public FlussoIncassoRidDTO creaFlussoRidXML(int mese, int anno,
			List<IncassoRidDTO> rids, ParametriContradaDTO parms)
			throws IOException, ContradaExceptionBloccante {

		int i = 1;
		int totIncasso = 0;
		FlussoIncassoRidDTO flusso = null;
		try {
			sb.append("<flusso>\n");
			scriviHeader(parms, rids);
			scriviDistinta(parms, rids);
			sb.append("<elenco>\n");
			for (IncassoRidDTO rid : rids) {
				scriviDisposizione(rid, parms, i);
				i++;
				totIncasso += rid.getImRichiesto();
			}
			sb.append("</elenco>\n");
			sb.append("</flusso>\n");
			// apriFile(anno, mese, Constanti.EXT_FILE_RID);

			generaFileXML(mese, anno, Constanti.EXT_FILE_XML);

			flusso = new FlussoIncassoRidDTO();
			flusso.setImFlusso(totIncasso);
			flusso.setNrIncassi(rids.size());
			flusso.setIncassi(rids);
			flusso.setNomeFile(file.getAbsolutePath());
			flusso.setNomeFileSemplice(nomeFileSemplice);
		} catch (Exception e) {
			throw new ContradaExceptionBloccante(DecodificaErrore.get5018(), e);
		} finally {
			// chiudiFile();
		}
		return flusso;

	}

	private void generaFileXML(int nrMese, int nrAnno, String suffix)
			throws IOException, URISyntaxException, TransformerException,
			ContradaExceptionBloccante {

		File xslFile = new File(FlussoRidXml.class.getClassLoader()
				.getResource("it/contrada/web/report/invioSDD.xsl").toURI());
		File xmlTemp = File.createTempFile("xmlTemp", ".xml");
		FileWriter fstream = new FileWriter(xmlTemp);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(xmlTemp), "UTF8"));
		out.write(sb.toString());
		out.close();
		fstream.close();

		scriviFileOutputXML(nrAnno, nrMese, suffix, xslFile, xmlTemp);

	}

	private void scriviFileOutputXML(int anno, int mese, String suffix,
			File xslFile, File xmlFile) throws IOException,
			ContradaExceptionBloccante, TransformerException {

		// String nomeFile = getNomeFileRidIncasso(anno, mese);
		String nomeFile = getNomeFileRidIncassoNoSuffix(anno, mese);

		// si salva il nome semplice
		nomeFileSemplice = nomeFile;

		// file = new File(directory, nomeFile);
		file = File.createTempFile(nomeFile, suffix);

		fos = new FileOutputStream(file);
		scriviRid = new PrintStream(fos);

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Source xslSource = new javax.xml.transform.stream.StreamSource(xslFile);
		Source xmlSource = new javax.xml.transform.stream.StreamSource(xmlFile);
		javax.xml.transform.stream.StreamResult result = new StreamResult(
				scriviRid);
		Transformer transformer;
		transformer = tFactory.newTransformer(xslSource);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.transform(xmlSource, result);
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

	private void scriviHeader(ParametriContradaDTO parms,
			List<IncassoRidDTO> rids) throws IOException {

		Date date = GregorianCalendar.getInstance().getTime();
		String formatDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
				.format(date);
		String isoDATE = formatDate.substring(0, 22) + ":"
				+ formatDate.substring(22);

		String msgId = "DistXml-" + isoDATE;

		int somma = 0;
		for (IncassoRidDTO inc : rids) {
			somma += inc.getImRichiesto();
		}
		BigDecimal sommaBD = new BigDecimal(somma).divide(new BigDecimal(100));

		sb.append("<msgId>" + msgId + "</msgId>\n");
		sb.append("<dtCreazione>" + isoDATE + "</dtCreazione>\n");
		sb.append("<nbOfTxs>" + rids.size() + "</nbOfTxs>\n");
		sb.append("<ctrlSum>" + sommaBD.toString() + "</ctrlSum>\n");
		sb.append("<nomeAzienda>" + parms.getTxIntestazione()
				+ "</nomeAzienda>\n");
		sb.append("<codiceUnicoAzienda>" + "SIA"+parms.getCdSia()
				+ "</codiceUnicoAzienda>\n");

	}

	private void scriviDistinta(ParametriContradaDTO parms,
			List<IncassoRidDTO> rids) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		sb.append("<idSottodistinta>Sottodistinta1</idSottodistinta>\n");
		String isoDate = format.format(rids.get(0).getDtValuta());
		sb.append("<dtScadenza>" + isoDate + "</dtScadenza>\n");
		sb.append("<nomeAzienda>" + parms.getTxIntestazione()
				+ "</nomeAzienda>\n");
		String iban = null;
		if (parms.getCdIban() != null) {
			iban = parms.getCdIban();
		} else {
			iban = BaseUtil.formatIban(parms.getCdPaese(),
					parms.getCheckDigit(), parms.getNrCin(),
					Integer.toString(parms.getCdAbi()),
					Integer.toString(parms.getCdCab()), parms.getNrConto());
		}
		sb.append("<ibanAccredito>" + iban + "</ibanAccredito>\n");
		sb.append("<abiBancaAccredito>" + String.format("%5s",Integer.toString(parms.getCdAbi())).replace(" ", "0") + "</abiBancaAccredito>\n");
		
		sb.append("<identificativoCreditore>" + parms.getIdSeda()
				+ "</identificativoCreditore>\n");
	}

	public void scriviDisposizione(IncassoRidDTO rid,
			ParametriContradaDTO parms, int i) throws IOException {

		sb.append("<disposizione>\n");
		sb.append("<progressivoDisposizione>" + i
				+ "</progressivoDisposizione>\n");
		sb.append("<idRateizzazione>" + rid.getIdFlussoAddebito()
				+ "</idRateizzazione>\n");
		BigDecimal importo = new BigDecimal(rid.getImRichiesto())
				.divide(new BigDecimal(100));
		sb.append("<importoDispozione>" + importo.toString()
				+ "</importoDispozione>\n");
		sb.append("<idRid>" + rid.getIdRid() + "</idRid>\n");
		sb.append("<intestatarioConto>" + rid.getTxIntestatario()
				+ "</intestatarioConto>\n");
		sb.append("<iban>" + rid.getCdIban().trim().toUpperCase() + "</iban>\n");

		String dsMese = TipoMeseIncasso.lookUpMeseByOrdinal(rid.getNrMese())
				.toString();
		String causale = (String.format(
				"%s - %s",
				parms.getTxIntestazione(),
				rid.getDsCausaleRid().replaceAll("<mese>", dsMese)
						.replaceAll("<anno>", rid.getNrAnno().toString())));
		sb.append("<causaleDisposizione>" + causale
				+ "</causaleDisposizione>\n");
		sb.append("</disposizione>\n");
	}

	private void chiudiFile() {
		try {

			if (fos != null)
				fos.close();
			if (file != null)
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
