package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.web.util.Costante;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * Servlet implementation class generatePdf
 */
public class GeneratePdf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GeneratePdf() {
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
		String nomeFile = request.getParameter(Costante.NOME_FILE).toString();
		PrintFile filePrint = (PrintFile) request.getSession().getAttribute(nomeFile);
		File file = new File(filePrint.getNomeFileCompleto());

		InputStream is = new FileInputStream(file);
		
		  // We create a reader with the InputStream
        PdfReader reader = new PdfReader(is, null);
	
		// We create an OutputStream for the new PDF
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		//create pdf
		try {
			PdfStamper stamper = new PdfStamper(reader, baos);
			stamper.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}
				
		OutputStream os = response.getOutputStream();
        baos.writeTo(os);
        os.flush();
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
