package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.RicercaAnagraficaBD;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.web.report.PdfReport;
import it.contrada.web.util.Costante;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class StampaPDFLetteraTessera
 */
public class StampaPDFLetteraTessera extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StampaPDFLetteraTessera() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<TesseraStampataDTO> tessere = (List<TesseraStampataDTO>) request
				.getSession().getAttribute(Costante.TESSERE_PDF_SESSION);

		List<AnagraficaDTO> anags = new ArrayList<AnagraficaDTO>();
		AnagraficaDTO anag = null;
		boolean aggiungi = false;

		// recupero tutti gli indirizzi associati alle tessera, distinct per
		// idFamiglia
		for (TesseraStampataDTO tes : tessere) {
			aggiungi = false;
			if (anags.isEmpty()) {
				// lsta vuota
				aggiungi = true;

			} else {
				// le tessere sono ordinate per id famiglia
				if (anags.get(anags.size()-1).getIdFamiglia() != tes.getIdFamiglia()) {
					// famiglia mai inserita
					aggiungi = true;
				}
			}
			if (aggiungi) {
				anag = new AnagraficaDTO();
				anag.setIntestatario(tes.getIntestatario());
				anag.setIndirizzo(tes.getIndirizzo());
				anag.setCapProvincia(tes.getCapProvincia());
				anag.setIdFamiglia(tes.getIdFamiglia());
				anags.add(anag);
			}

		}

		PdfReport report = new PdfReport();
		try {
			PrintFile file = report.generaPdfLettereInvioTessere(anags);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename="
					+ file.getNomeFile());
			response.setContentLength((int) file.getFile().length());

			FileInputStream fileInputStream = new FileInputStream(file
					.getFile());
			OutputStream responseOutputStream = response.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} 
	}
}
