package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.dto.AnagraficaDTO;
import it.contrada.web.enumcontrada.TipoDocumento;
import it.contrada.web.report.PdfReport;
import it.contrada.web.util.Costante;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class StampaLettera
 */
public class StampaLetteraPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StampaLetteraPDF() {
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
		PdfReport report = new PdfReport();
		PrintFile file=null; 
		try {
			String nomeFile = request.getParameter(Costante.NOME_FILE)
					.toString();
			String tipoDocumento = request
					.getParameter(Costante.TIPO_DOCUMENTO).toString();
			List<AnagraficaDTO> anags = (List<AnagraficaDTO>) request
					.getSession().getAttribute(nomeFile);

			if (tipoDocumento.equalsIgnoreCase(TipoDocumento.Sottoscrizioni.getTipoDocumento())) {
				file = report.generaPdfLetteraSottoscrizioni(anags);
			}

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

		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			throw new ServletException(e);
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
