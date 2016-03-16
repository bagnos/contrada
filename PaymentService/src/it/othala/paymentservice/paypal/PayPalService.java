package it.othala.paymentservice.paypal;

import it.othala.payment.paypal.dto.DoExpressCheckoutPaymentDTO;
import it.othala.payment.paypal.dto.GetExpressCheckoutDetailsDTO;
import it.othala.payment.paypal.dto.ItemCartDTO;
import it.othala.payment.paypal.dto.OrderDTO;
import it.othala.payment.paypal.dto.OrderPayPalDTO;
import it.othala.payment.paypal.dto.ProfilePayPalDTO;
import it.othala.payment.paypal.dto.RefundTransactionDTO;
import it.othala.payment.paypal.dto.SetExpressCheckoutDTO;
import it.othala.payment.paypal.dto.TesseraPaymentDTO;
import it.othala.paymentservice.paypal.exception.PayPalException;
import it.othala.paymentservice.paypal.exception.PayPalFailureException;
import it.othala.paymentservice.paypal.exception.PayPalFundingFailureException;
import it.othala.paymentservice.paypal.exception.PayPalIpnErrorException;
import it.othala.paymentservice.paypal.exception.PayPalIpnInvalidException;
import it.othala.paymentservice.paypal.exception.PayPalPostRefundPaymentException;
import it.othala.paymentservice.paypal.utils.CommonUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import paypalnvp.core.PayPal;
import paypalnvp.core.PayPal.Environment;
import paypalnvp.fields.Currency;
import paypalnvp.fields.Payment;
import paypalnvp.fields.PaymentAction;
import paypalnvp.fields.PaymentItem;
import paypalnvp.profile.BaseProfile;
import paypalnvp.profile.Profile;
import paypalnvp.request.DoExpressCheckoutPayment;
import paypalnvp.request.GetExpressCheckoutDetails;
import paypalnvp.request.RefundTransaction;
import paypalnvp.request.SetExpressCheckout;

//import javax.servlet.http.HttpServletRequest;

public class PayPalService {

	private String redirectUrl = null;
	private final String L_PAYMENTREQUEST_0_DESCn = "L_PAYMENTREQUEST_0_DESC";
	private final String L_PAYMENTREQUEST_0_AMTm = "L_PAYMENTREQUEST_0_AMT";
	private final String L_PAYMENTREQUEST_0_QTYm = "L_PAYMENTREQUEST_0_QTY";
	private final String L_PAYMENTREQUEST_0_NUMBERm = "L_PAYMENTREQUEST_0_NUMBER";
	private final String L_ERRORCODEn = "L_ERRORCODE";

	private final String L_LONGMESSAGEn = "L_LONGMESSAGE";
	private List<String> errorCodes;
	private String errorMessage;
	private Map<String, String> paymentDetails;
	public static final String COMPLETED_STATUS = "Completed";
	private String username;
	private static Log log = LogFactory.getLog(PayPalService.class);
	private ProfilePayPalDTO profilePayPal = null;

	public String getUsername() {
		return username;
	}

	private PayPal pp = null;

	public PayPalService(ProfilePayPalDTO myProfile) {
		profilePayPal = myProfile;
		Profile prof = new BaseProfile.Builder(profilePayPal.getUserName(),
				profilePayPal.getPassword()).signature(
				profilePayPal.getSignature()).build();
		this.username = profilePayPal.getUserName();
		Environment env = getEnvironment(profilePayPal.getEnvironment());
		pp = new PayPal(prof, env);

	}

	public String getPayPalIpnURL() {
		return pp.getPayPalIpnUrl();
	}

	public ProfilePayPalDTO getInstanceProfile(String password,
			String signature, String username, String receveirMail,
			String locale, String returnUrl, String cancelUrl,
			String notifyUrl, String imageUrl, boolean ambienteTest) {
		ProfilePayPalDTO prof = new ProfilePayPalDTO();
		if (ambienteTest) {
			prof.setEnvironment("SANDBOX");
		}

		prof.setPassword(password);
		prof.setSignature(signature);
		prof.setUserName(username);
		prof.setReceiverEmail(receveirMail);
		prof.setLang(locale);

		prof.setReturnUrl(returnUrl);
		prof.setCancelUrl(cancelUrl);
		prof.setImageUrl(imageUrl);
		prof.setNotifyUrl(notifyUrl);

		return prof;

	}

	private OrderPayPalDTO valueOf(ProfilePayPalDTO profile, OrderDTO order) {
		OrderPayPalDTO ordPp = new OrderPayPalDTO();
		ordPp.setOrder(order);
		ordPp.setCancelUrl(profile.getCancelUrl());
		ordPp.setImageUrl(profile.getImageUrl());
		ordPp.setLocale(profile.getLang());
		ordPp.setRedirectUrl(profile.getRedirectUrl());
		ordPp.setReturnUrl(profile.getReturnUrl());

		return ordPp;
	}

	public HashMap<String, String> getNotificationIPN(String request)
			throws PayPalIpnInvalidException, PayPalIpnErrorException,
			PayPalException {

		String response = null;
		HashMap<String, String> responseMap = new HashMap<String, String>();
		try {
			log.info(String.format("prosessIpnMessage, request for verify: %s",
					request));
			response = pp.setResponseIPN(request);
			log.info("responseIPN:" + response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			throw new PayPalException(e);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new PayPalException(e);
		}
		if (response.equalsIgnoreCase("VERIFIED")) {
			String[] pairs = request.split("&"); // split nvp
			if (pairs != null) {
				for (String pair : pairs) {
					String[] nvp = pair.split("="); // split key value
					try {
						if (nvp != null && nvp.length > 1) {
							responseMap.put(nvp[0],
									URLDecoder.decode(nvp[1], "UTF-8"));
						}
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						throw new PayPalException(e);
					}
				}
			}
			return responseMap;

		} else if (response.equalsIgnoreCase("INVALID")) {
			throw new PayPalIpnInvalidException(response);
		} else {
			throw new PayPalIpnErrorException(response);
		}

	}

	public SetExpressCheckoutDTO setExpressCheckout(OrderDTO order)
			throws PayPalFailureException, PayPalException {
		OrderPayPalDTO cart = valueOf(profilePayPal, order);
		SetExpressCheckoutDTO setExpChecktDTO = null;

		Payment payment = getPayment(cart);

		SetExpressCheckout setEC = new SetExpressCheckout(payment,
				cart.getReturnUrl(), cart.getCancelUrl());
		setEC.setImage(cart.getImageUrl());
		setEC.setPaymentAction(PaymentAction.SALE);

		try {
			pp.setResponse(setEC);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PayPalException(e);
		}

		Map<String, String> response = setEC.getNVPResponse();
		if (response != null && !response.isEmpty()) {
			setExpChecktDTO = getExpressCheckOutDTO(response);
		}

		return setExpChecktDTO;
	}

	public RefundTransactionDTO refundTransaction(String idTransaction,
			BigDecimal amt, boolean fgPartial, List<String> articles,
			String refundId) throws PayPalException, PayPalFailureException,
			PayPalPostRefundPaymentException {
		RefundTransaction refTransaction;
		RefundTransactionDTO refundTransactionDTO = null;

		if (fgPartial) {
			refTransaction = RefundTransaction.getPartialRefund(idTransaction,
					CommonUtils.getImportoNoCurrency(amt));
			refTransaction.getNVPRequest().put("CURRENCYCODE", "EUR");
			StringBuilder sb = new StringBuilder();
			if (articles != null && !articles.isEmpty()) {
				for (String s : articles) {
					sb.append(s);
					sb.append("\t");
				}
				refTransaction.setNote(sb.toString());

			}
		} else {
			refTransaction = RefundTransaction.getFullRefund(idTransaction);
		}
		refTransaction.getNVPRequest().put("STOREID", refundId);

		try {
			pp.setResponse(refTransaction);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PayPalException(e);
		}

		Map<String, String> response = refTransaction.getNVPResponse();
		if (response != null && !response.isEmpty()) {
			refundTransactionDTO = getRefundTransactionDTO(response);
		}
		return refundTransactionDTO;
	}

	/**
	 * 
	 * @param details
	 * @return restituita in caso di errore 10486 da PayPal,typically an invalid
	 *         or maxed out credit card
	 * @throws PayPalFundingFailureException
	 *             restituito in caso di errore imprevisto da PayPal
	 * @throws PayPalException
	 *             restituito in caso di errore gestito da PayPal, ACK=Failure
	 * @throws PayPalFailureException
	 *             Pagamento non effettuato, ack=failure nel messaggio di
	 *             ritorno
	 * 
	 */

	public DoExpressCheckoutPaymentDTO doExpressCheckoutPayment(String token)
			throws PayPalFundingFailureException, PayPalException,
			PayPalFailureException {
		GetExpressCheckoutDetailsDTO details = getExpressCheckoutDetails(token);
		this.redirectUrl = profilePayPal.getRedirectUrl();
		DoExpressCheckoutPayment doCheck = new DoExpressCheckoutPayment(
				details.getToken(), PaymentAction.SALE, details.getPayerid(),
				details.getAmount(), details.getCurrencyCode(),
				details.getShipAmount(), details.getItemAmount());
		// doCheck.setUSESESSIONPAYMENTDETAILS(true);
		doCheck.setPaymentDetails(paymentDetails);
		doCheck.setPAYMENTREQUEST_0_NOTIFYURL(profilePayPal.getNotifyUrl());

		try {
			pp.setResponse(doCheck);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PayPalException(e);
		}

		Map<String, String> response = doCheck.getNVPResponse();
		return getDoExpressCheckoutPaymentDTO(response, details.getToken());
	}

	public GetExpressCheckoutDetailsDTO getExpressCheckoutDetails(String token)
			throws PayPalException, PayPalFailureException {
		GetExpressCheckoutDetails getExpress = new GetExpressCheckoutDetails(
				token);

		try {
			pp.setResponse(getExpress);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new PayPalException(e);
		}
		Map<String, String> response = getExpress.getNVPResponse();
		GetExpressCheckoutDetailsDTO detailDto = getExpressCheckOutDetailsDTO(response);
		return detailDto;
	}

	public static Environment getEnvironment(String env) {
		Environment environment = null;
		if (env == null || env.isEmpty()) {
			environment = Environment.LIVE;
		} else {
			environment = Environment.valueOf(env);
		}

		return environment;
	}

	private Payment getPayment(OrderPayPalDTO cart) {
		PaymentItem item = null;
		List<PaymentItem> items = new ArrayList<PaymentItem>();
		String description;

		for (int i = 0; i <= cart.getOrder().getCart().size() - 1; i++) {
			item = new PaymentItem();
			String descrArt = cart.getOrder().getCart().get(i).getTxTessera();
			description = descrArt;
			item.setDescription(description);
			item.setAmount(cart.getOrder().getCart().get(i).getImTessera().toString());
				
			item.setQuantity(1);
			item.setItemNumber(String.valueOf(cart.getOrder().getCart().get(i).getIdTessera()));

			items.add(item);
		}

		// Payment payment = new Payment(cart.getTotalPriceOrder().toString());
		BigDecimal itemOrder = BigDecimal.ZERO;
		for (TesseraPaymentDTO tes:cart.getOrder().getCart())
		{
			itemOrder=tes.getImTessera().add(itemOrder);
		}
		Payment payment = new Payment(itemOrder
				.setScale(2, RoundingMode.HALF_UP).toString(), items);
		payment.setCurrency(Currency.EUR);
		payment.setAllowingNote(true);
		payment.setLocalCode(cart.getLocale().toUpperCase());
		// payment.setOverrideShippingAddress();
		payment.setSuppressingShippingAddress();
		payment.setCustomField(String.valueOf(cart.getOrder().getIdOrder()));
		payment.setShippingAmount(BigDecimal.ZERO.setScale(2,
				RoundingMode.HALF_UP).toString());
		payment.setPaymentRequestITEMAMT(itemOrder.setScale(2,
				RoundingMode.HALF_UP).toString());

		return payment;
	}

	private SetExpressCheckoutDTO getExpressCheckOutDTO(
			Map<String, String> response) throws PayPalFailureException {
		StringBuilder sn = new StringBuilder();
		for (String e : response.keySet()) {
			sn.append(String.format("%s=%s;", e, response.get(e).toString()));
		}

		SetExpressCheckoutDTO setExpChecktDTO = new SetExpressCheckoutDTO();
		if (response.get("ACK").toString().equalsIgnoreCase("Success")) {
			setExpChecktDTO.setToken(response.get("TOKEN").toString());

			setExpChecktDTO.setRedirectUrl(pp.getRedirectUrl(response.get(
					"TOKEN").toString()));

			setExpChecktDTO.setOkMessage(sn.toString());

		} else {
			updateError(response);
			throw new PayPalFailureException(sn.toString() + " " + errorMessage);

		}

		return setExpChecktDTO;
	}

	private RefundTransactionDTO getRefundTransactionDTO(
			Map<String, String> response) throws PayPalFailureException,
			PayPalPostRefundPaymentException {
		StringBuilder sn = new StringBuilder();
		for (String e : response.keySet()) {
			sn.append(String.format("%s=%s;", e, response.get(e).toString()));
		}

		RefundTransactionDTO refundTransactionDTO = new RefundTransactionDTO();
		if (response.get("ACK").toString().equalsIgnoreCase("Success")) {
			try {
				refundTransactionDTO.setPENDINGREASON(response.get(
						"PENDINGREASON").toString());
				refundTransactionDTO.setREFUNDSTATUS(response.get(
						"REFUNDSTATUS").toString());
				refundTransactionDTO.setREFUNDTRANSACTIONID(response.get(
						"REFUNDTRANSACTIONID").toString());
				refundTransactionDTO.setTOTALREFUNDEDAMT(new BigDecimal(
						response.get("GROSSREFUNDAMT")));
				refundTransactionDTO.setOkMessage(sn.toString());
			} catch (Exception e) {
				throw new PayPalPostRefundPaymentException(e, "",
						"errore nella costruzione di RefundTransactionDTO ");
			}

		} else {
			updateError(response);
			throw new PayPalFailureException(sn.toString() + " " + errorMessage);

		}

		return refundTransactionDTO;
	}

	private DoExpressCheckoutPaymentDTO getDoExpressCheckoutPaymentDTO(
			Map<String, String> response, String token) throws PayPalException,
			PayPalFundingFailureException, PayPalFailureException {
		StringBuilder sn = new StringBuilder();
		for (String e : response.keySet()) {
			sn.append(String.format("%s=%s;", e, response.get(e).toString()));
		}
		DoExpressCheckoutPaymentDTO checkDTO = new DoExpressCheckoutPaymentDTO();
		if (response.get("ACK").toString().equalsIgnoreCase("Success")) {
			checkDTO.setPAYMENTINFO_0_TRANSACTIONID(response
					.get("PAYMENTINFO_0_TRANSACTIONID"));
			checkDTO.setNote(response.get("NOTE"));
			checkDTO.setPAYMENTINFO_0_PAYMENTSTATUS(response
					.get("PAYMENTINFO_0_PAYMENTSTATUS"));
			checkDTO.setPAYMENTINFO_0_PENDINGREASON(response
					.get("PAYMENTINFO_0_PENDINGREASON"));
			checkDTO.setL_PAYMENTINFO_0_FMF(response.get("L_PAYMENTINFO_0_FMF"));
			checkDTO.setOkMessage(sn.toString());

		} else {
			updateError(response);

			if (isFundinFailure()) {
				throw new PayPalFundingFailureException(errorMessage,
						pp.getRedirectUrl(token));
			} else {
				throw new PayPalFailureException(sn.toString() + " "
						+ errorMessage);
			}

		}
		return checkDTO;
	}

	private GetExpressCheckoutDetailsDTO getExpressCheckOutDetailsDTO(
			Map<String, String> response) throws PayPalFailureException {
		StringBuilder sn = new StringBuilder();
		for (String e : response.keySet()) {
			sn.append(String.format("%s=%s;", e, response.get(e).toString()));
		}

		GetExpressCheckoutDetailsDTO getExpressCheckoutDetailsDTO = new GetExpressCheckoutDetailsDTO();
		if (response.get("ACK").toString().equalsIgnoreCase("Success")) {
			getExpressCheckoutDetailsDTO.setToken(response.get("TOKEN")
					.toString());
			getExpressCheckoutDetailsDTO.setCustom(response.get(
					"PAYMENTREQUEST_0_CUSTOM").toString());
			getExpressCheckoutDetailsDTO.setPayerid(response.get("PAYERID")
					.toString());
			getExpressCheckoutDetailsDTO.setEmail(response.get("EMAIL")
					.toString());
			getExpressCheckoutDetailsDTO.setCorrelationid(response.get(
					"CORRELATIONID").toString());
			getExpressCheckoutDetailsDTO.setFirstname(response.get("FIRSTNAME")
					.toString());
			getExpressCheckoutDetailsDTO.setLastname(response.get("LASTNAME")
					.toString());
			getExpressCheckoutDetailsDTO.setCurrencyCode(response.get(
					"CURRENCYCODE").toString());
			getExpressCheckoutDetailsDTO.setAmount(new BigDecimal(response.get(
					"PAYMENTREQUEST_0_AMT").toString()));
			getExpressCheckoutDetailsDTO.setItemAmount(new BigDecimal(response
					.get("PAYMENTREQUEST_0_ITEMAMT").toString()));
			getExpressCheckoutDetailsDTO.setShipAmount(new BigDecimal(response
					.get("PAYMENTREQUEST_0_SHIPPINGAMT").toString()));

			/*
			 * getExpressCheckoutDetailsDTO.getShippingAddress().setCap(
			 * response.get("PAYMENTREQUEST_0_SHIPTOZIP").toString());
			 * 
			 * String[] nomeCompleto =
			 * response.get("PAYMENTREQUEST_0_SHIPTONAME"
			 * ).toString().split(" ");
			 * 
			 * getExpressCheckoutDetailsDTO.getShippingAddress().setNome(
			 * nomeCompleto[0]);
			 * getExpressCheckoutDetailsDTO.getShippingAddress(
			 * ).setCognome(nomeCompleto[1]);
			 * getExpressCheckoutDetailsDTO.getShippingAddress().setVia(
			 * (response.get("PAYMENTREQUEST_0_SHIPTOSTREET").toString()));
			 * getExpressCheckoutDetailsDTO
			 * .getShippingAddress().setProvincia(response
			 * .get("PAYMENTREQUEST_0_STATE"));
			 * getExpressCheckoutDetailsDTO.getShippingAddress().setNazione(
			 * response.get("PAYMENTREQUEST_0_SHIPTOCOUNTRYCODE"));
			 * 
			 * getExpressCheckoutDetailsDTO.getShippingAddress().setComune(response
			 * .get("PAYMENTREQUEST_0_SHIPTOCITY"));
			 * getExpressCheckoutDetailsDTO
			 * .getShippingAddress().setTel(response.
			 * get("PAYMENTREQUEST_0_SHIPTOPHONENUM"));
			 */
			updateCart(response, getExpressCheckoutDetailsDTO);

			getExpressCheckoutDetailsDTO.setOkMessage(sn.toString());

		} else {
			updateError(response);
			throw new PayPalFailureException(sn.toString() + " " + errorMessage);

		}

		return getExpressCheckoutDetailsDTO;
	}

	/*
	 * private Profile getProfile() { Profile user = new
	 * BaseProfile.Builder(prop.getProperty("Username"),
	 * prop.getProperty("Password")).signature(
	 * prop.getProperty("Signature")).build(); return user; }
	 */

	private boolean isFundinFailure() {
		return (errorMessage).contains("10486");

	}

	private void updateCart(Map<String, String> response,
			GetExpressCheckoutDetailsDTO details) {
		int i = 0;

		ItemCartDTO item = null;
		String key = null;
		paymentDetails = new Hashtable<String, String>();
		while (true) {
			key = L_PAYMENTREQUEST_0_DESCn + i;
			if (response.containsKey(key)) {
				item = new ItemCartDTO();
				item.setDescription(response.get(key));
				paymentDetails.put(key, response.get(key));
				paymentDetails.put("L_DESC" + i, response.get(key));

				key = L_PAYMENTREQUEST_0_QTYm + i;
				item.setQta(Integer.valueOf(response.get(key)));
				paymentDetails.put(key, response.get(key));

				key = L_PAYMENTREQUEST_0_AMTm + i;
				item.setPrice(new BigDecimal(response.get(key)));
				paymentDetails.put(key, response.get(key));
				if (item.getPrice().compareTo(BigDecimal.ZERO) < 0) {
					details.setDiscount(item.getPrice());
				}

				key = L_PAYMENTREQUEST_0_NUMBERm + i;
				item.setCode(response.get(key));
				paymentDetails.put(key, response.get(key));
				details.getShoppingCartOrder().add(item);
				i++;
			} else {
				break;
			}
		}

	}
	
	public static boolean isPaymentPending(String paypalStatus) {
		// TODO Auto-generated method stub

		if (paypalStatus.equalsIgnoreCase("PENDING")) {
			return true;
		}

		if (paypalStatus.equalsIgnoreCase("PROCESSED")) {
			return true;
		}

		if (paypalStatus.equalsIgnoreCase("DELAYED")) {
			return true;
		}

		return false;

	}
	
	public static boolean isPaymentKO(String paypalStatus) {
		// TODO Auto-generated method stub

		if (paypalStatus.equalsIgnoreCase("DENIED")) {
			return true;
		}

		if (paypalStatus.equalsIgnoreCase("FAILED")) {
			return true;
		}

		if (paypalStatus.equalsIgnoreCase("EXPIRED")) {
			return true;
		}

		if (paypalStatus.equalsIgnoreCase("REVERSED")) {
			return true;
		}

		return false;

	}

	public static boolean isPaymentCompleted(String paypalStatus) {
		// TODO Auto-generated method stub
		if (paypalStatus.equalsIgnoreCase("COMPLETED") || paypalStatus.equalsIgnoreCase("COMPLETED_FUNDS_HELD")) {
			return true;
		}

		return false;

	}

	public static boolean isPaymenRefunded(String paypalStatus) {
		// TODO Auto-generated method stub
		if (paypalStatus.equalsIgnoreCase("REFUNDED")) {
			return true;
		}

		return false;

	}

	private void updateError(Map<String, String> response) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		String key = null;
		errorCodes = new ArrayList<String>();

		while (true) {
			key = L_ERRORCODEn + i;
			if (response.containsKey(key)) {
				sb.append(response.get(key));
				errorCodes.add(response.get(key));

				/*
				 * sb.append(System.getProperty("line.separator")); key =
				 * L_SHORTMESSAGEn + i; sb.append(response.get(key));
				 */
				sb.append(" - ");
				key = L_LONGMESSAGEn + i;
				sb.append(response.get(key));
				sb.append(System.getProperty("line.separator"));
				i++;
			} else {
				break;
			}
		}
		errorMessage = sb.toString();

	}

	/*
	 * private void updateState(DoExpressCheckoutPaymentDTO checkDTO) {
	 * checkDTO.setStatePayPal(TypeStatePayPal.COMPLETED); if
	 * (checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS
	 * ().equalsIgnoreCase("In progress") ||
	 * checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS
	 * ().equalsIgnoreCase("Pending verification") ||
	 * checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS().equalsIgnoreCase("Processing"))
	 * { checkDTO.setStatePayPal(TypeStatePayPal.PROCESSING); return; } if
	 * (checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS().equalsIgnoreCase("Denied") ||
	 * checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS().equalsIgnoreCase("Failed") ||
	 * checkDTO.getPAYMENTINFO_0_PAYMENTSTATUS().equalsIgnoreCase("Refused")) {
	 * checkDTO.setStatePayPal(TypeStatePayPal.REFUSED); return; } }
	 */

}
