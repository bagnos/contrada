package it.contrada.servlets;

import it.contrada.businessdelegate.GestioneTesseraBD;
import it.contrada.exceptions.ContradaExceptionBloccante;
import it.contrada.mail.BaseMail;
import it.contrada.web.util.PayPalUtil;
import it.othala.payment.paypal.dto.IpnDTO;
import it.othala.payment.paypal.dto.MessageIpnDTO;
import it.othala.payment.paypal.dto.OrderDTO;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.TesseraPaymentDTO;
import it.othala.paymentservice.paypal.PayPalService;
import it.othala.paymentservice.paypal.exception.PayPalException;
import it.othala.paymentservice.paypal.exception.PayPalIpnErrorException;
import it.othala.paymentservice.paypal.exception.PayPalIpnInvalidException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet implementation class IpnPayPalServlet
 */
@WebServlet("/IpnPayPalServlet")
public class IpnPayPalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(IpnPayPalServlet.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IpnPayPalServlet() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ProfilePayPalDTO prof = PayPalUtil.getProfile("it", request);

			// recupero body orginario
			String originalRequest = getBody(request);
			// String originalRequest =
			// "residence_country=IT&invoice=abc1234&address_city=San+Jose&first_name=John&payer_id=TESTBUYERID01&shipping=3.04&mc_fee=0.44&txn_id=616099981&receiver_email=othalaBusines2_api1.othala.it&quantity=1&custom=1&payment_date=14%3A56%3A44+18+Jun+2014+PDT&address_country_code=US&address_zip=95131&tax=2.02&item_name=something&address_name=John+Smith&last_name=Smith&receiver_id=seller%40paypalsandbox.com&item_number=AK-1234&verify_sign=AFcWxV21C7fd0v3bYYYRCpSSRl31AdjaQWOXh1uA4qBTjfxWKuAn3BGg&address_country=United+States&payment_status=Completed&address_status=unconfirmed&business=seller%40paypalsandbox.com&payer_email=buyer%40paypalsandbox.com¬ify_version=2.1&txn_type=web_accept&test_ipn=1&payer_status=unverified&mc_currency=EUR&mc_gross=1000.00&address_state=CA&mc_gross1=1000&payment_type=echeck&address_street=123%2C+any+street";

			processIpnMessage(originalRequest, prof);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("errore ipn", e);
			throw new ServletException(e);
		}

	}

	public static String getBody(HttpServletRequest request) throws Exception {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(
						inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

	private IpnDTO valueOf(HashMap<String, String> req) {
		IpnDTO ipnDTO = new IpnDTO();
		ipnDTO.setCustom(req.get("custom"));
		ipnDTO.setMc_currency(req.get("mc_currency"));
		ipnDTO.setMc_gross(req.get("mc_gross"));
		ipnDTO.setPayment_status(req.get("payment_status"));
		ipnDTO.setReceiver_email(req.get("receiver_email"));
		ipnDTO.setTxn_id(req.get("txn_id"));
		ipnDTO.setParent_txn_id(req.get("parent_txn_id"));
		ipnDTO.setReason_code(req.get("reason_code"));
		return ipnDTO;
	}

	public void processIpnMessage(String originalRequest,
			ProfilePayPalDTO profile) throws Exception {

		log.info("IPN Original Request:" + originalRequest);
		String responseRequest = originalRequest + "&cmd=_notify-validate";
		StringBuilder sb = new StringBuilder();
		boolean errorFormalMessage = false;
		OrderDTO order = null;

		try {
			// resend message to PayPal for securiry protocol
			PayPalService service = new PayPalService(profile);

			HashMap<String, String> responseMap = service
					.getNotificationIPN(responseRequest);
			IpnDTO ipnDTO = valueOf(responseMap);
			log.info(String.format("prosessIpnMessage, ipnDTO: %s",
					ipnDTO.toString()));

			// check that txn_id has not been previously processed
			String txn_id = ipnDTO.getTxn_id();
			if (ipnDTO.getCustom() == null || ipnDTO.getCustom().isEmpty()) {
				log.info("messaggio non trattato da IPN");
				return;
			}
			Integer idOrder = Integer.valueOf(ipnDTO.getCustom());

			String state = ipnDTO.getPayment_status();
			if (!exitsIdTransaction(txn_id, ipnDTO.getPayment_status())) {

				// check that receiver_email is your Primary PayPal email
				String recEmailMerchant = profile.getReceiverEmail();
				String receiver_email = ipnDTO.getReceiver_email();
				if (!recEmailMerchant.trim().equalsIgnoreCase(receiver_email)) {
					sb.append(String
							.format("messagio non elaborato: emailMerchant %s diversa dalla mail %s presente nel messaggio %s",
									recEmailMerchant, receiver_email,
									ipnDTO.toString()));
					errorFormalMessage = true;
				}

				if (!ipnDTO.getMc_currency().trim().equalsIgnoreCase("EUR")) {
					sb.append(String
							.format("messagio non elaborato:divisa accettata %s diversa dalla divisa %s presente nel messaggio %s",
									"EUR", ipnDTO.getMc_currency(),
									ipnDTO.toString()));
					errorFormalMessage = true;
				}

				if (errorFormalMessage) {
					log.error(String
							.format("Messagio %s non elaborato, ci sono errori formali: %s",
									ipnDTO.getTxn_id(), sb.toString()));
					return;
				}

				// inserisco il messaggio
				MessageIpnDTO ipnMessage = new MessageIpnDTO();
				ipnMessage.setIdOrder(idOrder);
				ipnMessage.setFgElaborato(!errorFormalMessage);
				ipnMessage.setIdTransaction(txn_id);
				ipnMessage.setTxMessage(ipnDTO.toString());
				ipnMessage.setTxStato(ipnDTO.getPayment_status());
				ipnMessage.setTxNote(sb.toString());

				String messaggioEsito = GestioneTesseraBD.confermaOrderPayment(
						order, ipnDTO.getPayment_status(), ipnMessage);

				if (messaggioEsito != null) {
					messaggioEsito="Gentile Protettore, \n\n" + messaggioEsito +"\n\n La Commissione Protettorato";
					sendMailPayment(order, messaggioEsito);
					
				}

				return;

			} else {
				// messaggio già elaborato
				log.error(String.format(
						"transactionId %s del messaggio %s già elaborato ",
						txn_id, originalRequest));
				return;
			}

		} catch (PayPalIpnInvalidException e) {
			// TODO Auto-generated catch block
			log.error(
					"invalid nella ricezione IPN da paypal, messaggion non corretto",
					e);
		} catch (PayPalIpnErrorException e) {
			// TODO Auto-generated catch block
			log.error("stringa non prevista nella ricezione IPN da paypal", e);
			throw e;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(
					"errore imprevisto sull'invio del messaggio IPN verso paypal",
					e);
			throw e;
		}
	}

	private boolean exitsIdTransaction(String idTransaction, String txStato)
			throws ContradaExceptionBloccante {
		// TODO Auto-generated method stub
		return GestioneTesseraBD.existIdTrasaction(idTransaction, txStato);

	}

	public void sendMailPayment(OrderDTO order, String messaggio) {
		// TODO Auto-generated method stub
		String content = null;

		try {
			content = "gentile protettore, \n";
			content += messaggio;
			content += "\n\n La Commissione Protettorato";
			BaseMail.inviaMail(new String[] { order.getUser() },
					"Pagamento Online Protettorato", content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("errore invio mail protettorato IpnPayPal", e);
		}
	}

}
