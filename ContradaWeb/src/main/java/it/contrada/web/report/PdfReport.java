package it.contrada.web.report;

import it.contrada.backingbeans.PrintFile;
import it.contrada.backingbeans.pagecodes.ErrorPage;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.TesseraDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.util.BaseUtil;
import it.contrada.util.Constanti;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode39;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PdfReport {

	private List<AnagraficaDTO> items;
	private final int NUM_COLS_PAGE = 3;
	private final float SIZE = 8f;
	private final float HEIGHT_CELL = PageSize.A4.getHeight() / 10 - 0.1F;
	private final float PADDING_CELL = 4F;
	private final int BORDER = 0;
	private final String XSL_LETTERA_TESSERE = "it/contrada/web/report/templateLetteraInvioTessera.xsl";
	private final String XSL_LETTERA_INSOLUTI = "it/contrada/web/report/templateLetteraInsoluti.xsl";
	private final String XSL_LETTERA_SOTTOSCRIZIONI = "it/contrada/web/report/templateLetteraSottoscrizioni.xsl";
	private final String XSL_DISTINTA = "it/contrada/web/report/templateDistinta.xsl";

	private final String FORMAT_IMPORTO_IT = "€ ###,##0.00"; //
	private SimpleDateFormat formatDDMMYYYY = new SimpleDateFormat("ddMMyyyy");
	private SimpleDateFormat formatDDMMYYYYEstesa = new SimpleDateFormat(
			"dd MMMMMM yyyy", Locale.ITALIAN);
	private NumberFormat nfImporto = null;
	private DecimalFormat formatterDecimalImportoIT;

	private String heightCellContenuto = "height:180px";
	private String heightCellMargine = "height:41px";
	private String widthCellContenuto = "width:320px";
	private String barcodeFont = null;

	private static Log log = LogFactory.getLog(PdfReport.class);

	public PdfReport() {
		nfImporto = NumberFormat.getNumberInstance(new Locale("it"));
		formatterDecimalImportoIT = (DecimalFormat) nfImporto;
		formatterDecimalImportoIT.applyPattern(FORMAT_IMPORTO_IT);
	}

	public PrintFile generaPdfEtichette(List<AnagraficaDTO> items)
			throws DocumentException, IOException {

		PrintFile pFile = null;

		String nomeFile = String.format("Etichette%s",
				formatDDMMYYYY.format(Calendar.getInstance().getTime()));

		File file = File.createTempFile(nomeFile, Constanti.EXT_FILE_PDF);

		FileOutputStream fos = new FileOutputStream(file);

		Document document = new Document(PageSize.A4, 10, 0, 0, 0);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.open();

			// creazione tabella
			PdfPTable table = new PdfPTable(NUM_COLS_PAGE);
			table.getDefaultCell().setBorder(1);

			table.setWidthPercentage(100F);

			for (AnagraficaDTO a : items) {
				table.addCell(genPdfCell(a));
			}

			document.add(table);
			pFile = new PrintFile();
			pFile.setNomeFile(nomeFile);
			pFile.setNomeFileCompleto(file.getAbsolutePath());
			return pFile;
		} finally {
			document.close();

		}

	}

	// creazione cella
	private PdfPCell genPdfCell(AnagraficaDTO a) {

		PdfPCell cell = new PdfPCell();
		cell.setFixedHeight(HEIGHT_CELL);
		cell.setPadding(PADDING_CELL);
		cell.setBorder(BORDER);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		Font f = new Font(Font.FontFamily.COURIER, SIZE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.addElement(new Phrase(String.format("%s\n%s\n%s", a
				.getIntestatario().toUpperCase(), a.getIndirizzo()
				.toUpperCase(), a.getCapProvincia().toUpperCase()), f));

		// Phrase phrase = new Phrase("44630259", f);

		/*
		 * Paragraph p = new Paragraph(a.getIntestatario(), f);
		 * cell.addElement(p);
		 * 
		 * p = new Paragraph(a.getIndirizzo(), f); cell.addElement(p);
		 * 
		 * p = new Paragraph(a.getCapProvincia(), f); cell.addElement(p);
		 */

		return cell;
	}

	public PrintFile generaPdfLettereInvioTessere(List<AnagraficaDTO> anags)
			throws Exception {

		return generaPdfLettera(anags, XSL_LETTERA_TESSERE);

	}

	public PrintFile generaPdfLetteraInsoluti(List<AnagraficaDTO> anags,
			int annoDa, int annoA) throws Exception{

		return generaPdfLetteraConTessere(anags, XSL_LETTERA_INSOLUTI, annoDa,
				annoA);

	}

	public PrintFile generaPdfLetteraSottoscrizioni(List<AnagraficaDTO> anags)
			throws Exception {

		return generaPdfAnagraficheConPrincipale(anags,
				XSL_LETTERA_SOTTOSCRIZIONI);

	}

	/**
	 * effettua la generazione del pdf per la stampa delle tessere a partire dal
	 * template (xsl) e dall'xml
	 * 
	 * @param xslFile
	 * @param xmlSource
	 * @return
	 * @throws IOException
	 * @throws TransformerException
	 * @throws DocumentException
	 * @throws URISyntaxException
	 */
	private PrintFile generaPdfLettera(List<AnagraficaDTO> anags, String xsl)
			throws Exception {

		File xslFile = new File(PdfReport.class.getClassLoader()
				.getResource(xsl).toURI());
		File imgComm = new File(PdfReport.class.getClassLoader()
				.getResource("it/contrada/web/report/Commissione.gif").toURI());

		File xmlTemp = File.createTempFile("xmlTemp", ".xml");
		FileWriter fstream = new FileWriter(xmlTemp);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(xmlTemp), "UTF8"));

		String xmlSource = null;
		PrintFile pFile = null;
		pFile = new PrintFile();
		String nomeFile = String.format("Lettere%s",
				formatDDMMYYYY.format(Calendar.getInstance().getTime()));

		String data = formatDDMMYYYYEstesa.format(Calendar.getInstance()
				.getTime());

		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		out.write("<Elenco>");

		for (AnagraficaDTO anag : anags) {
			/*
			 * <Intestatario>Simone Bagnolesi</Intestatario> <Indirizzo>Via
			 * Aretina, 9</indirizzo> <Cap>53100 Siena</Cap> <Data>22 Ottobre
			 * 2012</Data> <Famiglia>22<Famiglia>
			 */
			out.write("<Anagrafica>");
			out.write("<Intestatario>");
			out.write(anag.getIntestatario());
			out.write("</Intestatario>");

			out.write("<Indirizzo>");
			out.write(anag.getIndirizzo());
			out.write("</Indirizzo>");

			out.write("<Cap>");
			out.write(anag.getCapProvincia());
			out.write("</Cap>");

			out.write("<Data>");
			out.write(data);
			out.write("</Data>");

			out.write("<Famiglia>");
			out.write(anag.getIdFamiglia().toString());
			out.write("</Famiglia>");

			out.write("<Immagine>");
			out.write(imgComm.getPath());
			out.write("</Immagine>");

			out.write("</Anagrafica>");
		}

		out.write("</Elenco>");
		out.close();
		fstream.close();

		// scrivo il file xml temporaneo

		File pdfFile = xslXmlToHtml(xslFile, xmlTemp);

		pFile.setNomeFile(nomeFile);
		pFile.setFile(pdfFile);
		pFile.setNomeFileCompleto(pdfFile.getAbsolutePath());

		return pFile;

	}

	public PrintFile generaPdfDistinta(int nrDistinta, int nrAnno, String data,
			List<TesseraDTO> tessere,String utente) throws Exception {

		int quotaTotale = 0;
		int quotaPrivistaTotale = 0;

		File xslFile = new File(PdfReport.class.getClassLoader()
				.getResource(XSL_DISTINTA).toURI());

		File xmlTemp = File.createTempFile("xmlTemp", ".xml");
		FileWriter fstream = new FileWriter(xmlTemp);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(xmlTemp), "UTF8"));

		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		out.write("<distinta>");
		out.write("<nrDistinta>" + nrDistinta + "</nrDistinta>");
		out.write("<nrAnno>" + nrAnno + "</nrAnno>");
		out.write("<data>" + data + "</data>");
		out.write("<utente>" + utente + "</utente>");
		out.write("<tessere>");

		for (TesseraDTO tessera : tessere) {
			/*
			 * <Intestatario>Simone Bagnolesi</Intestatario> <Indirizzo>Via
			 * Aretina, 9</indirizzo> <Cap>53100 Siena</Cap> <Data>22 Ottobre
			 * 2012</Data> <Famiglia>22<Famiglia>
			 */
			out.write("<tessera>");
			out.write("<idAnag>");
			out.write(Integer.valueOf(tessera.getIdAnag()).toString());
			out.write("</idAnag>");

			out.write("<cognome>");
			out.write(tessera.getCognome());
			out.write("</cognome>");

			out.write("<nome>");
			out.write(tessera.getNome());
			out.write("</nome>");

			out.write("<dsTipoTessera>");
			out.write(tessera.getDsTipoTessera());
			out.write("</dsTipoTessera>");

			out.write("<dsIncasso>");
			out.write(tessera.getDsIncasso());
			out.write("</dsIncasso>");

			out.write("<quota>");
			out.write(BaseUtil.formatImporto(tessera.getQuota()));
			out.write("</quota>");

			out.write("<quotaIncassata>");
			out.write(BaseUtil.formatImporto(tessera.getQuotaIncassata()));
			out.write("</quotaIncassata>");

			out.write("</tessera>");

			quotaTotale += tessera.getQuotaIncassata();
			quotaPrivistaTotale += tessera.getQuota();
		}

		out.write("</tessere>");

		out.write("<quotaPrivistaTotale>"
				+ BaseUtil.formatImporto(quotaPrivistaTotale)
				+ "</quotaPrivistaTotale>");
		out.write("<quotaTotale>" + BaseUtil.formatImporto(quotaTotale)
				+ "</quotaTotale>");

		out.write("</distinta>");
		out.close();
		fstream.close();

		// scrivo il file xml temporaneo

		File filePDF = xslXmlToHtml(xslFile, xmlTemp);

		PrintFile pFile = new PrintFile();
		String nomeFile = String.format("Distinta%s",
				formatDDMMYYYY.format(Calendar.getInstance().getTime()));
		pFile.setNomeFile(nomeFile);
		pFile.setFile(filePDF);
		pFile.setNomeFileCompleto(filePDF.getAbsolutePath());

		return pFile;

	}

	private PrintFile generaPdfLetteraConTessere(List<AnagraficaDTO> anags,
			String xsl, int annoDa, int annoA) throws Exception {

		String annoStrDa = Integer.valueOf(annoDa).toString();
		String annoStrA = Integer.valueOf(annoA).toString();

		File xslFile = new File(PdfReport.class.getClassLoader()
				.getResource(xsl).toURI());
		File imgComm = new File(PdfReport.class.getClassLoader()
				.getResource("it/contrada/web/report/Commissione.gif").toURI());

		File xmlTemp = File.createTempFile("xmlTemp", ".xml");
		FileWriter fstream = new FileWriter(xmlTemp);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(xmlTemp), "UTF8"));

		String xmlSource = null;
		String telefono = null;
		PrintFile pFile = null;
		pFile = new PrintFile();
		String nomeFile = String.format("Lettere%s",
				formatDDMMYYYY.format(Calendar.getInstance().getTime()));

		String data = formatDDMMYYYYEstesa.format(Calendar.getInstance()
				.getTime());

		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		out.write("<Elenco>");

		for (AnagraficaDTO anag : anags) {
			/*
			 * <Intestatario>Simone Bagnolesi</Intestatario> <Indirizzo>Via
			 * Aretina, 9</indirizzo> <Cap>53100 Siena</Cap> <Data>22 Ottobre
			 * 2012</Data> <Famiglia>22<Famiglia>
			 */
			telefono = null;
			out.write("<Anagrafica>");
			out.write("<Intestatario>");
			out.write(anag.getIntestatario());
			out.write("</Intestatario>");

			out.write("<Indirizzo>");
			out.write(anag.getIndirizzo());
			out.write("</Indirizzo>");

			out.write("<Cap>");
			out.write(anag.getCapProvincia());
			out.write("</Cap>");

			out.write("<Data>");
			out.write(data);
			out.write("</Data>");

			out.write("<Famiglia>");
			out.write(anag.getIdFamiglia().toString());
			out.write("</Famiglia>");

			out.write("<Immagine>");
			out.write(imgComm.getPath());
			out.write("</Immagine>");

			out.write("<AnnoDA>");
			out.write(annoStrDa);
			out.write("</AnnoDA>");

			out.write("<AnnoA>");
			out.write(annoStrA);
			out.write("</AnnoA>");

			out.write("<Tel>");
			telefono = String.format("%s %s",
					anag.getTxCell() != null ? anag.getTxCell() : "",
					anag.getTxFisso() != null ? anag.getTxFisso() : "");
			out.write(telefono.trim());
			out.write("</Tel>");

			out.write("<Mail>");
			if (anag.getMail() != null) {
				out.write(anag.getMail().trim());
			}
			out.write("</Mail>");

			if (annoStrA.equalsIgnoreCase(annoStrDa)) {
				// recupero anno, in allegato il bolletttino postale per il
				// pagamento
				out.write("<recuperoAnno>");
				out.write("true");
				out.write("</recuperoAnno>");
			} else {
				// recupero anni passati, contattare direttamente i responsabili
				out.write("<recuperoAnno>");
				out.write("false");
				out.write("</recuperoAnno>");
			}

			out.write("<Tessere>");
			for (TesseraDTO tes : anag.getTessere()) {
				out.write("<Protettore>");
				out.write("<Nominativo>");
				out.write(String.format("%s %s", tes.getCognome(),
						tes.getNome()));
				out.write("</Nominativo>");
				out.write("<Quota>");
				out.write(nfImporto.format(Integer
						.valueOf(tes.getQuota() / 100).doubleValue()));
				out.write("</Quota>");
				out.write("</Protettore>");
			}
			out.write("</Tessere>");
			out.write("<Quota>");
			out.write(nfImporto.format((Double.valueOf(anag.getQuota() / 100))));
			out.write("</Quota>");

			out.write("</Anagrafica>");
		}

		out.write("</Elenco>");
		out.close();
		fstream.close();

		// scrivo il file xml temporaneo

		File pdfFile = xslXmlToHtml(xslFile, xmlTemp);

		pFile.setNomeFile(nomeFile);
		pFile.setFile(pdfFile);
		pFile.setNomeFileCompleto(pdfFile.getAbsolutePath());

		return pFile;

	}

	private PrintFile generaPdfAnagraficheConPrincipale(
			List<AnagraficaDTO> anags, String xsl) throws Exception {

		File xslFile = new File(PdfReport.class.getClassLoader()
				.getResource(xsl).toURI());
		File imgComm = new File(PdfReport.class.getClassLoader()
				.getResource("it/contrada/web/report/Commissione.gif").toURI());

		File xmlTemp = File.createTempFile("xmlTemp", ".xml");
		FileWriter fstream = new FileWriter(xmlTemp);

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(xmlTemp), "UTF8"));

		String xmlSource = null;
		String telefono = null;
		PrintFile pFile = null;
		pFile = new PrintFile();
		String nomeFile = String.format("Lettere%s",
				formatDDMMYYYY.format(Calendar.getInstance().getTime()));

		String data = formatDDMMYYYYEstesa.format(Calendar.getInstance()
				.getTime());

		out.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		out.write("<Elenco>");

		for (AnagraficaDTO anag : anags) {
			/*
			 * <Intestatario>Simone Bagnolesi</Intestatario> <Indirizzo>Via
			 * Aretina, 9</indirizzo> <Cap>53100 Siena</Cap> <Data>22 Ottobre
			 * 2012</Data> <Famiglia>22<Famiglia>
			 */
			telefono = null;
			out.write("<Anagrafica>");
			out.write("<Intestatario>");
			out.write(anag.getIntestatarioPrinc());
			out.write("</Intestatario>");

			out.write("<Indirizzo>");
			out.write(anag.getIndirizzo());
			out.write("</Indirizzo>");

			out.write("<Cap>");
			out.write(anag.getCapProvincia());
			out.write("</Cap>");

			out.write("<Famiglia>");
			out.write(anag.getIdFamiglia().toString());
			out.write("</Famiglia>");

			out.write("<Sesso>");
			out.write(anag.getSesso() != null ? anag.getSesso() : "");
			out.write("</Sesso>");

			out.write("<Immagine>");
			out.write(imgComm.getPath());
			out.write("</Immagine>");

			out.write("<Telefono><![CDATA[");
			out.write(anag.getTelefono());
			out.write("]]></Telefono>");

			out.write("<Mail><![CDATA[");
			out.write(anag.getTxMail());
			out.write("]]></Mail>");

			out.write("<Anagrafiche>");
			for (AnagraficaDTO tes : anag.getAnagrafiche()) {
				out.write("<Protettore>");

				out.write("<Nominativo>");
				out.write(tes.getIntestatario());
				out.write("</Nominativo>");

				out.write("</Protettore>");
			}
			out.write("</Anagrafiche>");

			out.write("</Anagrafica>");
		}

		out.write("</Elenco>");
		out.close();
		fstream.close();

		// scrivo il file xml temporaneo

		File pdfFile = xslXmlToHtml(xslFile, xmlTemp);

		pFile.setNomeFile(nomeFile);
		pFile.setFile(pdfFile);
		pFile.setNomeFileCompleto(pdfFile.getAbsolutePath());

		return pFile;

	}

	private File xslXmlToHtml(File xsl, File xml) throws Exception {

		// creo il file temporanei
		File htmlTemp = File.createTempFile("htmlTemp", ".html");

		// effetto la conversione xml,xsl to html scrivo il file html temporaneo
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Source xslSource = new javax.xml.transform.stream.StreamSource(xsl);
		Source xmlSource = new javax.xml.transform.stream.StreamSource(xml);
		javax.xml.transform.stream.StreamResult result = new StreamResult(
				htmlTemp);
		Transformer transformer;
		transformer = tFactory.newTransformer(xslSource);
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transformer.transform(xmlSource, result);

		// a partite dal file html creo il file pdf
		return generatePdfFromHtml(htmlTemp);

	}

	public File generatePdfFromHtml(File htmlTemp) throws Exception {

		File pdfTemp = File.createTempFile("pdfTemp", ".pdf");
		Document document = new Document(PageSize.A4, 30, 30, 5, 5);

		PdfWriter pdfWriter = null;
		FileInputStream is = null;
		try {
			pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(
					pdfTemp));
			document.open();

			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();

			is = new FileInputStream(htmlTemp);

			worker.parseXHtml(pdfWriter, document, is, Charset.forName("UTF-8"));

			// worker.parseXHtml(pdfWriter, document, new FileReader(htmlTemp));

		} catch (Exception e) {
			log.error(e);
			throw e;
		} finally {
			if (is != null)
				is.close();

			if (document != null)
				document.close();

			if (pdfWriter != null)
				pdfWriter.close();
		}

		return pdfTemp;
	}

	private File generatePdfFromHtml(String sourceHtml) throws Exception {
		// scrivo un file html temporaneo
		File htmlTemp = File.createTempFile("htmlTemp", "html");
		FileWriter fstream = new FileWriter(htmlTemp);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(sourceHtml);

		return generatePdfFromHtml(htmlTemp);
	}

	public PrintFile generaPdfTessera(List<TesseraStampataDTO> tessere)
			throws IOException, ContradaExceptionBloccante, DocumentException {
		PrintFile pFile = null;

		final float PAGE_MARGIN_TOP = 12F;
		final float PAGE_MARGIN_LEFT = 44f;
		final float PAGE_MARGIN_RIGHT = 44f;
		final float PAGE_MARGIN_BOTTOM = 42f;
		final float HEIGHT_TABLE_MARGINE = 47;
		final float FONT_SIZE = 10F;
		Document document = null;
		FileOutputStream fos = null;
		try {
			String nomeFile = String.format("Tessere%s",
					formatDDMMYYYY.format(Calendar.getInstance().getTime()));

			File file = File.createTempFile(nomeFile, Constanti.EXT_FILE_PDF);

			fos = new FileOutputStream(file);

			// margini pagina
			document = new Document(PageSize.A4, PAGE_MARGIN_LEFT,
					PAGE_MARGIN_RIGHT, PAGE_MARGIN_TOP, PAGE_MARGIN_BOTTOM);

			int i = 1;
			int tesserePerPagina = 4;

			PdfWriter writer = PdfWriter.getInstance(document, fos);
			document.open();

			PdfContentByte cb = writer.getDirectContent();

			PdfPTable table = null;

			for (TesseraStampataDTO tes : tessere) {
				table = scriviTabellaTessera(tes, cb);

				if ((i) % tesserePerPagina == 0) {

					// cambia pagina
					document.add(table);
					document.newPage();

				} else {

					table.setSpacingAfter(HEIGHT_TABLE_MARGINE);
					// insrisco il margine
					document.add(table);

				}
				i++;
			}
			pFile = new PrintFile();
			pFile.setNomeFile(nomeFile);
			pFile.setNomeFileCompleto(file.getAbsolutePath());
			pFile.setFile(file);
		} finally {
			if (document != null) {
				document.close();
			}
			if (fos != null) {
				fos.close();
			}
		}

		return pFile;

	}

	private PdfPTable scriviTabellaTessera(TesseraStampataDTO tes,
			PdfContentByte cb) throws IOException, ContradaExceptionBloccante,
			DocumentException {

		final int NUM_COLS_TABELLA_TESSERA = 2;
		final float HEIGHT_CELL_TABELLA_TESSERA = 160f;
		final float PADDING_CELL_TABELLA_TESSERA = 9f;
		final float FONT_SIZE_CELL_ESATTORE = 10f;
		final float FONT_SIZE_CELL_PROTETTORE = 12f;
		// final float LINE_SPACING = 4.5f;
		final int BORDER = 0;

		String tesseraAnno = String.format("%s-%d",
				Long.toString(tes.getIdTessera()), tes.getAnno());

		// creo la tabella con due colone
		PdfPTable table = new PdfPTable(NUM_COLS_TABELLA_TESSERA);
		table.getDefaultCell().setBorder(BORDER);

		table.setWidthPercentage(100F);

		// cella contenuto esattore
		Font f = new Font(Font.FontFamily.COURIER, FONT_SIZE_CELL_ESATTORE);
		f.setColor(BaseColor.BLACK);

		Paragraph pEsattore = new Paragraph();
		pEsattore.setFont(f);
		pEsattore.setMultipliedLeading(1);

		PdfPCell cellEsattore = new PdfPCell();
		cellEsattore.setBorder(BORDER);
		cellEsattore.setFixedHeight(HEIGHT_CELL_TABELLA_TESSERA);
		cellEsattore.setPadding(PADDING_CELL_TABELLA_TESSERA);
		cellEsattore.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cellEsattore.setHorizontalAlignment(Element.ALIGN_LEFT);

		// barcode
		Barcode39 code39 = new Barcode39();
		code39.setCode(tesseraAnno);
		pEsattore.add(new Chunk(code39.createImageWithBarcode(cb, null, null),
				10, 10));

		/*
		 * BarcodeEAN codeEAN = new BarcodeEAN(); codeEAN.setCode(tesseraAnno);
		 * pEsattore.add(new Chunk(codeEAN.createImageWithBarcode(cb, null,
		 * null), 10, 10));
		 */
		// document.add(code39.createImageWithBarcode(cb, null, null));

		/*
		 * pEsattore.add(new Phrase(String.format("\n%s", tesseraAnno),
		 * f6Barcode));
		 */

		// Nr. tessera, Nr. Anagrafica, Nr.Famiglia
		pEsattore.add(new Phrase(String.format("\nAnag %s - Fam. %s",
				Long.toString(tes.getIdAnagrafica()),
				Integer.toString(tes.getIdFamiglia()))));

		// cognome e nome
		pEsattore.add(new Phrase(String.format("\n%s %s", tes.getCognome(),
				tes.getNome())));

		// indirizzo
		pEsattore.add(new Phrase(String.format("\n%s", tes.getIndirizzo())));

		// cap e provincia
		pEsattore.add(new Phrase(String.format("\n%s", tes.getCapProvincia())));

		StringBuilder contatti = new StringBuilder();
		if (tes.getFisso() != null && !tes.getFisso().isEmpty()) {
			contatti.append(String.format("%s ", tes.getFisso()));
		}
		if (tes.getMail() != null && !tes.getMail().isEmpty()) {
			contatti.append((String.format(" %s ", tes.getMail())));
		}
		if (tes.getCell() != null && !tes.getCell().isEmpty()) {
			contatti.append((String.format(" %s ", tes.getCell())));
		}
		if (!contatti.toString().isEmpty()) {
			pEsattore.add(new Phrase(String.format("\n%s ", contatti.toString()
					.trim())));
		}

		if (tes.getNote() != null && !tes.getNote().isEmpty()) {
			pEsattore.add(new Phrase(String.format("\n%s ", tes.getNote())));
		}
		if (tes.getEsattore() != null && !tes.getEsattore().isEmpty()) {
			pEsattore.add(new Phrase(String.format("\nEsattore:%s ",
					tes.getEsattore())));
		}

		pEsattore.add(new Phrase(String.format("\n%s ANNO %s", tes
				.getDsTipoTessera().toUpperCase(), tes.getAnno())));

		double importo = tes.getQuota() / 100;
		pEsattore.add(new Phrase(String.format("\n%s - %.2f", tes.getCarica(),
				importo)));

		cellEsattore.addElement(pEsattore);
		table.addCell(cellEsattore);

		// cella contenuto protettore
		PdfPCell cellProtettore = new PdfPCell();
		cellProtettore.setBorder(BORDER);
		cellProtettore.setFixedHeight(HEIGHT_CELL_TABELLA_TESSERA);
		cellProtettore.setPadding(PADDING_CELL_TABELLA_TESSERA);
		cellProtettore.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cellProtettore.setHorizontalAlignment(Element.ALIGN_LEFT);

		Font fp = new Font(Font.FontFamily.COURIER, FONT_SIZE_CELL_PROTETTORE);
		fp.setColor(BaseColor.BLUE);

		Paragraph pProt = new Paragraph();
		pProt.setFont(fp);
		pProt.setMultipliedLeading(1);

		pProt.add(new Phrase(String.format("\n%s %s", tes.getCognome()
				.toUpperCase(), tes.getNome().toUpperCase())));
		pProt.add(new Phrase(String.format("\n%s ANNO %s", tes
				.getDsTipoTessera().toUpperCase(), tes.getAnno())));

		cellProtettore.addElement(pProt);

		/*
		 * Font fp8 = new Font(Font.FontFamily.COURIER, 8);
		 * 
		 * cellProtettore.addElement(new Phrase(String.format("/n%s %s ", tes
		 * .getCarica().toUpperCase(), Long .toString(tes.getIdAnagrafica())),
		 * fp8));
		 */
		table.addCell(cellProtettore);

		return table;
	}

	private void createPDF(List<TesseraStampataDTO> tessere)
			throws IOException, ContradaExceptionBloccante {

		// PrintWriter writer= response.getWriter();

		barcodeFont = Configuration.getProperty("fontBarcode");

		StringBuilder writer = new StringBuilder();

		// tabella esterna
		writer.append("<html><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" ><meta http-equiv=\"X-UA-Compatible\" content=\"IE=8\" >");
		writer.append("<style type=\"text/css\"> @page {margin-top: 56px; margin-left: 75px; margin-right:68px; margin-bottom:68px}</style>");

		writer.append("<body style=\"margin-top: 0px; margin-right: 0px; margin-bottom: 0px; margin-left: 0px; font-size: 10px; color: blue;\">");

		writer

		.append("<table style=\"padding: 0px;\">");
		int i = 1;
		int tesserePerPagina = 4;
		for (TesseraStampataDTO tes : tessere) {
			if ((i) % tesserePerPagina == 0) {
				// cambia pagina

				scriviRigaContenuto(writer, tes);
				writer.append("</table>");
				writer.append("<p style=\"page-break-after:always\"></p>");
				writer.append("<table style=\"padding: 0px;\">");

			} else {
				scriviRigaContenuto(writer, tes);
				scriviRigaMargine(writer);

			}
			i++;
		}

		if ((i) % tesserePerPagina != 0) {
			writer.append("</table>");
		}
		writer.append("</body></html>");

		// String str1=writer.toString();
		// String str = "<html><body>ssss</body></html>";

		((HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest()).getSession().setAttribute(
				Costante.HTML_PDF, writer.toString());

	}

	private void scriviRigaMargine(StringBuilder writer) throws IOException {

		writer.append("<tr>");

		// celle contenuto
		writer.append(String.format("<td style=\"%s;%s;\">", heightCellMargine,
				widthCellContenuto));
		writer.append("</td>");

		writer.append(String.format("<td style=\"%s;%s;\">", heightCellMargine,
				widthCellContenuto));
		writer.append("</td>");

		writer.append("</tr>");
	}

	private void scriviRigaContenuto(StringBuilder writer,
			TesseraStampataDTO tes) throws IOException {

		writer.append("<tr>");
		String tesseraAnno = String.format("%s-%d",
				Long.toString(tes.getIdTessera()), tes.getAnno());

		// cella contenuto esattore

		writer.append(String
				.format("<td style=\"%s;%s;padding:15px;text-align:left;vertical-align:bottom;font-size:12px;color:black\">",
						heightCellContenuto, widthCellContenuto));
		writer.append(String.format(" <font face='%s' size='6'>%s</font></br>",
				barcodeFont, tesseraAnno));
		writer.append(String.format("Nr. Tes. %s - Nr. Anag %s - ",
				Long.toString(tes.getIdTessera()),
				Long.toString(tes.getIdAnagrafica())));
		writer.append(String.format("Nr. Fam. %s </br>",
				Integer.toString(tes.getIdFamiglia())));
		writer.append(String.format("%s %s</br>", tes.getCognome(),
				tes.getNome()));
		writer.append(String.format("%s</br> %s</br>", tes.getIndirizzo(),
				tes.getCapProvincia()));

		if (tes.getFisso() != null && !tes.getFisso().isEmpty()) {
			writer.append(String.format("%s ", tes.getFisso()));
		}
		if (tes.getMail() != null && !tes.getMail().isEmpty()) {
			writer.append(String.format("%s ", tes.getMail()));
		}
		if (tes.getCell() != null && !tes.getCell().isEmpty()) {
			writer.append(String.format("%s ", tes.getCell()));
		}
		if (tes.getNote() != null && !tes.getNote().isEmpty()) {
			writer.append(String.format("</br>%s ", tes.getNote()));
		}
		if (tes.getEsattore() != null && !tes.getEsattore().isEmpty()) {
			writer.append(String.format("</br>Esattore:%s ", tes.getEsattore()));
		}

		writer.append(String.format("</br>%s ANNO %s", tes.getDsTipoTessera()
				.toUpperCase(), tes.getAnno()));

		double importo = tes.getQuota() / 100;
		writer.append(String.format("</br>%s - %.2f", tes.getCarica(), importo));

		writer.append("</td>");

		// cella contenuto protettore
		writer.append(String
				.format("<td style=\"%s;%s;padding:15px;text-align:left;vertical-align:bottom;color:blue;font-size:12px;font-weight:bold;\">",
						heightCellContenuto, widthCellContenuto));
		writer.append(String.format("%s %s </br>", tes.getCognome()
				.toUpperCase(), tes.getNome().toUpperCase()));
		writer.append(String.format("%s ANNO %s", tes.getDsTipoTessera()
				.toUpperCase(), tes.getAnno()));
		writer.append(String
				.format("<span style=\"font-size:8px;font-weight:bold;\"></br>%s %s </span>",
						tes.getCarica().toUpperCase(),
						Long.toString(tes.getIdAnagrafica())));
		writer.append("</td>");

	}

}
