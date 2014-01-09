package it.contrada.servlets;

import it.contrada.businessdelegate.RicercaTipoTesseraBD;
import it.contrada.dto.TesseraDTO;
import it.contrada.interfaces.IRicercaTessera;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int nrDinstinta;
		int nrAnno;
		String data = null;

		// currencyOut = currencyFormatter.format(currency);

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		DecimalFormat df = new DecimalFormat(".00");

		nrDinstinta = Integer.parseInt(request.getParameter("id"));
		nrAnno = Integer.parseInt(request.getParameter("anno"));

		data = request.getParameter("data");

		try {
			List<TesseraDTO> tessere = RicercaTipoTesseraBD
					.ricercaTesserePerDistinta(nrAnno, nrDinstinta);

			StringBuilder writer = new StringBuilder();

			// tabella esterna
			writer
					.append("<html><META http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
			writer
					.append("<style type=\"text/css\"> @page {margin-top: 56px; margin-left: 75px; margin-right:68px; margin-bottom:68px}</style>");

			writer
					.append("<body style='font-size: 12px;font-family: Arial, Helvetica, sans-serif;' \">");

			writer
					.append("<p style='font-size:24px;font-weight:bold;'>Nobile Contrada del Nicchio - Commissione Protettorato");
			writer
					.append("</br><span style='font-size:18px;font-weight:normal;'>Distinta "
							+ nrDinstinta
							+ " per l'anno "
							+ nrAnno
							+ " del "
							+ data + "</span></p>");
			writer.append("<hr>");

			writer
					.append("<table cellpadding='2px' style=\"padding: 0px;border:none;font-size:12px;text-align:left;width:100%\">");

			writer.append("</tr>");

			writer.append("<tr style='font-weight:bold'>");
			writer.append("<td>");
			writer.append("Cod.");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Cognome");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Nome");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Tessera");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Pagamento");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Quota");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Quota Incassata");
			writer.append("</td>");

			writer.append("</tr>");
			writer.append("</tr>");

			int quotaTotale = 0;
			int quotaPrivistaTotale = 0;
			for (TesseraDTO tessera : tessere) {
				writer.append("<tr>");
				writer.append("<td>");
				writer.append(tessera.getIdAnag());
				writer.append("</td>");
				writer.append("<td>");
				writer.append(tessera.getCognome());
				writer.append("</td>");
				writer.append("<td>");
				writer.append(tessera.getNome());
				writer.append("</td>");
				writer.append("<td>");
				writer.append(tessera.getDsTipoTessera());
				writer.append("</td>");
				writer.append("<td>");
				writer.append(tessera.getDsIncasso());
				writer.append("</td>");
				writer.append("<td>");
				writer.append(df
						.format(((((double) tessera.getQuota()) / 100))));
				writer.append("</td>");
				writer.append("<td>");
				writer
						.append(df.format(((((double) tessera
								.getQuotaIncassata()) / 100))));
				writer.append("</td>");

				writer.append("</tr>");
				quotaTotale += tessera.getQuotaIncassata();
				quotaPrivistaTotale += tessera.getQuota();
			}

			writer.append("<tr style='font-weight:bold'>");
			writer.append("<td>");
			writer.append("");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("");
			writer.append("</td>");
			writer.append("<td>");
			writer.append("Totale &euro; : ");
			writer.append("</td>");
			writer.append("<td>");
			writer.append(df.format(((double) quotaPrivistaTotale) / 100));

			writer.append("</td>");
			writer.append("<td>");
			writer.append(df.format(((double) quotaTotale) / 100));

			writer.append("</td>");

			writer.append("</tr>");

			writer.append("</table>");
			writer
					.append("<script language=\"JavaScript\">window.print()</script>");

			writer.append("</body></html>");

			request.setCharacterEncoding("UTF-8");

			response.getWriter().print(writer.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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
