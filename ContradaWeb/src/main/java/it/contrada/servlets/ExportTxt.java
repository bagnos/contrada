package it.contrada.servlets;

import it.contrada.backingbeans.PrintFile;
import it.contrada.web.util.Costante;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExportTxt
 */
public class ExportTxt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportTxt() {
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

		response.setContentType("text/plain");

		String nomeFile = request.getParameter(Costante.NOME_FILE).toString();

		PrintFile filePrint = (PrintFile) request.getSession().getAttribute(
				nomeFile);

		try {
			File file = new File(filePrint.getNomeFileCompleto());
			
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ filePrint.getNomeFile()+".crm" + "\"");
			byte[] buf = new byte[1024];

			long length = file.length();
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			ServletOutputStream out = response.getOutputStream();
			response.setContentLength((int) length);
			while ((in != null) && ((length = in.read(buf)) != -1)) {
				out.write(buf, 0, (int) length);
			}
			in.close();
			out.close();
		} catch (Exception exc) {
			exc.printStackTrace();
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
