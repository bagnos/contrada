package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dto.TesseraDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.exceptions.ContradaExceptionNonBloccante;
import it.contrada.util.BaseUtil;
import it.contrada.web.report.PdfReport;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.security.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class StampaDistinta
 */
public class StampaDistinta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StampaDistinta() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int nrDistinta;
		int nrAnno;
		String data = null;
		FileInputStream fileInputStream = null;
		OutputStream responseOutputStream = null;
		String utente=null;

		// currencyOut = currencyFormatter.format(currency);

		nrDistinta = Integer.parseInt(request.getParameter("id"));
		nrAnno = Integer.parseInt(request.getParameter("anno"));

		data = request.getParameter("data");
		utente=request.getParameter("utente");

		try {
			List<TesseraDTO> tessere = RicercaTipoTesseraBD
					.ricercaTesserePerDistinta(nrAnno, nrDistinta);

			PdfReport rep = new PdfReport();
			PrintFile file = rep.generaPdfDistinta(nrDistinta, nrAnno, data,
					tessere,utente);

			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename="
					+ file.getNomeFile());
			response.setContentLength((int) file.getFile().length());

			fileInputStream = new FileInputStream(file.getFile());
			responseOutputStream = response.getOutputStream();
			int bytes;
			while ((bytes = fileInputStream.read()) != -1) {
				responseOutputStream.write(bytes);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}  finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (responseOutputStream != null) {
				responseOutputStream.close();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
