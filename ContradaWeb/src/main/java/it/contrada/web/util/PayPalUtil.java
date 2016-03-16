package it.contrada.web.util;

import it.othala.payment.paypal.dto.ProfilePayPalDTO;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class PayPalUtil {

	private static Properties prop=null;
	private static void loadProp() throws IOException {
		InputStream input = null;
		if (prop == null) {
			try {
				input = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("payment.properties");
				prop = new Properties();
				prop.load(input);
			} finally {
				if (input != null) {
					input.close();
				}
			}
		}
	}

	public static ProfilePayPalDTO getProfile(String locale,HttpServletRequest request) throws IOException {

		loadProp();

		ProfilePayPalDTO prof = new ProfilePayPalDTO();
		prof.setEnvironment(prop.getProperty("environment"));
		prof.setPassword(prop.getProperty("Password"));
		prof.setSignature(prop.getProperty("Signature"));
		prof.setUserName(prop.getProperty("Username"));
		prof.setReceiverEmail(prop.getProperty("receiverEmail"));
		prof.setLang(locale);

		prof.setReturnUrl(prop.getProperty("returnUrl").replace("#{request.remoteHost}", request.getServerName()));
		prof.setImageUrl(prop.getProperty("imageUrl").replace("#{request.remoteHost}", request.getServerName()));
		prof.setCancelUrl(prop.getProperty("cancelUrl").replace("#{request.remoteHost}", request.getServerName()));
		prof.setNotifyUrl(prop.getProperty("notifyUrl").replace("#{request.remoteHost}", request.getServerName()));

		return prof;
	}
}
