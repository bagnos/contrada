package it.othala.payment.paypal.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class OrderPayPalDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String returnUrl;
	private String cancelUrl;
	private String imageUrl;
	private String redirectUrl;
	private OrderDTO order;
	private String locale;

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public OrderDTO getOrder() {
		return order;
	}

	public void setOrder(OrderDTO order) {
		this.order = order;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
