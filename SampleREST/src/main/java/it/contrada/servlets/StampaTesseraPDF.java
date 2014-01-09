package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.dto.TesseraStampataDTO;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.web.report.PdfReport;
import it.contrada.web.util.Configuration;
import it.contrada.web.util.Costante;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class StampaTessera
 */
public class StampaTesseraPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String heightCellContenuto = "height:180px";
	private String heightCellMargine = "height:41px";
	private String widthCellContenuto = "width:320px";
	private String barcodeFont = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StampaTesseraPDF() {
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

		PdfReport rep = new PdfReport();
		try {
			PrintFile file = rep.generaPdfTessera(tessere);
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

		} catch (ContradaExceptionBloccante e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		}
	}

}
