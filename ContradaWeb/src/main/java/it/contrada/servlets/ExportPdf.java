package it.contrada.servlets;

import it.contrada.web.report.PdfReport;
import it.contrada.web.util.Costante;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;

/**
 * Servlet implementation class ExportPdf
 */
public class ExportPdf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExportPdf() {
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

		String html = request.getSession().getAttribute(Costante.HTML_PDF)
				.toString();

		response.getWriter().print(html);
		response.getWriter().print(
				"<script language=\"JavaScript\">window.print()</script>");

		/*
		 * response.setContentType("application/pdf"); Document document = new
		 * Document(PageSize.A4, 0, 0, 0, 0);
		 * 
		 * try { PdfWriter.getInstance(document, response.getOutputStream());
		 * 
		 * } catch (DocumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * document.open(); HTMLWorker htmlWorker = new HTMLWorker(document);
		 * 
		 * htmlWorker.parse(new StringReader(html));
		 * 
		 * /* HtmlPipelineContext htmlContext = new HtmlPipelineContext();
		 * 
		 * htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
		 * 
		 * CSSResolver cssResolver =
		 * 
		 * XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
		 * 
		 * Pipeline<?> pipeline =
		 * 
		 * new CssResolverPipeline(cssResolver,
		 * 
		 * new HtmlPipeline(htmlContext,
		 * 
		 * new PdfWriterPipeline(document, writer)));
		 * 
		 * XMLWorker worker = new XMLWorker(pipeline, true);
		 * 
		 * XMLParser p = new XMLParser(worker);
		 * 
		 * 
		 * 
		 * p.parse(HTMLParsingProcess.class.getResourceAsStream("/html/walden.html"
		 * ));
		 */

		// document.close();

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
